package BCD;

public class ReverseBCD {

	public static String reverseBCD(String s){
		
		System.out.println("");
		System.out.println("BCD Message : " + s);
		String array[] = s.split("\\|");
		String msg = "";
		for(int i=0; i<array.length; i++){
			//System.out.println(":"+array[i]);
			msg = msg + (char)Integer.parseInt(array[i]); 
		}
		System.out.println("After BCD Conversion : " + msg);
		return msg;
	}
}
