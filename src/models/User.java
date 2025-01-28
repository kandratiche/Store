package models;

public class User {
    private int id;
    private String name;
    private String surname;
    private String password;

    public User(){

    }

    public User(String name, String surname, String password) {
        setName(name);
        setSurname(surname);
        setPassword(password);
    }

    public User(int id, String name, String surname, String password) {
        this(name, surname, password);
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
