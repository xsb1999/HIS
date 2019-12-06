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

public class Diseases {			//一个患者的病种集合（是一个临时文件，每一个患者诊断完毕后会清空该文件以备下一位患者存病种集合）


	public static List<DiseaseType> readDiseases(){
		
		List<DiseaseType> list=new ArrayList<>();
		try {
			InputStream in=new FileInputStream("src\\diseases");
			DataInputStream din=new DataInputStream(in);
			
			Gson gson=new Gson();
			String str=din.readUTF();
			list=gson.fromJson(str,new TypeToken<List<DiseaseType>>() {}.getType() );
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	
	
	
	public static void qingkong() {
		File f=new File("src/diseases");
		f.delete();
		List<DiseaseType>list=new ArrayList<>();
		
		OutputStream out;
		try {
			out = new FileOutputStream("src/diseases");
			DataOutputStream dout=new DataOutputStream(out);
			Gson gson=new Gson();
			String str=gson.toJson(list);
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
	
	
	
	
	
	
	public static void main(String[]args) {	
		
		List<DiseaseType> list=new ArrayList<>();
		OutputStream out;
		try {
			out = new FileOutputStream("src/diseases");
			DataOutputStream dout=new DataOutputStream(out);
			Gson gson=new Gson();
			String str=gson.toJson(list);
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
