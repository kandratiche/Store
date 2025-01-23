package models;

public class Item {
    private int id;
    private String name;
    private int amount;
    private double price;

    public Item(){

    }

    public Item(String name, int amount, double price) {
        setItemName(name);
        setAmount(amount);
        setPrice(price);
    }

    public Item(int id, String name, int amount, double price) {
        this(name,amount,price);
        this.id = id;
    }

    public int getId() { return id; }

    public String getItemName() {
        return name;
    }

    public void setItemName(String itemName) {
        this.name = itemName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", Itemname='" + name + '\'' +
                ", amount='" + amount + '\'' +
                ", price=" + price +
                '}';
    }
}
