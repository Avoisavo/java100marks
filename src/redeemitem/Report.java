package redeemitem;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public abstract class Report {
   protected Map<String, Integer> redeemedItemsCount = new HashMap<>();
   protected Map<LocalDate, Integer> redemptionPerDay = new HashMap<>();
   protected Map<String, String> userNamesMap = new HashMap<>();
   protected Map<String, Integer> pointsMap = new HashMap<>();
   protected Map<String, Integer> redeemedItemsQuantity = new HashMap<>();
   protected int totalPointsRedeemed = 0;
   
   public abstract void generateReport();
   
   
   public void fetchRedemptionData(){
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/avo/Documents/GitHub/java100marks/src/data/customers.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(", ");
                    String redeemedItem = parts[2].substring(parts[2].indexOf(":") + 2);
                    int pointsRedeemed = Integer.parseInt(parts[3].substring(parts[3].indexOf(":") + 2));

                    redeemedItemsCount.put(redeemedItem, redeemedItemsCount.getOrDefault(redeemedItem, 0) + 1);
                    totalPointsRedeemed += pointsRedeemed;
                }
            } 
        catch (IOException e) {
                System.err.println("Error occurred while reading redeem_data.txt: " + e.getMessage());
            }
    }
   protected void fetchCustomerData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Jason Paw\\OneDrive - student.tarc.edu.my\\Documents\\GitHub\\java100marks\\src\\data\\customers.txt"))) {
            String line;
            String userId = "";
            String userName = "";
            String loyaltyStatus = "";
            int totalPointsEarned = 0;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("User ID: ")) {
                    userId = line.split(": ")[1];
                } else if (line.startsWith("User Name: ")) {
                    userName = line.split(": ")[1];
                } else if (line.startsWith("Loyalty Status: ")) {
                    loyaltyStatus = line.split(": ")[1];
                } else if (line.startsWith("Total Points Earned: ")) {
                    totalPointsEarned = Integer.parseInt(line.split(": ")[1]);

                    // Store the user name and total points earned
                    String key = userId + "-" + loyaltyStatus;
                    userNamesMap.put(key, userName);
                    pointsMap.put(key, totalPointsEarned);
                }
            }
        } catch (IOException e) {
            System.err.println("Error occurred while reading the file: " + e.getMessage());
        }
    }

}

// please refer to updated UML diagram or group leader regarding the updated policy of handling loyalty status

// Report for Gold Status
 class GoldStatusReport extends Report {
   
    // Override generateReport method
     @Override
    public void generateReport() {
        int totalPoints = 0;
        System.out.println("\nGold Status Report:");
        System.out.println("-----------------------------------------------------");
        System.out.println("|User ID\t|User Name\t|Total Points Earned|");
        System.out.println("-----------------------------------------------------");

        for (Map.Entry<String, Integer> entry : pointsMap.entrySet()) {
            if (entry.getKey().endsWith("Gold")) {
                String userId = entry.getKey().split("-")[0];
                String userName = userNamesMap.get(entry.getKey());
                int pointsEarned = entry.getValue();
                totalPoints += pointsEarned;
                System.out.println("|"+userId + "\t\t|" + userName + "\t\t|" + pointsEarned+"\t\t    |");
            }
        }
        System.out.println("-----------------------------------------------------");
        System.out.println("\nTotal Points Earned by All Gold Customer: " + totalPoints + "\n");
    }
}

// Report for Silver Status
 class SilverStatusReport extends Report {
    
    // Override generateReport method
    @Override
    public void generateReport() {
        int totalPoints = 0;
        System.out.println("\nSilver Status Report:");
        System.out.println("-----------------------------------------------------");
        System.out.println("|User ID\t|User Name\t|Total Points Earned|");
        System.out.println("-----------------------------------------------------");

        for (Map.Entry<String, Integer> entry : pointsMap.entrySet()) {
            if (entry.getKey().endsWith("Silver")) {
                String userId = entry.getKey().split("-")[0];
                String userName = userNamesMap.get(entry.getKey());
                int pointsEarned = entry.getValue();
                totalPoints += pointsEarned;
                System.out.println("|"+userId + "\t\t|" + userName + "\t\t|" + pointsEarned+"\t\t    |");
            }
        }
        System.out.println("-----------------------------------------------------");
        System.out.println("\nTotal Points Earned by All Silver Customer: " + totalPoints + "\n");
    }
}

// Report for Member Status
 class ClassicStatusReport extends Report {

    // Override generateReport method
    @Override
    public void generateReport() {
        int totalPoints = 0;
        System.out.println("\nClassic Status Report:");
        System.out.println("-----------------------------------------------------");
        System.out.println("|User ID\t|User Name\t|Total Points Earned|");
        System.out.println("-----------------------------------------------------");

        for (Map.Entry<String, Integer> entry : pointsMap.entrySet()) {
            if (entry.getKey().endsWith("Classic")) {
                String userId = entry.getKey().split("-")[0];
                String userName = userNamesMap.get(entry.getKey());
                int pointsEarned = entry.getValue();
                totalPoints += pointsEarned;
                System.out.println("|"+userId + "\t\t|" + userName + "\t\t|" + pointsEarned+"\t\t    |");
            }
        }
        System.out.println("-----------------------------------------------------");
        System.out.println("\nTotal Points Earned by All Classic Customer: " + totalPoints + "\n");
    }
}

// Yearly Points Earned Trends Report
class RedemptionSummary extends Report {
    
    @Override
    public void fetchRedemptionData() {
            try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Jason Paw\\OneDrive - student.tarc.edu.my\\Documents\\GitHub\\java100marks\\src\\data\\redemptionDetails.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                String redeemedItem = parts[2].substring(parts[2].indexOf(":") + 2);
                int quantityRedeemed = Integer.parseInt(parts[4].substring(parts[4].indexOf(":") + 2));
                redeemedItemsCount.put(redeemedItem, redeemedItemsCount.getOrDefault(redeemedItem, 0) + 1);
                redeemedItemsQuantity.put(redeemedItem, redeemedItemsQuantity.getOrDefault(redeemedItem, 0) + quantityRedeemed);
            }
        } catch (IOException e) {
            System.err.println("Error occurred while reading redeem_data.txt: " + e.getMessage());
        }
    }
    // Override generateReport method
    @Override
    public void generateReport() {
        System.out.println("--------------------------------");
        System.out.println("|Redemption Item Summary Report|");
        System.out.println("--------------------------------");
        for (Map.Entry<String, Integer> entry : redeemedItemsCount.entrySet()) {
            String redeemedItem = entry.getKey();
            int quantityRedeemed = redeemedItemsQuantity.getOrDefault(redeemedItem, 0);
            int countRedeemed = entry.getValue();
            System.out.print("Redeemed Items("+redeemedItem + ") \nTotal Count Redeemed: " + countRedeemed + "\nTotal Redeemed Amount: " + quantityRedeemed+"\n\n");
        }
    }
}
