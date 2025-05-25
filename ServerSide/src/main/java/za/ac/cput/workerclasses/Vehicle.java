package za.ac.cput.workerclasses;

import java.io.Serializable;

/**
 *
 * @author Samnkelo Ngxande 221311564
 * @author Sinhle Xiluva Mthethwa 221802797
 * @author Mose Mabina 230911498
 * 
 */

//This worker class is for the server side

public class Vehicle implements Serializable {
    
    //Declarations//////////////////////////////////////////////////////////////
    private String vehicleName;
    private int vote;
    ////////////////////////////////////////////////////////////////////////////
    
    //Constructors//////////////////////////////////////////////////////////////
    public Vehicle() {
        
    }
    
    public Vehicle(String vehicleName, int vote) {
        this.vehicleName = vehicleName;
        this.vote = vote;
    }
    ////////////////////////////////////////////////////////////////////////////
    
    //Getters And Setters///////////////////////////////////////////////////////

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
    ////////////////////////////////////////////////////////////////////////////
    
    @Override
    public String toString() {
        return "Vehicle name: " + vehicleName + " Vote: " + vote;
    }
        
}
