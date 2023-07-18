import java.util.*;

public class BankApplication {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the 'Name' and 'CustomerId' to access 1st user's Bank account:");
        String name = sc.nextLine();
        String customerId = sc.nextLine();
        BankAccount obj1 = new BankAccount(name, customerId);
        System.out.println((obj1 == null));
        System.out.println("Enter the 'Name' and 'CustomerId' to access 2nd user's Bank account:");
        name = sc.nextLine();
        customerId = sc.nextLine();
        BankAccount obj2 = new BankAccount(name, customerId);
        System.out.println("Enter the amount");
        double amount = Double.parseDouble(sc.nextLine());
        sc.close();
        DepositTransaction depTransac = new DepositTransaction(amount, obj1);
        depTransac.argCheck();
        WithdrawTransaction widTransac = new WithdrawTransaction(amount, obj2);
        TransactionQueue queue = new TransactionQueue();
        queue.enqueue(widTransac);
        queue.enqueue(depTransac);
        runTransactions(obj1, obj2, queue);

    }

    public static void runTransactions(BankAccount acc1, BankAccount acc2, TransactionQueue queue) {
        if (queue != null) {
            while (!queue.isEmpty()) {
                if (queue.dequeue().commit()) {
                    System.out.println("Transaction was completed");

                } else {
                    System.out.println("Transaction wasn't completed");
                }
                System.out.printf("acc1 balance: %f \n", acc1.getBalance());
                System.out.printf("acc2 balance %f \n", acc2.getBalance());
            }
        }
    }
}
