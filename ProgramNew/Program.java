package ProgramNew;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LaptopCatalog catalog = new LaptopCatalog(LaptopCatalog.getRandomCatalog());
        String command;
        do {
            command = readString("Enter \"f\" for full catalog, \"e\" for exit.", in).toLowerCase();
            if (command.equals("f"))
                showFullCatalog(catalog, in);
        } while (!command.equals("e"));
        in.close();
    }

    static void showFullCatalog(LaptopCatalog catalog, Scanner in) {
        print("Laptop catalog:");
        print(catalog.toString());
        String command;
        do {
            command = readString("Enter number to see more, \"b\" for back, \"e\" for exit.", in).toLowerCase();
            if (command.equals("e")) {
                terminate(in);
            }
            else if (!command.equals("b")) {
                try {
                    ArrayList<Laptop> laptops = catalog.getSortedCatalog();
                    int index = Integer.parseInt(command) - 1;
                    if (laptops.size() <= index) {
                        print("No such laptop.");
                    }
                    else {
                        showLaptop(index, laptops.get(index), in);
                    }
                }
                catch (Exception e) {
                    print("No such laptop.");
                }
            }
        } while (!command.equals("b"));
    }

    static void showLaptop(int index, Laptop laptop, Scanner in) {
        print(Integer.toString(index + 1) + ". " + laptop.toString());
    }

    static  void terminate(Scanner in) {
        in.close();
        System.exit(0);
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
