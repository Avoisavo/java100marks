package redeemitem; // please put it under "customer" module

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private static List<Customer> customers = new ArrayList<>();
    
    public static List<Customer> getCustomers() {

        return customers;
    }
    
    private String id;
    private String name;
    private String ic;
    private String phone;
    private String loyaltyStatus;
    private int points;

    // main constructor
    public Customer(String name, String ic, String phone) {
        this.name = name;
        this.ic = ic;
        this.phone = phone;
        this.loyaltyStatus = "Classic"; // Set default loyalty status
        this.points = 0;
    }
    
    // please put all constructors together at the top of the class
    public Customer(String id, String name, String ic, String phone, int points) {
        this.id = id;
        this.name = name;
        this.ic = ic;
        this.phone = phone;
        this.points = points;
    }
    
    public Customer(String name, String ic){
       this(name,ic,""); 
    }
    
    public Customer(String name){
       this(name,"",""); 
    }
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIc() {
        return ic;
    }

    public String getPhone() {
        return phone;
    }
    
    public String getLoyaltyStatus() {
        return loyaltyStatus;
    }
    
    

    public int getPoints() {
        return points;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void setLoyaltyStatus(String loyaltyStatus) {
        this.loyaltyStatus = loyaltyStatus;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 
        if (o == null || getClass() != o.getClass()) return false; 
        Customer customer = (Customer) o;
        return name.equals(customer.name) && ic.equals(customer.ic);
    }

}


