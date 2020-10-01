import imageProcessor.ImageTextEmbed;
import imageProcessor.ImageTextExtract;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Mainframe extends JFrame implements ActionListener, Runnable {

	JLabel background = new JLabel(new ImageIcon("bg1.gif"));
	JLabel Lfilename, Lmessage, Ldesign, Ltitle;
	JLabel orig_image, steg_image;
	JButton Bplay, Bopen, Bsave, Bstop, Bencrypt, Bdecrypt, Bsend, Bclear;
	JTextArea Amessage;
	JScrollPane scroll;
	JTextField Tfilename;
	Icon Iplay, Iopen, Istop, Isave;
	String Ekey, Dkey, address, name;
	JFileChooser filechooser;
	File Ofilename, Sfilename, tempfilename;
	InetAddress ipaddress;
	int Copened, Cencrypt, Cdecrypt, Cplay, Cstop, Csave;
	InputStream ins;
	// AudioStream as;
	Color fontColor = Color.white;
	Thread t;

	public Mainframe() throws Exception {

		// frame
		super("Encryptioning Images");
//		File f = new File("D:\StegnographyOG\src\bg1.gif");
//		if (!f.exists()) {
//			background = new JLabel(new ImageIcon("D:\StegnographyOG\src\bg.gif"));
//		}
		
		
		Container con = getContentPane();
		con.setLayout(null);
		con.setBackground(Color.BLACK);
		// Basic
		Copened = 0;
		Cencrypt = 0;
		Cdecrypt = 0;
		Cplay = 0;
		Csave = 0;
		Cstop = 0;

		t = new Thread(this);
		t.start();

		// Icons

		Iplay = new ImageIcon("play.gif");
		Isave = new ImageIcon("save.gif");
		Iopen = new ImageIcon("open.gif");
		Istop = new ImageIcon("stop.gif");

		// file chooser

		filechooser = new JFileChooser();
		filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		// comp

		Ltitle = new JLabel("Encryption using Image");
		Ltitle.setForeground(fontColor);
		Ldesign = new JLabel("Designed By : group 19");
		Ldesign.setForeground(fontColor);
		Lfilename = new JLabel("File Name ");
		Lfilename.setForeground(fontColor);
		Lmessage = new JLabel("Message  ");
		Lmessage.setForeground(fontColor);
		Bplay = new JButton("", Iplay);
		// Bopen = new JButton("", Iopen);
		// Bsave = new JButton("", Isave);
		Bopen = new JButton("Open");
		Bsave = new JButton("Save");
		Bstop = new JButton("", Istop);
		Bclear = new JButton("Clear");
		Bencrypt = new JButton("Encoding");
		Bdecrypt = new JButton("Decoding");
		Bsend = new JButton("Send");
		Amessage = new JTextArea();
		scroll = new JScrollPane(Amessage);

		Tfilename = new JTextField();

		// tool tips

		Tfilename.setToolTipText("Opened filename");
		Bplay.setToolTipText("play");
		Bopen.setToolTipText("open");
		Bsave.setToolTipText("save");
		Bstop.setToolTipText("stop");

		Tfilename.setEditable(false);

		// Bounds
		// background.setLayout(new FlowLayout());
		background.setBounds(0, 0, 800, 600);
		Ltitle.setBounds(300, 30, 250, 25);
		Lfilename.setBounds(100, 100, 100, 25);
		Tfilename.setBounds(100, 125, 230, 25);
		Lmessage.setBounds(450, 100, 100, 25);
		scroll.setBounds(450, 125, 300, 110);
		Amessage.setLineWrap(true);
		Bclear.setBounds(450, 250, 80, 22);
		Bopen.setBounds(100, 200, 110, 25);
		Bsave.setBounds(220, 200, 110, 25);
		Bplay.setBounds(220, 200, 50, 25);
		Bstop.setBounds(280, 200, 50, 25);
		Bencrypt.setBounds(100, 250, 110, 25);
		Bdecrypt.setBounds(220, 250, 110, 25);
		Bsend.setBounds(160, 300, 110, 25);
		Ldesign.setBounds(350, 420, 400, 50);

		// label for original image
		orig_image = new JLabel();
//		orig_image.setIcon(new ImageIcon("C:\\Users\\PRIYANKA\\Documents\\p1.jpg"));
		orig_image.setBounds(100, 300, 250, 200);
		orig_image.setVisible(false);
		con.add(orig_image);

		// label for stegano'ed image
		steg_image = new JLabel();
		steg_image.setIcon(new ImageIcon("bg1.gif"));
		steg_image.setBounds(450, 300, 250, 200);
		steg_image.setVisible(false);
		con.add(steg_image);
	
		// add

		con.add(Ltitle);
		// con.add(Ldesign);
		con.add(Lfilename);
		con.add(Tfilename);
		con.add(Lmessage);
		// con.add(Amessage);
		con.add(scroll);
		con.add(Bclear);
		// con.add(Bplay);
		con.add(Bopen);
		// con.add(Bsave);
		// con.add(Bstop);
		con.add(Bencrypt);
		con.add(Bdecrypt);
		// con.add(Bsend);
		con.add(background);
		// actionListener

		Bclear.addActionListener(this);
		Bplay.addActionListener(this);
		Bopen.addActionListener(this);
		Bsave.addActionListener(this);
		Bstop.addActionListener(this);
		Bencrypt.addActionListener(this);
		Bdecrypt.addActionListener(this);
		Bsend.addActionListener(this);

	} // constr of mainframe

	public void run() {
		try {
			Recv r = new Recv();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void Imageencrypt(String message, File file, int key) throws Exception {
		Date encStartDate = new Date();
		System.out.println("Encryption Button Clicked");
		ImageTextEmbed.EmbedImage(message, file, name);
		Amessage.setText("");
		Tfilename.setText("");
		Date endEndDate = new Date();
		JOptionPane.showMessageDialog(this, "Encrypted File Is Stored In d:/" + name, "Done",
				JOptionPane.INFORMATION_MESSAGE);
		System.out.println("Input Size : " + message.length() + " Byts");
		System.out.println("Time for encryption : " + (endEndDate.getTime() - encStartDate.getTime()) + " msec");
	}

	public void Imagedecrypt(File filename, int key) throws Exception {
Date encStartDate = new Date();
		
		System.out.println("Decryption Button Clicked");
		String msg = ImageTextExtract.extractText(filename);
		System.out.println("Input Size : " + msg.length() + " Byts");
		Date endEndDate = new Date();
		System.out.println("Time for decryption : " + (endEndDate.getTime() - encStartDate.getTime())  +" msec");
		if(msg != null){
			Amessage.setText(msg);
		} else {
			Amessage.setText("No Encoded message present or \n Entered key is not correct");
		}
	}


	public void actionPerformed(ActionEvent ae) {
		try {
			if (ae.getSource() == Bencrypt) {
				if (Copened == 1) {
					Ekey = "0";
					if (Amessage.getText().length() == 0)
						JOptionPane.showMessageDialog(this, "Enter text to encrypt", "Error",
								JOptionPane.ERROR_MESSAGE);
					else {    
						int key = Integer.parseInt(Ekey);
						Imageencrypt(Amessage.getText(), Ofilename, key);
						Cencrypt = 1;
						
						
						Icon origIcon = new ImageIcon( new ImageIcon(Ofilename.getPath().replace("\\", "\\\\")).getImage().getScaledInstance(250, -1, Image.SCALE_SMOOTH));
						this.orig_image.setIcon(origIcon);
						this.orig_image.setVisible(true);
						
						Icon stegIcon = new ImageIcon( new ImageIcon("D:\\" + Ofilename.getName()).getImage().getScaledInstance(250, -1, Image.SCALE_SMOOTH));
						this.steg_image.setIcon(stegIcon);
						this.steg_image.setVisible(true);
						
						
					}
				} else {
					JOptionPane.showMessageDialog(this, "Invalid or No file selected", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} else if (ae.getSource() == Bclear) {
				Amessage.setText("");
			} else if (ae.getSource() == Bdecrypt) {
				if (Copened == 1) {
					Dkey = "0";
					if (Dkey.trim().equals(""))
						JOptionPane.showMessageDialog(this, "Enter theKey", "Error", JOptionPane.ERROR_MESSAGE);
					else {
						int key = Integer.parseInt(Dkey);
						Imagedecrypt(Ofilename, key);
						Cdecrypt = 1;
					}
				} else
					JOptionPane.showMessageDialog(this, "File NotOpened", "Error", JOptionPane.ERROR_MESSAGE);
			} else if (ae.getSource() == Bopen) {
				int r = filechooser.showOpenDialog(this);
				tempfilename = filechooser.getSelectedFile(); // File type
				if (r == JFileChooser.CANCEL_OPTION)
					JOptionPane.showMessageDialog(this, "File NotSelected", "Error", JOptionPane.ERROR_MESSAGE);
				else {
					name = tempfilename.getName();
					if (false)
						JOptionPane.showMessageDialog(this, "Select jpeg image", "Error", JOptionPane.ERROR_MESSAGE);
					else {
						Copened = 1;
						Ofilename = tempfilename;
						Tfilename.setEditable(true);
						Tfilename.setText(name);
						Tfilename.setEditable(false);
					}

				}
			} else if (ae.getSource() == Bsave) {
				if (Copened == 1 && Cencrypt == 1 || Cdecrypt == 1) {
					int r = filechooser.showSaveDialog(this);
					Sfilename = filechooser.getSelectedFile(); // File type
					InputStream in = new FileInputStream("c:/output");
					OutputStream out = new FileOutputStream(Sfilename);
					Ofilename = Sfilename;
					name = Sfilename.getName();
					Tfilename.setEditable(true);
					Tfilename.setText(name);
					Tfilename.setEditable(false);
					while (true) {
						int i = in.read();
						if (i == -1)
							break;
						out.write(i);
					}
					in.close();
					out.close();
				} else {
					String s;
					if (Copened == 0)
						s = "File not Opened";
					else if (Cencrypt == 0)
						s = "Not Encrypted";
					else
						s = "Not Decrypted";

					JOptionPane.showMessageDialog(this, s, "Error", JOptionPane.ERROR_MESSAGE);
				}
			} else if (ae.getSource() == Bsend) {
				if (Copened == 1 && Cencrypt == 1) {
					address = JOptionPane.showInputDialog("Enter The IPaddress");
					ipaddress = InetAddress.getByName(address);
					Socket socket = new Socket(ipaddress, 6000);
					OutputStream out = socket.getOutputStream();
					InputStream in = new FileInputStream(Ofilename);
					while (true) {
						int i = in.read();
						if (i == -1)
							break;
						out.write(i);
					}
					in.close();
					out.close();
				} else {
					String s;
					if (Copened == 1)
						s = "Encryption not done";
					else
						s = "Open the File first";

					JOptionPane.showMessageDialog(this, s, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}