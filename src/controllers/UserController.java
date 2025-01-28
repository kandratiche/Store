package controllers;

import repositories.interfaces.IUserRepository;

public class UserController {

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
}
