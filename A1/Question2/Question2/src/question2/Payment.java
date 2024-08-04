package Question2;
/**
     * @class Payment
     * @brief Contain payment details object
     *
     * @author deepikaa
     * @version 01
     * @date 23/02/2024
     */

//enum to save repeating constant
enum PaymentMethod {

    DEBITCARD,
    CREDITCARD
}

public class Payment {

    //member variable
    /**
     * The long bankAcc
     */
    private long bankAcc;

    /**
     * The PaymentMethod pay
     */
    private PaymentMethod pay;

    /**
     * Default Constructor of Payment class
     *
     */
    public Payment() {
        bankAcc = 0;
        pay = PaymentMethod.DEBITCARD; //by default is debit 

    }

    /**
     * Constructor of Payment class
     *
     * This class is used to represent a long of bankAcc and int option 
     * option 0= debitcard , option 1 = creditcard
     *
     * @param bank Bank account number of Payment object
     * @param option Option of the payment type
     */
    public Payment(long bank, int option) {
        bankAcc = bank;

        if (option == 0) {
            pay = PaymentMethod.DEBITCARD;
        } else if (option == 1) {
            pay = PaymentMethod.CREDITCARD;
        }
    }

    /**
     * This method is used to set the bankAcc of the Payment object
     *
     * @param accNumber Bank account of Payment object
     */
    public void SetBankAcc(long accNumber) {
        bankAcc = accNumber;
    }

    /**
     * This method is used to get the bankAcc of the Payment 
     *
     * @return bankAcc Return the Payment object's bank account number
     */
    public long GetBankAcc() {
        return bankAcc;
    }

    /**
     * This method is used to get the payment type of Payment class 
     *
     * @return type Return the Payment object's payment method type
     */
    public String GetBankAccType() {

        String type = null;

        switch (pay) {
            case DEBITCARD:
                type = "Direct debit user";
                break;
            case CREDITCARD:
                type = "Credit card user";
                break;
        }
        return type;
    }

    /**
     * This method is to display the information of Payment object *
     */
    public void Display() {
        System.out.println("Bank Acc Num: " + bankAcc);
        System.out.println("Payment method: " + GetBankAccType());
    }

    
}
