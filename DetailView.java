package voterApp;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import voterApp.*;

public class DetailView extends Applet {

    public void init(String DetailViewType, String VoterID, String fontType){
    	final String ffontType = fontType;
    	VoterID = VoterID.replace("[", "");
    	VoterID = VoterID.replace("]","");
    	VoterID = VoterID.replace(" ","");
     final String finalVoterID = VoterID;
     final String dvt = DetailViewType;
   	 String[] buttonArray = new String[]{};
     String pageTitle = "";
     final String dViewType = DetailViewType;
     if(DetailViewType.equals("edit")){pageTitle = "Edit Voter";}
     if(DetailViewType.equals("view")){pageTitle = "View Voter";}
     if(DetailViewType.equals("compare")){pageTitle = "Compare Voters";}
     if(DetailViewType.equals("add")){pageTitle = "Add Voter";}
     if(DetailViewType.equals("compareduplicates")){pageTitle = "View Duplicate Pair";}
    	//detail view type can be "Add", "View", "Compare", or "Edit"
		UITools UIT = new UITools();
		GridBagLayout gbPanel0 = new GridBagLayout();
		JPanel pnPanel = new JPanel();
		JPanel pnPanel0 = new JPanel();
		GridBagConstraints gbcPanel1 = new GridBagConstraints();
		GridBagLayout gbPanel = new GridBagLayout();
		gbcPanel1 = UIT.GridBagConstraintsTool(gbPanel0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
		gbPanel0 = UIT.GridBagLayoutTool(gbcPanel1, gbPanel0, pnPanel, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
		pnPanel0 = UIT.PanelTool(gbPanel0, pnPanel);
		//adding labels and text fields
		if(DetailViewType != "compare" && DetailViewType != "compareduplicates"){
			UIT.LabelImageTextFieldTool(ffontType, pageTitle, "ID", "", 1, pnPanel0, 1, 0, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
			if(!ffontType.equals("Khmer")){
				String[] labels = {"Family Name: ", "Birth Date: ", "CID: ", "Registered Address 1: ", "Registered Address 2: ", "Registered Address 3: ", "Registered Village: ", "Registered Commune: ", "Registered District: ", "Registered Province: ", "Registered City: ", "Registered Zip: "};
				String[] labelstwo = { "Given Name: ", "Regnum: ", "NID: ", "Mailing Address 1: ", "Mailing Address 2: ", "Mailing Address 3: ", "Mailing Village: ", "Mailing Commune: ", "Mailing District: ", "Mailing Province: ", "Mailing City: ", "Mailing Zip: "};
				int labelsLength = labels.length;
				for (int i = 0; i < labelsLength; i++) {
					UIT.LabelImageTextFieldTool(ffontType, pageTitle, labels[i], "", 1, pnPanel0, 1, i+3, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
				}
				int labelstwoLength = labelstwo.length;
				for (int i = 0; i < labelstwoLength; i++) {
					UIT.LabelImageTextFieldTool(ffontType, pageTitle, labelstwo[i], "", 1, pnPanel0, 3, i+3, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
				}  
			}else if(ffontType.equals("Khmer")){
				String[] labelsK = {"ត្រកូល: ", "ថ្ងៃខែឆ្នាំកំនើត: ", "ប័ណ្ណបញ្ជាក់អត្តសញ្ញាណ: ", "អាសយដ្ឋាន្នដែលបានចុះបញ្ជី មួយ: ", "អាសយដ្ឋាន្នដែលបានចុះបញ្ជី ពីរ​នាក់: ", "អាសយដ្ឋាន្នដែលបានចុះបញ្ជី បី​នាក់: ", "បាន​ចុះ​ឈ្មោះ ភូមិ: ", "បាន​ចុះ​ឈ្មោះ ឃុំ ឬ សង្កាត់: ", "បាន​ចុះ​ឈ្មោះ ស្រុក ឬ ខ័ណ្ឌ: ", "បាន​ចុះ​ឈ្មោះ ខេត្ត: ", "បាន​ចុះ​ឈ្មោះ ក្រុង: ", "បាន​ចុះ​ឈ្មោះ លេខកូដតំបន់: "};
				String[] labelstwoK = { "ឈ្មោះ: ", "លេខចុះឈ្មោះ: ", "អត្តសញ្ញាណប័ណ្ណជាតិ: ", "អាសយដ្ឋាន្នប៉ុស្ត៍សំបុត្រ មួយ: ", "អាសយដ្ឋាន្នប៉ុស្ត៍សំបុត្រ ពីរ​នាក់: ", "អាសយដ្ឋាន្នប៉ុស្ត៍សំបុត្រ បី​នាក់: ", "សំបុត្រ​រួម ភូមិ: ", "សំបុត្រ​រួម ឃុំ ឬ សង្កាត់: ", "សំបុត្រ​រួម ស្រុក ឬ ខ័ណ្ឌ: ", "សំបុត្រ​រួម ខេត្ត: ", "សំបុត្រ​រួម ក្រុង: ", "សំបុត្រ​រួម លេខកូដតំបន់: "};
				int labelsKLength = labelsK.length;
				for (int i = 0; i < labelsKLength; i++) {
					UIT.LabelImageTextFieldTool(ffontType, pageTitle, labelsK[i], "", 1, pnPanel0, 1, i+3, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
				}
				int labelstwoKLength = labelstwoK.length;
				for (int i = 0; i < labelstwoKLength; i++) {
					UIT.LabelImageTextFieldTool(ffontType, pageTitle, labelstwoK[i], "", 1, pnPanel0, 3, i+3, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
				}  
			}

		}
		if(DetailViewType == "compare" || DetailViewType == "compareduplicates"){
			//System.out.println("were comparing voters from search view");
			//System.out.println("finalvoterid is " + finalVoterID);
			UIT.LabelImageTextFieldTool(ffontType, pageTitle, "ID", "", 1, pnPanel0, 1, 0, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
			//label to represent which duplicate pair we're on
			String[] arrcount = finalVoterID.split(",");
			int arrlen = arrcount.length / 2;
			String viewduplabel = "View 1 of " + arrlen;
			UIT.LabelImageTextFieldTool(ffontType, pageTitle, pageTitle, "", 0, pnPanel0, 0, 0, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
			if(DetailViewType != "compare"){
				UIT.LabelImageTextFieldTool(ffontType, pageTitle, viewduplabel, "", 0, pnPanel0, 0, 1, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
			}
			if(!ffontType.equals("Khmer")){
				final String[] labels = {"Family Name: ", "Birth Date: ", "CID: ", "Registered Address 1: ", "Registered Address 2: ", "Registered Address 3: "};
				final String[] labelsthree = {"Registered Village: ", "Registered Commune: ", "Registered District: ", "Registered Province: ", "Registered City: ", "Registered Zip: "};
				final String[] labelstwo = { "Given Name: ", "Regnum: ", "NID: ", "Mailing Address 1: ", "Mailing Address 2: ", "Mailing Address 3: "};
				final String[] labelsfour = { "Mailing Village: ", "Mailing Commune: ", "Mailing District: ", "Mailing Province: ", "Mailing City: ", "Mailing Zip: "};
				int labelsLength = labels.length;
				for (int i = 0; i < labelsLength; i++) {
					UIT.LabelImageTextFieldTool(ffontType, pageTitle, labels[i], "", 0, pnPanel0, i+1, 4, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
					UIT.LabelImageTextFieldTool(ffontType, pageTitle, "", "", 1, pnPanel0, i+1, 6, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
				}for (int i = 0; i < labelsLength; i++) {
					UIT.LabelImageTextFieldTool(ffontType, pageTitle, labelsthree[i], "", 0, pnPanel0, i+1, 6, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
					UIT.LabelImageTextFieldTool(ffontType, pageTitle, "", "", 1, pnPanel0, i+1, 8, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
				}for (int i = 0; i < labelsLength; i++) {
					UIT.LabelImageTextFieldTool(ffontType, pageTitle, labelstwo[i], "", 0, pnPanel0, i+1, 8, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
					UIT.LabelImageTextFieldTool(ffontType, pageTitle, "", "", 1, pnPanel0, i+1, 10, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
				}for (int i = 0; i < labelsLength; i++) {
					UIT.LabelImageTextFieldTool(ffontType, pageTitle, labelsfour[i], "", 0, pnPanel0, i+1, 10, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
					UIT.LabelImageTextFieldTool(ffontType, pageTitle, "", "", 1, pnPanel0, i+1, 12, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
				}for (int i = 0; i < labelsLength; i++) {
					UIT.LabelImageTextFieldTool(ffontType, pageTitle, labels[i], "", 0, pnPanel0, i+1, 14, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
					UIT.LabelImageTextFieldTool(ffontType, pageTitle, "", "", 1, pnPanel0, i+1, 16, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
				}for (int i = 0; i < labelsLength; i++) {
					UIT.LabelImageTextFieldTool(ffontType, pageTitle, labelsthree[i], "", 0, pnPanel0, i+1, 16, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
					UIT.LabelImageTextFieldTool(ffontType, pageTitle, "", "", 1, pnPanel0, i+1, 18, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
				}for (int i = 0; i < labelsLength; i++) {
					UIT.LabelImageTextFieldTool(ffontType, pageTitle, labelstwo[i], "", 0, pnPanel0, i+1, 18, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
					UIT.LabelImageTextFieldTool(ffontType, pageTitle, "", "", 1, pnPanel0, i+1, 20, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
				}for (int i = 0; i < labelsLength; i++) {
					UIT.LabelImageTextFieldTool(ffontType, pageTitle, labelsfour[i], "", 0, pnPanel0, i+1, 20, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
					UIT.LabelImageTextFieldTool(ffontType, pageTitle, "", "", 1, pnPanel0, i+1, 22, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
				} 
			}else if(ffontType.equals("Khmer")){
				final String[] labels = {"ត្រកូល: ", "ថ្ងៃខែឆ្នាំកំនើត: ", "ប័ណ្ណបញ្ជាក់អត្តសញ្ញាណ: ", "អាសយដ្ឋាន្នដែលបានចុះបញ្ជី មួយ: ", "អាសយដ្ឋាន្នដែលបានចុះបញ្ជី ពីរ​នាក់: ", "អាសយដ្ឋាន្នដែលបានចុះបញ្ជី បី​នាក់: "};
				final String[] labelsthree = {"បាន​ចុះ​ឈ្មោះ ភូមិ: ", "បាន​ចុះ​ឈ្មោះ ឃុំ ឬ សង្កាត់: ", "បាន​ចុះ​ឈ្មោះ ស្រុក ឬ ខ័ណ្ឌ: ", "បាន​ចុះ​ឈ្មោះ ខេត្ត: ", "បាន​ចុះ​ឈ្មោះ ក្រុង: ", "បាន​ចុះ​ឈ្មោះ លេខកូដតំបន់: "};
				final String[] labelstwo = {"ឈ្មោះ: ", "លេខចុះឈ្មោះ: ", "អត្តសញ្ញាណប័ណ្ណជាតិ: ", "អាសយដ្ឋាន្នប៉ុស្ត៍សំបុត្រ មួយ: ", "អាសយដ្ឋាន្នប៉ុស្ត៍សំបុត្រ ពីរ​នាក់: ", "អាសយដ្ឋាន្នប៉ុស្ត៍សំបុត្រ បី​នាក់: "};
				final String[] labelsfour = {"សំបុត្រ​រួម ភូមិ: ", "សំបុត្រ​រួម ឃុំ ឬ សង្កាត់: ", "សំបុត្រ​រួម ស្រុក ឬ ខ័ណ្ឌ: ", "សំបុត្រ​រួម ខេត្ត: ", "សំបុត្រ​រួម ក្រុង: ", "សំបុត្រ​រួម លេខកូដតំបន់: "};
				int labelsLength = labels.length;
			for (int i = 0; i < labelsLength; i++) {
				UIT.LabelImageTextFieldTool(ffontType, pageTitle, labels[i], "", 0, pnPanel0, i+1, 4, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
				UIT.LabelImageTextFieldTool(ffontType, pageTitle, "", "", 1, pnPanel0, i+1, 6, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
			}for (int i = 0; i < labelsLength; i++) {
				UIT.LabelImageTextFieldTool(ffontType, pageTitle, labelsthree[i], "", 0, pnPanel0, i+1, 6, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
				UIT.LabelImageTextFieldTool(ffontType, pageTitle, "", "", 1, pnPanel0, i+1, 8, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
			}for (int i = 0; i < labelsLength; i++) {
				UIT.LabelImageTextFieldTool(ffontType, pageTitle, labelstwo[i], "", 0, pnPanel0, i+1, 8, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
				UIT.LabelImageTextFieldTool(ffontType, pageTitle, "", "", 1, pnPanel0, i+1, 10, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
			}for (int i = 0; i < labelsLength; i++) {
				UIT.LabelImageTextFieldTool(ffontType, pageTitle, labelsfour[i], "", 0, pnPanel0, i+1, 10, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
				UIT.LabelImageTextFieldTool(ffontType, pageTitle, "", "", 1, pnPanel0, i+1, 12, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
			}for (int i = 0; i < labelsLength; i++) {
				UIT.LabelImageTextFieldTool(ffontType, pageTitle, labels[i], "", 0, pnPanel0, i+1, 14, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
				UIT.LabelImageTextFieldTool(ffontType, pageTitle, "", "", 1, pnPanel0, i+1, 16, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
			}for (int i = 0; i < labelsLength; i++) {
				UIT.LabelImageTextFieldTool(ffontType, pageTitle, labelsthree[i], "", 0, pnPanel0, i+1, 16, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
				UIT.LabelImageTextFieldTool(ffontType, pageTitle, "", "", 1, pnPanel0, i+1, 18, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
			}for (int i = 0; i < labelsLength; i++) {
				UIT.LabelImageTextFieldTool(ffontType, pageTitle, labelstwo[i], "", 0, pnPanel0, i+1, 18, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
				UIT.LabelImageTextFieldTool(ffontType, pageTitle, "", "", 1, pnPanel0, i+1, 20, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
			}for (int i = 0; i < labelsLength; i++) {
				UIT.LabelImageTextFieldTool(ffontType, pageTitle, labelsfour[i], "", 0, pnPanel0, i+1, 20, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
				UIT.LabelImageTextFieldTool(ffontType, pageTitle, "", "", 1, pnPanel0, i+1, 22, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
			} 
			}
		}
	    //fill in text field values if VoterID is not empty also need to check if voter id is two ids for compare voters view
        java.awt.Component[] componentList = pnPanel0.getComponents();
        
    /////POPULATE TEXTFIELDS WITH VOTER VALUES
        if(!VoterID.equals("") && (DetailViewType == "view" || DetailViewType == "edit"  || DetailViewType == "compare" || DetailViewType == "compareduplicates")){
			    DBFunctions DBA = new DBFunctions();
			    DBA.makeConnection();
			    String[] prearray = new String[50];
			    String[] stringarray = new String[50];
			    prearray = DBA.selectVoter(VoterID);
			    for (int t = 0;t<prearray.length;t++){
			    	if(t < 25){
			    		stringarray[t] = prearray[t];
			    	}
			    	if(t > 24 && t < 49){
			    		stringarray[t] = prearray[t+1];
			    	}
			    }
			    int f = 0;
			    String fieldval;
			   // System.out.println("stringarray size is : " + stringarray.length);
	        for (int i = 0; i < componentList.length; i++) {
	        	//refers to button you want a textfield reference
	        	String first = componentList[i].getClass().getName();
	        		if (first.equals("javax.swing.JTextField")) {
	    			  //  System.out.println("textfield number: " + i);
		  				    	JTextField fieldname = (JTextField) componentList[i];
		  				    	fieldval = stringarray[f];
		  				    	//System.out.println("val stringarray " + f + " is " + fieldval + ".");
		  				    	fieldname.setText(fieldval);
		  				    	f++;
	        		}
	        }
        }
    	if(DetailViewType == "view"){
    	    pageTitle = "View Voter";
	        buttonArray = new String[] {"Verify","Approve To Vote","Edit","Home","Voter Search"};
			final JFrame mainJFrame = UIT.FrameTool(ffontType, pnPanel0, buttonArray, pageTitle, 1024, 768, gbPanel0, gbcPanel1);
		    UIT.LabelImageTextFieldTool(ffontType, pageTitle, pageTitle, "", 0, pnPanel0, 2, 0, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
	        for (int k = 0; k < 5; k ++){
            	UIT.ButtonTool(ffontType, buttonArray[k], pnPanel0, 0, k, 2, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
            }
	        final java.awt.Component[] compViewList = pnPanel0.getComponents();
	        java.awt.Component[] componentdetailList = pnPanel0.getComponents();
	        for (int i = 0; i < componentdetailList.length; i++) {
	        	String sec = componentdetailList[i].getClass().getName();
			    if(sec.equals("javax.swing.JButton")){
			    	//System.out.println("JButton sequence in componentlist" + i); //51 through 56 are the buttons in view voters
			    		JButton mybutton = (JButton) componentdetailList[i];
			    		//you have to select each button individually and assign an action to each one
			    		if(i == 51){ //verify
			    			mybutton.addActionListener(new ActionListener() {
			    				public void actionPerformed(ActionEvent e){
						        				String[] verifyArray = {"verify_voter"};
							        			 DBFunctions DBF = new DBFunctions();
							        			 DBF.makeConnection();
							        			 DBF.updateVoter(finalVoterID, verifyArray);
			    				}
			    			});
			    		}
			    		if(i == 52){ //approve to vote
			    			mybutton.addActionListener(new ActionListener() {
			    				public void actionPerformed(ActionEvent e){
						        				String[] approveArray = {"approve_voter"};
							        			 DBFunctions DBF = new DBFunctions();
							        			 DBF.makeConnection();
							        			 DBF.updateVoter(finalVoterID, approveArray);
			    				}
			    			});
			    		}
			    		if(i == 53){ //go to editview
						  mybutton.addActionListener(new ActionListener() { 
						    public void actionPerformed(ActionEvent e) {
			        			  setVisible(false);
			       				  DetailView DV = new DetailView();
			       				  DV.init("edit",finalVoterID, ffontType); 
						  		}
					      });
			    		}
			    		if(i == 54){ //go to home
						  mybutton.addActionListener(new ActionListener() { 
						    public void actionPerformed(ActionEvent e) {
			        			  mainJFrame.dispose();
			    				  homeView HV = new homeView();
			    				  String[] buttonArray = new String[] {"Voter Search", "Import Voter","Duplicate Report","Audit User Activity"};
			    				  HV.homePageView(buttonArray, "Central App Home");
						  		}
					      });
			    		}
			    		if(i == 55){ //go to search
						  mybutton.addActionListener(new ActionListener() { 
						    public void actionPerformed(ActionEvent e) {
			        			  mainJFrame.dispose();
			    				 // SearchView SV = new SearchView();
			    				 // SV.init("", "clear");
						  		}
					      });
			    		}
			    }
	        }
	        if(!VoterID.equals("")){
	        	
	        }
			mainJFrame.add(pnPanel0,BorderLayout.NORTH);
	        mainJFrame.pack();
	        mainJFrame.setVisible(true); 
    	}
    	if(DetailViewType.equals("edit") || DetailViewType.equals("add")){
    		if(DetailViewType.equals("edit") && !ffontType.equals("Khmer")) {pageTitle = "Edit Voter";
	        	buttonArray = new String[] {"Upload Photo","Upload ID Photo","Save","Cancel and View Voter","Home","Return to Search"};
    		}if(DetailViewType.equals("edit") && ffontType.equals("Khmer")) {pageTitle = "Edit Voter";
    			buttonArray = new String[] {"បង្ហោះរូប","បង្ហោះរូប នៃ លេខអត្តសញ្ញាណ ឬ លេខសំគាល់","រក្សាទុក","លុបចោល ហើយពិនិត្យមើលអ្នកបោះឆ្នោត","ទំព័រ​ដើម","ត្រឡប់ទៅស្វែងរក"};
    		}if(DetailViewType.equals("add") && !ffontType.equals("Khmer")) {pageTitle = "Add Voter";
	        	buttonArray = new String[] {"Upload Photo","Upload ID Photo","Save","Home","Return to Search"};
    		}if(DetailViewType.equals("add") && ffontType.equals("Khmer")) {pageTitle = "Add Voter";
				buttonArray = new String[] {"បង្ហោះរូប","បង្ហោះរូប នៃ លេខអត្តសញ្ញាណ ឬ លេខសំគាល់","រក្សាទុក","ទំព័រ​ដើម","ត្រឡប់ទៅស្វែងរក"};
    		}
			final JFrame mainJFrame = UIT.FrameTool(ffontType, pnPanel0, buttonArray, pageTitle, 1024, 768, gbPanel0, gbcPanel1);
		    UIT.LabelImageTextFieldTool(ffontType, pageTitle, pageTitle, "", 0, pnPanel0, 2, 0, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
	        for (int k = 0; k < buttonArray.length; k ++){
            	UIT.ButtonTool(ffontType, buttonArray[k], pnPanel0, 0, k, 2, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
            }
	        final java.awt.Component[] compTwoList = pnPanel0.getComponents();
	        java.awt.Component[] componenteditList = pnPanel0.getComponents();

	        for (int i = 0; i < componenteditList.length; i++) {
	        	String sec = componenteditList[i].getClass().getName();
			    if(sec.equals("javax.swing.JButton")){
			    	//System.out.println("addvoter buttons JButton sequence in componenteditlist" + i); //51 through 57 are the buttons
			    		JButton mybutton = (JButton) componenteditList[i];
			    		//you have to select each button individually and assign an action to each one
			    		if(i == 51){
			    			mybutton.addActionListener(new ActionListener() {
			    				public void actionPerformed(ActionEvent e){
			    					ScanFingerprint SF = new ScanFingerprint();
			    					SF.init(ffontType, finalVoterID);
			    				}
			    			});
			    		}
			    		if(i == 52){
			    			mybutton.addActionListener(new ActionListener() {
			    				public void actionPerformed(ActionEvent e){
			    					ScanFingerprint SF = new ScanFingerprint();
			    					SF.init(ffontType, finalVoterID);
			    				}
			    			});
			    		}
			    		if(i == 53){ //save action
						  mybutton.addActionListener(new ActionListener() { 
						    public void actionPerformed(ActionEvent e) { 
			        			String txtvalue = "";
			        			String textvalue = "";
			        			String idValue = "";
			        			String labelText = "";
			        			int r = 0;
			        			String valuearray = "";
			        			//change columnValueArray to exclude extra fields for update statement, for insert statement it's fine the way it is
			        			String[] columnValueArray = {"ID","VoterID"," ","Date Entered: ","date_entered"," ","Date Modified: ","date_modified"," ","Created By: ","created_by"," ","Modified By: ","modified_user_id"," ","Assigned To: ","assigned_user_id"," ","Description: ","voter_description"," ","Salutation: ","salutation"," ","Given Name: ","first_name"," ","Middle Name: ","middle_name"," ","Family Name: ","last_name"," ","Name Suffix: ","name_suffix"," ","Birth Date: ","birthdate"," ","Gender: ","gender"," ","Regnum: ","regnum"," ","Registration House Number: ","reg_hnum"," ","Registration House Number Suffix: ","reg_hnumsfx"," ","Registration Date: ","regdate"," ","Inactive: ","inactive"," ","Registered Address 1: ", "reg_address1"," ","Registered Address 2: ", "reg_address2"," ","Registered Address 3: ", "reg_address3"," ","Registered Apt: ","reg_apt"," ","Registered City: ", "reg_city"," ","Registered State: ","reg_state"," ","Registered Zip: ","reg_zip"," ","Mailing Address 1: ","mail_address1"," ","Mailing Address 2: ","mail_address2"," ","Mailing Address 3: ","mail_address3"," ","Mailing Apt: ","mail_apt"," ","Mailing City: ","mail_city"," ","Mailing State: ","mail_state"," ","Mailing Zip: ","mail_zip"," ","Bad Address: ","mailing_address_flag"," ","Voting Location: ","voting_location"," ","Email1: ","email1"," ","Phone1: ","phone1"," ","Phone2: ","phone2"," ","Do not mail: ","do_not_mail"," ","Do not e-mail: ","do_not_email"," ","Do not call: ","do_not_call"," ","Title: ","title"," ","Occupation: ","occupation"," ","Department: ","department"," ","Party: ","primary_party_affiliation"," ","Potential Duplicate: ","potential_duplicate_voter"," ","Approved to Vote: ","approved_to_vote"," ","Approved to Register: ","approved_to_register"," ","Registered Province: ","reg_province", " ","Registered Commune: ","reg_commune", " ","Registered Village: ","reg_village", " ","Registered District: ","reg_district", " ","Mailing Province: ","mail_province", " ","Mailing Commune: ","mail_commune", " ","Mailing Village: ","mail_village", " ","Mailing District: ","mail_district", " ","CID: ","cid"," ","NID: ","nid"," "};			        			
			        			for (int i = 0; i < 49; i++) {
						        	//refers to button you want a textfield reference
				    	        	String first = compTwoList[i].getClass().getName();
						        			  if(first.equals("javax.swing.JLabel")){
						        				JLabel thisLabel = (JLabel) compTwoList[i];
						        				labelText = thisLabel.getText();
						        			  }
						        			  else if(first.equals("javax.swing.JTextField")){
					        						//find text value of next component
									        		if (first.equals("javax.swing.JTextField")) {
									        			if(i == 1){
									        				JTextField idField = (JTextField) compTwoList[1];
									        				idValue = idField.getText();
									        			}
								        				JTextField txtField = (JTextField) compTwoList[i+2];
								        				if(dViewType.equals("edit")){
									        				txtvalue = txtField.getText();
								        				}if(dViewType.equals("add")){
							        						textvalue = txtField.getText();
									        						valuearray += textvalue + "','";
									        			}
								        				r++;
													}
						        			  }
						        				for(int g=0;g<columnValueArray.length;g++){
						        					if(labelText.equals(columnValueArray[g])){
									        			if(columnValueArray[g+2].equals(" ")){
									        				columnValueArray[g+2] = columnValueArray[g+2].replace(columnValueArray[g+2],txtvalue);
									        			}
						        					}
						        			}
						        	}

						        			if(dViewType.equals("add")){
						        				if(valuearray.length() != 0){
						        					valuearray = "'" + idValue + "','" + valuearray.substring(0, valuearray.length() - 2);
						        					System.out.println(valuearray);
						        				}
						        				DBFunctions DBF = new DBFunctions();
						        				 DBF.makeConnection();
						        				 DBF.insertVoter(idValue, valuearray);
						        			}
						        			if(dViewType.equals("edit")){
						        				//save edited voter with update statement
						        				 DBFunctions DBF = new DBFunctions();
						        				 DBF.makeConnection();
						        				 DBF.updateVoter(idValue, columnValueArray);
						        			}
						        			mainJFrame.dispose();
										    DetailView DV = new DetailView();
										    DV.init("view", idValue, ffontType);						        		

						  		}
					      });
			    		}
			    		if(DetailViewType == "edit"){
			    		if(i == 54){ //cancel and view voter
			    			mybutton.addActionListener(new ActionListener(){
			    				public void actionPerformed(ActionEvent e){
			    						String idValue = "";
							        for (int i = 0; i < compTwoList.length; i++) 
							        {
					        	    	JTextField idField = (JTextField) compTwoList[1];
					        	    	idValue = idField.getText();
							        }
				  				    mainJFrame.dispose();
								    DetailView DView = new DetailView();
								    DView.init("view", idValue, ffontType);		
			    				}
			    			});
			    		}
			    		if(i == 55){ //go to home
						  mybutton.addActionListener(new ActionListener() { 
						    public void actionPerformed(ActionEvent e) {
			        			  mainJFrame.dispose();
			    				//  homeView HV = new homeView();
			    				//  String[] buttonArray = new String[] {"Voter Search", "Import Voter","Duplicate Report","Audit User Activity"};
			    				//  HV.homePageView(buttonArray, "Central App Home");
						  		}
					      });
			    		}
			    		if(i == 56){ //go to search
						  mybutton.addActionListener(new ActionListener() { 
						    public void actionPerformed(ActionEvent e) {
			        			  mainJFrame.dispose();
			    				 // SearchView SV = new SearchView();
			    				 // SV.init("", "clear");
						  		}
					      });
			    		}
			    		}else if(DetailViewType == "add"){
				    		if(i == 54){ //go to home
							  mybutton.addActionListener(new ActionListener() { 
							    public void actionPerformed(ActionEvent e) {
				        			  mainJFrame.dispose();
				    				 // homeView HV = new homeView();
				    				 // String[] buttonArray = new String[] {"Voter Search", "Import Voter","Duplicate Report","Audit User Activity"};
				    				//  HV.homePageView(buttonArray, "Central App Home");
							  		}
						      });
				    		}
				    		if(i == 55){ //go to search
							  mybutton.addActionListener(new ActionListener() { 
							    public void actionPerformed(ActionEvent e) {
				        			  mainJFrame.dispose();
				    				 // SearchView SV = new SearchView();
				    				 // SV.init("", "clear");
							  		}
						      });
				    		}
			    		}
			    }
			    		
	        }
			mainJFrame.add(pnPanel0,BorderLayout.NORTH);
	        mainJFrame.pack();
	        mainJFrame.setVisible(true); 
    	}else if(DetailViewType == "compare"){
    		System.out.println("instantiating compare view");
    	    pageTitle = "Compare Voters";
    	    if(!ffontType.equals("Khmer")){
    	    	buttonArray = new String[] {"Mark As Duplicates","Home","Return to Search"};
    	    }else if(ffontType.equals("Khmer")){
    	        buttonArray = new String[] {"កំណត់​ទៅ​ស្ទួន", "ទំព័រ​ដើម", "ស្វែងរក"};
    	    }
			final JFrame mainJFrame = UIT.FrameTool(ffontType, pnPanel0, buttonArray, pageTitle, 1024, 768, gbPanel0, gbcPanel1);
		   // UIT.LabelImageTextFieldTool(pageTitle, pageTitle, "", 0, pnPanel0, 2, 0, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);  
		   // UIT.LabelImageTextFieldTool(pageTitle, pageTitle, "", 0, pnPanel0, 18, 0, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);  

		    for (int r = 0; r < 2; r ++){
            	UIT.ButtonTool(ffontType, buttonArray[r], pnPanel0, 0, r+2, 1, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
            }
	        final java.awt.Component[] compList = pnPanel0.getComponents();
	        java.awt.Component[] compCompareList = pnPanel0.getComponents();
	        for (int i = 0; i < compList.length; i++) {
	        	String tri = compList[i].getClass().getName();
			    if(tri.equals("javax.swing.JButton")){
			    	System.out.println("compare JButton sequence in compComparelist" + i); // 50 through 56 are the buttons
		    		JButton mybutton = (JButton) compCompareList[i];
		    		//you have to select each button individually and assign an action to each one
		    		if(i == 99){  //mark as duplicates
		    			mybutton.addActionListener(new ActionListener() {
		    				public void actionPerformed(ActionEvent e){
		    					String[] voterIdArray = finalVoterID.split(",");		    				
		        				String[] duplicateArray = {"id1",voterIdArray[0],"id2",voterIdArray[1]};
			        			 DBFunctions DBF = new DBFunctions();
			        			 DBF.makeConnection();
			        			 DBF.updateVoter("", duplicateArray);
		    				}
		    			});
		    		}
		    		if(i == 100){ //go to home
					  mybutton.addActionListener(new ActionListener() { 
					    public void actionPerformed(ActionEvent e) {
		        			  mainJFrame.dispose();
		    				  homeView HV = new homeView();
		    				  String[] buttonArray = new String[] {"Voter Search", "Import Voter","Duplicate Report","Audit User Activity"};
		    				  HV.homePageView(buttonArray, "Central App Home");
					  		}
				      });
		    		}
		    		/*if(i == 53){ //go to search
					  mybutton.addActionListener(new ActionListener() { 
					    public void actionPerformed(ActionEvent e) {
		        			  mainJFrame.dispose();
		    				  SearchView SV = new SearchView();
		    				  SV.init("", "clear");
					  		}
				      });
		    		}*/
			    }
	        }
			mainJFrame.add(pnPanel0,BorderLayout.NORTH);
	        mainJFrame.pack();
	        mainJFrame.setVisible(true); 
    	}
	        else if(DetailViewType == "compareduplicates"){
	    	   pageTitle = "View Duplicate Pair";
		        String[] buttonArr = new String[] {"Mark As Duplicates","Home","Return to Search"};
				final JFrame mainJFrame = UIT.FrameTool(ffontType, pnPanel0, buttonArr, pageTitle, 1024, 768, gbPanel0, gbcPanel1);
			    for (int r = 0; r < 2; r ++){
	            	UIT.ButtonTool(ffontType, buttonArr[r], pnPanel0, 0, r+2, 3, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
	            }
		        String[] buttonArr2 = new String[] {"arrowallleft","arrowoneleft","arrowoneright","arrowallright"};
			    for (int g = 0; g < buttonArr2.length; g ++){
	            	UIT.ButtonTool(ffontType, buttonArr2[g], pnPanel0, 0, g+2, 35, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
	            }
				String[] voterIdArray = finalVoterID.split(",");		    				

		        final java.awt.Component[] compList = pnPanel0.getComponents();
		        java.awt.Component[] compCompareList = pnPanel0.getComponents();
		        for (int i = 0; i < compList.length; i++) {
		        	String tri = compList[i].getClass().getName();
			    	//System.out.println("compare "+tri+" sequence in compComparelist" + i); // 50 through 56 are the buttons

				    if(tri.equals("javax.swing.JButton")){
			    		JButton mybutton = (JButton) compCompareList[i];
			    		//you have to select each button individually and assign an action to each one
			    		if(i == 100){  //mark as duplicates
			    			mybutton.addActionListener(new ActionListener() {
			    				public void actionPerformed(ActionEvent e){
			    					String[] voterIdArray = finalVoterID.split(",");		    				
			        				String[] duplicateArray = {"id1",voterIdArray[0],"id2",voterIdArray[1]};
				        			 DBFunctions DBF = new DBFunctions();
				        		    DBF.updateVoter("", duplicateArray);

			    				}
			    			});
			    		}
			    		if(i == 101){ //go to home
						  mybutton.addActionListener(new ActionListener() { 
						    public void actionPerformed(ActionEvent e) {
			        			  mainJFrame.dispose();
			    				  // homeView HV = new homeView();
			    				  //String[] buttonArray = new String[] {"Voter Search", "Import Voter","Duplicate Report","Audit User Activity"};
			    				  // HV.homePageView(buttonArray, "Central App Home");
						  		}
					      });
			    		}
			    		if(i == 102){  //go to first pair
			    			mybutton.addActionListener(new ActionListener() {
			    				public void actionPerformed(ActionEvent e){
			    					//get the label value and check which number we're on
			    					//then go to that pair of duplicates in voteridarray
			    					String[] voterIdArray = finalVoterID.split(",");
			    					String arraylen = ""+voterIdArray.length+"";
			        			    JLabel lbl = (JLabel) compList[3];
	        	        	    	String lblt = lbl.getText();
	        	        	    	int posfirstspace = lblt.indexOf(" of");
	        	        	    	String lblnum = lblt.substring(5, posfirstspace);
	        	        	    	int lbnum = Integer.parseInt(lblnum) - 1;
	        	        	    	String newpair = ""+lbnum+2 + "";
	        	        	    	newpair = newpair.substring(1,2);
	        	        	    	newpair = "View 1 of " + arraylen;
	        	        	    	lbl.setText(newpair);
	        	        	    	///lblt is the view pair text that tells you which pair you're on
	        	        	    	String voterpair = voterIdArray[(lbnum*2)]+","+voterIdArray[(lbnum*2)+1];
			        			    DBFunctions DBA = new DBFunctions();
			        			    DBA.makeConnection();
			        			    String[] prearray = new String[50];
			        			    String[] stringarray = new String[50];
			        			    prearray = DBA.selectVoter(voterpair);
			        			    for (int t = 0;t<prearray.length;t++){
			        			    	if(t < 25){
			        			    		stringarray[t] = prearray[t];
			        			    	}
			        			    	if(t > 24 && t < 49){
			        			    		stringarray[t] = prearray[t+1];
			        			    	}
			        			    }
			        			    int f = 0;
			        			    String fieldval;
			        			    ///System.out.println("stringarray size is : " + stringarray.length);
			        	        for (int i = 0; i < compList.length; i++) {
			        	        	//refers to button you want a textfield reference
			        	        	String first = compList[i].getClass().getName();
			        	        		if (first.equals("javax.swing.JTextField")) {
			        	    			    System.out.println("textfield number: " + i);
			        		  				    	JTextField fieldname = (JTextField) compList[i];
			        		  				    	fieldval = stringarray[f];
			        		  				    //	System.out.println("val stringarray " + f + " is " + fieldval + ".");
			        		  				    	fieldname.setText(fieldval);
			        		  				    	f++;
			        	        		}
			        	        }
			    				}
			    			});
			    		}
			    		if(i == 103){  //go to previous pair
			    			mybutton.addActionListener(new ActionListener() {
			    				public void actionPerformed(ActionEvent e){
			    					//get the label value and check which number we're on
			    					//then go to that pair of duplicates in voteridarray
			    					String[] voterIdArray = finalVoterID.split(",");
			    					String arraylen = ""+voterIdArray.length+"";
			        			    JLabel lbl = (JLabel) compList[3];
	        	        	    	String lblt = lbl.getText();
	        	        	    	int posfirstspace = lblt.indexOf(" of");
	        	        	    	String lblnum = lblt.substring(5, posfirstspace);
	        	        	    	int lbnum = Integer.parseInt(lblnum) - 1;
	        	        	    	String newpair = ""+lbnum+2 + "";
	        	        	    	newpair = newpair.substring(1,2);
	        	        	    	newpair = "View "+newpair+" of " + arraylen;
	        	        	    	lbl.setText(newpair);
	        	        	    	///lblt is the view pair text that tells you which pair you're on
	        	        	    	String voterpair = voterIdArray[(lbnum*2)]+","+voterIdArray[(lbnum*2)+1];
			        			    DBFunctions DBA = new DBFunctions();
			        			    DBA.makeConnection();
			        			    String[] prearray = new String[50];
			        			    String[] stringarray = new String[50];
			        			    prearray = DBA.selectVoter(voterpair);
			        			    for (int t = 0;t<prearray.length;t++){
			        			    	if(t < 25){
			        			    		stringarray[t] = prearray[t];
			        			    	}
			        			    	if(t > 24 && t < 49){
			        			    		stringarray[t] = prearray[t+1];
			        			    	}
			        			    }
			        			    int f = 0;
			        			    String fieldval;
			        			    ///System.out.println("stringarray size is : " + stringarray.length);
			        	        for (int i = 0; i < compList.length; i++) {
			        	        	//refers to button you want a textfield reference
			        	        	String first = compList[i].getClass().getName();
			        	        		if (first.equals("javax.swing.JTextField")) {
			        	    			    System.out.println("textfield number: " + i);
			        		  				    	JTextField fieldname = (JTextField) compList[i];
			        		  				    	fieldval = stringarray[f];
			        		  				    //	System.out.println("val stringarray " + f + " is " + fieldval + ".");
			        		  				    	fieldname.setText(fieldval);
			        		  				    	f++;
			        	        		}
			        	        }
				        			// DBF.updateVoter("", duplicateArray);
			    				}
			    			});
			    		}
			    		if(i == 104){  //go to next pair
			    			mybutton.addActionListener(new ActionListener() {
			    				public void actionPerformed(ActionEvent e){
			    					//get the label value and check which number we're on
			    					//then go to that pair of duplicates in voteridarray
			    					String[] voterIdArray = finalVoterID.split(",");
			    					String arraylen = ""+voterIdArray.length+"";
			        			    JLabel lbl = (JLabel) compList[3];
	        	        	    	String lblt = lbl.getText();
	        	        	    	int posfirstspace = lblt.indexOf(" of");
	        	        	    	String lblnum = lblt.substring(5, posfirstspace);
	        	        	    	int lbnum = Integer.parseInt(lblnum) + 1;
	        	        	    	String newpair = ""+lbnum+2 + "";
	        	        	    	newpair = newpair.substring(1,2);
	        	        	    	newpair = "View "+newpair+" of " + arraylen;
	        	        	    	lbl.setText(newpair);
	        	        	    	///lblt is the view pair text that tells you which pair you're on
	        	        	    	String voterpair = voterIdArray[(lbnum*2)]+","+voterIdArray[(lbnum*2)+1];
			        			    DBFunctions DBA = new DBFunctions();
			        			    DBA.makeConnection();
			        			    String[] prearray = new String[50];
			        			    String[] stringarray = new String[50];
			        			    prearray = DBA.selectVoter(voterpair);
			        			    for (int t = 0;t<prearray.length;t++){
			        			    	if(t < 25){
			        			    		stringarray[t] = prearray[t];
			        			    	}
			        			    	if(t > 24 && t < 49){
			        			    		stringarray[t] = prearray[t+1];
			        			    	}
			        			    }
			        			    int f = 0;
			        			    String fieldval;
			        			    ///System.out.println("stringarray size is : " + stringarray.length);
			        	        for (int i = 0; i < compList.length; i++) {
			        	        	//refers to button you want a textfield reference
			        	        	String first = compList[i].getClass().getName();
			        	        		if (first.equals("javax.swing.JTextField")) {
			        	    			    System.out.println("textfield number: " + i);
			        		  				    	JTextField fieldname = (JTextField) compList[i];
			        		  				    	fieldval = stringarray[f];
			        		  				    //	System.out.println("val stringarray " + f + " is " + fieldval + ".");
			        		  				    	fieldname.setText(fieldval);
			        		  				    	f++;
			        	        		}
			        	        }
				        			// DBF.updateVoter("", duplicateArray);
			    				}
			    			});
			    		}
			    		if(i == 105){  //go to last pair
			    			mybutton.addActionListener(new ActionListener() {
			    				public void actionPerformed(ActionEvent e){
			    					//get the label value and check which number we're on
			    					//then go to that pair of duplicates in voteridarray
			    					String[] voterIdArray = finalVoterID.split(",");
			    					String arraylen = ""+voterIdArray.length+"";
			        			    JLabel lbl = (JLabel) compList[3];
	        	        	    	String lblt = lbl.getText();
	        	        	    	int posfirstspace = lblt.indexOf(" of");
	        	        	    	String lblnum = lblt.substring(5, posfirstspace);
	        	        	    	int lbnum = Integer.parseInt(lblnum) - 1;
	        	        	    	String newpair = ""+lbnum+2 + "";
	        	        	    	newpair = newpair.substring(1,2);
	        	        	    	newpair = "View "+arraylen+" of " + arraylen;
	        	        	    	lbl.setText(newpair);
	        	        	    	///lblt is the view pair text that tells you which pair you're on
	        	        	    	String voterpair = voterIdArray[(lbnum*2)]+","+voterIdArray[(lbnum*2)+1];
			        			    DBFunctions DBA = new DBFunctions();
			        			    DBA.makeConnection();
			        			    String[] prearray = new String[50];
			        			    String[] stringarray = new String[50];
			        			    prearray = DBA.selectVoter(voterpair);
			        			    for (int t = 0;t<prearray.length;t++){
			        			    	if(t < 25){
			        			    		stringarray[t] = prearray[t];
			        			    	}
			        			    	if(t > 24 && t < 49){
			        			    		stringarray[t] = prearray[t+1];
			        			    	}
			        			    }
			        			    int f = 0;
			        			    String fieldval;
			        			    ///System.out.println("stringarray size is : " + stringarray.length);
			        	        for (int i = 0; i < compList.length; i++) {
			        	        	//refers to button you want a textfield reference
			        	        	String first = compList[i].getClass().getName();
			        	        		if (first.equals("javax.swing.JTextField")) {
			        	    			    System.out.println("textfield number: " + i);
			        		  				    	JTextField fieldname = (JTextField) compList[i];
			        		  				    	fieldval = stringarray[f];
			        		  				    //	System.out.println("val stringarray " + f + " is " + fieldval + ".");
			        		  				    	fieldname.setText(fieldval);
			        		  				    	f++;
			        	        		}
			        	        }
				        			// DBF.updateVoter("", duplicateArray);
			    				}
			    			});
			    		}
				    }
		        }
			mainJFrame.add(pnPanel0,BorderLayout.NORTH);
	        mainJFrame.pack();
	        mainJFrame.setVisible(true); 
    	}

    }        
    
       // public void getText(){

   	   // String mystring= t.getText();
   	   // System.out.println("textfield "+ mystring);
      //  }
    
    }
