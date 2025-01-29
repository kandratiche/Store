package repositories.interfaces;

import models.User;

public interface IUserRepository {
    boolean auth(String name, String surname, String password);
    boolean reg(User user);

}
