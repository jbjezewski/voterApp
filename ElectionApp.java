package voterApp;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import voterApp.*;

public class ElectionApp extends Applet{

	public void init() {
		String[] myButtonArray = new String[] {"Import Votes"};
		String myHomeTitle = new String("Election Home");
		homeView HV = new homeView();
		HV.homePageView(myButtonArray,myHomeTitle);
	}
	public ElectionApp() {
		// TODO Auto-generated constructor stub
	}

}
