package co.edu.uniminuto.pa.bds;
        
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySqlDataSource { 
    // Declare the JDBC objects.  
    private static SQLServerDataSource miMySqlDS = null;
    private static SQLServerDataSource ds = null;
    
    private MySqlDataSource(){
        ds = new SQLServerDataSource();
        ds.setUser("adminmaru");  
        ds.setPassword("Maru1234!");  
        ds.setServerName("bdserverarq.database.windows.net");  
        ds.setPortNumber(1433);   
        ds.setDatabaseName("bdserver");        
    }
    
    public static Connection getConexionBD()
    {
        try {
            if (miMySqlDS == null)
            {
                miMySqlDS = new SQLServerDataSource();            
            }
            
            return ds.getConnection();
            
        } catch (SQLException ex) {
            Logger.getLogger(SQLServerDataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
    
    
    
}
