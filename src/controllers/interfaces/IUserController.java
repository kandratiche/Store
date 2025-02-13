package controllers.interfaces;

public interface IUserController {

    Integer auth(String name, String password);

    String reg(String username, String password, String name, String surname);

    boolean addBalance(int id, double amount);
}
