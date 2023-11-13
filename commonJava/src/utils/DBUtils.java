package utils;
import java.sql.*;
import java.util.Calendar;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;

public class DBUtils {

    //Path to DB
    private static final String url = "jdbc:sqlite:commonJava/src/database.db";

    //Unique bank identifier
    private static final String bankIdentifier = "158";

    //Holder for month and year data
    private static class DateHolder{
        int month;
        int year;
    }

    //Card details class which holds the most used and needed data to process a request
    //Also instead of having to send 4 variables you only need to send 1 variable
    public static class CardDetails{
        int pin;
        int cvc;
        int expMonth;
        int expYear;
        String cardNumber;
    }

    //The 2 different debit card types
    public enum DebitCard{
        Visa,
        MasterCard
    }

    //Method to create and return the connection to the method caller
    private static Connection connectDB(){
        try {
            Class.forName("org.sqlite.JDBC");

            return DriverManager.getConnection(url);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //A method fir running SQL queries
    //Example: Alter table, drop column, create table and etc.
    public static boolean ExecuteSQL(String sql){
        Connection conn = connectDB();

        try {
            assert conn != null;
            Statement st = conn.createStatement();
            st.execute(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //A method for getting and returning a ResultSet variable from DB
    private static ResultSet GetData(String sql){
        Connection conn = connectDB();

        try {
            assert conn != null;
            PreparedStatement p = conn.prepareStatement(sql);
            return p.executeQuery();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //A method for generating all the information for the debit card
    public static CardDetails GenerateCardDetails(DebitCard type){
        StringBuilder cardNumber = new StringBuilder();
        StringBuilder pin = new StringBuilder();
        StringBuilder cvc = new StringBuilder();
        Calendar c = Calendar.getInstance();
        DateHolder date = new DateHolder();
        java.util.Date expDate;
        Random rand = new Random();
        CardDetails detail = new CardDetails();

        //Generating Card Number
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

        //Generate PIN for card use at POS terminals and ATMs
        pin.append(rand.nextInt(1, 10));
        pin.append(rand.nextInt(10));
        pin.append(rand.nextInt(10));
        pin.append(rand.nextInt(10));

        //Generate CVC for card use at online POS terminals
        cvc.append(rand.nextInt(1, 10));
        cvc.append(rand.nextInt(10));
        cvc.append(rand.nextInt(10));

        //Calculate expiration date
        date.month = c.get(Calendar.MONTH)+1;
        date.year = c.get(Calendar.YEAR);
        c.clear();
        c.set(Calendar.MONTH, date.month);
        c.set(Calendar.YEAR, date.year+5);
        expDate = c.getTime();

        detail.cardNumber = cardNumber.toString();
        detail.pin = Integer.parseInt(pin.toString());
        detail.cvc = Integer.parseInt(cvc.toString());
        detail.expMonth = expDate.getMonth()+1;
        detail.expYear = expDate.getYear()+1900;

        return detail;
    }

    //A method for checking if the card number is already in DB
    //If there is one already one in DB returns true
    //If not returns false
    private static boolean CheckCardInDB(CardDetails details) {
        String sql = String.format("SELECT * FROM debitCards WHERE debitCardNumber = '%s'", details.cardNumber);
        ResultSet rs = GetData(sql);

        try {
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //A method for getting and returning a JSONArray of all data with the same email
    public static JSONArray GetDataByEmail(String inputEmail){
        String sql = String.format("select * from users where email = %s", inputEmail);

        ResultSet rs = GetData(sql);
        JSONArray array = new JSONArray();

        try{
            while (rs.next()){
                JSONObject item = new JSONObject();
                int id = rs.getInt("id");
                String name = rs.getString("fullName");
                String password = rs.getString("password");
                Date dob = rs.getDate("DOB");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");

                item.put("id", id);
                item.put("name", name);
                item.put("password", password);
                item.put("dob", dob);
                item.put("phoneNumber", phoneNumber);
                item.put("email", email);
                array.put(item);
            }
            return array;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //A method for creating a user and inserting to DB
    public static void InsertUserInDB(String name, String password, java.util.Date dob, String phoneNumber, String email){
        Connection conn = connectDB();
        String sql = String.format("insert into users values(null, '%s', '%s', '%s', '%s', '%s')", name, password, dob.toString(), phoneNumber, email);

        if(ExecuteSQL(sql)){
            System.out.println("User inserted successfully!");
        } else {
            System.out.println("User insertion failed!!");
        }
    }

    //A method for inserting card into DB
    public static void InsertDebitCard(String name, String email, CardDetails details){
        String sql = String.format("INSERT INTO debitCards VALUES(NULL, '%s', '%s', '%s', %d, %d, %d, %d)", name, email,
                details.cardNumber, details.cvc, details.pin, details.expMonth, details.expYear);

        if(ExecuteSQL(sql)){
            System.out.println("Card inserted successfully!");
        } else {
            System.out.println("Card insertion failed!!");
        }
    }

    //If you want to create a card and insert it into DB run this
    //How it runs:
    //First it calls GenerateCardDetails() which generates card details based on if you want MasterCard or Visa type card
    //After that is done it checks if another card with that card number already exists in DB
    //If there isn't one in DB already it inserts the card into DB
    //Else it generates new card details until one which doesn't already exist is created. Then it gets inserted in DB
    public static void CreateDebitCard(String name, String email, DebitCard type) {
        CardDetails cardDetails = GenerateCardDetails(type);

        if(CheckCardInDB(cardDetails)){
            cardDetails = GenerateCardDetails(type);
            while (CheckCardInDB(cardDetails)){
                cardDetails = GenerateCardDetails(type);
            }
        }
        InsertDebitCard(name, email, cardDetails);
    }
}
