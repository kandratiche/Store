package repositories.interfaces;

import models.User;

public interface IUserRepository {
    boolean auth(String username, String password);

    boolean reg(User user);

}
