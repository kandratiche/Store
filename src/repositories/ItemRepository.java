package repositories;

import data.interfaces.IDB;
import models.Item;
import repositories.interfaces.IItemRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemRepository implements IItemRepository {
    private final IDB db;
    public ItemRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createItem(String name, int amount, double price){
        return createItem(new Item(name, amount, price));
    }

    @Override
    public boolean createItem(Item item) {

        Connection conn = null;
        try {
            conn = db.getConnection();
            String sql = "INSERT INTO items (name, amount, price) VALUES (?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, item.getItemName());
            st.setInt(2, item.getAmount());
            st.setDouble(3, item.getPrice());
            st.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public Item getItemById(int id){
        Connection conn = null;

        try{
            conn = db.getConnection();
            String sql = "SELECT * FROM items WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            if(rs.next()){
                return new Item(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("amount"),
                        rs.getDouble("price"));
            }

        } catch (SQLException e) {
            System.out.println("sql error getItem: " + e.getMessage());
        }
        return null;

    }

    @Override
    public List<Item> getAllItems() {
        Connection conn = null;

        try{
            conn = db.getConnection();
            String sql = "SELECT * FROM items";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            List<Item> items = new ArrayList<>();

            while(rs.next()){
                Item item = new Item(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("amount"),
                        rs.getDouble("price"));
                items.add(item);
            }
            return items;
        } catch (SQLException e){
            System.out.println("sql error getAllItems: " + e.getMessage());
        }
        System.out.println("1. Add all items to cart.");

        return  null;
    }
    @Override
    public boolean addToCart(int productId, int quantity) {
        String sql = "INSERT INTO cart (product_id, quantity) VALUES (?, ?)";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            stmt.setInt(2, quantity);
            int rowsInserted = stmt.executeUpdate();

            System.out.println("Item added to cart");

        } catch (Exception e) {
            System.err.println("Error adding to cart: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Item deleteItem(String name) {
        String sql = "DELETE FROM items WHERE name = ?";
        try {
            Connection conn = db.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, name);
            int rowsDeleted = st.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Product with name \"" + name + "\" deleted successfully.");
            } else {
                System.out.println("No product found with the name \"" + name + "\".");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
