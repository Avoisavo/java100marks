package redeemitem;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// please refer to updated UML diagram or group leader regarding the use of this class
public class SavePoints {

    private static final String POINTS_FILE_PATH = "/Users/avo/Documents/GitHub/java100marks/src/data/points_earned.txt";

  // please format your code!!!
public static void savePointsEarned(int userId, LocalDate earningDate, int totalPointsEarned) {
    try (FileWriter writer = new FileWriter(POINTS_FILE_PATH,true)) {
        writer.write("User ID: " + userId + "\n");
        writer.write("Earned Points Date: " + userId + "\n");
        writer.write("Expiry Date: " + userId + "\n");
        writer.write("Total Points Earned: " + totalPointsEarned + "\n\n");
        
        writer.flush();

        System.out.println("Points earned saved to file successfully.");
    } catch (IOException e) {
        System.err.println("Error occurred while saving points earned to file: " + e.getMessage());
        e.printStackTrace(); 
    }
}

}

