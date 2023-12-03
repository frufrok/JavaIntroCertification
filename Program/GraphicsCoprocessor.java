package Program;

import java.util.Objects;

public class GraphicsCoprocessor {
    String brand;
    String model;
    String integrationType;
    double memorySize;

    public GraphicsCoprocessor(String brand, String model, String integrationType, double memorySize) {
        this.brand = brand;
        this.model = model;
        this.integrationType = integrationType;
        this.memorySize = memorySize;
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

    public String getIntegrationType() {
        return integrationType;
    }

    public void setIntegrationType(String integrationType) {
        this.integrationType = integrationType;
    }

    public double getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(double memorySize) {
        this.memorySize = memorySize;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %f Gb", integrationType, brand, model, memorySize);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphicsCoprocessor that = (GraphicsCoprocessor) o;
        return Double.compare(memorySize, that.memorySize) == 0 && Objects.equals(brand, that.brand) && Objects.equals(model, that.model) && Objects.equals(integrationType, that.integrationType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, integrationType, memorySize);
    }
}
