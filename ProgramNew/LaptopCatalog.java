package ProgramNew;
import java.util.*;

public class LaptopCatalog {
    HashSet<Laptop> catalog;

    HashMap<Parameter, Double> minValues;

    HashMap<Parameter, Double> maxValues;

    HashMap<Parameter, HashSet<String>> allowableValues;

    public LaptopCatalog(HashSet<Laptop> catalog) {
        this.catalog = catalog;
        this.minValues = new HashMap<>();
        this.maxValues = new HashMap<>();
        this.allowableValues = new HashMap<>();
    }

    public static String getRandomStringFromList(ArrayList<String> list){
        Random rnd = new Random();
        return list.get(rnd.nextInt(list.size()));
    }

    public static HashSet<Laptop> getRandomCatalog() {
        ArrayList<Map.Entry<String,String>> brandsAndModels = new ArrayList<>();
        brandsAndModels.add(new AbstractMap.SimpleEntry<>("Acer", "Aspire 5"));
        brandsAndModels.add(new AbstractMap.SimpleEntry<>("Apple", "MackBook Air M1"));
        brandsAndModels.add(new AbstractMap.SimpleEntry<>("Apple", "MackBook Air M2"));
        brandsAndModels.add(new AbstractMap.SimpleEntry<>("Gigabyte", "Aorus 15 BMF"));
        brandsAndModels.add(new AbstractMap.SimpleEntry<>("HP", "Dragonfly Pro Chromebook"));
        brandsAndModels.add(new AbstractMap.SimpleEntry<>("HP", "Pavillion Aero 13"));
        brandsAndModels.add(new AbstractMap.SimpleEntry<>("HP", "Pavillion Plus 14"));
        brandsAndModels.add(new AbstractMap.SimpleEntry<>("Lenovo", "IdeaPad Flex 5i 14"));
        brandsAndModels.add(new AbstractMap.SimpleEntry<>("Xiaomi", "RedmiBook"));
        Random rnd = new Random();

        ArrayList<String> colors =
                new ArrayList<>(Arrays.asList(
                    "White", "Black", "Blue", "Pink", "Yellow", "Red", "Green", "Gray", "Aluminium"));
        ArrayList<String> processorModels =
                new ArrayList<>(Arrays.asList(
                    "Intel Core i3", "Intel Core i5", "Intel Core i7",
                    "AMD Ryzen", "AMD Athlon", "Cortex-M4"));
        ArrayList<String> operatingSystems =
                new ArrayList<>(Arrays.asList(
                        "Windows 10", "Windows 11", "MacOS", "Linux", "Ubuntu"));

        HashSet<Laptop> result = new HashSet<>();
        for (Map.Entry<String, String> brandAndModel : brandsAndModels) {
            Laptop currentLaptop = new Laptop(
                    brandAndModel.getKey(),
                    brandAndModel.getValue(),
                    getRandomStringFromList(colors),
                    rnd.nextInt(1000000, 9999999),
                    rnd.nextInt(1000000, 9999999),
                    rnd.nextInt(25, 50) * 10,
                    rnd.nextInt(0, 10)*5, null);
            currentLaptop.addDefinition(
                    new Definition(
                            new Parameter("CPU", "Model", 0),
                            getRandomStringFromList(processorModels)));
            currentLaptop.addDefinition(
                    new Definition(
                            new Parameter("CPU", "Cores Count", 1),
                    rnd.nextInt(1,6)*2));
            currentLaptop.addDefinition(
                    new Definition(
                            new Parameter("RAM", "Size", 2),
                         Math.pow(2, rnd.nextInt(3, 6))));
            currentLaptop.addDefinition(
                    new Definition(
                            new Parameter("Operating System", "OS", 0),
                            getRandomStringFromList(operatingSystems)));
            currentLaptop.addDefinition(
                    new Definition(
                            new Parameter("Storage", "Hard Drive Size", 2),
                            Math.pow(2, rnd.nextInt(7, 12))));
            result.add(currentLaptop);
        }
        return  result;
    }

    public HashSet<Laptop> getCatalog() {
        return catalog;
    }

    public void setCatalog(HashSet<Laptop> catalog) {
        this.catalog = Objects.requireNonNullElseGet(catalog, HashSet::new);
    }

    public ArrayList<Laptop> getSortedCatalog() {
        ArrayList<Laptop> result = new ArrayList<>(catalog);
        result.sort(Comparator.comparing(Laptop::toString));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        ArrayList<Laptop> shownCatalog = getSortedCatalog();
        int count = shownCatalog.size();
        for (int i = 0; i < count; i++) {
            Laptop laptop = shownCatalog.get(i);
            result.append(i + 1).append(". ").append(laptop.toString()).append("\n");
        }
        return result.toString();
    }

    public HashMap<Parameter, Double> getMinValues() {
        return minValues;
    }

    public void setMinValues(HashMap<Parameter, Double> minValues) {
        this.minValues = Objects.requireNonNullElseGet(minValues, HashMap::new);
    }

