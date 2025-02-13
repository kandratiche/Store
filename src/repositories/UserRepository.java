
package repositories;

import data.interfaces.IDB;
import models.User;
import repositories.interfaces.IUserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements IUserRepository {

    private final IDB db;

    public UserRepository(IDB db) {
        this.db = db;
    }

    @Override
    public User auth(String username, String password) {
        Connection conn = null;
        User user = null;

        try {
            conn = db.getConnection();
            String sql = "SELECT id, role FROM users WHERE username = ? AND password = ?";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, username);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();

            if(rs.next()){
                int id = rs.getInt("id");
                String role = rs.getString("role");
                user = new User(id, role);
            };

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public boolean reg(User user) {
        Connection conn = null;

        try {
            conn = db.getConnection();
            String sql = "INSERT INTO users(username, password, name, surname, role) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            st.setString(3, user.getName());
            st.setString(4, user.getSurname());
            st.setString(5, user.getRole());
            st.execute();
            
            return true;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return false;
    }


    public boolean addBalance(int id, double balance){
        Connection conn = null;

        try{
            conn = db.getConnection();

            String sql = "UPDATE users SET balance = ? WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setDouble(1, balance);
            st.setInt(2, id);

            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }

        return false;

    }

}
