package model;

import javafx.beans.property.*;

import java.util.Date;

public class Sale {
    private IntegerProperty id;
    private ObjectProperty<Drug> drug;
    private IntegerProperty quantity;
    private StringProperty buyer;
    private ObjectProperty<Date> date;

    public Sale(int id, Drug drug, int quantity, String buyer, Date date) {
        this.id = new SimpleIntegerProperty(id);
        this.drug = new SimpleObjectProperty<>(drug);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.buyer = new SimpleStringProperty(buyer);
        this.date = new SimpleObjectProperty<>(date);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public Drug getDrug() {
        return drug.get();
    }

    public void setDrug(Drug drug) {
        this.drug.set(drug);
    }

    public ObjectProperty<Drug> drugProperty() {
        return drug;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public String getBuyer() {
        return buyer.get();
    }

    public void setBuyer(String buyer) {
        this.buyer.set(buyer);
    }

    public StringProperty buyerProperty() {
        return buyer;
    }

    public Date getDate() {
        return date.get();
    }

    public void setDate(Date date) {
        this.date.set(date);
    }

    public ObjectProperty<Date> dateProperty() {
        return date;
    }

    @Override
    public String toString() {
        return "Sale [id=" + id + ", drug=" + drug + ", quantity=" + quantity + ", buyer=" + buyer + ", date=" + date + "]";
    }
}
