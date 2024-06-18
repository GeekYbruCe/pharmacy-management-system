package service;

import model.*;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PharmacyService {
    private Connection connection;

    public PharmacyService() {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDrug(Drug drug) {
        String sql = "INSERT INTO drugs (drug_code, name, price, supplier_code, stock) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, drug.getCode());
            pstmt.setString(2, drug.getName());
            pstmt.setDouble(3, drug.getPrice());
            pstmt.setString(4, drug.getSupplier().getCode());
            pstmt.setInt(5, drug.getStock());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeDrug(String drugCode) {
        String sql = "DELETE FROM drugs WHERE drug_code = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, drugCode);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Drug searchDrug(String drugCode) {
        String sql = "SELECT * FROM drugs WHERE drug_code = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, drugCode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String supplierCode = rs.getString("supplier_code");
                int stock = rs.getInt("stock");
                Supplier supplier = searchSupplier(supplierCode);
                return new Drug(drugCode, name, price, supplier, stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Drug> viewAllDrugs() {
        List<Drug> drugs = new ArrayList<>();
        String sql = "SELECT * FROM drugs";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String drugCode = rs.getString("drug_code");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String supplierCode = rs.getString("supplier_code");
                int stock = rs.getInt("stock");
                Supplier supplier = searchSupplier(supplierCode);
                drugs.add(new Drug(drugCode, name, price, supplier, stock));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drugs;
    }

    public void addSupplier(Supplier supplier) {
        String sql = "INSERT INTO suppliers (supplier_code, name, location) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, supplier.getCode());
            pstmt.setString(2, supplier.getName());
            pstmt.setString(3, supplier.getLocation());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeSupplier(String supplierCode) {
        String sql = "DELETE FROM suppliers WHERE supplier_code = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, supplierCode);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Supplier searchSupplier(String supplierCode) {
        String sql = "SELECT * FROM suppliers WHERE supplier_code = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, supplierCode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String location = rs.getString("location");
                return new Supplier(supplierCode, name, location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Supplier> viewAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM suppliers";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String supplierCode = rs.getString("supplier_code");
                String name = rs.getString("name");
                String location = rs.getString("location");
                suppliers.add(new Supplier(supplierCode, name, location));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customers (customer_id, name, contact) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, customer.getId());
            pstmt.setString(2, customer.getName());
            pstmt.setString(3, customer.getContact());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Customer searchCustomer(String customerId) {
        String sql = "SELECT * FROM customers WHERE customer_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String contact = rs.getString("contact");
                return new Customer(customerId, name, contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Customer> viewAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String customerId = rs.getString("customer_id");
                String name = rs.getString("name");
                String contact = rs.getString("contact");
                customers.add(new Customer(customerId, name, contact));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public void addSale(Sale sale) {
        String sql = "INSERT INTO sales (drug_code, quantity, buyer, date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, sale.getDrug().getCode());
            pstmt.setInt(2, sale.getQuantity());
            pstmt.setString(3, sale.getBuyer());
            pstmt.setTimestamp(4, new Timestamp(sale.getDate().getTime()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Sale> viewAllSales() {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT * FROM sales";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String drugCode = rs.getString("drug_code");
                int quantity = rs.getInt("quantity");
                String buyer = rs.getString("buyer");
                Timestamp date = rs.getTimestamp("date");
                Drug drug = searchDrug(drugCode);
                sales.add(new Sale(id, drug, quantity, buyer, date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    public List<Sale> getSortedSales() {
        List<Sale> sales = viewAllSales();
        Collections.sort(sales, Comparator.comparing(Sale::getDate));
        return sales;
    }

    public void writeSalesToFile() {
        try (FileWriter writer = new FileWriter("sales.txt", true)) {
            List<Sale> sales = viewAllSales();
            for (Sale sale : sales) {
                writer.write(sale.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateReport() {
        // Implement report generation logic
    }
}
