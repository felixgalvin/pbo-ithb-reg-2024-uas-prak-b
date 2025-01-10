package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.classes.ConnectionManager;

public class DataBaseController {

    public String[] getDeliveryType() {
        ArrayList<String> deliveryType = new ArrayList<>();

        String query = "SELECT category_type FROM category";
        try {

            Connection con = ConnectionManager.getConnection();
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                deliveryType.add(rs.getString("category_type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return deliveryType.toArray(new String[0]);
    }

    public int getDeliveryFeeByName(String name) {
        String query = "SELECT * FROM category";
        try {
            Connection con = ConnectionManager.getConnection();
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                if (name.equals(rs.getString("category_type"))) {
                    return rs.getInt("fee");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return 0;
    }

    public int getDeliveryIdByName(String name) {
        String query = "SELECT * FROM category";
        try {
            Connection con = ConnectionManager.getConnection();
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                if (name.equals(rs.getString("category_type"))) {
                    return rs.getInt("category_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return 0;
    }
}
