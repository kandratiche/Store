package controllers;

import controllers.interfaces.IUserController;
import models.User;
import repositories.interfaces.IUserRepository;

public class UserController implements IUserController {

    private final IUserRepository repo;

    public UserController(IUserRepository repo) {
        this.repo = repo;
    }

    public boolean auth(String name, String surname, String password) {
        if (repo == null) {
            throw new IllegalStateException("Repository is not set.");
        }
        return repo.auth(name, surname, password);
    }
    public String reg(String name, String surname, String password) {
        User user = new User(name, surname, password);

        boolean newUser = repo.reg(user);
        return (newUser) ? "User was registered" : "User wasn't registered";
    }
}
