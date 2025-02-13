package repositories;

import data.interfaces.IDB;
import models.Item;
import repositories.interfaces.IItemRepository;

import javax.xml.transform.Result;
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
            System.out.println("Error to createItem Repository: " + e.getMessage());
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
            System.out.println("Error to getItemById Repository: " + e.getMessage());
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
            System.out.println("Error to getAllItems Repository: " + e.getMessage());
        }

        return  null;
    }
    @Override
    public boolean addToCart(int userId, int itemId, int amount) {
        String checkSql = "SELECT amount FROM items WHERE id = ?";
        String getCartSql = "SELECT cart FROM users WHERE id = ?";
        String updateCartSql = "UPDATE users SET cart = ? WHERE id = ?";

        try (Connection conn = db.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setInt(1, itemId);
                ResultSet rs = checkStmt.executeQuery();
                if (!rs.next() || rs.getInt("amount") < amount) {
                    System.out.println("Not enough stock or item does not exist.");
                    conn.rollback();
                    return false;
                }
            }

            String cart = "";
            try (PreparedStatement getCartStmt = conn.prepareStatement(getCartSql)) {
                getCartStmt.setInt(1, userId);
                ResultSet rs = getCartStmt.executeQuery();
                if (rs.next()) {
                    cart = rs.getString("cart");
                }
            }

            if (cart == null || cart.isEmpty()) {
                cart = "Item Id:" + itemId + ", Amount: " + amount;
                System.out.println(cart);
            } else {
                cart += ", Item Id:" + itemId + ", Amount:" + amount;
            }
            try (PreparedStatement updateStmt = conn.prepareStatement(updateCartSql)) {
                updateStmt.setString(1, cart);
                updateStmt.setInt(2, userId);
                updateStmt.executeUpdate();
                conn.commit();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return true;
        } catch (SQLException e) {
            System.out.println("SQL Error in addToCart: " + e.getMessage());
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
    @Override
    public boolean updateItem(String name, int newAmount, double newPrice) {
        try (Connection conn = db.getConnection()) {
            String sql = "UPDATE items SET amount = ?, price = ? WHERE name = ?";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, newAmount);
            st.setDouble(2, newPrice);
            st.setString(3, name);

            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("SQL Error in updateItem: " + e.getMessage());
        }
        return false;
    }
    @Override
    public boolean buyItem(String name, int amount, int id) {
        Connection conn = null;
        PreparedStatement checkSt = null;
        PreparedStatement updateSt = null;
        PreparedStatement balanceSt = null;
        PreparedStatement deductBalanceSt = null;
        PreparedStatement addInventorySt = null;
        ResultSet rs = null;
        String inv = "";

        try {
            conn = db.getConnection();
            if (conn == null) {
                System.out.println("Database connection failed!");
                return false;
            }

            String checkSql = "SELECT amount, price FROM items WHERE name = ?";
            checkSt = conn.prepareStatement(checkSql);
            checkSt.setString(1, name);
            rs = checkSt.executeQuery();

            if (rs.next()) {
                int currentAmount = rs.getInt("amount");
                System.out.println("Current Amount is: " + currentAmount);
                double price = rs.getDouble("price");
                System.out.println("Current Price is: " + price);

                if (currentAmount >= amount) {
                    double totalCost = price * amount;

                    String balanceSql = "SELECT balance FROM users WHERE id = ?";
                    balanceSt = conn.prepareStatement(balanceSql);
                    balanceSt.setInt(1, id);
                    ResultSet balanceRs = balanceSt.executeQuery();

                    if (balanceRs.next()) {
                        double balance = balanceRs.getDouble("balance");
                        if (balance >= totalCost) {
                            String removeItems = "UPDATE items SET amount = amount - ? WHERE name = ?";
                            updateSt = conn.prepareStatement(removeItems);
                            updateSt.setInt(1, amount);
                            updateSt.setString(2, name);
                            int rowsUpdated = updateSt.executeUpdate();

                            String removeBalance = "UPDATE users SET balance = balance - ? WHERE id = ?";
                            deductBalanceSt = conn.prepareStatement(removeBalance);
                            deductBalanceSt.setDouble(1, totalCost);
                            deductBalanceSt.setInt(2, id);
                            deductBalanceSt.executeUpdate();

                            String selectInv = "SELECT inventory FROM users WHERE id = ?";
                            String updateInv = "UPDATE users SET inventory = ? WHERE id = ?";

                            PreparedStatement selectInvSt = conn.prepareStatement(selectInv);
                            selectInvSt.setInt(1, id);
                            ResultSet invRs = selectInvSt.executeQuery();

                            if (invRs.next()) {
                                inv = invRs.getString("inventory");
                            }

                            if (inv == null || inv.isEmpty()) {
                                inv = "Item Name: " + name + "Amount: " + amount;
                                System.out.println(inv);
                            } else {
                                inv += ", Item Name: " + name + "Amount: " + amount;
                            }

                            try(PreparedStatement updateInvSt = conn.prepareStatement(updateInv)){
                                updateInvSt.setString(1, inv);
                                updateInvSt.setInt(2, id);
                                updateInvSt.executeUpdate();
                                conn.commit();
                            }
                            catch (SQLException e) {
                                System.out.println(e.getMessage());
                            }

                        } else {
                            System.out.println("Insufficient balance.");
                        }
                    }
                } else {
                    System.out.println("Not enough stock for " + name);
                }
            } else {
                System.out.println("Item not found: " + name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (checkSt != null) checkSt.close();
                if (updateSt != null) updateSt.close();
                if (balanceSt != null) balanceSt.close();
                if (deductBalanceSt != null) deductBalanceSt.close();
                if (addInventorySt != null) addInventorySt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}