/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jason Paw
 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Transaction class
class Transaction {
    private static int transactionIdCounter = 1; // Counter for auto-generated transaction ID
    private String transactionId;
    private String customerName;
    private int points;
    private LocalDate transactionDate;
    private String transactionType;

    // Constructor
    public Transaction(String customerName, int points, String transactionType) {
        this.transactionId = generateTransactionId(); // Auto-generated transaction ID
        this.customerName = customerName;
        this.points = points;
        this.transactionDate = LocalDate.now(); // Current date
        this.transactionType = transactionType;
    }
    private String generateTransactionId() {
        String id = String.format("%04d", transactionIdCounter);
        transactionIdCounter++;
        return id;
    }
    
    // Getters
    public String getTransactionId() {
        return transactionId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public int getPoints() {
        return points;
    }
    public LocalDate getTransactionDate() {
        return transactionDate;
    }
    public String getTransactionType() {
        return transactionType;
    }
}

// Abstract class for Report
abstract class Report {
    protected String loyaltyStatus;
    protected int spendingPercentage;
    protected int redeemedPercentage;
    protected int totalAmount;
    // Constructor
    public Report(String loyaltyStatus, int spendingPercentage, int redeemedPercentage, int totalAmount) {
        this.loyaltyStatus = loyaltyStatus;
        this.spendingPercentage = spendingPercentage;
        this.redeemedPercentage = redeemedPercentage;
        this.totalAmount = totalAmount;
    }
    // Abstract method to generate report
    public abstract void generateReport();
}

// Report for Gold Status
class GoldStatusReport extends Report {
    // Constructor
    public GoldStatusReport(int spendingPercentage, int redeemedPercentage, int totalAmount) {
        super("Gold", spendingPercentage, redeemedPercentage, totalAmount);
    }
    
    // Override generateReport method
    @Override
    public void generateReport() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("|                   Gold Status Report                 |");
        System.out.println("-------------------------------------------------------");
        System.out.println("|Year\t|Spending %\t|Redeemed %\t|Total Amount\t|");
        System.out.println("-------------------------------------------------------");
        // Simulated data for each year
        for (int year = 2020; year <= 2023; year++) {
            System.out.println("|"+year + "\t|" + spendingPercentage + "%\t\t|" + redeemedPercentage + "%\t\t|$" + totalAmount+"\t\t|");
        }
        System.out.println("-------------------------------------------------------");
    }
}

// Report for Silver Status
class SilverStatusReport extends Report {
    // Constructor
    public SilverStatusReport(int spendingPercentage, int redeemedPercentage, int totalAmount) {
        super("Silver", spendingPercentage, redeemedPercentage, totalAmount);
    }

    // Override generateReport method
    @Override
    public void generateReport() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("|                 Silver Status Report                 |");
        System.out.println("-------------------------------------------------------");
        System.out.println("|Year\t|Spending %\t|Redeemed %\t|Total Amount\t|");
        System.out.println("-------------------------------------------------------");
        // Simulated data for each year
        for (int year = 2020; year <= 2023; year++) {
            System.out.println("|"+year + "\t|" + spendingPercentage + "%\t\t|" + redeemedPercentage + "%\t\t|$" + totalAmount+"\t\t|");
        }
        System.out.println("-------------------------------------------------------");
    }
}

// Report for Member Status
class MemberStatusReport extends Report {
    // Constructor
    public MemberStatusReport(int spendingPercentage, int redeemedPercentage, int totalAmount) {
        super("Member", spendingPercentage, redeemedPercentage, totalAmount);
    }

    // Override generateReport method
    @Override
    public void generateReport() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("|                 Member Status Report                 |");
        System.out.println("-------------------------------------------------------");
        System.out.println("|Year\t|Spending %\t|Redeemed %\t|Total Amount\t|");
        System.out.println("-------------------------------------------------------");
        // Simulated data for each year
        for (int year = 2020; year <= 2023; year++) {
            System.out.println("|"+year + "\t|" + spendingPercentage + "%\t\t|" + redeemedPercentage + "%\t\t|$" + totalAmount+"\t\t|");
        }
        System.out.println("-------------------------------------------------------");
    }
}

