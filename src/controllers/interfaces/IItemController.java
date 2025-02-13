package controllers.interfaces;

public interface IItemController {

    String createItem(String name, int amount, double price);
    String getItemById(int id);
    String getAllItems(int choice);
    String addToCart(int userId, int itemId, int amount);
    String deleteItem(String name);
    String updateItem(String name, int newAmount, double newPrice);
    boolean buyItem(String name, int amount, int id);
    boolean addBalance(int id, double amount);
}
