package application.model;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class DoctorListOfPatient {

	
	//未诊列表
	public static List<Patient> getToSeeList(String Dname) {
		
	List<Patient>listp=new ArrayList<>();
	
	boolean flag=false;
		InputStream in;
		try {
			//"D:/java程序（eclipse）/HIS/src/Doctors"
			in = new FileInputStream("src\\Doctors");
			//DoctorListOfPatient.class.getClassLoader().getResource("Doctors").getPath()
			DataInputStream din=new DataInputStream(in);
			String str=din.readUTF();
			List<Doctor>listd=JSON.parseArray(str, Doctor.class);
			
			for(Doctor d:listd) {
				if(d.getName().equals(Dname)) {
					listp=d.getToSee();
					flag=true;
					break;
				}
				
			}
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(flag) {
			return listp;
		}
		
		else {
			return null;
		}
		
		
	}
	
	
	
	
	
	
	//已诊列表
	
	public static List<Patient> getHaveSeenList(String Dname) {
		
		List<Patient>listp=new ArrayList<>();
		
		boolean flag=false;
			InputStream in;
			try {
				in = new FileInputStream("src\\Doctors");
				
				DataInputStream din=new DataInputStream(in);
				String str=din.readUTF();
				List<Doctor>listd=JSON.parseArray(str, Doctor.class);
				
				for(Doctor d:listd) {
					if(d.getName().equals(Dname)) {
						listp=d.getHaveSeen();
						flag=true;
						break;
					}
					
				}
				
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(flag) {
				return listp;
			}
			
			else {
				return null;
			}
			
			
		}
	
	
	
	
	
	
}
