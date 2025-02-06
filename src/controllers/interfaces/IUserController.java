package controllers.interfaces;

public interface IUserController {

    boolean auth(String name, String surname);

    String reg(String username, String password, String name, String surname);
}
