import java.util.ArrayList;

/**
 * abstract class representing a transaction
 */
abstract class Transaction { 
    private double amount;

    abstract public boolean commit();

    public double getAmount(){
        return this.amount;
    }

    abstract public boolean argCheck();
}

/**
 * a transaction that deposits money into an account
 */
class DepositTransac extends Transaction{

    private double amount;
    private BankAccount account;

    DepositTransac(double amount, BankAccount account){
        this.amount = amount;
        this.account = account;
    }

    /**
     * returns true if the money was deposited, false otherwise
     */
    public boolean commit(){
        if (!this.argCheck()){
            return false;
        }
        else{
            this.account.deposit(this.amount);
            System.out.println("the amount was successfully deposited");
            return true;
        }
    }

    /**
     * returns true if the amount is nonnegative and the account exists, false otherwise
     */
    public boolean argCheck(){
        if (this.amount < 0){
            System.out.println("U cannot deposit a negative amount");
            return false;
        }
        if (this.account == null){
            System.out.println("No account to deposit to");
            return false;
        }
        return true;
    }
}

/**
 * A transaction that withdraws money from an account
 */
class WithdrawTransac extends Transaction{
    private double amount;
    private BankAccount account;

    WithdrawTransac(double amount, BankAccount account){
        this.amount = amount;
        this.account = account;
    }


    /**
     * returns true if the money was withdrawn, false otherwise
     */
    public boolean commit(){
        if (!this.argCheck()){
            return false;
        }
        else if (this.account.withdraw(this.amount)){
            System.out.println("the amount was successfully withdrawn");
            return true;
        }
        System.out.println("the amount wasn't withdrawn");
        return false;
    }

    /**
     * returns true if the amount is nonnegative and the account exists, false otherwise
     */
    public boolean argCheck(){
        if (this.amount < 0){
            System.out.println("U cannot withdraw a negative amount");
            return false;
        }
        if (this.account == null){
            System.out.println("No account to withdraw from");
            return false;
        }
        return true;
    }

}

class TransferTransac extends Transaction{
    private double amount;
    private BankAccount depositAccount; // deposits the money in this account
    private BankAccount withdrawAccount; //withdraws money from this account


    TransferTransac(double amount, BankAccount depAcc, BankAccount widAcc){
        this.amount = amount;
        this.depositAccount = depAcc;
        this.withdrawAccount = widAcc;
    }

    /**
     * returns true if the money was successfully transferred from one account to another, false otherwise
     */
    public boolean commit(){
        if (!this.argCheck()){
            return false;
        }
        boolean wasWithdrawn = this.withdrawAccount.withdraw(this.amount);
        if (wasWithdrawn){
            this.depositAccount.deposit(this.amount);
            System.out.println("amount was transferred successfully");
            return true;
        }
        System.out.println("Amount wasn't transferred");
        return false;
    }

    /**
     * returns true if the amount is nonnegative and both accounts exist, false otherwise
     */
    public boolean argCheck(){
        if (amount < 0){
            System.out.println("Cannot transfer negative amount");
            return false;
        }
        else if (this.depositAccount == null){
            System.out.println("no account to deposit to");
            return false;
        }
        else if (this.withdrawAccount == null){
            System.out.println("no account to withdraw from to");
            return false;
        }
        return true;
    }

}


/**
 * This class is a queue of transactions, makes working with multiple transactions easier
 */
class TransacQueue{
    private ArrayList<Transaction> queue;

    TransacQueue(){
        this.queue = new ArrayList<Transaction>();
    }

    boolean isEmpty(){
        return (this.queue.size() == 0);
    }

    void enqueue(Transaction transac){
        this.queue.add(0, transac);
    }

    Transaction dequeue(){
        Transaction transac = this.queue.get(0);
        this.queue.remove(transac);
        return transac;
    }

}

class TransacBuilder{
    public String[] requestTypes = {"deposit", "withdraw", "transfer"};

    TransacBuilder(){
    
    }

    /**
     * 
     * @param request: string representing transaction type
     * @param amount: amount to be deposited, withdrawn or transferred
     * @param account1: deposit/withdraw account in deposit and withdraw transactions respectively
     * @param account2: in a transfer transaction, the amount is deposited into this account
     * @return the appropriate transaction based on the request
     */
    Transaction getTransac(String request, double amount, BankAccount account1, BankAccount account2){
        Transaction newTransac;
        if (request.equals(this.requestTypes[0])){
            newTransac = new DepositTransac(amount, account1);
        }
        else if (request.equals(this.requestTypes[1])){
            newTransac = new WithdrawTransac(amount, account1);
        }
        else if (request.equals(this.requestTypes[2])){
            newTransac = new TransferTransac(amount, account2, account1);
        }
        else{
            newTransac = null;
        }
        return newTransac;
    }
}