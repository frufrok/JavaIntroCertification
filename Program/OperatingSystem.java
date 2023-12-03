package Program;

import java.util.Objects;

public class OperatingSystem {
    String brand;
    String name;

    public OperatingSystem(String brand, String name) {
        this.brand = brand;
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s %s", brand, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperatingSystem that = (OperatingSystem) o;
        return Objects.equals(brand, that.brand) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, name);
    }
}
