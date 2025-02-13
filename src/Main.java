import controllers.ItemController;
import controllers.UserController;
import controllers.interfaces.IItemController;
import controllers.interfaces.IUserController;
import data.PostgreDB;
import data.interfaces.IDB;

import repositories.ItemRepository;
import repositories.UserRepository;
import repositories.interfaces.IItemRepository;
import repositories.interfaces.IUserRepository;
import services.ItemService;

public class Main {
    public static void main(String[] args) {
        IDB db = PostgreDB.getInstance("jdbc:postgresql://localhost:5432", "postgres", "1234", "Store");


        IUserRepository userRepository = new UserRepository(db);
        IItemRepository itemRepository = new ItemRepository(db);

        ItemService itemService = new ItemService(itemRepository);

        IItemController itemController = new ItemController(itemService);
        IUserController userController = new UserController(userRepository);

        MyApplication app = new MyApplication(itemController, userController);
        app.start();
        db.close();
    }
}
