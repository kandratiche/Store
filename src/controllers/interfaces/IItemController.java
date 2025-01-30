package controllers.interfaces;
public interface IItemController {

    String createItem(String name, int amount, double price);
    String getItemById(int id);
    String getAllItems();
    String addToCart(int id, int amount);
    String deleteItem(String name);
}
