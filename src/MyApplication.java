import controllers.interfaces.IItemController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final IItemController controller;
    private final Scanner scanner = new Scanner(System.in);
    public MyApplication(IItemController controller) { this.controller = controller; }

    public void start() {
        while(true){
            mainMenu();
            try{
                int option = scanner.nextInt();
                switch (option) {
                    case 1: getAllItemsMenu(); break;
                    case 2: getItemByIdMenu(); break;
                    case 3: createItemMenu(); break;
                    default: return;
                }
            } catch (InputMismatchException e){
                System.out.println("Input must be number: " + e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void createItemMenu() {
        System.out.println("Enter item name: ");
        scanner.nextLine();
        String itemName = scanner.nextLine();

        System.out.println("Enter item amount: ");
        int amount = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter item price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        String response = controller.createItem(itemName, amount, price);
        System.out.println(response);
    }

    private void getItemByIdMenu() {
        System.out.println("Enter item id: ");
        int id = scanner.nextInt();
        String response = controller.getItemById(id);
        System.out.println(response);
    }

    private void getAllItemsMenu() {
        String response = controller.getAllItems();
        System.out.println(response);
    }

    private void mainMenu() {
        System.out.println();
        System.out.println("!!!Store!!!");
        System.out.println("Select an option: ");
        System.out.println("1. Get All Item");
        System.out.println("2. Get Item By Id");
        System.out.println("3. Create a new Item");
        System.out.println("0. Exit");
        System.out.println("Enter your choice: ");
    }

}
