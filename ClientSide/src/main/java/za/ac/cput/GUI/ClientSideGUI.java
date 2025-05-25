package za.ac.cput.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import za.ac.cput.client_side.ClientSide;
import za.ac.cput.workerclasses.Admin;

/**
 *
 * @author Samnkelo Ngxande 221311564
 * @author Sinhle Xiluva Mthethwa 221802797
 * @author Mose Mabina 230911498
 * 
 */
public class ClientSideGUI extends JFrame implements ActionListener {
    
    //Start Of Declarations/////////////////////////////////////////////////////
    
    //Panels
    private JPanel topPanel;
    private JPanel centrePanel;
    private JPanel containerPanel;
    private JPanel textAreaPanel;
    private JPanel buttonPanel;
    
    //Labels
    private JLabel titleLabel;
    private JLabel vehicleLabel;
    
    //Combo Box
    private JComboBox<String> comboBox;
    private JScrollPane cBoxScrollPane;
    //private ArrayList list = new ArrayList();
    
    
    //Text Area
    public JTextArea textArea;
    private JScrollPane scrollPane;
    
    //Table
    public DefaultTableModel tableModel;
    public JTable jTable;
    public JScrollPane tablePane;
    
    //Buttons
    private JButton vote;
    private JButton add;
    private JButton view;
    private JButton exit;
    
    //Other
    ClientSide clnSide = new ClientSide(1213);
    Admin adminObj = new Admin();
    
    //End Of Declarations///////////////////////////////////////////////////////
    
    public ClientSideGUI() {
        //ClientSide clnSide = new ClientSide(1213);
        //Panels////////////////////////////////////////////////////////////////
        topPanel = new JPanel();
        centrePanel = new JPanel();
        containerPanel = new JPanel();
        textAreaPanel = new JPanel();
        buttonPanel = new JPanel();
        
        topPanel.setBackground(new Color(0x16324F));
        buttonPanel.setBackground(new Color(0x16324F));
        centrePanel.setBackground(Color.WHITE);
        
        topPanel.setPreferredSize(new Dimension(50,50));
        centrePanel.setPreferredSize(new Dimension(350,250));
        containerPanel.setPreferredSize(new Dimension(700,500));
        textAreaPanel.setPreferredSize(new Dimension(350,250));
        buttonPanel.setPreferredSize(new Dimension(40,40));
        
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        containerPanel.setLayout(new GridLayout(2,1));
        centrePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        textAreaPanel.setLayout(new GridLayout());
        ////////////////////////////////////////////////////////////////////////
        
        //Labels////////////////////////////////////////////////////////////////
        titleLabel = new JLabel("CAR OF THE YEAR");
        vehicleLabel = new JLabel("Vehicle");
        
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("MONOSPACED", Font.BOLD,20));
        vehicleLabel.setFont(new Font("SANS-SARIF", Font.BOLD, 13));
        ////////////////////////////////////////////////////////////////////////
        
        //Combo Box/////////////////////////////////////////////////////////////
        //comboBoxContent[0] = "Select Vehicle";
        //comboBoxContent[1] = "Hyundai i30N";
        //comboBoxContent[2] = "Honda Civic Type R FL5";
        //comboBoxContent[3] = "Audi RS3";
        //comboBoxContent[4] = "VW Golf R";
        //comboBoxContent[5] = "BMW M2 CS";
        //comboBoxContent[6] = "Mercedes-benz Amg CLA45";
        //comboBoxContent[7] = "Toyota Corolla GR";
        //comboBox = new JComboBox(comboBoxContent);
        comboBox = new JComboBox();
        cBoxScrollPane = new JScrollPane(comboBox);
        
        comboBox.addItem("Select Vehicle");
        clnSide.getCarList(this);System.out.println("getCarList(this) works..");
        comboBox.addActionListener(this);
        ////////////////////////////////////////////////////////////////////////
        
        //Text Area/////////////////////////////////////////////////////////////
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        ////////////////////////////////////////////////////////////////////////
        
