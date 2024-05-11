/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customer; // please put it under customer module

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Linghue Wee
 */
public class CustomerUpdate {
    public static void updateName(Customer loggedInCustomer) {
        System.out.print("Enter new name: ");
        String newName = CustomerRegistration.getInput("Enter new name: ", "^[a-zA-Z]+( [a-zA-Z]+)*$");
        loggedInCustomer.setName(newName);
        System.out.println("Name updated successfully!");
        updateFile(loggedInCustomer);
    }
    
    public static void updatePhone(Customer loggedInCustomer) {
        System.out.print("Enter new phone number: ");
        String newPhone = CustomerRegistration.getInput("Enter user phone : ","^[0-9]{10}$");
        loggedInCustomer.setPhone(newPhone);
        System.out.println("Phone number updated successfully!");
        updateFile(loggedInCustomer);
    }
     
  // please format your code!!!!!!!!!!!! >:(
            private static void updateFile(Customer updatedCustomer) {
    // please use temporary variable instead of temporary file
        // Create a temporary file to hold the updated data
        String tempFileName = "temp_customers.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader("customers.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0];

                // Check if the current line corresponds to the updated customer
                if (id.equals(updatedCustomer.getId())) {
                    // Write the updated customer details to the temporary file
                    writer.write(updatedCustomer.getId() + "," + updatedCustomer.getName() + "," +
                            updatedCustomer.getIc() + "," + updatedCustomer.getPhone() + "," + updatedCustomer.getPoints());
                } else {
                    // Write the unchanged customer details to the temporary file
                    writer.write(line);
                }
                writer.newLine(); // Add a new line after writing each customer's details
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace the original file with the temporary file
        File originalFile = new File("customers.txt");
        File tempFile = new File(tempFileName);
        if (tempFile.exists()) {
            if (originalFile.delete()) {
                if (tempFile.renameTo(originalFile)) {
                    System.out.println("File updated successfully.");
                } else {
                    System.out.println("Failed to rename temporary file.");
                }
            } else {
                System.out.println("Failed to delete original file.");
            }
        } else {
            System.out.println("Temporary file does not exist.");
        }
    }


}

    
    


