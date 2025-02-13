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
        if(itemName == null || itemName.trim().isEmpty()){
            throw new IllegalArgumentException("Item name cannot be null or empty");
        }
        this.name = itemName;
    }

    public int getAmount() {
        if(amount <= 0){
            throw new IllegalArgumentException("Amount cannot be less than 0 or equal to 0");
        }
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(price < 0){
            throw new IllegalArgumentException("Price cannot be equal to 0");
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", Name='" + name + '\'' +
                ", Quantity='" + amount + '\'' +
                ", Price=" + price;
    }
}
