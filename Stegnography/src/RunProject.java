import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;


public class RunProject
 {
  public static void main(String arg[])throws Exception
   {
    Mainframe frame=new Mainframe();
    frame.setSize(800,600);
    frame.setVisible(true);


    frame.addWindowListener( new WindowAdapter()
     {
      public void windowClosing(WindowEvent we)
       {
        System.exit(0);
       }
     });
	  ByteArrayOutputStream ba = new ByteArrayOutputStream();
	  DataOutputStream ds = new DataOutputStream(ba);
	  
	  ds.close();
	  ds.flush();
	  ba.toString();
	  //System.out.println(ba.toString()+"df");
	  //System.out.println("DONE");
   } // end of main

 } // end of class
