package Program;

import java.util.Objects;

public class Ram {
    String brand;

    String model;

    String generation;

    double size;

    public Ram (String brand, String model, String generation, double size) {
        this.brand = brand;
        this.model = model;
        this.generation = generation;
        this.size = size;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getGeneration() {
        return generation;
    }

    public double getSize() {
        return size;
    }

    public void setBrand(String value) {
        brand = value;
    }

    public void setModel(String value) {
        model = value;
    }

    public void setGeneration(String value) {
        generation = value;
    }

    public void setSize(double value) {
        size = value;
    }

    @Override
    public String toString() {
        return String.format("%s %f Gb (%s %s)", generation, size, brand, model);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ram ram = (Ram) o;
        return Double.compare(size, ram.size) == 0 && Objects.equals(brand, ram.brand) && Objects.equals(model, ram.model) && Objects.equals(generation, ram.generation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, generation, size);
    }
}
