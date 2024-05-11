package redeemitem;


import java.util.HashMap;
import java.util.Map;

public abstract class Report {
    protected String loyaltyStatus;
    protected int totalPointsEarned;
    protected int totalPointsRedeemed;
    protected int totalProductRedeemed;
    protected Map<Integer, Integer> yearlyRedeemedAmounts;
    protected Map<Integer, Integer> pointsEarnedData;
    
    public Report(String loyaltyStatus, int totalPointsEarned, int totalPointsRedeemed,int totalProductRedeemed) {
        this.loyaltyStatus = loyaltyStatus;
        this.totalPointsEarned = totalPointsEarned;
        this.totalPointsRedeemed = totalPointsRedeemed;
        this.totalProductRedeemed = totalProductRedeemed;
        this.yearlyRedeemedAmounts = new HashMap<>();
    }
    
    public abstract void generateReport();

    void addYearlyRedeemedAmount(int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void addPointsEarnedData(int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

// please refer to updated UML diagram or group leader regarding the updated policy of handling loyalty status

// Report for Gold Status
 class GoldStatusReport extends Report {
    // Constructor
    public GoldStatusReport( int totalPointsEarned, int totalPointsRedeemed,int totalProductRedeemed) {
        super("Gold", totalPointsEarned, totalPointsRedeemed,totalProductRedeemed);
    }
    
    // Override generateReport method
    @Override
    public void generateReport() {
        System.out.println("\n---------------------------------------------------------------------------");
        System.out.println("|                 Overall Gold Customer Yearly Report                      |");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("|Year\t|Total Points Earned\t|Total Points Redeemed\t|Total Product Redeemed");
        System.out.println("---------------------------------------------------------------------------");
        // Simulated data for each year
        for (int year = 2020; year <= 2021; year++) {
            System.out.println("|"+year + "\t|$" + totalPointsEarned+"\t\t\t|$"+totalPointsRedeemed+"\t\t\t|"+totalProductRedeemed+"\t\t\t|");
        }
        System.out.println("---------------------------------------------------------------------------");
    }
}

// Report for Silver Status
 class SilverStatusReport extends Report {
    
    // Constructor
    public SilverStatusReport( int totalPointsEarned, int totalPointsRedeemed,int totalProductRedeemed) {
        super("Silver", totalPointsEarned, totalPointsRedeemed,totalProductRedeemed);
    }

    // Override generateReport method
    @Override
    public void generateReport() {
        System.out.println("\n---------------------------------------------------------------------------");
        System.out.println("|                  Overall Silver Customer Yearly Report                   |");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("|Year\t|Total Amount Earned\t|Total Amount Redeemed\t|Total Product Redeemed");
        System.out.println("---------------------------------------------------------------------------");
        // Simulated data for each year
        for (int year = 2020; year <= 2021; year++) {
            System.out.println("|"+year + "\t|$" + totalPointsEarned+"\t\t\t|$"+totalPointsRedeemed+"\t\t\t|"+totalProductRedeemed+"\t\t\t|");
        }
        System.out.println("---------------------------------------------------------------------------");
    }
}

// Report for Member Status
 class MemberStatusReport extends Report {
    // Constructor
    public MemberStatusReport (int totalPointsEarned, int totalPointsRedeemed,int totalProductRedeemed) {
        super("Member", totalPointsEarned, totalPointsRedeemed,totalProductRedeemed);
    }

    // Override generateReport method
    @Override
    public void generateReport() {
        System.out.println("\n---------------------------------------------------------------------------");
        System.out.println("|                   Overall Member Customer Yearly Report                   |");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("|Year\t|Total Amount Earned\t|Total Amount Redeemed\t|Total Product Redeemed");
        System.out.println("---------------------------------------------------------------------------");
        // Simulated data for each year
        for (int year = 2020; year <= 2021; year++) {
            System.out.println("|"+year + "\t|$" + totalPointsEarned+"\t\t\t|$"+totalPointsRedeemed+"\t\t\t|"+totalProductRedeemed+"\t\t\t|");
        }
        System.out.println("---------------------------------------------------------------------------");
    }
}

// Yearly Redemption Trends Report
 class RedemptionTrendsReport extends Report {
    
    // Constructor
    public RedemptionTrendsReport( int totalPointsEarned, int totalPointsRedeemed,int totalProductRedeemed) {
        super("", totalPointsEarned, totalPointsRedeemed,totalProductRedeemed);
    }
    @Override
    public void addYearlyRedeemedAmount(int year, int redeemedAmount) {
        yearlyRedeemedAmounts.put(year, redeemedAmount);
    }
    // Override generateReport method
    @Override
    public void generateReport() {
        System.out.println("\n------------------------------");
        System.out.println("|Points Redemption Trends Report|");
        System.out.println("------------------------------");
        System.out.println("|Year\t|Redeemed Amount\t|");
        System.out.println("------------------------------");
        for (Map.Entry<Integer, Integer> entry : yearlyRedeemedAmounts.entrySet()) {
            int year = entry.getKey();
            int redeemedAmount = entry.getValue();
            System.out.println("|" + year + "\t|$" + redeemedAmount + "\t\t\t|");
        }
        System.out.println("------------------------------");
    }
}

// Yearly Points Earned Trends Report
 class PointsEarnedTrendsReport extends Report {
    // Constructor
    public PointsEarnedTrendsReport(int totalPointsEarned, int totalPointsRedeemed,int totalProductRedeemed) {
        super("",totalPointsEarned, totalPointsRedeemed,totalProductRedeemed);
        this.pointsEarnedData = new HashMap<>();
    }


    public void addPointsEarnedData(int year, int pointsEarned) {
        pointsEarnedData.put(year, pointsEarned);
    }

    // Override generateReport method
    @Override
    public void generateReport() {
      
        System.out.println("\n----------------------------");
        System.out.println("|Points Earned Trends Report|");
        System.out.println("----------------------------");
        System.out.println("|Year\t|Points Earned\t|");
        System.out.println("----------------------------");
        // Display points earned data for each year
        for (Map.Entry<Integer, Integer> entry : pointsEarnedData.entrySet()) {
            System.out.println("|" + entry.getKey() + "\t|" + entry.getValue() + "\t\t|");
        }
        
        System.out.println("------------------------------");
    }
}
