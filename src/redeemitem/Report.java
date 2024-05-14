package redeemitem;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public abstract class Report {
   public Map<String, Integer> redeemedItemsCount = new HashMap<>();
   public Map<LocalDate, Integer> redemptionPerDay = new HashMap<>();
   Map<String, Integer> redeemedItemsQuantity = new HashMap<>();
   public int totalPointsRedeemed = 0;
   
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
}

// please refer to updated UML diagram or group leader regarding the updated policy of handling loyalty status

// Report for Gold Status
 class GoldStatusReport extends Report {
   
    // Override generateReport method
    @Override
    public void generateReport() {
        System.out.println("\n---------------------------------------------------------");
        System.out.println("|        Overall Gold Customer Yearly Report          |");
        System.out.println("---------------------------------------------------------");
        System.out.println("|Year\t|Total Points Earned\t|Total Product Redeemed\t|");
        System.out.println("---------------------------------------------------------");
        // Simulated data for each year
        for (int year = 2020; year <= 2021; year++) {
            System.out.println("|"+year + "\t|$" + totalPointsRedeemed+"\t\t\t|"+totalPointsRedeemed+"\t\t\t|");
        }
        System.out.println("---------------------------------------------------------");
    }
}

// Report for Silver Status
 class SilverStatusReport extends Report {
    
    // Override generateReport method
    @Override
    public void generateReport() {
        System.out.println("\n---------------------------------------------------------");
        System.out.println("|      Overall Silver Customer Yearly Report          |");
        System.out.println("---------------------------------------------------------");
        System.out.println("|Year\t|Total Amount Earned\t|Total Product Redeemed\t|");
        System.out.println("---------------------------------------------------------");
        // Simulated data for each year
        for (int year = 2020; year <= 2021; year++) {
            System.out.println("|"+year + "\t|$" + totalPointsRedeemed+"\t\t\t|"+totalPointsRedeemed+"\t\t\t|");
        }
        System.out.println("---------------------------------------------------------");
    }
}

// Report for Member Status
 class ClassicStatusReport extends Report {

    // Override generateReport method
    @Override
    public void generateReport() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("|      Overall Classic Customer Yearly Report         |");
        System.out.println("-----------------------------------------------------------");
        System.out.println("|Year\t|Total Amount Earned\t|Total Product Redeemed\t|");
        System.out.println("-----------------------------------------------------------");
        // Simulated data for each year
        for (int year = 2020; year <= 2021; year++) {
            System.out.println("|"+year + "\t|$" + totalPointsRedeemed+"\t\t\t|"+totalPointsRedeemed+"\t\t\t|");
        }
        System.out.println("-----------------------------------------------------------");
    }
}

// Yearly Points Earned Trends Report
class PointsRedeemedTrendsReport extends Report {
    
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
            System.out.print("Redeemed Items("+redeemedItem + "): \nTotal Count Redeemed: " + countRedeemed + "\nTotal Redeemed Amount: " + quantityRedeemed+"\n\n");
        }
    }
}
