package ProgramNew;
import java.util.*;
import java.util.stream.Collectors;

public class Laptop {
    String brand;

    String model;

    String color;

    int vendorCode;

    int barCode;

    double price;

    double discount;

    HashMap<Parameter, Definition> definitions;

    HashMap<Parameter, String> stringParams;

    HashMap<Parameter, Integer> intParams;

    HashMap<Parameter, Double> doubleParams;

    public Laptop(String brand, String model, String color, int vendorCode, int barCode, double price, double discount, HashMap<Parameter, String> stringParams, HashMap<Parameter, Integer> intParams, HashMap<Parameter, Double> doubleParams) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.vendorCode = vendorCode;
        this.barCode = barCode;
        this.price = price;
        this.discount = discount;
        this.stringParams = Objects.requireNonNullElseGet(stringParams, HashMap::new);
        this.intParams = Objects.requireNonNullElseGet(intParams, HashMap::new);
        this.doubleParams = Objects.requireNonNullElseGet(doubleParams, HashMap::new);
        defineStringParamPrivate("Primary", "Brand", brand);
        defineStringParamPrivate("Primary", "Model", model);
        defineStringParamPrivate("Primary", "Color", color);
        defineDoubleParamPrivate("Primary", "Price", getDiscountedPrice());
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
        defineStringParamPrivate("Primary", "Brand", brand);
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
        defineStringParamPrivate("Primary", "Model", model);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        defineStringParamPrivate("Primary", "Color", color);
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = Math.max(price, 0);
        defineDoubleParamPrivate("Primary", "Price", getDiscountedPrice());
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = Math.max(0, Math.min(discount, 100));
        defineDoubleParamPrivate("Primary", "Price", getDiscountedPrice());
    }

    public double getDiscountedPrice() {
        return price * (100-discount) / 100;
    }

    public int getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(int vendorCode) {
        this.vendorCode = vendorCode;
    }

    public int getBarCode() {
        return barCode;
    }

    public void setBarCode(int barCode) {
        this.barCode = barCode;
    }

    public HashMap<Parameter, String> getStringParams() {
        return stringParams;
    }

    public void setStringParams(HashMap<Parameter, String> stringParams) {
        this.stringParams = Objects.requireNonNullElseGet(stringParams, HashMap::new);
        defineStringParamPrivate("Primary", "Brand", brand);
        defineStringParamPrivate("Primary", "Model", model);
        defineStringParamPrivate("Primary", "Color", color);
    }

    public void defineStringParam(String section, String name, String value) {
        if (section.equals("Primary")){
            if (!name.equals("Brand") && !name.equals("Model") && !name.equals("Color")) {
                defineStringParamPrivate(section, name, value);
            }
        }
    }

    void defineStringParamPrivate(String section, String name, String value) {
        if (value != null && !value.isBlank()) {
            stringParams.put(new Parameter(section, name, 0), value);
        }
        else {
            stringParams.remove(new Parameter(section, name, 0));
        }
    }

    public HashMap<Parameter, Integer> getIntParams() {
        return intParams;
    }

    public void setIntParams(HashMap<Parameter, Integer> intParams) {
        this.intParams = Objects.requireNonNullElseGet(intParams, HashMap::new);
    }

    public void defineIntParam(String section, String name, int value) {
        if (value != 0) {
            intParams.put(new Parameter(section, name, 1), value);
        }
        else {
            intParams.remove(new Parameter(section, name, 1));
        }
    }

    public HashMap<Parameter, Double> getDoubleParams() {
        return doubleParams;
    }

    public void setDoubleParams(HashMap<Parameter, Double> doubleParams) {
        this.doubleParams = Objects.requireNonNullElseGet(doubleParams, HashMap::new);
        defineDoubleParamPrivate("Primary", "Price", getDiscountedPrice());
    }

    public void defineDoubleParam(String section, String name, double value) {
        if (!name.equals("Price")) {
            defineDoubleParamPrivate(section, name, value);
        }
    }

    void defineDoubleParamPrivate(String section, String name, double value) {
        if (value != 0.0) {
            doubleParams.put(new Parameter(section, name, 1), value);
        }
        else {
            doubleParams.remove(new Parameter(section, name, 1));
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %.2f $ (old price: %.2f, discount: %.1f %%) VC: %d, BC: %d",
                this.brand, this.model, this.color, this.getDiscountedPrice(), this.price, this.discount, this.vendorCode, this.barCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return vendorCode == laptop.vendorCode && barCode == laptop.barCode && Double.compare(price, laptop.price) == 0 && Double.compare(discount, laptop.discount) == 0 && Objects.equals(brand, laptop.brand) && Objects.equals(model, laptop.model) && Objects.equals(color, laptop.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, color, vendorCode, barCode, price, discount);
    }
}
