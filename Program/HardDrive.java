package Program;

import java.util.Objects;

public class HardDrive {
    String brand;
    String model;
    String description;
    double size;
    double readSpeed;
    double writeSpeed;

    public HardDrive(String brand, String mode, String description, double size, double readSpeed, double writeSpeed) {
        this.brand = brand;
        this.model = mode;
        this.description = description;
        this.size = size;
        this.readSpeed = readSpeed;
        this.writeSpeed = writeSpeed;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getReadSpeed() {
        return readSpeed;
    }

    public void setReadSpeed(double readSpeed) {
        this.readSpeed = readSpeed;
    }

    public double getWriteSpeed() {
        return writeSpeed;
    }

    public void setWriteSpeed(double writeSpeed) {
        this.writeSpeed = writeSpeed;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %f Gb Speed (R/W): %f/%f Gbit/sec", description, brand, model, size, readSpeed, writeSpeed);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HardDrive hardDrive = (HardDrive) o;
        return Double.compare(size, hardDrive.size) == 0 && Double.compare(readSpeed, hardDrive.readSpeed) == 0 && Double.compare(writeSpeed, hardDrive.writeSpeed) == 0 && Objects.equals(brand, hardDrive.brand) && Objects.equals(model, hardDrive.model) && Objects.equals(description, hardDrive.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, description, size, readSpeed, writeSpeed);
    }
}
