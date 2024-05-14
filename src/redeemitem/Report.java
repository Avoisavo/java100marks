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

   
   public void fetchCustomerData() {
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


// Report for Gold Status
 class GoldStatusReport extends Report {
    // Override generateReport method
     @Override
    public void generateReport() {
        String header = String.format("%-10s | %-15s | %-20s", "|User ID", "User Name", "Total Points Earned|");
        String separator = new String(new char[header.length()]).replace('\0', '-');
        System.out.println("\n"+separator);
        System.out.println("|               Gold Status Report                |");
        System.out.println(separator);
        System.out.println(header);
        System.out.println(separator);
        int totalPoints = 0;

        for (Map.Entry<String, Integer> entry : pointsMap.entrySet()) {
            if (entry.getKey().endsWith("Gold")) {
                String userId = entry.getKey().split("-")[0];
                String userName = userNamesMap.get(entry.getKey());
                int pointsEarned = entry.getValue();
                totalPoints += pointsEarned;
                System.out.println("|"+String.format("%-9s | %-15s | %-19d", userId, userName, pointsEarned)+"|");
            }
        }
        System.out.println(separator);
        System.out.println("\nTotal Points Earned by All Gold Customer: " + totalPoints + "\n");
    }
}


// Report for Silver Status
 class SilverStatusReport extends Report {
    // Override generateReport method
    @Override
        public void generateReport() {
        String header = String.format("%-10s | %-15s | %-20s", "|User ID", "User Name", "Total Points Earned|");
        String separator = new String(new char[header.length()]).replace('\0', '-');
        System.out.println("\n"+separator);
        System.out.println("|             Silver Status Report                |");
        System.out.println(separator);
        System.out.println(header);
        System.out.println(separator);
        int totalPoints = 0;

        for (Map.Entry<String, Integer> entry : pointsMap.entrySet()) {
            if (entry.getKey().endsWith("Silver")) {
                String userId = entry.getKey().split("-")[0];
                String userName = userNamesMap.get(entry.getKey());
                int pointsEarned = entry.getValue();
                totalPoints += pointsEarned;
                System.out.println("|"+String.format("%-9s | %-15s | %-19d", userId, userName, pointsEarned)+"|");
            }
        }
        System.out.println(separator);
        System.out.println("\nTotal Points Earned by All Gold Customer: " + totalPoints + "\n");
    }
}


// Report for Classic Status
 class ClassicStatusReport extends Report {
    // Override generateReport method
    @Override
        public void generateReport() {
        String header = String.format("%-10s | %-15s | %-20s", "|User ID", "User Name", "Total Points Earned|");
        String separator = new String(new char[header.length()]).replace('\0', '-');
        System.out.println("\n"+separator);
        System.out.println("|            Classic Status Report                |");
        System.out.println(separator);
        System.out.println(header);
        System.out.println(separator);
        int totalPoints = 0;

        for (Map.Entry<String, Integer> entry : pointsMap.entrySet()) {
            if (entry.getKey().endsWith("Classic")) {
                String userId = entry.getKey().split("-")[0];
                String userName = userNamesMap.get(entry.getKey());
                int pointsEarned = entry.getValue();
                totalPoints += pointsEarned;
                System.out.println("|"+String.format("%-9s | %-15s | %-19d", userId, userName, pointsEarned)+"|");
            }
        }
        System.out.println(separator);
        System.out.println("\nTotal Points Earned by All Gold Customer: " + totalPoints + "\n");
    }
}


// Redemption Points Summary Report
class RedemptionSummary extends Report {
    // Override generateReport method
    @Override
    public void generateReport() {
        String header = String.format("%-10s | %-15s | %-20s", "|Redeemed Items", "Total Count Redeemed", "Total Redeemed Amount|");
        String separator = new String(new char[header.length()]).replace('\0', '-');
        
        System.out.println("\n---------------------------------------------------------------");
        System.out.println("|               Redemption Item Summary Report                |");
        System.out.println(separator);
        System.out.println(header);
        System.out.println(separator);
        for (Map.Entry<String, Integer> entry : redeemedItemsCount.entrySet()) {
            String redeemedItem = entry.getKey();
            int quantityRedeemed = redeemedItemsQuantity.getOrDefault(redeemedItem, 0);
            int countRedeemed = entry.getValue();
            System.out.println(String.format("|"+"%-14s | %-20s | %-21s", redeemedItem, countRedeemed, quantityRedeemed)+"|");
            
        }System.out.println("---------------------------------------------------------------\n");
    }
}
