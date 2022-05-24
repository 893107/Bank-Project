package input.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Inputtest {

	static BufferedReader a = new BufferedReader(new InputStreamReader(System.in));
	static String b = null;
	
	public static String userinput() {
		
		
		
		
		try {
			b = a.readLine();
			//System.out.println(b);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	public static void streamclose() {
		try {
			if(a != null ) {
				a.close();
				//a = null;					
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

}
