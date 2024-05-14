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
        String filePath = "C:\\Users\\ladym\\Documents\\GitHub\\java100marks\\src\\data\\customers.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean foundUserId = false;
            while ((line = br.readLine()) != null) {
                if (foundUserId) {
                    if (line.startsWith("Total Points Earned: ")) {
                        return Integer.parseInt(line.substring("Total Points Earned: ".length()));
                    }
                } else if (line.startsWith("User ID: " + userId)) {
                    foundUserId = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void updateCustomerPoints(int userId, int additionalPoints) {
        Path filePath = Paths.get("C:\\Users\\ladym\\Documents\\GitHub\\java100marks\\src\\data\\customers.txt");
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void earnedPoint(int userId) {
        String filePath = "C:\\Users\\ladym\\Documents\\GitHub\\java100marks\\src\\data\\customers.txt";
        int totalEarnedPoints = fetchCustomerPoints(userId);
        if (totalEarnedPoints == 0) {
            System.out.println("User with ID " + userId + " not found.");
            return;
        }

        System.out.println("Total Points Earned for user " + userId + ": " + totalEarnedPoints);
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
                    // Assuming the next line after User ID is the User Name
                    // You might need to adjust this logic based on your actual data structure
                    for (int j = i + 1; j < lines.size(); j++) {
                        if (lines.get(j).startsWith("User Name: ")) {
                            // Now we know we're within the user's block, let's find the Total Points Earned line
                            for (int k = j + 1; k < lines.size(); k++) {
                                if (lines.get(k).startsWith("Total Points Earned: ")) {
                                    lines.set(k, "Total Points Earned: " + newPoints);
                                    pointsUpdated = true;
                                    break;
                                }
                            }
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
