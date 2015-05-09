package voterApp;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.*;
import java.util.*;
import java.util.Date;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.DefaultCellEditor;
import javax.swing.table.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.jcraft.jsch.*;

import voterApp.SearchResults;

public class DBFunctions {

    // jdbc Connection
    private static Connection con = null;
    private static Statement stmt = null;
    private static String url = "jdbc:sqlserver://";
   // private static String serverName = "electiondemo.cwxiubkqmkco.us-east-1.rds.amazonaws.com";
   //private static String serverName = "WIN-0P3NNRL0C6J";
    private static String serverName = "54.210.170.133";
   // private static String serverName = "ec2-54-210-170-133.compute-1.amazonaws.com";
    private static String userName = "Morinteresting";
    private static String password = "PinkPotato800";
  //  private static String getConnectionUrl = url+serverName+":1433;databaseName=Morinteresting;selectMethod=cursor;";
    private static String getConnectionUrl = url+serverName+":1433;databaseName=Morinteresting;"; 

    //private static String getConnectionUrl = url+serverName+":1433;integratedSecurity=true;";
    public void makeConnection()
    {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            con = DriverManager.getConnection(getConnectionUrl,userName,password);
            if(con!=null) System.out.println("Connection Successful!");
       }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Error Trace in getConnection() : " + ex.getMessage());
      }    	 
    }
    public void bulkInsert(String filename){
    	String insSt = "";
        try
        {
        	if(con == null){
        		makeConnection();
        		stmt = con.createStatement();
        		insSt = "BULK INSERT Morinteresting.dbo.Voters FROM '"+filename+"'";
        		insSt += " WITH (  ROWTERMINATOR = '\n', FIELDTERMINATOR = ',' )";
        		//field order that file rows need to be in: "VoterID,last_name,birthdate,cid,reg_address1,reg_address2,reg_address3,reg_village,reg_commune,reg_district,reg_province,reg_city,reg_zip,first_name,regnum,nid,mail_address1,mail_address2,mail_address3,mail_village,mail_commune,mail_district,mail_province,mail_city,mail_zip"
        		stmt.execute(insSt);
        		System.out.println(insSt);
        		stmt.close();
        	}
        	else if(con != null){
        		stmt = con.createStatement();
        		insSt = "BULK INSERT Morinteresting.dbo.Voters FROM '"+filename+"'";
        		insSt += " WITH (  ROWTERMINATOR = '\n', FIELDTERMINATOR = ',' )";
        		//field order that file rows need to be in: "VoterID,last_name,birthdate,cid,reg_address1,reg_address2,reg_address3,reg_village,reg_commune,reg_district,reg_province,reg_city,reg_zip,first_name,regnum,nid,mail_address1,mail_address2,mail_address3,mail_village,mail_commune,mail_district,mail_province,mail_city,mail_zip"
        		stmt.execute(insSt);
        		System.out.println(insSt);
        		stmt.close();
        	}
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
            System.out.println("Error Trace in Bulk Insert to Voter : " + sqlExcept.getMessage());

        }
    }
    public String bulkInsertDuplicatesTemp(String filename, String numtable){
    	String insSt = "";
    	String crSt = "";

    	//String tablename = "Morinteresting.dbo.Voters_Temp_"+newID;
    	String tablename = "Voters_Temp_"+numtable;
        crSt += "CREATE TABLE " + tablename + " (";
        crSt += "VoterID int NOT NULL,	date_entered datetime NULL,	date_modified datetime NULL,	";
        crSt += "created_by int NULL,	modified_user_id int NULL,	assigned_user_id int NULL,	";
        crSt += "voter_description varchar(255) NULL,	salutation varchar(20) NULL,	first_name varchar(60) NULL,	";
        crSt += "middle_name varchar(60) NULL,	last_name varchar(60) NULL,	name_suffix varchar(20) NULL,	";
        crSt += "birthdate date NULL,	gender varchar(6) NULL,	regnum int NULL,	reg_hnum int NULL,	";
        crSt += "reg_hnumsfx varchar(20) NULL,	regdate date NULL,	inactive tinyint NULL,	";
        crSt += "reg_address1 varchar(60) NULL,	reg_address2 varchar(60) NULL,	reg_address3 varchar(60) NULL,	";
        crSt += "reg_apt varchar(60) NULL,	reg_city varchar(60) NULL,	reg_state varchar(60) NULL,	";
        crSt += "reg_zip varchar(60) NULL,	mail_address1 varchar(60) NULL,	mail_address2 varchar(60) NULL,	";
        crSt += "mail_address3 varchar(60) NULL,	mail_apt varchar(60) NULL,	mail_city varchar(60) NULL,	";
        crSt += "mail_state varchar(60) NULL,	mail_zip varchar(60) NULL,	mailing_address_flag tinyint NULL,	";
        crSt += "voting_location varchar(60) NULL,	email1 varchar(60) NULL,	phone1 varchar(20) NULL,	";
        crSt += "phone2 varchar(20) NULL,	do_not_mail tinyint NULL,	do_not_email tinyint NULL,	";
        crSt += "do_not_call tinyint NULL,	title varchar(60) NULL,	occupation varchar(60) NULL,	";
        crSt += "department varchar(60) NULL,	primary_party_affiliation varchar(60) NULL,	potential_duplicate_voter tinyint NULL,	";
        crSt += "approved_to_vote tinyint NULL,	approved_to_register tinyint NULL,	reg_province varchar(60) NULL,	";
        crSt += "reg_commune varchar(60) NULL,	reg_village varchar(60) NULL,	reg_district varchar(60) NULL,	";
        crSt += "mail_province varchar(60) NULL,	mail_commune varchar(60) NULL,	mail_village varchar(60) NULL,	";
        crSt += "mail_district varchar(60) NULL,	cid int NULL,	nid int NULL,PRIMARY KEY (VoterID) ) ";

        //field order that file rows need to be in: "VoterID,last_name,birthdate,cid,reg_address1,reg_address2,reg_address3,reg_village,reg_commune,reg_district,reg_province,reg_city,reg_zip,first_name,regnum,nid,mail_address1,mail_address2,mail_address3,mail_village,mail_commune,mail_district,mail_province,mail_city,mail_zip"
        String checktableexists = "SELECT * FROM Morinteresting.INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = '"+tablename+"'";
    	//check if table already exists******************************************
        boolean alreadyexists = false;
        try
        {
            if(con == null){
          		makeConnection();
                stmt = con.createStatement();
                ResultSet resultSet = stmt.executeQuery(checktableexists);
            	while (resultSet.next()){
                    System.out.println("Table exists? ");
                  //  int thisrow = resultSet.getRow();
                    System.out.println("first column of first result is " + resultSet.getString(3));
                    String existingtname = resultSet.getString(3);
                    if(existingtname != null){
                    	alreadyexists = true;
                    }
            	}    
                stmt.close();
            }else if(con != null){
            	stmt = con.createStatement();
            	ResultSet resultSet = stmt.executeQuery(checktableexists);
            	while (resultSet.next()){
                    System.out.println("Table exists? ");
                  //  int thisrow = resultSet.getRow();
                    System.out.println("first column of first result is " + resultSet.getString(3));
                    String existingtname = resultSet.getString(3);
                    if(existingtname != null){
                    	alreadyexists = true;
                    }
            	}    
            	stmt.close();
            }
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
            System.out.println("Error Trace in Check Temp Voter Table Exists : " + sqlExcept.getMessage());
        }       
        System.out.println(alreadyexists + " tells us whether the table already exists, meaning they clicked a download button already");
        if(alreadyexists == false){
        try
        {
            if(con == null){
          		makeConnection();
                stmt = con.createStatement();
                stmt.executeUpdate(crSt);
                System.out.println("Created table in given database...");
                System.out.println(crSt);
                stmt.close();
            }else if(con != null){
            stmt = con.createStatement();
            stmt.executeUpdate(crSt);
            System.out.println("Created table in given database...");
            System.out.println(crSt);
            stmt.close();
            }
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
            System.out.println("Error Trace in Create Temp Voter Table : " + sqlExcept.getMessage());
        }
        
        //now we insert uploaded file data into temp table
        try
        {
            stmt = con.createStatement();
            insSt = "BULK INSERT Morinteresting.dbo."+tablename+" FROM '"+filename+"'";
            insSt += "WITH (  ROWTERMINATOR = '\n', FIELDTERMINATOR = ',' )";
            //field order that file rows need to be in: "VoterID,last_name,birthdate,cid,reg_address1,reg_address2,reg_address3,reg_village,reg_commune,reg_district,reg_province,reg_city,reg_zip,first_name,regnum,nid,mail_address1,mail_address2,mail_address3,mail_village,mail_commune,mail_district,mail_province,mail_city,mail_zip"
            stmt.execute(insSt);
            System.out.println(insSt);
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
            System.out.println("Error Trace in Bulk Insert to Temp Voter : " + sqlExcept.getMessage());

        }
        }
        return tablename;
    }
    public String[][] dupCheck(String table1, String table2){
    		String buildqueryString = "";    
    		buildqueryString = "SELECT * FROM Morinteresting.dbo."+table1+" v1 ";
    		buildqueryString += "LEFT JOIN Morinteresting.dbo."+table2+" v2 ON v1.VoterID = v2.VoterID  ";
    		buildqueryString += "WHERE v1.first_name = v2.first_name AND v1.last_name = v2.last_name AND v1.birthdate = v2.birthdate  AND v1.gender = v2.gender ";
    		//get substring from start to first space, "18"
    		buildqueryString += "AND SUBSTRING(LOWER(v1.reg_address1),0,CHARINDEX(' ', v1.reg_address1, 0)) = SUBSTRING(LOWER(v2.reg_address1),0,CHARINDEX(' ', v2.reg_address1, 0)) "; 
    		//get substring between first space and last space, "Sunset Corner H Street"
    		buildqueryString += "AND SUBSTRING(LOWER(v1.reg_address1),CHARINDEX(' ', v1.reg_address1, 0), LEN(v1.reg_address1) ";
    		buildqueryString += " - CHARINDEX(' ', REVERSE(v1.reg_address1), 0) - CHARINDEX(' ', v1.reg_address1, 0)) =  ";
    		buildqueryString += "SUBSTRING(LOWER(v2.reg_address1), CHARINDEX(' ', v2.reg_address1, 0), LEN(v2.reg_address1) ";
    		buildqueryString += " - CHARINDEX(' ', REVERSE(v2.reg_address1), 0) - CHARINDEX(' ', v2.reg_address1, 0)) ";
    		buildqueryString += "AND v1.reg_address1 != '' AND v2.reg_address1 != '' AND v1.reg_address1 IS NOT NULL AND v2.reg_address1 IS NOT NULL AND ";
    		buildqueryString += "v1.birthdate != '' AND v2.birthdate != '' AND v1.birthdate IS NOT NULL AND v2.birthdate IS NOT NULL AND ";
    		buildqueryString += "v1.first_name != '' AND v2.first_name != '' AND v1.first_name IS NOT NULL AND v2.first_name IS NOT NULL AND ";
    		buildqueryString += "v1.last_name != '' AND v2.last_name != '' AND v1.last_name IS NOT NULL AND v2.last_name IS NOT NULL AND ";
    		buildqueryString += "v1.gender != '' AND v2.gender != '' AND v1.gender IS NOT NULL AND v2.gender IS NOT NULL";
        String[][] dupdata;
        System.out.println("dupcheck is comparing " + table1 + " and " + table2);
        
        ////////////////////////////getting count of resultset, may want to combine this with second query execution
        int size = 0;
        try
        {  
          if(con == null){
        		makeConnection();
                stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet resultSet = stmt.executeQuery(buildqueryString);
                if (resultSet != null) 
                {
                	resultSet.beforeFirst();
                	resultSet.last();
                	size = resultSet.getRow();
                }
                resultSet.close();
                stmt.close();
          }else if(con != null){
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = stmt.executeQuery(buildqueryString);
            if (resultSet != null) 
            {
            	resultSet.beforeFirst();
            	resultSet.last();
            	size = resultSet.getRow();
            }
            resultSet.close();
            stmt.close();
          }
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        System.out.println("dupcheck resultset size before modification is " + size);
       	if(size == 0 || size == -1){size = 100;}

        
        ////////////create a multidimensional array of the appropriate size for this result set count
        dupdata = new String[size][58];

        ////////////now do duplicate check between tables specified
        try 
        {
            stmt = con.createStatement();
            //field order that file rows need to be in: "VoterID,last_name,birthdate,cid,reg_address1,reg_address2,reg_address3,reg_village,reg_commune,reg_district,reg_province,reg_city,reg_zip,first_name,regnum,nid,mail_address1,mail_address2,mail_address3,mail_village,mail_commune,mail_district,mail_province,mail_city,mail_zip"
            ResultSet resultSet = stmt.executeQuery(buildqueryString);
           // System.out.println("query string is " + buildqueryString);
          //  System.out.println("dupcheck resultset size is " + size);
           	int row = 0;
        	while (resultSet.next()){
        		if(row < size){
        			for(int j=1;j<59;j++){
            	      // System.out.println("dupdata from dupcheck for " + j);
            	      //  System.out.println(resultSet.getString(j));
        				dupdata[row][j-1] = resultSet.getString(j);
            	      // System.out.println(dupdata[row]);

        			}

        			row++;
        		}
        	}                 
            resultSet.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
            System.out.println("Error Trace in dupCheck for report and importer : " + sqlExcept.getMessage());

        }
        ///consider if you can just return a list of ids instead of the whole row

        return dupdata;
    }
    public void dropTempTable(String tablename)
    {    	
        try
        {
        	
            stmt = con.createStatement();
            String dropTemp = "ALTER DATABASE Morinteresting DROP TABLE Morinteresting.dbo."+tablename;
            stmt.execute(dropTemp);
            System.out.println(dropTemp);
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
            System.out.println("Error Trace in drop temp table : " + sqlExcept.getMessage());

        }
    }   
    public void bulkTransfer(String table1name)
    {   
    	//select voters from temp table
    	//dont need this function, just do bulk insert from created files
    	//load into Voters table
    	//"INSERT INTO Morinteresting.dbo.Voters "+columnList;
    	//"SELECT "+columnList+"FROM tempTableName";
       /* try
        {
        	
            stmt = con.createStatement();
        //    String insertStmt = "INSERT INTO Morinteresting.dbo.Voters (VoterID,last_name,birthdate,cid,reg_address1,reg_address2,reg_address3,reg_village,reg_commune,reg_district,reg_province,reg_city,reg_zip,first_name,regnum,nid,mail_address1,mail_address2,mail_address3,mail_village,mail_commune,mail_district,mail_province,mail_city,mail_zip) VALUES (" + valuearray + ") ";
            stmt.execute(insertStmt);
            System.out.println(insertStmt);
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
            System.out.println("Error Trace in bulk Transfer : " + sqlExcept.getMessage());

        }*/
    }
    public String selectTTVoterExceptDuplicates(String tablename, String[] fileList, String[] dbList)
    {    	
		//selectTTVoter function will get all the data from the temp table,
		//which means all the data from the original file
		//except for the records that are possible duplicates from
		//the list of duplicate ids in dblist and filelist
		//and written the voter data to be imported into a file called outputfile
    	
    	String notinsertstring = "";
    	String myst = "";
    	PrintWriter writer = null;
    	String columnList = "VoterID, first_name";
    	String[] colArray = columnList.split(",");
        String username = System.getProperty("user.name");
        String outputfile = "C:\\Users\\" + username + "\\Desktop\\temp_non_duplicates_voter.txt";
        //plus datetimestamp or other data

        try
        {
            stmt = con.createStatement();
            
            //we want to create string with all the duplicate ids we don't want in our insert statement
            for(int g=0;g<fileList.length;g++){
            	notinsertstring += fileList[g]+"','";
            }
            for(int r=0;r<dbList.length;r++){
            	notinsertstring += dbList[r]+"','";
            }
            notinsertstring = notinsertstring.substring(0,(notinsertstring.length() - 2));
            
            myst = "SELECT VoterID FROM Morinteresting.dbo."+tablename+" WHERE VoterID NOT IN ('";
            myst += notinsertstring+ ")";
           // writer = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(outputfile)), "UTF-8"));
            //if (writer != null) try { writer.close(); } catch (FileNotFoundException logOrIgnore) {}

            ResultSet resultSet = stmt.executeQuery(myst);
            while (resultSet.next()){
            	for(int g=0;g<colArray.length;g++){
            		if(g < (colArray.length - 1)){
            			writer.append(resultSet.getString(colArray[g])).append(",");
            		}else if (g == (colArray.length - 1)){
            			writer.append(resultSet.getString(colArray[g])).println();
            		}
            	}
		   }
            writer.close();
            
            resultSet.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
            System.out.println("Error Trace in select temp table voter except duplicates() : " + sqlExcept.getMessage());
        }
        return outputfile;
    }
    public void insertVoter(String id, String valuearray)
    {    	
        try
        {
        	
            stmt = con.createStatement();
            String insertStmt = "INSERT INTO Morinteresting.dbo.Voters (VoterID,last_name,birthdate,cid,reg_address1,reg_address2,reg_address3,reg_village,reg_commune,reg_district,reg_province,reg_city,reg_zip,first_name,regnum,nid,mail_address1,mail_address2,mail_address3,mail_village,mail_commune,mail_district,mail_province,mail_city,mail_zip) VALUES (" + valuearray + ") ";
            stmt.execute(insertStmt);
            System.out.println(insertStmt);
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
            System.out.println("Error Trace in insertVoter() : " + sqlExcept.getMessage());

        }
    }
    class BooleanTableModel extends AbstractTableModel { 
  	  String[] columnNames;
  	  Object[][] data;
    @Override
    public int getRowCount()
    {
        return data.length;
    }   	        
    @Override
    public int getColumnCount()            
    {
        return columnNames.length;
    } 
    @Override
    public Object getValueAt(int row, int column)
    {        
        return data[row][column];
    }
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    @Override
    //public Class getColumnClass(int c) {
   //     return getValueAt(0, c).getClass();
  	 // return data[0][c].getClass();
    //}  
    public Class<?> getColumnClass(int columnIndex) {
        return data[0][columnIndex].getClass();
    }
    @Override
    public boolean isCellEditable(int row, int column)
    {
       if (column == 0)
       	{
    	   	return true;
   		}
       else
   		{
  	   		return false;
   		}
    } 
  }
    public Object[][] getSearchResults(String queryString) 
    {
    	System.out.println("getsearchresults queryString" + queryString);
        String[] columnNames = {"ID", "Given Name", "Family Name", "Regnum", "Registered Address 1", "Registered Address 2", "Phone", "NID"};
       // Object[][] dummydata;
        int size = 0;
        try
        {  
          if(con == null){
        		makeConnection();
          }else if(con != null){
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String columnList = "VoterID,first_name,last_name,regnum,reg_address1,reg_address2,phone1,nid";
            if(queryString.indexOf("CHARINDEX") != -1){
            	//do duplicate check
                ResultSet resultSet = stmt.executeQuery(queryString);
                if (resultSet != null) 
    	        {
    	        	resultSet.beforeFirst();
    	        	resultSet.last();
    	        	size = resultSet.getRow();
    	        }
                resultSet.close();
                stmt.close();
            }else{
            	ResultSet resultSet = stmt.executeQuery("SELECT "+columnList+" FROM Morinteresting.dbo.Voters " + queryString);
            	if (resultSet != null) 
            	{
            		resultSet.beforeFirst();
            		resultSet.last();
            		size = resultSet.getRow();
            	}
            	resultSet.close();
            	stmt.close();
        	}
          }
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        if(size == 0 || size > 10){
        	//add no results found message.
        	size = 10;
        }
        //dummydata = new String [size+1][8];
      //  dummydata = new Object[size+1][9];
        Object[][] data = {
                {Boolean.FALSE, "","","","","","","",""},
                {Boolean.FALSE, "","","","","","","",""},
                {Boolean.FALSE, "","","","","","","",""},
                {Boolean.FALSE, "","","","","","","",""},
                {Boolean.FALSE, "","","","","","","",""},
                {Boolean.FALSE, "","","","","","","",""},
                {Boolean.FALSE, "","","","","","","",""},
                {Boolean.FALSE, "","","","","","","",""},
                {Boolean.FALSE, "","","","","","","",""},
                {Boolean.FALSE, "","","","","","","",""}
        };
        System.out.println("size = " + size);
        try
        {  
          if(con == null){
        		makeConnection();
          }else if(con != null){
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String columnList = "VoterID,first_name,last_name,regnum,reg_address1,reg_address2,phone1,nid";
            if(queryString.indexOf("CHARINDEX") != -1){
            	//do duplicate check
                ResultSet resultSet = stmt.executeQuery(queryString);
            	int row = 0;
            	while (resultSet.next()){
            		if(row < 11){
            			//find way to cache results beyond 10th result, maybe a list of ids in a variable or file if we'll allow pagination
            			for(int j=1;j<9;j++){
            				//data[row][0] = Boolean.FALSE;
            				if(row < 10){
            					data[row][j] = resultSet.getString(j);
            				}
            			}
            			if(row < 10){
            				row++;
            			}
            		}
            	}                 
                resultSet.close();
                stmt.close();
            }else{
            	ResultSet resultSet = stmt.executeQuery("SELECT "+columnList+" FROM Morinteresting.dbo.Voters " + queryString);
            	int row = 0;
            	while (resultSet.next()){
            		if(row < 11){
            			//find way to cache results beyond 10th result, maybe a list of ids in a variable or file if we'll allow pagination
            			for(int j=1;j<9;j++){
            				//data[row][0] = Boolean.FALSE;
            				if(row < 10){
            					data[row][j] = resultSet.getString(j);
            				}
            			}
            			if(row<10){
            				row++;
            			}
            		}
            	}
            	resultSet.close();
            	stmt.close();
            }
          }
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        //BooleanTableModel BTM = new BooleanTableModel();
        //BTM.columnNames = columnNames;
       // BTM.data = data;
        return data;
    }
    
    public void updateVoter(String id, String[] fieldarray)
    {    	
        try
        {
            if(con == null){
          		makeConnection();
            }else if(con != null){
            stmt = con.createStatement();
            String statementString = "";
            for(int i=0;i<fieldarray.length;i+=3){
            	if(fieldarray[0].equals("id1")){ //marking two voters as duplicates from Compare Voters 
             	   statementString = "UPDATE Morinteresting.dbo.Voters SET potential_duplicate_voter = 1 WHERE VoterID = '"+fieldarray[1]+"' OR VoterID = '"+fieldarray[3]+"'";
            	}
            	else if (fieldarray[0].equals("verify_voter")){
              	   statementString = "UPDATE Morinteresting.dbo.Voters SET approved_to_register = 1 WHERE VoterID = '"+id+"'";
            	}else if (fieldarray[0].equals("approve_voter")){
               	   statementString = "UPDATE Morinteresting.dbo.Voters SET approved_to_vote = 1 WHERE VoterID = '"+id+"'";
             	}
            	else{
            		if(statementString.isEmpty()){
            			statementString = "UPDATE Morinteresting.dbo.Voters SET ";
            		}else if(i == fieldarray.length - 3){
            			statementString += ", " + fieldarray[i+1] + " = '" + fieldarray[i+2] + "' WHERE VoterID = '"+ id + "'";
            		}else if(i == 3){
            			statementString += fieldarray[i+1] + " = '" + fieldarray[i+2] + "' ";
            		}else{
            			statementString += ", " + fieldarray[i+1] + " = '" + fieldarray[i+2] + "' ";
            		}
            	}

            }
			System.out.println("update statement " + statementString);

            stmt.execute(statementString);
            stmt.close();
            }
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    public String selectUser(String username)
    {    	
        String uid = "";
        try
        {
            if(con == null){
          		makeConnection();
                stmt = con.createStatement();
                //create logic to handle misstypings unless you do a user dropdown
                String statementString = "SELECT UserID FROM Morinteresting.dbo.Users WHERE user_name = '"+username+"'";
                System.out.println(statementString);
                ResultSet rs = stmt.executeQuery(statementString);
                while (rs.next()){
            		System.out.println("select user " + rs.getString(1));
                	uid = rs.getString(1);
                }
                stmt.close();
            }else if(con != null){
            stmt = con.createStatement();
            //create logic to handle misstypings unless you do a user dropdown
            String statementString = "SELECT UserID FROM Morinteresting.dbo.Users WHERE user_name = '"+username+"'";
            System.out.println(statementString);
            ResultSet rs = stmt.executeQuery(statementString);
            while (rs.next()){
        		System.out.println("select user " + rs.getString(1));
            	uid = rs.getString(1);
            }
            stmt.close();
            }
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        System.out.println("uid is " + uid);
        return uid;
    }
    public String auditUser(String uid){
    	//this function will find all the voters modified by the user specified
    	//then write those voters and all of their data to a local file
    	//and return that file's location as a string
    	//check if we'll need to check for assigned_user and created_by user as well
    	String uname = System.getProperty("user.name");
        String localDir = "C:\\Users\\"+uname+"\\Downloads\\";
        Date currentdate = new Date();
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
        String datestamp = SDF.format(currentdate);
		String dupFile = "audit_user_"+uid+"_"+datestamp+".txt";
		String locFile = localDir + dupFile;
		try {
          try (
                PrintStream output = new PrintStream(new File(locFile));
            ){
              if(con == null){
          		makeConnection();
                stmt = con.createStatement();
            	String userQuery = "SELECT * FROM Morinteresting.dbo.Voters WHERE modified_user_id = '"+ uid + "'";
            	ResultSet rs = stmt.executeQuery(userQuery);
                output.println("VoterID,last_name,birthdate,cid,reg_address1,reg_address2,reg_address3,reg_village,reg_commune,reg_district,reg_province,reg_city,reg_zip,first_name,regnum,nid,mail_address1,mail_address2,mail_address3,mail_village,mail_commune,mail_district,mail_province,mail_city,mail_zip");
            	while (rs.next()){
        			String filerow = "";
        			for(int k=1;k<51;k++){
        					filerow += rs.getString(k) + ",";
            				//if(rs.getString(k) == null || rs.getString(k) == "null" || rs.getString(k) == ""){
            				//	filerow += ",";
            				//}
        			}
                    output.println(filerow);
                	System.out.println("filerow" + filerow);
            	}
            output.close();
        	stmt.close();
            } else if(con != null){
            stmt = con.createStatement();
        	String userQuery = "SELECT * FROM Morinteresting.dbo.Voters WHERE modified_user_id = '"+ uid + "'";
        	ResultSet rs = stmt.executeQuery(userQuery);
            output.println("VoterID,last_name,birthdate,cid,reg_address1,reg_address2,reg_address3,reg_village,reg_commune,reg_district,reg_province,reg_city,reg_zip,first_name,regnum,nid,mail_address1,mail_address2,mail_address3,mail_village,mail_commune,mail_district,mail_province,mail_city,mail_zip");
        	while (rs.next()){
        			String filerow = "";
        			for(int g=1;g<51;g++){
        				filerow += rs.getString(g)+ ",";
        				//if(rs.getString(g) == null || rs.getString(g) == "null" || rs.getString(g) == ""){
        				//	filerow += ",";
        				//}
        			}
                    output.println(filerow);
                	System.out.println("filerow" + filerow);
        		}
            output.close();
        	}
          	stmt.close();
    		}catch (SQLException sqlExcept)
            {
                sqlExcept.printStackTrace();
            }
            } catch (FileNotFoundException s) {
                s.printStackTrace();
            }
            catch( IOException e ) {
                System.out.println(e);
                System.out.println("input output auditerror");
                //System.exit(0);
           }

		return locFile;
    }
    

    public String[] selectVoter(String vid)
    {
	    String[] stringarray = new String[50];

        try
        {
            if(con == null){
          		makeConnection();
            }else if(con != null){
            stmt = con.createStatement();
            String columnList = "VoterID,last_name,birthdate,cid,reg_address1,reg_address2,reg_address3,reg_village,reg_commune, reg_district, reg_province, reg_city,reg_zip,first_name,regnum,nid,mail_address1,mail_address2,mail_address3,mail_village,mail_commune,mail_district, mail_province,mail_city,mail_zip";
            //check if were comparing voters by searching for the comma delimiter
        	vid = vid.replace("[", "");
        	vid = vid.replace("]","");
            if(vid.indexOf(",") != -1){
            	vid = vid.replace(" ", "");
            	System.out.println("This is the modified vid");
            	System.out.println(vid);
            	String[] idArray = vid.split(",");
            	vid = idArray[0] + " OR VoterID = " + idArray[1];
            	System.out.println(vid);
            }
            
            ResultSet resultSet = stmt.executeQuery("SELECT "+columnList+" FROM Morinteresting.dbo.Voters WHERE VoterID =" + vid);
		    while (resultSet.next()){
		        java.sql.ResultSetMetaData rsmd = resultSet.getMetaData();
		        int numberOfColumns = rsmd.getColumnCount();
		        for(int columnIndex = 1; columnIndex <= numberOfColumns; columnIndex ++){
		            	//if(columnIndex != 25){
		            		if(resultSet.getRow() > 1){
			            		stringarray[columnIndex + 24] = resultSet.getString(columnIndex);
		            		}else{
		            			stringarray[columnIndex-1] = resultSet.getString(columnIndex);
		            		}
		            	//} 
			            //	System.out.println("dbvalue " + columnIndex + " is " + resultSet.getString(columnIndex));

		        }
		   }
		    
            resultSet.close();
            stmt.close();
            return stringarray;
            }
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        return stringarray;
    }
    
    public static void shutdown()
    {
        try
        {
            if (stmt != null)
            {
                stmt.close();
            }
            if (con != null)
            {
                DriverManager.getConnection(getConnectionUrl,userName,password); // + ";shutdown=true"
                con.close();
            }           
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
}
