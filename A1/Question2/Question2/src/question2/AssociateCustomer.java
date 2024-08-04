package Question2;

import java.util.ArrayList;
/**
     * @class associateCustomer
     * @brief Contain Associate customer object
     *
     * @author deepikaa
     * @version 01
     * @date 23/02/2024
     */

public class AssociateCustomer extends Customer {

    
    /**
     * Default Constructor of AssociateCustomer class
     *
     * This class is used to represent the default value of Customer parent
     * class.
     *
     */
    public AssociateCustomer() {
        super();
    }

   
    /**
     * non-default Constructor of AssociateCustomer class
     *
     * This class is used to represent the AssociteCustomer
     * variables
     *
     *
     * @param cusName Name of the customer
     * @param cusEmail Customer's email address
     * @param list A list of supplement
     */
    public AssociateCustomer(String cusName, String cusEmail, ArrayList<Supplement> list) {
        super(cusName, cusEmail, list);
    }

}
