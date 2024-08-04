/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2.magazine;

import java.io.File;
import java.util.List;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @class MagazineG
 * @brief Contain Associate customer object
 *
 * @author deepikaa
 * @version 02
 * @date 25/03/2024
 */
public class MagazineGUI {

    private GridPane root;
    private TextArea infoPanelBox;
    private Stage primaryStage;
    private Button viewButton, createButton, editButton, submitButton, addSupplementButton, addCustomerButton,
            editSupplementButton, editCustomerButton, deleteSupplementButton, deleteCustomerButton,
            addMagButton, loadMagButton, saveMagButton;
    private TextField magazineNameTextField, supplementNameTextField, supplementCostTextField, customersNameTextField,
            emailAddressTextField, streetNumberTextField, streetNameTextField, suburbTextField, postCodeTextField,
            accountNumberTextField, typeOfCustomerTextField;
    private Label payingCustomerLabel, accountNumberLabel, currentMagazine;
    private List<File> file;
    private ListView<Supplement> supplementsView, supplementChoice,supplements;
    private ListView<Customer> customersView, customerChoice;
    private ComboBox<String> typeOfCustomer, cardType, magazineChoice;
    private ComboBox<PayingCustomer> payingCustomerChoice;

    public MagazineGUI(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
  private void addEqualColumnConstraints(GridPane root, int numColumns, HPos alignment) {
        for (int i = 0; i < numColumns; i++) {
            ColumnConstraints column = new ColumnConstraints(200);
            column.setHalignment(alignment);
            root.getColumnConstraints().add(column);
        }
    }
    public void homePage() {
        root = new GridPane();

        Label header = new Label("Magazine Service");

        homePageButtons(header);
        BackgroundFill backgroundFill = new BackgroundFill(Color.IVORY, null, null);
        Background background = new Background(backgroundFill);

        // Create the layout
        root.setMinSize(700, 700);
        root.setPadding(new Insets(10));
        root.setVgap(20);
        root.setHgap(10);
        root.setBackground(background);

        addEqualColumnConstraints(root, 3, HPos.CENTER);

        // Create the scene and set it on the stage
        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Magazine Service");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

  

    public void homePageButtons(Label header) {
        // Create buttons for different modes
        viewButton = new Button("View");
        viewButton.setPrefWidth(100);
        viewButton.setPrefHeight(30);
        viewButton.setFocusTraversable(false);

        createButton = new Button("Create");
        createButton.setPrefWidth(100);
        createButton.setPrefHeight(30);
        createButton.setFocusTraversable(false);

        editButton = new Button("Edit");
        editButton.setPrefWidth(100);
        editButton.setPrefHeight(30);
        editButton.setFocusTraversable(false);

        // Arranging nodes 
        root.add(header, 0, 0, 3, 1);
        root.add(viewButton, 0, 1, 1, 1);
        root.add(createButton, 1, 1, 1, 1);
        root.add(editButton, 2, 1, 1, 1);
    }

    public void viewPage() {
        homePage();

        // Create ListView for supplements
        supplementsView = new ListView<>();
        supplementsView.setStyle("-fx-pref-height: 200px");

        // Create ListView for customers
        customersView = new ListView<>();
        customersView.setStyle("-fx-pref-height: 200px");

        viewPageDetails();

    }

    public void viewPageDetails() {
        Label infoPane = new Label("Information Panel:");
        infoPane.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Create box for information panel
        infoPanelBox = new TextArea();
        infoPanelBox.setEditable(false);

        Label supplementPane = new Label("List of Supplements: ");
        supplementPane.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label customerPane = new Label("List of Customers: ");
        customerPane.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        currentMagazine = new Label("");
        currentMagazine.setStyle("-fx-font-size: 10px; -fx-font-weight: bold;");

        // Add nodes to the gridPane
        root.add(supplementPane, 0, 3);
        root.add(supplementsView, 0, 4);
        root.add(customerPane, 0, 5);
        root.add(customersView, 0, 6);
        root.add(infoPane, 1, 3);
        root.add(infoPanelBox, 1, 4, 2, 3);
        root.add(currentMagazine, 0, 7, 3, 1);
    }

    public void createPage() {
        homePage();
        createButton.setDisable(true);

        Label createHeader = new Label("Choose Option:");
        createHeader.setStyle("-fx-font-weight: bold;");

        // Create buttons for different modes
        addMagButton = new Button("Add Magazine");
        addMagButton.setFocusTraversable(false);
        addMagButton.setMinSize(200, 30);

        loadMagButton = new Button("Load Magazine");
        loadMagButton.setFocusTraversable(false);
        loadMagButton.setMinSize(200, 30);

        saveMagButton = new Button("Save Magazine");
        saveMagButton.setFocusTraversable(false);
        saveMagButton.setMinSize(200, 30);

        // Add nodes to the gridPane
        root.add(createHeader, 1, 5);
        root.add(addMagButton, 1, 7);
        root.add(loadMagButton, 1, 9);
        root.add(saveMagButton, 1, 11);
    }

    public void addMagazine() {
        homePage();

        Label magazineNameLabel = new Label("Magazine name:");
        magazineNameTextField = new TextField();

        submitButton();

        // Add nodes to the gridPane
        root.add(magazineNameLabel, 0, 5);
        root.add(magazineNameTextField, 1, 5);
        root.add(submitButton, 1, 6);
    }

    public void loadMagazine() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Load Magazine File to service");

        // Ensure only .ser file can be chosen
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Serialized Files (*.ser)", "*.ser");
        fileChooser.getExtensionFilters().add(extFilter);

        Stage stage = new Stage();
        file = fileChooser.showOpenMultipleDialog(stage);
    }

    public void saveMagazine() {
        homePage();

        Label saveMagazineHeader = new Label("Select a magazine to save:");
        saveMagazineHeader.setStyle("-fx-font-weight: bold;");

        // Create combobox for choosing magazine
        magazineChoice = new ComboBox<>();
        magazineChoice.setPromptText("Magazine");
        magazineChoice.setStyle("-fx-pref-width: 200px");

        submitButton();

        // Add nodes to the gridPane
        root.add(saveMagazineHeader, 1, 4);
        root.add(magazineChoice, 1, 5);
        root.add(submitButton, 1, 6);
    }

    public void editPage() {
        homePage();

        Label editHeader = new Label("Choose an Option:");
        editHeader.setStyle("-fx-font-weight: bold;");

        // Create buttons for different modes
        addSupplementButton = new Button("Add Supplement");
        addSupplementButton.setFocusTraversable(false);
        addSupplementButton.setMinSize(200, 30);

        addCustomerButton = new Button("Add Customer");
        addCustomerButton.setFocusTraversable(false);
        addCustomerButton.setMinSize(200, 30);

        editSupplementButton = new Button("Edit Supplement");
        editSupplementButton.setFocusTraversable(false);
        editSupplementButton.setMinSize(200, 30);

        editCustomerButton = new Button("Edit Customer");
        editCustomerButton.setFocusTraversable(false);
        editCustomerButton.setMinSize(200, 30);

        deleteSupplementButton = new Button("Delete Supplement");
        deleteSupplementButton.setFocusTraversable(false);
        deleteSupplementButton.setMinSize(200, 30);

        deleteCustomerButton = new Button("Delete Customer");
        deleteCustomerButton.setFocusTraversable(false);
        deleteCustomerButton.setMinSize(200, 30);

        currentMagazine = new Label("");
        currentMagazine.setStyle("-fx-font-size: 10px; -fx-font-weight: bold;");

        // Add nodes to the gridPane
        root.add(editHeader, 1, 5);
        root.add(addSupplementButton, 1, 7);
        root.add(addCustomerButton, 1, 9);
        root.add(editSupplementButton, 1, 11);
        root.add(editCustomerButton, 1, 13);
        root.add(deleteSupplementButton, 1, 15);
        root.add(deleteCustomerButton, 1, 17);
        root.add(currentMagazine, 0, 20, 3, 1);
    }

    public void addSupplement() {
        homePage();

        Label addSupplementHeader = new Label("Add Supplement:");
        addSupplementHeader.setStyle("-fx-font-weight: bold;");

        Label supplementNameLabel = new Label("Supplement name:");
        supplementNameTextField = new TextField();

        Label supplementCostLabel = new Label("Supplement cost:");
        supplementCostTextField = new TextField();

        submitButton();

        // Add nodes to the gridPane
        root.add(addSupplementHeader, 1, 3);
        root.add(supplementNameLabel, 0, 5);
        root.add(supplementNameTextField, 1, 5);
        root.add(supplementCostLabel, 0, 6);
        root.add(supplementCostTextField, 1, 6);
        root.add(submitButton, 1, 7);
    }

    public void addCustomer() {
        homePage();

        // Customer header
        Label addCustomerHeader = new Label("Add Customer:");
        addCustomerHeader.setStyle("-fx-font-weight: bold;");

        // Customer type field
        Label customerTypeLabel = new Label("Select type of customer:");
        typeOfCustomer = new ComboBox<>();
        typeOfCustomer.setPromptText("Customer");
        typeOfCustomer.setStyle("-fx-pref-width: 200px");

        // Customer name field
        Label customerNameLabel = new Label("Customer name:");
        customersNameTextField = new TextField();

        // Email field
        Label emailAddressLabel = new Label("Email Address:");
        emailAddressTextField = new TextField();

        // Street number field
        Label streetNumberLabel = new Label("Street Number:");
        streetNumberTextField = new TextField();

        // Street name field
        Label streetNameLabel = new Label("Street Name:");
        streetNameTextField = new TextField();

        // Suburb field
        Label suburbLabel = new Label("Suburb:");
        suburbTextField = new TextField();

        // Postcode field
        Label postcodeLabel = new Label("Postcode:");
        postCodeTextField = new TextField();

        // Supplement field
        Label supplementsLabel = new Label("Select Supplement(s):");
        supplementChoice = new ListView<>();
        supplementChoice.setStyle("-fx-pref-height: 75px");

        // Allow multiple selection for supplements
        supplementChoice.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        // Paying customer field
        payingCustomerLabel = new Label("Select Paying Customer:");
        payingCustomerLabel.setVisible(false);
        payingCustomerChoice = new ComboBox<>();
        payingCustomerChoice.setStyle("-fx-pref-width: 200px");
        payingCustomerChoice.setVisible(false);

        // Account number field
        accountNumberLabel = new Label("Account Number:");
        accountNumberLabel.setVisible(false);
        accountNumberTextField = new TextField();
        accountNumberTextField.setVisible(false);

        // Card type field
        cardType = new ComboBox<>();
        cardType.getItems().addAll("Credit Card", "Debit Card");
        cardType.setPromptText("Select Card Type");
        cardType.setStyle("-fx-pref-width: 200px");
        cardType.setVisible(false);

        submitButton();

        // Arranging nodes in grid
        root.add(addCustomerHeader, 1, 3);
        root.add(customerTypeLabel, 0, 4);
        root.add(typeOfCustomer, 1, 4);
        root.add(customerNameLabel, 0, 5);
        root.add(customersNameTextField, 1, 5);
        root.add(emailAddressLabel, 0, 6);
        root.add(emailAddressTextField, 1, 6);

        root.add(streetNumberLabel, 0, 7);
        root.add(streetNumberTextField, 1, 7);
        root.add(streetNameLabel, 0, 8);
        root.add(streetNameTextField, 1, 8);
        root.add(suburbLabel, 0, 9);
        root.add(suburbTextField, 1, 9);
        root.add(postcodeLabel, 0, 10);
        root.add(postCodeTextField, 1, 10);

        root.add(supplementsLabel, 0, 11);
        root.add(supplementChoice, 1, 11);

        root.add(payingCustomerLabel, 0, 12);
        root.add(payingCustomerChoice, 1, 12);

        root.add(accountNumberLabel, 0, 12);
        root.add(accountNumberTextField, 1, 12);
        root.add(cardType, 1, 13);

        root.add(submitButton, 1, 14);
    }

    public void editSupplement() {
        homePage();

        // Selecting supplement
        Label supplementsLabel = new Label("Select supplement to edit:");
        supplementsLabel.setStyle("-fx-font-weight: bold;");

        // Create listview for supplement choice
        supplementChoice = new ListView<>();
        supplementChoice.setStyle("-fx-pref-height: 450px");

        Label supplementNameLabel = new Label("Supplement name:");
        supplementNameTextField = new TextField();
        Label supplementCostLabel = new Label("Supplement cost:");
        supplementCostTextField = new TextField();

        submitButton();

        // Add nodes to the gridPane
        root.add(supplementsLabel, 0, 3);
        root.add(supplementChoice, 0, 4, 1, 12);
        root.add(supplementNameLabel, 1, 12);
        root.add(supplementNameTextField, 2, 12);
        root.add(supplementCostLabel, 1, 13);
        root.add(supplementCostTextField, 2, 13);
        root.add(submitButton, 2, 14);
    }

    public void editCustomer() {
        homePage();

        // Selecting customer
        Label customersLabel = new Label("Select customer to edit:");
        customersLabel.setStyle("-fx-font-weight: bold;");
        customerChoice = new ListView<>();

        // Customer type field
        Label customerTypeLabel = new Label("Type of customer:");
        typeOfCustomerTextField = new TextField();
        typeOfCustomerTextField.setDisable(true);

        // Customer name field
        Label customerNameLabel = new Label("Customer name:");
        customersNameTextField = new TextField();

        // Email field
        Label emailAddressLabel = new Label("Email Address:");
        emailAddressTextField = new TextField();

        // Street number field
        Label streetNumberLabel = new Label("Street Number:");
        streetNumberTextField = new TextField();

        // Street name field
        Label streetNameLabel = new Label("Street Name:");
        streetNameTextField = new TextField();

        // Suburb field
        Label suburbLabel = new Label("Suburb:");
        suburbTextField = new TextField();

        // Postcode field
        Label postcodeLabel = new Label("Postcode:");
        postCodeTextField = new TextField();

        // Existing supplements
        Label oldSupplementsHeader = new Label("Your supplement(s):");
        supplements = new ListView<>();

        // Disable selection and only allow scrolling
        supplements.addEventFilter(MouseEvent.MOUSE_PRESSED, MouseEvent::consume);

        // Supplement field
        Label supplementsLabel = new Label("Select Supplement(s):");
        supplementChoice = new ListView<>();

        supplementChoice.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        // Paying customer field
        payingCustomerLabel = new Label("Select Paying Customer:");
        payingCustomerLabel.setVisible(false);
        payingCustomerChoice = new ComboBox<>();
        payingCustomerChoice.setPromptText("Select One");
        payingCustomerChoice.setStyle("-fx-pref-width: 200px");
        payingCustomerChoice.setVisible(false);

        // Account number field
        accountNumberLabel = new Label("Account Number:");
        accountNumberLabel.setVisible(false);
        accountNumberTextField = new TextField();
        accountNumberTextField.setVisible(false);

        // Card type field
        cardType = new ComboBox<>();
        cardType.setPromptText("Select Card Type");
        cardType.setStyle("-fx-pref-width: 200px");
        cardType.setVisible(false);

        submitButton();

        // Add nodes to the gridPane
        root.add(customersLabel, 0, 3);
        root.add(customerChoice, 0, 4, 1, 10);
        root.add(customerTypeLabel, 1, 3);
        root.add(typeOfCustomerTextField, 2, 3);
        root.add(customerNameLabel, 1, 4);
        root.add(customersNameTextField, 2, 4);
        root.add(emailAddressLabel, 1, 5);
        root.add(emailAddressTextField, 2, 5);

        root.add(streetNumberLabel, 1, 6);
        root.add(streetNumberTextField, 2, 6);
        root.add(streetNameLabel, 1, 7);
        root.add(streetNameTextField, 2, 7);
        root.add(suburbLabel, 1, 8);
        root.add(suburbTextField, 2, 8);
        root.add(postcodeLabel, 1, 9);
        root.add(postCodeTextField, 2, 9);

        root.add(oldSupplementsHeader, 1, 10);
        root.add(supplements, 1, 11);
        root.add(supplementsLabel, 2, 10);
        root.add(supplementChoice, 2, 11);

        root.add(payingCustomerLabel, 1, 12);
        root.add(payingCustomerChoice, 2, 12);

        root.add(cardType, 2, 12);
        root.add(accountNumberLabel, 1, 13);
        root.add(accountNumberTextField, 2, 13);

        root.add(submitButton, 2, 14);
    }

    public void deleteSupplement() {
        homePage();

        Label supplementsLabel = new Label("Select supplement to delete:");
        supplementsLabel.setStyle("-fx-font-weight: bold;");

        // Create a listview for supplement choice
        supplementChoice = new ListView<>();
        supplementChoice.setStyle("-fx-pref-height: 400px");

        Label supplementsDetails = new Label("Supplement information:");
        supplementsDetails.setStyle("-fx-font-weight: bold;");

        infoPanelBox = new TextArea();
        infoPanelBox.setEditable(false);

        submitButton();

        // Add nodes to the gridPane
        root.add(supplementsLabel, 0, 3);
        root.add(supplementChoice, 0, 4);
        root.add(supplementsDetails, 1, 3);
        root.add(infoPanelBox, 1, 4, 2, 1);
        root.add(submitButton, 1, 6);
    }

    public void deleteCustomer() {
        homePage();

        Label customersLabel = new Label("Select customer to delete:");
        customersLabel.setStyle("-fx-font-weight: bold;");

        // Create a listview for customer choice
        customerChoice = new ListView<>();
        customerChoice.setStyle("-fx-pref-height: 400px");

        Label customersDetails = new Label("Customer information:");
        customersDetails.setStyle("-fx-font-weight: bold;");

        infoPanelBox = new TextArea();
        infoPanelBox.setEditable(false);

        submitButton();

        // Add nodes to the gridPane
        root.add(customersLabel, 0, 3);
        root.add(customerChoice, 0, 4);
        root.add(customersDetails, 1, 3);
        root.add(infoPanelBox, 1, 4, 2, 1);
        root.add(submitButton, 1, 6);
    }

    public void magazineViewCheck() {
        homePage();
        viewButton.setDisable(true);

        Label viewCheckHeader = new Label("Select a magazine to view:");
        viewCheckHeader.setStyle("-fx-font-weight: bold;");

        // Create combobox for choosing magazine
        magazineChoice = new ComboBox<>();
        magazineChoice.setPromptText("Magazine");
        magazineChoice.setStyle("-fx-pref-width: 200px");

        submitButton();
        // Add nodes to the gridPane
        root.add(viewCheckHeader, 1, 4);
        root.add(magazineChoice, 1, 5);
        root.add(submitButton, 1, 6);
    }

    public void magazineEditCheck() {
        homePage();
        editButton.setDisable(true);

        Label editCheckHeader = new Label("Select a magazine to edit:");
        editCheckHeader.setStyle("-fx-font-weight: bold;");

        // Create combobox for choosing magazine
        magazineChoice = new ComboBox<>();
        magazineChoice.setPromptText("Magazine");
        magazineChoice.setStyle("-fx-pref-width: 200px");

        submitButton();

        // Add nodes to the gridPane
        root.add(editCheckHeader, 1, 4);
        root.add(magazineChoice, 1, 5);
        root.add(submitButton, 1, 6);
    }

    public void submitButton() {
        // Create a button for submitting the data
        submitButton = new Button("Submit");
        submitButton.setMinSize(200, 25);
    }

    public Button getViewButton() {
        return viewButton;
    }

    public Button getCreateButton() {
        return createButton;
    }

    public Button getEditButton() {
        return editButton;
    }

    public Button getSubmitButton() {
        return submitButton;
    }

    public Button getAddMagButton() {
        return addMagButton;
    }

    public Button getLoadMagButton() {
        return loadMagButton;
    }

    public Button getSaveMagButton() {
        return saveMagButton;
    }

    public Button getAddSupplementButton() {
        return addSupplementButton;
    }

    public Button getAddCustomerButton() {
        return addCustomerButton;
    }

    public Button getEditSupplementButton() {
        return editSupplementButton;
    }

    public Button getEditCustomerButton() {
        return editCustomerButton;
    }

    public Button getDeleteSupplementButton() {
        return deleteSupplementButton;
    }

    public Button getDeleteCustomerButton() {
        return deleteCustomerButton;
    }

    public ListView<Supplement> getSupplementsView() {
        return supplementsView;
    }

    public ListView<Supplement> getSupplementChoice() {
        return supplementChoice;
    }

    public ListView<Supplement> getSupplements() {
        return supplements;
    }

    public ListView<Customer> getCustomersView() {
        return customersView;
    }

    public ListView<Customer> getCustomerChoice() {
        return customerChoice;
    }

    public ComboBox<String> getMagazineChoice() {
        return magazineChoice;
    }

    public ComboBox<String> getTypeOfCustomer() {
        return typeOfCustomer;
    }

    public ComboBox<String> getCardType() {
        return cardType;
    }

    public ComboBox<PayingCustomer> getPayingCustomerChoice() {
        return payingCustomerChoice;
    }

    public TextArea getInfoPanelBox() {
        return infoPanelBox;
    }

    public TextField getMagazineNameTextField() {
        return magazineNameTextField;
    }

    public TextField getSupplementNameTextField() {
        return supplementNameTextField;
    }

    public TextField getSupplementCostTextField() {
        return supplementCostTextField;
    }

    public TextField getCustomersNameTextField() {
        return customersNameTextField;
    }

    public TextField getEmailAddressTextField() {
        return emailAddressTextField;
    }

    public TextField getStreetNumberTextField() {
        return streetNumberTextField;
    }

    public TextField getStreetNameTextField() {
        return streetNameTextField;
    }

    public TextField getSuburbTextField() {
        return suburbTextField;
    }

    public TextField getPostCodeTextField() {
        return postCodeTextField;
    }

    public TextField getAccountNumberTextField() {
        return accountNumberTextField;
    }

    public TextField getTypeOfCustomerTextField() {
        return typeOfCustomerTextField;
    }

    public Label getPayingCustomerLabel() {
        return payingCustomerLabel;
    }

    public Label getAccountNumberLabel() {
        return accountNumberLabel;
    }

    public Label getCurrentMagazine() {
        return currentMagazine;
    }

    public List<File> getFile() {
        return file;
    }
}
