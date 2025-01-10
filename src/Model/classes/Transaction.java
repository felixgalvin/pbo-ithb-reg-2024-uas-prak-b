package Model.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class Transaction {

    private int id;
    private int customerId;
    private String deliveryType;
    private double deliveryWeight;
    private int totalCost;
    private Date createdAt;
    private String receiptName;
    private String receiptAddress;
    private String receiptPhone;

    public Transaction(int id, int customerId, String deliveryType, double deliveryWeight, int totalCost, Date createdAt,
            String receiptName, String receiptAddress, String receiptPhone) {
        this.id = id;
        this.customerId = customerId;
        this.deliveryType = deliveryType;
        this.deliveryWeight = deliveryWeight;
        this.totalCost = totalCost;
        this.createdAt = createdAt;
        this.receiptName = receiptName;
        this.receiptAddress = receiptAddress;
        this.receiptPhone = receiptPhone;
    }

    public Transaction(String string, String string2, double double1, double double2, Timestamp timestamp,
            Timestamp timestamp2) {
                
    }

    public Transaction() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public double getDeliveryWeight() {
        return deliveryWeight;
    }

    public void setDeliveryWeight(double deliveryWeight) {
        this.deliveryWeight = deliveryWeight;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getReceiptName() {
        return receiptName;
    }

    public void setReceiptName(String receiptName) {
        this.receiptName = receiptName;
    }

    public String getReceiptAddress() {
        return receiptAddress;
    }

    public void setReceiptAddress(String receiptAddress) {
        this.receiptAddress = receiptAddress;
    }

    public String getReceiptPhone() {
        return receiptPhone;
    }

    public void setReceiptPhone(String receiptPhone) {
        this.receiptPhone = receiptPhone;
    }

    public static boolean addTransaksi(Transaction trn) {
        String query = "INSERT INTO transaction (cust_id, delivery_type, delivery_weight, total_cost, created_at, receipt_name, receipt_address, receipt_phone) VALUES(?,?,?,?,?,?,?,?)";
        int type = getCategory(trn.deliveryType);

        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)){
            st.setInt(1, cust_id);
            st.setInt(2, type);
            st.setDouble(3, package_weight);
            st.setDouble(4, total_cost);
            st.setTimestamp(5, timestamp );
            st.setString(6, receiptName);
            st.setString(7, receiptAddress);
            st.setString(8, receiptPhone);

            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan saat registrasi: " + ex.getMessage());
            return false;
        } 
    }
}
