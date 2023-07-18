import java.util.*;

public class BankApplication {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the 'Name' and 'CustomerId' to access 1st user's Bank account:");
        String name=sc.nextLine();
        String customerId=sc.nextLine();
        BankAccount obj1=new BankAccount(name,customerId);
        System.out.println("Enter the 'Name' and 'CustomerId' to access 2nd user's Bank account:");
        name=sc.nextLine();
        customerId=sc.nextLine();
        BankAccount obj2=new BankAccount(name,customerId);
        System.out.println("Enter the amount");
        double amount = Double.parseDouble(sc.nextLine());
        DepositTransaction depTransac = new DepositTransaction(amount, obj1);
        WithdrawTransaction widTransac = new WithdrawTransaction(amount, obj2);
        TransactionQueue queue = new TransactionQueue();
        queue.enqueue(widTransac);
        queue.enqueue(depTransac);
        runTransactions(obj1, obj2, queue);
        sc.close();
    }

    public static void runTransactions(BankAccount acc1, BankAccount acc2, TransactionQueue queue){
        if (queue != null){
            while(!queue.isEmpty()){
                Transaction transac = queue.dequeue();
                if (transac.commit()){
                    System.out.println("Transaction was completed");
                }else{
                    System.out.println("Transaction wasn't completed");
                }
                System.out.printf("acc1 balance: %f \n", acc1.getBalance());
                System.out.printf("acc2 balance %f \n", acc2.getBalance());
            }
        }
    }
}

class BankAccount{
    private double bal;
    private String customerName;
    private String customerId;// unique identifier for a customer

    BankAccount(String customerName,String customerId){
        this.customerName=customerName;
        this.customerId=customerId;
        this.bal = 200;
    }

    /**
     * returns true if the two accounts are the same
     */
    boolean equals(BankAccount account2){
        return (this.customerId.equals(account2.customerId));
    }

    /**
     * deposits a positive amount of money into the account
     */
    void deposit(double amount){
        if(amount>=0){
            bal+=amount;
        }
    }

    double getBalance(){
        return this.bal;
    }

    /**
     * returns true if the money was withdrawn, false otherwise
     */
    boolean withdraw(double amt){
        if(bal>=amt){
            bal-=amt;
            return true;
        }
        return false;
    }

    String getName(){
        return this.customerName;
    }

}

