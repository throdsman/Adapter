 
package Model;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseInitializer
{
    public static void initializeTables() {
        System.out.println("--- INICIANDO SERVLET CONTEXT LISTENER ---");
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
        
        System.out.println("--- INICIANDO SERVLET CONTEXT LISTENER ---");
        try (Connection conn = DBConnection.getInstance().createConnection(); // Your connection method
             Statement stmt = conn.createStatement()) {
            
            stmt.execute(createTableSQL);
            System.out.println("--- DB Creado de manera correcta ---");

        } catch (Exception e) {
            System.out.println("--- DB Failed to be created ---");
            e.printStackTrace();
        }
    }
}
