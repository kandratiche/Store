import controllers.UserController;
import controllers.interfaces.IItemController;
import controllers.interfaces.IUserController;

import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final IItemController itemController;
    private final IUserController userController;
    private final Scanner scanner = new Scanner(System.in);
    public MyApplication(IItemController itemController, IUserController userController) {
        this.itemController = itemController;
        this.userController = userController;
    }


    public void start() {
        while(true){
            choiceMenu();
        }
    }

    private void auth(){
        scanner.nextLine();
        System.out.println("Enter the Name: ");
        String name = scanner.nextLine();

        System.out.println("Enter the Surname: ");
        String surname = scanner.nextLine();

        System.out.println("Enter the Password: ");
        String password = scanner.nextLine();

        boolean isAuthenticated = userController.auth(name, surname,password);

        if(isAuthenticated){
            mainMenuForAdmin();
            try{
                int option = scanner.nextInt();
                switch (option) {
                    case 1: createItemMenu(); break;
                    case 2: getItemByIdMenu(); break;
                    case 3: deleteItemMenu(); break;
                    case 4: updateItemMenu(); break;
                    default: return;
                }
            } catch (InputMismatchException e){
                System.out.println("Input must be number: " + e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Authentication failed.");
            mainMenuForCustomer();
            try{
                int option = scanner.nextInt();
                switch (option) {
                    case 1: getAllItemsMenu(); break;
                    case 2: getItemByIdMenu(); break;
                    case 3: auth(); break;
                    default: return;
                }
            } catch (InputMismatchException e){
                System.out.println("Input must be number: " + e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void choiceMenu(){
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("Choose an option: ");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                reg();
                break;
            case 2:
                auth();
                break;
        }
    }

    private void reg(){
        scanner.nextLine();
        System.out.println("***Register***\nEnter the name: ");
        String name = scanner.nextLine();

        System.out.println("***Register***\nEnter the surname: ");
        String surname = scanner.nextLine();

        System.out.println("***Register***\nEnter the password: ");
        String password = scanner.nextLine();

        boolean isExist = userController.auth(name, surname, password);
        if(isExist) {
            System.out.println("User already exists.");
            choiceMenu();
        }
        else{
            String response = userController.reg(name, surname, password);
            System.out.println(response);
            auth();
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

        String response = itemController.createItem(itemName, amount, price);
        System.out.println(response);
    }

    private void getItemByIdMenu() {
        System.out.println("Enter item id: ");
        int id = scanner.nextInt();
        String response = itemController.getItemById(id);
        System.out.println(response);
    }

    private void getAllItemsMenu() {
        String response = itemController.getAllItems();
        System.out.println(response);
    }

    private void mainMenuForCustomer() {
        System.out.println();
        System.out.println("!!!Store!!!");
        System.out.println("Select an option: ");
        System.out.println("1. Get All Item");
        System.out.println("2. Get Item By Id");
        System.out.println("3. Enter on Admin Menu");
        System.out.println("0. Exit");
        System.out.println("Enter your choice: ");
    }

    private void mainMenuForAdmin() {
        System.out.println();
        System.out.println("Admin Menu");
        System.out.println("Select an option: ");
        System.out.println("1. Add Item");
        System.out.println("2. Get item by Id");
        System.out.println("3. Delete Item");
        System.out.println("4. Update Item");
        System.out.println("0. Exit");
        System.out.println("Enter your choice: ");
    }
    private void deleteItemMenu() {
        System.out.println("Enter item name: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        String response = itemController.deleteItem(name);
        System.out.println(response);
    }
    private void updateItemMenu() {
        System.out.println("Enter item name to update: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        System.out.println("Enter new item amount: ");
        int newAmount = scanner.nextInt();

        System.out.println("Enter new item price: ");
        double newPrice = scanner.nextDouble();

        String response = itemController.updateItem(name, newAmount, newPrice);
        System.out.println(response);
    }

}