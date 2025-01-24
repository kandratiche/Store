package repositories.interfaces;

import models.Item;

import java.util.List;

public interface IItemRepository {
    boolean createItem(String name, int amount, double price);

    boolean createItem(Item item);
    Item getItemById(int id);
    List<Item> getAllItems();
    void deleteItem(String name);

    void deleteProductByName(String name);
}
