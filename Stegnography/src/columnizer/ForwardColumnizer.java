package columnizer;

import java.util.Arrays;

public class ForwardColumnizer {

	public static String columnize(String s){
		
		System.out.println("Applying Column Transpositioning");
		
		if(s == null)
			return null;
		int length = s.length();
		int size = (int) Math.ceil(Math.sqrt(length));
		//System.out.println(length);
		//System.out.println(size);
		
		for(int i=length; i<size*size; i++){
			s = s+"#";
		}
		System.out.println("Adjusted String : " + s);
		char array[][] = new char[size][size];
		int count = 0;
		char inputArray[] = s.toCharArray();
		for(int i=0; i < size; i++){
			for(int j=0; j<size ;j++){
				array[i][j] = inputArray[count++];
			}
		}
		
		for(int i=0; i < size; i++){
			//System.out.println(Arrays.toString(array[i]));
		}
		String columnizeString = "";
		for(int i=0; i < size; i++){
			for(int j=0; j<size ;j++){
				columnizeString = columnizeString + array[j][i];
				//System.out.println(columnizeString);
			}
		}
		
		System.out.println("After Columnizing : " + columnizeString);
		return columnizeString;
	}
}
