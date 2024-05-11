package redeemitem;

import java.util.Scanner;

// please refer to updated UML diagram or group leader regarding this class
public class RedemptionManager {
    
    private int customer_point; 
    
    public void redeemItems(int selectedIndex, int quantity, int userId) {
        customer_point = RetrivePoints.getTotalPointsEarned(userId);
        PointsManager.deductPoints(selectedIndex - 1, quantity, customer_point, userId);
    }
}

    
