package columnizer;

import java.util.Arrays;

public class BackwordColumnizer {

	public static String reverseColumnize(String s){
		
		System.out.println("Applying Reverse Column Transpositioning");
		System.out.println("Message To Process : " + s);
		if(s == null)
			return null;
		int length = s.length();
		int size = (int) Math.ceil(Math.sqrt(length));
		
		for(int i=length; i<size*size; i++){
			s = s+"#";
		}
		char array[][] = new char[size][size];
		int count = 0;
		char inputArray[] = s.toCharArray();
		for(int i=0; i < size; i++){
			for(int j=0; j<size ;j++){
				array[j][i] = inputArray[count++];
			}
		}
		
		/*for(int i=0; i < size; i++){
			System.out.println(Arrays.toString(array[i]));
		}*/
		String reverseColumnizeString = "";
		for(int i=0; i < size; i++){
			for(int j=0; j<size ;j++){
				reverseColumnizeString = reverseColumnizeString + array[i][j];
				//System.out.println(reverseColumnizeString);
			}
		}
		
		while(reverseColumnizeString.charAt(reverseColumnizeString.length() - 1) == '#'){
			reverseColumnizeString = reverseColumnizeString.substring(0, reverseColumnizeString.length() - 1);
		}
		
		System.out.println("After Reverse columnize : " + reverseColumnizeString);
		return reverseColumnizeString;
	}
}
