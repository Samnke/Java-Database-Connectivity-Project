package za.ac.cput.server_side;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;
import za.ac.cput.database.AdminDAO;
import za.ac.cput.database.VehicleDAO;
import za.ac.cput.workerclasses.Vehicle;

/**
 *
 * @author Samnkelo Ngxande 221311564
 * @author Sinhle Xiluva Mthethwa 221802797
 * @author Mose Mabina 230911498
 * 
 */
public class ServerSide {
    
    private ServerSocket svrSoc;
    private Socket soc;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private AdminDAO adminDAO;
    private VehicleDAO vehicleDAO;
    Vehicle vecObj = new Vehicle();
    
    //Constructor///////////////////////////////////////////////////////////////
    public ServerSide(Connection connection) {
        adminDAO = new AdminDAO(connection);
        vehicleDAO = new VehicleDAO(connection);
        
        try{
            svrSoc = new ServerSocket(1213, 1);
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    //Listen////////////////////////////////////////////////////////////////////
    public void listen() {
        try{
            
            System.out.println("Server is listening..");
            soc = svrSoc.accept();
            System.out.println("Client is connected..");
            
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    //Sreams////////////////////////////////////////////////////////////////////
    public void getSteams() {
        try{
        out = new ObjectOutputStream(soc.getOutputStream());
        out.flush();
        in = new ObjectInputStream(soc.getInputStream());
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    //Handle Requests///////////////////////////////////////////////////////////
    public void handleRequests() {
        
        try{
            Object receivedObject;
            while((receivedObject = in.readObject()) != null) {
                if(receivedObject instanceof String) {
                    String command =  (String) receivedObject;
                    
                    //Get Car List//////////////////////////////////////////////
                    if(command.equals("GET_CAR_LIST")) {
                        ArrayList<String> vehicle = vehicleDAO.getCarList();
                        
                        out.writeObject(vehicle);
                        out.flush();
                        System.out.println("Sent vehicle list to client..");
                    }
                    ////////////////////////////////////////////////////////////
                    //Get Car List With Votes
                    else if(command.equals("GET_CAR_LIST_AND_VOTES")) {
                        ArrayList<Vehicle> vehicle = vehicleDAO.getCarVoteList();
                        
                        out.writeObject(vehicle);
                        out.flush();
                        System.out.println("Sent vehicle list with votes to client..");
                    }
                    ////////////////////////////////////////////////////////////
                    //Update Car Vote
                    else if(command.equals("UPDATE_VOTE_NUMBER")) {
                        String carName = (String) in.readObject();
                        int carVote = (int) in.readObject();
                        
                        vehicleDAO.updateVote(carName, carVote);
                    }
                    ////////////////////////////////////////////////////////////
                    //Confirm admin
                    else if (command.equals("CONFIRM_ADMIN")) {
                        String receivedUsername = (String) in.readObject();
                        String password = adminDAO.confirmPass(receivedUsername);
                        
                        out.writeObject(password);
                        out.flush();
                        System.out.println("Password sent for confirmation");
                    }
                    ////////////////////////////////////////////////////////////
                    //Add a car
                    else if(command.equals("ADD_A_CAR")) {
                        Vehicle receivedVehicle = (Vehicle) in.readObject();
                        vehicleDAO.addCar(receivedVehicle);
                        System.out.println("Received vehicle" + vecObj.getVehicleName());
                    }
                }
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        
    }
    
}
