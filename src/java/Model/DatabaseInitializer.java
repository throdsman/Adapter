 
package Model;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseInitializer
{
    public static void initializeTables() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users ("
                + "user_id SERIAL PRIMARY KEY, "
                + "user_name VARCHAR(100) NOT NULL, "
                + "user_email VARCHAR(100) UNIQUE NOT NULL, "
                + "user_age INT NOT NULL, "
                + "user_pass VARCHAR(255) NOT NULL, "
                + "user_role VARCHAR(50) DEFAULT 'Basic', "
                + "user_failed_attempts INT DEFAULT 0, "
                + "user_lockout_time timestamp"
                + ");";

        try (Connection conn = DBConnection.getInstance().createConnection(); // Your connection method
             Statement stmt = conn.createStatement()) {
            
            stmt.execute(createTableSQL);
            System.out.println("Database tables verified/created successfully.");

        } catch (Exception e) {
            System.err.println("Failed to initialize database tables:");
            e.printStackTrace();
        }
    }
}
