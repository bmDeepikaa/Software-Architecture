/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2.magazine;

import java.io.File;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @class MagazineClient
 * @brief main client class that includes the start method that gets the gui of
 * the entire magazine service up and logic to link gui to the object
 *
 * @author deepikaa
 * @version 02
 * @date 25/03/2024
 */
public class MagazineClient extends Application {

    private MagazineGUI gui;
    private MagazineManager magazineManager = new MagazineManager();
    private String magazineName;
    private Magazine magazine = new Magazine();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        gui = new MagazineGUI(primaryStage);
        // Program starts at create mode
        gui.createPage();
        switchToCreate();
        mainButtons();
    }

    private void addMagazine() {
        gui.addMagazine();
        mainButtons();

        gui.getSubmitButton().setOnAction(e -> {
            magazineName = gui.getMagazineNameTextField().getText().toLowerCase();
            // To check if magazine field is empty
            if (!magazineName.trim().isEmpty()) {
                // To limit the magazine length at 50 else throw a alert message
                if (magazineName.length() > 50) {
                    magazineManager.showAlert("Magazine name cannot be more than 50 characters");
                } else {
                    // Check for duplication of magazine name
                    if (magazineManager.compareMagazineName(magazineName)) {
                        magazineManager.showAlert("'" + magazineName + "' already exist");
                    } else {
                        magazineManager.addMagazine(magazineName);
                        switchToCreate();
                    }
                }
            } else {
                gui.getMagazineNameTextField().clear();
                magazineManager.showAlert("Magazine name cannot be empty");
            }
        });

        buttonEnabledForCreate();
    }

    private void buttonEnabledForCreate() {
        gui.getViewButton().setOnAction(e -> checkMagazineView());
        gui.getEditButton().setOnAction(e -> checkMagazineEdit());
    }

    private void loadMagazine() {
        gui.loadMagazine();

        // Check if file is selected
        if (gui.getFile() != null) {
            for (File file : gui.getFile()) {
                magazineName = file.getName().replace(".ser", "");
                magazineManager.loadMagazineFromFile(magazineName);
            }
        } else {
            magazineManager.showAlert("No file selected");
        }
    }

    private void saveMagazine() {
        gui.saveMagazine();
        mainButtons();

        gui.getMagazineChoice().getItems().addAll(magazineManager.getAllMagazineNames());

        gui.getSubmitButton().setOnAction(e -> {
            magazineName = gui.getMagazineChoice().getSelectionModel().getSelectedItem();
            // Check if magazine is selected and not empty
            if (magazineName != null) {
                magazineManager.saveMagazineToFile(magazineName);
                switchToCreate();
            } else {
                gui.getMagazineChoice().setValue(null);
                magazineManager.showAlert("No magazine selected");
            }
        });
    }

    private void addSupplement(String magazineName) {
        gui.addSupplement();
        mainButtons();

        magazine = magazineManager.getMagazine(magazineName);

        gui.getSubmitButton().setOnAction(e -> {
            String supplementName = gui.getSupplementNameTextField().getText().trim().toLowerCase();
            String supplementCostText = gui.getSupplementCostTextField().getText().trim();
                           

            // check if the supplement is empty , if supplement cost is not numbers there will be alert else save the supplement details to the service
            if (!supplementName.isEmpty()) {
                try {
                    double supplementCost = Double.parseDouble(supplementCostText);
                    Supplement supplement = new Supplement(supplementName, supplementCost);
                    if(!checkDuplicateSupplement(supplementName,magazine.getSupplements())){
                    magazine.addSupplement(supplement);
                    switchToEdit(magazineName);
                    }
                } catch (Exception ex) {
                    gui.getSupplementCostTextField().clear();
                    magazineManager.showAlert("Please input numbers only for cost of supplement");
                }
            } else {
                gui.getSupplementNameTextField().clear();
                magazineManager.showAlert("Supplement name cannot be empty");
            }
        });
    }
 

    private void addCustomer(String magazineName) {
        gui.addCustomer();
        mainButtons();

        magazine = magazineManager.getMagazine(magazineName);

        addCustomerInputData(magazine);

        ArrayList<Supplement>[] supplements = new ArrayList[]{new ArrayList<>()};

        gui.getSupplementChoice().getSelectionModel().selectedItemProperty().addListener((ob, oldValue, newValue) -> {
            supplements[0] = new ArrayList<>(gui.getSupplementChoice().getSelectionModel().getSelectedItems());
        });

        //  event handler for customer type
        gui.getTypeOfCustomer().setOnAction(e -> {
            switch (gui.getTypeOfCustomer().getValue()) {
                case "Paying Customer":
                    setPayingCustomerVisible();
                    break;
                case "Associate Customer":
                    setAssociateCustomerVisible();
                    break;
            }
        });

        //  event handler for the submit button
        gui.getSubmitButton().setOnAction(e -> {
            ArrayList<Boolean> validateList = new ArrayList<>();
            validateList = addCustomerValidation(validateList);

            // Check validation array
            Boolean isValidated = magazineManager.validateArray(validateList);

            // All fields validated
            if (isValidated) {
                switch (gui.getTypeOfCustomer().getValue()) {
                    // If paying customer, add details to paying customer object
                    case "Paying Customer":
                        handlePayingCustomer(supplements);
                        break;
                    // If associate customer, add details to associate customer object
                    case "Associate Customer":
                        handleAssociateCustomer(supplements);
                        break;
                }
                switchToEdit(magazineName);
            }
        });
    }

    private void handlePayingCustomer(ArrayList<Supplement>[] supplements) {
        PayingCustomer payingCustomer = new PayingCustomer();
        setCustomerData(payingCustomer, supplements);
        payingCustomer.setPaymentMethod(new PaymentMethod(
                gui.getCardType().getValue(),
                Integer.parseInt(gui.getAccountNumberTextField().getText())));
        // Update magazine service
        magazine.addCustomer(payingCustomer);
    }

    private void handleAssociateCustomer(ArrayList<Supplement>[] supplements) {
        AssociateCustomer associateCustomer = new AssociateCustomer();
        setCustomerData(associateCustomer, supplements);
        PayingCustomer selectedPayingCustomer = gui.getPayingCustomerChoice().getValue();
        selectedPayingCustomer.addAssociateCustomer(associateCustomer);
        // Update magazine service
        magazine.addCustomer(associateCustomer);
    }

    private void editSupplement(String magazineName) {
        gui.editSupplement();
        mainButtons();

        magazine = magazineManager.getMagazine(magazineName);

        // Adding existing supplements
        gui.getSupplementChoice().getItems().addAll(magazine.getSupplements());

        // Monitor selection
        gui.getSupplementChoice().getSelectionModel().selectedItemProperty().addListener((ob, oldValue, newValue) -> {
            gui.getSupplementNameTextField().setText(newValue.getName());
            gui.getSupplementCostTextField().setText(String.valueOf(newValue.getCost()));
        });

        // Set event handler for the submit button
        gui.getSubmitButton().setOnAction(e -> {
            Supplement supplement = gui.getSupplementChoice().getSelectionModel().getSelectedItem();
            // If no selection
            if (supplement != null) {
                String supplementName = gui.getSupplementNameTextField().getText().trim().toLowerCase();
                // If no supplement name
                if (!supplementName.isEmpty()) {
                    try {
                        if(!checkDuplicateSupplement(supplementName,magazine.getSupplements())){
                        supplement.setName(supplementName);
                        supplement.setCost(Double.parseDouble(gui.getSupplementCostTextField().getText()));
                        switchToEdit(magazineName);
                        }
                    } catch (Exception ex) {
                        gui.getSupplementCostTextField().clear();
                        magazineManager.showAlert("Please input numbers only for the cost of the supplement");
                    }
                } else {
                    gui.getSupplementNameTextField().clear();
                    magazineManager.showAlert("Supplement name cannot be empty ");
                }
            } else {
                magazineManager.showAlert("Please select a supplement to edit");
            }
        });
    }

    private void editCustomer(String magazineName) {
        gui.editCustomer();
        mainButtons();
        magazine = magazineManager.getMagazine(magazineName);
        editCustomerInputData();

        ArrayList<Supplement>[] supplements = new ArrayList[]{new ArrayList<>()};
        gui.getSupplementChoice().getSelectionModel().selectedItemProperty().addListener((ob, oldValue, newValue) -> {
            supplements[0] = new ArrayList<>(gui.getSupplementChoice().getSelectionModel().getSelectedItems());
        });

        // Set  based on selected customer
        gui.getCustomerChoice().getSelectionModel().selectedItemProperty().addListener((ob, oldValue, newValue) -> {
            editCustomerSetValue(newValue, magazine);
        });
        gui.getSubmitButton().setOnAction(e -> {
            ArrayList<Boolean> validateList = new ArrayList<>();
            validateList = editCustomerValidation(validateList);
            // Check validation array
            Boolean isValidated = magazineManager.validateArray(validateList);
            if (isValidated) {
                editCustomerSetData(supplements, magazine);
                switchToEdit(magazineName);
            }
        });
    }

    private void deleteSupplement(String magazineName) {
        gui.deleteSupplement();
        mainButtons();

        magazine = magazineManager.getMagazine(magazineName);

        // Add existing supplements -> list view
        gui.getSupplementChoice().getItems().addAll(magazine.getSupplements());

        // Monitor the choice of supplement
        gui.getSupplementChoice().getSelectionModel().selectedItemProperty().addListener((ob, oldValue, newValue) -> {
            showSupplementDetails(newValue);
        });

        gui.getSubmitButton().setOnAction(e -> {
            Supplement supplement = gui.getSupplementChoice().getSelectionModel().getSelectedItem();
            if (supplement != null) {
                if (isSupplementUse(supplement, magazine)) {
                    magazineManager.showAlert("You are not able to delete a supplement that is in use");
                } else {
                    magazine.removeSupplement(supplement);
                    switchToEdit(magazineName);
                }
            } else {
                magazineManager.showAlert("Please select a supplement to delete");
            }
        });
    }

    private boolean isSupplementUse(Supplement supplement, Magazine magazine) {
        for (Customer customer : magazine.getCustomers()) {
            for (Supplement supp : customer.getSupplement()) {
                if (supp.equals(supplement)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void deleteCustomer(String magazineName) {
        gui.deleteCustomer();
        mainButtons();

        magazine = magazineManager.getMagazine(magazineName);

        // Add existing customers -> list view
        gui.getCustomerChoice().getItems().addAll(magazine.getCustomers());

        // Monitor selection of the customer 
        gui.getCustomerChoice().getSelectionModel().selectedItemProperty().addListener((ob, oldValue, newValue) -> {
            showCustomerInfo(newValue);
        });

        gui.getSubmitButton().setOnAction(e -> {
            Customer customer = gui.getCustomerChoice().getSelectionModel().getSelectedItem();
            if (customer != null) {
                if (customer instanceof PayingCustomer) {
                    PayingCustomer selectedPayingCustomer = (PayingCustomer) customer;
                    if (selectedPayingCustomer.containsAssociateCustomer()) {
                        magazineManager.showAlert("You cannot delete a customer that has a associate customer");
                    } else {
                        deleteAndSwitch(customer, magazineName);
                    }
                } else if (customer instanceof AssociateCustomer) {
                    deleteAssociateCustomerFromPayingCustomer(customer, magazine);
                    deleteAndSwitch(customer, magazineName);
                }
            } else {
                magazineManager.showAlert("Please select a customer to delete");
            }
        });
    }

    private void deleteAndSwitch(Customer customer, String magazineName) {
        magazine.removeCustomer(customer);
        switchToEdit(magazineName);
    }

    //check magazine before viewing it
    private void checkMagazineView() {
        gui.magazineViewCheck();

        gui.getMagazineChoice().getItems().addAll(magazineManager.getAllMagazineNames());

        gui.getSubmitButton().setOnAction(e -> {
            magazineName = gui.getMagazineChoice().getSelectionModel().getSelectedItem();
            if (magazineName != null) {
                switchToView(magazineName);
                gui.getMagazineChoice().setValue(null);
            } else {
                gui.getMagazineChoice().setValue(null);
                magazineManager.showAlert("No magazine selected");
            }
        });

        buttonEnabledForView();
    }

    private void buttonEnabledForView() {
        gui.getCreateButton().setOnAction(e -> switchToCreate());
        gui.getEditButton().setOnAction(e -> checkMagazineEdit());
    }

    // To perform check of magazine before editing
    private void checkMagazineEdit() {
        gui.magazineEditCheck();

        gui.getMagazineChoice().getItems().addAll(magazineManager.getAllMagazineNames());

        gui.getSubmitButton().setOnAction(e -> {
            magazineName = gui.getMagazineChoice().getSelectionModel().getSelectedItem();
            if (magazineName != null) {
                switchToEdit(magazineName);
                gui.getMagazineChoice().setValue(null);
            } else {
                gui.getMagazineChoice().setValue(null);
                magazineManager.showAlert("No magazine selected");
            }
        });
        buttonEnabledForEdit();

    }

    private void buttonEnabledForEdit() {
        gui.getViewButton().setOnAction(e -> checkMagazineView());
        gui.getCreateButton().setOnAction(e -> switchToCreate());
    }

    

    private void addCustomerInputData(Magazine magazine) {
        gui.getTypeOfCustomer().getItems().addAll("Paying Customer", "Associate Customer");

        // Add supplements to list view
        gui.getSupplementChoice().getItems().addAll(magazine.getSupplements());

        // Create customer arraylist and add all customers
        ArrayList<Customer> customerList = new ArrayList<>();
        ArrayList<PayingCustomer> payingCustomerList = new ArrayList<>();
        customerList.addAll(magazine.getCustomers());

        // Check if paying customer
        for (Customer customer : customerList) {
            if (customer instanceof PayingCustomer) {
                payingCustomerList.add((PayingCustomer) customer);
            }
        }
        gui.getPayingCustomerChoice().getItems().addAll(payingCustomerList);
        gui.getPayingCustomerChoice().setPromptText("Select One");
    }
    
   

    private ArrayList<Boolean> addCustomerValidation(ArrayList<Boolean> vList) {
        // Validation for customer type field
        if (gui.getTypeOfCustomer().getSelectionModel().isEmpty()) {
            magazineManager.showAlert("Please select type of customer");
            vList.add(false);
        } else {
            vList.add(true);
        }

        // Validation for customer name
         magazineManager.validateIfEmpty(gui.getCustomersNameTextField(), "Customer name cannot be empty ", vList);
       String cusName=gui.getCustomersNameTextField().getText().trim();
       if(!cusName.isEmpty()){
           if(!checkDuplicateCustomer(magazine.getCustomers(),cusName)){
               vList.add(true);
           }else
           {
               vList.add(false);
           }
           
       }
       
         // Validate email address
        if (magazineManager.isValidEmailAddress(gui.getEmailAddressTextField().getText())) {
            vList.add(true);
        } else {
            magazineManager.showAlert("Invalid email address");
            vList.add(false);
        }

        magazineManager.validateIfEmpty(gui.getStreetNumberTextField(), "Street number cannot be empty ", vList);
        magazineManager.validateIfEmpty(gui.getStreetNameTextField(), "Street name cannot be empty ", vList);
        magazineManager.validateIfEmpty(gui.getSuburbTextField(), "Suburb cannot be empty ", vList);
        magazineManager.validateIfEmpty(gui.getPostCodeTextField(), "Postcode cannot be empty ", vList);

        // Validation for supplement field
        if (gui.getSupplementChoice().getSelectionModel().isEmpty()) {
            magazineManager.showAlert("Please select at least one supplement");
            vList.add(false);
        } else {
            vList.add(true);
        }

        String customer = gui.getTypeOfCustomer().getValue();
        // Check if paying customer
        if (customer != null && customer.equals("Paying Customer")) {
            try {
                Integer.valueOf(gui.getAccountNumberTextField().getText());
                vList.add(true);
            } catch (Exception ex) {
                gui.getAccountNumberTextField().clear();
                magazineManager.showAlert("Please input numbers only for account number");
                vList.add(false);
            }
            if (gui.getCardType().getSelectionModel().isEmpty()) {
                magazineManager.showAlert("Please select card type");
                vList.add(false);
            } else {
                vList.add(true);
            }
        }
        // Check if associate customer
        if (customer != null && customer.equals("Associate Customer")) {
            if (gui.getPayingCustomerChoice().getSelectionModel().isEmpty()) {
                magazineManager.showAlert("Please select a paying customer");
                vList.add(false);
            } else {
                vList.add(true);
            }
        }
        return vList;
    }

    private void editCustomerInputData() {
        // Adding customers to list view
        gui.getCustomerChoice().getItems().addAll(magazine.getCustomers());

        // Adding supplements subscribed by respective customer
        gui.getSupplementChoice().getItems().addAll(magazine.getSupplements());

        // Adding all customers to list view
        ArrayList<Customer> customerList = new ArrayList<>();
        ArrayList<PayingCustomer> payingCustomerList = new ArrayList<>();
        customerList.addAll(magazine.getCustomers());

        // Check for paying customer
        for (Customer customer : customerList) {
            if (customer instanceof PayingCustomer) {
                payingCustomerList.add((PayingCustomer) customer);
            }
        }

        gui.getPayingCustomerChoice().getItems().addAll(payingCustomerList);
        // Card type field
        gui.getCardType().getItems().addAll("Credit Card", "Debit Card");
    }

    private void editCustomerSetValue(Customer cust, Magazine magazine) {
        if (cust instanceof PayingCustomer) {
            setPayingCustomerVisible();
            gui.getTypeOfCustomerTextField().setText("Paying Customer");
            PayingCustomer payingCustomer = (PayingCustomer) cust;
            gui.getAccountNumberTextField().setText(String.valueOf(payingCustomer.getPaymentMethod().getAccountNo()));
            gui.getCardType().setValue(payingCustomer.getPaymentMethod().getCardType());
        } else if (cust instanceof AssociateCustomer) {
            setAssociateCustomerVisible();
            gui.getTypeOfCustomerTextField().setText("Associate Customer");
            editCustomerSetPayingCustomer(cust, magazine);
        }

        gui.getCustomersNameTextField().setText(cust.getName());
gui.getEmailAddressTextField().setText(cust.getEmail());
        gui.getStreetNumberTextField().setText(cust.getAddress().getStreetNumber());
        gui.getStreetNameTextField().setText(cust.getAddress().getStreetName());
        gui.getSuburbTextField().setText(cust.getAddress().getSuburb());
        gui.getPostCodeTextField().setText(cust.getAddress().getPostcode());
        gui.getSupplements().getItems().setAll(cust.getSupplement());

        
    }

    private void editCustomerSetPayingCustomer(Customer cust, Magazine magazine) {
        for (Customer customer : magazine.getCustomers()) {
            if (customer instanceof PayingCustomer) {
                PayingCustomer payingCustomer = (PayingCustomer) customer;
                // Check for paying customer that has the associate customer
                if (payingCustomer.compareAssociateCustomer(cust.getName())) {
                    // Set associated paying customer
                    gui.getPayingCustomerChoice().setValue(payingCustomer);
                }
            }
        }
    }

    private ArrayList<Boolean> editCustomerValidation(ArrayList<Boolean> validateList) {
        // If no customer selected
        if (gui.getCustomerChoice().getSelectionModel().getSelectedItem() != null) {

            // Validation for customer name
            magazineManager.validateIfEmpty(gui.getCustomersNameTextField(), "Customer name cannot be empty", validateList);

            // Validate email address
            if (magazineManager.isValidEmailAddress(gui.getEmailAddressTextField().getText())) {
                validateList.add(true);
            } else {
                magazineManager.showAlert("Invalid email address");
                validateList.add(false);
            }

            // Validation for relevant fields
            magazineManager.validateIfEmpty(gui.getStreetNumberTextField(), "Street number cannot be empty", validateList);
            magazineManager.validateIfEmpty(gui.getStreetNameTextField(), "Street name cannot be empty", validateList);
            magazineManager.validateIfEmpty(gui.getSuburbTextField(), "Suburb cannot be empty ", validateList);
            magazineManager.validateIfEmpty(gui.getPostCodeTextField(), "Postcode cannot be empty ", validateList);

            // Validation for supplement field
            if (gui.getSupplementChoice().getSelectionModel().isEmpty()) {
                magazineManager.showAlert("Please select at least one supplement");
                validateList.add(false);
            } else {
                validateList.add(true);
            }

            // Check for paying customer type
            if (gui.getTypeOfCustomerTextField().getText().equals("Paying Customer")) {
                try {
                    Integer.parseInt(gui.getAccountNumberTextField().getText());
                    validateList.add(true);
                } catch (Exception ex) {
                    gui.getAccountNumberTextField().clear();
                    magazineManager.showAlert("Please input numbers only for account number");
                    validateList.add(false);
                }
            }
        } else {
            magazineManager.showAlert("Please select a customer to edit");
            validateList.add(false);
        }
        return validateList;
    }

    private void editCustomerSetData(ArrayList<Supplement>[] supplements, Magazine magazine) {
        Customer customer = gui.getCustomerChoice().getSelectionModel().getSelectedItem();
        // If paying customer selected, update all fields
        if (gui.getTypeOfCustomerTextField().getText().equals("Paying Customer")) {
            PayingCustomer selectedPayingCustomer = (PayingCustomer) customer;
            setCustomerData(selectedPayingCustomer, supplements);
            selectedPayingCustomer.setPaymentMethod(new PaymentMethod(
                    gui.getCardType().getValue(),
                    Integer.parseInt(gui.getAccountNumberTextField().getText())));
        } // If associate customer selected, update accordingly
        else if (gui.getTypeOfCustomerTextField().getText().equals("Associate Customer")) {
            AssociateCustomer selectedAssociateCustomer = (AssociateCustomer) customer;
            setCustomerData(selectedAssociateCustomer, supplements);
            PayingCustomer selectedPayingCustomer = gui.getPayingCustomerChoice().getValue();
            // To remove associate customer from paying customer and add to new
            if (!selectedPayingCustomer.compareAssociateCustomer(selectedAssociateCustomer.getName())) {
                deleteAssociateCustomerFromPayingCustomer(customer, magazine);
                selectedPayingCustomer.addAssociateCustomer(selectedAssociateCustomer);
            }
        }
    }

   

    //for loop to check if the associate customer is linked to a paying customer and remove from the payer list
    private void deleteAssociateCustomerFromPayingCustomer(Customer selCustomer, Magazine magazine) {
        for (Customer customer : magazine.getCustomers()) {
            if (customer instanceof PayingCustomer) {
                PayingCustomer payingCustomer = (PayingCustomer) customer;
                if (payingCustomer.compareAssociateCustomer(selCustomer.getName())) {
                    // Delete associate customer from paying customer
                    payingCustomer.removeAssociateCustomer(selCustomer);
                }
            }
        }
    }
    private void showSupplementDetails(Supplement supplement) {
        String text = "Name: " + supplement.getName()
                + "\nWeekly Cost: $" + String.format("%.2f", supplement.getCost());

        gui.getInfoPanelBox().setText(text);
    }

    private void showCustomerInfo(Customer customer) {
        customer.getSupplement();
        String text = "Name: " + customer.getName()
                + "\n\nAddress: " + customer.getAddress() + "\n\nEmail: "
                + customer.getEmail() + "\n\nSupplements Subscribed to:";
        for (int i = 0; i < customer.getSupplement().size(); i++) {
            int count = i + 1;
            text += ("\n" + count + ". " + customer.getSupplement().get(i).getName());
        }
        if (customer instanceof PayingCustomer) {
            PayingCustomer payingCustomer = (PayingCustomer) customer;
            text += "\n\nYou are a Paying Customer.\n\nPayment Method:\n"
                    + payingCustomer.getPaymentMethod() + "\n\nAssociate Customer:";
            for (Customer associateCustomer : payingCustomer.getAssociateCustomers()) {
                text += "\n" + associateCustomer.getName();
            }
            // Monthly billing history
            double totalSupplementsCost = payingCustomer.calculateTotalSupplementsCost();
            text += "\n\nMonthly cost: $" + String.format("%.2f", totalSupplementsCost);
        } else if (customer instanceof AssociateCustomer) {
            text += "\n\nYou are an Associate Customer.";
        }

        gui.getInfoPanelBox().setText(text);
    }
     private Boolean checkDuplicateCustomer(ArrayList<Customer> customer,String name ){
        for(Customer cust :customer)
        {
            if(cust.getName().equalsIgnoreCase(name))
            {
                magazineManager.showAlert(" Customer "+name+" exists"  );
                return true;
            }
        }
        
        return false;
    }
     
       private Boolean checkDuplicateSupplement(String supplementName ,ArrayList<Supplement> supplements)
   {
        for (Supplement supplement : supplements) {
            if (supplement.getName().equalsIgnoreCase(supplementName)) {
            magazineManager.showAlert(" supplement "+supplementName+" exists"  );
                return true;
            }
        }
        return false;
   }
     private void setCustomerData(Customer customer, ArrayList<Supplement>[] supplements) {
        if(!checkDuplicateCustomer(magazine.getCustomers(),gui.getCustomersNameTextField().getText())){
        customer.setName(gui.getCustomersNameTextField().getText());
        }
        customer.setEmail(gui.getEmailAddressTextField().getText());
        customer.setAddress(new Address(
                gui.getStreetNumberTextField().getText(),
                gui.getStreetNameTextField().getText(),
                gui.getSuburbTextField().getText(),
                gui.getPostCodeTextField().getText()
        ));
        customer.setSupplement(supplements[0]);
   

    }

    private void setPayingCustomerVisible() {
        gui.getPayingCustomerLabel().setVisible(false);
        gui.getPayingCustomerChoice().setVisible(false);
        gui.getAccountNumberLabel().setVisible(true);
        gui.getAccountNumberTextField().setVisible(true);
        gui.getCardType().setVisible(true);
    }

    private void setAssociateCustomerVisible() {
        gui.getPayingCustomerLabel().setVisible(true);
        gui.getPayingCustomerChoice().setValue(null);
        gui.getPayingCustomerChoice().setVisible(true);

        gui.getAccountNumberLabel().setVisible(false);
        gui.getAccountNumberTextField().clear();
        gui.getAccountNumberTextField().setVisible(false);

        gui.getCardType().setValue(null);
        gui.getCardType().setVisible(false);
    }

    private void mainButtons() {
        gui.getViewButton().setOnAction(e -> checkMagazineView());
        gui.getCreateButton().setOnAction(e -> switchToCreate());
        gui.getEditButton().setOnAction(e -> checkMagazineEdit());
    }

    private void switchToCreate() {
        gui.createPage();

        gui.getAddMagButton().setOnAction(e -> addMagazine());
        gui.getLoadMagButton().setOnAction(e -> loadMagazine());
        gui.getSaveMagButton().setOnAction(e -> saveMagazine());

        gui.getViewButton().setOnAction(e -> checkMagazineView());
        gui.getEditButton().setOnAction(e -> checkMagazineEdit());
    }

    private void switchToEdit(String magazineName) {
        gui.editPage();

        gui.getAddSupplementButton().setOnAction(e -> addSupplement(magazineName));
        gui.getAddCustomerButton().setOnAction(e -> addCustomer(magazineName));

        gui.getEditSupplementButton().setOnAction(e -> editSupplement(magazineName));
        gui.getEditCustomerButton().setOnAction(e -> editCustomer(magazineName));

        gui.getDeleteSupplementButton().setOnAction(e -> deleteSupplement(magazineName));
        gui.getDeleteCustomerButton().setOnAction(e -> deleteCustomer(magazineName));

        gui.getCurrentMagazine().setText("Currently editing: " + magazineName);

        gui.getViewButton().setOnAction(e -> checkMagazineView());
        gui.getCreateButton().setOnAction(e -> switchToCreate());
        gui.getEditButton().setOnAction(e -> checkMagazineEdit());
    }

    private void switchToView(String magazineName) {
        gui.viewPage();
        magazine = magazineManager.getMagazine(magazineName);

        // Add all supplements -> output to list view
        gui.getSupplementsView().getItems().addAll(magazine.getSupplements());
        // Monitor the supllements selected
        gui.getSupplementsView().getSelectionModel().selectedItemProperty().addListener((ob, oldValue, newValue) -> {
            if (newValue != null) {
                gui.getCustomersView().getSelectionModel().clearSelection();
                showSupplementDetails(newValue);
            }
        });
        // Add all customers ->output to list view
        gui.getCustomersView().getItems().addAll(magazine.getCustomers());
        // Monitor the customer info selected
        gui.getCustomersView().getSelectionModel().selectedItemProperty().addListener((ob, oldValue, newValue) -> {
            if (newValue != null) {
                gui.getSupplementsView().getSelectionModel().clearSelection();
                showCustomerInfo(newValue);
            }
        });

        gui.getCurrentMagazine().setText("Current Magazine: " + magazineName);

        mainButtons();
    }

}
