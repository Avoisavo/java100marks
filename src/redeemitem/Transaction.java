package redeemitem;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// why is this entire file commented lmao

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jason Paw
 */
/*
// Transaction class
public class Transaction {
    private static int transactionIdCounter = 1; // Counter for auto-generated transaction ID
    private String transactionId;
    private String customerName;
    private int points;
    private Date transactionDate ;
    private String transactionType;

    // Constructor
    public Transaction(String customerName, int points, String transactionType) {
        this.transactionId = generateTransactionId(); // Auto-generated transaction ID
        this.customerName = customerName;
        this.points = points;
        this.transactionDate = new Date();  // Current date
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
    public Date getTransactionDate() {
        return transactionDate;
    }
    public String getTransactionType() {
        return transactionType;
    }
    
    // Method to display transactions in table format
    public static void displayTransactions(List<Transaction> transactions) {
        System.out.println("-------------------------------------------------------------------");
        System.out.println("|                        TRANSACTION DETAILS                      |");
        System.out.println("------------------------------------------------------------------");
        System.out.println("| Transaction ID | Customer Name | Points |  Date     | Type      |");
        System.out.println("-------------------------------------------------------------------");
        for (Transaction transaction : transactions) {
            System.out.printf("| %-15s| %-14s| %-7d| %-8tF | %-10s|\n",
                    transaction.getTransactionId(), transaction.getCustomerName(),
                    transaction.getPoints(), transaction.getTransactionDate(),
                    transaction.getTransactionType());
        }
        System.out.println("-------------------------------------------------------------------\n");
    }
}*/
