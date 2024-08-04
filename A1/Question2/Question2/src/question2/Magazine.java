package Question2;

import java.util.ArrayList;
/**
     * @class Magazine
     * @brief Contain supplement and magazine details in a object
     *
     * @author deepikaa
     * @version 01
     * @date 23/02/2024
     */

public class Magazine {

    //member variables
    /**
     * The String name
     */
    private String mName;

    /**
     * The double weeklyCost
     */
    private double weeklyCost;

    /**
     * The ArrayList<Supplement> suppList
     */
    private ArrayList<Supplement> suppList;

    /**
     * Default Constructor of Magazine class
     *
     *
     *
     */
    public Magazine() {
        mName = "NA";
        weeklyCost = 0;
        suppList = new ArrayList<Supplement>();
    }

    /**
     * Non Default Constructor of Magazine class
     *
     *
     * @param names Name of Magazine object
     * @param cost Weekly cost of the Magazine object
     * @param list Supplement list of the Magazine object
     */
    public Magazine(String names, double cost, ArrayList<Supplement> list) {
        mName = names;
        weeklyCost = cost;
        suppList = list;
    }

    /**
     * This method is used to set the name   
     * @param newName
     * 
     */
    public void SetName(String newName) {
        mName = newName;
    }

    /**
     * This method is used to get the name 
     * @return name
     * Return the Magazine object's name
     */
    public String GetName() {
        return mName;
    }

    /**
     * This method is used to get the weekly cost of the Magazine 
     * @return weeklyCost Return the Magazine object's weekly cost
     */
    public double GetWeeklyCost() {
        return weeklyCost;
    }

    /**
     * This method is used to set the weekly cost  
     * @param newCost Weekly cost of Magazine object
     */
    public void SetWeeklyCost(double newCost) {
        weeklyCost = newCost;
    }

   
    /**
     * This method is used to calculate and return the total weekly of the magazine and supplements
     * @return finalCost Return the Magazine object's
     * total weekly cost
     */
    public double GetTotalMagazineCost() {
        double total = 0;

        for (int i = 0; i < suppList.size(); i++) {
            total += suppList.get(i).GetWeeklyCost();
        }
        double finalCost = total + weeklyCost;
        return finalCost;
    }

    /**
     * This method is used to set the supplement list the Magazine class object
     * * @param newList New supplement list of Magazine object
     */
    public void SetSupplimentList(ArrayList<Supplement> newList) {
        suppList = newList;
    }

    /**
     * This method is used to return the supplement list of Magazine object 
     * @return suppList Return the supplement list of Magazine object
     */
    public ArrayList<Supplement> GetMagSupplement() {
        return suppList;
    }

    /**
     * This method is to display the information of the Magazine object,
     * including name weekly cost, total cost and corresponding supplements 
     */
    public void WriteOutput() {
        System.out.println("Magazine name: " + mName);
        System.out.println("Magazine cost: " + weeklyCost);
        System.out.println("Total cost - including all supplements: " + GetTotalMagazineCost());
        System.out.println("Suppliments: ");
        System.out.println("---------------------------");

        for (int i = 0; i < suppList.size(); i++) {
            suppList.get(i).Display();
        }
    }
}