// Main class
public class PointRewardSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int trans_ans;
        boolean contTrans = true;
        
        // Create sample transactions
        Transaction transaction1 = new Transaction("John Doe", 100, "EARNED");
        Transaction transaction2 = new Transaction("Jane Smith", 50, "REDEEMED");
        Transaction transaction3 = new Transaction("Alice Johnson", 200, "EARNED");

        // Create a list of transactions
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);

        do{
            try{
                System.out.print("Do you want to look at the transaction details?[Yes=(1)/No=(0)]: ");
                trans_ans = scanner.nextInt();
                if (trans_ans == 1){
                    // Generate the report
                    displayTransactions(transactions);
                    contTrans = false;
                }
                else if (trans_ans == 0 ){
                    contTrans = false;
                }
                else{
                System.out.print("Error input. Please try again.\n");
                scanner.nextLine();
                }
            }
            catch(Exception ex){
                System.out.print("Error input. Please try again.\n");
                scanner.nextLine();
            }
        }while (contTrans);
        
        
        int report_ans;
        int genereport_ans;
        int repeatgene_ans;
        boolean contReport = true;
        boolean contGeneReport = true;
        boolean repeatReport = true;
        
        // Create reports for different loyalty statuses
        Report goldReport = new GoldStatusReport(20, 10, 5000);
        Report silverReport = new SilverStatusReport(15, 5, 3000);
        Report memberReport = new MemberStatusReport(10, 2, 2000);

        
        do{
            try{
                System.out.print("Do you want to look at the report?[Yes=(1)/No=(0)]: ");
                report_ans = scanner.nextInt();
                if (report_ans == 1){
                    do{
                        try{
                        System.out.print("Which report do you want to look?[Member(1)/Silver(2)/Gold(3)/Cancel(0)]: ");
                        genereport_ans = scanner.nextInt();
                        if(genereport_ans == 1){
                            // Generate reports
                            memberReport.generateReport();
                            do {
                                try{
                                    System.out.print("Do you want to look at report again?[Yes=(1)/No=(0)]: ");
                                    repeatgene_ans = scanner.nextInt();
                                    if(repeatgene_ans == 1){ 
                                        repeatReport = false;
                                    }
                                    else if(repeatgene_ans == 0){
                                    repeatReport = false;
                                    contGeneReport = false;
                                    }
                                    else{
                                    System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                    scanner.nextLine();
                                    repeatReport = true;}
                                }
                                catch(Exception ex){
                                    System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                    scanner.nextLine();
                                    repeatReport = true;
                                }
                            
                            }while(repeatReport);
                        }
                        else if (genereport_ans == 2){
                            // Generate reports
                            silverReport.generateReport();
                            do {
                                try{
                                    System.out.print("Do you want to look at report again?[Yes=(1)/No=(0)]: ");
                                    repeatgene_ans = scanner.nextInt();
                                    if(repeatgene_ans == 1){ 
                                        repeatReport = false;
                                    }
                                    else if(repeatgene_ans == 0){
                                    repeatReport = false;
                                    contGeneReport = false;
                                    }
                                    else{
                                    System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                    scanner.nextLine();
                                    repeatReport = true;}
                                }
                                catch(Exception ex){
                                    System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                    scanner.nextLine();
                                    repeatReport = true;
                                }
                            
                            }while(repeatReport);
                        }
                        else if (genereport_ans == 3){
                             // Generate reports
                            goldReport.generateReport();
                            do {
                                try{
                                    System.out.print("Do you want to look at report again?[Yes=(1)/No=(0)]: ");
                                    repeatgene_ans = scanner.nextInt();
                                    if(repeatgene_ans == 1){ 
                                        repeatReport = false;
                                    }
                                    else if(repeatgene_ans == 0){
                                    repeatReport = false;
                                    contGeneReport = false;
                                    }
                                    else{
                                    System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                    scanner.nextLine();
                                    repeatReport = true;}
                                }
                                catch(Exception ex){
                                    System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                    scanner.nextLine();
                                    repeatReport = true;
                                }
                            
                            }while(repeatReport);
                        }
                        else if (genereport_ans == 0){
                             contGeneReport = false;
                        }
                        else {
                            System.out.print("Error input. Please try again.\n");
                            scanner.nextLine();
                                    }
                        }
                        
                        catch(Exception ex){
                         System.out.print("Error input. Please try again.\n");
                         scanner.nextLine();
                         }
                        
                    }while(contGeneReport);   
                    contReport = false;
                }
                else if (report_ans == 0 ){
                    contReport = false;
                }
                else{
                    System.out.print("Error input. Please try again.\n");
                scanner.nextLine();
                }
            }
            catch(Exception ex){
                System.out.print("Error input. Please try again.\n");
                scanner.nextLine();
            }
        }while (contReport);
    }

    // Method to display transactions in table format
    static void displayTransactions(List<Transaction> transactions) {
        System.out.println("-------------------------------------------------------------------");
        System.out.println("|                        TRANSACTION DETAILS                      |");
        System.out.println("------------------------------------------------------------------");
        System.out.println("| Transaction ID | Customer Name | Points |  Date     | Type      |");
        System.out.println("-------------------------------------------------------------------");
        for (Transaction transaction : transactions) {
            System.out.printf("| %-15s| %-14s| %-7d| %-8s| %-10s|\n",
                    transaction.getTransactionId(), transaction.getCustomerName(),
                    transaction.getPoints(), transaction.getTransactionDate(),
                    transaction.getTransactionType());
        }
        System.out.println("-------------------------------------------------------------------");
    }
        
}





