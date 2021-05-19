package singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asunawesker
 */
public class ConnectDB {
    
     private static ConnectDB connectionInstance = null;
    private Connection connectionDB = null;     
    private final String URL = "jdbc:postgresql://localhost:5432/uv";
    private final String USER = "postgres";
    private final String PASSWORD = "yamaha112";
    
    private ConnectDB() {
        try {
            connectionDB = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Felicidades, no eres tan inútil");            
        } catch(SQLException e) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.WARNING, null, e);            
        }
    }
    
    public static ConnectDB getInstance() throws SQLException{
        if (connectionInstance == null) 
            connectionInstance = new ConnectDB();
                   
        return connectionInstance;
    }
    
    public boolean execute(TransactionDB transaction) {
        boolean response = transaction.execute(connectionDB);
        return response;
    }
    
}
