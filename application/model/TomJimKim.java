package application.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TomJimKim {
	
	
	
	public static void clear() {
		File f=new File("src\\tomjimkim");
		f.delete();
		
		List<String>listY1=new ArrayList<>();
		Gson gson=new Gson();
		String str=gson.toJson(listY1);
		try {
			OutputStream out=new FileOutputStream("src\\tomjimkim");
			DataOutputStream dout=new DataOutputStream(out);
			dout.writeUTF(str);
			dout.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	

	public static List<String> read(){
		try {
			InputStream in=new FileInputStream("src\\tomjimkim");
			DataInputStream din=new DataInputStream(in);
			List<String>list=new ArrayList<>();
			Gson gson=new Gson();
			String str=din.readUTF();
			list=gson.fromJson(str,new TypeToken<List<String>>() {}.getType() );
			return list;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	
	

	
	public static void main(String[]args) {
		
		List<String>list=new ArrayList<>();
		
		Gson gson=new Gson();
		String str=gson.toJson(list);
		try {
			OutputStream out=new FileOutputStream("src\\tomjimkim");
			DataOutputStream dout=new DataOutputStream(out);
			dout.writeUTF(str);
			dout.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
}
