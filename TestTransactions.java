public class TestTransactions{
    public static void main(String[] args){
        testZeroTransac();
        testNegativeTransac();
        testNullTransac();
        testDepTransac();
        testWithdrawTransac();
    }
    /**
     * performs basic tests on the deposit transaction
     */
    private static void testDepTransac(){
        BankAccount acc1 = new BankAccount("Sing", "Song");
        DepositTransac transac = new DepositTransac(300, acc1);
        boolean failed = false;
        if (!transac.commit()){
            System.out.println("Failed basic deposit test\n");
            failed = true;
        }
        if (acc1.getBalance() != 500.0){
            System.out.println("Balance changed after adding 0\n");
            System.out.printf("%f\n", acc1.getBalance());
            failed = true;
        }
        if(!failed){
            System.out.println("passed all deposit tests\n");
        }

    }


    /**
     * performs basic tests on the withdraw transaction
     */
    private static void testWithdrawTransac(){
        BankAccount acc1 = new BankAccount("Sing", "Song");
        boolean failed = false;
    
        WithdrawTransac transac = new WithdrawTransac(203, acc1);
        if(transac.commit()){
            System.out.println("Withdrew over max amount\n");
            System.out.printf("Balance: %f\n", acc1.getBalance());
            failed = true;
        }
        transac = new WithdrawTransac(32, acc1);
        if (!transac.commit()){
            System.out.println("Couldn't withdraw 32 from fresh account\n");
            failed = true;
        }
        if(acc1.getBalance() != 168.0){
            System.out.println("Withdrew a different amount\n");
            System.out.printf("Balance: %f\n", acc1.getBalance());
            failed = true;
        }
        transac = new WithdrawTransac(170, acc1);
        if(transac.commit()){
            System.out.println("Withdrew over max amount\n");
            System.out.printf("Balance: %f\n", acc1.getBalance());
            failed = true;
        }
        if (!failed){
            System.out.println("Passed all withdraw tests\n");
        }
    }


    /**
     * tests all transactions on whether they handle transaction done with 0 money properly
     */
    private static void testZeroTransac(){
        BankAccount acc1 = new BankAccount("Sing", "Song");
        BankAccount acc2 = new BankAccount("TiL", "TOK");
        Transaction transac = new DepositTransac(0, acc1);
        boolean failed = false;
        if (!transac.commit()){
            System.out.println("Failed to add 0 to bank account\n");
            failed = true;
        }
        transac = new WithdrawTransac(0, acc2);
        if (!transac.commit()){
            System.out.println("Failed to withdraw 0 to bank account\n");
            failed = true;
        }
        transac = new TransferTransac(0,acc1, acc2);
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
    private static void testNegativeTransac(){
        BankAccount acc1 = new BankAccount("Sing", "Song");
        BankAccount acc2 = new BankAccount("TiL", "TOK");
        Transaction transac = new DepositTransac(-20, acc1);
        boolean failed = false;
        if (transac.commit()){
            System.out.println("Deposited a negative amount to bank account\n");
            failed = true;
        }
        transac = new WithdrawTransac(-35, acc1);
        if (transac.commit()){
            System.out.println("Withdrew a negative amount from bank account\n");
            failed = true;
        }
        transac = new TransferTransac(-23,acc1, acc2);
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
    private static void testNullTransac(){
        BankAccount acc1 = new BankAccount("Sing", "Song");
        BankAccount acc2 = new BankAccount("TiL", "TOK");
        Transaction transac = new DepositTransac(20, null);
        boolean failed = false;
        try{
            failed = transac.commit();
        }
        catch(Exception e){
            System.out.println("Tried to deposit into a non existent account\n");
            failed = true;
        }
        transac = new WithdrawTransac(0, null);
        try{
            failed = transac.commit();
        }
        catch (Exception e){
            System.out.println("Tried to deposit into a non existent account\n");
            failed = true;
        }
        transac = new TransferTransac(0,null, acc2);
        try{
            failed = transac.commit();
        }
        catch (Exception e){
            System.out.println("Tried to transfer into a existent account\n");
            failed = true;
        }
        transac = new TransferTransac(0,acc1, null);
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