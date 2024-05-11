package redeemitem;

import java.util.ArrayList;

public class PointsManager {

    private static ArrayList<String> redeemableItems = new ArrayList<String>();
    
    public static int deductPoints(int selectedIndex, int quantityNeeded, int customerPoints, int userId) {
        redeemableItems = RedeemFileUtils.getRedeemableItems();

        if (selectedIndex >= 0 && selectedIndex < redeemableItems.size()) {
            String itemName = redeemableItems.get(selectedIndex);
            int itemPointCost = RedeemFileUtils.getPointCost(itemName);
            int quantity = RedeemFileUtils.getItemQuantity(selectedIndex);
            
            if (itemPointCost > 0 && customerPoints >= itemPointCost * quantityNeeded) {
                int totalPoints = itemPointCost * quantityNeeded;
                customerPoints -= totalPoints;
                quantity -= quantityNeeded;
                RedeemFileUtils.setItemQuantity(selectedIndex,quantity);
                EarnedPoints.setTotalEarnedPoints(userId,customerPoints);
                
                System.out.println("Points deducted successfully. Remaining points: " + customerPoints);
                System.out.println("Item redeemed successfully. Thank you!\n");
                
                // Return the totalPoints
                return totalPoints;
            } else {
                System.out.println("Insufficient points.\n");
                return 0; // Return 0 if insufficient points
            }
        } else {
            System.out.println("Invalid index. Please try again.");
            return 0; // Return 0 for invalid index
        }
    }
}
