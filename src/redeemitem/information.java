package redeemitem;

import java.util.Scanner;

public class information {

    public static int getUserId(Scanner scanner) {
        System.out.print("Please enter your ID: ");
        int userId = scanner.nextInt();
        return userId;
    }
}

