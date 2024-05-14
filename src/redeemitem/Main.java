package redeemitem;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    private static Customer loggedInCustomer;
    private static int loggedInUserId;

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("================================");
            System.out.println("Welcome to Loyalty Program Menu");
            System.out.println("================================");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Update Info");
            System.out.println("4. Earned Point");
            System.out.println("5. Redeem Points");
            System.out.println("6. Check Loyalty");
            System.out.println("7. Report");
            System.out.println("8. Exit");
            System.out.println("================================");
            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a digit number:");
                scanner.next(); // Consume the invalid input
            }
            choice = scanner.nextInt();

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
                    earnedPoint();
                    break;
                case 5:
                    redeemPoints();
                    break;
                case 6:
                    checkLoyalty();
                    break;
                case 7:
                    report();
                    break;
                case 8:
                    System.out.println("Exiting Loyalty Program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        } while (choice != 8);
        scanner.close();
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
        // Create a new customer
        Customer customer = CustomerRegistration.createCustomer();

        // Print "Customer created"
        System.out.println("Customer created");

        // Register the customer
        CustomerRegistration.registerCustomer(customer);
    }

    public static void logIn() {
        Customer loggedInCustomer = CustomerLogin.logInCustomer();
        if (loggedInCustomer != null) {
            System.out.println("Logged in successfully!");
            // Set the logged-in customer
            Main.loggedInCustomer = loggedInCustomer;
            // Set the logged-in user ID
            loggedInUserId = Integer.parseInt(loggedInCustomer.getId());
            System.out.println("Customer details:");
            System.out.println("Name: " + loggedInCustomer.getName());
            System.out.println("IC: " + loggedInCustomer.getIc());
            System.out.println("Phone: " + loggedInCustomer.getPhone());
            System.out.println("Points: " + loggedInCustomer.getPoints());
            System.out.println("ID: " + loggedInUserId);
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

    public static void earnedPoint() {
        if (loggedInCustomer == null) {
            System.out.println("Please log in first.");
            return;
        }
        System.out.println("You have selected Earned Point.");
        System.out.println("your ID: " + loggedInUserId);

        int loggedInCustomerPoints = EarnedPoints.fetchCustomerPoints(loggedInUserId);

        // Creating a shopping cart and selecting products
        ShoppingCart cart = new ShoppingCart();
        cart.selectProducts(scanner);

        // Displaying cart contents
        cart.displayCartContents();

        // Make payment
        if (cart.makePayment(scanner)) {
            int totalPointsEarned = cart.calculateTotalPointsEarned();
            LocalDate earningDate = LocalDate.now(); // Current date

           // Log earned points and date
           System.out.println("Earned " + totalPointsEarned + " points on " + earningDate);

           // Update customer's points in the system
           EarnedPoints.updateCustomerPoints(loggedInUserId, totalPointsEarned);

           // Fetch current points
           int currentPoints = EarnedPoints.fetchCustomerPoints(loggedInUserId);
           System.out.println("Current points for user " + loggedInUserId + ": " + currentPoints);


           // Fetch updated points
           int updatedPoints = EarnedPoints.fetchCustomerPoints(loggedInUserId);
           System.out.println("Updated points for user " + loggedInUserId + ": " + updatedPoints);
        }
    }

    public static void redeemPoints() {
        if (loggedInCustomer == null) {
            System.out.println("Please log in first.");
            return;
        }
        RedemptionManager RM = new RedemptionManager();
        RedeemFileUtils.loadItems();

        boolean continueLoop = true;
        while (continueLoop) {
            System.out.println("Luxcloth Redemption Center");
            System.out.println("----------------------");
            System.out.println("1. Add Items - You will be prompted to enter the item name, cost, and quantity.");
            System.out.println("2. Clear All Data - Selecting this option will clear all existing data.");
            System.out.println("3. Delete Item by Index - Use this option to delete a specific item from the list by its index.");
            System.out.println("4. Check Redeemable Items - This option displays the list of all redeemable items.");
            System.out.println("5. Redeem Item - Select this option to redeem a specific item from the list.");
            System.out.println("6. Exit - Choose this option to exit the program and close the application.");
            System.out.println("----------------------");
            System.out.println("Please choose your option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            if (option < 1 || option > 6) {
                System.out.println("That's an invalid option, please choose a valid option.");
                continue;
            }

            switch (option) {
                case 1:
                    RedeemFileUtils.addItems(scanner);
                    RedeemFileUtils.saveItems();
                    break;
                case 2:
                    RedeemFileUtils.clearItems();
                    System.out.println("All data has been cleared.");
                    RedeemFileUtils.reloadItems();
                    break;
                case 3:
                    RedeemFileUtils.displayItems();
                    RedeemFileUtils.deleteItem(scanner);
                    break;
                case 4:
                    RedeemFileUtils.displayItems();
                    System.out.println(loggedInUserId);
                    break;
                case 5:
                    RedeemFileUtils.displayItems();
                    System.out.println("Enter the name of the item you want to redeem (enter 0 to exit):");
                    String itemName = scanner.nextLine();

                    if (itemName.equals("0")) {
                        break;
                    }

                    int itemIndex = RedeemFileUtils.getItemIndexByName(itemName); // Find the index by name
                    while (itemIndex == -1) { // Loop until a valid item name is entered
                        System.out.println("Item not found. Please enter a valid item name (or enter 0 to exit):");
                        itemName = scanner.nextLine();

                        if (itemName.equals("0")) {
                            break; // Exit case 5
                        }

                        itemIndex = RedeemFileUtils.getItemIndexByName(itemName);
                    }

                    if (itemIndex != -1) {
                        int itemQuantity = RedeemFileUtils.getItemQuantity(itemIndex - 1);
                        if (itemQuantity == 0) {
                            System.out.println("Sorry, this item is currently out of stock and cannot be redeemed.\n");
                            break;
                        }

                        System.out.println("Enter the quantity of the item you want to redeem (or enter 0 to exit):");
                        int quantityNeeded = Integer.parseInt(scanner.nextLine());

                        try {
                            if (quantityNeeded == 0) {
                                break;
                            } else if (quantityNeeded < 0) {
                                System.out.println("Invalid quantity. Please enter a positive number (or enter 0 to exit):");
                            } else if (quantityNeeded > itemQuantity) {
                                System.out.println("Sorry, the quantity needed exceeds the available quantity of the item.\n");
                                break;
                            } else {
                                RM.redeemItems(itemIndex, quantityNeeded, loggedInUserId); // Redeem the item
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid quantity (or enter 0 to exit):");
                        }

                    }
                    break;
                case 6:
                    continueLoop = false; // Set the loop condition to false to exit the loop
                    break;
            }
        }
    }

    public static void checkLoyalty() {
        System.out.println("You have selected Check Loyalty.");

    }

    public static void report() {
        int trans_ans;
        int genereport_ans;
        int repeatgene_ans;
        int askadmin_ans;

        boolean contGeneReport = true;
        boolean repeatReport = true;
        boolean contTrans = true;
        boolean contaskadmin = true;
        
     
        Report goldReport = new GoldStatusReport();
        Report silverReport = new SilverStatusReport();
        Report classicReport = new ClassicStatusReport();
        Report redemptionReport = new RedemptionSummary();
      

        do{
            try{
                    //Provide user input and display the output of report menu.
                    System.out.print("Please enter your admin password: ");
                    genereport_ans = scanner.nextInt();
                    if (genereport_ans == 1234){
                        contaskadmin = false;
                    }else{
                        System.out.println("Oops is wrong password. Please try again.\n");
                    }
            }
            catch(Exception ex){
                    System.out.println("Oops is wrong password. Please try again.\n");
                    scanner.nextLine();
                    }
            
            }while(contaskadmin);
        
            //Create a loop     
            do{
                try{
                    //Provide user input and display the output of report menu.
                    System.out.print("\n-------------------------------------------------------\nWhich report do you want to look?\n-------------------------------------------------------\n(1) Overall Classic Status Report  \n(2) Overall Silver Status Report \n(3) Overall Gold Status Report  \n(4) Redemption Item Summary Report \n(0) Cancel\nPlease enter in the range[0 to 4]: ");
                    genereport_ans = scanner.nextInt();

                    switch (genereport_ans) {
                        case 1:
                            // Generate member customer report
                            classicReport.fetchCustomerData();
                            classicReport.generateReport();
                            //Create a loop
                            do {
                                try{
                                    //Provide user input and display the output of repeat report options.
                                    System.out.print("Do you want to look at report again?[Yes=(1)/No=(0)]: ");
                                    repeatgene_ans = scanner.nextInt();
                                    //When user want to look at report again
                                    //Display report menu again.
                                    if(repeatgene_ans == 1){
                                        //stop the loop
                                        repeatReport = false;
                                    }
                                    //When user does not want to look at report again
                                    else if(repeatgene_ans == 0){
                                        //stop all the loop
                                        repeatReport = false;
                                        contGeneReport = false;
                                    }
                                    else{
                                        //When user enter out of the range of 1 to 0, display the error message.
                                        System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                        scanner.nextLine();
                                        repeatReport = true;}
                                }
                                //When user is not enter number input, display the error message.
                                catch(Exception ex){
                                    System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                    scanner.nextLine();
                                    repeatReport = true;
                                }

                            }//Stop the loop when repeatReport is false.
                            while(repeatReport);
                            break;
                        case 2:
                            // Generate Silver customer report
                            silverReport.fetchCustomerData();
                            silverReport.generateReport();
                            do {
                                try{
                                    //Provide user input and display the output of repeat report options.
                                    System.out.print("Do you want to look at report again?[Yes=(1)/No=(0)]: ");
                                    repeatgene_ans = scanner.nextInt();
                                    //When user want to look at report again
                                    //Display report menu again.
                                    if(repeatgene_ans == 1){
                                        //stop the loop
                                        repeatReport = false;
                                    }
                                    //When user does not want to look at report again
                                    else if(repeatgene_ans == 0){
                                        //stop all the loop
                                        repeatReport = false;
                                        contGeneReport = false;
                                    }
                                    else{
                                        //When user enter out of the range of 1 to 0, display the error message.
                                        System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                        scanner.nextLine();
                                        repeatReport = true;}
                                }
                                //When user is not enter number input, display the error message.
                                catch(Exception ex){
                                    System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                    scanner.nextLine();
                                    repeatReport = true;
                                }

                            }//Stop the loop when repeatReport is false.
                            while(repeatReport);
                            break;
                        case 3:
                            // Generate Gold customer report
                            goldReport.fetchCustomerData();
                            goldReport.generateReport();
                            do {
                                try{
                                    //Provide user input and display the output of repeat report options.
                                    System.out.print("Do you want to look at report again?[Yes=(1)/No=(0)]: ");
                                    repeatgene_ans = scanner.nextInt();
                                    //When user want to look at report again
                                    //Display report menu again.
                                    if(repeatgene_ans == 1){
                                        //stop the loop
                                        repeatReport = false;
                                    }
                                    //When user does not want to look at report again
                                    else if(repeatgene_ans == 0){
                                        //stop all the loop
                                        repeatReport = false;
                                        contGeneReport = false;
                                    }
                                    else{
                                        //When user enter out of the range of 1 to 0, display the error message.
                                        System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                        scanner.nextLine();
                                        repeatReport = true;}
                                }
                                //When user is not enter number input, display the error message.
                                catch(Exception ex){
                                    System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                    scanner.nextLine();
                                    repeatReport = true;
                                }

                            }while(repeatReport);
                            break;
                        case 4:
                            // Adding points earned data for each year THIS IS ONLY EXAMPLE WHERE IT COLLECT THE DATA JUST MODIFY IT
                            redemptionReport.fetchRedemptionData();
                            redemptionReport.generateReport();
                            do {
                                try{
                                    //Provide user input and display the output of repeat report options.
                                    System.out.print("Do you want to look at report again?[Yes=(1)/No=(0)]: ");
                                    repeatgene_ans = scanner.nextInt();
                                    //When user want to look at report again
                                    //Display report menu again.
                                    if(repeatgene_ans == 1){
                                        //stop the loop
                                        repeatReport = false;
                                    }
                                    //When user does not want to look at report again
                                    else if(repeatgene_ans == 0){
                                        //stop all the loop
                                        repeatReport = false;
                                        contGeneReport = false;
                                    }
                                    else{
                                        //When user enter out of the range of 1 to 0, display the error message.
                                        System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                        scanner.nextLine();
                                        repeatReport = true;}
                                }
                                //When user is not enter number input, display the error message.
                                catch(Exception ex){
                                    System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                    scanner.nextLine();
                                    repeatReport = true;
                                }

                            }//Stop the loop when repeatReport is false.
                            while(repeatReport);
                            break;
                        case 0:
                            contGeneReport = false;
                            break;
                        default:
                            //Display error message when user enter out of range.
                            System.out.print("Something is wrong. Please enter [0-5] only.\n");
                            scanner.nextLine();
                            break;
                    }
                    }
                //when user enter wrong input that is not number.
                catch(Exception ex){
                     System.out.print("Oops is wrong. Please try again.\n");
                     scanner.nextLine();
                     }
               }//the loop will stop when contGeneReport is false
            while(contGeneReport);  
         
    }     

}
