package za.ac.cput.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Samnkelo Ngxande 221311564
 * @author Sinhle Xiluva Mthethwa 221802797
 * @author Mose Mabina 230911498
 * 
 */
public class AdminDAO {
    //Declarations
    private Connection connection;
    
    //Constructor
    public AdminDAO(Connection connection) {
        this.connection = connection;
    }
    
    //----------------------------CRUD Operations-----------------------------//
    //--------------------------------------------------------------------------

    //Confirm Admin
    public String confirmPass(String username) {
        
        String pass = "";
        String sqlQuery = "SELECT ADMIN_PASSWORD FROM ADMIN_TABLE WHERE ADMIN_ID = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, username);
            ResultSet resSet = statement.executeQuery();
            
            if(resSet.next()) {
                return resSet.getString("ADMIN_PASSWORD");
            } else{
                return null;
            }
        }
        catch(SQLException sqle) {
            sqle.printStackTrace();
            return null;
        }
    }

}
