/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redeemitem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SavePoints {

    private static final String POINTS_FILE_PATH = "/Users/avo/Desktop/RedeemItem/nbproject/points_earned.txt";

    public static void savePointsEarned(int userId, LocalDate earningDate, int totalPointsEarned) {
        try (FileWriter writer = new FileWriter(POINTS_FILE_PATH, true)) {

            LocalDate expiryDate = earningDate.plusDays(90);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedEarningDate = earningDate.format(formatter);
            String formattedExpiryDate = expiryDate.format(formatter);

            writer.write("User ID: " + userId + "\n");
            writer.write("Earned Points Date: " + formattedEarningDate + "\n");
            writer.write("Expiry Date: " + formattedExpiryDate + "\n");
            writer.write("Total Points Earned: " + totalPointsEarned + "\n\n");
            System.out.println("Points earned saved to file successfully.");
        } catch (IOException e) {
            System.err.println("Error occurred while saving points earned to file: " + e.getMessage());
        }
    }
}
