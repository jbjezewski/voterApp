package voterApp;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.*;

import javax.swing.*;

import voterApp.DBFunctions;

public class ImportView extends Applet {

	String filename;
	String wholeFile;
	String myuserName = System.getProperty("user.name");
	String dir = "C:/Users/"+myuserName+"/Downloads/";
	String reDir = "/cygdrive/c/Users/Administrator/Desktop/";


    public void init(String ImportStep, String importFileName, int addC, int updateC, int verifyC, int deleteC, int totalC, String confirmInsertedFileName, String fontType){
    	final String ffontType = fontType;
    	if(ImportStep == "Voter1"){
    		String pageTitle = "Import Voter Tool Step 1 Upload";
			String[] buttonArray = new String[] {"",""};
			String msgString = "Select a file or files from your computer containing voter information. Then click Upload to view the confirmation page before submitting.";
    		voterApp.UITools UIT = new UITools();
    		JPanel pnPanel = new JPanel();
    		GridBagConstraints gbcPanel1 = new GridBagConstraints();
    		GridBagLayout gbPanel = new GridBagLayout();
    		GridBagLayout gbPanel0 = UIT.GridBagLayoutTool(gbcPanel1, gbPanel, pnPanel, 1,1,1,1, 2,1, 1, 1,1,1);
    		JPanel pnPanel0 = UIT.PanelTool(gbPanel0, pnPanel);
    		final JFrame mainJFrame = UIT.FrameTool(fontType, pnPanel0, buttonArray, pageTitle, 1024, 768, gbPanel0, gbcPanel1);
        	UIT.ButtonTool(fontType, "Browse", pnPanel0, 0, 1, 6, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
    		UIT.LabelImageTextFieldTool(fontType, pageTitle, "", "", 1, pnPanel0, 1, 6, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
        	UIT.ButtonTool(fontType, "Upload", pnPanel0, 0, 2, 9, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
        	UIT.ButtonTool(fontType, "Home", pnPanel0, 0, 0, 10, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
    		UIT.LabelImageTextFieldTool(fontType, pageTitle, pageTitle, "", 0, pnPanel0, 1, 1, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
    		UIT.LabelImageTextFieldTool(fontType, pageTitle, msgString, "", 0, pnPanel0, 2, 8, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);

	        final java.awt.Component[] compList = pnPanel0.getComponents();
	        java.awt.Component[] compsList = pnPanel0.getComponents();
	        for (int i = 0; i < compsList.length; i++) {
	        	String sec = compsList[i].getClass().getName();
			    if(sec.equals("javax.swing.JButton")){
			    		JButton mybutton = (JButton) compsList[i];
    			    	//System.out.println("JButton sequence in compslist" + compList[i]); //0,2,3,4 are the buttons in import view
			    		//you have to select each button individually and assign an action to each one
			    		if(i == 0){ //browse
			    			mybutton.addActionListener(new ActionListener() { 
			    				public void actionPerformed(ActionEvent e) { 
			    					JFileChooser fileChooser = new JFileChooser();
			    					fileChooser.setCurrentDirectory(new File(dir));
			    					int returnValue = fileChooser.showOpenDialog(null);
			    					File selectedFile;
			    					String fileName;
			    					if (returnValue == JFileChooser.APPROVE_OPTION) {
			    						selectedFile = fileChooser.getSelectedFile();
			    						fileName = selectedFile.getName();
			    						filename = fileName;
			    						System.out.println("fileName " + fileName);
			    						wholeFile = fileChooser.getSelectedFile().getAbsolutePath();
			    						JTextField textField = (JTextField) compList[1];
			    						textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
			    					//ImportView IV = new ImportView();
			    					//IV.init("Voter3");
			    					}			    					
			    				} 
			    			});
		    	        }if(i == 2){ //upload
		    	        	mybutton.addActionListener(new ActionListener() { 
		    	        		public void actionPerformed(ActionEvent e) { 
		    	        			FileFunctions FF = new FileFunctions();
		    	        			System.out.println("upload file for import filename " + filename);
		    	        			JTextField textField = (JTextField) compList[1];
		    						String wfile = textField.getText();
		    						//System.out.println(wfile);
		    	        			//change to windows remote directory
		    	        			String remoteFile = reDir + filename;
		    	        			String uploadstatus = FF.upload(wholeFile, remoteFile);
		    	        			if(uploadstatus.equals("true")){
		    	        				System.out.println("Successful upload of file: " + filename);
		    	        			}
		    	        			mainJFrame.dispose();
		    	        			ImportView IV2 = new ImportView();
		    	        			IV2.init("Voter2", wholeFile, 0,0,0,0,0,"", ffontType);
		    	        		} 
		    	        	});
		    	        }if(i == 3){ //home
		    	        	mybutton.addActionListener(new ActionListener() { 
		    	        		public void actionPerformed(ActionEvent e) { 
		    	        			mainJFrame.dispose();
		    	        		} 
		    	        	});
		    	        }
			    }
	        }
    		mainJFrame.add(pnPanel0,BorderLayout.SOUTH);
            mainJFrame.pack();
            mainJFrame.setVisible(true);
    	}
    	if(ImportStep == "Voter2"){
    	//check if voter file has possible duplicates, if so provide download links in buttons
    		System.out.println("importfilename " + importFileName);
    		final String fimportfilename = importFileName;
    		FileFunctions FFn = new FileFunctions();
    		String newfilename = FFn.addIDsToFile(importFileName);
    		final int addCount = FFn.countStrings("ADD", importFileName);
    		final int updateCount = FFn.countStrings("UPDATE", importFileName);
    		final int deleteCount = FFn.countStrings("DELETE", importFileName);
    		final int verifyCount = FFn.countStrings("VERIFY", importFileName);
    		final int totalCount = FFn.countRows(importFileName);
    		int indexlast = newfilename.lastIndexOf("\\");
    	    String justfilename = newfilename.substring(indexlast, newfilename.length());
    		final String remFileName = justfilename.substring(1,justfilename.length());
			FFn.upload(newfilename, reDir + remFileName);
    		String pageTitle = "Import Voter Tool Step Two: Confirm";
			String[] buttonArray = new String[] {"Home","Download File-To-File","Download File-To-Database","Confirm With Duplicates","Confirm Without File-To-File Duplicates","Confirm Without File-to-Database Duplicates"};
    		voterApp.UITools UIT = new UITools();
    		JPanel pnPanel = new JPanel();
    		GridBagConstraints gbcPanel1 = new GridBagConstraints();
    		GridBagLayout gbPanel = new GridBagLayout();
    		GridBagLayout gbPanel0 = UIT.GridBagLayoutTool(gbcPanel1, gbPanel, pnPanel, 1,1,1,1, 2,1, 1, 1,1,1);
    		JPanel pnPanel0 = UIT.PanelTool(gbPanel0, pnPanel);
    		final JFrame mainJFrame = UIT.FrameTool(fontType, pnPanel0, buttonArray, pageTitle, 1024, 768, gbPanel0, gbcPanel1);
	       // for (int k = 0; k < buttonArray.length; k ++){
           // 	UIT.ButtonTool(buttonArray[k], pnPanel0, k, 7, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
           // } 
    		String[] labelArray = new String[]{"Click Download File-To-Database to see the list of duplicates we've found between the file you uploaded and the database records.","Click Download File-To-File to see the list of duplicates within the files you uploaded."
					,"Check the box for which changes you want to make (Add, Update, Delete, or Verify), or click All to make all the changes.","Click Confirm Without Duplicates to add or modify the records that were not duplicates.","Click Confirm With Duplicates to add or modify all of the records from the files you uploaded."};		
    		final String[] labelsone = { "Add: ", "Verify: ", "Update: ", "Delete: ", "All: "};
    		for(int i=0;i<labelsone.length;i++){
    			UIT.LabelImageTextFieldTool(fontType, pageTitle, labelsone[i], "", 1, pnPanel0, 1, i+2, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
    		}for(int j=0;j<labelArray.length;j++){
    			UIT.LabelImageTextFieldTool(fontType, pageTitle, labelArray[j], "", 0, pnPanel0, 3, j+2, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
    		}
    		UIT.LabelImageTextFieldTool(fontType, pageTitle, pageTitle, "", 0, pnPanel0, 2, 0, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
	        final java.awt.Component[] compViewList = pnPanel0.getComponents();
	        java.awt.Component[] componentdetailList = pnPanel0.getComponents();
		    java.util.Random rand = new java.util.Random();
		    int randomNum = rand.nextInt((100000001 - 1000000) + 1) + 1000000;
		    final String newID = "" + randomNum + "";
	        for (int i = 0; i < componentdetailList.length; i++) {
	        	String sec = componentdetailList[i].getClass().getName();
	        	if(sec.equals("javax.swing.JTextField")){
	        		System.out.println("textfield iteration " + i);
	        		if(i == 7){
	        			JTextField jt = (JTextField) componentdetailList[i];
	        			jt.setText(""+addCount+"");
	        		}
	        		if(i == 9){
	        			JTextField jt = (JTextField) componentdetailList[i];
	        			jt.setText(""+verifyCount+"");
	        		}
	        		if(i == 11){
	        			JTextField jt = (JTextField) componentdetailList[i];
	        			jt.setText(""+updateCount+"");
	        		}
	        		if(i == 13){
	        			JTextField jt = (JTextField) componentdetailList[i];
	        			jt.setText(""+deleteCount+"");
	        		}
	        		if(i == 15){
	        			//int totalCount = addCount + updateCount + deleteCount + verifyCount;
	        			//may not be total count if type of import is not specified in some rows
	        			JTextField jt = (JTextField) componentdetailList[i];
	        			jt.setText(""+totalCount+"");
	        		}
	        	}
			    if(sec.equals("javax.swing.JButton")){
			    	System.out.println("JButton sequence in componentlist" + i); //0 - 5 are the buttons in import voter 3
			    		JButton mybutton = (JButton) componentdetailList[i];
			    		//you have to select each button individually and assign an action to each one
			    		if(i == 0){ //home
			    			System.out.println("Action: home");
			    			mybutton.addActionListener(new ActionListener() { 
			    				public void actionPerformed(ActionEvent e) { 
			    					  mainJFrame.dispose();
			    				} 
			    			});
			    		}if(i == 1){ //download file to file duplicates
			    			System.out.println("Action: download file to file duplicates");
			    			mybutton.addActionListener(new ActionListener() { 
			    				public void actionPerformed(ActionEvent e) { 
			    					//run function to find duplicates in a file and 
			    					//and mark the original file row as a potential duplicate
			    					//create a file containing those rows and return the filename
			    					//create temp table and insert file rows into the temp table
			    					DBFunctions DBF = new DBFunctions();
			    					String actualRemDir = "C:\\Users\\Administrator\\Desktop\\";
			    					System.out.println("remfilename is " + remFileName);
			    					String tempTableName = DBF.bulkInsertDuplicatesTemp(actualRemDir + remFileName, newID);
			    					//perform duplicate check on two tables, the temp table and the voter table
			    					String[][] idList = DBF.dupCheck(tempTableName, tempTableName);
			    					//as of now, dupCheck returns a multi-dimensional array
			    					String outputDir = "C:\\Users\\"+System.getProperty("user.name")+"\\Downloads\\";
			    					String newFilename = "possible_f2fduplicates_"+remFileName+".txt";
			    					//now write the idList results from dupCheck into a file whose name includes the original filename
			    			        try (
			    			                PrintStream output = new PrintStream(new File(outputDir + newFilename));
			    			            ){
			    			            for(int i=0;i<idList.length;i++){
			    			                String newRow ="";
			    			                for (int j=0;j<58;j++){
			    			                        newRow+=idList[i][j]+" ";
			    			                }
			    			                output.println(newRow);
			    			            }
			    			            output.close();
			    			        } catch (FileNotFoundException s) {
			    			            s.printStackTrace();
			    			        }
			    				} 
			    			});
			    		}if(i == 2){ //download file to database duplicates
			    			System.out.println("Action: download file to database duplicates");
			    			mybutton.addActionListener(new ActionListener() { 
			    				public void actionPerformed(ActionEvent e) { 
			    					//run function to find duplicates between file and database
			    					//and mark the original file row as a possible duplicate
			    					//create a file containing those rows and return the filename
			    					//use a special folder for files to be downloaded, currently using Admin/desktop
			    					//create temp table and insert file rows into the temp table
			    					DBFunctions DBF = new DBFunctions();
			    					String actualRemDir = "C:\\Users\\Administrator\\Desktop\\";
			    					System.out.println("remfilename is " + remFileName);
			    					String tempTableName = DBF.bulkInsertDuplicatesTemp(actualRemDir + remFileName, newID);
			    					//perform duplicate check on two tables, the temp table and the voter table
			    					String[][] idList = DBF.dupCheck(tempTableName, "Voters");
			    					//as of now, dupCheck returns a multi-dimensional array
			    					String outputDir = "C:\\Users\\"+System.getProperty("user.name")+"\\Downloads\\";
			    					String newFilename = "possible_f2dbduplicates_"+remFileName+".txt";			    					
			    					//now write the idList results from dupCheck into a file whose name includes the original filename
			    			        try (
			    			                PrintStream output = new PrintStream(new File(outputDir + newFilename));
			    			            ){
			    			            for(int i=0;i<idList.length;i++){
			    			                String newRow ="";
			    			                for (int j=0;j<58;j++){
			    			                        newRow+=idList[i][j]+" ";
			    			                }
			    			                output.println(newRow);
			    			            }
			    			            output.close();
			    			        } catch (FileNotFoundException s) {
			    			            s.printStackTrace();
			    			        }
			    				} 
			    			});
			    		}if(i == 3){ //confirm with duplicates
			    			System.out.println("Action: confirm with duplicates");

			    			mybutton.addActionListener(new ActionListener() { 
			    				public void actionPerformed(ActionEvent e) { 
			    					String actualRemDir = "C:\\Users\\Administrator\\Desktop\\";
			    					//import all rows from the file or temp table anyway even if possible duplicates were found
			    					//vidlist is the list of voters that are f2db duplicates
			    					//String[][] vidList = DBF.dupCheck(tempTableName, "Voters");
			    					//idlist is the list of voters that are f2f duplicates within the file
			    					//String[][] idList = DBF.dupCheck(tempTableName, tempTableName);	
			    					//confirm with duplicates indicates a simple bulk insert using the original file
			    					DBFunctions DBF = new DBFunctions();
			    					System.out.println("remfilename is " + remFileName);
			    					DBF.bulkInsert(actualRemDir + remFileName);
			    					//DBF.dropTempTable("Voters_Temp_"+newID);
			    					ImportView IV3 = new ImportView();
			    					IV3.init("Voter3", filename, addCount, updateCount, verifyCount, deleteCount, totalCount, remFileName, ffontType);
			    				} 
			    			});
			    		}if(i == 4){ //confirm without f2f duplicates
			    			System.out.println("Action: confirm with f2f duplicates");

			    			mybutton.addActionListener(new ActionListener() { 
			    				public void actionPerformed(ActionEvent e) { 
			    					//import only rows from the file that were not possible duplicates
			    					//you also need an array of the inserted record ids so you can undo it if necessary
			    					//either by keeping it in a variable or by changing a field in each record
			    					//to indicate that this was the most recent import and these records may
			    					//still be deleted in the next step or just keep this file for a while until 
			    					//they move past undo selected changes button in step 3 without clicking
			    					DBFunctions DBF = new DBFunctions();
			    					//check if temptablename exists, if not it means they didn't click a download button and you must create it with a bulk insert temp
			    					String actualRemDir = "C:\\Users\\Administrator\\Desktop\\";
			    					String tempTableName = DBF.bulkInsertDuplicatesTemp(actualRemDir + remFileName, newID);
			    					String[][] dupidlist = DBF.dupCheck(tempTableName, tempTableName);
			    					FileFunctions FF = new FileFunctions();
			    					String finalinsert = FF.createFileWithoutDuplicates(fimportfilename, "f2f", dupidlist);
			    					DBF.bulkInsert(finalinsert);
			    					//DBF.dropTempTable("Voters_Temp_"+newID);
			    					ImportView IV3 = new ImportView();
			    					IV3.init("Voter3", filename, addCount, updateCount, verifyCount, deleteCount, totalCount,finalinsert, ffontType);
			    				} 
			    			});
			    		}
			    		if(i == 5){ //confirm without f2db duplicates
			    			System.out.println("Action: confirm with f2db duplicates");

			    			mybutton.addActionListener(new ActionListener() { 
			    				public void actionPerformed(ActionEvent e) { 
			    					//import only rows from the file that were not possible duplicates
			    					DBFunctions DBF = new DBFunctions();		    					
			    					String actualRemDir = "C:\\Users\\Administrator\\Desktop\\";
			    					String tempTableName = DBF.bulkInsertDuplicatesTemp(actualRemDir + remFileName, newID);
			    					String[][] dupidlist = DBF.dupCheck("Voters", tempTableName);
			    					FileFunctions FF = new FileFunctions();
			    					String finalinsert = FF.createFileWithoutDuplicates(fimportfilename, "f2db",dupidlist);
			    					DBF.bulkInsert(finalinsert);
			    					//DBF.dropTempTable("Voters_Temp_"+newID);
			    					ImportView IV3 = new ImportView();
			    					IV3.init("Voter3", filename, addCount, updateCount, verifyCount, deleteCount, totalCount, finalinsert, ffontType);
			    				} 
			    			});
			    		}
			    }
	        }
    		mainJFrame.add(pnPanel0,BorderLayout.SOUTH);
            mainJFrame.pack();
            mainJFrame.setVisible(true);
    	}
    	if(ImportStep == "Voter3"){
    		String pageTitle = "Import Voter Tool Step Three: Summary";
			//String[] buttonArray = new String[] {"Undo Selected Changes","Home", "Download records not imported"};
			String[] buttonArray = new String[] {"",""};
    		voterApp.UITools UIT = new UITools();
    		JPanel pnPanel = new JPanel();
    		GridBagConstraints gbcPanel1 = new GridBagConstraints();
    		GridBagLayout gbPanel = new GridBagLayout();
    		GridBagLayout gbPanel0 = UIT.GridBagLayoutTool(gbcPanel1, gbPanel, pnPanel, 1,1,1,1, 2,1, 1, 1,1,1);
    		JPanel pnPanel0 = UIT.PanelTool(gbPanel0, pnPanel);
    		final JFrame mainJFrame = UIT.FrameTool(fontType, pnPanel0, buttonArray, pageTitle, 1024, 768, gbPanel0, gbcPanel1);
    		final String[] labelsone = { "Add: ", "Verify: ", "Update: ", "Delete: ", "All: "};
    		for(int i=0;i<labelsone.length;i++){
    			UIT.LabelImageTextFieldTool(fontType, pageTitle, labelsone[i], "", 1, pnPanel0, 1, i+2, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
    		}
    		String summary = "This is a summary of your Adds, Updates, Deletes, and Verifications. Check the boxes of any changes you wish to undo, then click Undo Selected Changes.";
    		UIT.LabelImageTextFieldTool(fontType, pageTitle, pageTitle, "", 0, pnPanel0, 1, 0, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
    		UIT.LabelImageTextFieldTool(fontType, pageTitle, summary, "", 0, pnPanel0, 1, 7, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
        	UIT.ButtonTool(fontType, "Undo Selected Changes", pnPanel0, 0, 1, 8, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
        	UIT.ButtonTool(fontType, "Home", pnPanel0, 0, 0, 9, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
        	UIT.ButtonTool(fontType, "Download Records Not Imported", pnPanel0, 0, 2, 8, 5,5,5,5, 50, 50, gbPanel0, gbcPanel1);

    		final java.awt.Component[] compViewList = pnPanel0.getComponents();
	        java.awt.Component[] componentdetailList = pnPanel0.getComponents();
	        for (int i = 0; i < componentdetailList.length; i++) {
	        	String sec = componentdetailList[i].getClass().getName();
		        	if(sec.equals("javax.swing.JTextField")){
		        		System.out.println("textfield iteration " + i);
		        		if(i == 1){
		        			JTextField jt = (JTextField) componentdetailList[i];
		        			jt.setText(""+addC+"");
		        		}
		        		if(i == 3){
		        			JTextField jt = (JTextField) componentdetailList[i];
		        			jt.setText(""+verifyC+"");
		        		}
		        		if(i == 5){
		        			JTextField jt = (JTextField) componentdetailList[i];
		        			jt.setText(""+updateC+"");
		        		}
		        		if(i == 7){
		        			JTextField jt = (JTextField) componentdetailList[i];
		        			jt.setText(""+deleteC+"");
		        		}
		        		if(i == 9){
		        			//int totalCount = addCount + updateCount + deleteCount + verifyCount;
		        			//may not be total count if type of import is not specified in some rows
		        			JTextField jt = (JTextField) componentdetailList[i];
		        			jt.setText(""+totalC+"");
		        		}
		        	}
			    if(sec.equals("javax.swing.JButton")){
			    	System.out.println("JButton 3 sequence in componentlist" + i); //0 and 1 are the buttons in import view 3
			    		JButton mybutton = (JButton) componentdetailList[i];
			    		//you have to select each button individually and assign an action to each one
			    		if(i == 12){ //undo selected changes
			    			mybutton.addActionListener(new ActionListener() { 
			    				public void actionPerformed(ActionEvent e) { 
			    					//UndoSelectedChanges();
			    					//do a dropTempTable(tablename) if they click this
			    					//delete file composed of non-duplicate voters if it exists
			    					//(if they clicked Confirm Without Duplicates in step 2)
			    				} 
			    			});
			    		}if(i == 13){ 
			    			mybutton.addActionListener(new ActionListener() { 
			    				public void actionPerformed(ActionEvent e) { 
			    					mainJFrame.dispose();
			    				} 
			    			});
			    		}if(i == 14){ 
			    			mybutton.addActionListener(new ActionListener() { 
			    				public void actionPerformed(ActionEvent e) { 
			    					//option to download list of duplicates not imported?
			    				} 
			    			});
			    		}
			    }
	        }
    		mainJFrame.add(pnPanel0,BorderLayout.SOUTH);
            mainJFrame.pack();
            mainJFrame.setVisible(true);
    	}
    	
    }

}
