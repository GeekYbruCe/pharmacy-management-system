package model;

import javafx.beans.property.*;

public class Supplier {
    private StringProperty code;
    private StringProperty name;
    private StringProperty location;

    public Supplier(String code, String name, String location) {
        this.code = new SimpleStringProperty(code);
        this.name = new SimpleStringProperty(name);
        this.location = new SimpleStringProperty(location);
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

    public String getLocation() {
        return location.get();
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public StringProperty locationProperty() {
        return location;
    }

    @Override
    public String toString() {
        return "Supplier [code=" + code + ", name=" + name + ", location=" + location + "]";
    }
}
