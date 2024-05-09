package redeemitem;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("================================");
            System.out.println("Welcome to Loyalty Program Menu");
            System.out.println("================================");
            System.out.println("1. Register");
            System.out.println("2. Earned Point");
            System.out.println("3. Redeem Points");
            System.out.println("4. Check Loyalty");
            System.out.println("5. Report");
            System.out.println("6. Exit");
            System.out.println("================================");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    earnedPoint();
                    break;
                case 3:
                    redeemPoints();
                    break;
                case 4:
                    checkLoyalty();
                    break;
                case 5:
                    report();
                    break;
                case 6:
                    System.out.println("Exiting Loyalty Program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        } while (choice != 6);

        scanner.close();
    }

    public static void register() {
        System.out.println("You have selected Register.");
        // Add your registration logic here
    }

    public static void earnedPoint() {
        System.out.println("You have selected Earned Point.");

        // Assuming the information class is available with the getUserId() method
        int userId = information.getUserId(Main.scanner);

        // Show available products
        List<Product> availableProducts = Product.getAvailableProducts();

        // Select products and display cart contents
        ShoppingCart cart = new ShoppingCart();
        List<Product> selectedProducts = Product.selectProducts(scanner);
        for (Product product : selectedProducts) {
            cart.addToCart(product);
        }
        Payment.displayCartContents(cart);

        // Make payment
        if (Payment.makePayment(scanner)) {
            int totalPointsEarned = cart.calculateTotalPointsEarned();
            LocalDate earningDate = LocalDate.now(); // Get current date

            //SAVE POINTS
            SavePoints.savePointsEarned(userId, earningDate, totalPointsEarned);

            // RETRIVE POINTS
            int totalEarnedPoints = RetrivePoints.getTotalPointsEarned(userId);
            System.out.println("Total points earned by user: " + totalEarnedPoints);

            //UPDATE POINTS
            EarnedPoints.updatePointsEarned(userId, totalPointsEarned, totalEarnedPoints);

        }
    }

    public static void redeemPoints() {
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
                                int userId = 147;
                                RM.redeemItems(itemIndex, quantityNeeded, userId); // Redeem the item
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

        // Add redeem points logic here
    }

    public static void checkLoyalty() {
        System.out.println("You have selected Check Loyalty.");
        int userId = information.getUserId(Main.scanner);
        
        // Create an instance of LoyaltyStatus
        LoyaltyStatus loyaltyStatus = new LoyaltyStatus();
        loyaltyStatus.readLoyaltyStatusFromFile(userId);

 
    }
    public static void report(){
        
    }
}
