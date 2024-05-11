/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package customer; // please put it under "customer" module

import java.util.ArrayList;
import java.util.List;

public class Customer {
    //public static Customer[] customers;
    private static List<Customer> customers = new ArrayList<>();
    
    //  to get all customers
  public static List<Customer> getCustomers() {
    // this method no need, "Customers" should have no access to other customers in the system
    // if you want to keep this method, write it so that "Admin" is the only class that uses this method
        return customers;
    }

    
    private String id;
    private String name;
    private String ic;
    private String phone;
    private int points;
    private static int customerCount = 0; // this property no need, can get from customers list

    // main constructor
    public Customer(String name, String ic, String phone) {
        this.name = name;
        this.ic = ic;
        this.phone = phone;
      
        customerCount++; // please remove this
        this.id = String.format("%03d",customerCount);
        this.points = 0;
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

    public int getPoints() {
        return points;
    }

  // please remove this
    public static int getCustomerCount() {
        return customerCount;
    }
    
  // please put all constructors together at the top of the class
    public Customer(String id, String name, String ic, String phone, int points) {
    // please format your code >:(
    this.id = id;
    this.name = name;
    this.ic = ic;
    this.phone = phone;
    this.points = points;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // no need this line, just check if name or ic is equal to each other
        if (o == null || getClass() != o.getClass()) return false; // no need this line, just check if name or ic is equal to each other
        Customer customer = (Customer) o;
        return name.equals(customer.name) && ic.equals(customer.ic);
    }


}
    //end 
    
    
// please dont have so many empty lines


