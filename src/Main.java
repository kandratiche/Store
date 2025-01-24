import controllers.ItemController;
import controllers.interfaces.IItemController;
import data.PostgreDB;
import data.interfaces.IDB;
import repositories.ItemRepository;
import repositories.interfaces.IItemRepository;
public class Main {
    public static void main(String[] args) {
        IDB db = new PostgreDB("jdbc:postgresql://localhost:5432", "postgres", "1234", "Store");
        IItemRepository repo = new ItemRepository(db);
        IItemController controller = new ItemController(repo);
        MyApplication app = new MyApplication(controller);
        app.start();
        db.close();
    }
}