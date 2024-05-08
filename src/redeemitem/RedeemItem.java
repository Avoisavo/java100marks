package redeemitem;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class RedeemItem {

    public static int userId = 147;

    public static void main(String[] args) {
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

            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option < 1 || option > 6) {
                System.out.println("That's an invalid option, please choose a valid option.");
                continue;
            }

            switch (option) {
                case 1:
                    RedeemFileUtils.addItems();
                    RedeemFileUtils.saveItems();
                    break;
                case 2:
                    RedeemFileUtils.clearItems();
                    System.out.println("All data has been cleared.");
                    RedeemFileUtils.reloadItems();
                    break;
                case 3:
                    RedeemFileUtils.displayItems();
                    RedeemFileUtils.deleteItem();
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

            }
        }
    }

}
