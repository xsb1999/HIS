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
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Bingzhong {          //Bingzhong1�ļ���ҽԺ�������еĲ��֣�Bingzhong.txt��һ��������
	
	
	
	
	public static Vector<DiseaseType> read() {
		
		
		InputStream in;
		try {
			in = new FileInputStream("src/bingzhong.txt");
			DataInputStream din1=new DataInputStream(in);
			String str111=din1.readUTF();
			Gson gson1=new Gson();
			Vector<DiseaseType>dd=gson1.fromJson(str111,new TypeToken<Vector<DiseaseType>>() {}.getType() );
			
			return dd;
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
		
	}
	
	
	
	
	public static void qingkong() {
		
		File f=new File("src/bingzhong.txt");
		f.delete();
		
		Vector<DiseaseType> v=new Vector<>();
		
		Gson g1=new Gson(); 
		String s1=g1.toJson(v);
		OutputStream out1;
		try {
			out1 = new FileOutputStream("src/bingzhong.txt");
			DataOutputStream dout=new DataOutputStream(out1);
			dout.writeUTF(s1);
			dout.close();
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

	public static void main(String[] args) {
		
		Vector<DiseaseType> v=new Vector<>();
		Vector<DiseaseType> v1=new Vector<>();
		
		v.add(new DiseaseType("1","��"));
		v.add(new DiseaseType("2","�ڿ�"));
		v.add(new DiseaseType("3","���"));
		v.add(new DiseaseType("4","�����ڿ�"));
		v.add(new DiseaseType("5","�����ڿ�"));
		v.add(new DiseaseType("6","�β�"));
		v.add(new DiseaseType("7","θ��"));
		v.add(new DiseaseType("8","����"));
		v.add(new DiseaseType("9","θ����"));
		v.add(new DiseaseType("10","θ��"));
		v.add(new DiseaseType("11","��Ӳ��"));
		
		
		
		Gson g=new Gson(); 
		String s=g.toJson(v);
		OutputStream out;
		try {
			out = new FileOutputStream("src/bingzhong1");
			DataOutputStream dout=new DataOutputStream(out);
			dout.writeUTF(s);
			dout.close();
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		Gson g1=new Gson(); 
		String s1=g1.toJson(v1);
		OutputStream out1;
		try {
			out1 = new FileOutputStream("src/bingzhong.txt");
			DataOutputStream dout=new DataOutputStream(out1);
			dout.writeUTF(s1);
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
