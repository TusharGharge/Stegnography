package imageProcessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import compression.LZWCompression;
import compression.LZWDecompression;

import BCD.ReverseBCD;

import columnizer.BackwordColumnizer;

public class ImageTextExtract {

	public static String extractText(File filename) throws Exception {
		
		System.out.println("Decrypting Message from image...");
		int key = 0;
		LZWCompression c = new LZWCompression();
		String msgc = "";
		InputStream ins = new FileInputStream(filename);
		byte b[] = new byte[ins.available()];
		ins.read(b);
		char ch = '-';
		int in = b.length - 1;
		String msg = "";

		
		ch = (char) ((int) b[in]);
		
		
		//ch = (char) (key + (int) b[in]); --> DCOMP
		//System.out.println(ch);

		if ((ch + "").equals("^")) {
			String s = "";
			for (int i = 0; i <= 4; i++) {
				in--;
				ch = (char) (key + (int) b[in]);
				s = ch + s;
			}
			//System.out.println(Integer.parseInt(s));
			int leng = Integer.parseInt(s);
			while (leng > 0) {
				in--;
				//msg = (char) (key + (int) b[in]) + msg; --> DCOMP
				msg =  (char)b[in] + msg;
				leng--;
			}
			msgc = c.compress(msg);
			ins.close();
			
			
			System.out.println("Message Obtained From Image : " + msgc);
			System.out.println("Decompressing Message Using LZWDecompression");
			LZWDecompression dcomp = new LZWDecompression();
			dcomp.LZW_Decompress(msgc);
			System.out.println("DeCompressed Message : " + msg);
			System.out.println("Decryption Message...");
			
			msg = ReverseBCD.reverseBCD(msg);
			msg = BackwordColumnizer.reverseColumnize(msg);
			System.out.println("Final Messgae Obtained : " + msg);
			return (msg);
		} else {
			ins.close();
			return null;
		}

	}
}
