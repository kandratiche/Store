package models;

public class User {
    private int id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String role;

    public User(){

    }

    public User(String username, String password, String name, String surname, String role) {
        setUsername(username);
        setPassword(password);
        setName(name);
        setSurname(surname);
        setRole(role);
    }

    public User(int id, String username, String password, String name, String surname, String role) {
        this(username, password, name, surname, role);
        this.id = id;
    }

    public User(int id, String role){
        this.id = id;
        this.role = role;
    }


    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
