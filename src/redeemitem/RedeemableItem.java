package redeemitem;

public class RedeemableItem {

    private String name;
    private int cost;
    private int quantity;

    public RedeemableItem(String name, int cost, int quantity) {
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
    }

    public String getItemName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getQuantity() {
        return cost;
    }

}
