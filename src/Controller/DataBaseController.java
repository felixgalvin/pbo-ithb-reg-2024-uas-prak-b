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
}
