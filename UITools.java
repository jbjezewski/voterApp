package voterApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.Random;

import javax.swing.*;

import java.applet.*;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UITools extends Applet {
    JFrame mainJFrame = new JFrame();
    JPanel pnPanel0 = new JPanel();
	public UITools() {
		// TODO Auto-generated constructor stub
	}
	public JFrame FrameTool(String fontType, JPanel pnPanel0, String[] buttonArray, String FrameTitle, int setSizeX, int setSizeY, GridBagLayout gbPanel0, GridBagConstraints gbcPanel1){

	    mainJFrame.setLayout(new BorderLayout());
	    mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainJFrame.setTitle(FrameTitle);
	    mainJFrame.setBackground(Color.WHITE);
	    mainJFrame.setSize(setSizeX,setSizeY);
	    mainJFrame.setResizable(false);
	    if(fontType.equals("Khmer")){
		    buttonArray = new String[] {"ស្វែងរក ","ស្វែងរកច្បាប់ចម្លង","ថែមអ្នកបោះឆ្នោត","ពិនិត្យមើលអ្នកបោះឆ្នោត","ប្រៀបធៀបអ្នកបោះឆ្នោត","ថតចម្លងស្នាមម្រាមដៃ","លុបចេញ/ ជម្រះ","ទំព័រ​ដើម"};
	    }
	    int buttonArrayLength = buttonArray.length;
	    if(!buttonArray[0].equals("")){
	    for(int i=0;i<buttonArrayLength;i++){
	       if(FrameTitle.equals("Voter Search")){
	    	   JButton button = ButtonTool(fontType, buttonArray[i], pnPanel0, 0, i, 20, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
	    	   ButtonFunctionTool(fontType, button, buttonArray[i]);
	       }
	       if(FrameTitle.equals("Central App Home")){
	    	   JButton button = ButtonTool(fontType, buttonArray[i], pnPanel0, 0, 3, i+4, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
	    	   ButtonFunctionTool(fontType, button, buttonArray[i]);
	       }
	       if(FrameTitle.equals("Polling Station Home")){
	    	   JButton button = ButtonTool(fontType, buttonArray[i], pnPanel0, 0, 3, i+4, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
	    	   ButtonFunctionTool(fontType, button, buttonArray[i]);
	       }
	       if(FrameTitle.equals("Duplicate Report Output")){
	    	   JButton button = ButtonTool(fontType, buttonArray[i], pnPanel0, 0, 2, i+4, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
	    	   ButtonFunctionTool(fontType, button, buttonArray[i]);
	       }
	       /*if(FrameTitle.equals("Import Voter Tool Step One: Upload")){
	    	   JButton button = new JButton();
	    	   if(i == 0){
	    		   button = ButtonTool(buttonArray[i], pnPanel0, 0, 2, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
	    	   }
	    	   if(i == 1){
	    		   button = ButtonTool(buttonArray[i], pnPanel0, 5, 2, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
	    	   }
	    	   ButtonFunctionTool(button, buttonArray[i]);
	       }*/
	       if(FrameTitle.equals("Import Voter Tool Step Two: Confirm")){
	    	   	   JButton button = new JButton();
	    	   if(i == 0){
	    		   button = ButtonTool(fontType, buttonArray[i], pnPanel0, 0, 0, 11, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
	    	   }  
	    	   if(i == 1){
	    		   button = ButtonTool(fontType, buttonArray[i], pnPanel0, 0, 2, 7, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
	    	   } 
	    	   if(i == 2){
	    		  button = ButtonTool(fontType, buttonArray[i], pnPanel0, 0, 2, 8, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
	    	   } 
	    	   if(i == 3){
	    		   button = ButtonTool(fontType, buttonArray[i], pnPanel0, 0, 2, 9, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
	    	   } 
	    	   if(i == 4){
	    		   button = ButtonTool(fontType, buttonArray[i], pnPanel0, 0, 2, 10, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
	    	   }
	    	   if(i == 5){
	    		   button = ButtonTool(fontType, buttonArray[i], pnPanel0, 0, 2, 11, 1, 1, 1, 1, 50, 50, gbPanel0, gbcPanel1);
	    	   } 
	    		   ButtonFunctionTool(fontType, button, buttonArray[i]);	    	   
	       }
	    }
	    }
	    return mainJFrame;
	}
	
  public GridBagConstraints GridBagConstraintsTool(GridBagLayout gbPanel0, int Inset1, int Inset2, int Inset3, int Inset4, int gridxvar, int gridyvar, int gridw, int gridh, int weightx, int weighty){
		GridBagConstraints gbcPanel1 = new GridBagConstraints();
		gbcPanel1.insets = new Insets(Inset1, Inset2, Inset3, Inset4);
		gbcPanel1.gridx = gridxvar;
		gbcPanel1.gridy = gridyvar;
		gbcPanel1.gridwidth = gridw;
		gbcPanel1.gridheight = gridh;
		gbcPanel1.fill = GridBagConstraints.CENTER;
		gbcPanel1.weightx = weightx;
		gbcPanel1.weighty = weighty;
		gbcPanel1.anchor = GridBagConstraints.NORTH;
		return gbcPanel1;
  }
  public GridBagLayout GridBagLayoutTool(GridBagConstraints gbcPanel1, GridBagLayout gbPanel0, JPanel pnPanel0, int Inset1, int Inset2, int Inset3, int Inset4, int gridxvar, int gridyvar, int gridw, int gridh, int weightx, int weighty){

		gbPanel0.setConstraints( pnPanel0, gbcPanel1 );
		return gbPanel0;
  }

  public JPanel PanelTool(GridBagLayout gbPanel0, JPanel pnPanel){
	pnPanel.setVisible(true);
	pnPanel.setLayout( gbPanel0 );
	pnPanel.setBackground(Color.WHITE);
	return pnPanel;
  }
   public JCheckBox CheckBoxTool(String pageTitle, String labelText, JPanel pnPanel0, int gridxvar, int gridyvar, int gridw, int gridh, int weightx, int weighty, int setSizeX, int setSizeY, GridBagLayout gbPanel0, GridBagConstraints gbcPanel1){
	      JLabel label;
	    	label = new JLabel(labelText, JLabel.TRAILING);
			label.setSize(setSizeX,setSizeY);
			label.setLayout(new FlowLayout());
			gbcPanel1.gridx = gridxvar;
			gbcPanel1.gridy = gridyvar;
			gbcPanel1.gridwidth = gridw;
			gbcPanel1.gridheight = gridh;
			gbcPanel1.fill = GridBagConstraints.BOTH;
			gbcPanel1.weightx = weightx;
			gbcPanel1.weighty = weighty;
			gbcPanel1.anchor = GridBagConstraints.NORTH;
			gbPanel0.setConstraints( label, gbcPanel1 );
			pnPanel0.add( label );
	   JCheckBox checkBox = new JCheckBox();
    	checkBox.setSize(setSizeX,setSizeY);
    	checkBox.setLayout(new FlowLayout());
    	checkBox.setVisible(true);
    	gbcPanel1.gridx = gridxvar+1;
    	gbcPanel1.gridy = gridyvar;
    	gbcPanel1.gridwidth = gridw;
    	gbcPanel1.gridheight = gridh;
    	gbcPanel1.fill = GridBagConstraints.BOTH;
    	gbcPanel1.weightx = weightx;
    	gbcPanel1.weighty = weighty;
    	gbcPanel1.anchor = GridBagConstraints.NORTH;
    	gbPanel0.setConstraints( checkBox, gbcPanel1 );
    	pnPanel0.add( checkBox );
    	return checkBox;
    }

  public JLabel LabelImageTextFieldTool(String fontType, String pageTitle, String labelText, String imageLocation, int textBool, JPanel pnPanel0, int gridxvar, int gridyvar, int gridw, int gridh, int weightx, int weighty, int setSizeX, int setSizeY, GridBagLayout gbPanel0, GridBagConstraints gbcPanel1){
      JLabel label;
  	label = new JLabel(labelText, JLabel.TRAILING);
  if (imageLocation != ""){
	    ImageIcon image0 = new ImageIcon(imageLocation);
	    label = new JLabel(image0);
        Font font = new Font (fontType, Font.TRUETYPE_FONT, 14);
	    label.setFont(font);
		label.setSize(setSizeX,setSizeY);
		label.setLayout(new FlowLayout());
		gbcPanel1.gridx = gridxvar;
		gbcPanel1.gridy = gridyvar;
		gbcPanel1.gridwidth = gridw;
		gbcPanel1.gridheight = gridh;
		gbcPanel1.fill = GridBagConstraints.BOTH;
		gbcPanel1.weightx = weightx;
		gbcPanel1.weighty = weighty;
		gbcPanel1.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints( label, gbcPanel1 );
		pnPanel0.add( label );
		return label;
  }else if(imageLocation == ""){
	    if(labelText != ""){
	    	label.setSize(setSizeX,setSizeY);
	    	label.setLayout(new FlowLayout());
	        Font font = new Font (fontType, Font.TRUETYPE_FONT, 14);
	    	label.setFont(font);
	    	gbcPanel1.gridx = gridxvar;
	    	gbcPanel1.gridy = gridyvar;
	    	gbcPanel1.gridwidth = gridw;
	    	gbcPanel1.gridheight = gridh;
	    	gbcPanel1.fill = GridBagConstraints.CENTER;
	    	gbcPanel1.weightx = weightx;
	    	gbcPanel1.weighty = weighty;
	    	if(labelText.indexOf("Click Download") != -1 || labelText.indexOf("Click Confirm") != -1 || labelText.indexOf("Check the Box") != -1){
	    		gbcPanel1.anchor = GridBagConstraints.CENTER;
	    	}
	    	else{
	    		gbcPanel1.anchor = GridBagConstraints.NORTH;
	    	}
	    	gbPanel0.setConstraints( label, gbcPanel1 );
	    	if(labelText.equals("ID") && pageTitle != "Add Voter"){ 
	    		label.setVisible(false);
	    	}
	    	pnPanel0.add( label );
	    }	
		if(textBool == 1){
			final JTextField fieldname = new JTextField(10);
		    if(labelText != ""){
		    	label.setLabelFor(fieldname);
		    	if(labelText.equals("ID") && pageTitle != "VoterSearch" && pageTitle != "Add Voter"){
		    		fieldname.setVisible(false);
		    	}
		    }
	        Font font = new Font (fontType, Font.TRUETYPE_FONT, 14);
		    fieldname.setFont(font);
			fieldname.setSize(setSizeX,setSizeY);
			fieldname.setLayout(new FlowLayout());
			if(labelText == "Selected Row"){
				fieldname.setVisible(false);
			}
			if(labelText == "Family Name: "){
				fieldname.requestFocusInWindow();
			}if(pageTitle != "Edit Voter" && pageTitle != "Add Voter" && pageTitle != "Voter Search"){
				fieldname.setEditable(false);
			}if(pageTitle == "Edit Voter" || pageTitle == "Add Voter" || pageTitle == "Voter Search" || labelText == "Enter Username: "){
				fieldname.setEditable(true);
			}
			if(pageTitle == "Add Voter" && labelText.equals("ID")){
			    Random rand = new Random();
			    int randomNum = rand.nextInt((100000001 - 1000000) + 1) + 1000000;
			    String newID = "" + randomNum + "";
				fieldname.setText(newID); //this should actually be done right before the insert statement
				fieldname.setEditable(false);
			}
			if(pageTitle != "Compare Voters" && pageTitle != "View Duplicate Pair"){
				gbcPanel1.gridx = gridxvar + 1;
				gbcPanel1.gridy = gridyvar;
			}
			if(pageTitle == "Compare Voters" || pageTitle == "View Duplicate Pair"){
				gbcPanel1.gridx = gridxvar;
				gbcPanel1.gridy = gridyvar - 1;
			}
			gbcPanel1.gridwidth = gridw;
			gbcPanel1.gridheight = gridh;
			gbcPanel1.fill = GridBagConstraints.BOTH;
			gbcPanel1.weightx = weightx;
			gbcPanel1.weighty = weighty;
			gbcPanel1.anchor = GridBagConstraints.NORTH;
			gbPanel0.setConstraints( fieldname, gbcPanel1 );
			pnPanel0.add( fieldname );
		    return label;
		}
    }
      return label;
  }
  public JButton ButtonTool(String fontType, String labelText, JPanel pnPanel0, int textbool, int gridxvar, int gridyvar, int gridw, int gridh, int weightx, int weighty, int setSizeX, int setSizeY, GridBagLayout gbPanel0, GridBagConstraints gbcPanel1){
    JButton btBut0;
    if(labelText.equals("Home") || labelText.equals("ទំព័រ​ដើម")){
    	btBut0 = new JButton(new ImageIcon("C:\\Users\\JJezewski\\Pictures\\Home.png"));
        Font font = new Font (fontType, Font.TRUETYPE_FONT, 14);
	    btBut0.setFont(font);
		gbcPanel1.gridx = gridxvar;
		gbcPanel1.gridy = gridyvar;
		gbcPanel1.gridwidth = gridw;
		gbcPanel1.gridheight = gridh;
		gbcPanel1.fill = GridBagConstraints.CENTER;
		gbcPanel1.weightx = weightx;
		gbcPanel1.weighty = weighty;
		gbcPanel1.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints( btBut0, gbcPanel1 );
		pnPanel0.add( btBut0 );
    }
    else if(labelText.indexOf("Search") != -1 && labelText.equals("Search") == false 
    		|| labelText.indexOf("ស្វែងរក") != -1 && labelText.equals("ស្វែងរក") == false){
    	btBut0 = new JButton(new ImageIcon("C:\\Users\\JJezewski\\Pictures\\Search.png"));
        Font font = new Font (fontType, Font.TRUETYPE_FONT, 14);
	    btBut0.setFont(font);
		gbcPanel1.gridx = gridxvar;
		gbcPanel1.gridy = gridyvar;
		gbcPanel1.gridwidth = gridw;
		gbcPanel1.gridheight = gridh;
		gbcPanel1.fill = GridBagConstraints.CENTER;
		gbcPanel1.weightx = weightx;
		gbcPanel1.weighty = weighty;
		gbcPanel1.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints( btBut0, gbcPanel1 );
		pnPanel0.add( btBut0 );
    }
    else if(labelText.indexOf("arrow") != -1){
    	String buttonimage = "";
    	if(labelText.equals("arrowoneleft")){
    		buttonimage = "C:\\Users\\JJezewski\\Pictures\\OneLeft.png";
    	}if(labelText.equals("arrowoneright")){
    		buttonimage = "C:\\Users\\JJezewski\\Pictures\\OneRight.png";
    	}if(labelText.equals("arrowallleft")){
    		buttonimage = "C:\\Users\\JJezewski\\Pictures\\AllLeft.png";
    	}if(labelText.equals("arrowallright")){
    		buttonimage = "C:\\Users\\JJezewski\\Pictures\\AllRight.png";
    	}
    	btBut0 = new JButton(new ImageIcon(buttonimage));
		gbcPanel1.gridx = gridxvar;
		gbcPanel1.gridy = gridyvar;
		gbcPanel1.gridwidth = gridw;
		gbcPanel1.gridheight = gridh;
		gbcPanel1.fill = GridBagConstraints.CENTER;
		gbcPanel1.weightx = weightx;
		gbcPanel1.weighty = weighty;
		gbcPanel1.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints( btBut0, gbcPanel1 );
		pnPanel0.add( btBut0 );
    }
    else{btBut0 = new JButton( labelText  );
    Font font = new Font (fontType, Font.TRUETYPE_FONT, 14);
    btBut0.setFont(font);
		gbcPanel1.gridx = gridxvar;
		gbcPanel1.gridy = gridyvar;
		gbcPanel1.gridwidth = gridw;
		gbcPanel1.gridheight = gridh;
		gbcPanel1.fill = GridBagConstraints.CENTER;
		gbcPanel1.weightx = weightx;
		gbcPanel1.weighty = weighty;
		gbcPanel1.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints( btBut0, gbcPanel1 );
		pnPanel0.add( btBut0 );
		if(textbool != 0){
			final JTextField fieldname = new JTextField(10);
			fieldname.setSize(setSizeX,setSizeY);
		    fieldname.setFont(font);
			fieldname.setLayout(new FlowLayout());
			fieldname.setEditable(false);
			gbcPanel1.gridx = gridxvar+1;
			gbcPanel1.gridy = gridyvar;
			gbcPanel1.gridwidth = gridw;
			gbcPanel1.gridheight = gridh;
			gbcPanel1.fill = GridBagConstraints.BOTH;
			gbcPanel1.weightx = weightx;
			gbcPanel1.weighty = weighty;
			gbcPanel1.anchor = GridBagConstraints.NORTH;
			gbPanel0.setConstraints( fieldname, gbcPanel1 );
			pnPanel0.add( fieldname );
		}
    }
	return btBut0;
  }
  
  public void ButtonFunctionTool(String fontType, JButton button, String actionName){
	  final String ffontType = fontType;
	  if(actionName.equals("Voter Search")){
		  button.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				  SearchView SV = new SearchView();
				  SV.init("", "clear", ffontType); //no query
			  }
		  });
	  }
	  if(actionName.equals("Home")){
		  button.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				  homeView HV = new homeView();
				  String[] buttonArray = new String[] {"Voter Search", "Import Voter","Duplicate Report","Audit User Activity"};
				  HV.homePageView(buttonArray, "Central App Home");
			  }
		  });
	  }
  }
  
}
