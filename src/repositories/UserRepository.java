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
    public boolean auth(String name, String surname, String password) {
        Connection conn = null;

        try {
            conn = db.getConnection();
            String sql = "SELECT * FROM managers WHERE name = ? AND surname = ? AND password = ?";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, name);
            st.setString(2, surname);
            st.setString(3, password);

            ResultSet rs = st.executeQuery();

            return rs.next();

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
        return false;
    }

    @Override
    public boolean reg(User user) {
        Connection conn = null;

        try {
            conn = db.getConnection();
            String sql = "INSERT INTO managers(name, surname, password) VALUES(?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, user.getName());
            st.setString(2, user.getSurname());
            st.setString(3, user.getPassword());
            st.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return false;
    }
}
