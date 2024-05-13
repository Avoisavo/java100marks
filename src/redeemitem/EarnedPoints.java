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

    public static int fetchCustomerPoints(int userId) {
        int points = 0;
        String filePath = "C:\\Users\\ladym\\Documents\\GitHub\\java100marks\\src\\data\\customers.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean foundUserId = false;
            while ((line = br.readLine()) != null) {
                if (foundUserId) {
                    // Check if the line starts with "Total Points Earned: "
                    if (line.startsWith("Total Points Earned: ")) {
                        // Extract the numeric part after "Total Points Earned: "
                        points = Integer.parseInt(line.substring("Total Points Earned: ".length()));
                        break; // Exit the loop once points are found
                    }
                } else if (line.startsWith("User ID: " + userId)) {
                    foundUserId = true; // Mark that we've found the user ID
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return points;
    }

    public static void updateCustomerPoints(int userId, int additionalPoints) {
        Path filePath = Paths.get("C:\\Users\\ladym\\Documents\\GitHub\\java100marks\\src\\data\\customers.txt");
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            boolean pointsUpdated = false;
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.startsWith("User ID: " + userId)) {
                    String pointsLine = lines.get(i + 4);
                    if (pointsLine.startsWith("Total Points Earned: ")) {
                        int oldPoints = Integer.parseInt(pointsLine.substring("Total Points Earned: ".length()));
                        lines.set(i + 4, "Total Points Earned: " + (oldPoints + additionalPoints));
                        pointsUpdated = true;
                    }
                    // Update expiry date or add if not present
                    if (lines.size() > i + 5 && lines.get(i + 5).startsWith("Expiry Date: ")) {
                        lines.set(i + 5, "Expiry Date: " + LocalDate.now().plusDays(90).format(DateTimeFormatter.ISO_DATE));
                    } else {
                        lines.add(i + 5, "Expiry Date: " + LocalDate.now().plusDays(90).format(DateTimeFormatter.ISO_DATE));
                    }
                    break;
                }
            }
            if (!pointsUpdated) {
                lines.add("User ID: " + userId);
                lines.add("Total Points Earned: " + additionalPoints);
                lines.add("Expiry Date: " + LocalDate.now().plusDays(90).format(DateTimeFormatter.ISO_DATE));
            }

            Files.write(filePath, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void earnedPoint(int userId) {
        String filePath = "C:\\Users\\ladym\\Documents\\GitHub\\java100marks\\src\\data\\customers.txt ";
        int totalEarnedPoints = getTotalEarnedPoints(filePath, userId);
        if (totalEarnedPoints == -1) {
            System.out.println("User with ID " + userId + " not found.");
            return;
        }

        // Your logic here using totalEarnedPoints
    }

    private static int getTotalEarnedPoints(String filePath, int userId) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            Pattern pattern = Pattern.compile("User ID: " + userId + "\\n.+\\n.+\\nTotal Points Earned: (\\d+)");
            Matcher matcher = pattern.matcher(stringBuilder.toString());
            if (matcher.find()) {
                return Integer.parseInt(matcher.group(1));
            } else {
                return -1; // User not found
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return -1;
        }
    }

    public static void setTotalEarnedPoints(int userId, int newPoints) {
        Path filePath = Paths.get("C:\\Users\\ladym\\Documents\\GitHub\\java100marks\\src\\data\\customers.txt");
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            boolean pointsUpdated = false;
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.startsWith("User ID: " + userId)) {
                    // Find the line that contains the total points earned
                    for (int j = i + 3; j < lines.size(); j++) {
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
}
