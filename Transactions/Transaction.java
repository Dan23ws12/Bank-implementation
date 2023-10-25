package Transactions;

import Model.BankAccount;
/**
 * abstract class representing a transaction
 */
abstract class Transaction {
    private BankAccount depositAccount; // deposits the money in this account
    private BankAccount withdrawAccount; // withdraws money from this account
    private final double amount;
    public Transaction(double amount){
        this.amount = amount;
    }

    public void setDepositAccount(BankAccount depositAccount){
        this.depositAccount = depositAccount;
    }

    public void setWithdrawAccount(BankAccount withdrawAccount){
        this.withdrawAccount = withdrawAccount;
    }
    /*
    * returns true if an account is not null, false otherwise
    * */
    public boolean isAccountNotNull(BankAccount account){
        return (account != null);
    }

    abstract public void executeTransaction();

    abstract public boolean validate();

    public double getAmount() {
        return this.amount;
    }

    public BankAccount getDepositAccount() {
        return depositAccount;
    }

    public BankAccount getWithdrawAccount() {
        return withdrawAccount;
    }
}


