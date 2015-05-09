package voterApp;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import com.jcraft.jsch.*;

import javax.swing.JTextField;

import org.apache.commons.net.ftp.FTPClient;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.*;
 
/**
 * A basic file upload and download tool
 *
 * @author www.codejava.net
 *
 */
public class FileFunctions  {
 
    // FTP server information
    private String host;
    private int port;
    private String username;
    private String password;
 
    private FTPClient ftpClient = new FTPClient();
    private int replyCode;
	String myuserName = System.getProperty("user.name");
	String knownHostsFilename = "C:\\Users\\" + myuserName + "\\Downloads\\Admin.pem";
 
    private InputStream inputStream;
    
    public String createFileWithoutDuplicates(String originalfilename, String duptype, String[][] idListvar){
    	//compare each random id to all existing ids to make sure it doesn't already exist in db
    	//maybe store list of all voter ids in file on server for ease of access to idlist
    	//check if first or second column is id column (first column with have import type like add or verify)
    	String finalname = "";
    	int dotindex = originalfilename.indexOf(".");
    	String newfilename = originalfilename.substring(0,dotindex);
    	String fileext = originalfilename.substring(dotindex,originalfilename.length());
    	String idfilename = newfilename + "_withIDs"+fileext;
    	String newfilewext = newfilename + "_without"+duptype+"duplicates"+ fileext;

	    try{

	    	FileInputStream fis = new FileInputStream(idfilename);
	    	BufferedReader br = new BufferedReader(new InputStreamReader(fis));
	    	finalname = newfilewext;
	    	//FileOutputStream fos = new FileOutputStream(newfilename + "_withIDs"+ fileext);
	    	//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	    	String readLine = "";
	    	File myfile = new File(idfilename);
	    	int numrows = countRows(idfilename);
	    	String[] newfilerows = new String[numrows];
	    	String[] dataarr = new String[numrows];
	    	for(int c = 0;c<numrows;c++){
	            String lineText = "";
	            lineText = br.readLine();
	    		lineText = lineText.substring(0,lineText.length()-1);
	    		newfilerows[c] = lineText;
	    	}

	    	//BufferedWriter output = new BufferedWriter(new FileWriter(newfilewext));
	    	boolean isundesired = false;
	    	try (
	                PrintStream poutput = new PrintStream(new File(newfilewext));
	            ){
	    		for(int count = 0; count < newfilerows.length; count++) {
	    			//put condition to check if this row's id is in the array of undesired ids
	    			for (int h = 0; h < idListvar.length; h++){
	    				String thislistid = idListvar[h][0];
	    				System.out.println("thislistid " + thislistid);
	    				if(newfilerows[count].indexOf(thislistid) != -1){
	    					isundesired = true;
	    				}
	    			}
	    			if(isundesired != true){
	    				System.out.println("we're writing this row to the new file " + newfilerows[count]);
	    				//	output.write(newfilerows[count]);
	    					poutput.println(newfilerows[count]);
	    					//if(count != newfilerows.length-1) {// so extra new line is not inserted at end of file
	    						//output.newLine();
	    					//}
	    			}
	    		}
	    		poutput.close();
	    	}
	    }catch (Exception ie){
	    	System.out.println(ie.getMessage());
	    }
	    //now sftp newfile to remote directory
	    String loFile = newfilewext;
	    String repstring = "C:\\Users\\"+System.getProperty("user.name")+"\\Downloads\\";
	    String newstring = "/cygdrive/c/Users/Administrator/Desktop/";
	    String rstring = "C:\\Users\\Administrator\\Desktop\\";
	    String reFile = loFile.replace(repstring, newstring);
	    System.out.println("uploading lofile " + loFile + " to reFile " + reFile);
	    //upload(loFile, reFile);
	    FileFunctions FF = new FileFunctions();
		String uploadstatus = FF.upload(loFile, reFile);
		if(uploadstatus.equals("true")){
			System.out.println("Successful upload of file: " + loFile + " to remote file " + reFile);
		}
	    String rfFile = loFile.replace(repstring, rstring);

	    return rfFile;
    }
    public String addIDsToFile(String filename){
    	//compare each random id to all existing ids to make sure it doesn't already exist in db
    	//maybe store list of all voter ids in file on server for ease of access to idlist
    	//check if first or second column is id column (first column with have import type like add or verify)
    	String finalname = "";
	    try{
	    	FileInputStream fis = new FileInputStream(filename);
	    	BufferedReader br = new BufferedReader(new InputStreamReader(fis));
	    	int dotindex = filename.indexOf(".");
	    	String newfilename = filename.substring(0,dotindex);
	    	String fileext = filename.substring(dotindex,filename.length());
	    	String newfilewext = newfilename + "_withIDs"+ fileext;
	    	finalname = newfilewext;
	    	//FileOutputStream fos = new FileOutputStream(newfilename + "_withIDs"+ fileext);
	    	//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	    	String readLine = "";
	    	File myfile = new File(filename);
	    	int numrows = countRows(filename);
	    	String[] newfilerows = new String[numrows];
	    	for(int c = 0;c<numrows;c++){
	    	    java.util.Random rand = new java.util.Random();
	    	    int randomNum = rand.nextInt((100000001 - 1000000) + 1) + 1000000;
	    	    String newID = "" + randomNum + "";
	            String lineText = "";
	            lineText = br.readLine();
	    		/*lineText = lineText.replace("ADD ", "ADD " + newID + " ");
	    		lineText = lineText.replace("UPDATE ", "UPDATE " + newID + " ");
	    		lineText = lineText.replace("DELETE ", "DELETE " + newID + " ");
	    		lineText = lineText.replace("VERIFY ", "VERIFY " + newID + " ");*/ //we want the adds to be removed
	    		lineText = lineText.replace("ADD,", newID + ",");
	    		lineText = lineText.replace("UPDATE,",newID + ",");
	    		lineText = lineText.replace("DELETE,", newID + ",");
	    		lineText = lineText.replace("VERIFY,", newID + ",");
	    		lineText = lineText.substring(0,lineText.length()-1);
	    		newfilerows[c] = lineText;
	    	}
	    	BufferedWriter output = new BufferedWriter(new FileWriter(newfilewext));
	    	try {
	    		for(int count = 0; count < newfilerows.length; count++) {
	    			output.write(newfilerows[count]);
	    			if(count != newfilerows.length-1) {// so extra new line is not inserted at end of file
	    				output.newLine();
	    			}
	    		}
	    	}
	    	finally {
	    		output.close();
	    	}
	    }catch (Exception ie){
	    	System.out.println(ie.getMessage());
	    }
	    return finalname;
    }
    public int countStrings(String searchstring, String filename){
		  int count = 0;
    	  try{
  	    	  FileInputStream fileinputstream = new FileInputStream(filename);
    		  BufferedReader in = new BufferedReader(new InputStreamReader(fileinputstream));
    		  String readLine = "";
    		  while((readLine = in.readLine()) != null) {
        		  //System.out.println("readline is " + readLine);

    			  String[] words = readLine.split(",");
        		//  System.out.println("words");
        		 // System.out.println(words);

    			  for(int s=0;s<words.length;s++){
    				  if(words[s].equals(searchstring)) count++;
    			  }
    		  }
    	    fileinputstream.close();
    	  }catch (Exception e){//Catch exception if any
    	    System.err.println("Error: " + e.getMessage());
    	  }
    	  return count;
    	}
    public int countRows(String filename){
		  int count = 0;
    	  try{
  	    	  FileInputStream fileinputstream = new FileInputStream(filename);
    		  BufferedReader in = new BufferedReader(new InputStreamReader(fileinputstream));
    		  String readLine = "";
    		  while((readLine = in.readLine()) != null) {
    				  count++;
    		  }
    	    fileinputstream.close();
    	  }catch (Exception e){//Catch exception if any
    	    System.err.println("Error: " + e.getMessage());
    	  }
    	  return count;
    	}
    public static class MyUserInfo implements UserInfo, UIKeyboardInteractive {

