package Program;

import java.util.Objects;

public class Laptop {
    String brand;
    String model;
    String color;
    double weight;
    double price;
    double discount;
    Display display;
    GraphicsCoprocessor graphics;
    HardDrive hardDrive;
    OperatingSystem operatingSystem;
    Processor processor;

    public Laptop(String brand, String model, String color, double weight, double price, double discount, Display display, GraphicsCoprocessor graphics, HardDrive hardDrive, OperatingSystem operatingSystem, Processor processor) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.weight = weight;
        this.price = price;
        this.discount = discount;
        this.display = display;
        this.graphics = graphics;
        this.hardDrive = hardDrive;
        this.operatingSystem = operatingSystem;
        this.processor = processor;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public GraphicsCoprocessor getGraphics() {
        return graphics;
    }

    public void setGraphics(GraphicsCoprocessor graphics) {
        this.graphics = graphics;
    }

    public HardDrive getHardDrive() {
        return hardDrive;
    }

    public void setHardDrive(HardDrive hardDrive) {
        this.hardDrive = hardDrive;
    }

    public OperatingSystem getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(OperatingSystem operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", brand, model, color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return Double.compare(weight, laptop.weight) == 0 && Double.compare(price, laptop.price) == 0 && Double.compare(discount, laptop.discount) == 0 && Objects.equals(brand, laptop.brand) && Objects.equals(model, laptop.model) && Objects.equals(color, laptop.color) && Objects.equals(display, laptop.display) && Objects.equals(graphics, laptop.graphics) && Objects.equals(hardDrive, laptop.hardDrive) && Objects.equals(operatingSystem, laptop.operatingSystem) && Objects.equals(processor, laptop.processor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, color, weight, price, discount, display, graphics, hardDrive, operatingSystem, processor);
    }
}
