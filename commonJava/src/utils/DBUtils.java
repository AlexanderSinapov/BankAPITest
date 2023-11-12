package utils;
import java.sql.*;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

public class DBUtils {

    private static String url = "jdbc:sqlite:commonJava/src/database.db";
    private static PreparedStatement p = null;
    private static ResultSet rs = null;

    private static String bankIdentifier = "158";

    private static class DateHolder{
        int month;
        int year;
    }

    private static class CardDetails{
        int pin;
        int cvc;
        java.util.Date expDate;
        String cardNumber;

    }

    public enum DebitCard{
        Visa,
        MasterCard
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

    public static CardDetails GenerateCardDetails(DebitCard type){
        StringBuilder cardNumber = new StringBuilder();
        int pin = 0;
        int cvc = 0;
        Calendar c = Calendar.getInstance();
        DateHolder date = new DateHolder();
        java.util.Date expDate;
        Random rand = new Random();
        CardDetails detail = new CardDetails();

        if (type == DebitCard.Visa){
            cardNumber.append("4");
        }
        else {
            cardNumber.append("5");
        }

        cardNumber.append(bankIdentifier);

        while (cardNumber.length() < 16){
            cardNumber.append(rand.nextInt(10));
        }

        pin = rand.nextInt(10000);
        cvc = rand.nextInt(1000);

        date.month = c.get(Calendar.MONTH)+1;
        date.year = c.get(Calendar.YEAR);

        c.clear();
        c.set(Calendar.MONTH, date.month);
        c.set(Calendar.YEAR, date.year+5);

        expDate = c.getTime();

        detail.cardNumber = cardNumber.toString();
        detail.pin = pin;
        detail.cvc = cvc;
        detail.expDate = expDate;

        return detail;
    }

    private static boolean CheckCardInDB(CardDetails details) {
        String sql = String.format("SELECT debit_cards FROM users WHERE debit_cards = '%s'", details.cardNumber);
        ResultSet rs = GetData(sql);
        JSONArray array = new JSONArray();

        try {
            while (rs.next()) {
                // Assuming that "debit_cards" is a column in the ResultSet
                String debitCardNumber = rs.getString("debit_cards");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // It's good practice to close the ResultSet and any other database resources
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Debit Card Number not found in Database");
        return false;
    }


    public static JSONArray GetDataByName(String fullName){
        String sql = String.format("select * from users where fullName = '%s'", fullName);

        rs = GetData(sql);
        JSONArray array = new JSONArray();

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
        String sql = String.format("insert into users values(null, '%s', '%s', '%s', %d, '%s', '%s', '9999')", name, password, dob.toString(), pin, phoneNumber, email);

        try{
            Statement st = conn.createStatement();
            st.execute(sql);
            System.out.println("Inserted Successfully!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void InsertDataInDBWithDebitCard(String name, String password, java.util.Date dob, int pin, String phoneNumber, String email, CardDetails details){
        Connection conn = connectDB();
        JSONObject obj = new JSONObject();
        obj.put("cardNumber", details.cardNumber);
        obj.put("pin", details.pin);
        obj.put("cvc", details.cvc);
        obj.put("expDateYear", details.expDate.getYear()+1900);
        obj.put("expDateMonth", details.expDate.getMonth());
        String sql = String.format("insert into users values(null, '%s', '%s', '%s', %d, '%s', '%s', '%s')", name, password, dob.toString(), pin, phoneNumber, email, obj.toString());

        try{
            Statement st = conn.createStatement();
            st.execute(sql);
            System.out.println("Inserted Successfully!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void InsertDebitCard(String name, String inputEmail){
        String sql = String.format("select * from users where email = '%s' and fullName = '%s'" , inputEmail, name);
        ResultSet rs = GetData(sql);
        JSONArray array = new JSONArray();

        try {
            while (rs.next()){
                JSONObject onj = new JSONObject();
                String debit_card = rs.getString("debit_cards");
                System.out.println(debit_card);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void CreateDebitCard(String name, String email, DebitCard type) {
        CardDetails cardDetails = GenerateCardDetails(type);
        cardDetails.cardNumber = "5158397123815249";

        if(CheckCardInDB(cardDetails)){
            cardDetails = GenerateCardDetails(type);
            while (CheckCardInDB(cardDetails)){
                cardDetails = GenerateCardDetails(type);
            }
        }
        InsertDebitCard(name, email);

    }

}
