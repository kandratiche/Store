package repositories.interfaces;

public interface IUserRepository {
    boolean auth(String name, String surname, String password);
}
