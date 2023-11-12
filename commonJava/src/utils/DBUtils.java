package utils;
import java.sql.*;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class DBUtils {

    private static String url = "jdbc:sqlite:commonJava/src/database.db";
    private static PreparedStatement p = null;
    private static ResultSet rs = null;

    public DBUtils() {
        ;
    }

    private static Connection connectDB(){
        try {
            Class.forName("org.sqlite.JDBC");

            Connection conn = DriverManager.getConnection(url);
            return conn;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static ResultSet GetData(String sql){
        Connection conn = connectDB();

        try {
            p = conn.prepareStatement(sql);
            rs = p.executeQuery();
            return  rs;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONArray GetDataByName(String fullName){
        String sql = String.format("select * from users where fullName = '%s'", fullName);

        rs = GetData(sql);
        JSONArray array = new JSONArray();
        List data;

        try{
            while (rs.next()){
                JSONObject item = new JSONObject();  // Initialize the item here
                item.clear();
                int id = rs.getInt("id");
                String name = rs.getString("fullName");
                String password = rs.getString("password");
                String dob = rs.getString("DOB");
                int Pin = rs.getInt("PIN");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String debit_cards = rs.getString("debit_cards");

                item.put("id", id);
                item.put("name", name);
                item.put("password", password);
                item.put("dob", dob);
                item.put("pin", Pin);
                item.put("phoneNumber", phoneNumber);
                item.put("email", email);
                item.put("debit_cards", debit_cards);
                array.put(item);
            }
            return array;
        }
        catch (Exception e){
            e.printStackTrace();
        }


        return null;
    }

    public static JSONArray GetDataByEmail(String inputEmail){
        String sql = String.format("select * from users where email = %s", inputEmail);

        rs = GetData(sql);
        JSONArray array = null;
        JSONObject item;
        List data;

        try{
            while (rs.next()){
                item = null;
                int id = rs.getInt("id");
                String name = rs.getString("fullName");
                String password = rs.getString("password");
                Date dob = rs.getDate("DOB");
                int Pin = rs.getInt("PIN");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String debit_cards = rs.getString("debit_cards");

                item.put("id", id);
                item.put("name", name);
                item.put("password", password);
                item.put("dob", dob);
                item.put("pin", Pin);
                item.put("phoneNumber", phoneNumber);
                item.put("email", email);
                item.put("debit_cards", debit_cards);
                array.put(item);
            }
            return array;
        }
        catch (Exception e){
            e.printStackTrace();
        }


        return null;
    }

    public static void InsertDataInDB(String name, String password, java.util.Date dob, int pin, String phoneNumber, String email){
        Connection conn = connectDB();
        String sql = String.format("insert into users values(null, '%s', '%s', '%s', %d, '%s', '%s', '')", name, password, dob.toString(), pin, phoneNumber, email);

        try{
            Statement st = conn.createStatement();
            st.execute(sql);
            System.out.println("Inserted Successfully!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
