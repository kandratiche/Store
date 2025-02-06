package controllers;

import controllers.interfaces.IItemController;
import models.Item;
import repositories.interfaces.IItemRepository;

import java.util.List;

public class ItemController implements IItemController {

    private final IItemRepository repo;

    public ItemController(IItemRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createItem( String name, int amount, double price) {
        Item item = new Item(name, amount, price);

        boolean created = repo.createItem(item);

        return (created) ? "Item was created" : "Item creation failed";

    }

    @Override
    public String getItemById(int id) {
        Item item = repo.getItemById(id);
        return (item != null) ? item.toString() : "Item not found";
    }

    @Override
    public String getAllItems() {
        List<Item> items = repo.getAllItems();
        StringBuilder response = new StringBuilder();
        for (Item item : items) {
            response.append(item.toString()).append("\n");
        }
        return response.toString();
    }

    @Override
    public String addToCart(int userId, int itemId, int amount) {
        boolean added = repo.addToCart(userId, itemId, amount);
        return added ? "Item added to cart" : "Failed to add item to cart";
    }
    @Override
    public String deleteItem(String name) {
        Item item = repo.deleteItem(name);
        return (item != null) ? item.toString() : "Item not found";
    }
    @Override
    public String updateItem(String name, int newAmount, double newPrice) {
        boolean updated = repo.updateItem(name, newAmount, newPrice);
        return updated ? "Item updated successfully!" : "Failed to update item. Item with name '" + name + "' not found.";
    }
    @Override
    public String buyItem(String name, int quantity) {
        boolean success = repo.buyItem(name, quantity);
        return success ? "Purchase successful!" : "Purchase failed!";
    }
}
