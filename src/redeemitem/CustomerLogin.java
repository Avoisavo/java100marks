/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customer; // please put it under customer module

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Linghue Wee
 */
public class CustomerLogin {
    // please rethink the approach for this method, there is an easier way to do this
    public static Customer findCustomer(String name, String ic) {
        for (Customer customer : Customer.getCustomers()) {
            if (customer.getName().equalsIgnoreCase(name) && customer.getIc().equals(ic)) {
                return customer; // Found the customer
            }
        }
        return null; // Customer not found
    }

        public static Customer logInCustomer() {
        while (true) {
            System.out.print("Enter your name: ");
            String name = Main.scanner.nextLine().trim(); // Trim leading and trailing spaces

            System.out.print("Enter your IC: ");
            String ic = Main.scanner.nextLine().trim(); // Trim leading and trailing spaces

            // Read customer data from the file and compare with user input
            try (Scanner fileScanner = new Scanner(new File("customers.txt"))) {
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    String[] parts = line.split(",");
                    String customerId = parts[0].trim();
                    String customerName = parts[1].trim();
                    String customerIc = parts[2].trim();

                    // please use the findCustomer method here, dont rewrite the same code
                    if (customerName.equalsIgnoreCase(name) && customerIc.equals(ic)) {
                        // Customer found, return the details
                        String phone = parts[3].trim();
                        int points = Integer.parseInt(parts[4].trim());
                        System.out.println("Customer details:");
                        return new Customer(customerId, customerName, customerIc, phone, points);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Customer not found. Please try again.");
        }
    }
        

}
