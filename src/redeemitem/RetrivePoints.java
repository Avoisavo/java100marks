package redeemitem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


// please refer to updated UML diagram or group leader regarding the use of this class
public class RetrivePoints {
  // please use relative file paths instead of absolute file paths
    private static final String POINTS_FILE_PATH = "C:\\Users\\Jason Paw\\OneDrive - student.tarc.edu.my\\Documents\\GitHub\\java100marks\\src\\data\\points_earned.txt";

    public static int getTotalPointsEarned(int userId) {
        int totalPoints = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(POINTS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("User ID: " + userId)) { // Check if the line matches the user ID
                    // Skip to Total Points Earned line
                    reader.readLine(); // Skip Earned Points Date
                    reader.readLine(); // Skip Expiry Date
                    String pointsLine = reader.readLine(); // Read Total Points Earned
                    totalPoints += Integer.parseInt(pointsLine.substring("Total Points Earned: ".length()));
                }
            }
        } catch (IOException e) {
            System.err.println("Error occurred while reading points earned from file: " + e.getMessage());
        }
        return totalPoints;
    }

}
