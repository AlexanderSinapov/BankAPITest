package main;
import org.json.JSONArray;
import utils.DBUtils;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Window window = new Window();
        // Just for testing don't worry
        //DBUtils.InsertDataInDB("Simeon Petkov Petkov", "test123", new Date(1990,04,9), 1963, "0885414963", "spetkov273@gmail.com");
//        JSONArray result = DBUtils.GetDataByName("Simeon Petkov Petkov");
//        System.out.println(result.toString());
        //Output:
        //[{"password":"test123","phoneNumber":"0885414963","pin":1963,"dob":"Fri May 09 00:00:00 EEST 3890","name":"Simeon Petkov Petkov","id":1,"debit_cards":"","email":"spetkov273@gmail.com"}]

        //DBUtils.CreateDebitCard("Simeon Petkov Petkov", "spetkov273@gmail.com", DBUtils.DebitCard.MasterCard);

        //DBUtils.InsertDataInDBWithDebitCard("Simeon Petkov Petkov", "test123", new Date(1990,04,9), 1963, "0885414963", "spetkov273@gmail.com", DBUtils.GenerateCardDetails(DBUtils.DebitCard.MasterCard));
//        String sql = "CREATE TABLE debitCards (" +
//                "id INTEGER PRIMARY KEY," +
//                "fullName TEXT NOT NULL," +
//                "email TEXT NOT NULL," +
//                "debitCardNumber TEXT NOT NULL," +
//                "cvc INTEGER NOT NULL," +
//                "pin INTEGER NOT NULL," +
//                "expMonth INTEGER NOT NULL," +
//                "expYear INTEGER NOT NULL" +
//                ");";
//
//        DBUtils.ExecuteSQL(sql);
    }
}