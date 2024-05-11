/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Linghue Wee
 */


        
public class CustomerRegistration {
    public static Customer createCustomer(){
        boolean isValidCustomer = false;
        Customer customer = null;
        
        while (!isValidCustomer){
            
            String name = getInput("Enter user name : ", "^[a-zA-Z]+$");
            String ic = getInput("Enter user ic : ", "^\\d{12}$");
            String phone = getInput("Enter user phone : ","^[0-9]{10}$");

            customer = new Customer (name, ic, phone);
            isValidCustomer = CustomerValidation.validateCustomerDetails(customer);
        }
        return customer;
        
    }
    
            
    public static String getInput(String message, String pattern){
        String input;
        boolean isValidInput = false;
        
        do {
            System.out.print(message);
            input = Main.scanner.nextLine();
            if (input.matches(pattern)) {
                isValidInput = true;
            } else {
                System.out.println("Invalid input! Please enter a valid value.");
            }
        } while (!isValidInput);
        
        return input;
    }
    
    public static void registerCustomer(Customer customer, int newCustomerId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("customers.txt", true))) {
            // Format the customer information
            String customerInfo = String.format("%03d,%s,%s,%s,%d\n", newCustomerId, customer.getName(), customer.getIc(), customer.getPhone(), customer.getPoints());
            // Write customer information to the file
            writer.write(customerInfo);
            System.out.println("Customer information written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to write customer information to file.");
        }
    }
    
    public static int getLastCustomerId() {
        int lastCustomerId = 0;
        try (Scanner fileScanner = new Scanner(new File("customers.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                int customerId = Integer.parseInt(parts[0]);
                lastCustomerId = Math.max(lastCustomerId, customerId);
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        return lastCustomerId;
    }
    
    public static void addCustomer(Customer customer) {
        Customer.getCustomers().add(customer);
    }
}    
       