package voterApp;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

import voterApp.*;

public class SearchView extends Applet {
    String voterid = "";
    String svoterid = "";
    String[] buttonArray = new String[] {"Search","Find Duplicates","Add Voter","View Voter","Compare Voters","Scan Fingerprint","Clear","Home"};
    String[] buttonKArray = new String[] {"ស្វែងរក ","ស្វែងរកច្បាប់ចម្លង","ថែមអ្នកបោះឆ្នោត","ពិនិត្យមើលអ្នកបោះឆ្នោត","ប្រៀបធៀបអ្នកបោះឆ្នោត","ថតចម្លងស្នាមម្រាមដៃ","លុបចេញ/ ជម្រះ","ទំព័រ​ដើម"};
    String pageTitle = "Voter Search";
	UITools UIT = new UITools();
	final UITools UITf = new UITools();
	GridBagLayout gbPanel0 = new GridBagLayout();
	JPanel pnPanel = new JPanel();
	JPanel pnPanel0 = new JPanel();
	GridBagConstraints gbcPanel1 = new GridBagConstraints();
    int rowCount;
	String buildqueryString;	
    String pageQuery;
	
	public void init(String query, String searchViewType, String fontType) {
    	final String ffontType = fontType;
    	System.out.println("search view font " + fontType);
			//GridBagLayout gbPanel = new GridBagLayout();
			gbcPanel1 = UIT.GridBagConstraintsTool(gbPanel0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
			gbPanel0 = UIT.GridBagLayoutTool(gbcPanel1, gbPanel0, pnPanel, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
			pnPanel0 = UIT.PanelTool(gbPanel0, pnPanel);
	        for (int k = 0; k < 7; k ++){
            	//UIT.ButtonTool(buttonArray[k], pnPanel0, k, 2, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
            }
			final JFrame mainJFrame = UIT.FrameTool(ffontType, pnPanel0, buttonArray, pageTitle, 1024, 768, gbPanel0, gbcPanel1);
		    final String[] labels = {"Family Name: ", "Birth Date: ", "CID: ", "Registered Address 1: ", "Registered Address 2: ", "Registered Address 3: ", "Registered Village: ", "Registered Commune: ", "Registered District: ", "Registered Province: ", "Registered City: ", "Registered Zip: "};
		    final String[] labelsK = {"ត្រកូល: ", "ថ្ងៃខែឆ្នាំកំនើត: ", "ប័ណ្ណបញ្ជាក់អត្តសញ្ញាណ: ", "អាសយដ្ឋាន្នដែលបានចុះបញ្ជី មួយ: ", "អាសយដ្ឋាន្នដែលបានចុះបញ្ជី ពីរ​នាក់: ", "អាសយដ្ឋាន្នដែលបានចុះបញ្ជី បី​នាក់: ", "បាន​ចុះ​ឈ្មោះ ភូមិ: ", "បាន​ចុះ​ឈ្មោះ ឃុំ ឬ សង្កាត់: ", "បាន​ចុះ​ឈ្មោះ ស្រុក ឬ ខ័ណ្ឌ: ", "បាន​ចុះ​ឈ្មោះ ខេត្ត: ", "បាន​ចុះ​ឈ្មោះ ក្រុង: ", "បាន​ចុះ​ឈ្មោះ លេខកូដតំបន់: "};
		    int labelsLength = labels.length;
		    final String[] labelstwo = { "Given Name: ", "Regnum: ", "NID: ", "Mailing Address 1: ", "Mailing Address 2: ", "Mailing Address 3: ", "Mailing Village: ", "Mailing Commune: ", "Mailing District: ", "Mailing Province: ", "Mailing City: ", "Mailing Zip: "};
		    final String[] labelsKtwo = { "ឈ្មោះ: ", "លេខចុះឈ្មោះ: ", "អត្តសញ្ញាណប័ណ្ណជាតិ: ", "អាសយដ្ឋាន្នប៉ុស្ត៍សំបុត្រ មួយ: ", "អាសយដ្ឋាន្នប៉ុស្ត៍សំបុត្រ ពីរ​នាក់: ", "អាសយដ្ឋាន្នប៉ុស្ត៍សំបុត្រ បី​នាក់: ", "សំបុត្រ​រួម ភូមិ: ", "សំបុត្រ​រួម ឃុំ ឬ សង្កាត់: ", "សំបុត្រ​រួម ស្រុក ឬ ខ័ណ្ឌ: ", "សំបុត្រ​រួម ខេត្ត: ", "សំបុត្រ​រួម ក្រុង: ", "សំបុត្រ​រួម លេខកូដតំបន់: "};
		    int labelstwoLength = labelstwo.length;
		    
		    for (int i = 0; i < labelsLength; i++) {
		    	if(!ffontType.equals("Khmer")){
		    		UIT.LabelImageTextFieldTool(ffontType, "Voter Search", labels[i], "", 1, pnPanel0, 1, i+3, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);  
		    	}else if(ffontType.equals("Khmer")){
		    		UIT.LabelImageTextFieldTool(ffontType, "Voter Search", labelsK[i], "", 1, pnPanel0, 1, i+3, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
		    	}
		    }
		    for (int i = 0; i < labelstwoLength; i++) {
		    	if(!ffontType.equals("Khmer")){
		    		UIT.LabelImageTextFieldTool(ffontType, "Voter Search", labelstwo[i], "", 1, pnPanel0, 3, i+3, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
		    	} else if(ffontType.equals("Khmer")){
			    	UIT.LabelImageTextFieldTool(ffontType, "Voter Search", labelsKtwo[i], "", 1, pnPanel0, 3, i+3, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);		    		
		    	}
		    }
		    //textfield for selected row value
		    	UIT.LabelImageTextFieldTool(ffontType, "Voter Search", "Selected Row","", 1, pnPanel0, 0, 22, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
		        java.awt.Component[] componentList = pnPanel0.getComponents();
		        final java.awt.Component[] compList = pnPanel0.getComponents();

		    if(searchViewType.equals("query")){
		    	//filling in previous query search values that gave the displayed results
		    	//shouldnt need to do this for duplicates
		    	int f = 0;
		    	String fieldval = "";
		    	String[] columnLabels = {"Family Name: ","last_name","Birth Date: ", "birthdate","CID: ", "cid","Registered Address 1: ", "reg_address1","Registered Address 2: ", "reg_address2","Registered Address 3: ", "reg_address3","Registered Village: ", "reg_village","Registered Commune: ", "reg_commune", "Registered District: ", "reg_district","Registered Province: ", "reg_province","Registered City: ", "reg_city","Registered Zip: ", "reg_zip","Given Name: ", "first_name","Regnum: ", "regnum", "NID: ", "nid","Mailing Address 1: ", "mail_address1","Mailing Address 2: ", "mail_address2","Mailing Address 3: ", "mail_address3","Mailing Village: ", "mail_village","", "Mailing Commune: ", "mail_commune","", "Mailing District: ", "mail_district","", "Mailing Province: ", "mail_province","Mailing City: ", "mail_city","Mailing Zip: ", "mail_zip"};
		    	String[] columnLabelsK = {"ត្រកូល:","last_name",  "ថ្ងៃខែឆ្នាំកំនើត: ", "birthdate",  "ប័ណ្ណបញ្ជាក់អត្តសញ្ញាណ: ", "cid",  "អាសយដ្ឋាន្នដែលបានចុះបញ្ជី មួយ: ", "reg_address1",  "អាសយដ្ឋាន្នដែលបានចុះបញ្ជី ពីរ​នាក់: ", "reg_address2",  "អាសយដ្ឋាន្នដែលបានចុះបញ្ជី បី​នាក់: ", "reg_address3", "បាន​ចុះ​ឈ្មោះ ភូមិ: ", "reg_village", "បាន​ចុះ​ឈ្មោះ ឃុំ ឬ សង្កាត់:  ", "reg_commune",   "បាន​ចុះ​ឈ្មោះ ស្រុក ឬ ខ័ណ្ឌ: ", "reg_district",  "បាន​ចុះ​ឈ្មោះ ខេត្ត: ", "reg_province",  "បាន​ចុះ​ឈ្មោះ ក្រុង: ", "reg_city",  "បាន​ចុះ​ឈ្មោះ លេខកូដតំបន់: ", "r	eg_zip",  "ឈ្មោះ: ", "first_name",  "លេខចុះឈ្មោះ: ", "regnum",   "អត្តសញ្ញាណប័ណ្ណជាតិ: ", "nid",  "អាសយដ្ឋាន្នប៉ុស្ត៍សំបុត្រ មួយ: ", "mail_address1",  "អាសយដ្ឋាន្នប៉ុស្ត៍សំបុត្រ ពីរ​នាក់: ", "mail_address2",  "អាសយដ្ឋាន្នប៉ុស្ត៍សំបុត្រ បី​នាក់: ", "mail_address3",  "សំបុត្រ​រួម ភូមិ: ", "mail_village", "សំបុត្រ​រួម ឃុំ ឬ សង្កាត់: ", "mail_commune", "សំបុត្រ​រួម ស្រុក ឬ ខ័ណ្ឌ: ", "mail_district", "សំបុត្រ​រួម ខេត្ត: ", "mail_province",  "សំបុត្រ​រួម ក្រុង: ", "mail_city",  "សំបុត្រ​រួម លេខកូដតំបន់: ", "mail_zip"};		    	
		    	int startWhere = query.indexOf("WHERE");
		    	int startOrder = query.indexOf("ORDER BY");
		    	if(startWhere != -1 && startOrder != -1){
		    		query = query.substring(startWhere, startOrder);
		    		String[] prevQuery = query.split(" ");
		    		for(int l = 0; l < prevQuery.length; l++){
		    			System.out.println("prevQuery " + l + " = '" + prevQuery[l] + "'");
		    		}
		    		int x = 0;
		    		if(prevQuery.length < 6){
		    			x = ((prevQuery.length - 1)/3) + 1;
		    		}else if(prevQuery.length == 6 || prevQuery.length > 6){
		    			x = (prevQuery.length - 1)/2;
		    		}
		    		String[] displayQuery = new String[x];
		    		System.out.println("prevQuery length " + prevQuery.length);
		    		System.out.println("displayQuery length " + displayQuery.length);
		    		int s = 0;
		    		for(int h = 0; h < prevQuery.length; h++){
		    			if(prevQuery[h] != null && prevQuery[h].isEmpty() != true){
		    				if(prevQuery[h].indexOf("LIKE") == -1 && prevQuery[h].indexOf("AND") == -1 && prevQuery[h].indexOf("WHERE") == -1 && prevQuery[h].indexOf("%") == -1){
		    					displayQuery[s] = prevQuery[h];
				    			System.out.println(" prevQuery up " + h + "  " + prevQuery[h]);
				    			System.out.println(" displayQuery up " + s + "  " + displayQuery[s]);
		    					s++;
		    				}if(prevQuery[h].indexOf("%") != -1){
		    					displayQuery[s] = prevQuery[h].substring(2, prevQuery[h].length() - 2);
				    			System.out.println(" prevQuery down " + h + "  " + prevQuery[h]);
				    			System.out.println(" displayQuery down " + s + "  " + displayQuery[s]);
		    					s++;
		    				}
		    			}
		    		}
		    		for (int e = 0; e<displayQuery.length; e++){
		    			System.out.println(" displayQuery " + e + "  " + displayQuery[e]);
		    		}
		    		for (int i = 0; i < componentList.length; i++) {
		    			//refers to button you want a textfield reference
		    			String first = componentList[i].getClass().getName();
		        		if (first.equals("javax.swing.JTextField")) {
		        			JLabel assocLabel = (JLabel) componentList[i-1];
		        			String labelTxt = assocLabel.getText();
		        			System.out.println("labeltxt " + labelTxt);
		        			if(!ffontType.equals("Khmer")){
		        			for(int y = 0; y < columnLabels.length; y+=2){
			        			if(labelTxt.equals(columnLabels[y])){
			        				System.out.println("column label y " + y + " = " + columnLabels[y]);
			        				//now iterate through displayquery and check if this field is in that array
			        				for(int r = 0; r < displayQuery.length; r++){
			        					if(displayQuery[r].equals(columnLabels[y+1])){
			        						JTextField fieldname = (JTextField) componentList[i];
			        						fieldval = displayQuery[f+1];
			        						System.out.println("val stringarray " + f + " is " + fieldval + ".");
			        						fieldname.setText(fieldval);
			        						if(f + 2 < displayQuery.length && f + 2 != displayQuery.length){
			        							f = f+2;
			        						}
			        					}
			        				}
			        			}
		        			}
		        			}else if(ffontType.equals("Khmer")){
			        			for(int y = 0; y < columnLabelsK.length; y+=2){
				        			if(labelTxt.equals(columnLabelsK[y])){
				        				System.out.println("column labelk y " + y + " = " + columnLabelsK[y]);
				        				//now iterate through displayquery and check if this field is in that array
				        				for(int r = 0; r < displayQuery.length; r++){
				        					if(displayQuery[r].equals(columnLabelsK[y+1])){
				        						JTextField fieldname = (JTextField) componentList[i];
				        						fieldval = displayQuery[f+1];
				        						System.out.println("val stringarray " + f + " is " + fieldval + ".");
				        						fieldname.setText(fieldval);
				        						if(f + 2 < displayQuery.length && f + 2 != displayQuery.length){
				        							f = f+2;
				        						}
				        					}
				        				}
				        			}
			        			}		        				
		        			}
		        			
		        		}
		    		}
		    	}
		    	else if(startWhere == -1){
		    		System.out.println("no where clause for this search");
		    	}
		    }
		    if(searchViewType.equals("query")){
		    	 SearchResults SR = new SearchResults();
				  JTable mytable = SR.init(query, mainJFrame, ffontType);
   	          int rowCount = mytable.getRowCount();
   	          int columnCount = mytable.getColumnCount();
     	        //check if table dummydata has dimensions of 0x0 because that means there were no results found
   	          if(rowCount == 0 && columnCount == 0){
   	        	  UIT.LabelImageTextFieldTool(ffontType, "Voter Search", "No results found", "", 0, pnPanel0, 3, 24,1,1,1,1,50,50,gbPanel0,gbcPanel1);
   	          }
     	          mytable.addMouseListener(new MouseAdapter() {
   				    @Override
   				    public void mouseClicked(final MouseEvent e) {
   				    	System.out.println("Mouse clicked " + e.MOUSE_CLICKED);
   				        if (e.getClickCount() == 1) {
   				            final JTable target = (JTable)e.getSource();
   				            final int row = target.getSelectedRow();
   				            final int column = target.getSelectedColumn();
   				            final String voterid = (String)target.getValueAt(row, column).toString();
   				            final int[] var = target.getSelectedRows();
			        	    //	Object[] selectedRows = new Object[10];
		        	    	List<String> selectedRows = new ArrayList<String>();
		        	    	for(int i = 0; i < target.getRowCount(); i++) {
		        	    	     if((Boolean) target.getValueAt(i, 0)) {
		        	    	    	 System.out.println(target.getValueAt(i,0));
		        	    	    	 System.out.println(target.getValueAt(i,1));
		        	    	    		 //selectedRows.add(i);
		        	    	    		 selectedRows.add(""+target.getValueAt(i, 1)+"");
						        	    	System.out.println("selectedRows");
						        	    	System.out.println(selectedRows);
		        	    	     }
		        	    	}
		        	    	String allselected = selectedRows.toString();
   		        	      for (int n = 0; n<var.length;n++){
   		        	    	 // System.out.println("selected rows n down " + var[n]);
   		        	      }
   			        	      int go = 0;
   		        	    	for(int n = 0;n<compList.length;n++){
   		        	    		String first = compList[n].getClass().getName();
   		    	        		if (first.equals("javax.swing.JLabel")) {
   		        				    JLabel thisLabel = (JLabel) compList[n];
   		        				    String labelText = thisLabel.getText();
   		        				    if(labelText.equals("Selected Row") || labelText.equals("ជួរ​ដេក​ដែល​បាន​ជ្រើស")){
   		        				    	go = n;
   		        				    }
   		    	        		}if(first.equals("javax.swing.JTextField") && (n - 1 ) == go){
   		    	        			JTextField txtField = (JTextField) compList[n];
   		    	        			txtField.setText(allselected);
   		    	        			System.out.println("search voterid = " + allselected);
   		    	        		}
   		        	    	}
   				        }
   				    }
   				});
		    }
		    if(searchViewType.equals("clear")){
				  SearchResults SR = new SearchResults();
				  SR.init("none",mainJFrame, ffontType);
		    }
		    if(searchViewType.equals("duplicates")){
		    		//add a mark as duplicates button or message "Possible Duplicates"
		    	  SearchResults SR = new SearchResults();
		  	      System.out.println("true query " + query);
		  	      JTable mytable = SR.init(query, mainJFrame, ffontType); //problem here for non-duplicate queries
			          int rowCount = mytable.getRowCount();
			          int columnCount = mytable.getColumnCount();
		  	        //check if table dummydata has dimensions of 0x0 because that means there were no results found
			          if(rowCount == 0 && columnCount == 0){
			        	  UIT.LabelImageTextFieldTool(ffontType, "Voter Search", "No results found", "", 0, pnPanel0, 3, 24,1,1,1,1,50,50,gbPanel0,gbcPanel1);
			          }
		  	          mytable.addMouseListener(new MouseAdapter() {
						    @Override
						    public void mouseClicked(final MouseEvent e) {
						    	System.out.println("Mouse clicked " + e.MOUSE_CLICKED);
						        if (e.getClickCount() == 1) {
						            final JTable target = (JTable)e.getSource();
						            final int row = target.getSelectedRow();
						            final int column = target.getSelectedColumn();
						            final String voterid = (String)target.getValueAt(row, column).toString();
						            final int[] var = target.getSelectedRows();
				        	    	List<String> selectedRows = new ArrayList<String>();
				        	    	for(int i = 0; i < target.getRowCount(); i++) {
				        	    	     if((Boolean) target.getValueAt(i, 0)) {
				        	    	    	 System.out.println(target.getValueAt(i,0));
				        	    	    	 System.out.println(target.getValueAt(i,1));
				        	    	    		 //selectedRows.add(i);
				        	    	    		 selectedRows.add(""+target.getValueAt(i, 1)+"");
								        	    	System.out.println("selectedRows");
								        	    	System.out.println(selectedRows);
				        	    	     }
				        	    	}
				        	    	String allselected = selectedRows.toString();
				        	      for (int n = 0; n<var.length;n++){
				        	    	  System.out.println("selected column n down " + var[n]);
				        	      }
					        	      int go = 0;
				        	    	for(int n = 0;n<compList.length;n++){
				        	    		String first = compList[n].getClass().getName();
				    	        		if (first.equals("javax.swing.JLabel")) {
				        				    JLabel thisLabel = (JLabel) compList[n];
				        				    String labelText = thisLabel.getText();
				        				    if(labelText.equals("Selected Row") || labelText.equals("ជួរ​ដេក​ដែល​បាន​ជ្រើស")){
				        				    	go = n;
				        				    }
				    	        		}if(first.equals("javax.swing.JTextField") && (n - 1 ) == go){
				    	        			JTextField txtField = (JTextField) compList[n];
				    	        			txtField.setText(allselected);
				    	        			System.out.println("voterid = " + allselected);
				    	        		}
				        	    	}
						        }
						    }
						});
				
		    }
	      //  final java.awt.Component[] compsList = mainJFrame.getComponents();
	        for (int i = 0; i < compList.length; i++) {
	        //	System.out.println(compList[i]);
	        //	System.out.println(compList[i].getClass().getName());
	        }
	        for (int i = 0; i < componentList.length; i++) {
	        	//System.out.println(componentList[i]);
	        	String sec = componentList[i].getClass().getName();
			    if(sec.equals("javax.swing.JButton")){
			    	//System.out.println(componentList[i]); //0 through 7 are the buttons
			    		JButton mybutton = (JButton) componentList[i];
			    		//you have to select each button individually and assign an action to each one
			    	    if(i == 0){ //perform search
			    			  mybutton.addActionListener(new ActionListener() { 
			    				  public void actionPerformed(ActionEvent e) {
			    					  //build where clause of query by fetching textfield values
					        	    	buildqueryString = "";
					        			String txtvalue = "";
				        				String[] columnValueArray = {"Family Name: ","last_name", "", "Birth Date: ", "birthdate", "", "CID: ", "cid", "", "Registered Address 1: ", "reg_address1", "", "Registered Address 2: ", "reg_address2", "", "Registered Address 3: ", "reg_address3", "", "Registered Village: ", "reg_village", "", "Registered Commune: ", "reg_commune",  "", "Registered District: ", "reg_district", "", "Registered Province: ", "reg_province", "", "Registered City: ", "reg_city", "", "Registered Zip: ", "reg_zip", "", "Given Name: ", "first_name", "", "Regnum: ", "regnum",  "", "NID: ", "nid", "", "Mailing Address 1: ", "mail_address1", "", "Mailing Address 2: ", "mail_address2", "", "Mailing Address 3: ", "mail_address3", "", "Mailing Village: ", "mail_village","", "Mailing Commune: ", "mail_commune","", "Mailing District: ", "mail_district","", "Mailing Province: ", "mail_province", "", "Mailing City: ", "mail_city", "", "Mailing Zip: ", "mail_zip",""};
				        				String[] columnValueArrayK = {"ត្រកូល: ","last_name", "", "ថ្ងៃខែឆ្នាំកំនើត: ", "birthdate", "", "ប័ណ្ណបញ្ជាក់អត្តសញ្ញាណ: ", "cid", "", "អាសយដ្ឋាន្នដែលបានចុះបញ្ជី មួយ: ", "reg_address1", "", "អាសយដ្ឋាន្នដែលបានចុះបញ្ជី ពីរ​នាក់: ", "reg_address2", "", "អាសយដ្ឋាន្នដែលបានចុះបញ្ជី បី​នាក់: ", "reg_address3", "បាន​ចុះ​ឈ្មោះ ភូមិ: ", "reg_village", "បាន​ចុះ​ឈ្មោះ ឃុំ ឬ សង្កាត់: ", "reg_commune",  "", "បាន​ចុះ​ឈ្មោះ ស្រុក ឬ ខ័ណ្ឌ: ", "reg_district", "", "បាន​ចុះ​ឈ្មោះ ខេត្ត: ", "reg_province", "", "បាន​ចុះ​ឈ្មោះ ក្រុង: ", "reg_city", "", "បាន​ចុះ​ឈ្មោះ លេខកូដតំបន់: ", "reg_zip", "", "ឈ្មោះ: ", "first_name", "", "លេខចុះឈ្មោះ: ", "regnum",  "", "អត្តសញ្ញាណប័ណ្ណជាតិ: ", "nid", "", "អាសយដ្ឋាន្នប៉ុស្ត៍សំបុត្រ មួយ: ", "mail_address1", "", "អាសយដ្ឋាន្នប៉ុស្ត៍សំបុត្រ ពីរ​នាក់: ", "mail_address2", "", "អាសយដ្ឋាន្នប៉ុស្ត៍សំបុត្រ បី​នាក់: ", "mail_address3", "", "សំបុត្រ​រួម ភូមិ: ", "mail_village","", "សំបុត្រ​រួម ឃុំ ឬ សង្កាត់: ", "mail_commune","", "សំបុត្រ​រួម ស្រុក ឬ ខ័ណ្ឌ: ", "mail_district","", "សំបុត្រ​រួម ខេត្ត: ", "mail_province", "", "សំបុត្រ​រួម ក្រុង: ", "mail_city", "", "សំបុត្រ​រួម លេខកូដតំបន់: ", "mail_zip",""};
				        				
				        				for(int n = 0;n<compList.length;n++){
					        	    		String first = compList[n].getClass().getName();
					    	        		if (first.equals("javax.swing.JLabel")) {
					    	        			if(compList[n+1].getClass().getName() != "javax.swing.JCheckBox"){
					    	        				JTextField txtField = (JTextField) compList[n+1];
					    	        				txtvalue = txtField.getText();
					    	        				JLabel thisLabel = (JLabel) compList[n];
					    	        				String labelText = thisLabel.getText();
					    	        				if(!ffontType.equals("Khmer")){
					    	        					for(int g=0;g<columnValueArray.length;g++){
					    	        						if(labelText.equals(columnValueArray[g])){
					    	        							columnValueArray[g+2] = columnValueArray[g+2].replace(columnValueArray[g+2],txtvalue);
					    	        						}
					    	        					}
					    	        				}else if(ffontType.equals("Khmer")){
					    	        					for(int g=0;g<columnValueArrayK.length;g++){
					    	        						if(labelText.equals(columnValueArrayK[g])){
					    	        							columnValueArrayK[g+2] = columnValueArrayK[g+2].replace(columnValueArrayK[g+2],txtvalue);
					    	        						}
					    	        					}
					    	        				}
					    	        			}
					    	        		}
					        	    	}
					        	    	for (int p = 0; p < columnValueArray.length; p+=3){
					        	    		if(p == 0 && !columnValueArray[p+2].equals("")){
					        	    			buildqueryString = " WHERE " + columnValueArray[p+1] + " LIKE '%" + columnValueArray[p+2] + "%' ";
					        	    		}
					        	    		else if(p != 0 && !columnValueArray[p+2].equals("")){
					        	    			buildqueryString += " AND " + columnValueArray[p+1] + " LIKE '%" + columnValueArray[p+2] + "%' ";
					        	    		}
					        	    	}
					        	      System.out.println("squery is true " + buildqueryString);
					        	      if(buildqueryString != "" && buildqueryString.substring(0,4).equals(" AND")){
					        	    	  buildqueryString = " WHERE " + buildqueryString.substring(4, buildqueryString.length());
					        	      }
					          	      if(buildqueryString == null){
					          	    	  buildqueryString = "";
					          	      }else if(buildqueryString != null){
						        	    	buildqueryString += " ORDER BY VoterID"; 
					          	      }
					          	      	  mainJFrame.dispose();
					          	    	  SearchView SV = new SearchView();
					        	    	  SV.init(buildqueryString, "query", ffontType);
				    					 
			    				  }
			    			  });
			    		}
			    		if(i == 1){
			    			mybutton.addActionListener(new ActionListener() {
			    				public void actionPerformed(ActionEvent e){
			    					buildqueryString = "";
			    					 //this is the duplicate query if one of the duplicate fields has a value
			    					 //this is the duplicate query if one of the fields has a character * to allow duplicate checks on other fields than BANG
			    					 //this is the duplicate query if all the fields are null or for the duplicate report
			    					//concatenate first name and last name
			    	            String columnList = "v1.VoterID,v1.first_name,v1.last_name,v1.regnum,v1.reg_address1,v1.reg_address2,v1.phone1,v1.nid";
			    	        	buildqueryString += " SELECT v1.VoterID, v2.* FROM Morinteresting.dbo.Voters v1 JOIN ( ";
			    	            buildqueryString += " SELECT first_name, last_name, gender, birthdate, reg_address1 "; //,count(*) as qty;
			    	            buildqueryString += " FROM Morinteresting.dbo.Voters ";
			    	            buildqueryString += " GROUP BY first_name, last_name, gender, birthdate, reg_address1 ";
			    	            buildqueryString += " HAVING count(*) > 1 ) v2 ON v1.first_name = v2.first_name AND ";
			    	        	buildqueryString += " v1.last_name = v2.last_name AND v1.gender = v2.gender AND v1.birthdate = v2.birthdate ";
			    	        	buildqueryString += " AND v1.reg_address1 = v2.reg_address1 ";
				        	     // System.out.println("search view query string: " + queryString);
			    				  mainJFrame.dispose();
				        	      SearchView SV = new SearchView();
								  SV.init(buildqueryString, "duplicates", ffontType);
					        	    //  System.out.println("dv id " + voterid);
			    				}
			    			});
			    		}
			    		if(i == 2){//add
			    			mybutton.addActionListener(new ActionListener() {
			    				public void actionPerformed(ActionEvent e){
			    					//generate random number	    					
			    					DetailView DV = new DetailView();
			    					DV.init("add","", ffontType);		
			    					System.out.println("font type before add voter click " + ffontType);
			    				}
			    			});
			    		}
			    		if(i == 3){ //view voter
			    			mybutton.addActionListener(new ActionListener() {
			    				public void actionPerformed(ActionEvent e){
			    					//get selected voter id from search results table
					        	      int go = 0;
					        	      String txtvalue = "";
					        	    	for(int n = 0;n<compList.length;n++){
					        	    		String first = compList[n].getClass().getName();
					    	        		if (first.equals("javax.swing.JLabel")) {
					        				    JLabel thisLabel = (JLabel) compList[n];
					        				    String labelText = thisLabel.getText();
					        				    if(labelText.equals("Selected Row")){
					        				    	go = n;
					        				    }
					    	        		}if(first.equals("javax.swing.JTextField") && (n - 1 ) == go){
					    	        			JTextField txtField = (JTextField) compList[n];
					    	        			txtvalue = txtField.getText();
					    	        			System.out.println("selected voterid = " + txtvalue);
					    	        		}
					        	    	}

				    					DetailView DV = new DetailView();
				    					DV.init("view",txtvalue, ffontType);
			    				}
			    			});
			    		}
			    		if(i == 4){//compare voter
			    			  mybutton.addActionListener(new ActionListener() { 
			    				  public void actionPerformed(ActionEvent e) {
					        	      int go = 0;
					        	      String txtvalue = "";
					        	    	for(int n = 0;n<compList.length;n++){
					        	    		String first = compList[n].getClass().getName();
					    	        		if (first.equals("javax.swing.JLabel")) {
					        				    JLabel thisLabel = (JLabel) compList[n];
					        				    String labelText = thisLabel.getText();
					        				    if(labelText.equals("Selected Row")){
					        				    	go = n;
					        				    }
					    	        		}if(first.equals("javax.swing.JTextField") && (n - 1 ) == go){
					    	        			JTextField txtField = (JTextField) compList[n];
					    	        			txtvalue = txtField.getText();
					    	        			System.out.println("selected compare rows = " + txtvalue);
					    	        		}
					        	    	}
					        	    	//get both selected voters
				    					DetailView DV = new DetailView();
				    					DV.init("compare",txtvalue, ffontType);
			    				  }
			    			  });
			    		  }
			    		if(i == 5){
			    			  mybutton.addActionListener(new ActionListener() { 
			    				  public void actionPerformed(ActionEvent e) {
			    					    String finalVoterID = ""; //get selected voter or print up empty scan screen
				    					ScanFingerprint SF = new ScanFingerprint();
				    					SF.init(ffontType, finalVoterID);
			    				  }
			    			  });
			    		  }
			    		if(i == 6){
			    			  mybutton.addActionListener(new ActionListener() { 
			    				  public void actionPerformed(ActionEvent e) {	
			    					  	//clear table
			    					  mainJFrame.dispose();
			    					  SearchView SV = new SearchView();
			    					  SV.init(buildqueryString, "clear", ffontType);
			    				  }
			    			  });
			    		}
			    		if(i == 7){//home
			    			  mybutton.addActionListener(new ActionListener() { 
			    				  public void actionPerformed(ActionEvent e) {	
			    					    mainJFrame.dispose();
			    				  }
			    			  });
			    		}
			    }
	        }



	          for (int g = 0; g < 11; g++){
	        //	  UIT.CheckBoxTool("Voter Search", pnPanel0, 0, g+23, 1,1,1,1,1,1, gbPanel0, gbcPanel1);
	          }
		if(query == "query"){
			System.out.println("query condition is true");
		}
		if(query == "duplicates"){
			System.out.println(" condition is true");

		}
		if(query == "clear"){
      	  UIT.LabelImageTextFieldTool(ffontType, "Voter Search", "Enter search values or check for duplicates.", "", 0, pnPanel0, 3, 24,1,1,1,1,50,50,gbPanel0,gbcPanel1);
		}
			mainJFrame.add(pnPanel0,BorderLayout.NORTH);
		    mainJFrame.pack();
		    mainJFrame.setVisible(true);	
	}
	public SearchView(){
		// TODO Auto-generated constructor stub
	}

}
