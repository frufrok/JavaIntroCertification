package ProgramNew;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class LaptopCatalog {
    HashSet<Laptop> catalog;

    public LaptopCatalog(HashSet<Laptop> catalog) {
        this.catalog = catalog;
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
        this.catalog = catalog;
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
}