    public double getMinValue(Parameter parameter) {
        return this.minValues.get(parameter);
    }

    public void setMinValue(Parameter parameter, Double value) {
        this.minValues.put(parameter, value);
    }

    public void removeMinValue(Parameter parameter) {
        this.minValues.remove(parameter);
    }

    public HashMap<Parameter, Double> getMaxValues() {
        return maxValues;
    }

    public void setMaxValues(HashMap<Parameter, Double> maxValues) {
        this.maxValues = Objects.requireNonNullElseGet(maxValues, HashMap::new);
    }

    public Double getMaxValue(Parameter parameter) {
        return this.maxValues.get(parameter);
    }

    public void setMaxValue(Parameter parameter, Double value) {
        this.maxValues.put(parameter, value);
    }

    public void removeMaxValue(Parameter parameter) {
        this.maxValues.remove(parameter);
    }

    public HashMap<Parameter, HashSet<String>> getAllowableValues() {
        return allowableValues;
    }

    public void setAllowableValues(HashMap<Parameter, HashSet<String>> allowableValues) {
        this.allowableValues = Objects.requireNonNullElseGet(allowableValues, HashMap::new);
    }

    public void addAllowableValue(Parameter parameter, String value) {
        HashSet<String> curSet = this.allowableValues.get(parameter);
        if (curSet == null)
            curSet = new HashSet<>();
        curSet.add(value);
        this.allowableValues.put(parameter, curSet);
    }

    public void removeAllowableValue(Parameter parameter, String value) {
        HashSet<String> curSet = this.allowableValues.get(parameter);
        if (curSet != null) {
            curSet.remove(value);
            if (curSet.isEmpty()) {
                this.allowableValues.remove(parameter);
            }
        }
    }

    public void switchAllowableValue(Parameter parameter, String value) {
        HashSet<String> curSet = this.allowableValues.get(parameter);
        if (curSet == null) {
            addAllowableValue(parameter, value);
        }
        else {
            if (curSet.contains(value)) {
                removeAllowableValue(parameter, value);
            }
            else {
                addAllowableValue(parameter, value);
            }
        }
    }

    public ArrayList<Double> getNumericValues(Parameter numericParameter) {
        ArrayList<Double> result = new ArrayList<>();
        if (numericParameter.getValueType() == 1 || numericParameter.getValueType()==2) {
            HashSet<Double> values = new HashSet<>();
            catalog.forEach(laptop ->
                    laptop.getDefinitions().values().stream().
                            filter(definition ->
                                    definition.getParameter().equals(numericParameter)).
                            forEach(definition ->
                                    values.add(Double.parseDouble(definition.getValue().toString()))));
            result = new ArrayList<>(values);
            result.sort(Double::compareTo);
        }
        return result;
    }

    public ArrayList<String> getStringValues(Parameter stringParameter) {
        ArrayList<String> result = new ArrayList<>();
        if (stringParameter.getValueType() != 1 && stringParameter.getValueType() != 2) {
            HashSet<String> values = new HashSet<>();
            catalog.forEach(laptop ->
                    laptop.getDefinitions().values().stream().
                            filter(definition ->
                                    definition.getParameter().equals(stringParameter)).
                            forEach(definition ->
                                    values.add(definition.getValue().toString())));
            result = new ArrayList<>(values);
            result.sort(String::compareTo);
        }
        return  result;
    }

    public boolean isStringValueAllowable(Parameter stringParameter, String value) {
        HashSet<String> curAllowableValues = this.allowableValues.get(stringParameter);
        return (curAllowableValues != null && curAllowableValues.contains(value));
    }

    public boolean meetsRequirements(Laptop laptop) {
        for (Map.Entry<Parameter, Double> pair : this.minValues.entrySet()) {
            Definition definition = laptop.getDefinitions().get(pair.getKey());
            if (definition == null) {
                return false;
            }
            else {
                try{
                    if ((Double) definition.getValue() < pair.getValue()){
                        return false;
                    }
                }
                catch (Exception ignored) {
                    return false;
                }
            }
        }
        for (Map.Entry<Parameter, Double> pair : this.maxValues.entrySet()) {
            Definition definition = laptop.getDefinitions().get(pair.getKey());
            if (definition == null) {
                return false;
            }
            else {
                try{
                    if ((Double) definition.getValue() > pair.getValue()){
                        return false;
                    }
                }
                catch (Exception ignored) {
                    return false;
                }
            }
        }
        for (Map.Entry<Parameter, HashSet<String>> pair : this.allowableValues.entrySet()) {
            Definition definition = laptop.getDefinitions().get(pair.getKey());
            if (definition == null) {
                return false;
            }
            else {
                if (!pair.getValue().contains(definition.getValue().toString())){
                    return false;
                }
            }
        }
        return true;
    }

    public LaptopCatalog getFilteredCatalog() {
        List<Laptop> filteredList = this.catalog.stream().filter(this::meetsRequirements).toList();
        return new LaptopCatalog(new HashSet<>(filteredList));
    }
}


