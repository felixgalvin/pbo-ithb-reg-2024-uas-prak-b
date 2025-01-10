package Model.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import Model.enums.Status;

public class DeliveryDetails {

    private int id;
    private int transactionId;
    private Status status;
    private String currentPosition;
    private String evidence;
    private Date date;
    private String updatedBy;

    public DeliveryDetails(int id, int transactionId, Status status, String currentPosition, String evidence, Date date,
            String updatedBy) {
        this.id = id;
        this.transactionId = transactionId;
        this.status = status;
        this.currentPosition = currentPosition;
        this.evidence = evidence;
        this.date = date;
        this.updatedBy = updatedBy;
    }

    public DeliveryDetails() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public static ArrayList<DeliveryDetails> gDeliveryDetails() {
        ArrayList<DeliveryDetails> deliveryDetailsList = new ArrayList<>();
        String query = "SELECT * FROM delivery_details";
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement st = con.prepareStatement(query)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                deliveryDetailsList.add(new DeliveryDetails(
                        rs.getInt("delivery_id"),
                        rs.getInt("transaction_id"),
                        Status.valueOf(rs.getString("status")),
                        rs.getString("current_position"),
                        rs.getString("evidence"),
                        new Date(rs.getTimestamp("date").getTime()),
                        rs.getString("updated_by")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan saat retrieval: " + e.getMessage());
        }
        return deliveryDetailsList;
    }


    public static boolean addDeliveryDetails(DeliveryDetails dd) {
        String query = "INSERT INTO delivery_details (transaction_id, status, current_position, evidence, date, updated_by) VALUES(?,?,?,?,?,?)";

        try (Connection con = ConnectionManager.getConnection(); PreparedStatement st = con.prepareStatement(query)){
            st.setInt(1, dd.transactionId);
            st.setString(2, dd.status.name());
            st.setString(3, dd.currentPosition);
            st.setString(4, dd.evidence);
            st.setTimestamp(5, new Timestamp(dd.date.getTime()));
            st.setString(6, dd.updatedBy);

            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan saat insertion: " + e.getMessage());
            return false;
        } 
    }

}