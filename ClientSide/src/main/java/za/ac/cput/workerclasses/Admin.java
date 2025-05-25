package za.ac.cput.workerclasses;

import java.io.Serializable;

/**
 *
 * @author Samnkelo Ngxande 221311564
 * @author Sinhle Xiluva Mthethwa 221802797
 * @author Mose Mabina 230911498
 * 
 */

//This admin class is on the client side
public class Admin implements Serializable {
    
    //Declarations//////////////////////////////////////////////////////////////
    private String adminName;
    private String adminPassword;
    ////////////////////////////////////////////////////////////////////////////
    
    //Constructors//////////////////////////////////////////////////////////////
    public Admin() {
        
    }
    
    public Admin(String adminName, String adminPassword) {
        this.adminName = adminName;
        this.adminPassword = adminPassword;
    }
    ////////////////////////////////////////////////////////////////////////////
    
    //Setters And Getters
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    ////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return "Admin{" + "adminName=" + adminName + ", adminPassword=" + adminPassword + '}';
    }
    
}
