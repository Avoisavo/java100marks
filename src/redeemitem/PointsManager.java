package redeemitem;

import java.util.ArrayList;

// as per discussion, please refer to UML diagram or group leader regarding updates for this class as it is not needed anymore
public class PointsManager {

    private static ArrayList<String> redeemableItems = new ArrayList<String>();
    
    
    public static void deductPoints(int selectedIndex, int quantityNeeded, int customerPoints, int userId) {
        // Get the item name based on the selected index
   
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
            } else {
                System.out.println("Insufficient points.\n");
            }
        } else {
            System.out.println("Invalid index. Please try again.");
        }
    }
}

