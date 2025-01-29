package repositories.interfaces;

import models.User;

public interface IUserRepository {
    boolean auth(String name, String surname, String password);
    boolean reg(User user);

<<<<<<< HEAD
}
=======
}
>>>>>>> e24a5b5f689ea966da06eaaf5e02a855f918ca55
