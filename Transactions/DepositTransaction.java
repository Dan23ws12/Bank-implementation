package Transactions;

import Model.BankAccount;
/**
 * a transaction that deposits money into an account
 */


class DepositTransaction extends Transaction {

    public DepositTransaction(double amount, BankAccount account) {
        super(amount);
        this.setDepositAccount(account);
    }

    /**
     * returns true if the money was deposited, false otherwise
     */
    @Override
    public void executeTransaction() {
        this.validate();
        this.getDepositAccount().deposit(this.getAmount());
    }

    /**
     * returns true if the amount is nonnegative and the account exists, false
     * otherwise
     */
    @Override
    public boolean validate() {
        return (this.getAmount() >= 0) &&
                (this.isAccountNotNull(getWithdrawAccount()));
    }

}