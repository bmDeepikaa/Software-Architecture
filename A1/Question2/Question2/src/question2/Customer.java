/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question2;

import java.util.ArrayList;
/**
     * @class Customer
     * @brief Contain customer base detail such as name,email in a object
     *
     * @author deepikaa
     * @version 01
     * @date 23/02/2024
     */

public  class Customer {

    //member variables
    /**
     * The String name
     */
    private String name;

    /**
     * The String email
     */
    private String email;

    /**
     * The ArrayList<Supplement> supplimentList
     */
    private ArrayList<Supplement> supplements;

    /**
     * Default Constructor of Customer class
     *
     * This class is used to represent the default value of String name and
     * email followed by a list of supplements the customer interested in
     *
     */
    public Customer() {
        name = "NoName";
        email = "NoEmail";
        supplements = new ArrayList<Supplement>();
    }

    /**
     * Constructor of Customer class
     *
     * This class is used to represent a String of name, email and a list of
     * supplement
     *
     * @param cusName Name of the customer
     * @param cusEmail Customer's email address
     * @param list A list of supplement
     */
    public Customer(String cusName, String cusEmail, ArrayList<Supplement> list) {
        name = cusName;
        email = cusEmail;
        supplements = list;
    }

    /**
     * This method is used to set the name of the Customer object
     *
     * @param newName Name of customer object
     */
    public void SetCustomerName(String newName) {
        if (name.length() > 0) {
            name = newName.toLowerCase();
        } 
    }

    /**
     * This method is used to get the name of the customer object * @return name
     * Return the Customer object's name
     */
    public String GetCustomerName() {
        return name;
    }

    /**
     * This method is used to set the email address of the Customer object
     *
     *
     * @param newEmail Email address of customer object
     */
    public void SetEmail(String newEmail) {
         if (name.length() > 0) {
            name = newEmail.toLowerCase();
         }
        
    }

    /**
     * This method is used to get the email address of the customer object
     *
     *
     * @return email
     */
    public String GetEmail() {
        return email;
    }

    /**
     * This method is get the supplement list of the customer
     *
     * @return Customer's supplement list
     */
    public ArrayList<Supplement> GetSuppList() {
        return supplements;
    }

    /**
     * This method is set the supplement list of the customer * @param list
     */
    public void SetSuppList(ArrayList<Supplement> list) {
        supplements = list;
    }

    /**
     * This method is used to add new Supplement object to the supplement list
     *
     *
     * @param supObj Supplement class object
     */
    public void AddSupplement(Supplement supObj) {
        supplements.add(supObj);
    }

    /**
     * This method is used to remove an Supplement object from the supplement
     * list * @param supObj Supplement class object
     */
    public void RemoveSupplement(Supplement supObj) {

        boolean found = false;

        for (int i = 0; i < supplements.size(); i++) {
            if (supplements.get(i) == supObj) {
                supplements.remove(i);
                System.out.println("Supplement " + supObj.GetName() + " successfully removed ");
                found = true;
            }
        }
        if (found == false) {
            System.out.println("Supplement " + supObj.GetName() + " not found ");
        }
    }

    /**
     * This method is used to display all supplement in the supplement list *
     */
    public void PrintSupplement() {
        for (Supplement supplement : supplements) {
            supplement.Display();
        }
    }

    /**
     * This method is used get the total weekly cost of all the supplements
     *
     * @return total Total weekly cost of all supplements
     */
    public double GetWeeklyPrice() {

        double total = 0;

        for (Supplement supplement : supplements) {
            total += supplement.GetWeeklyCost();
        }
        return total;
    }

    /**
     * This method is used to display customer's information
     *
     */
    public void WriteOutput() {
        System.out.println("Customer name: " + name);
        System.out.println("Customer email: " + email);
        System.out.println("Your magazine is ready !!! ");
        System.out.println("Interested supplements: ");
        System.out.println("---------------------------");
        PrintSupplement();
    }
    
    

}
