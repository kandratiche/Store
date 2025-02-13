package controllers.interfaces;

import models.User;

public interface IUserController {

    User auth(String name, String password);

    String reg(String username, String password, String name, String surname, String role);

    boolean addBalance(int id, double amount);
}
