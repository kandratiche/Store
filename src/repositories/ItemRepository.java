package repositories;

import data.interfaces.IDB;
import repositories.interfaces.IItemRepository;

import java.sql.*;
import java.util.ArrayList;

public class ItemRepository implements IItemRepository {
    private final IDB db;
    public ItemRepository(IDB db) {
        this.db = db;
    }

    public boolean createItem(Item item){
        Connection conn = null;
        try{
            conn = db.getConnection();
            String sql = "INSERT INTO items (itemName, amount, price) VALUES (?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1,item.getItemName());
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
                        rs.getString("itemName"),
                        rs.getInt("amount"),
                        rs.getDouble("price"));
            }

        } catch (SQLException e) {
            System.out.println("sql error getItem: " + e.getMessage());
        }
        return null;

    }

    @Override
    public List<Item> getAllItems(){
        Connection conn = null;

        try{
            conn = db.getConnection();
            String sql = "SELECT * FROM items";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            List<Item> items = new ArrayList<>();

            while(rs.next()){
                Item item = new Item(rs.getInt("id"),
                        rs.getString("itemName"),
                        rs.getInt("amount"),
                        rs.getDouble("price"));
                items.add(item);
            }
            return items;
        } catch (SQLException e){
            System.out.println("sql error getAllItems: " + e.getMessage());
        }
        return  null;
    }

}
