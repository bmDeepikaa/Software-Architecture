/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
     * @class Question2
     * @brief the client of the programme
     *
     * @author deepikaa
     * @version 01
     * @date 23/02/2024
     */

public class Question2 {

    public static final int useTestData = 0;
    public static final int useInputData1 = 1;
    public static final int useInputData2 = 2;

    static Scanner sc = new Scanner(System.in);
    static DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) {

        DisplayStudentDetails();

        ArrayList<Customer> customerList = new ArrayList<Customer>();
        Magazine magazine = new Magazine();

        magazine = BuiltInMagazineSupp();
        BuiltInCustomer(customerList, magazine);

        MenuProcess(customerList, magazine);

    }

    public static Magazine BuiltInMagazineSupp() {

        Supplement s1 = new Supplement("sport", 10.5);
        Supplement s2 = new Supplement("review", 15);
        Supplement s3 = new Supplement("literature", 19.55);
        Supplement s4 = new Supplement("movie", 2.55);

        ArrayList<Supplement> suppList = new ArrayList<Supplement>();
        suppList.add(s1);
        suppList.add(s2);
        suppList.add(s3);
        suppList.add(s4);

        Magazine magazine = new Magazine("DEE_Magazine", 15, suppList);
        return magazine;
    }

    public static void BuiltInCustomer(ArrayList<Customer> cusList, Magazine magazine) {

        //supplements
        ArrayList<Supplement> list = magazine.GetMagSupplement();
        Supplement s1 = list.get(0);
        Supplement s2 = list.get(1);
        Supplement s3 = list.get(2);
        Supplement s4 = list.get(3);

        //Main Customer 01
        ArrayList<Supplement> cus1SuppList = new ArrayList<Supplement>();
        cus1SuppList.add(s1);
        cus1SuppList.add(s2);
        cus1SuppList.add(s3);
        cus1SuppList.add(s4);

        AssociateCustomer cus1 = new AssociateCustomer("mic", "mic@gmail.com", cus1SuppList);

        //Main Customer 02
        ArrayList<Supplement> cus2SuppList = new ArrayList<Supplement>();
        cus2SuppList.add(s1);
        cus2SuppList.add(s4);
        AssociateCustomer cus2 = new AssociateCustomer("john", "john@gmail.com", cus2SuppList);

        //Main Customer 03
        ArrayList<Supplement> assoCusList1_mainCus03 = new ArrayList<Supplement>();
        assoCusList1_mainCus03.add(s2);
        assoCusList1_mainCus03.add(s3);
        AssociateCustomer cus3 = new AssociateCustomer("dee", "dee@gmail.com", assoCusList1_mainCus03);

        ArrayList<Supplement> assoCusList2_mainCus03 = new ArrayList<Supplement>();
        assoCusList2_mainCus03.add(s1);
        AssociateCustomer cus4 = new AssociateCustomer("cham", "cham@yahoo.com", assoCusList2_mainCus03);

        ArrayList<AssociateCustomer> mainCus03List = new ArrayList<AssociateCustomer>();
        mainCus03List.add(cus3);
        mainCus03List.add(cus4);

        ArrayList<Supplement> suppList_mainCus03 = new ArrayList<Supplement>();
        suppList_mainCus03.add(s3);
        suppList_mainCus03.add(s4);

        PayingCustomer payCus1 = new PayingCustomer("paul", "paul@gmail.com", suppList_mainCus03, 12398715, 0, mainCus03List);

        //Main Customer 04
        ArrayList<Supplement> assoCusList1_mainCus04 = new ArrayList<Supplement>();
        assoCusList1_mainCus04.add(s1);
        assoCusList1_mainCus04.add(s2);
        AssociateCustomer cus5 = new AssociateCustomer("man", "man@gmail.com", assoCusList1_mainCus04);

        ArrayList<AssociateCustomer> mainCus04List = new ArrayList<AssociateCustomer>();
        mainCus04List.add(cus5);

        ArrayList<Supplement> suppList_mainCus04 = new ArrayList<Supplement>();
        suppList_mainCus04.add(s2);
        suppList_mainCus04.add(s4);

        PayingCustomer payCus2 = new PayingCustomer("larcus", "larcus@gmail.com", suppList_mainCus04, 45612344, 1, mainCus04List);

        //Main Customer 05
        ArrayList<AssociateCustomer> mainCus05List = new ArrayList<AssociateCustomer>();
        mainCus05List.add(cus1);
        mainCus05List.add(cus2);

        ArrayList<Supplement> suppList_mainCus05 = new ArrayList<Supplement>();
        suppList_mainCus05.add(s2);
        suppList_mainCus05.add(s3);
        suppList_mainCus05.add(s4);

        PayingCustomer payCus3 = new PayingCustomer("kelly", "kelly@gmail.com", suppList_mainCus05, 41789620, 1, mainCus05List);

        cusList.add(cus1);
        cusList.add(cus2);
        cusList.add(payCus1);
        cusList.add(payCus2);
        cusList.add(payCus3);
    }

    public static int MenuChoices() throws Exception {
        System.out.println("\n---------< Menu ----------");
        System.out.println("[1] - Print out the text of all the emails for all customers for four weeks of magazines");
        System.out.println("[2] - Print out the text for the end of month emails for the paying customers");
        System.out.println("[3] - Add a new customer to the magazine service");
        System.out.println("[4] - Remove an existing customer from the magazine service");
        System.out.println("[5] - Exit program ");
        System.out.print("\nEnter your choice : ");
        int choice = Integer.parseInt(sc.next());
        return choice;

    }

    public static void MenuProcess(ArrayList<Customer> cusList, Magazine magazine) {
        int option = 0;
        do {
            try {
                option = MenuChoices();
                switch (option) {
                    case 1:
                        PrintEmailforWeek(cusList);
                        break;
                    case 2:
                        PrintEmailforMonth(cusList, magazine);
                        break;
                    case 3:
                        AddCustomer(cusList, magazine);
                        break;
                    case 4:
                        RemoveCustomer(cusList);
                        break;
                    case 5:
                        System.out.println("Thank you Bye!!");
                        break;
                    default:
                        System.out.println("Invalid option!");
                }
            } catch (Exception e) //if user enter non-numerical value
            {
                System.out.println("Use a numerical value !");
            }
        } while (option != 5);

    }

    public static void DisplayStudentDetails() {
        System.out.println("\nStudent Name : BM DEEPIKAA ");
        System.out.println("Class : ICT373C");
        System.out.println("StudentNo : 34780995");
        System.out.println("Mode Of Enrolment : Part Time");
        System.out.println("Tutor Name : Andy Lee ");

        System.out.println("----------------------------------------------");

    }
    //check associate customer duplication 
    //because associate customer might be in Paying customer's associate customer list

    public static boolean CheckDuplicateValue(ArrayList<String> list, Customer customer) {

        boolean isDuplicate = false;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(customer.GetCustomerName())) {
                isDuplicate = true;
                break;
            }
        }
        return isDuplicate;
    }

    public static void PrintEmailforWeek(ArrayList<Customer> cusList) {

        int counter = 1, supNo = 1;
        ArrayList<String> duplicate = new ArrayList<String>();

        System.out.println("");
        for (int i = 0; i < cusList.size(); i++) {
            if (cusList.get(i) instanceof PayingCustomer) {

                boolean isDuplicate = CheckDuplicateValue(duplicate, cusList.get(i));
                if (!isDuplicate) {
                    duplicate.add(cusList.get(i).GetCustomerName());
                    DisplayOutput(cusList.get(i), counter, supNo);
                    counter++;
                }

                //print associate customer in Paying customer's associate customer list
                ArrayList<AssociateCustomer> assoCusList = ((PayingCustomer) cusList.get(i)).GetAssoCusList();
                for (int index = 0; index < assoCusList.size(); index++) {

                    boolean isDuplicate2 = CheckDuplicateValue(duplicate, assoCusList.get(index));
                    if (!isDuplicate2) {
                        duplicate.add(assoCusList.get(index).GetCustomerName());
                        DisplayOutput(assoCusList.get(index), counter, supNo);
                        counter++;
                    }
                }
            } else {
                boolean isDuplicate3 = CheckDuplicateValue(duplicate, cusList.get(i));
                if (!isDuplicate3) {
                    duplicate.add(cusList.get(i).GetCustomerName());
                    DisplayOutput(cusList.get(i), counter, supNo);
                    counter++;
                }
            }
        }
    }

    public static void PrintEmailforMonth(ArrayList<Customer> cusList, Magazine magazine) {

        int counter = 1;
        //Magazine magazine = PointA();
        ArrayList<Supplement> magazineSup = magazine.GetMagSupplement();

        System.out.println("");
        for (Customer cusList1 : cusList) {
            if (cusList1 instanceof PayingCustomer) {
                int magazineCounter = 0;
                PayingCustomer cus = (PayingCustomer) cusList1; //retrieve the paying customer
                LinkedHashMap<String, Integer> map = CountSupplementsInMag(cus, magazine);
                System.out.println("[" + counter + "]");
                counter++;
                System.out.println("Customer name: " + cusList1.GetCustomerName());
                System.out.println("Bank A/C No: " + ((PayingCustomer) cusList1).GetPayment().GetBankAcc());
                System.out.println("Bank A/C Type: " + ((PayingCustomer) cusList1).GetPayment().GetBankAccType());
                System.out.println("Total Supplements' Weekly Cost: " + df.format(cusList1.GetWeeklyPrice()));
                System.out.println("Magazine weekly cost: " + magazine.GetWeeklyCost());
                System.out.println("Total Monthy Payment: " + df.format(4 * (magazine.GetWeeklyCost() + cusList1.GetWeeklyPrice())));
                System.out.println("Detail Info: ");
                System.out.println("--------------------------------------------");
                System.out.println("[Suppl]\t[Cost]\t[Qty]\t[Price]");
                for (String i : map.keySet()) {
                    System.out.println(" " + i + "\t " + magazineSup.get(magazineCounter).GetWeeklyCost() + "\t  "
                            + map.get(i) + "\t " + map.get(i) * magazineSup.get(magazineCounter).GetWeeklyCost());
                    magazineCounter++;
                }
                System.out.println("\nAssociate Customers: ");
                System.out.println("-------------------------");
                ArrayList<AssociateCustomer> list = ((PayingCustomer) cusList1).GetAssoCusList();
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i + 1 + ". " + list.get(i).GetCustomerName());
                }
                System.out.println("");
            }
        }
    }

    public static void DisplayOutput(Customer customer, int counter, int supNo) {

        boolean empty = true;

        System.out.println("[" + counter + "]");
        System.out.println("Customer name: " + customer.GetCustomerName());
        System.out.println("Customer email: " + customer.GetEmail());
        System.out.println("Supplements: ");
        System.out.println("--------------------");
        ArrayList<Supplement> list = customer.GetSuppList();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(supNo + ". " + list.get(i).GetName());
            supNo++;
            empty = false;
        }

        if (empty) {
            System.out.println("--No supplement--");
        }
        System.out.println("");
    }

    public static void AddCustomer(ArrayList<Customer> cusList, Magazine magazine) {

        int customer = 0;
        boolean valid = false;

        while (!valid) {
            try {
                System.out.println("What type of customer you want to add? ");
                System.out.println("[1]-Associate Customer ");
                System.out.println("[2]-Paying Customer ");
                System.out.print("Enter your choice: ");
                customer = sc.nextInt();

                if (customer == useInputData1 || customer == useInputData2) {
                    valid = true;
                } else {
                    System.out.println("\nInvalid input. Try again\n");
                }
            } catch (InputMismatchException e) {
                String bad_input = sc.next();
                System.out.println("\nInvalid input - " + bad_input + " is not a numerical value ! ");
            }
        }

        if (customer == useInputData1) {
            //create & add associate customer
            AssociateCustomer aCus = CreateAssociateCustomer(magazine);
            cusList.add(aCus);
            System.out.print("Associate customer successfully added");
        } else {
            //create & add paying customer
            PayingCustomer pCus = CreatePayingCustomer(magazine);
            cusList.add(pCus);
            System.out.print("Paying customer successfully added");
        }
    }

    public static void RemoveCustomer(ArrayList<Customer> cusList) {

        boolean found = false;

        System.out.print("\nEnter the name of customer you want to remove: ");
        String userInputName = sc.next().toLowerCase().trim();

        for (int i = 0; i < cusList.size(); i++) {
            if (cusList.get(i) instanceof PayingCustomer) {
                //loop through the associate customer list of paying customer
                for (int counter = 0; counter < ((PayingCustomer) cusList.get(i)).GetAssoCusList().size(); counter++) {
                    AssociateCustomer innerCustomer = ((PayingCustomer) cusList.get(i)).GetAssoCusList().get(counter);

                    //check if the userInputName is in the paying customer's associate customer list
                    if (innerCustomer.GetCustomerName().equals(userInputName)) {
                        ((PayingCustomer) cusList.get(i)).GetAssoCusList().remove(counter);
                        found = true;
                    }
                }

                //if userInputName is a Paying customer, remove all his/her associate customer as well
                if (cusList.get(i).GetCustomerName().equals(userInputName)) {
                    for (int counter = 0; counter < ((PayingCustomer) cusList.get(i)).GetAssoCusList().size(); counter++) {
                        AssociateCustomer innerCus = ((PayingCustomer) cusList.get(i)).GetAssoCusList().get(counter);

                        for (int j = 0; j < cusList.size(); j++) {
                            if (cusList.get(j).GetCustomerName().equals(innerCus.GetCustomerName())) {
                                cusList.remove(j);
                            }
                        }
                    }
                }
            }

            if (cusList.get(i).GetCustomerName().equals(userInputName)) {
                cusList.remove(i);
                found = true;
            }
        }

        if (!found) {
            System.out.println("\nCustomer " + userInputName + " not found ");
        } else {
            System.out.println("\nCustomer " + userInputName + " records has been removed ");
        }

    }//end of method

    public static AssociateCustomer CreateAssociateCustomer(Magazine magazine) {

        ArrayList<Supplement> list = magazine.GetMagSupplement();

        ArrayList<Supplement> supplementList = new ArrayList<Supplement>();
        int[] suppArray = new int[4];

        System.out.print("Enter customer name: ");
        String name = sc.next();
        System.out.print("Enter customer email address: ");
        String email = sc.next();
        ReadSupplement(suppArray, magazine); //read user supplement option
        StoreSupplementArr(suppArray, supplementList, list);  //store supplement to another ArrayList

        AssociateCustomer cus = new AssociateCustomer(name, email, supplementList);
        return cus;
    }

    public static PayingCustomer CreatePayingCustomer(Magazine magazine) {

        boolean validPayment = false, moreAssoCus = true, validAcc = false;
        //default value of card always debit
        int type = 0;
        long bankAccNumber = 0;
        ArrayList<Supplement> list = magazine.GetMagSupplement();
        ArrayList<AssociateCustomer> assoCusList = new ArrayList<AssociateCustomer>(); //associate customer list for paying customer
        System.out.print("Enter customer name: ");
        String name = sc.next();
        System.out.print("Enter customer email address: ");
        String email = sc.next();

        //check bank account
        while (!validAcc) {
            try {
                System.out.print("Enter bank account number: ");
                bankAccNumber = sc.nextLong();
                validAcc = true;

            } catch (InputMismatchException e) {
                String bad_input = sc.next();
                System.out.println("\nInvalid input - bank number cannot be non-numerical value ! ");
            }
        }

        //check payment type
        while (!validPayment) {
            try {
                System.out.print("Enter payment type [0-Direct Debit 1-Credit Card]: ");
                type = sc.nextInt();

                if (type == 0 || type == 1) {
                    validPayment = true;
                } else {
                    System.out.println("Invalid input!! Try again");
                }
            } catch (InputMismatchException e) {
                String bad_input = sc.next();
                System.out.println("\nInvalid input - " + bad_input + " is not a numerical value ! ");
            }
        }

        //get supplement list for paying customer
        int[] payCusSuppArray = new int[4];
        ArrayList<Supplement> payCusSupplementList = new ArrayList<Supplement>();

        ReadSupplement(payCusSuppArray, magazine); //read user supplement option
        StoreSupplementArr(payCusSuppArray, payCusSupplementList, list); //store supplement to another ArrayList

        //check if contains associate customer
        System.out.print("Do you have any associate customer with you? [Yes/No]: ");
        char userInput = sc.next().toLowerCase().charAt(0);

        if (userInput == 'y') {
            while (moreAssoCus) {
                System.out.print("Enter associate customer information");
                AssociateCustomer cus = CreateAssociateCustomer(magazine);
                assoCusList.add(cus);

                System.out.print("Continue enter associate customer info ? [Yes/No]: ");
                char more = sc.next().toLowerCase().charAt(0);

                if (more == 'n') {
                    moreAssoCus = false;
                }
            }
        }

        PayingCustomer payCustomer = new PayingCustomer(name, email, payCusSupplementList, bankAccNumber, type, assoCusList);

        return payCustomer;
    }

    public static void ReadSupplement(int[] array, Magazine magazine) {

        ArrayList<Supplement> mag = magazine.GetMagSupplement();

        int counter = 0, index = 0;
        System.out.println("\nSupplements :");
        System.out.println(" 1 - [" + mag.get(0).GetName() + "]");
        System.out.println(" 2 - [" + mag.get(1).GetName() + "]");
        System.out.println(" 3 - [" + mag.get(2).GetName() + "]");
        System.out.println(" 4 - [" + mag.get(3).GetName() + "]");

        while (counter < 4) {
            try {
                System.out.println("\nWhat supplements are you interested in : ");
                index = sc.nextInt();

                if (index >= 1 && index <= 4) {
                    array[counter] = index;
                    counter++;
                } else {
                    System.out.println("\nInvalid input!! ");
                }

                if (counter < 4) {
                    System.out.println("\nAny other supplement ? [yes/no]");
                    char option = sc.next().toLowerCase().charAt(0);
                    if (option == 'n') {
                        break;
                    }
                }
            } catch (Exception e) //if user enter non-numerical value
            {
                String bad_input = sc.next();
                System.out.println("Invalid input - " + bad_input + " is not a numerical value ! ");
            }
        }
    }

    //used to store supplement to another ArrayList 
    //purpose of this method is to reuse and avoid rewrite same code 
    public static void StoreSupplementArr(int[] arr, ArrayList<Supplement> position, ArrayList<Supplement> suppList) {

        for (int i = 0; i < arr.length; i++) {
            switch (arr[i]) {
                case 1:
                    position.add(suppList.get(0));
                    break;
                case 2:
                    position.add(suppList.get(1));
                    break;
                case 3:
                    position.add(suppList.get(2));
                    break;
                case 4:
                    position.add(suppList.get(3));
                    break;
            }
        }
    }

    public static LinkedHashMap<String, Integer> CountSupplementsInMag(PayingCustomer customer, Magazine magazine) {

        int a = 0, b = 0, c = 0, d = 0;
        int size = magazine.GetMagSupplement().size();
        String[] strArray = new String[size];
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

        for (int i = 0; i < size; i++) {
            strArray[i] = magazine.GetMagSupplement().get(i).GetName();
        }

        //retrieve Paying customer's supplement list
        ArrayList<Supplement> supList = customer.GetSuppList();
        for (int i = 0; i < supList.size(); i++) {
            if (supList.get(i).GetName().equals(strArray[0])) {
                a++;
            } else if (supList.get(i).GetName().equals(strArray[1])) {
                b++;
            } else if (supList.get(i).GetName().equals(strArray[2])) {
                c++;
            } else {
                d++;
            }
        }

        //retrieve the supplement list of associate customer in Paying customer
        ArrayList<AssociateCustomer> assoCusList = customer.GetAssoCusList();
        //loop through the associate customer list
        for (int j = 0; j < assoCusList.size(); j++) {
            AssociateCustomer associateCus = assoCusList.get(j);
            ArrayList<Supplement> assoCusSupList = associateCus.GetSuppList();
            for (int counter = 0; counter < assoCusSupList.size(); counter++) {
                if (assoCusSupList.get(counter).GetName().equals(strArray[0])) {
                    a++;
                } else if (assoCusSupList.get(counter).GetName().equals(strArray[1])) {
                    b++;
                } else if (assoCusSupList.get(counter).GetName().equals(strArray[2])) {
                    c++;
                } else {
                    d++;
                }
            }
        }
        map.put(strArray[0], a);
        map.put(strArray[1], b);
        map.put(strArray[2], c);
        map.put(strArray[3], d);

        return map;
    }

}
