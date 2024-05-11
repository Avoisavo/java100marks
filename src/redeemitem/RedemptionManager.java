package redeemitem;

import java.util.Scanner;

public class RedemptionManager {
    
    private int customer_point; 
    
    public void redeemItems(int selectedIndex, int quantity, int userId) {
        customer_point = RetrivePoints.getTotalPointsEarned(userId);
        PointsManager.deductPoints(selectedIndex - 1, quantity, customer_point, userId);
    }
}

    