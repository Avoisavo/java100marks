package redeemitem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class EarnedPoints {

    private static final String POINTS_FILE_PATH = "/Users/avo/Documents/GitHub/java100marks/src/data/points_earned.txt";

    public static void updatePointsEarned(int userId, int additionalPoints, int totalEarnedPoints) {
        Map<Integer, Integer> pointsMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(POINTS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("User ID: ")) {
                    int id = Integer.parseInt(line.substring("User ID: ".length()));
                    reader.readLine(); // Skip Earned Points Date
                    reader.readLine(); // Skip Expiry Date
                    String pointsLine = reader.readLine(); // Read Total Points Earned
                    int points = Integer.parseInt(pointsLine.substring("Total Points Earned: ".length()));
                    pointsMap.put(id, points);
                }
            }
        } catch (IOException e) {
            System.err.println("Error occurred while reading points earned from file: " + e.getMessage());
        }

        // Update points for the given user ID
        if (pointsMap.containsKey(userId)) {
            int currentPoints = pointsMap.get(userId);
            pointsMap.put(userId, currentPoints + additionalPoints);
        }

        // Rewrite points to file
        try (FileWriter writer = new FileWriter(POINTS_FILE_PATH)) {
            for (Map.Entry<Integer, Integer> entry : pointsMap.entrySet()) {
                writer.write("User ID: " + entry.getKey() + "\n");
                writer.write("Earned Points Date: " + LocalDate.now() + "\n");
                writer.write("Expiry Date: " + LocalDate.now().plusDays(90) + "\n");
                writer.write("Total Points Earned: " + (entry.getKey() == userId ? totalEarnedPoints : entry.getValue()) + "\n\n");
            }
            System.out.println("Points earned updated successfully.");
        } catch (IOException e) {
            System.err.println("Error occurred while updating points earned to file: " + e.getMessage());
        }
    }

    public static void setTotalEarnedPoints(int userId, int newTotalEarnedPoints) {
        // Call updatePointsEarned with the new total points
        updatePointsEarned(userId, 0, newTotalEarnedPoints); // Assuming no additional points are added
    }
}
