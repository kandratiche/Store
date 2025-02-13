package services;

import models.Item;
import repositories.interfaces.IItemRepository;
import java.util.List;

public class ItemService {
    private final IItemRepository itemRepository;

    public ItemService(IItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public boolean createItem(String name, int amount, double price) {
        return itemRepository.createItem(new Item(name, amount, price));
    }

    public Item getItemById(int id) {
        return itemRepository.getItemById(id);
    }

    public List<Item> getAllItems() {
        return itemRepository.getAllItems();
    }

    public boolean updateItem(String name, int newAmount, double newPrice) {
        return itemRepository.updateItem(name, newAmount, newPrice);
    }

    public boolean deleteItem(String name) {
        return itemRepository.deleteItem(name) != null;
    }

    public boolean addToCart(int userId, int itemId, int amount) {
        return itemRepository.addToCart(userId, itemId, amount);
    }

    public boolean buyItem(String name, int amount, int id) {
        return itemRepository.buyItem(name, amount, id);
    }
}
