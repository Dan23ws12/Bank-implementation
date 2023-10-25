package Model;

import java.io.Serializable;
import java.util.HashMap;

public class CustomerDatabase implements Serializable {
    private final HashMap<String[], BankAccount> customers;
    private static CustomerDatabase INSTANCE;

    private CustomerDatabase(){
        this.customers = new HashMap<String[], BankAccount>();
    }
    public static CustomerDatabase getINSTANCE() {
        if (INSTANCE == null){
            INSTANCE = new CustomerDatabase();
        }
        return INSTANCE;
    }

    public void addCustomer(String username,String password, BankAccount account){

    }
}
