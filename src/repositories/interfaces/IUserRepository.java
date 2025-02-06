package repositories.interfaces;

import models.User;

public interface IUserRepository {
    boolean auth(String name, String surname);

    boolean reg(User user);

}
