package repositories.interfaces;

import models.Item;

import java.util.List;

public interface IItemRepository {
    boolean createItem(String name, int amount, double price);

    boolean createItem(Item item);
    Item getItemById(int id);
    List<Item> getAllItems();

    boolean addToCart(int userId, int itemId, int amount);

    Item deleteItem(String name);
    boolean updateItem(String name, int newAmount, double newPrice);
    boolean buyItem(String name, int amount);
}
