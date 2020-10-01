package BCD;

public class ForwardBCD {

	public static String forwardBCD(String s){
		
		System.out.println("Applying BCD Conversion");
		System.out.println("Original Message : " + s);
		String bcdMsg = "";
		for(int i=0; i<s.length(); i++){
			bcdMsg = bcdMsg + (int)s.charAt(i) + "|"; 
		}
		System.out.println("After BCD Conversion : " + bcdMsg);
		return bcdMsg;
	}
}
