package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import service.PharmacyService;

import java.util.Date;
import java.util.List;

public class PharmacyController {
    private final PharmacyService pharmacyService;

    // Drug fields
    @FXML
    private TextField drugCodeField;
    @FXML
    private TextField drugNameField;
    @FXML
    private TextField drugPriceField;
    @FXML
    private TextField supplierCodeFieldForDrug; // Changed variable name to avoid conflict
    @FXML
    private TextField drugStockField;
    @FXML
    private TableView<Drug> drugTable;
    @FXML
    private TableColumn<Drug, String> drugCodeColumn;
    @FXML
    private TableColumn<Drug, String> drugNameColumn;
    @FXML
    private TableColumn<Drug, Double> drugPriceColumn;
    @FXML
    private TableColumn<Drug, String> drugSupplierColumn;
    @FXML
    private TableColumn<Drug, Integer> drugStockColumn;

    // Supplier fields
    @FXML
    private TextField supplierCodeField;
    @FXML
    private TextField supplierNameField;
    @FXML
    private TextField supplierLocationField;
    @FXML
    private TableView<Supplier> supplierTable;
    @FXML
    private TableColumn<Supplier, String> supplierCodeColumn;
    @FXML
    private TableColumn<Supplier, String> supplierNameColumn;
    @FXML
    private TableColumn<Supplier, String> supplierLocationColumn;

    // Customer fields
    @FXML
    private TextField customerIdField;
    @FXML
    private TextField customerNameField;
    @FXML
    private TextField customerContactField;
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, String> customerIdColumn;
    @FXML
    private TableColumn<Customer, String> customerNameColumn;
    @FXML
    private TableColumn<Customer, String> customerContactColumn;

    // Sale fields
    @FXML
    private TableView<Sale> saleTable;
    @FXML
    private TableColumn<Sale, Integer> saleIdColumn;
    @FXML
    private TableColumn<Sale, String> saleDrugColumn;
    @FXML
    private TableColumn<Sale, Integer> saleQuantityColumn;
    @FXML
    private TableColumn<Sale, String> saleBuyerColumn;
    @FXML
    private TableColumn<Sale, Date> saleDateColumn;

    public PharmacyController() {
        pharmacyService = new PharmacyService();
    }

    @FXML
    public void initialize() {
        drugCodeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        drugNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        drugPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        drugSupplierColumn.setCellValueFactory(cellData -> cellData.getValue().supplierProperty().get().nameProperty());
        drugStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        loadDrugData();

        supplierCodeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        supplierNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        supplierLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        loadSupplierData();

        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerContactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        loadCustomerData();

        saleIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        saleDrugColumn.setCellValueFactory(cellData -> cellData.getValue().drugProperty().get().nameProperty());
        saleQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        saleBuyerColumn.setCellValueFactory(new PropertyValueFactory<>("buyer"));
        saleDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        loadSaleData();
    }

    @FXML
    private void handleAddDrug() {
        String drugCode = drugCodeField.getText();
        String name = drugNameField.getText();
        double price = Double.parseDouble(drugPriceField.getText());
        String supplierCode = supplierCodeFieldForDrug.getText();
        int stock = Integer.parseInt(drugStockField.getText());

        Supplier supplier = pharmacyService.searchSupplier(supplierCode);
        if (supplier != null) {
            Drug drug = new Drug(drugCode, name, price, supplier, stock);
            pharmacyService.addDrug(drug);
            loadDrugData();
        } else {
            // Handle supplier not found
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Supplier Not Found");
            alert.setContentText("Supplier with code " + supplierCode + " does not exist.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleAddSupplier() {
        String supplierCode = supplierCodeField.getText();
        String name = supplierNameField.getText();
        String location = supplierLocationField.getText();

        Supplier supplier = new Supplier(supplierCode, name, location);
        pharmacyService.addSupplier(supplier);
        loadSupplierData();
    }

    @FXML
    private void handleAddCustomer() {
        String customerId = customerIdField.getText();
        String name = customerNameField.getText();
        String contact = customerContactField.getText();

        Customer customer = new Customer(customerId, name, contact);
        pharmacyService.addCustomer(customer);
        loadCustomerData();
    }

    private void loadDrugData() {
        List<Drug> drugs = pharmacyService.viewAllDrugs();
        drugTable.getItems().setAll(drugs);
    }

    private void loadSupplierData() {
        List<Supplier> suppliers = pharmacyService.viewAllSuppliers();
        supplierTable.getItems().setAll(suppliers);
    }

    private void loadCustomerData() {
        List<Customer> customers = pharmacyService.viewAllCustomers();
        customerTable.getItems().setAll(customers);
    }

    private void loadSaleData() {
        List<Sale> sales = pharmacyService.viewAllSales();
        saleTable.getItems().setAll(sales);
    }

    @FXML
    private void handleGenerateReport() {
        pharmacyService.generateReport();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Report");
        alert.setHeaderText("Report Generated");
        alert.setContentText("Report has been generated successfully.");
        alert.showAndWait();
    }
}
