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

import com.alibaba.fastjson.JSON;

public class Hospital {
	private static List<Doctor>listD=new ArrayList<>();
	//private static List<Patient>listP=new ArrayList<>();
	private static List<Medicine>listM=new ArrayList<>();

	public static void main(String[]args) {
	try {
		InputStream in1=new FileInputStream("D:/java程序（eclipse）/HIS/src/Doctors");
		DataInputStream din1=new DataInputStream(in1);
		String str1=din1.readUTF();
		listD=JSON.parseArray(str1, Doctor.class);
		
		
		
//		InputStream in2=new FileInputStream("D:/java程序（eclipse）/RegisterPane/src/Patients");
//		DataInputStream din2=new DataInputStream(in2);
//		String str2=din2.readUTF();
//		listP=JSON.parseArray(str2, Patient.class);
		
		
		InputStream in3=new FileInputStream("D:/java程序（eclipse）/HIS/src/Medicine");
		DataInputStream din3=new DataInputStream(in3);
		String str3=din3.readUTF();
		listM=JSON.parseArray(str3, Medicine.class);
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	List<Hospital>listH=new ArrayList<>();
	
	String s=JSON.toJSONString(listH);
	OutputStream out;
	try {
		out = new FileOutputStream("D:/java程序（eclipse）/HIS/src/Hostipal");
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
	
	
	
	
	
	
	
	
	
	}
	
}
