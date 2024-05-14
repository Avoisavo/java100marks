package redeemitem; // please put it under customer module

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Linghue Wee
 */
public class CustomerUpdate {
    
    public static void updateName(Customer loggedInCustomer) {
        String newName = CustomerRegistration.getInput("Enter user name : ", "^[a-zA-Z]+$", "Invalid name format. Please enter alphabets only.");
        loggedInCustomer.setName(newName);
        System.out.println("Name updated successfully!");
        updateFile(loggedInCustomer);
    }
    
    public static void updatePhone(Customer loggedInCustomer) {
        String newPhone = CustomerRegistration.getInput("Enter user phone : ", "^[0-9]{10}$", "Invalid phone format. Please enter 10 digits.");
        loggedInCustomer.setPhone(newPhone);
        System.out.println("Phone number updated successfully!");
        updateFile(loggedInCustomer);
    }
     
    private static void updateFile(Customer updatedCustomer) {
        File originalFile = new File("src/redeemitem/customers.txt");
        File tempFile = new File("src/data/temp_customers.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(originalFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            // Read all the lines from the file
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("User ID: " + updatedCustomer.getId())) {
                    // Update the line with the new customer name
                    writer.write("User ID: " + updatedCustomer.getId());
                    writer.newLine();
                    writer.write("User Name: " + updatedCustomer.getName());
                    writer.newLine();
                    reader.readLine(); // Skip the old name line

                    writer.write(reader.readLine()); // IC line
                    writer.newLine();

                    writer.write("Phone Number: " + updatedCustomer.getPhone());
                    writer.newLine();
                    reader.readLine(); // Skip the old phone number line

                    String loyaltyStatusLine = reader.readLine();
                    writer.write(loyaltyStatusLine); // Loyalty Status line
                    writer.newLine();

                    writer.write(reader.readLine()); // Total Points Earned line
                    writer.newLine();
                } else {
                    // Add the unchanged line to the temp file
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Replace the original file with the temp file
        if (originalFile.delete()) {
            if (tempFile.renameTo(originalFile)) {
                System.out.println("File updated successfully.");
            } else {
                System.out.println("Failed to rename temp file.");
            }
        } else {
            System.out.println("Failed to delete original file.");
        }
    }


}

