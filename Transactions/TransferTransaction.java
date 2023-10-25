package Transactions;

import Model.BankAccount;

class TransferTransaction extends Transaction {

    public TransferTransaction(double amount, BankAccount depAcc, BankAccount widAcc) {
        super(amount);
        this.setDepositAccount(depAcc);
        this.setWithdrawAccount(widAcc);
    }

    /**
     * returns true if the money was successfully transferred from one account to
     * another, false otherwise
     */
    @Override
    public void executeTransaction() {
        this.validate();
        this.getWithdrawAccount().withdraw(this.getAmount());
        this.getDepositAccount().deposit(this.getAmount());
    }

    /**
     * returns true if the amount is nonnegative and both accounts exist, false
     * otherwise
     */
    @Override
    public boolean validate() {
        return (getAmount() >= 0) &&
                (this.isAccountNotNull(getWithdrawAccount())) && (this.isAccountNotNull(getDepositAccount()));
    }
}
