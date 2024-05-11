
package redeemitem;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;

// as per discussion, please refer to updated UML diagram or group leader for new transaction policy
public class Payment {

    //DISPLAY CHART FOR USER
    public static void displayCartContents(ShoppingCart cart) {
        // Display cart contents
        System.out.println("Your cart:");
        for (Product product : cart.getSelectedProducts()) {
            System.out.println("\tRM" + String.format("%.2f", product.getPrice()) + "\tPoints Earned: " + product.getPointsEarned());
        }
        double totalPrice = cart.calculateTotalPrice();
        int totalPointsEarned = cart.calculateTotalPointsEarned();
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Total price: RM" + df.format(totalPrice));
        System.out.println("Total points earned: " + totalPointsEarned);
    }

    // Ask user to make PAYMENT
    public static boolean makePayment(Scanner scanner) {
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
}
