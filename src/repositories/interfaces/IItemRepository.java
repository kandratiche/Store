package repositories.interfaces;

public interface IItemRepository {
    boolean createItem(Item item);
    Item getItemById(int id);
    List<Item> getAllItems();
}
