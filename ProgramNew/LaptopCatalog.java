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

    public static HashSet<Laptop> getRandomCatalog() {
        HashSet<Laptop> result = new HashSet<>();
        Laptop samsung1 = new Laptop("Samsung", "GalaxyNote", "Black", 101, 201, 500, 15, null);
        Laptop samsung2 = new Laptop("Samsung", "GalaxyNote", "Black", 101, 201, 500, 15, null);
        result.add(samsung1);
        result.add(samsung2);
        samsung2.addDefinition(new Definition(new Parameter("Other", "Wi-Fi Generation", 0), "5G"));
        samsung2.addDefinition(new Definition(new Parameter("CPU", "Cores Count", 1), 8));
        result.add(samsung2);
        result.add(new Laptop("HP", "Pavilion", "White", 102, 202, 450, 10, null));

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
        this.maxValues = Objects.requireNonNullElseGet(maxValues, HashMap::new);;
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
}
