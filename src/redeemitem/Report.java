package redeemitem;


import java.util.HashMap;
import java.util.Map;

public abstract class Report {
    protected String loyaltyStatus;
    protected int totalPointsEarned;
    protected int totalProductRedeemed;
    protected Map<Integer, Integer> yearlyRedeemedAmounts;
    protected Map<Integer, Integer> pointsEarnedData;
    
    public Report(String loyaltyStatus, int totalPointsEarned,int totalProductRedeemed) {
        this.loyaltyStatus = loyaltyStatus;
        this.totalPointsEarned = totalPointsEarned;
        this.totalProductRedeemed = totalProductRedeemed;
        this.yearlyRedeemedAmounts = new HashMap<>();
    }
    
    public abstract void generateReport();

    void addPointsEarnedData(int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

// please refer to updated UML diagram or group leader regarding the updated policy of handling loyalty status

// Report for Gold Status
 class GoldStatusReport extends Report {
    // Constructor
    public GoldStatusReport( int totalPointsEarned,int totalProductRedeemed) {
        super("Gold", totalPointsEarned,totalProductRedeemed);
    }
    
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
            System.out.println("|"+year + "\t|$" + totalPointsEarned+"\t\t\t|"+totalProductRedeemed+"\t\t\t|");
        }
        System.out.println("---------------------------------------------------------");
    }
}

// Report for Silver Status
 class SilverStatusReport extends Report {
    
    // Constructor
    public SilverStatusReport( int totalPointsEarned,int totalProductRedeemed) {
        super("Silver", totalPointsEarned,totalProductRedeemed);
    }

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
            System.out.println("|"+year + "\t|$" + totalPointsEarned+"\t\t\t|"+totalProductRedeemed+"\t\t\t|");
        }
        System.out.println("---------------------------------------------------------");
    }
}

// Report for Member Status
 class ClassicStatusReport extends Report {
    // Constructor
    public ClassicStatusReport (int totalPointsEarned,int totalProductRedeemed) {
        super("Member", totalPointsEarned,totalProductRedeemed);
    }

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
            System.out.println("|"+year + "\t|$" + totalPointsEarned+"\t\t\t|"+totalProductRedeemed+"\t\t\t|");
        }
        System.out.println("-----------------------------------------------------------");
    }
}

// Yearly Points Earned Trends Report
class PointsEarnedTrendsReport extends Report {
    
    // Constructor
    public PointsEarnedTrendsReport(int totalPointsEarned, int totalProductRedeemed) {
        super("", totalPointsEarned, totalProductRedeemed);
        this.pointsEarnedData = new HashMap<>();
    }

    
    // Method to add points earned data for a specific year
    public void addPointsEarnedData(int year, int pointsEarned) {
        pointsEarnedData.put(year, pointsEarned);
    }
    
    // Override generateReport method
    @Override
    public void generateReport() {
        System.out.println("\n-------------------------------------");
        System.out.println("| Total Points Earned Trends Report |");
        System.out.println("-------------------------------------");
        System.out.println("| Year | Points Earned ");
        for (Map.Entry<Integer, Integer> entry : pointsEarnedData.entrySet()) {
            int year = entry.getKey();
            int pointsEarned = entry.getValue();
            System.out.print("| " + year + " | ");
            for (int i = 0; i < (pointsEarned/100); i++) {
                System.out.print("*");
            }
            System.out.println("("+pointsEarned+")");
        }
        System.out.println("");
    }
}