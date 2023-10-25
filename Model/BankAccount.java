package Model;

public class BankAccount {
    private double balance;
    private final String customerName;
    private final String customerId;// unique identifier for a customer
    private static final double  LOWEST_BALANCE = 0;

    public BankAccount(String customerName, String customerId) {
        int BASE_AMOUNT = 200;
        this.customerName = customerName;
        this.customerId = customerId;
        this.balance = BASE_AMOUNT;
    }

    /**
     * returns true if the two accounts are the same
     */
    public boolean equals(BankAccount account2) {
        if (account2 != null){
            return (this.customerId.equals(account2.customerId));
        }
        return false;
    }

    /**
     * deposits a positive amount of money into the account
     */
    public void deposit(double amount) {
        if (amount >= 0) {
            this.balance += amount;
        }
    }

    public double getBalance() {
        return this.balance;
    }

    /**
     * returns true if the money was withdrawn, false otherwise
     */
    public boolean withdraw(double amt) {
        if (balance -amt>= LOWEST_BALANCE) {
            this.balance -= amt;
            return true;
        }
        return false;
    }

    protected void setLowestBalance(Double balance){

    }

    public String getCustomerName(){
        return this.customerName;
    }

}
