/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redeemitem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Product {

    private double price;
    private int pointsEarned;

    public Product(double price, int pointsEarned) {
        this.price = price;
        this.pointsEarned = pointsEarned;
    }

    public double getPrice() {
        return price;
    }

    public int getPointsEarned() {
        return pointsEarned;
    }

    public static List<Product> getAvailableProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(10.50, 10)); // Example userId
        products.add(new Product(20.99, 20)); // Example userId
        products.add(new Product(15.99, 15)); // Example userId
        return products;
    }

    public static void displayAvailableProducts() {
        List<Product> products = getAvailableProducts();
        System.out.println("Available Items:");
        System.out.println("-------------------------------------------------");
        System.out.println("S.No\tItem\t\tPrice\tPoints Earned");
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println((i + 1) + "\t" + "\tRM" + product.getPrice() + "\t" + product.getPointsEarned());
        }
        System.out.println("-------------------------------------------------");
    }

    public static List<Product> selectProducts() {
        List<Product> products = getAvailableProducts();
        List<Product> selectedProducts = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean continueAdding = true;

        while (continueAdding) {
            displayAvailableProducts();
            System.out.print("Enter the number of the item you want to purchase or 0 to finish: ");
            int selectedItem = scanner.nextInt();

            if (selectedItem >= 1 && selectedItem <= products.size()) {
                selectedProducts.add(products.get(selectedItem - 1));
                System.out.println("Item added to cart.");
            } else if (selectedItem == 0) {
                continueAdding = false;
            } else {
                System.out.println("Invalid selection.");
            }
        }
        return selectedProducts;
    }
}
