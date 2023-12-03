package Program;

import java.util.*;

public class LaptopCatalog {
    HashSet<Laptop> laptops;
    Laptop laptopFilter;

    public LaptopCatalog(HashSet<Laptop> laptops) {
        this.laptops = laptops;
    }
    public static LaptopCatalog generateCatalog() {
        return new LaptopCatalog(new HashSet<Laptop>(Arrays.asList(Laptop.generateRandomLaptops(10))));
    }
    public Laptop getLaptopFilter() {
        return laptopFilter;
    }

    public void setLaptopFilter(Laptop laptopFilter) {
        this.laptopFilter = laptopFilter;
    }

    public String toStringTabulated() {
        StringBuilder result = new StringBuilder();
        for (Laptop laptop: laptops) {
            result.append(laptop.toStringTabulated()+"\n");
        }
        return result.toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Laptop laptop: laptops) {
            result.append(laptop.toString()+"\n");
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaptopCatalog that = (LaptopCatalog) o;
        return Objects.equals(laptops, that.laptops);
    }

    @Override
    public int hashCode() {
        return Objects.hash(laptops);
    }
}
