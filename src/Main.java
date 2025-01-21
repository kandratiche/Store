import data.PostgreDB;
import data.interfaces.IDB;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgreDB("jdbc:postgresql://localhost:5432", "postgres", "1234", "Store");

    }
}