package ProgramNew;
import java.util.*;
import java.util.stream.Collectors;

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
                        showLaptop(index, laptops.get(index));
                    }
                }
                catch (Exception e) {
                    print("No such laptop.");
                }
            }
        } while (!command.equals("b"));
    }

    static void showLaptop(int index, Laptop laptop) {
        print((index + 1) + ". " + laptop.toString());
        HashMap<Parameter, Definition> defs = laptop.definitions;
        Map<String, List<Definition>> groupedDefs = defs.values().stream().collect(
                Collectors.groupingBy(Definition::getParameterSection));
        ArrayList<String> sections = new ArrayList<>(groupedDefs.keySet());
        sections.sort(String::compareTo);
        for (String section : sections) {
            print("\t" + section + ":");
            List<Definition> sectionDefs = groupedDefs.get(section);
            sectionDefs.sort(Comparator.comparing(Definition::getParameterName));
            for (Definition def : sectionDefs) {
                print("\t\t" + def.getParameterName() + ": " + def.getValue().toString());
            }
        }
    }

    static String reqLenStr(String str, int reqLen){
        if (str!= null && !str.isBlank()) {
            int len = str.length();
            if (len > reqLen) {
                return str.substring(reqLen);
            }
            else {
                return str + " ".repeat(reqLen - len);
            }
        }
        else
        {
            return " ".repeat(reqLen);
        }
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
