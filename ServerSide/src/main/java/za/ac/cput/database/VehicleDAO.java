package za.ac.cput.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import za.ac.cput.server_side_gui.LogsGUI;
import za.ac.cput.workerclasses.Vehicle;

/**
 *
 * @author Samnkelo Ngxande 221311564
 * @author Sinhle Xiluva Mthethwa 221802797
 * @author Mose Mabina 230911498
 * 
 */
public class VehicleDAO {
    
    //Declarations//////////////////////////////////////////////////////////////
    private Connection connection;
    Vehicle vehicleObj = new Vehicle();
    LogsGUI logs = new LogsGUI();
    //Constructor///////////////////////////////////////////////////////////////
    public VehicleDAO(Connection connection) {
        this.connection = connection;
    }
    
    //----------------------------CRUD Operations-----------------------------//
    //--------------------------------------------------------------------------
    
    //Get car list only
    public ArrayList<String> getCarList() {
        ArrayList<String> vehicle = new ArrayList<>();
        String query = "SELECT CAR_NAME FROM VEHICLES";
        
        logs.textArea.append("\nRequest received: [Populate combobox]");
        
        try(Statement statement = connection.createStatement(); ResultSet resSet = statement.executeQuery(query)) {
            while(resSet.next()) {
                String vehicle_ = resSet.getString("CAR_NAME");
                vehicle.add(vehicle_);
            }
            logs.textArea.append("\nCombobox populated: " + vehicle +"\n");
        }
        catch(SQLException sqle) {
            sqle.printStackTrace();
        }
        return vehicle;
    }
    
    //Get Car List With Votes
    public ArrayList<Vehicle> getCarVoteList() {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String sqlQuery = "SELECT * FROM VEHICLES";
        
        logs.textArea.append("\nRequest received: [Veiw vehicle and vote list]");
        
        try(Statement statement = connection.createStatement(); ResultSet resSet = statement.executeQuery(sqlQuery)) {
            
            while(resSet.next()) {
                String carName = resSet.getString("CAR_NAME");
                Integer carVote = resSet.getInt("CAR_VOTE");
                
                Vehicle vehicle = new Vehicle(carName, carVote);
                vehicles.add(vehicle);
            }
            logs.textArea.append("\nVehicle and vote list sent to client: \n" + vehicles +"\n");
        }
        catch(SQLException sqle) {
            sqle.printStackTrace();
        }
        return vehicles;
    }
    
    //Update votes
    public void updateVote(String carName, int carVote) {
        
        String updateQuery = "UPDATE VEHICLES SET CAR_VOTE = CAR_VOTE+? WHERE CAR_NAME = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setInt(1, carVote);
            statement.setString(2, carName);
            logs.textArea.append("\nVote received:" + "\nVehicle voted for: " + carName +"\n");
            
            int rowsAffected = statement.executeUpdate();
            connection.commit();
            
            if(rowsAffected > 0) {
                //JOptionPane.showMessageDialog(null, "Vote successfull");
                System.out.println("Vote successfull");
            }
            else{
                JOptionPane.showMessageDialog(null, "Error, try again..", "Error", JOptionPane.OK_OPTION);
            }
        }
        catch(SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    //Add a vehicle
    public void addCar(Vehicle vehicle) {
        String insertQuery = "INSERT INTO VEHICLES(CAR_NAME, CAR_VOTE) VALUES (?,?)";
        
        logs.textArea.append("\nVehicle received:");
        
        
        try(PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, vehicle.getVehicleName());
            preparedStatement.setInt(2, vehicle.getVote());
            
            int rowsAffected = preparedStatement.executeUpdate();
            connection.commit();
            
            if(rowsAffected > 0) {
                logs.textArea.append("\nName of received vehicle: " + vehicle);
                logs.textArea.append("\nVehicle addad to database");
                JOptionPane.showMessageDialog(null, "Vehicle added successful");
            }
            else {
                JOptionPane.showMessageDialog(null, "Error, failed to add car", "Error", JOptionPane.OK_OPTION);
            }
        }
        catch(SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
