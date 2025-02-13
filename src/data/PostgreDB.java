package data;

import data.interfaces.IDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgreDB implements IDB {
    private static PostgreDB instance;
    private Connection connection;

    private String host;
    private String username;
    private String password;
    private String dbName;

    public PostgreDB(String host, String username, String password, String dbName) {
        setHost(host);
        setUsername(username);
        setPassword(password);
        setDbName(dbName);
    }

    public static PostgreDB getInstance(String host, String username, String password, String dbName) {
        if(instance == null) {
            synchronized (PostgreDB.class) {
                if (instance == null) {
                    instance = new PostgreDB(host, username, password, dbName);
                }
            }
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        String connectionUrl = host + "/" + dbName;
        try{
            if(connection == null || connection.isClosed()) {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(connectionUrl, username, password);
            }
        }
        catch(Exception e){
            System.out.println("Failed to connect to database: " + e.getMessage());
        }
        return connection;
    }

    @Override
    public void close() {
        if(connection != null) {
            try{
                connection.close();
                connection = null;
            } catch (Exception e) {
                System.out.println("Failed to close connection: " + e.getMessage());
            }
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }



}
