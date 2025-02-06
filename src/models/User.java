package models;

public class User {
    private int id;
    private String username;
    private String password;
    private String name;
    private String surname;

    public User(){

    }

    public User(String username, String password, String name, String surname) {
        setUsername(username);
        setPassword(password);
        setName(name);
        setSurname(surname);
    }

    public User(int id, String username, String password, String name, String surname) {
        this(username, password, name, surname);
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
