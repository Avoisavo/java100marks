/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package customer;

import java.util.Scanner;
import java.io.*;

public class Customer {

    private int id;
    private String name;
    private String ic;
    private String phone;
    private int points;
    private static int customerCount = 0;

    public Customer(int id, String name, String ic, String phone, int points) {
        this.id = id;
        this.name = name;
        this.ic = ic;
        this.phone = phone;
        this.points = points;
        customerCount++;
    }

    public int getId() {
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

    public static int getCustomerCount() {
        return customerCount;
    }

    public static void main(String[] args) {
        Customer[] customers = loadCustomersFromFile(); // Load customers from fi
        Scanner scanner = new Scanner(System.in);

        boolean validOption = false;

        System.out.println(" WELCOME TO LUXCLOTH! ");

        do {
            System.out.println(" MENU : ");
            System.out.println(" 1. REGISTER ");
            System.out.println(" 2. LOGIN ");
            System.out.println(" 3. UPDATE INFO ");
            System.out.print(" Enter Your Option (1-3): ");

            if (scanner.hasNextInt()) {
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        registerCustomer(scanner, customers);
                        validOption = true;
                        break;
                    case 2:
                        // loginCustomer(scanner, customers);
                        validOption = true;
                        break;
                    case 3:
                        // updateCustomerInfo(scanner, customers);
                        validOption = true;
                        break;
                    default:
                        System.out.println(" Invalid option ! PLEASE ENTER 1 or 2 or 3 only ! ");
                }
            } else {
                System.out.println("Invalid input, enter digit only!");
                scanner.next();
            }
        } while (!validOption);

        scanner.close();
    }

    private static void registerCustomer(Scanner scanner, Customer[] customers) {
        // Generate ID with leading zeros based on the number of customers already in the array
        String id = String.format("%03d", customers.length + 1);

        // Get customer details from user input
        System.out.print("Enter customer name: ");
        String name = scanner.next();

        System.out.print("Enter customer IC: ");
        String ic = scanner.next();

        System.out.print("Enter customer phone: ");
        String phone = scanner.next();

        // Set points to 0 for new customers
        int points = 0;

        // Convert id String to int
        int customerId = Integer.parseInt(id);

        // Create a new customer object with the entered details
        Customer newCustomer = new Customer(customerId, name, ic, phone, points);

        // Add the new customer to the array
        Customer[] updatedCustomers = new Customer[customers.length + 1];
        for (int i = 0; i < customers.length; i++) {
            updatedCustomers[i] = customers[i];
        }
        updatedCustomers[customers.length] = newCustomer;

        // Write updated customer information to file
        writeCustomersToFile(updatedCustomers);

        System.out.println("Registration successful!");
    }
    
    public static void validateForLogin (Scanner scanner , String[] customerInfo){
         // Validating Name
        boolean validName = false;
        do {
            System.out.print("Enter customer name: ");
            String name = scanner.next();
            if (name.matches("[a-zA-Z]+")) { // Check if name contains only alphabets
                customerInfo[1] = name;
                validName = true;
            } else {
                System.out.println("Invalid name! Please enter alphabets only.");
            }
        } while (!validName);
        
        // Validating IC
        boolean validIC = false;
        do {
            System.out.print("Enter customer IC (without '-'): ");
            String ic = scanner.next();
            if (ic.matches("\\d{12}")) { // Check if IC is exactly 12 digits
                customerInfo[2] = ic;
                validIC = true;
            } else {
                System.out.println("Invalid IC! Please enter exactly 12 digits.");
            }
        } while (!validIC);
    }
    
    public static void validateForReg(Scanner scanner , String[] customerInfo){
        validateForLogin(scanner , customerInfo);
        
        // Validating Phone
        boolean validPhone = false;
        do {
            System.out.print("Enter customer phone : ");
            String phone = scanner.next();
            if (phone.matches("\\d+")) { // Check if phone contains only digits
                customerInfo[3] = phone;
                validPhone = true;
            } else {
                System.out.println("Invalid phone number! Please enter digits only.");
            }
        } while (!validPhone);

        // Validating Email
        boolean validEmail = false;
        do {
            System.out.print("Enter customer email (must be @gmail.com): ");
            String email = scanner.next();
            if (email.endsWith("@gmail.com")) { // Check if email ends with @gmail.com
                customerInfo[4] = email;
                validEmail = true;
            } else {
                System.out.println("Invalid email format! Please enter correct email.");
            }
        } while (!validEmail);
    }
    
