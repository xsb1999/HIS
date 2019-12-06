package application.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class Urgent {
	
	
	
	public static void qingkong(String s) {
		File f=new File("src/urgent");
		f.delete();
		
		try {
			Writer fw=new FileWriter("src/urgent");
			fw.write(s);
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public static String readw(){

		Reader fr;
		try {
			fr = new FileReader("src/urgent");
			BufferedReader br=new BufferedReader(fr);
			String a=br.readLine();
			return a;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
			
	
}


	public static void main(String[] args) {
		
		try {
			Writer fw=new FileWriter("src/urgent");
			String a="1";
			fw.write(a);
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
