import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddColumnExample {

    public static void main(String[] args) {
        Class.forName("org.sqlite.JDBC");
        String url = "C:\\Users\\Monkata\\IdeaProjects\\BankAPITest\\commonJava\\src\\database.db";

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {

            // SQL statement to add a new column
            String alterTableQuery = "ALTER TABLE users ADD COLUMN debitCards TEXT";

            // Execute the ALTER TABLE statement
            statement.execute(alterTableQuery);

            System.out.println("Column added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
