import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

class Recv extends JFrame implements Runnable
 {
   JFileChooser fc;
   ServerSocket ss;
   Socket s;
   InputStream ins;
   OutputStream out;
   byte b[];
   int len;

  public Recv() throws Exception
    {

      b=new byte[100];
      fc=new JFileChooser();
      fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
      ss=new ServerSocket(6000);
      torun();
    }
  public void torun() throws Exception
   {
      while(true)
       {
        s=ss.accept();
        ins=s.getInputStream();
        String str="You have Receive An AudioFile.Save them";
JOptionPane.showMessageDialog(this,str,"Information",JOptionPane.INFORMATION_MESSAGE);
        int r=fc.showSaveDialog(this);
        File file=fc.getSelectedFile();
        out=new FileOutputStream(file);
        Thread t=new Thread(this);
        t.start();
       }
    }
   public void run()
    {
      try
        {
         while(true)
          {
           int n=ins.read();
        if(n==-1) break;
           out.write(n);
          }
       //  s.close();
         ins.close();
         out.close();
        }
      catch(Exception e)
        {
           System.out.println(e);
        }
    } // end of run
 } //end of class


