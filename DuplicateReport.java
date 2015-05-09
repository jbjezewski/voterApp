package voterApp;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.swing.*;

import voterApp.*;

public class DuplicateReport extends Applet{
    public void init(String fontType){
    	final String ffontType = fontType;
        final String uname = System.getProperty("user.name");
        final String localDir = "C:\\Users\\"+uname+"\\Downloads\\";
        final Date currentdate = new Date();
        final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
        final String datestamp = SDF.format(currentdate);
		final String dupFile = "duplicate_chart_"+datestamp+".txt";
	   	//perform duplicate check on two tables, the voter table, and specifies a certain type of duplicate check
		DBFunctions DBF = new DBFunctions();
		final String[][] idList4 = DBF.dupCheck("Voters", "Voters");
		int w = 0;
		String idString = "";
		System.out.println("duplicate id list length is " + idList4.length);
		for(w=0;w<idList4.length;w++){
				idString += idList4[w][0]+",";
			//System.out.println("while " + idList4[w][0]);
		}
		final int fw = w;
		final String fidString = idString;
			String pageTitle = "Duplicate Report Output";
			String[] lblArray = new String[] {"Click Download to examine the file containing the duplicates.","Click Correct Duplicates to view the pairs of duplicates, starting with the first pair"}; 
			String[] buttonArray = new String[] {"Download","Correct Duplicates","Home"};
			String pageTitleK = "បញ្ចេញទិន្នន័យរបាយការណ៍ឯកសារចម្លង";
			String[] lblArrayK = new String[] {"ចុច ទាញ​យក ដើម្បីពិនិត្យឯកសារដែលមានការចម្លងច្រើនដង","ចុច កែតំរូវច្បាប់ចម្លង / ច្បាប់ចម្លងត្រឹមត្រូវ ដើម្បីមើលឯកសារចម្លងគ្នាជាគូរៗ ដែលចាប់ផ្តើមពីគូរទីមួយ"};
			String[] buttonArrayK = new String[] {"ដើម្បីពិនិត្យឯកសារដែលមានការចម្លងច្រើនដង","កែតំរូវច្បាប់ចម្លង / ច្បាប់ចម្លងត្រឹមត្រូវ","ទំព័រ​ដើម"};
		
		voterApp.UITools UIT = new UITools();
		JPanel pnPanel = new JPanel();
		GridBagConstraints gbcPanel1 = new GridBagConstraints();
		GridBagLayout gbPanel = new GridBagLayout();
		GridBagLayout gbPanel0 = UIT.GridBagLayoutTool(gbcPanel1, gbPanel, pnPanel, 1,1,1,1, 2,1, 1, 1,1,1);
		JPanel pnPanel0 = UIT.PanelTool(gbPanel0, pnPanel);
		//if(!fontType.equals("Khmer")){
			final JFrame mainJFrame = UIT.FrameTool(ffontType, pnPanel0, buttonArray, pageTitle, 1024, 768, gbPanel0, gbcPanel1);
		//}else if(!fontType.equals("Khmer")){
		//	final JFrame mainJFrame = UIT.FrameTool(ffontType, pnPanel0, buttonArrayK, pageTitle, 1024, 768, gbPanel0, gbcPanel1);
		//}
		UIT.ButtonTool(ffontType, "Choose Download Directory: ", pnPanel0, 1, 1, 3, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);

       // for (int k = 0; k < buttonArray.length; k ++){
       // 	UIT.ButtonTool(buttonArray[k], pnPanel0, 0, k, 7, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
       // }
		for(int j=0;j<lblArray.length;j++){
			UIT.LabelImageTextFieldTool(ffontType, pageTitle, lblArray[j], "", 0, pnPanel0, 3, j+2, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
		}
		UIT.LabelImageTextFieldTool(ffontType, pageTitle, pageTitle, "", 0, pnPanel0, 2, 0, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
        final java.awt.Component[] compViewList = pnPanel0.getComponents();
        java.awt.Component[] componentdetailList = pnPanel0.getComponents();
		final int returnValue = 0;
        for (int i = 0; i < componentdetailList.length; i++) {
        	String sec = componentdetailList[i].getClass().getName();
	    	System.out.println("sequence in componentlist" + i + "sec " + sec); //0,2,3,4,5 are the buttons in duplicate report
		    System.out.println(componentdetailList[i]);
	    	if(sec.equals("javax.swing.JButton")){
		    		JButton mybutton = (JButton) componentdetailList[i];
		    		//you have to select each button individually and assign an action to each one
		    		if(i == 3){ //choose directory
		    			mybutton.addActionListener(new ActionListener() { 
		    				public void actionPerformed(ActionEvent e) { 
		    					//use a special folder for files to be downloaded, currently using desktop
		    					FileFunctions FF = new FileFunctions();
		    					//should put option to select folder to download to with jfilechooser
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
		    						locDir = selectedFile.getAbsolutePath();
		    						System.out.println("selected directory " + locDir);
		    						JTextField textField = (JTextField) compViewList[4];
		    						textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
		    					}
		    				} 
		    			});
		    		}if(i==0){ //correct duplicates
		    			mybutton.addActionListener(new ActionListener() { 
		    				public void actionPerformed(ActionEvent e) { 
		    					FileFunctions FF = new FileFunctions();
	    						JTextField textField = (JTextField) compViewList[4];
	    						String lDir = textField.getText();
	    						//String downloadstatus = "";
	    						//System.out.println("ldir " + lDir +"\\"+ dupFile);
	    						//if(lDir != null){
	    						//	downloadstatus = FF.download(lDir +"\\"+ dupFile, remoteFile);
		    					/*}else if(lDir == null){
		    						String usname = System.getProperty("user.name");
	    							downloadstatus = FF.download("C:\\Users\\"+usname+"\\Downloads\\" + dupFile, remoteFile);
		    					}if(downloadstatus.equals("true")){
		    						System.out.println("Duplicates within file downloaded to:  " + localFile);
		    					}*/
	    						System.out.println(fidString + " is fidstring of duplicates");
	    							if(fidString != null){
	    								//as of now, dupCheck returns a multi-dimensional array
	    								//now write the idList results from dupCheck into a file whose name includes the original filename
	    								try (
	    										//this needs to go to remote directory remoteFile and then be downloaded to the selected directory
	    										//but put localdir for testing
	    										PrintStream output = new PrintStream(new File(lDir + "\\"+dupFile));
	    										){
	    									for(int i=0;i<fw;i++){
	    										String newRow ="";
	    										for (int j=0;j<25;j++){
	    				                 	      newRow+=idList4[i][j]+" ";
	    										}
	    										output.println(newRow);
	    									}
	    									output.close();
	    								} catch (FileNotFoundException s) {
	    									s.printStackTrace();
	    								}
	    								catch( IOException ef ) {
	    									System.out.println(ef);
	    									//System.exit(0);
	    								}
	    							}
		    				}
	    				});
		    		}if(i==1){//correct duplicates
		    			mybutton.addActionListener(new ActionListener() { 
		    				public void actionPerformed(ActionEvent e) { 
		    					DetailView DV = new DetailView();
		    					DV.init("compareduplicates", fidString, ffontType);
		    				} 
		    			});
		    		}
		    		if(i==2){//home
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
}
