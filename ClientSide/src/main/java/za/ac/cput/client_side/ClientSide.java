package za.ac.cput.client_side;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import za.ac.cput.GUI.ClientSideGUI;
import za.ac.cput.workerclasses.Admin;
import za.ac.cput.workerclasses.Vehicle;

/**
 *
 * @author Samnkelo Ngxande 221311564
 * @author Sinhle Xiluva Mthethwa 221802797
 * @author Mose Mabina 230911498
 * 
 */
public class ClientSide {
    
    //Declarations
    private Socket server;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    DefaultTableModel defTableModel;
    Vehicle vecObj;
    Admin adminObj = new Admin();
    //private Scanner sc = new Scanner(System.in); //For testing
    
    
    //Constructors//////////////////////////////////////////////////////////////
    public ClientSide() {
        //getStreams();
    }
    
    public ClientSide(int port) {
        try{
            server = new Socket("127.0.0.1", port);
            getStreams();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        System.out.println("connection requested..");
    }
    
    //Streams///////////////////////////////////////////////////////////////////
    public void getStreams() {
        try{
            out = new ObjectOutputStream(server.getOutputStream());
            out.flush();
            in = new ObjectInputStream(server.getInputStream());
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    
    //Send vote
    public void sendVoteToServer(String carName, int carVote) {
        try {
            out.writeObject("UPDATE_VOTE_NUMBER");
            out.writeObject(carName);
            out.writeObject(carVote);
            out.flush();
            
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    //Set vehicle
    public void setVehicle(String carName, int carVote) {
        vecObj = new Vehicle(carName, carVote);
        System.out.println("Car set to: " + vecObj.getVehicleName() + " Vote: " + vecObj.getVote());
    }
    
    
    //Requests//////////////////////////////////////////////////////////////////
    //Receiving Car List
    public void getCarList(ClientSideGUI clnSideGui) {
        try{
            out.writeObject("GET_CAR_LIST");
            out.flush();
            
            Object receivedObject = in.readObject();
            
            if(receivedObject instanceof ArrayList) {
                ArrayList<String> vehicle = (ArrayList<String>) receivedObject;
                System.out.println("Received car list from server: " + vehicle);
                clnSideGui.populateComboBox(vehicle);
            }
            else{
                System.out.println("Car list retrieval failed..");
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }
    //Receiving Car List With Votes
    public void getCarVoteList(ClientSideGUI clnSideGui) {
        try{
            out.writeObject("GET_CAR_LIST_AND_VOTES");
            out.flush();
            
            Object receivedObject = in.readObject();
            
            if(receivedObject instanceof ArrayList) {
                ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) receivedObject;
                System.out.println("Received student list from server..");
                
                defTableModel = new DefaultTableModel();
                defTableModel.addColumn("CAR NAMES");
                defTableModel.addColumn("NUMBER OF VOTES");
                
                for(Vehicle vecObj: vehicles) {
                    Object[] rowData = {vecObj.getVehicleName(), vecObj.getVote()};
                    defTableModel.addRow(rowData);
                }
                clnSideGui.jTable.setModel(defTableModel);
                //StringBuilder stringBuilder = new StringBuilder();
                //for(Vehicle vecObj : vehicles) {
                //    stringBuilder.append(vecObj.toString()).append("\n\n");
                //    
                //    clnSideGui.textArea.setText(stringBuilder.toString());
                //}
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }
    
    //Confirm admin
    public void confirmAdmin(ClientSideGUI clnSideGui, String username) {
        
        try{
            out.writeObject("CONFIRM_ADMIN");
            out.writeObject(username);
            out.flush();
            
            Object receivedObject = in.readObject();
            if(receivedObject instanceof String) {
                String password = (String) receivedObject;
                System.out.println("received password from server: " + password);
                //adminObj.setAdminPassword(password);
                clnSideGui.setPassword(password);
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }
    
    //Send vehicle
    public void sendVehicle() {
        try {
            out.writeObject("ADD_A_CAR");
            out.writeObject(vecObj);
            out.flush();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        
    }
    
}
