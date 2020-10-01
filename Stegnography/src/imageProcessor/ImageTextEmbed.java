package imageProcessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import compression.LZWCompression;

import BCD.ForwardBCD;
import columnizer.ForwardColumnizer;

public class ImageTextEmbed {

	public static void EmbedImage(String message, File file, String name) throws Exception{
		
		System.out.println("Message Entered : " + message);
		message = ForwardColumnizer.columnize(message);
		message = ForwardBCD.forwardBCD(message);
		System.out.println("Applying Encryption....");
		
		System.out.println("Applying LZW Compression....");
		LZWCompression lzw = new LZWCompression();
		String compressedMessage = lzw.compress(message);
		System.out.println("Compressed Message : " + compressedMessage);
		//message = compressedMessage;
		System.out.println("Embedding Data In To Image....");
		int identification_number = 1;
		//if(1/1 == 1){
		// CUSTOM ERROR
			//throw new Exception("Dont know what to do !!!");
		//}
		InputStream ins = new FileInputStream(file);
		OutputStream outs = new FileOutputStream(new File("d:/" + name));
		byte b[] = new byte[ins.available()];

		byte a[] = new byte[message.length() + 1 + 5];

		int c = message.length();

		String num = "" + c;
		while (num.length() <= 4) {
			num = "0" + num;
		}
		message = message + num + "^";
		
		a = message.getBytes();
		/*for (c = 0; c < message.length(); c++) {
			char ch = message.charAt(c);
			a[c] = Byte.parseByte((int) ch - identification_number + "");
		}*/
		
		ins.read(b);
		ins.available();
		outs.write(b);
		outs.write(a);
		
		ins.close();
		outs.close();
		
		System.out.println("Encryption Process Completed");
	}
}
