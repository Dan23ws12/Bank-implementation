
public class BankAccount {
    private double balance;
    private String customerName;
    private String customerId;// unique identifier for a customer
    private static int BASE_AMOUNT = 200;

    public BankAccount(String customerName, String customerId) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.balance = BASE_AMOUNT;
    }

    /**
     * returns true if the two accounts are the same
     */
    public boolean equals(BankAccount account2) {
        return (this.customerId.equals(account2.customerId));
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
        if (balance >= amt) {
            this.balance -= amt;
            return true;
        }
        return false;
    }

    public String getName() {
        return this.customerName;
    }

}
