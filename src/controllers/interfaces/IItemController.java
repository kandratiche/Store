package controllers.interfaces;

public interface IItemController {

    String createItem(String name, int amount, double price);
    String getItemById(int id);
    String getAllItems();
    String addToCart(int userId, int itemId, int amount);
    String deleteItem(String name);
    String updateItem(String name, int newAmount, double newPrice);
    String buyItem(String name, int amount);
}
