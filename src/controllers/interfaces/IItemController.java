package controllers.interfaces;
public interface IItemController {

    String createItem(String name, int amount, double price);
    String getItemById(int id);
    String getAllItems();

}
