package Question2;

import java.util.ArrayList;

/**
     * @class PayingCustomer
     * @brief Contain Paying customer detail such as the list of customers add the payment method of a customer object
     *
     * @author deepikaa
     * @version 01
     * @date 23/02/2024
     */

public class PayingCustomer extends Customer {

	//member variables
	/**
	 * The Payment paymentMethod
	 */
	private Payment paymentMethod;
	
	/**
	 * The ArrayList<AssociateCustomer> customerList
	 */
	private ArrayList<AssociateCustomer> assoCusList;
	
	/**
	 * Default Constructor of PayingCustomer class                          
	 * 
	 */
	public PayingCustomer() {
		super();
		paymentMethod = new Payment();
		assoCusList = new ArrayList<AssociateCustomer>();
	}
	
	/**
	 * Non Default Constructor of PayingCustomer class                          
	 * 
         * @param  cusName Name of the customer 
	 * @param  cusEmail Customer's email address   
	 * @param  list A list of supplement  
	 * @param  bankAcc Bank account number 
	 * @param  option Type of payment method 
	 * @param  cusList A list of associate customer 
	 */
	public PayingCustomer(String cusName, String cusEmail, ArrayList<Supplement> list, long bankAcc, 
			int option, ArrayList<AssociateCustomer> cusList) {
		super(cusName, cusEmail, list);
		paymentMethod = new Payment(bankAcc, option);
		assoCusList = cusList;
	}
	
	/**
	 * add new AssociateCustomer to the  existing associate customer list                    
         * 
	 * @param customer AssociateCustomer class object    
	 */
	public void AddCustomer(AssociateCustomer customer) {
		assoCusList.add(customer);
	}
	
	/**
	 *  remove an AssociateCustomer object from the associate customer list                     
	 * 
         * 
	 * @param customer AssociateCustomer class object    
	 */
	public void RemoveCustomer(AssociateCustomer customer) {
		
		boolean found = false;
		
		for(int i=0; i<assoCusList.size(); i++) {
			if(assoCusList.get(i)==customer) {
				assoCusList.remove(i);
				System.out.println("Associate customer " + customer.GetCustomerName() + " has been removed ");
				found = true;
			}
		}
		if(found==false)
			System.out.println("Associate customer " + customer.GetCustomerName() + " not found ");
	}
	
	/**
	 * This method is used to get the Paying object of the PayingCustomer                         
	 * 
         * 
	 * @return paymentMethod Return the PayingCustomer object's paymentMethod   
	 */
	public Payment GetPayment() {
		return paymentMethod;
	}
	
	/**
	 * This method is used to get PayingCustomer's associate customer list                   
	 * 
         * 
         * 
	 * @return customerList Return the PayingCustomer object's associate customer list  
	 */
	public ArrayList<AssociateCustomer> GetAssoCusList(){
		return assoCusList;
	}
	
	/**
	 * This method is used to set PayingCustomer's associate customer list                   
	 * <p>
	 * Precondition: Pass in an ArrayList of associate customer <br>
	 * Postcondition: The customerList is set.
	 * </p>   
	 * @param list An ArrayList of associate customer
	 */
	public void SetAssoCusList(ArrayList<AssociateCustomer> list){
		assoCusList=list;
	}

	/**
	 * This method is used to display the name of all associate customer of the Paying customer                       
	 * 
         * 
         * 
	 */
	public void PrintCustomer() {
		for(int idx = 0; idx < assoCusList.size(); idx++) {   
			System.out.println(assoCusList.get(idx).GetCustomerName());
		}  
	}
	
	 
	/**
	 * This method is used to get PayingCustomer's total supplement weekly cost
	 * including his/her associate customer                        
	 
         * 
         * 
	 * @return total Return the PayingCustomer object's total supplement weekly cost   
	 */
	public double GetWeeklyPrice() {
		
		double total = super.GetWeeklyPrice();
	
		for(int idx=0; idx<assoCusList.size(); idx++) {
			AssociateCustomer customer = assoCusList.get(idx);
			total += customer.GetWeeklyPrice();
		}
		return total;
	}
	
	/**
	 * This method is used to display the info of the paying customer                      
	 * 
         * 
	 */
	public void WriteOutput() {
		super.WriteOutput();
		paymentMethod.Display();
		System.out.println("Total weekly charge: $" + GetWeeklyPrice());
		System.out.println("Associated customer: ");
		System.out.println("------------------------- ");
		PrintCustomer();
	}
	
	
}

