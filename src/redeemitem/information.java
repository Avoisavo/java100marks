/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redeemitem;

import java.util.Scanner;

public class information {

    public static int getUserId() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your ID: ");
        int userId = scanner.nextInt();
        return userId;
    }
}
