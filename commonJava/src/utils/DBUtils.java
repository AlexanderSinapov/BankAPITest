package utils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.InetAddress;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DBUtils {

    // Base URL for API requests
    private static final String baseApiUrl = String.format("http://%s:8545/api", "20.67.242.144");

    // Variables to store session information and user data
    public static String emailSession;
    public static String authToken;
    private static String resetToken;
    public static JSONObject userData;

    // Array to store user's cards
    public static JSONArray cards = new JSONArray();

    // Method to get the IPv4 address of the local machine
    public static String getIPv4Address() {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            return localhost.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to send a registration request to the server
    public static boolean RequestRegister(String email, String password, String fullName, String dob, String phoneNumber) throws Exception {
        // Validation checks for required fields
        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || phoneNumber.isEmpty() || dob.isEmpty() || !email.contains("@"))
            return false;

        // Build and send the registration request
        HttpRequest request = HttpRequest.newBuilder()
                .setHeader("FullName", fullName)
                .setHeader("Email", email)
                .setHeader("Password", password)
                .setHeader("DOB", dob)
                .setHeader("PhoneNumber", phoneNumber)
                .GET()
                .uri(new URI(baseApiUrl + "/register/submit"))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        // Check the response status code
        return response.statusCode() == 200;
    }

    // Method to send a login request to the server
    public static boolean RequestLogin(String email, String password) throws Exception {
        // Validation checks for required fields
        if (email.isEmpty() || !email.contains("@") || password.isEmpty())
            return false;

        // Build and send the login request
        HttpRequest request = HttpRequest.newBuilder()
                .setHeader("Email", email)
                .setHeader("Password", password)
                .GET()
                .uri(new URI(baseApiUrl + "/login/submit"))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        // Check the response status code
        if (response.statusCode() == 200) {
            // Parse and store user authentication information
            JSONArray array = new JSONArray(response.body());
            JSONObject obj = array.getJSONObject(0);
            authToken = obj.getString("authToken");
            emailSession = email;
            return true;
        } else return false;
    }

    // Method to send the first step of the forgot password process
    public static boolean RequestForgotPasswordFirst(String inputEmail) throws Exception {
        // Validation checks for required fields
        if (inputEmail.isEmpty() || !inputEmail.contains("@"))
            return false;

        // Build and send the request
        HttpRequest request = HttpRequest.newBuilder()
                .setHeader("Email", inputEmail)
                .GET()
                .uri(new URI(baseApiUrl + "/forgotPassword"))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        // Check the response status code
        if (response.statusCode() == 200) {
            // Parse and store reset token
            JSONArray array = new JSONArray(response.body());
            JSONObject obj = array.getJSONObject(0);
            resetToken = obj.getString("resetToken");
            emailSession = inputEmail;
            return true;
        } else return false;
    }

    // Method to send the second step of the forgot password process
    public static boolean RequestForgotPasswordSecond(String pin) throws Exception {
        // Validation checks for required fields
        if (emailSession.isEmpty() || !emailSession.contains("@"))
            return false;

        // Build and send the request
        HttpRequest request = HttpRequest.newBuilder()
                .setHeader("Email", emailSession)
                .setHeader("PIN", pin)
                .setHeader("resetToken", resetToken)
                .GET()
                .uri(new URI(baseApiUrl + "/forgotPassword/pin"))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        // Check the response status code
        return response.statusCode() == 200;
    }

    // Method to send the final step of the forgot password process
    public static boolean RequestForgotPasswordFinal(String password) throws Exception {
        // Validation checks for required fields
        if (password.isEmpty())
            return false;

        // Build and send the request
        HttpRequest request = HttpRequest.newBuilder()
                .setHeader("Email", emailSession)
                .setHeader("resetToken", resetToken)
                .setHeader("newPassword", password)
                .GET()
                .uri(new URI(baseApiUrl + "/forgotPassword/password"))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        // Check the response status code
        if (response.statusCode() == 200) {
            emailSession = "";
            return true;
        } else return false;
    }

    // Method to send a request to add a new card
    public static boolean RequestNewCard(String type, String nickname, String customPin, Boolean CustomPinCheck) {
        // Validation checks for required fields
        if (CustomPinCheck && customPin.isEmpty())
            return false;
        if (nickname.isEmpty())
            nickname = type + " Card";
        if (customPin.isEmpty())
            customPin = "null";

        try {
            // Build and send the request
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

            // Check the response status code
            return response.statusCode() == 200;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to send a request to get the user's cards
    public static JSONArray RequestGetCards() {
        try {
            // Build and send the request
            HttpRequest request = HttpRequest.newBuilder()
                    .setHeader("Email", emailSession)
                    .setHeader("authToken", authToken)
                    .GET()
                    .uri(new URI(baseApiUrl + "/getdata/cards"))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Check the response status code
            if (response.statusCode() == 200) {
                try {
                    return new JSONArray(response.body());
                } catch (Exception e) {
                    return null;
                }
            } else return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to send a request to get user data
    public static JSONObject RequestGetData() {
        try {
            // Build and send the request
            HttpRequest request = HttpRequest.newBuilder()
                    .setHeader("Email", emailSession)
                    .setHeader("authToken", authToken)
                    .GET()
                    .uri(new URI(baseApiUrl + "/getdata"))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Check the response status code
            if (response.statusCode() == 200) {
                try {
                    return new JSONObject(response.body());
                } catch (Exception e) {
                    return null;
                }
            } else return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to send a request to check a card
    public static boolean RequestCheckCard(String cardNumber) {
        try {
            // Build and send the request
            HttpRequest request = HttpRequest.newBuilder()
                    .setHeader("Email", emailSession)
                    .setHeader("authToken", authToken)
                    .setHeader("CardNumber", cardNumber)
                    .GET()
                    .uri(new URI(baseApiUrl + "/checkcard"))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Check the response status code
            return response.statusCode() == 200;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to send a request to close a card
    public static boolean RequestCloseCard(String cardNumber) {
        try {
            // Build and send the request
            HttpRequest request = HttpRequest.newBuilder()
                    .setHeader("Email", emailSession)
                    .setHeader("authToken", authToken)
                    .setHeader("CardNumber", cardNumber)
                    .GET()
                    .uri(new URI(baseApiUrl + "/cards/remove"))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Check the response status code
            return response.statusCode() == 200;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
