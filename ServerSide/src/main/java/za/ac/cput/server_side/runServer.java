package za.ac.cput.server_side;

import java.sql.Connection;
import java.sql.SQLException;
import za.ac.cput.database.DBConnection;
import za.ac.cput.server_side_gui.LogsGUI;

/**
 *
 * @author Samnkelo Ngxande 221311564
 * @author Sinhle Xiluva Mthethwa 221802797
 * @author Mose Mabina 230911498
 * 
 */
public class runServer {
    
    //Declaration///////////////////////////////////////////////////////////////
    //Connection connection;
    //ServerSide srvObj;
    
    //Main method
    public static void main(String[]args) throws SQLException {
        LogsGUI logsObj = new LogsGUI();
        Connection connection = DBConnection.getConnection();
        //DBConnection.createT(connection); // I commented this line out because the database table created by this line is already on the database now after the first run.
        ServerSide srvObj = new ServerSide(connection);
        srvObj.listen();
        srvObj.getSteams();
        srvObj.handleRequests();
    }
    
}
