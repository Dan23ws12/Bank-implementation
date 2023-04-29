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
        DepositTransac depTransac = new DepositTransac(amount, obj1);
        WithdrawTransac widTransac = new WithdrawTransac(amount, obj2);
        depTransac.commit();
        widTransac.commit();
        System.out.printf("account1 balance: %f \n", obj1.getBalance());
        System.out.printf("account2 balance: %f \n", obj2.getBalance());
        sc.close();
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
        System.out.println("Bank balance insufficient");
        return false;
    }

    String getName(){
        return this.customerName;
    }

}

