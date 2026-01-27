package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class DBConnection {

    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

  
    static {
        URL = dotenv.get("DB_URL", "jdbc:mysql://localhost:3306/college");
        USER = dotenv.get("DB_USER", "root");
        PASSWORD = dotenv.get("DB_PASSWORD");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: MySQL JDBC Driver not found.");
            e.printStackTrace();
        }
    }


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
