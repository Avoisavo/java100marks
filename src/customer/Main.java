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
import java.util.InputMismatchException;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Customer loggedInCustomer = null;
    
    /*public static void main (String[] args){
       // Get the last customer ID
        int lastCustomerId = CustomerRegistration.getLastCustomerId();

        // Increment the last customer ID to generate the next available ID
        int newCustomerId = lastCustomerId + 1;

        Customer customer = CustomerRegistration.createCustomer();
        System.out.println("Customer created: " + customer);

        // Register the new customer with the new customer ID
        CustomerRegistration.registerCustomer(customer, newCustomerId);
      
        // Close scanner and end program
        endProgram();
    }
    
    public static void endProgram(){
        scanner.close();
        System.out.println("end");
    }
   
}*/
    
    public static void main(String[] args) {
            boolean exit = false;

            while (!exit) {
                System.out.println("Welcome to the Customer Management System!");
                System.out.println("1. Register a new customer");
                System.out.println("2. Log in");
                System.out.println("3. Update customer information");
                System.out.println("4. Exit");
                System.out.print("Please choose an option: ");

                int choice = getIntInput();

                switch (choice) {
                    case 1:
                        registerCustomer();
                        break;
                    case 2:
                        logIn();
                        break;
                    case 3:
                        updateCustomer();
                        break;
                    case 4:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option! Please choose a valid option.");
                }
            }
        
        // Close scanner and end program
        endProgram();
    }
    
    
        public static void endProgram() {
            scanner.close();
            System.out.println("Program ended.");
        }
    
    public static int getIntInput() {
        int choice = 0;
        try {
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Consume invalid input
        }
        return choice;
    }
    
    public static void registerCustomer() {
        // Get the last customer ID
        int lastCustomerId = CustomerRegistration.getLastCustomerId();
        // Increment the last customer ID to generate the next available ID
        int newCustomerId = lastCustomerId + 1;
        Customer customer = CustomerRegistration.createCustomer();
        System.out.println("Customer created: " + customer);
        // Add the customer to the list of customers
        CustomerRegistration.addCustomer(customer);
        // Register the new customer with the new customer ID
        CustomerRegistration.registerCustomer(customer, newCustomerId);
    }
    
    public static void logIn() {
    Customer loggedInCustomer = CustomerLogin.logInCustomer();
    if (loggedInCustomer != null) {
        System.out.println("Logged in successfully!");
        // Set the logged-in customer
        Main.loggedInCustomer = loggedInCustomer;

        // Print the details of the logged-in customer
        System.out.println("Customer details:");
        System.out.println("Name: " + loggedInCustomer.getName());
        System.out.println("IC: " + loggedInCustomer.getIc());
        System.out.println("Phone: " + loggedInCustomer.getPhone());
        System.out.println("Points: " + loggedInCustomer.getPoints());
    } else {
        System.out.println("Login failed. Please try again.");
    }
}
    
    public static void updateCustomer() {
        if (loggedInCustomer == null) {
            System.out.println("Please login first");
            return;
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("What would you like to update?");
            System.out.println("1. Name");
            System.out.println("2. Phone");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    CustomerUpdate.updateName(loggedInCustomer);
                    break;
                case 2:
                    CustomerUpdate.updatePhone(loggedInCustomer);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option! Please choose a valid option.");
            }
        }
    }
}

   