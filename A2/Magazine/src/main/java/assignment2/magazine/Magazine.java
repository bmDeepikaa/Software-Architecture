/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2.magazine;

import java.io.Serializable;
import java.util.ArrayList;

/**
     * @class Magazine
     * @brief Contain Magazine object details such as a array of supplements and customers
     *
     * @author deepikaa
     * @version 02
     * @date 25/03/2024
     */
public class Magazine implements Serializable {

    private ArrayList<Supplement> supplements;
    private ArrayList<Customer> customers;

    // Default constructor
    public Magazine() {
        this.supplements = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    // Adding a single customer to arraylist of customer
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // Removing a single customer from arraylist of customer
    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    // Adding a single supplement to arraylist of supplement
    public void addSupplement(Supplement supplement) {
        supplements.add(supplement);
    }

    // Removing a single supplement from arraylist of supplement
    public void removeSupplement(Supplement supplement) {
        supplements.remove(supplement);
    }

    // Getter for arraylist of supplement
    public ArrayList<Supplement> getSupplements() {
        return supplements;
    }

    // Getter for arraylist of customer
    public ArrayList<Customer> getCustomers() {
        return customers;
    }
}