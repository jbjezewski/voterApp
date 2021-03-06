package voterApp;

	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.*;
	import java.io.*;
	/**
	    FontSwitcher

	    @author Stefan Wagner
	    @date So 13. Mai 03:25:23 CEST 2012    
	*/
	public class FontSwitcher extends JFrame implements ActionListener
	{
	    private static final String progname = "FontSwitcher 0.1";

	    private JTextField feedback;
	    private JButton jb;
	    private JList fontList;

	    public FontSwitcher ()
	    {
	        super (progname);
	        JPanel mainpanel = new JPanel ();
	        mainpanel.setLayout (new BorderLayout ());
	        this.getContentPane ().add (mainpanel);

	        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment ();
	        String [] possiblefonts = ge.getAvailableFontFamilyNames ();
	        String [] desiredfonts = new String[]{"Khmer","Times New Roman"};
	        String [] fonts = new String[desiredfonts.length];
	        for(int g = 0; g < possiblefonts.length; g++){
	        	for(int s = 0; s < desiredfonts.length; s++){
	        		if(possiblefonts[g].equals(desiredfonts[s])){
	        			fonts[s] = possiblefonts[g];
	        		}
	        	}
	        }
	        fontList = new JList (fonts);
	        JScrollPane js = new JScrollPane (fontList);
	        feedback = new JTextField ("Test Font");
	        jb = new JButton ("apply font");
	        jb.addActionListener (this);
	        mainpanel.add (feedback, BorderLayout.NORTH);
	        mainpanel.add (js, BorderLayout.CENTER);
	        mainpanel.add (jb, BorderLayout.SOUTH);
	        setSize (100, 150);
	        setLocation (100, 100);
	        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	        setVisible (true);
	    }

	    public void actionPerformed (final ActionEvent e)
	    {
	        SwingWorker worker = new SwingWorker () 
	        {
	            protected String doInBackground () throws InterruptedException 
	            {
	                String cmd = e.getActionCommand ();
	                if (cmd.equals ("apply font"))
	                {
	                    String selectedFont = fontList.getSelectedValue ().toString ();
	                    System.out.println("you selected " + selectedFont);
	                    Font font = new Font (selectedFont, Font.TRUETYPE_FONT, 14);
	                    jb.setFont (font);
	                    feedback.setFont (font);
	                }
	                return "done";
	            }
	            protected void done () 
	            {
	                feedback.setText ("done");
	            }
	        };
	        worker.execute ();
	    }

	    public static void main (final String args[])
	    {
	        Runnable runner = new Runnable () 
	        {
	            public void run () 
	            {
	                new FontSwitcher ();
	            }
	        };
	        EventQueue.invokeLater (runner);
	    }
	

}
