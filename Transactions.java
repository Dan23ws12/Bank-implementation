import java.util.ArrayList;

/**
 * abstract class representing a transaction
 */
abstract class Transaction {
    private double amount;

    abstract public boolean commit();

    abstract public boolean argCheck();

    public double getAmount() {
        return this.amount;
    }

    public BankAccount getDepositAccount() {
        return null;
    }

    public BankAccount getWithdrawAccount() {
        return null;
    }
}

/**
 * a transaction that deposits money into an account
 */
class DepositTransaction extends Transaction {

    private double amount;
    private BankAccount depositAccount;

    public DepositTransaction(double amount, BankAccount account) {
        this.amount = amount;
        this.depositAccount = account;
    }

    /**
     * returns true if the money was deposited, false otherwise
     */
    @Override
    public boolean commit() {
        if (!this.argCheck()) {
            return false;
        } else {
            this.depositAccount.deposit(this.amount);
            return true;
        }
    }

    /**
     * returns true if the amount is nonnegative and the account exists, false
     * otherwise
     */
    @Override
    public boolean argCheck() {
        if ((this.amount < 0) || (this.depositAccount == null)) {
            return false;
        }
        return true;
    }

    @Override
    public BankAccount getDepositAccount() {
        return this.depositAccount;
    }
}

/**
 * A transaction that withdraws money from an account
 */
class WithdrawTransaction extends Transaction {
    private double amount;
    private BankAccount withdrawAccount;

    public WithdrawTransaction(double amount, BankAccount account) {
        this.amount = amount;
        this.withdrawAccount = account;
    }

    /**
     * returns true if the money was withdrawn, false otherwise
     */
    @Override
    public boolean commit() {
        if (!this.argCheck()) {
            return false;
        }
        return this.withdrawAccount.withdraw(this.amount);
    }

    /**
     * returns true if the amount is nonnegative and the account exists, false
     * otherwise
     */
    @Override
    public boolean argCheck() {
        if ((this.amount < 0) || (this.withdrawAccount == null)) {
            return false;
        }
        return true;
    }

    @Override
    public BankAccount getWithdrawAccount() {
        return this.withdrawAccount;
    }
}

class TransferTransaction extends Transaction {
    private double amount;
    private BankAccount depositAccount; // deposits the money in this account
    private BankAccount withdrawAccount; // withdraws money from this account

    public TransferTransaction(double amount, BankAccount depAcc, BankAccount widAcc) {
        this.amount = amount;
        this.depositAccount = depAcc;
        this.withdrawAccount = widAcc;
    }

    /**
     * returns true if the money was successfully transferred from one account to
     * another, false otherwise
     */
    @Override
    public boolean commit() {
        if (!this.argCheck()) {
            return false;
        }
        boolean wasWithdrawn = this.withdrawAccount.withdraw(this.amount);
        if (wasWithdrawn) {
            this.depositAccount.deposit(this.amount);
            return true;
        }
        return false;
    }

    /**
     * returns true if the amount is nonnegative and both accounts exist, false
     * otherwise
     */
    @Override
    public boolean argCheck() {
        if ((amount < 0) || (this.depositAccount == null) || (this.withdrawAccount == null)) {
            return false;
        }
        return true;
    }
}

/**
 * This class is a queue of transactions, makes working with multiple
 * transactions easier
 */
class TransactionQueue {
    private ArrayList<Transaction> queue;

    TransactionQueue() {
        this.queue = new ArrayList<Transaction>();
    }

    boolean isEmpty() {
        return (this.queue.size() == 0);
    }

    void enqueue(Transaction transac) {
        this.queue.add(transac);
    }

    Transaction dequeue() {
        Transaction transac = this.queue.get(0);
        this.queue.remove(transac);
        return transac;
    }

}

class TransactionFactory {
    public static String depositType = "deposit";
    public static String withdrawType = "withdraw";
    public static String transferType = "transfer";

    /**
     * 
     * @param type:           string representing transaction type to be returned
     * @param amount:         amount to be deposited, withdrawn or transferred
     * @param depositAccount: money is deposited in this account
     * @param account2:       money is withdrawn from this account
     * @return the appropriate transaction based on the request
     */
    public static Transaction getTransaction(String type, double amount, BankAccount depositAccount,
            BankAccount withdrawAccount) {
        if (type.equals(depositType)) {
            return new DepositTransaction(amount, depositAccount);
        } else if (type.equals(withdrawType)) {
            return new WithdrawTransaction(amount, withdrawAccount);
        } else if (type.equals(transferType)) {
            return new TransferTransaction(amount, depositAccount, withdrawAccount);
        }
        return null;
    }

    public static String getDepositType() {
        return depositType;
    }

    public static String getWithdrawType() {
        return withdrawType;
    }

    public static String getTransferType() {
        return transferType;
    }
}