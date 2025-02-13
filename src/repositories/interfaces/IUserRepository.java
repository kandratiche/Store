package repositories.interfaces;

import models.User;

public interface IUserRepository {
    User auth(String username, String password);

    boolean reg(User user);

    boolean addBalance(int id, double amount);

}
