/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redeemitem;

/**
 *
 * @author ladym
 */
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;

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
    public static boolean makePayment() {
        Scanner scanner = new Scanner(System.in);
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
