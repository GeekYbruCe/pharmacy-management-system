package model;

import javafx.beans.property.*;

public class Customer {
    private StringProperty id;
    private StringProperty name;
    private StringProperty contact;

    public Customer(String id, String name, String contact) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.contact = new SimpleStringProperty(contact);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public StringProperty idProperty() {
        return id;
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

    public String getContact() {
        return contact.get();
    }

    public void setContact(String contact) {
        this.contact.set(contact);
    }

    public StringProperty contactProperty() {
        return contact;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", contact=" + contact + "]";
    }
}
