package controllers;

import controllers.interfaces.IItemController;
import data.PostgreDB;
import models.Item;
import services.ItemService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ItemController implements IItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @Override
    public String createItem( String name, int amount, double price) {
        Item item = new Item(name, amount, price);

        boolean created = itemService.createItem(item.getItemName(), item.getAmount(), item.getPrice());


        return (created) ? "Item was created" : "Item creation failed";

    }

    @Override
    public String getItemById(int id) {
        Item item = itemService.getItemById(id);
        return (item != null) ? item.toString() : "Item not found";
    }

    @Override
    public String getAllItems() {
        List<Item> items = itemService.getAllItems();
        StringBuilder response = new StringBuilder();
        for (Item item : items) {
            response.append(item.toString()).append("\n");
        }
        return response.toString();
    }

    @Override
    public String addToCart(int userId, int itemId, int amount) {
        boolean added = itemService.addToCart(userId, itemId, amount);
        return added ? "Item added to cart" : "Failed to add item to cart";
    }
    @Override
    public String deleteItem(String name) {
        boolean deleted = itemService.deleteItem(name);
        return deleted ? "Item deleted" : "Item not found";
    }

    @Override
    public String updateItem(String name, int newAmount, double newPrice) {
        return "";
    }

    @Override
    public boolean buyItem(String name, int amount, int id) {
        return itemService.buyItem(name, amount, id);
    }
    public boolean addBalance(int id, double amount) {
        Connection conn = null;
        PreparedStatement updateSt = null;

        try {
            PostgreDB db = null;
            conn = db.getConnection();
            if (conn == null) {
                System.out.println("Database connection failed!");
                return false;
            }

            // Update user balance
            String updateSql = "UPDATE users SET balance = balance + ? WHERE id = ?";
            updateSt = conn.prepareStatement(updateSql);
            updateSt.setDouble(1, amount);
            updateSt.setInt(2, id);
            int rowsUpdated = updateSt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Balance updated successfully.");
                return true;
            } else {
                System.out.println("Failed to update balance. User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (updateSt != null) updateSt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
