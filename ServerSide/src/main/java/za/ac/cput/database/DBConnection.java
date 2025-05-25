package za.ac.cput.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Samnkelo Ngxande 221311564
 * @author Sinhle Xiluva Mthethwa 221802797
 * @author Mose Mabina 230911498
 * 
 */
public class DBConnection {
    
    public static final String dbURL = "jdbc:derby://localhost:1527/VehicleDatabase";
    public static final String username = "username";
    public static final String password = "password";
    
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(dbURL, username, password);
        System.out.println("Connection established");
        return connection;
    }
    
    public static void createT(Connection connection) throws SQLException {
        //Connection connection = getConnection();
        Statement statement = connection.createStatement(); 
        
        String createTable = "CREATE TABLE VEHICLES (" +
                        "CAR_ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                        "CAR_NAME VARCHAR(255) NOT NULL," +
                        "CAR_VOTE INT DEFAULT 0)";
        statement.execute(createTable);

        String[] carNames = {"Honda Civic Type R FL5", "Subaru WRX STI Premium",
                                "Nissan GT-R Black Edition", "Audi RS3",
                                "Mercedes-Benz AMG CLA45", "Hyundai i30N",
                                "BMW M2 CS", "VW Golf R", "Toyota Corolla GR"};
        for (String carName : carNames) {
            String insertCar = "INSERT INTO VEHICLES (CAR_NAME) VALUES ('" + carName + "')";
            statement.execute(insertCar);
        }

        statement.close();
        connection.close();
    }
    
}
