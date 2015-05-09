package voterApp;
import java.applet.*;
import voterApp.FontSwitcher;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

import voterApp.DBFunctions;

public class PollingStation extends Applet {
	
	public void init() {
		FontSwitcher FS = new FontSwitcher();
		String[] myButtonArray = new String[] {"Manual Search","Add Voter","Scan Fingerprint","Khmer","English"};
		String myHomeTitle = new String("Polling Station Home");
		homeView HV = new homeView();
		HV.homePageView(myButtonArray,myHomeTitle);
	}
	
	public PollingStation() {
		// TODO Auto-generated constructor stub
	}

}
