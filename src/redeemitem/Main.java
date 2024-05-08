
package redeemitem;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        int userId = information.getUserId();
        // Retrieve the userId from information.java

        // Show available products
        List<Product> availableProducts = Product.getAvailableProducts();


        // Select products and display cart contents
        ShoppingCart cart = new ShoppingCart();
        List<Product> selectedProducts = Product.selectProducts();
        for (Product product : selectedProducts) {
            cart.addToCart(product);
        }
        Payment.displayCartContents(cart);

        // Make payment
        if (Payment.makePayment()) {
            int totalPointsEarned = cart.calculateTotalPointsEarned();
            LocalDate earningDate = LocalDate.now(); // Get current date

            //SAVE POINTSSSSS
            SavePoints.savePointsEarned(userId, earningDate, totalPointsEarned);

            // RETRIVE POINTSSSS
            int totalEarnedPoints = RetrivePoints.getTotalPointsEarned(userId);
            System.out.println("Total points earned by user: " + totalEarnedPoints);

            //UPDATE POINTSSS
            EarnedPoints.updatePointsEarned(userId, totalPointsEarned, totalEarnedPoints);

        }
    }
}
