package redeemitem;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RedeemFileUtils {

    private static ArrayList<String> redeemableItems = new ArrayList<>();
    private static ArrayList<Integer> itemCosts = new ArrayList<>();
    private static ArrayList<Integer> quantity = new ArrayList<>();
    private static ArrayList<Integer> itemIndices = new ArrayList<>(); // Added for tracking item indices
    private static int lastAddedItemIndex = 1;

    private static final String itemList ="/Users/avo/Documents/GitHub/java100marks/src/data/redeemableItems.txt";

    public static void addItems(Scanner scanner) {
        int index = lastAddedItemIndex; // Start from the last added item's index
        System.out.println("Please enter the items you want to add. Enter item name, point cost, and quantity. Enter 0 for the item name to stop.");
        while (true) {
            System.out.println("Item " + index);
            System.out.print("Item Name: ");
            String itemNameInput = scanner.nextLine();
            if (itemNameInput.equals("0")) {
                break;
            }

            if (itemNameInput.trim().isEmpty()) {
                System.out.println("Error: Item name cannot be empty.");
                continue;
            }

            System.out.print("Item Cost: ");
            String itemCostInput = scanner.nextLine();
            if (itemCostInput.equals("0")) {
                System.out.println("Item cost cannot be 0.");
                continue;
            }

            try {
                int itemCost = Integer.parseInt(itemCostInput);
                System.out.print("Item Quantity: ");
                String itemQuantityInput = scanner.nextLine();
                if (itemQuantityInput.equals("0")) {
                    System.out.println("Item quantity cannot be 0.");
                    continue;
                }

                int itemQuantity = Integer.parseInt(itemQuantityInput);

                // Add the item to the lists
                redeemableItems.add(itemNameInput.trim());
                itemCosts.add(itemCost);
                quantity.add(itemQuantity);
                itemIndices.add(index); // Add the index to the list

                // Update the last added item's index
                lastAddedItemIndex = index + 1; // Increment the index for the next item

                index++;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a numerical value for the item cost or quantity.");
                continue;
            }
        }
    }

    public static void saveItems() {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(itemList))) {
            for (int i = 0; i < redeemableItems.size(); i++) {
                writer.println(itemIndices.get(i) + "|" + redeemableItems.get(i) + "|" + itemCosts.get(i) + "|" + quantity.get(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void loadItems() {
        try (Scanner fileScanner = new Scanner(new FileInputStream(itemList))) {
            while (fileScanner.hasNextLine()) {
                String[] itemParts = fileScanner.nextLine().split("\\|");
                if (itemParts.length == 4) {
                    itemIndices.add(Integer.parseInt(itemParts[0].trim()));
                    redeemableItems.add(itemParts[1].trim());
                    itemCosts.add(Integer.parseInt(itemParts[2].trim()));
                    quantity.add(Integer.parseInt(itemParts[3].trim()));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void clearItems() {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(itemList))) {
            writer.print("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        lastAddedItemIndex = 1;
    }

    public static void deleteItem(Scanner scanner) {
        System.out.println("Enter the index of the item you want to delete:");
        int indexToDelete = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left-over
        int actualIndex = indexToDelete - 1; // Adjust for 0-based indexing

        if (actualIndex >= 0 && actualIndex < itemIndices.size()) {
            // Remove the item from all lists
            itemIndices.remove(actualIndex);
            redeemableItems.remove(actualIndex);
            itemCosts.remove(actualIndex);
            quantity.remove(actualIndex);

            // Shift the indices of the items that come after the deleted item down by one
            for (int i = 0; i < itemIndices.size(); i++) {
                if (itemIndices.get(i) > indexToDelete) {
                    itemIndices.set(i, itemIndices.get(i) - 1);
                }
            }

            // Update the last added item's index if necessary
            if (lastAddedItemIndex > actualIndex) {
                lastAddedItemIndex--;
            }

            System.out.println("Item deleted successfully.");
            saveItems();
        } else {
            System.out.println("Invalid index. Please try again.");
        }
    }

    public static void reloadItems() {
        redeemableItems.clear();
        itemCosts.clear();
        quantity.clear();
        itemIndices.clear();
        loadItems();
    }

    public static int getPointCost(String itemName) {
        for (int i = 0; i < redeemableItems.size(); i++) {
            if (redeemableItems.get(i).equals(itemName)) {
                return itemCosts.get(i);
            }
        }
        return 0;
    }

    public static ArrayList<String> getRedeemableItems() {
        return redeemableItems;
    }

    public static int getItemQuantity(int index) {
        if (index >= 0 && index < quantity.size()) {
            return quantity.get(index);
        } else {
            return -1;
        }
    }

    public static void setItemQuantity(int index, int newQuantity) {
        if (index >= 0 && index < quantity.size()) {
            quantity.set(index, newQuantity);
            saveItems();
            System.out.println("Item quantity updated successfully.");
        } else {
            System.out.println("Invalid index for updating item quantity.");
        }
    }

    public static void displayItems() {
        System.out.println("\n");
        System.out.println("----------------------");
        System.out.println("|  Redeemable Items  |");
        System.out.println("----------------------");
        System.out.printf("%-20s %-25s %-15s %-10s%n", "Index", "Item Name", "Point Cost", "Quantity");
        System.out.printf("%-20s %-25s %-15s %-10s%n", "----------------------------------------------------------------------", " ", " ", " ");

        for (int i = 0; i < redeemableItems.size(); i++) {
            System.out.printf("%-20s %-25s %-15s %-10s%n", itemIndices.get(i), redeemableItems.get(i), itemCosts.get(i), quantity.get(i));
        }
    }

    public static int getItemIndexByName(String itemName) {
         for (int i = 0; i < redeemableItems.size(); i++) {
            if (redeemableItems.get(i).equals(itemName)) {
                return itemIndices.get(i);
            }
        }
        return -1;
    }

    public static String getItemNameByIndex(int index) {
        if (index > 0 && index <= redeemableItems.size()) {
            return redeemableItems.get(index - 1);
        }
        return null;
    }
}
