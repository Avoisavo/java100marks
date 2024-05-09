package redeemitem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoyaltyStatus extends EarnedPoints {
    private static final String POINTS_FILE_PATH = "/Users/avo/Documents/GitHub/java100marks/src/data/points_earned.txt";
    private String status;
    private int pointConvertRate;

    public void readLoyaltyStatusFromFile(int userId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(POINTS_FILE_PATH))) {
            String line;
            int userPoints = 0; 
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("User ID: " + userId)) {
                  
                    while ((line = reader.readLine()) != null && !line.isEmpty()) {
                        if (line.startsWith("Total Points Earned: ")) {
                            userPoints = Integer.parseInt(line.substring("Total Points Earned: ".length()));
                            break; 
                        }
                    }
                    break; 
                }
            }

            if (userPoints > 100) {
                Gold gold = new Gold();
                gold.goldStatus();
            } else if (userPoints > 50) {
                Silver silver = new Silver();
                silver.silverStatus();
            } else if (userPoints > 10) {
                Classic classic = new Classic();
                classic.classicStatus();
            } else {
                System.out.println("Basic loyalty status");
            }
        } catch (IOException e) {
            System.err.println("Error occurred while reading loyalty status from file: " + e.getMessage());
        }
    }

}

class Gold extends LoyaltyStatus {
      public void goldStatus() {
        System.out.println("");
        System.out.println("-----Your Loyalty Status----");
        System.out.println("Your Loyalty Status is Gold!");
        System.out.println("");

    }
}

class Silver extends LoyaltyStatus {
        public void silverStatus() {
        System.out.println("");
        System.out.println("-----Your Loyalty Status----");
        System.out.println("Your Loyalty Status is Silver!");
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
