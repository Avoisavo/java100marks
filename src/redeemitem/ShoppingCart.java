package redeemitem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.DecimalFormat;

public class ShoppingCart {

    public List<Product> selectedProducts;

    public ShoppingCart() {
        this.selectedProducts = new ArrayList<>();
    }

    public void displayAvailableProducts() {
        List<Product> products = Product.getAvailableProducts();
        System.out.println("Available Items:");
        System.out.println("-------------------------------------------------");
        System.out.println("S.No\tItem\t\tPrice\tPoints Earned");
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println((i + 1) + "\t" + "\tRM" + product.getPrice() + "\t" + product.getPointsEarned());
        }
        System.out.println("-------------------------------------------------");
    }

    public void displayCartContents() {
        System.out.println("Your cart:");
        for (Product product : selectedProducts) {
            System.out.println("\tRM" + String.format("%.2f", product.getPrice()) + "\tPoints Earned: " + product.getPointsEarned());
        }
        double totalPrice = calculateTotalPrice();
        int totalPointsEarned = calculateTotalPointsEarned();
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Total price: RM" + df.format(totalPrice));
        System.out.println("Total points earned: " + totalPointsEarned);
    }

    public boolean makePayment(Scanner scanner) {
        System.out.print("Do you want to make payment? (yes/no): ");
        String makePayment = scanner.next();

        if (makePayment.equalsIgnoreCase("yes")) {
            System.out.println("Payment successful.");
            return true;
        } else {
            System.out.println("Payment unsuccessful.");
            return false;
        }
    }

public List<Product> selectProducts(Scanner scanner) {
    boolean continueAdding = true;

    while (continueAdding) {
        displayAvailableProducts();
        System.out.print("Enter the number of the item you want to purchase or 0 to finish: ");
        if (scanner.hasNextInt()) {
            int selectedItem = scanner.nextInt();
            if (selectedItem >= 1 && selectedItem <= 3) {
                selectedProducts.add(Product.getAvailableProducts().get(selectedItem - 1));
                System.out.println("Item added to cart.");
            } else if (selectedItem == 0) {
                continueAdding = false;
            } else {
                System.out.println("Invalid selection. Please enter a number between 1 and 3.");
            }
        } else {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Consume the invalid input
        }
    }
    return selectedProducts;
}


    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Product product : selectedProducts) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    public int calculateTotalPointsEarned() {
        int totalPointsEarned = 0;
        for (Product product : selectedProducts) {
            totalPointsEarned += product.getPointsEarned();
        }
        return totalPointsEarned;
    }
}


