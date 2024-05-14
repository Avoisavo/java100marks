package redeemitem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EarnedPoints {

    static final String FILE_PATH = "src/redeemitem/customers.txt";

    // Fetch customer points
    public static int fetchCustomerPoints(int loggedInUserId) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean foundUserId = false;
            while ((line = br.readLine()) != null) {
                if (foundUserId) {
                    if (line.startsWith("Total Points Earned: ")) {
                        return Integer.parseInt(line.substring("Total Points Earned: ".length()));
                    }
                } else if (line.startsWith("User ID: " + loggedInUserId)) {
                    foundUserId = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public static void updateCustomerPoints(int userId, int additionalPoints) {
        Path filePath = Paths.get(FILE_PATH);
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            boolean userFound = false;
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.startsWith("User ID: " + userId)) {
                    userFound = true;
                    // Look for the Total Points Earned line
                    for (int j = i + 1; j < lines.size(); j++) {
                        if (lines.get(j).startsWith("Total Points Earned: ")) {
                            int oldPoints = Integer.parseInt(lines.get(j).substring("Total Points Earned: ".length()));
                            int newPoints = oldPoints + additionalPoints;
                            lines.set(j, "Total Points Earned: " + newPoints);

                            // Update expiry date or add if not present
                            if (j + 1 < lines.size() && lines.get(j + 1).startsWith("Expiry Date: ")) {
                                lines.set(j + 1, "Expiry Date: " + LocalDate.now().plusDays(90).format(DateTimeFormatter.ISO_DATE));
                            } else {
                                lines.add(j + 1, "Expiry Date: " + LocalDate.now().plusDays(90).format(DateTimeFormatter.ISO_DATE));
                            }
                            break;
                        }
                    }
                    break;
                }
            }
            if (!userFound) {
                lines.add("User ID: " + userId);
                lines.add("Total Points Earned: " + additionalPoints);
                lines.add("Expiry Date: " + LocalDate.now().plusDays(90).format(DateTimeFormatter.ISO_DATE));
            }
            Files.write(filePath, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to update loyalty status in the file    
    public static void setTotalEarnedPoints(int userId, int newPoints) {
        Path filePath = Paths.get(FILE_PATH);
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            boolean pointsUpdated = false;
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.startsWith("User ID: " + userId)) {
                    for (int j = i + 1; j < lines.size(); j++) {
                        if (lines.get(j).startsWith("Total Points Earned: ")) {
                            lines.set(j, "Total Points Earned: " + newPoints);
                            pointsUpdated = true;
                            break;
                        }
                    }
                    break;
                }
            }
            if (!pointsUpdated) {
                System.out.println("User with ID " + userId + " not found.");
            } else {
                Files.write(filePath, lines, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getLoyaltyStatus(List<String> lines, int userIndex) {
        for (int i = userIndex; i < lines.size(); i++) {
            if (lines.get(i).startsWith("Loyalty Status: ")) {
                return lines.get(i).substring("Loyalty Status: ".length());
            }
        }
        return "Classic";
    }

    private static void updateLoyaltyStatus(int loggedInUserId, int newPoints, List<String> lines, int userIndex) {
        String newStatus;
        if (newPoints > 2000) {
            newStatus = "Gold";
        } else if (newPoints > 500) {
            newStatus = "Silver";
        } else {
            newStatus = "Classic";
        }

        for (int i = userIndex; i < lines.size(); i++) {
            if (lines.get(i).startsWith("Loyalty Status: ")) {
                lines.set(i, "Loyalty Status: " + newStatus);
                System.out.println("Updated loyalty status for user " + loggedInUserId + ": " + newStatus);
                return;
            }
        }

        // If loyalty status line is not found, add it
        lines.add(userIndex + 1, "Loyalty Status: " + newStatus);
        System.out.println("Added loyalty status for user " + loggedInUserId + ": " + newStatus);
    }
}



