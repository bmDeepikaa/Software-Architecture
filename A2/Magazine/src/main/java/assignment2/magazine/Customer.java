/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2.magazine;

/**
     * @class Customer
     * @brief Contain base customer object
     *
     * @author deepikaa
     * @version 02
     * @date 25/03/2024
     */
import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {

    private String name;
    private String email;
    private Address address;
    private ArrayList<Supplement> supplements;

    public Customer() {
        this.name = "";
        this.email = "";
        this.address = new Address();
        this.supplements = new ArrayList<>();
    }
    
    // Parameterized constructor
    public Customer(String newName, String newEmail, Address newAddress) {
        name = newName;
        email = newEmail;
        address = newAddress;
        this.supplements = new ArrayList<>();
    }

    // Getter for  name
    public String getName() {
        return name;
    }

    // Setter for  name
    public void setName(String newName) {
        name = newName;
    }

    // Getter for  email
    public String getEmail() {
        return email;
    }

    // Setter for  email
    public void setEmail(String newEmail) {
        email = newEmail;
    }

    // Getter for ArrayList containing supplements
    public ArrayList<Supplement> getSupplement() {
        return supplements;
    }

    // Setter for ArrayList containing supplements
    public void setSupplement(ArrayList<Supplement> supplements) {
        this.supplements = supplements;
    }

    // To add a single supplement to customer arraylist of supplements
    public void addSupplement(Supplement supplement) {
        this.supplements.add(supplement);
    }

    // To remove a single supplement from customer arraylist of supplements
    public void removeSupplement(Supplement supplement) {
        this.supplements.remove(supplement);
    }

    // Getter for address
    public Address getAddress() {
        return address;
    }

    // Setter for  address
    public void setAddress(Address newAddress) {
        address = newAddress;
    }

    // Overriding method for toString()
    @Override
    public String toString() {
        return name;
    }
}