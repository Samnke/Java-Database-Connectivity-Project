package za.ac.cput.server_side_gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Samnkelo Ngxande 221311564
 * @author Sinhle Xiluva Mthethwa 221802797
 * @author Mose Mabina 230911498
 * 
 */
public class LogsGUI extends JFrame implements ActionListener{
    //Declarations//////////////////////////////////////////////////////////////
    
    //Panels
    private JPanel textAreaPanel;
    private JPanel buttonPanel;
    
    //Text Area
    public JTextArea textArea;
    private JScrollPane scrollPane;
    
    //Button
    private JButton clear;
    private JButton exit;
    
    //End of declaration////////////////////////////////////////////////////////
    
    public LogsGUI() {
        
        //Panels
        textAreaPanel = new JPanel();
        buttonPanel = new JPanel();
        
        textAreaPanel.setPreferredSize(new Dimension(600,473));
        buttonPanel.setPreferredSize(new Dimension(600,40));
        textAreaPanel.setLayout(new GridLayout());
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(0x16324F));
        
        //Text area
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        
        //Buttons
        clear = new JButton("Clear");
        exit = new JButton("Exit");
        
        clear.setFocusable(false);
        exit.setFocusable(false);
        clear.addActionListener(this);
        exit.addActionListener(this);
        
        //Add to Panels
        textAreaPanel.add(scrollPane);
        buttonPanel.add(clear);
        buttonPanel.add(exit);
        
        //Frame
        this.setTitle("Logs");
        this.setSize(600,550);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Add to frame
        this.add(textAreaPanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.SOUTH);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent i) {
        //Exit button
        if(i.getSource() == exit) {
            System.exit(0);
        }
        
        //Clear button
        if(i.getSource() == clear) {
            textArea.setText("");
        }
    }
    
}
