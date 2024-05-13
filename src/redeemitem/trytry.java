import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class trytry {
    public static void main(String[] args) {
        // Get the absolute path of the file
        String filePath = Paths.get("src/data/customers.txt").toAbsolutePath().toString();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file at path: " + filePath + " Error: " + e.getMessage());
        }
    }
}

