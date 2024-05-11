/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redeemitem;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Product> selectedProducts;

    public ShoppingCart() {
        this.selectedProducts = new ArrayList<>();
    }

    public void addToCart(Product product) {
        selectedProducts.add(product);
    }

    public List<Product> getSelectedProducts() {
        return selectedProducts;
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Product product : selectedProducts) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    public int calculateTotalPointsEarned() {
        int totalPointsEarned = 0;
        for (Product product : selectedProducts) {
            totalPointsEarned += product.getPointsEarned();
        }
        return totalPointsEarned;
    }
}
