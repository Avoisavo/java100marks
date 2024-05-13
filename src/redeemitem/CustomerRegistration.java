package redeemitem; // please put it under customer module

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.InputMismatchException;
import java.util.Random;
/**
 *
 * @author Linghue Wee
 */
       
public class CustomerRegistration {
    
    public static Customer createCustomer() {
    boolean isValidCustomer = false;
    Customer customer = null;

    while (!isValidCustomer) {
        String name = getInput("Enter user name : ", "^[a-zA-Z]+$", "Invalid name format. Please enter alphabets only.");
        String ic = getInput("Enter user ic : ", "^\\d{12}$", "Invalid IC format. Please enter 12 digits.");
        String phone = getInput("Enter user phone : ", "^[0-9]{10}$", "Invalid phone format. Please enter 10 digits.");

        // Validate customer details
        if (!Pattern.matches("^[a-zA-Z]+$", name)) {
            System.out.println("Invalid name format. Please enter alphabets only.");
            continue;
        }
        if (!Pattern.matches("^\\d{12}$", ic)) {
            System.out.println("Invalid IC format. Please enter 12 digits.");
            continue;
        }
        if (!Pattern.matches("^[0-9]{10}$", phone)) {
            System.out.println("Invalid phone format. Please enter 10 digits.");
            continue;
        }

        // If all details are valid, create the customer
        customer = new Customer(name, ic, phone);
        isValidCustomer = true;
    }
    return customer;
}
       
    public static String getInput(String message, String pattern, String errorMessage) {
        String input;
        boolean isValidInput = false;
        int attempts = 0; // Counter for invalid attempts

        do {
            System.out.print(message);
            input = Main.scanner.next();
            if (input.matches(pattern)) {
                isValidInput = true;
            } else {
                System.out.println(errorMessage);
                attempts++;
                if (attempts >= 3) {
                    System.out.println("You have exceeded the maximum number of attempts.");
                    System.out.print("Do you want to continue? (0 for no, 1 for yes): ");
                    int choice;
                    do {
                        try {
                            choice = Main.scanner.nextInt();
                            if (choice == 0) {
                                System.out.println("Exiting the program...");
                                System.exit(0); // Signal to exit
                            } else if (choice == 1) {
                                break; // Exit the loop to allow retrying input
                            } else {
                                System.out.println("Invalid choice. Please enter '0' or '1'.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter an integer (0 or 1).");
                            Main.scanner.nextLine(); // Consume invalid input
                        }
                    } while (true);
                    attempts = 0; // Reset the attempt counter
                }
            }
        } while (!isValidInput);


        return input;
    }

    public static void registerCustomer(Customer customer) {
        // Generate a random 3-digit customer ID
        int newCustomerId = generateRandomCustomerId();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/avo/Documents/GitHub/java100marks/src/data/customers.txt", true))) {
            // Write customer information to the file
            writer.write("User ID: " + newCustomerId + "\n");
            writer.write("User Name: " + customer.getName() + "\n");
            writer.write("IC: " + customer.getIc() + "\n");
            writer.write("Phone Number: " + customer.getPhone() + "\n");
            writer.write("Total Points Earned: " + customer.getPoints() + "\n\n");

            System.out.println("Customer information written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to write customer information to file.");
        }
    }

    private static int generateRandomCustomerId() {
        Random random = new Random();
        return random.nextInt(900) + 100; // Generates a random number between 100 and 999
    }
}
    
  
