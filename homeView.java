package voterApp;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.Charset;

import voterApp.*;

public class homeView {

	public void homePageView(String[] buttonArray, String homeTitle) {
	//	String[] buttonArr = new String[]{"",""};
		voterApp.UITools UIT = new UITools();
		JPanel pnPanel = new JPanel();
		GridBagConstraints gbcPanel1 = new GridBagConstraints();
		GridBagLayout gbPanel = new GridBagLayout();
		GridBagLayout gbPanel0 = UIT.GridBagLayoutTool(gbcPanel1, gbPanel, pnPanel, 1,1,1,1, 2,1, 1, 1,1,1);
		JPanel pnPanel0 = UIT.PanelTool(gbPanel0, pnPanel);
		final JFrame mainJFrame = UIT.FrameTool("", pnPanel0, buttonArray, homeTitle, 1024, 768, gbPanel0, gbcPanel1);
		/*if(homeTitle == "Central App Home"){
		       for(int g=0;g<buttonArray.length;g++){
		    	   JButton button = UIT.ButtonTool(buttonArray[g], pnPanel0, 0, g, 3, 0, 0, 0, 0, 50, 50, gbPanel0, gbcPanel1);
		    	  // ButtonFunctionTool(button, buttonArray[g]);
		       }
		}*/
		java.awt.Component [] list = pnPanel0.getComponents();
		final java.awt.Component [] list2 = pnPanel0.getComponents();
		for(int i = 0; i<list.length; i++){
			System.out.println(list[i] + " i " + i);
			if(list[i].getClass().getName().equals("javax.swing.JButton")){
				if(i == 0){//search
					//if(homeTitle.equals("Polling Station Home")){
					JButton mybutton = (JButton) list[i];
					mybutton.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							String currentTitle = mainJFrame.getTitle();
							if(currentTitle.indexOf("Khmer") != -1){
								SearchView SV = new SearchView();
								SV.init("","clear", "Khmer");
							}else{
								SearchView SV = new SearchView();
								SV.init("","clear", "Times New Roman");
							}
						}
					});	
					//}
				}
				if(i == 1){//import or add voter
					if(homeTitle.equals("Central App Home")){
					JButton mybutton = (JButton) list[i];
					mybutton.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							String currentTitle = mainJFrame.getTitle();
							if(currentTitle.indexOf("Khmer") != -1){
								ImportView IV = new ImportView();
								IV.init("Voter1","", 0,0,0,0,0,"", "Khmer");
							}else{
								ImportView IV = new ImportView();
								IV.init("Voter1","", 0,0,0,0,0,"", "Times New Roman");
							}
						}
					});
					}else if(homeTitle.equals("Polling Station Home")){
						JButton mybutton = (JButton) list[i];
		    			mybutton.addActionListener(new ActionListener() {
		    				public void actionPerformed(ActionEvent e){
								String currentTitle = mainJFrame.getTitle();
								if(currentTitle.indexOf("Khmer") != -1){				
									DetailView DV = new DetailView();
									DV.init("add","","Khmer");	
								}else{
									DetailView DV = new DetailView();
									DV.init("add","","Times New Roman");		
								}
		    				}
		    			});	
					}
				}
				if(i == 2){
					if(homeTitle.equals("Central App Home")){
					JButton mybutton = (JButton) list[i];
					mybutton.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							String currentTitle = mainJFrame.getTitle();
							if(currentTitle.indexOf("Khmer") != -1){
								DuplicateReport DR = new DuplicateReport();
								DR.init("Khmer");
							}else{
								DuplicateReport DR = new DuplicateReport();
								DR.init("Times New Roman");
							}
						}
					});
					}else if(homeTitle.equals("Polling Station Home")){
						JButton mybutton = (JButton) list[i];
		    			  mybutton.addActionListener(new ActionListener() { 
		    				  public void actionPerformed(ActionEvent e) {
									String currentTitle = mainJFrame.getTitle();
									if(currentTitle.indexOf("Khmer") != -1){
										String finalVoterID = ""; //get selected voter or print up empty scan screen
										ScanFingerprint SF = new ScanFingerprint();
										SF.init(finalVoterID, "Khmer");
									}else{
										String finalVoterID = ""; //get selected voter or print up empty scan screen
										ScanFingerprint SF = new ScanFingerprint();
										SF.init(finalVoterID, "Times New Roman");
									}
		    				  }
		    			  });
					}
				}
				if(i == 3){//audit for central, khmer for polling station
					if(homeTitle.equals("Central App Home")){
					JButton mybutton = (JButton) list[i];
					mybutton.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							String currentTitle = mainJFrame.getTitle();
							if(currentTitle.indexOf("Khmer") != -1){
								AuditUserActivity AV = new AuditUserActivity();
								AV.init("Khmer");
							}else{
								AuditUserActivity AV = new AuditUserActivity();
								AV.init("Times New Roman");
							}
						}
					});
					}else if(homeTitle.equals("Polling Station Home")){
						JButton mybutton = (JButton) list[i];
						mybutton.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								mainJFrame.setTitle("Khmer Polling Station Home");	
			                    Font font = new Font ("Khmer", Font.TRUETYPE_FONT, 14);
			                    String[] buttonArray = new String[] {"","ថែមអ្នកបោះឆ្នោត","ថតចម្លងស្នាមម្រាមដៃ","ភាសា​ខ្មែរ","ភាសា​អង់គ្លេស"}; //search, add voter, scan, khmer, english
			                    for(int i = 0; i<list2.length; i++){
									JButton myb = (JButton) list2[i];
									list2[i].setFont(font);
									myb.setText(buttonArray[i]);
								}
							}
						});
					}
				}
				if(i == 4){//khmer for central, english for polling station
					 if(homeTitle.equals("Central App Home")){
						JButton mybutton = (JButton) list[i];
						mybutton.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								mainJFrame.setTitle("Khmer Central App Home");	
			                    Font font = new Font ("Khmer", Font.TRUETYPE_FONT, 14);
			                    String[] buttonArray = new String[] {"","បញ្ចូលអ្នកបោះឆ្នោត","ចម្លងរបាយការណ៍","ពិនិត្យសកម្មភាពអ្នកប្រើប្រាស់","ភាសា​ខ្មែរ","ភាសា​អង់គ្លេស"};								for(int i = 0; i<list2.length; i++){
									JButton myb = (JButton) list2[i];
									list2[i].setFont(font);
									myb.setText(buttonArray[i]);
								}
							}
						});
					}else if(homeTitle.equals("Polling Station Home")){
						JButton mybutton = (JButton) list[i];
						mybutton.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								mainJFrame.setTitle("English Polling Station Home");
			                    Font font = new Font ("Times New Roman", Font.TRUETYPE_FONT, 14);
			            		String[] buttonArray = new String[] {"", "Add Voter","Scan Fingerprint","Khmer","English"};
								for(int i = 0; i<list2.length; i++){
									JButton myb = (JButton) list2[i];
									list2[i].setFont(font);
									myb.setText(buttonArray[i]);
								}
							}
						});
					}
				}
				if(i == 5){//english for central
					if(homeTitle.equals("Central App Home")){
					JButton mybutton = (JButton) list[i];
					mybutton.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							mainJFrame.setTitle("English Central App Home");
		                    Font font = new Font ("Times New Roman", Font.TRUETYPE_FONT, 14);
		            		String[] buttonArray = new String[] {"", "Import Voter","Duplicate Report","Audit User Activity","Khmer","English"};
							for(int i = 0; i<list2.length; i++){
								JButton myb = (JButton) list2[i];
								list2[i].setFont(font);
								myb.setText(buttonArray[i]);
							}
						}
					});
					}
				}
			}
		}
		/*if(homeTitle == "Central App Home"){
			for (int i=0;i<buttonArray.length;i++){
				JButton btBut0;
				btBut0 = UIT.ButtonTool(buttonArray[i], pnPanel0, 1, i+5, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
				UIT.ButtonFunctionTool(btBut0, buttonArray[i]);
			}
		}	
		if(homeTitle == "Enter Your Voting Code"){
			for (int i=0;i<buttonArray.length;i++){
				JButton btBut0;
				btBut0 = UIT.ButtonTool(buttonArray[i], pnPanel0, 1, 4, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
				UIT.ButtonFunctionTool(btBut0, buttonArray[i]);
			}
		}*/	
		if(homeTitle == "Polling Station Home"){
			for (int i=0;i<buttonArray.length;i++){
				JButton btBut0;
				btBut0 = UIT.ButtonTool("", buttonArray[i], pnPanel0, 1, 1, i+4, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
				UIT.ButtonFunctionTool("", btBut0, buttonArray[i]);
			}
		}
		UIT.LabelImageTextFieldTool("", "", "Flag", "C:\\Users\\JJezewski\\Pictures\\Flag.PNG", 0, pnPanel0, 3, 3, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
		//UIT.LabelImageTextFieldTool(homeTitle, homeTitle, "", 0, pnPanel0, 3, 0, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
		mainJFrame.add(pnPanel0,BorderLayout.SOUTH);
	    mainJFrame.pack();
	    mainJFrame.setVisible(true);
	}
}

