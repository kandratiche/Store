package controllers;

import controllers.interfaces.IUserController;
import models.User;
import repositories.interfaces.IUserRepository;

public class UserController implements IUserController {

    private final IUserRepository repo;

    public UserController(IUserRepository repo) {
        this.repo = repo;
    }

    public boolean auth(String username, String password) {
        if (repo == null) {
            throw new IllegalStateException("Repository is not set.");
        }
        return repo.auth(username, password);
    }

    public String reg(String username, String password, String name, String surname) {
        User user = new User(username, password, name, surname);
        boolean newUser = repo.reg(user);
        return (newUser) ? "User was registered" : "User wasn't registered";
    }
}