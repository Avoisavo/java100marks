package redeemitem;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RedemptionManager {

    public void redeemItems(int selectedIndex, int quantity, int userId) {
        int customer_point = RetrivePoints.getTotalPointsEarned(userId);
        int totalPoints = PointsManager.deductPoints(selectedIndex - 1, quantity, customer_point, userId);

        String itemName = RedeemFileUtils.getRedeemableItems().get(selectedIndex - 1);

        // Generate a random redemption transaction Id
        int transactionId = generateTransactionId();

        // Get the current date
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String redemptionDate = dateFormat.format(currentDate);

        // Save redemption details to file
        saveRedemptionDetails(userId, transactionId, totalPoints, redemptionDate, itemName);
    }

    private int generateTransactionId() {
        Random random = new Random();
        return random.nextInt(1000000); // You can adjust the range as needed
    }

    private void saveRedemptionDetails(int userId, int transactionId, int totalPoints, String redemptionDate, String itemName) {
        try {
            FileWriter writer = new FileWriter("redemptionDetails.txt", true); // Append mode
            writer.write("User ID: " + userId + ", Transaction ID: " + transactionId + ", Redeemed Item: " + itemName + ", Total Points Redeemed: " + totalPoints + ", Redemption Date: " + redemptionDate + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
