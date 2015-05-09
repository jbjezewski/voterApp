package voterApp;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.awt.*;

import voterApp.DBFunctions;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.DefaultCellEditor;
import javax.swing.table.DefaultTableModel;

public class SearchResults extends Applet{
	
    public JTable init(String queryString, JFrame mainJFrame, String fontType) {
    	
    			final String ffontType = fontType;
    			String thisQuery = queryString; 
    			System.out.println("search results query string "  + thisQuery);
    	        //make sure the program exits when the frame closes
    	        mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	        //mainJFrame.setTitle("Search Results");
    	        mainJFrame.setSize(1000,800);
    	        mainJFrame.setLocationRelativeTo(null);
    	        DBFunctions DBF = new DBFunctions();
    	        if(thisQuery.equals("none")){
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
        	        BooleanTableModel voterTable = new BooleanTableModel();
        	        JTable table = new JTable(voterTable);
	                final Font font = new Font(ffontType, Font.TRUETYPE_FONT, 14);
	                final class CustomTableCellRenderer extends DefaultTableCellRenderer 
	                {
	                    public Component getTableCellRendererComponent
	                       (JTable table, Object value, boolean isSelected,
	                       boolean hasFocus, int row, int column) 
	                    {
	                        Component cell = super.getTableCellRendererComponent
	                           (table, value, isSelected, hasFocus, row, column);
	                            cell.setFont(font);
	                        return cell;
	                    }
	                }
        	        TableCellRenderer renderer = new CustomTableCellRenderer();
        	       try
        	        {
        	        	Object obj = new Object();
        	        	for(int s=0; s < 9; s++){
        	        		renderer.getTableCellRendererComponent(table, obj, false, false, 0, s);
        	        	}
        	            table.setDefaultRenderer( Class.forName
        	               ( "java.lang.Integer" ), renderer );
        	        }
        	        catch( ClassNotFoundException ex )
        	        {
        	            System.exit( 0 );
        	        }
         	       // table.createImage(16, 16);
         	        table.setAutoCreateRowSorter(true);
            	      //  table.setShowGrid(false);
         	        table.setGridColor(Color.BLUE);
         	        table.setBackground(Color.WHITE);	        
         	        JScrollPane tableScrollPane = new JScrollPane(table);
         	        mainJFrame.add(tableScrollPane);
         	        mainJFrame.setVisible(true);
     	        return table;
    	        }else{
    	        	BooleanTableModel voterTable = new BooleanTableModel();
        	        Font font = new Font (fontType, Font.TRUETYPE_FONT, 14);
        	      //  voterTable.setFont(font);
    	        	if(!fontType.equals("Khmer")){
    	        	voterTable.columnNames = new String[]{"Selected","ID", "Given Name", "Family Name", "Regnum", "Registered Address 1", "Registered Address 2", "Phone", "NID"};
    	        	}else if(fontType.equals("Khmer")){
    	        		voterTable.columnNames = new String[]{"បាន​ជ្រើស​រើស","លេខអត្តសញ្ញាណ ឬ លេខសំគាល់", "ឈ្មោះ", "ត្រកូល", "លេខចុះឈ្មោះ", "អាសយដ្ឋាន្នដែលបានចុះបញ្ជី មួយ", "អាសយដ្ឋាន្នដែលបានចុះបញ្ជី ពីរ", "ទូរស័ព្ទ", "អត្តសញ្ញាណប័ណ្ណជាតិ"};
    	        	}
    	        	voterTable.data = DBF.getSearchResults(thisQuery);
    	        	for (int v = 0;v<10;v++){
    	        		voterTable.isCellEditable(0,v);
    	        	}
    	        	//BooleanTableModel voterTable = DBF.getSearchResults(thisQuery);
        	        JTable table = new JTable(voterTable);
        	        table.setFont(font);
         	       // table.createImage(16, 16);
         	        table.setAutoCreateRowSorter(true);
            	    table.setShowGrid(true);
         	        table.setGridColor(Color.BLUE);
         	        table.setBackground(Color.WHITE);	        
         	        JScrollPane tableScrollPane = new JScrollPane(table);
         	        mainJFrame.add(tableScrollPane);
         	        mainJFrame.setVisible(true);
     	        return table;
    	        }
	   }

  class BooleanTableModel extends AbstractTableModel { 
	  String[] columnNames = new String[9];
	  Object[][] data = new Object[10][9];
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
  public Class getColumnClass(int columnIndex) {
      Object[][] data = {
              {Boolean.FALSE,""}
      };
	//  System.out.println(data[0][0].getClass());
	//  System.out.println(data[0][1].getClass());

	  if(columnIndex==0){
		  return data[0][0].getClass();
	  }else{
		  return data[0][1].getClass();
	  }
  }
  @Override
  public boolean isCellEditable(int row, int column)
  {
	 // System.out.println("checking if cell is editable"+row+" and column " + column);
     if (column == 0)
     	{
  	   	return true;
 		}
     else
 		{
	   		return false;
 		}
  }
  @Override
  public void setValueAt(Object inValue, int Row, int Col) {
      if(Row < 0 || Col < 0 || Row >= getRowCount() )
          return;
          switch (Col) {
              case 0:data[Row][Col] = inValue;break;
              case 1:data[Row][Col] = inValue;break;
              case 2:data[Row][Col] = inValue;break;
              case 3:data[Row][Col] = inValue;break;
              case 4:data[Row][Col] = inValue;break;
              case 5:data[Row][Col] = inValue;break;
              case 6:data[Row][Col] = inValue;break;
              case 7:data[Row][Col] = inValue;break;
              case 8:data[Row][Col] = inValue;break;
              case 9:data[Row][Col] = inValue;break;
          }
          fireTableCellUpdated(Row, Col);
      }
  }
  
 
}

