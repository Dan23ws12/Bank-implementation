package Transactions;

import Model.BankAccount;

/**
 * A transaction that withdraws money from an account
 */
class WithdrawTransaction extends Transaction {
    public WithdrawTransaction(double amount, BankAccount account) {
        super(amount);
        this.setWithdrawAccount(account);
    }

    /**
     * If the account has enough money then this function executes the transaction,
     * throws an Exception otherwise
     */
    @Override
    public void executeTransaction() {
        this.validate();
        this.getWithdrawAccount().withdraw(this.getAmount());
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
