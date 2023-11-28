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

    public static String emailSession;
    public static String authToken;
    private static String resetToken;
    public static JSONObject userData;

    public static JSONArray cards = new JSONArray();

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

    public static boolean RequestNewCard(String type, String nickname, String customPin, Boolean CustomPinCheck){
        if(CustomPinCheck && customPin.isEmpty()) return false;
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
                try {
                    return new JSONArray(response.body());
                } catch (Exception e){
                    return null;
                }
            } else return null;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject RequestGetData(){
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .setHeader("Email", emailSession)
                    .setHeader("authToken", authToken)
                    .GET()
                    .uri(new URI(baseApiUrl + "/getdata"))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() == 200){
                try {
                    return new JSONObject(response.body());
                } catch (Exception e){
                    return null;
                }
            } else return null;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static boolean RequestCheckCard(String cardNumber){
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .setHeader("Email", emailSession)
                    .setHeader("authToken", authToken)
                    .setHeader("CardNumber", cardNumber)
                    .GET()
                    .uri(new URI(baseApiUrl + "/checkcard"))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            return response.statusCode() == 200;

        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static boolean RequestCloseCard(String cardNumber){
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .setHeader("Email", emailSession)
                    .setHeader("authToken", authToken)
                    .setHeader("CardNumber", cardNumber)
                    .GET()
                    .uri(new URI(baseApiUrl + "/cards/remove"))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            return response.statusCode() == 200;

        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
