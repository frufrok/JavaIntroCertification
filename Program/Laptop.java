package Program;
import java.util.*;

public class Laptop {
    String brand;

    String model;

    String color;

    int vendorCode;

    int barCode;

    double price;

    double discount;

    HashMap<Parameter, Definition> definitions;

    public Laptop(String brand, String model, String color, int vendorCode, int barCode, double price, double discount, HashMap<Parameter, Definition> definitions) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.vendorCode = vendorCode;
        this.barCode = barCode;
        this.price = price;
        this.discount = discount;
        this.definitions = Objects.requireNonNullElseGet(definitions, HashMap::new);
        refreshBaseDefinitions();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
        refreshBaseDefinitions();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
        refreshBaseDefinitions();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        refreshBaseDefinitions();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = Math.max(price, 0);
        refreshBaseDefinitions();
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = Math.max(0, Math.min(discount, 100));
        refreshBaseDefinitions();
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

    private void refreshBaseDefinitions() {
        Parameter brandParam = new Parameter("Primary", "Brand", 0);
        Parameter modelParam = new Parameter("Primary", "Model", 0);
        Parameter colorParam = new Parameter("Appearance", "Color", 0);
        Parameter vendorCodeParam = new Parameter("Codes", "Vendor Code", 1);
        Parameter barCodeParam = new Parameter("Codes", "Bar Code", 1);
        Parameter priceParam = new Parameter("Price", "Base", 2);
        Parameter discountParam = new Parameter("Price", "Discount", 2);
        Parameter discountedPriceParam = new Parameter("Price", "Final", 2);

        this.definitions.put(brandParam, new Definition(brandParam, this.brand));
        this.definitions.put(modelParam, new Definition(modelParam, this.model));
        this.definitions.put(colorParam, new Definition(colorParam, this.color));
        this.definitions.put(vendorCodeParam, new Definition(vendorCodeParam, this.vendorCode));
        this.definitions.put(barCodeParam, new Definition(barCodeParam, this.barCode));
        this.definitions.put(priceParam, new Definition(priceParam, this.price));
        this.definitions.put(discountParam, new Definition(discountParam, this.discount));
        this.definitions.put(discountedPriceParam, new Definition(discountedPriceParam, getDiscountedPrice()));
    }

    public HashMap<Parameter, Definition> getDefinitions() {
        return this.definitions;
    }

    public void setDefinitions(HashMap<Parameter, Definition> definitions) {
        this.definitions = Objects.requireNonNullElseGet(definitions, HashMap::new);
        refreshBaseDefinitions();
    }

    public void addDefinition(Definition definition) {
        this.definitions.put(definition.parameter, definition);
        refreshBaseDefinitions();
    }

    public boolean hasSameDefinitions(Laptop another) {
        boolean result = true;
        ArrayList<Definition> thisDefinitions = new ArrayList<>(this.definitions.values());
        ArrayList<Definition> anotherDefinitions = new ArrayList<>(another.definitions.values());

        if (thisDefinitions.size() == anotherDefinitions.size()){
            Comparator<Definition> comparator = Comparator.comparing(o -> o.parameter.toString());

            thisDefinitions.sort(comparator);
            anotherDefinitions.sort(comparator);

            int count = thisDefinitions.size();
            for (int i = 0; i < count; i++) {
                if (!thisDefinitions.get(i).equals(anotherDefinitions.get(i))) {
                    result = false;
                    break;
                }
            }
        }
        else {
            result = false;
        }
        return result;
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
        return vendorCode == laptop.vendorCode && barCode == laptop.barCode &&
                Double.compare(price, laptop.price) == 0 && Double.compare(discount, laptop.discount) == 0 &&
                Objects.equals(brand, laptop.brand) && Objects.equals(model, laptop.model) &&
                Objects.equals(color, laptop.color) && hasSameDefinitions(laptop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, color, vendorCode, barCode, price, discount);
    }
}
