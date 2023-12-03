package Program;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LaptopCatalog catalog = LaptopCatalog.generateCatalog();
        String command;
        do {
            command = readString("Enter \"f\" for full catalog, \"e\" for exit.", in).toLowerCase();
            if (command.equals("f"))
                showFullCatalog(catalog);
        } while (!command.equals("e"));
        in.close();
    }

    static void showFullCatalog(LaptopCatalog catalog) {
        print("Laptop catalog:");
        print(catalog.toStringTabulated());
    }

    static void print(String message) {
        System.out.println(message);
    }
    static String readString(String message, Scanner in) {
        if (!message.isBlank()) {
            System.out.println(message);
        }
        String result;
        try {
            result = in.nextLine();
        }
        catch(Exception e) {
            result = "";
        }
        return result;
    }
}
