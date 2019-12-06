package application.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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

public class Yp {

	
	public static List<Medicine> read(){
		try {
			InputStream in=new FileInputStream("src\\yp");
			DataInputStream din=new DataInputStream(in);
			List<Medicine>list=new ArrayList<>();
			Gson gson=new Gson();
			String str=din.readUTF();
			list=gson.fromJson(str,new TypeToken<List<Medicine>>() {}.getType() );
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
	
	
	
	public static void main(String[] args) {

		List<Medicine>listm=new ArrayList<>();
		
		Gson gson=new Gson();
		String str=gson.toJson(listm);
		try {
			OutputStream out=new FileOutputStream("src\\yp");
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
