package ProgramNew;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LaptopCatalog catalog = new LaptopCatalog(LaptopCatalog.getRandomCatalog());
        String command;
        do {
            command = readString("Enter \"f\" for full catalog, \"s\" for filter catalog, \"e\" for exit.", in).toLowerCase();
            if (command.equals("f"))
                showFullCatalog(catalog, in);
            if (command.equals("s"))
                configureFilters(catalog, in);
        } while (!command.equals("e"));
        in.close();
    }

    static DecimalFormat df() {
        DecimalFormat result = new DecimalFormat();
        result.setMaximumFractionDigits(3);
        result.setMinimumFractionDigits(0);
        return result;
    }

    static void showFullCatalog(LaptopCatalog catalog, Scanner in) {
        print("Laptop catalog:");
        print(catalog.toString());
        String command;
        do {
            command = readString("Enter number to see more, \"s\" for filter catalog, \"b\" for back, \"e\" for exit.", in).toLowerCase();
            if (command.equals("e")) {
                terminate(in);
            }
            else if (command.equals("s")) {
                configureFilters(catalog, in);
                break;
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
                catch (Exception ignored) {
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

    static  void configureFilters(LaptopCatalog catalog, Scanner in) {
        String command;
        do {
            ArrayList<Parameter> filterParams = showParamsList(catalog);
            command = readString("Enter number to configure filter, \"d\" if you are done, \"b\" for back, \"e\" for exit.", in).toLowerCase();
            if (command.equals("e")) {
                terminate(in);
            }
            else if (command.equals("d")) {
                showFilteredCatalog(catalog, in);
                break;
            }
            else if (!command.equals("b")) {
                try {
                    setFilter(catalog, filterParams.get(Integer.parseInt(command)-1), in);
                }
                catch (Exception ignored) {
                    print("No such parameter.");
                }
            }
        } while (!command.equals("b"));
    }

    static ArrayList<Parameter> showParamsList(LaptopCatalog catalog) {
        ArrayList<Parameter> result= new ArrayList<>();
        int i = 0;
        HashSet<Parameter> allParams = new HashSet<>();
        catalog.getCatalog().forEach(laptop -> allParams.addAll(laptop.getDefinitions().keySet()));

        Map<String, List<Parameter>> groupedParams = allParams.stream().collect(
                Collectors.groupingBy(Parameter::getSection));
        ArrayList<String> sections = new ArrayList<>(groupedParams.keySet());
        sections.sort(String::compareTo);
        for (String section : sections) {
            print(section + ":");
            List<Parameter> sectionParams = groupedParams.get(section);
            sectionParams.sort(Comparator.comparing(Parameter::getName));
            for (Parameter param : sectionParams) {
                StringBuilder curFilter = new StringBuilder();
                if (param.getValueType() == 1 || param.getValueType() == 2) {
                    Double minValue = catalog.minValues.get(param);
                    Double maxValue = catalog.maxValues.get(param);
                    if (minValue!=null || maxValue != null) {
                        curFilter.append(" [");
                        if (minValue!=null) {
                            curFilter.append(String.format("from %s", df().format(minValue)));
                        }
                        if (minValue!= null && maxValue != null) {
                            curFilter.append(" ");
                        }
                        if (maxValue != null) {
                            curFilter.append(String.format("up to %s", df().format(maxValue)));
                        }
                        curFilter.append("]");
                    }
                }
                else {
                    HashSet<String> allowable = catalog.getAllowableValues().get(param);
                    if (allowable != null) {
                        curFilter.append(" ");
                        ArrayList<String> allowableList = new ArrayList<>(allowable);
                        allowableList.sort(String::compareTo);
                        curFilter.append(allowableList);
                    }
                }
                print("\t" + (i+1) + ". " + param.getName() + curFilter);
                result.add(param);
                i++;
            }
        }
        return result;
    }

    static void setFilter(LaptopCatalog catalog, Parameter parameter, Scanner in) {
        if (parameter.getValueType() == 1 || parameter.getValueType() == 2) {
            HashSet<Double> values = new HashSet<>();
            catalog.getCatalog().forEach(laptop ->
                    laptop.getDefinitions().values().stream().
                            filter(definition ->
                                    definition.getParameter().equals(parameter) &&
                                            (definition.getParameter().getValueType()==1 ||
                                                    definition.getParameter().getValueType()==2)).
                            forEach(definition ->
                                    values.add(Double.parseDouble(definition.getValue().toString()))));
            ArrayList<Double> sortedValues = new ArrayList<>(values);
            sortedValues.sort(Double::compareTo);
            String command;
            boolean flag;
            do {
                command = readString(String.format("Values of %s are differ from %s to %s. Enter min value to filter or leave field empty.",
                        parameter.getName(), df().format(sortedValues.getFirst()), df().format(sortedValues.getLast())), in).toLowerCase();
                flag = false;
                if (command.isBlank()) {
                    catalog.removeMinValue(parameter);
                    flag = true;
                }
                else {
                    try {
                        catalog.setMinValue(parameter, Double.parseDouble(command));
                        flag = true;
                    }
                    catch (Exception ignored) {
                        print("Invalid input. Try again.");
                    }
                }
            } while (!flag);
            do {
                command = readString("Enter max value to filter or leave field empty.", in);
                flag = false;
                if (command.isBlank()) {
                    catalog.removeMaxValue(parameter);
                    flag = true;
                }
                else {
                    try {
                        Double newMax = Double.parseDouble(command);
                        Double curMin = catalog.getMinValues().get(parameter);
                        if (curMin!=null && newMax < curMin) {
                            newMax = curMin;
                        }
                        catalog.setMaxValue(parameter, newMax);
                        flag = true;
                    }
                    catch (Exception ignored) {
                        print("Invalid input. Try again.");
                    }
                }
            } while (!flag);
        }
        else {
            HashSet<String> values = new HashSet<>();
            catalog.getCatalog().forEach(laptop ->
                    laptop.getDefinitions().values().stream().
                            filter(definition ->
                                    definition.getParameter().equals(parameter) &&
                                    definition.getParameter().getValueType()!=1 &&
                                            definition.getParameter().getValueType()!=2).
                            forEach(definition ->
                                    values.add(definition.getValue().toString())));
            ArrayList<String> sortedValues = new ArrayList<>(values);
            sortedValues.sort(String::compareTo);

            String command;
            do {
                print("There are these values (allowed by filter are signed by *):");
                for (int i = 0; i < sortedValues.size(); i++) {
                    String value = sortedValues.get(i);
                    String sign = "";
                    HashSet<String> curAllowableValues = catalog.getAllowableValues().get(parameter);
                    if (curAllowableValues!=null && curAllowableValues.contains(value)){
                        sign =  " *";
                    }
                    print("\t" + (i + 1) + ". " + value + sign);
                }
                command = readString("Enter number to add or remove value from filter allowable values list, \"d\" when done.", in);
                if (!command.equals("d")) {
                    try {
                        int index = Integer.parseInt(command) - 1;
                        if (index < sortedValues.size()) {
                            String value = sortedValues.get(index);
                            catalog.switchAllowableValue(parameter, value);
                        }
                        else {
                            print("No such value. Try again.");
                        }
                    }
                    catch (Exception ignored) {
                        print("No such value. Try again.");
                    }
                }
            } while (!command.equals("d"));
        }
    }

    static  void showFilteredCatalog(LaptopCatalog catalog, Scanner in) {



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
        catch(Exception ignored) {
            result = "";
        }
        return result;
    }
}