        @Override
        public String getPassphrase() {
            return null;
        }
        @Override
        public String getPassword() {
            return null;
        }
        @Override
        public boolean promptPassphrase(String arg0) {
            return false;
        }
        @Override
        public boolean promptPassword(String arg0) {
            return false;
        }
        @Override
        public boolean promptYesNo(String arg0) {
            return false;
        }
        @Override
        public void showMessage(String arg0) {
        }
        @Override
        public String[] promptKeyboardInteractive(String arg0, String arg1,
                String arg2, String[] arg3, boolean[] arg4) {
            return null;
        }
    }
    public String upload(String localFile, String remoteFile){
        JSch jsch = new JSch();
        Session session = null;
        String response = "";
        try{
		session = jsch.getSession( "Administrator", "54.210.170.133" , 22); 
		session.setPassword("xxN@k(o%aG");
		SFTP.MyUserInfo ui = new SFTP.MyUserInfo();
		ui.passwd = "xxN@k(o%aG";
		session.setConfig("PreferredAuthentications", "password,gssapi-with-mic,publickey,keyboard-interactive");
		session.setTimeout(10*60*1000);
		session.setUserInfo(ui);
		session.connect();
		System.out.println(session.isConnected());
		Channel channel = session.openChannel( "sftp" );
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		try{
		System.out.println("current directory is " + sftpChannel.pwd());
        }catch(SftpException see){
        	System.out.println(see.getMessage());
        }try{
			jsch.setKnownHosts( knownHostsFilename );
		}catch (JSchException f){
			System.out.println(f.toString());
		}
		String absoluteFilePathPrivatekey = "";
		File tmpFileObject = new File(knownHostsFilename);
		if (tmpFileObject.exists() && tmpFileObject.isFile())
		{
			absoluteFilePathPrivatekey = tmpFileObject.getAbsolutePath();
		}
		try{ jsch.addIdentity(absoluteFilePathPrivatekey, ""); //second param is passphrase for key
		}catch (JSchException ff){
			System.out.println(ff.toString());
		}    	
		        	//decide if you need to change the filename once it's uploaded
		        	//or changes permissions or directory from there
		        	try{
		        	sftpChannel.put(localFile, remoteFile);//upload to rfile
		        	//OutputStream on = sftpChannel.put(localFile);
		        	}catch (SftpException h){		        	
		        		System.out.println(h.toString());
		        		response = "false";
		        	}	        
		        	sftpChannel.exit();
		        	session.disconnect();
	        		response = "true";
        		}catch (JSchException g){
	        		System.out.println(g.toString());
	        	}	        
    	return response;
    }
    public String download(String localFile, String remoteFile){
        JSch jsch = new JSch();
        Session session = null;
        String response = "";
        try{
		session = jsch.getSession( "Administrator", "54.210.170.133" , 22); 
		session.setPassword("xxN@k(o%aG");
		SFTP.MyUserInfo ui = new SFTP.MyUserInfo();
		ui.passwd = "xxN@k(o%aG";
		session.setConfig("PreferredAuthentications", "password,gssapi-with-mic,publickey,keyboard-interactive");
		session.setTimeout(10*60*1000);
		session.setUserInfo(ui);
		session.connect();
		System.out.println(session.isConnected());
		Channel channel = session.openChannel( "sftp" );
		channel.connect();
		ChannelSftp sftpChannel = (ChannelSftp) channel;
		try{
		System.out.println("current directory is " + sftpChannel.pwd());
        }catch(SftpException see){
        	System.out.println(see.getMessage());
        }try{
			jsch.setKnownHosts( knownHostsFilename );
		}catch (JSchException f){
			System.out.println(f.toString());
		}
		String absoluteFilePathPrivatekey = "";
		File tmpFileObject = new File(knownHostsFilename);
		if (tmpFileObject.exists() && tmpFileObject.isFile())
		{
			absoluteFilePathPrivatekey = tmpFileObject.getAbsolutePath();
		}
		try{ jsch.addIdentity(absoluteFilePathPrivatekey, ""); //second param is passphrase for key
		}catch (JSchException ff){
			System.out.println(ff.toString());
		}          	
		        	//decide if you need to change filename once it's uploaded or changes permissions or dir
		        	try{
		        		sftpChannel.get(remoteFile, localFile);//upload to rfile
		        		//InputStream in = sftpChannel.get(remoteFile);
		        	}catch (SftpException h){
		        		System.out.println(h.toString());
		        		response = "false";
		        	}        
		        	sftpChannel.exit();
		        	session.disconnect();
	        		response = "true";
        		}catch (JSchException g){
        			System.out.println(g.toString());
        		}	        
    	return response;
    }
    public int countRowsFromFile(String filename){
    	//read rows from file and return as a two-dimensional string array
    	int i = 0;
    	return i;
    }
    public String[][] getRowsFromFile(String filename){
    	//read rows from file and return as a two-dimensional string array
    	String[][] rowArray = new String[9][9];
    	return rowArray;
    }
    
}

