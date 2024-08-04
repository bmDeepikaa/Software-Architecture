package Question2;

/**
     * @class Supplement
     * @brief Contain supplements of a magazine in  object
     *
     * @author deepikaa
     * @version 01
     * @date 23/02/2024
     */

public class Supplement {

    //member variables
    /**
     * The String name
     */
    private String name;

    /**
     * The double weeklyCost
     */
    private double weeklyCost;

    /**
     * Default Constructor of Supplement class
     * <p>
     * This class is used to represent the default value of String name and
     * double weeklyCost
     * </p>
     */
    public Supplement() {
        name = "NA";
        weeklyCost = 0;
    }

    /**
     * Constructor of Supplement class
     * <p>
     * This class is used to represent a String of name and double weeklyCost
     * </p>
     *
     * @param names Name of Supplement object
     * @param cost Weekly cost of the Supplement object
     */
    public Supplement(String names, double cost) {
        name = names;
        weeklyCost = cost;
    }

    /**
     * This method is used to set the name 
     *
     *
     * @param newName Name of Supplement object
     */
    public void SetName(String newName) {
        if (name.length() > 0) {
            name = newName.toLowerCase();
        } 
    }

    /**
     * This method is used to get the name 
     *
     * @return name Return the Supplement object's name
     */
    public String GetName() {
        return name;
    }

    /**
     * This method is used to set the weekly cost 
     *
     * @param newCost Weekly cost of Supplement object
     */
    public void SetWeeklyCost(double newCost) {
        weeklyCost = newCost;
    }

    /**
     * This method is used to get the weekly cost of the Supplement 
     *
     * @return weeklyCost Return the Supplement object's weekly cost
     */
    public double GetWeeklyCost() {
        return weeklyCost;
    }

    /**
     * This method is to display the name and weekly cost of Supplement
     *
     */
    public void Display() {
        System.out.println("Supplement name: " + name);
        System.out.println("Weekly cost: " + weeklyCost);
        System.out.println();
    }
}
