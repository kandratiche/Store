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
        // Set up database connection
        IDB db = new PostgreDB("jdbc:postgresql://localhost:5432", "postgres", "1234", "Store");

        // Initialize the repositories
        IUserRepository userRepository = new UserRepository(db);
        IItemRepository itemRepository = new ItemRepository(db);

        // Initialize the controllers
        IItemController itemController = new ItemController(itemRepository);
        IUserController userController = new IUserController(userRepository);

        // Initialize MyApplication and pass controllers
        MyApplication app = new MyApplication(itemController, userController);
        app.start();

        // Close database connection
        db.close();
    }
}