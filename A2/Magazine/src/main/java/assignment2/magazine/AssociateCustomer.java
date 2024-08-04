/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2.magazine;

/**
     * @class associateCustomer
     * @brief Contain Associate customer object
     *
     * @author deepikaa
     * @version 02
     * @date 25/03/2024
     */

public class AssociateCustomer extends Customer {
    
    public AssociateCustomer() {
        super();
    }

    // Parameterized constructor
    public AssociateCustomer(String newName, String newEmail, Address newAddress) {
        super(newName, newEmail, newAddress);
    }
}
