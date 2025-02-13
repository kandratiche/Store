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
        if(username == null || username.trim().isEmpty()){
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
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
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if(surname == null || surname.trim().isEmpty()){
            throw new IllegalArgumentException("Surname cannot be null or empty");
        }
        this.surname = surname;
    }
}
