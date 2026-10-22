
package Model;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
    private static DBConnection conn;

    private String jdbcURL;
    private String username;
    private String password;
    
    private DBConnection()
    {
        jdbcURL = System.getenv("DB_URL");
        username =  System.getenv("DB_USER");
        password =  System.getenv("DB_PASS");
    }
    
    public static DBConnection getInstance()
    {
        if (conn == null)
        {
            conn = new DBConnection();
        }
        
        return conn;
    }
    
    public Connection createConnection() throws SQLException, ClassNotFoundException
    {
        Class.forName("org.postgresql.Driver");
        
        return DriverManager.getConnection(this.jdbcURL, this.username, this.password);
    }
}
