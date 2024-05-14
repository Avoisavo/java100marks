package redeemitem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
//  please put loyaltyStatus as a property under Customer instead of writing a class
public class LoyaltyStatus extends EarnedPoints {
    private static final String POINTS_FILE_PATH = "src/redeemitem/customers.txt";
    private String status;
    private int pointConvertRate;

    public void readLoyaltyStatusFromFile(int loggedInUserId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(POINTS_FILE_PATH))) {
            String line;
            int userPoints = 0; 
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("User ID: " + loggedInUserId)) {
                  
                    while ((line = reader.readLine()) != null && !line.isEmpty()) {
                        if (line.startsWith("Total Points Earned: ")) {
                            userPoints = Integer.parseInt(line.substring("Total Points Earned: ".length()));
                            break; 
                        }
                    }
                    break; 
                }
            }

            if (userPoints > 2000) {
                Gold gold = new Gold();
                gold.goldStatus();
            } else if (userPoints > 500) {
                Silver silver = new Silver();
                silver.silverStatus();
            } else if (userPoints > 0) {
                Classic classic = new Classic();
                classic.classicStatus();
            } else {
                System.out.println("Classic loyalty status");
            }
        } catch (IOException e) {
            System.err.println("Error occurred while reading loyalty status from file: " + e.getMessage());
        }
    }
    
    public String getStatus(int loggedInUserId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(POINTS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("User ID: " + loggedInUserId)) {
                    while ((line = reader.readLine()) != null && !line.isEmpty()) {
                        if (line.startsWith("Loyalty Status: ")) {
                            status = line.substring("Loyalty Status: ".length());
                            return status;
                        }
                    }
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error occurred while reading loyalty status from file: " + e.getMessage());
        }
        return null; // Return null if status not found
    }
    

}

class Gold extends LoyaltyStatus {
      public void goldStatus() {
        System.out.println("");
        System.out.println("-----Your Loyalty Status----");
        System.out.println("Your Loyalty Status is Gold!");
        System.out.println("Your points reward is 1.5x points!");
        System.out.println("");

    }
}

class Silver extends LoyaltyStatus {
        public void silverStatus() {
        System.out.println("");
        System.out.println("-----Your Loyalty Status----");
        System.out.println("Your Loyalty Status is Silver!");
        System.out.println("Your points reward is 1.2x points!");
        System.out.println("");
    }
}

class Classic extends LoyaltyStatus {
        public void classicStatus() {
        System.out.println("");
        System.out.println("-----Your Loyalty Status----");
        System.out.println("Your Loyalty Status is Classic!");
        System.out.println("");
    }
}
