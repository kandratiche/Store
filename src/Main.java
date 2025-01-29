import controllers.ItemController;
import controllers.UserController;
import controllers.interfaces.IItemController;
import controllers.interfaces.IUserController;
import data.PostgreDB;
import data.interfaces.IDB;
import models.User;
import repositories.ItemRepository;
import repositories.UserRepository;
import repositories.interfaces.IItemRepository;
import repositories.interfaces.IUserRepository;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgreDB("jdbc:postgresql://localhost:5423", "postgres", "1234", "Store");

        IUserRepository userRepository = new UserRepository(db);
        IItemRepository itemRepository = new ItemRepository(db);

        IItemController itemController = new ItemController(itemRepository);
        IUserController userController = new UserController(userRepository);

        MyApplication app = new MyApplication(itemController, userController);
        app.start();

        db.close();
    }
}