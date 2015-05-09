	package voterApp;
	import java.applet.*;
	import java.awt.*;
	import java.awt.event.*;
	import java.sql.*;

	import javax.swing.*;

	import voterApp.DBFunctions;

	public class VotingApp extends Applet {
		
		public void init() {
			String[] myButtonArray = new String[] {"Enter"};
			String myHomeTitle = new String("Enter Your Voting Code");
			homeView HV = new homeView();
			HV.homePageView(myButtonArray,myHomeTitle);
		}
		
		public VotingApp() {
			// TODO Auto-generated constructor stub
		}

	}
