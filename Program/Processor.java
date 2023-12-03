package Program;

import java.util.Objects;

public class Processor {
    String brand;
    String model;
    int coresCount;
    double frequency;

    public Processor(String brand, String model, int coresCount, double frequency) {
        this.brand = brand;
        this.model = model;
        this.coresCount = coresCount;
        this.frequency = frequency;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCoresCount() {
        return coresCount;
    }

    public void setCoresCount(int coresCount) {
        this.coresCount = coresCount;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }
    @Override
    public String toString() {
        return String.format("%s %s %dx%f GHz", brand, model, coresCount, frequency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Processor processor = (Processor) o;
        return coresCount == processor.coresCount && Double.compare(frequency, processor.frequency) == 0 && Objects.equals(brand, processor.brand) && Objects.equals(model, processor.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, coresCount, frequency);
    }
}
