/**import org.junit.jupiter.*; */

public class TestTransactions{
    private static BankAccount account1;
    private static BankAccount account2;
    private static Transaction transac;
    public static void main(String[] args){
        testZeroTransaction();
        testNegativeTransaction();
        testNullTransaction();
        testDepTransaction();
        testWithdrawTransaction();
    }
    /**
     * performs basic tests on the deposit transaction
     */

    private static void initAllTests(){
        account1 = new BankAccount("Sing", "Song");
        account2 = new BankAccount("TiL", "TOK");
    }
    
    
    private static void testDepTransaction(){
       
        DepositTransaction transac = new DepositTransaction(300, account1);
        boolean failed = false;
        if (!transac.commit()){
            System.out.println("Failed basic deposit test\n");
            failed = true;
        }
        if (account1.getBalance() != 500.0){
            System.out.println("Balance changed after adding 0\n");
            System.out.printf("%f\n", account1.getBalance());
            failed = true;
        }
        if(!failed){
            System.out.println("passed all deposit tests\n");
        }

    }


    /**
     * performs basic tests on the withdraw transaction
     */
    private static void testWithdrawTransaction(){
        account1 = new BankAccount("Sing", "Song");
        boolean failed = false;
    
        transac = new WithdrawTransaction(203, account1);
        if(transac.commit()){
            System.out.println("Withdrew over max amount\n");
            System.out.printf("Balance: %f\n", account1.getBalance());
            failed = true;
        }
        transac = new WithdrawTransaction(32, account1);
        if (!transac.commit()){
            System.out.println("Couldn't withdraw 32 from fresh account\n");
            failed = true;
        }
        if(account1.getBalance() != 168.0){
            System.out.println("Withdrew a different amount\n");
            System.out.printf("Balance: %f\n", account1.getBalance());
            failed = true;
        }
        transac = new WithdrawTransaction(170, account1);
        if(transac.commit()){
            System.out.println("Withdrew over max amount\n");
            System.out.printf("Balance: %f\n", account1.getBalance());
            failed = true;
        }
        if (!failed){
            System.out.println("Passed all withdraw tests\n");
        }
    }


    /**
     * tests all transactions on whether they handle transaction done with 0 money properly
     */
    private static void testZeroTransaction(){
        
        transac = new DepositTransaction(0, account1);
        boolean failed = false;
        if (!transac.commit()){
            System.out.println("Failed to add 0 to bank account\n");
            failed = true;
        }
        transac = new WithdrawTransaction(0, account2);
        if (!transac.commit()){
            System.out.println("Failed to withdraw 0 to bank account\n");
            failed = true;
        }
        transac = new TransferTransaction(0,account1, account2);
        if (!transac.commit()){
            System.out.println("Failed to transfer 0 to bank account\n");
            failed = true;
        }
        if (!failed){
            System.out.println("passed all zero tests\n");
        }
    }

    /**
     * tests all transactions on whether they handle transaction done with negative money properly
     */
    private static void testNegativeTransaction(){
        account1 = new BankAccount("Sing", "Song");
        account2 = new BankAccount("TiL", "TOK");
        transac = new DepositTransaction(-20, account1);
        boolean failed = false;
        if (transac.commit()){
            System.out.println("Deposited a negative amount to bank account\n");
            failed = true;
        }
        transac = new WithdrawTransaction(-35, account1);
        if (transac.commit()){
            System.out.println("Withdrew a negative amount from bank account\n");
            failed = true;
        }
        transac = new TransferTransaction(-23,account1, account2);
        if (transac.commit()){
            System.out.println("Transfered a negative amount to bank account\n");
            failed = true;
        }
        if (!failed){
            System.out.println("passed all negative tests\n");
        }

    }


    /**
     * tests all transactions on whether they handle transaction done with none existent accounts 
     * properly
     */
    private static void testNullTransaction(){
        transac = new DepositTransaction(20, null);
        boolean failed = false;
        try{
            failed = transac.commit();
        }
        catch(Exception e){
            System.out.println("Tried to deposit into a non existent account\n");
            failed = true;
        }
        transac = new WithdrawTransaction(0, null);
        try{
            failed = transac.commit();
        }
        catch (Exception e){
            System.out.println("Tried to deposit into a non existent account\n");
            failed = true;
        }
        transac = new TransferTransaction(0,null, account2);
        try{
            failed = transac.commit();
        }
        catch (Exception e){
            System.out.println("Tried to transfer into a existent account\n");
            failed = true;
        }
        transac = new TransferTransaction(0,account1, null);
        try{
            failed = transac.commit();
        }
        catch (Exception e){
            System.out.println("Tried to transfer from a existent account\n");
            failed = true;
        }
        if (!failed){
            System.out.println("passed all null tests\n");
        }

    }
}