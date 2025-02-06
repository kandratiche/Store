package repositories.interfaces;

import models.User;

public interface IUserRepository {
    Integer auth(String username, String password);

    boolean reg(User user);

}