    private static Customer[] loadCustomersFromFile() {
        Customer[] customers = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("cust.txt"))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                count++;
            }
            customers = new Customer[count];
            reader.close();

            BufferedReader reader2 = new BufferedReader(new FileReader("cust.txt"));
            int index = 0;
            while ((line = reader2.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String ic = data[2];
                String phone = data[3];
                int points = Integer.parseInt(data[4]);
                customers[index] = new Customer(id, name, ic, phone, points);
                index++;
            }
            reader2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }

    private static void writeCustomersToFile(Customer[] customers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("cust.txt"))) {
            for (Customer customer : customers) {
                writer.write(customer.getId() + "," + customer.getName() + "," + customer.getIc() + "," + customer.getPhone() + "," + customer.getPoints() + "\n");
            }
            // Explicitly flush and close the writer
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



    

   /* private static void showCustInfo(Scanner scanner) {
        
        boolean custFound = false;
        
        do {
            
            validateForLogin(scanner , customerInfo);
            

            try {
                BufferedReader reader = new BufferedReader(new FileReader("cust.txt"));
                String line;
                boolean found = false;
                while ((line = reader.readLine()) != null) {
                    String[] customerInfo = line.split(","); // split into newline when ','
                     if (customerInfo.length >= 5 && customerInfo[1].equals(name) && customerInfo[2].equals(ic)) {
                        found = true;
                        System.out.println("Customer Information:");
                        System.out.println("Customer ID: " + customerInfo[0]);
                        System.out.println("Customer Name: " + customerInfo[1]);
                        System.out.println("Customer IC: " + customerInfo[2]);
                        System.out.println("Customer Phone: " + customerInfo[3]);
                        System.out.println("Customer Email: " + customerInfo[4]);
                        System.out.println();
                        break; // No need to continue searching if found
                    }
                }
                reader.close();
                if (!found) {
                    System.out.println("Customer not found.Please Re-enter again!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        
        }while (!custFound);
        
    }
    
    
    
    public static void validateForLogin (Scanner scanner , String[] customerInfo){
         // Validating Name
        boolean validName = false;
        do {
            System.out.print("Enter customer name: ");
            String name = scanner.next();
            if (name.matches("[a-zA-Z]+")) { // Check if name contains only alphabets
                customerInfo[1] = name;
                validName = true;
            } else {
                System.out.println("Invalid name! Please enter alphabets only.");
            }
        } while (!validName);
        
        // Validating IC
        boolean validIC = false;
        do {
            System.out.print("Enter customer IC (without '-'): ");
            String ic = scanner.next();
            if (ic.matches("\\d{12}")) { // Check if IC is exactly 12 digits
                customerInfo[2] = ic;
                validIC = true;
            } else {
                System.out.println("Invalid IC! Please enter exactly 12 digits.");
            }
        } while (!validIC);
    }
    
    
    
    
    
    public static void validateForReg(Scanner scanner , String[] customerInfo){
        validateForLogin(scanner , customerInfo);
        
        // Validating Phone
        boolean validPhone = false;
        do {
            System.out.print("Enter customer phone : ");
            String phone = scanner.next();
            if (phone.matches("\\d+")) { // Check if phone contains only digits
                customerInfo[3] = phone;
                validPhone = true;
            } else {
                System.out.println("Invalid phone number! Please enter digits only.");
            }
        } while (!validPhone);

        // Validating Email
        boolean validEmail = false;
        do {
            System.out.print("Enter customer email (must be @gmail.com): ");
            String email = scanner.next();
            if (email.endsWith("@gmail.com")) { // Check if email ends with @gmail.com
                customerInfo[4] = email;
                validEmail = true;
            } else {
                System.out.println("Invalid email format! Please enter correct email.");
            }
        } while (!validEmail);
    }
    
    private static void loadCustCount() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("cust.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                // Splitting the line to extract the ID
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].trim().length() > 0) {
                    int id = Integer.parseInt(parts[0].trim()); // Trim whitespace
                    customerCount = Math.max(customerCount, id);
                }
            }
            
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // Handle cases where parsing fails
            e.printStackTrace();
        }
    }

    private static void updateCustInfo(Scanner scanner) {
        // Show existing customer information
        showCustInfo(scanner);
        
        boolean validUpdateOption = false;

        do {
            System.out.println("UPDATE OPTIONS: ");
            System.out.println("1. Update Phone ");
            System.out.println("2. Update Email Address ");
            System.out.println("3. Cancel ");
            System.out.println("Please select your options (1-3 only)");

            if (scanner.hasNextInt()) {
                int updateCustOption = scanner.nextInt();

                switch (updateCustOption) {
                    case 1:
                        // Update phone number
                        System.out.print("Enter new phone number: ");
                        String newPhone = scanner.next();

                        // Update phone in customerInfo array
                        customerInfo[3] = newPhone;
                        System.out.println("Phone number updated successfully.");
                        validUpdateOption = true;
                        break;

                    case 2:
                        // Update email address
                        System.out.print("Enter new email address: ");
                        String newEmail = scanner.next();

                        // Update email in customerInfo array
                        customerInfo[4] = newEmail;
                        System.out.println("Email address updated successfully.");
                        validUpdateOption = true;
                        break;

                    case 3:
                        // Cancel update
                        System.out.println("Update cancelled.");
                        return; // Exit the method

                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                        validUpdateOption = false;
                        break;
                }
            } else {
                System.out.println("Invalid input, enter digit only!");
                scanner.next();
            }
        } while (!validUpdateOption);

        // Now, you need to write the updated customer information back to the file.
        // You can follow a similar approach as in the Registration method to write the updated information.
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("cust.txt"));
            for (String info : customerInfo) {
                writer.write(info + ",");
            }
            writer.newLine(); // Add a new line for the next customer
            writer.close();
            System.out.println("Customer information updated successfully in the file.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to update customer information in the file.");
        }
    }*/


