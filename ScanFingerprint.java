package voterApp;
import java.applet.*;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class ScanFingerprint extends Applet{

	public void init(String fontType, String VoterID){
		final String ffontType = fontType;
		final String finalVoterID = VoterID;
	    String[] buttonArray = new String[] {"Scan Fingerprint", "Accept Fingerprint", "Cancel", "Cancel and Go to Search"};
	    String[] buttonArrayK = new String[] {"ថតចម្លងស្នាមម្រាមដៃ", "ទទួលស្គាល់ស្នាមម្រាមដៃ", "លុបចោល/បោះបង់", "ត្រឡប់ទៅស្វែងរក"};
	    String pageTitle = "Scan Fingerprint";
		UITools UIT = new UITools();
		GridBagLayout gbPanel0 = new GridBagLayout();
		JPanel pnPanel = new JPanel();
		JPanel pnPanel0 = new JPanel();
		GridBagConstraints gbcPanel1 = new GridBagConstraints();
		GridBagLayout gbPanel = new GridBagLayout();
		gbcPanel1 = UIT.GridBagConstraintsTool(gbPanel0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
		gbPanel0 = UIT.GridBagLayoutTool(gbcPanel1, gbPanel0, pnPanel, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
		pnPanel0 = UIT.PanelTool(gbPanel0, pnPanel);
		if(!fontType.equals("Khmer")){
			for (int k = 0; k < 4; k ++){
				UIT.ButtonTool(ffontType, buttonArray[k], pnPanel0, 0, k, 2, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
			}
		}
		if(fontType.equals("Khmer")){
			for (int k = 0; k < 4; k ++){
				UIT.ButtonTool(ffontType, buttonArrayK[k], pnPanel0, 0, k, 2, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
			}
		}
		final JFrame mainJFrame = UIT.FrameTool(ffontType, pnPanel0, buttonArray, pageTitle, 1024, 768, gbPanel0, gbcPanel1);
		UIT.LabelImageTextFieldTool(ffontType, pageTitle, "Fingerprint","C:\\Users\\JJezewski\\Pictures\\Fingerprint_Whorl.JPG", 0, pnPanel0, 1, 4, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
        final java.awt.Component[] compViewList = pnPanel0.getComponents();
        java.awt.Component[] componentdetailList = pnPanel0.getComponents();
        for (int i = 0; i < componentdetailList.length; i++) {
        	String sec = componentdetailList[i].getClass().getName();
		    if(sec.equals("javax.swing.JButton")){
		    	//System.out.println("scan fingerprint JButton sequence in componentlist" + i); //0 through 3 are the buttons in view voters
		    		JButton mybutton = (JButton) componentdetailList[i];
		    		//you have to select each button individually and assign an action to each one
		    		if(i == 0){ 
		    			mybutton.addActionListener(new ActionListener() {
		    				public void actionPerformed(ActionEvent e){
		    					//call scan fingerprint code for external application	
		    				}
		    			});
		    		}
		    		if(i == 1){ //accept fingerprint, saving to record
		    			mybutton.addActionListener(new ActionListener() {
		    				public void actionPerformed(ActionEvent e){
		    					//call external application to find image location
					        				String[] fingerprintArray = {"fingerprint_image_location","/home/user/voterid_"+finalVoterID+"_fingerprint.jpg"};
						        			// DBFunctions DBF = new DBFunctions();
						        			// DBF.makeConnection();
						        			// DBF.updateVoter(finalVoterID, fingerprintArray);
		    				}
		    			});
		    		}
		    		if(i == 2){ //view voter
					  mybutton.addActionListener(new ActionListener() { 
					    public void actionPerformed(ActionEvent e) {
		        			  mainJFrame.dispose();
		       				  //DetailView DV = new DetailView();
		       				  //DV.init("view",finalVoterID); 
					  		}
				      });
		    		}
		    		if(i == 3){ //go to search
					  mybutton.addActionListener(new ActionListener() { 
					    public void actionPerformed(ActionEvent e) {
		        			  mainJFrame.dispose();
		    				 // SearchView SV = new SearchView();
		    				 // SV.init("false", "");
					  		}
				      });
		    		}
		    }
        }
		mainJFrame.add(pnPanel0,BorderLayout.NORTH);
	    mainJFrame.pack();
	    mainJFrame.setVisible(true);		
	}
	public ScanFingerprint() {
		// TODO Auto-generated constructor stub
	}

}
