package ProgramNew;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class LaptopCatalog {
    HashSet<Laptop> catalog;

    public LaptopCatalog(HashSet<Laptop> catalog) {
        this.catalog = catalog;
    }


    public static HashSet<Laptop> getRandomCatalog() {
        HashSet<Laptop> result = new HashSet<>();
        result.add(new Laptop("Samsung", "GalaxyNote", "Black", 101, 201, 500, 15, null,null,null));
        result.add(new Laptop("Samsung", "GalaxyNote", "Black", 101, 201, 500, 15, null,null,null));
        result.add(new Laptop("HP", "Pavilion", "White", 102, 202, 450, 10, null,null,null));
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
            result.append(Integer.toString(i+1) + ". " + laptop.toString() + "\n");
        }
        return result.toString();
    }
}
