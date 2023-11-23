package utils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.InetAddress;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class DBUtils {

    private static final String baseApiUrl = String.format("http://%s:8545/api", getIPv4Address());

    private static String emailSession;
    private static String authToken;
    private static String resetToken;

    //Path to DB
    //private static final String url = "jdbc:sqlite:commonJava/src/database.db";

    //Unique bank identifier
    //private static final String bankIdentifier = "158";

    //Holder for month and year data
//    private static class DateHolder{
//        int month;
//        int year;
//    }

    //Card details class which holds the most used and needed data to process a request
    //Also instead of having to send 4 variables you only need to send 1 variable
//    public static class CardDetails{
//        int pin;
//        int cvc;
//        int expMonth;
//        int expYear;
//        String cardNumber;
//    }

    //The 2 different debit card types
//    public enum DebitCard{
//        Visa,
//        MasterCard
//    }

    public static String getIPv4Address() {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            return localhost.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static boolean RequestRegister(String email, String password, String fullName, String dob, String phoneNumber) throws Exception{
        if(fullName.isEmpty()) return false;
        if(email.isEmpty()) return false;
        if(password.isEmpty()) return false;
        if(phoneNumber.isEmpty()) return false;
        if(dob.isEmpty()) return false;
        if(!email.contains("@")) return false;

        HttpRequest request = HttpRequest.newBuilder()
                .setHeader("FullName", fullName)
                .setHeader("Email", email)
                .setHeader("Password", password)
                .setHeader("DOB", dob)
                .setHeader("PhoneNumber", phoneNumber)
                .GET()
                .uri(new URI(baseApiUrl+"/register/submit"))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200){
            System.out.println(response);
            System.out.println(response.headers());
            return true;
        } else return false;
    }

    public static boolean RequestLogin(String email, String password) throws Exception {
        if(email.isEmpty()) return false;
        if(!email.contains("@")) return false;
        if(password.isEmpty()) return false;

        HttpRequest request = HttpRequest.newBuilder()
                .setHeader("Email", email)
                .setHeader("Password", password)
                .GET()
                .uri(new URI(baseApiUrl+"/login/submit"))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200){
            JSONArray array = new JSONArray(response.body());
            JSONObject obj = array.getJSONObject(0);
            authToken = obj.getString("authToken");
            emailSession = email;
            return true;
        } else return false;

    }

    public static boolean RequestForgotPasswordFirst(String inputEmail) throws Exception {
        if(inputEmail.isEmpty()) return false;
        if(!inputEmail.contains("@")) return false;

        HttpRequest request = HttpRequest.newBuilder()
                .setHeader("Email", inputEmail)
                .GET()
                .uri(new URI(baseApiUrl+"/forgotPassword"))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200){
            JSONArray array = new JSONArray(response.body());
            JSONObject obj = array.getJSONObject(0);
            resetToken = obj.getString("resetToken");
            emailSession = inputEmail;
            return true;
        } else return false;
    }

    public static boolean RequestForgotPasswordSecond(String pin) throws Exception {
        if(emailSession.isEmpty()) return false;
        if(!emailSession.contains("@")) return false;

        HttpRequest request = HttpRequest.newBuilder()
                .setHeader("Email", emailSession)
                .setHeader("PIN", pin)
                .setHeader("resetToken", resetToken)
                .GET()
                .uri(new URI(baseApiUrl+"/forgotPassword/pin"))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode() == 200;
    }

    public static boolean RequestForgotPasswordFinal(String password) throws Exception {
        if(password.isEmpty()) return false;

        HttpRequest request = HttpRequest.newBuilder()
                .setHeader("Email", emailSession)
                .setHeader("resetToken", resetToken)
                .setHeader("newPassword", password)
                .GET()
                .uri(new URI(baseApiUrl+"/forgotPassword/password"))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200){
            emailSession = "";
            return true;
        } else return false;
    }

    public static boolean RequestNewCard(String type, String nickname, String customPin){
        if(nickname.isEmpty()) nickname = type +" Card";
        if(customPin.isEmpty()) customPin = "null";

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .setHeader("Email", emailSession)
                    .setHeader("authToken", authToken)
                    .setHeader("PIN", customPin)
                    .setHeader("nickname", nickname)
                    .setHeader("CardType", type)
                    .GET()
                    .uri(new URI(baseApiUrl + "/cards/add"))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            return response.statusCode() == 200;

        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static JSONArray RequestGetCards(){
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .setHeader("Email", emailSession)
                    .setHeader("authToken", authToken)
                    .GET()
                    .uri(new URI(baseApiUrl + "/getdata/cards"))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() == 200){
                return new JSONArray(response.body());
            } else return null;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

//    public static boolean CheckIfAdult(java.util.Date dob){
//        System.out.println(dob.toString());
//        int DOBYear = dob.getYear()+1900;
//        int age = Calendar.getInstance().get(Calendar.YEAR) - DOBYear;
//        System.out.println(age);
//
//        if(age >= 18){
//            return true;
//        } else {
//            return false;
//        }
//    }

    //Method to create and return the connection to the method caller
//    private static Connection connectDB(){
//        try {
//            Class.forName("org.sqlite.JDBC");
//
//            return DriverManager.getConnection(url);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    //A method fir running SQL queries
//    //Example: Alter table, drop column, create table and etc.
//    public static boolean ExecuteSQL(String sql){
//        Connection conn = connectDB();
//
//        try {
//            assert conn != null;
//            Statement st = conn.createStatement();
//            st.execute(sql);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    //A method for getting and returning a ResultSet variable from DB
//    private static ResultSet GetData(String sql){
//        Connection conn = connectDB();
//
//        try {
//            assert conn != null;
//            PreparedStatement p = conn.prepareStatement(sql);
//            return p.executeQuery();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    //A method for generating all the information for the debit card
//    public static CardDetails GenerateCardDetails(DebitCard type){
//        StringBuilder cardNumber = new StringBuilder();
//        StringBuilder pin = new StringBuilder();
//        StringBuilder cvc = new StringBuilder();
//        Calendar c = Calendar.getInstance();
//        DateHolder date = new DateHolder();
//        java.util.Date expDate;
//        Random rand = new Random();
//        CardDetails detail = new CardDetails();
//
//        //Generating Card Number
//        if (type == DebitCard.Visa){
//            cardNumber.append("4");
//        }
//        else {
//            cardNumber.append("5");
//        }
//        cardNumber.append(bankIdentifier);
//        while (cardNumber.length() < 16){
//            cardNumber.append(rand.nextInt(10));
//        }
//
//        //Generate PIN for card use at POS terminals and ATMs
//        pin.append(rand.nextInt(1, 10));
//        pin.append(rand.nextInt(10));
//        pin.append(rand.nextInt(10));
//        pin.append(rand.nextInt(10));
//
//        //Generate CVC for card use at online POS terminals
//        cvc.append(rand.nextInt(1, 10));
//        cvc.append(rand.nextInt(10));
//        cvc.append(rand.nextInt(10));
//
//        //Calculate expiration date
//        date.month = c.get(Calendar.MONTH)+1;
//        date.year = c.get(Calendar.YEAR);
//        c.clear();
//        c.set(Calendar.MONTH, date.month);
//        c.set(Calendar.YEAR, date.year+5);
//        expDate = c.getTime();
//
//        detail.cardNumber = cardNumber.toString();
//        detail.pin = Integer.parseInt(pin.toString());
//        detail.cvc = Integer.parseInt(cvc.toString());
//        detail.expMonth = expDate.getMonth()+1;
//        detail.expYear = expDate.getYear()+1900;
//
//        return detail;
//    }
//
//    //A method for checking if the card number is already in DB
//    //If there is one already one in DB returns true
//    //If not returns false
//    private static boolean CheckCardInDB(CardDetails details) {
//        String sql = String.format("SELECT * FROM debitCards WHERE debitCardNumber = '%s'", details.cardNumber);
//        ResultSet rs = GetData(sql);
//
//        try {
//            while (rs.next()) {
//                return true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return false;
//    }
//
//    //A method for checking if an email already exist
//    private static boolean CheckEmail(String email){
//        String sql = String.format("SELECT * FROM users WHERE email = '%s'", email);
//        ResultSet rs = GetData(sql);
//
//        try {
//            while (rs.next()) {
//                return true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return false;
//    }
//
//    //A method for getting and returning a JSONArray of all data with the same email
//    public static JSONArray GetDataByEmail(String inputEmail){
//        String sql = String.format("select * from users where email = '%s'", inputEmail);
//
//        ResultSet rs = GetData(sql);
//        JSONArray array = new JSONArray();
//
//        try{
//            while (rs.next()){
//                JSONObject item = new JSONObject();
//                int id = rs.getInt("id");
//                String name = rs.getString("fullName");
//                String password = rs.getString("password");
//                String dob = rs.getString("DOB");
//                String phoneNumber = rs.getString("phoneNumber");
//                String email = rs.getString("email");
//
//                item.put("id", id);
//                item.put("name", name);
//                item.put("password", password);
//                item.put("dob", dob);
//                item.put("phoneNumber", phoneNumber);
//                item.put("email", email);
//                array.put(item);
//            }
//            return array;
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    //A method for creating a user and inserting to DB
//    public static void InsertUserInDB(String name, String password, java.util.Date dob, String phoneNumber, String email){
//        Connection conn = connectDB();
//        String sql = String.format("insert into users values(null, '%s', '%s', '%s', '%s', '%s')", name, password, phoneNumber, email, dob.toString());
//
//        if(ExecuteSQL(sql)){
//            System.out.println("User inserted successfully!");
//        } else {
//            System.out.println("User insertion failed!!");
//        }
//    }
//
//    //A method for inserting card into DB
//    public static void InsertDebitCard(String name, String email, CardDetails details){
//        String sql = String.format("INSERT INTO debitCards VALUES(NULL, '%s', '%s', '%s', %d, %d, %d, %d)", name, email,
//                details.cardNumber, details.cvc, details.pin, details.expMonth, details.expYear);
//
//        if(ExecuteSQL(sql)){
//            System.out.println("Card inserted successfully!");
//        } else {
//            System.out.println("Card insertion failed!!");
//        }
//    }
//
//    //If you want to create a card and insert it into DB run this
//    //How it runs:
//    //First it calls GenerateCardDetails() which generates card details based on if you want MasterCard or Visa type card
//    //After that is done it checks if another card with that card number already exists in DB
//    //If there isn't one in DB already it inserts the card into DB
//    //Else it generates new card details until one which doesn't already exist is created. Then it gets inserted in DB
//    public static void CreateDebitCard(String name, String email, DebitCard type) {
//        CardDetails cardDetails = GenerateCardDetails(type);
//
//        if(CheckCardInDB(cardDetails)){
//            cardDetails = GenerateCardDetails(type);
//            while (CheckCardInDB(cardDetails)){
//                cardDetails = GenerateCardDetails(type);
//            }
//        }
//        InsertDebitCard(name, email, cardDetails);
//    }
//
//    //A request method for login in the user
//    public static boolean RequestLogin(String email, String password){
//        if (email.isEmpty()){
//            return false;
//        }
//        if(password.isEmpty()){
//            return false;
//        }
//
//        JSONArray data = GetDataByEmail(email);
//
//        if(data != null && !data.isEmpty()){
//            JSONObject obj = data.getJSONObject(0);
//            return Objects.equals(password, obj.getString("password"));
//        } else {
//            return false;
//        }
//    }
//
//    //A request method for registering a new user
//    public static boolean RequestRegister(String name, String email, String password, String phoneNumber, String DOB){
//        if(name.isEmpty()) return false;
//        if(email.isEmpty()) return false;
//        if(password.isEmpty()) return false;
//        if(phoneNumber.isEmpty()) return false;
//        if(DOB.isEmpty()) return false;
//        java.util.Date dobInDate = new java.util.Date(DOB);
//        if (!CheckIfAdult(dobInDate)) return false;
//        if(!email.contains("@")) return false;
//
//        if(!CheckEmail(email)){
//            InsertUserInDB(name, password, dobInDate, phoneNumber, email);
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    //A method for checking if user is above or 18 years old
}
