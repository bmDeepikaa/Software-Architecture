/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2.magazine;

import java.io.Serializable;
/**
     * @class PaymentMethod
     * @brief Contain Payment object
     *
     * @author deepikaa
     * @version 02
     * @date 25/03/2024
     */
public class PaymentMethod implements Serializable {

    private String cardType;
    private int accountNo;
    
    public PaymentMethod() {
        this.cardType = "";
        this.accountNo = 0;
    }

    // Parameterized constructor
    public PaymentMethod(String newCardType, int newAccountNo) {
        cardType = newCardType;
        accountNo = newAccountNo;
    }
    
    // Getter for card type
    public String getCardType() {
        return cardType;
    }

    // Setter for card type
    public void setCardType(String newCardType) {
        cardType = newCardType;
    }

    // Getter for account number
    public int getAccountNo() {
        return accountNo;
    }

    // Setter for account number
    public void setAccountNo(int newAccountNo) {
        accountNo = newAccountNo;
    }

    // Overriding method for toString()
    @Override
    public String toString() {
        return cardType + "\n" + accountNo;
    }
}