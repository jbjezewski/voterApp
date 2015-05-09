package voterApp;
import java.applet.*;

import voterApp.homeView;

import java.util.*;

public class CentralApp extends Applet {
	
	public void init() {
		String[] myButtonArray = new String[] {"Manual Search", "Import Voter","Duplicate Report","Audit User Activity","Khmer","English"};
		String myHomeTitle = new String("Central App Home");
		voterApp.homeView HV = new voterApp.homeView();
		HV.homePageView(myButtonArray,myHomeTitle);
	}

}
