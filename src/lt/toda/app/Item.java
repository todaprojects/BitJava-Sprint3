package lt.toda.app;

public class Item {
    private String name;
    private double price;
    private int quantity;

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Item() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toFile() {
        return String.format("%s|%.2f|%d\n", name, price, quantity);
    }

    @Override
    public String toString() {
        return String.format("  %-20s |  %-20.2f |  %d ", name, price, quantity);
    }
}