        //Table/////////////////////////////////////////////////////////////////
        tableModel = new DefaultTableModel();
        tableModel.addColumn("CAR NAMES");
        tableModel.addColumn("NUMBER OF VOTES");
        
        jTable = new JTable(tableModel);
        
        tablePane = new JScrollPane(jTable);
        
        //Buttons
        vote = new JButton("Vote");
        add = new JButton("Add");
        view = new JButton("View");
        exit = new JButton("Exit");
        
        vote.setFocusable(false);
        add.setFocusable(false);
        view.setFocusable(false);
        exit.setFocusable(false);
        
        vote.addActionListener(this);
        add.addActionListener(this);
        view.addActionListener(this);
        exit.addActionListener(this);
        
        //Add To Panels
        topPanel.add(titleLabel);
        containerPanel.add(centrePanel);
        containerPanel.add(textAreaPanel);
        centrePanel.add(vehicleLabel);
        centrePanel.add(comboBox);
        textAreaPanel.add(tablePane);
        buttonPanel.add(vote);
        buttonPanel.add(view);
        buttonPanel.add(add);
        buttonPanel.add(exit);
        
        //Frame/////////////////////////////////////////////////////////////////
        this.setVisible(true);
        this.setTitle("Car Voting");
        this.setSize(600, 590);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Add To Frame
        this.add(topPanel, BorderLayout.NORTH);
        this.add(containerPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        
        
    }
    
    public void populateComboBox(ArrayList<String> cars) {
        for(String carName : cars) {
            comboBox.addItem(carName);
        }
    }
    
    public void setPassword(String password) {
        adminObj.setAdminPassword(password);
    }
    
    @Override
    public void actionPerformed(ActionEvent i) {
        
        //Vote button///////////////////////////////////////////////////////////
        if(i.getSource() == vote) {
            String selectedCar;
            Object selectedItem = comboBox.getSelectedItem();
            
            if(selectedItem != null && !selectedItem.equals("Select Vehicle")) {
                selectedCar = (String) selectedItem;
                System.out.println("Selected car: "  + selectedCar);
                
                int choice = JOptionPane.showConfirmDialog(null, "You selected " + selectedCar + "\n Are you sure?", "Confirm", JOptionPane.YES_NO_CANCEL_OPTION);
                if(choice == JOptionPane.YES_OPTION) {
                    System.out.println("User selected yes");
                    
                    String carName = selectedCar;
                    int vote = 1;
                    
                    //clnSide.sendVote(carName, vote);
                    clnSide.sendVoteToServer(carName, vote);
                }
                else if(choice == JOptionPane.NO_OPTION) {
                    System.out.println("User selected no");
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Error, please select a car and try again..", "Error", JOptionPane.OK_OPTION);
            }
        }
        ////////////////////////////////////////////////////////////////////////
        //View button
        if(i.getSource() == view) {
            clnSide.getCarVoteList(this);
        }
        ////////////////////////////////////////////////////////////////////////
        //Add button
        if(i.getSource() == add) {
            String username;
            String password;
            
            username = JOptionPane.showInputDialog("Please enter username");
            clnSide.confirmAdmin(this, username);
            
            if(username != null) {
                password = JOptionPane.showInputDialog("Please enter password");
                System.out.println("Entered username and password: " + username + " " + password);
                
                if(password.equals(adminObj.getAdminPassword())) {
                    //clnSide.confirmAdmin(this, username);
                    //JOptionPane.showMessageDialog(null, "Username and password found, therefore user can add a car"); //This is just for testing
                    adminObj.setAdminPassword(null);//To clear password attribute after confirmation
                    String vehicle;
                    int vote = 0;
                    
                    vehicle = JOptionPane.showInputDialog("Enter vehicle name");
                    clnSide.setVehicle(vehicle, vote);
                    clnSide.sendVehicle();
                    clnSide.getCarList(this);System.out.println("getCarList(this) works..");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Incorrect username or password", "Error", JOptionPane.OK_OPTION);
                }
            }
        }
        //Exit Button
        if(i.getSource() == exit) {
            System.exit(0);
        }
        
    }
    
}
