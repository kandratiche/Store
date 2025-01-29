package controllers.interfaces;

public interface IUserController {

    boolean auth(String name, String surname, String password);

    String reg(String name, String surname, String password);
}
