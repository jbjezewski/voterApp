package voterApp;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import com.jcraft.jsch.*;

import javax.swing.*;

import java.io.*;
import java.net.MalformedURLException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.swing.*;

import voterApp.*;

public class AuditUserActivity extends Applet {
	
	public void init(String fontType){
    final String ffontType = fontType;
    final String uname = System.getProperty("user.name");
    final String remoteDir = "/cygdrive/c/Users/Administrator/Desktop";
    final String localDir = "C:\\Users\\"+uname+"\\Desktop\\";

	String pageTitle = "Audit User Activity";
	String[] buttonarr = new String[]{ "Home", "Download Voters Changed By This User"};
	voterApp.UITools UIT = new UITools();
	JPanel pnPanel = new JPanel();
	GridBagConstraints gbcPanel1 = new GridBagConstraints();
	GridBagLayout gbPanel = new GridBagLayout();
	GridBagLayout gbPanel0 = UIT.GridBagLayoutTool(gbcPanel1, gbPanel, pnPanel, 1,1,1,1, 2,1, 1, 1,1,1);
	JPanel pnPanel0 = UIT.PanelTool(gbPanel0, pnPanel);
	final JFrame mainJFrame = UIT.FrameTool(ffontType, pnPanel0, buttonarr, pageTitle, 1024, 768, gbPanel0, gbcPanel1);
    UIT.ButtonTool(ffontType, "Home", pnPanel0, 0, 0, 7, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
    UIT.ButtonTool(ffontType, "Download Voters Changed By This User", pnPanel0, 0, 3, 7, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);

	UIT.LabelImageTextFieldTool(ffontType, pageTitle, "Enter Username: ", "", 1, pnPanel0, 2, 5, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
	UIT.LabelImageTextFieldTool(ffontType, pageTitle, pageTitle, "", 0, pnPanel0, 3, 0, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
    final java.awt.Component[] compViewList = pnPanel0.getComponents();
    java.awt.Component[] componentdetailList = pnPanel0.getComponents();
    for (int i = 0; i < componentdetailList.length; i++) {
    	String sec = componentdetailList[i].getClass().getName();
    	System.out.println("sequence in componentlist" + i + "sec " + sec); //0,2,3,4,5 are the buttons in duplicate report
	    if(sec.equals("javax.swing.JButton")){
	    		JButton mybutton = (JButton) componentdetailList[i];
	    		//you have to select each button individually and assign an action to each one
	    		if(i == 1){ //choose directory	
	    			mybutton.addActionListener(new ActionListener() { 
	    				public void actionPerformed(ActionEvent e) {
	    					String myuserName = System.getProperty("user.name");
	    					JSch jsch = new JSch();
	    					String knownHostsFilename = "C:\\Users\\" + myuserName + "\\Downloads\\Admin.pem";
	    					try{
	    						jsch.setKnownHosts( knownHostsFilename );
	    					}catch (JSchException f){
	    						System.out.println(f.toString());
	    					}
	    					String absoluteFilePathPrivatekey = "";
	    					File tmpFileObject = new File(knownHostsFilename);
	    					if (tmpFileObject.exists() && tmpFileObject.isFile())
	    					{
	    						absoluteFilePathPrivatekey = tmpFileObject.getAbsolutePath();
	    					}
	    					try{ jsch.addIdentity(absoluteFilePathPrivatekey, ""); //second param is passphrase for key
	    					}catch (JSchException ff){
	    						System.out.println(ff.toString());
	    					}    
	    						//choose the directory to download the audit report to
		    					JFileChooser fileChooser = new JFileChooser();
		    			        String locDir = "C:\\Users\\"+uname+"\\Downloads\\";
		    					fileChooser.setCurrentDirectory(new File(locDir));
		    				    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    				    fileChooser.setAcceptAllFileFilterUsed(false);
		    					int saveValue = fileChooser.showSaveDialog(null);
		    					File selectedFile;
		    					if (saveValue == JFileChooser.APPROVE_OPTION) {
		    						////should be picking up directory rather than a file
		    						selectedFile = fileChooser.getSelectedFile();
		    						locDir = selectedFile.getName();
		    						String mys = selectedFile.getAbsolutePath();
		    						System.out.println("selected directory " + mys);
		    					}
		    					//get the employee name they entered
	    						JTextField textField = (JTextField) compViewList[3];
	    						String employeename = textField.getText();	
	    						employeename = employeename.replace(" ", "_");//check for other odd characters in users names
	    						DBFunctions DBF = new DBFunctions();
	    						System.out.println("employee entered is " + employeename);
	    						String uid = DBF.selectUser(employeename);
	    						String localFile = DBF.auditUser(uid);
	    						//can remind the user where to look for the file with a label containing
	    						//localFile value if we want
	    						textField.setText("");	 
	    				} 
	    			});
	    		}if(i == 0){
	    			mybutton.addActionListener(new ActionListener() { 
	    				public void actionPerformed(ActionEvent e) { 
	    					mainJFrame.dispose();
	    					//homeView HV = new homeView();
	    					//String[] buttonArray = new String[] {"Voter Search", "Import Voter","Duplicate Report","Audit User Activity"};
	    					// HV.homePageView(buttonArray, "Central App Home");
	    				} 
	    			});
	    		}
	    }
		mainJFrame.add(pnPanel0,BorderLayout.SOUTH);
        mainJFrame.pack();
        mainJFrame.setVisible(true);
    }
  }	
}
	    
