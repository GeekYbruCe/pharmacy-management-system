package model;

import javafx.beans.property.*;

public class Drug {
    private StringProperty code;
    private StringProperty name;
    private DoubleProperty price;
    private ObjectProperty<Supplier> supplier;
    private IntegerProperty stock;

    public Drug(String code, String name, double price, Supplier supplier, int stock) {
        this.code = new SimpleStringProperty(code);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.supplier = new SimpleObjectProperty<>(supplier);
        this.stock = new SimpleIntegerProperty(stock);
    }

    public String getCode() {
        return code.get();
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    public StringProperty codeProperty() {
        return code;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public Supplier getSupplier() {
        return supplier.get();
    }

    public void setSupplier(Supplier supplier) {
        this.supplier.set(supplier);
    }

    public ObjectProperty<Supplier> supplierProperty() {
        return supplier;
    }

    public int getStock() {
        return stock.get();
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

    public IntegerProperty stockProperty() {
        return stock;
    }

    @Override
    public String toString() {
        return "Drug [code=" + code + ", name=" + name + ", price=" + price + ", supplier=" + supplier + ", stock=" + stock + "]";
    }
}
