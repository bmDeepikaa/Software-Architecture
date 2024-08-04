/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2.magazine;

import java.io.Serializable;
/**
     * @class supplement
     * @brief Contain supplement object and variables
     *
     * @author deepikaa
     * @version 02
     * @date 25/03/2024
     */
public class Supplement implements Serializable {

    private String name;
    private double weeklyCost;

    // Parameterized constructor
    public Supplement(String newName, double newWeeklyCost) {
        name = newName;
        weeklyCost = newWeeklyCost;
    }

    // Getter for supplement name
    public String getName() {
        return name;
    }

    // Setter for supplement name
    public void setName(String newName) {
        name = newName;
    }

    // Getter for supplement cost
    public double getCost() {
        return weeklyCost;
    }

    // Setter for supplement cost
    public void setCost(double newWeeklyCost) {
        weeklyCost = newWeeklyCost;
    }

    // Overriding method for toString()
    @Override
    public String toString() {
        return name;
    }
}