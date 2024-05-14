package redeemitem; // please put it under customer module

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class CustomerLogin {
    // please rethink the approach for this method, there is an easier way to do this
    public static Customer findCustomer(String name, String ic) {
        for (Customer customer : Customer.getCustomers()) {
            if (customer.getName().equalsIgnoreCase(name) && customer.getIc().equals(ic)) {
                return customer; // Found the customer
            }
        }
        return null; 
    }

    public static Customer logInCustomer() {
        boolean loggedIn = false;
        Customer customer = null;
        String customerName = null;
        String customerIc = null;
        String phone = null;
        String loyaltyStatus = null;
        String userId = null;
        int points = 0;

        while (!loggedIn) {
            String name = CustomerRegistration.getInput("Enter user name : ", "^[a-zA-Z]+$", "Invalid name format. Please enter alphabets only.");

            String ic = CustomerRegistration.getInput("Enter user ic : ", "^\\d{12}$", "Invalid IC format. Please enter 12 digits.");

            try (Scanner fileScanner = new Scanner(new File("C:\\Users\\ladym\\Documents\\GitHub\\java100marks\\src\\data\\customers.txt"))) {
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        String label = parts[0].trim();
                        String value = parts[1].trim();

                        switch (label) {
                            case "User ID":
                                userId = value;
                                break;
                            case "User Name":
                                customerName = value;
                                break;
                            case "IC":
                                customerIc = value;
                                break;
                            case "Phone Number":
                                phone = value;
                                break;
                            case "Loyalty Status":
                                loyaltyStatus = value;
                                break;
                            case "Total Points Earned":
                                points = Integer.parseInt(value);

                                if (customerName != null && customerIc != null && phone != null) {
                                    if (customerName.equalsIgnoreCase(name) && customerIc.equals(ic)) {
                                        System.out.println("Logged in successfully!");
                                        System.out.println("Customer details:");
                                        System.out.println("User ID: " + userId);
                                        System.out.println("Name: " + customerName);
                                        System.out.println("IC: " + customerIc);
                                        System.out.println("Phone: " + phone);
                                        System.out.println("Loyalty Status: " + loyaltyStatus);
                                        System.out.println("Points: " + points);
                                        customer = new Customer(userId, customerName, customerIc, phone, points);
                                        loggedIn = true; // Set the flag to true
                                    }
                                }
                                break;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            if (!loggedIn) {
                System.out.println("Customer not found. Please try again.");
            }
        }

        return customer; // Return the logged-in customer
    }



}

