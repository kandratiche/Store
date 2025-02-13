package controllers;

import controllers.interfaces.IUserController;
import models.User;
import repositories.interfaces.IUserRepository;

public class UserController implements IUserController {

    private final IUserRepository repo;

    public UserController(IUserRepository repo) {
        this.repo = repo;
    }

    public User auth(String username, String password) {
        if (repo == null) {
            throw new IllegalStateException("Repository is not set.");
        }
        return repo.auth(username, password);
    }

    public String reg(String username, String password, String name, String surname, String role) {
        User user = new User(username, password, name, surname, role);
        boolean newUser = repo.reg(user);
        return (newUser) ? "User was registered" : "User wasn't registered";
    }


    public boolean addBalance(int id, double amount){
        boolean updated = repo.addBalance(id, amount);
        System.out.println((updated) ? "Success" : "Not Success");
        return (updated) ? true : false;
    }

}