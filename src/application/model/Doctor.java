package application.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Doctor{
	private String name;
	private String department;
	
	private List<Patient> haveSeen=new ArrayList<Patient>();
	private List<Patient> toSee=new ArrayList<Patient>();
	
	public Doctor(String name,String d) {
		this.name = name;
		this.department=d;
	}
	

	public Doctor() {
	
	}
	
	public List<Patient> getHaveSeen() {
		return haveSeen;
	}


	public void setHaveSeen(List<Patient> haveSeen) {
		this.haveSeen = haveSeen;
	}


	public List<Patient> getToSee() {
		return toSee;
	}


	public void setToSee(List<Patient> toSee) {
		this.toSee = toSee;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

	
	
	public static List<Doctor> readD() {
		try {
			InputStream in=new FileInputStream("src/Doctors");
			DataInputStream din=new DataInputStream(in);
			List<Doctor>list=new ArrayList<>();
			Gson gson=new Gson();
			String str=din.readUTF();
			list=gson.fromJson(str,new TypeToken<List<Doctor>>() {}.getType() );
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
		
		
		
		List<Doctor>listD=new ArrayList<>();
		listD.add(new Doctor("kim","骨科"));
		listD.add(new Doctor("jim","心血管外科"));
		listD.add(new Doctor("tom","骨科"));
		listD.add(new Doctor("Jordan","胸外科"));
		listD.add(new Doctor("bob","胸外科"));
		
		try {
			String str=JSON.toJSONString(listD);
			OutputStream out=new FileOutputStream("src/Doctors");
			DataOutputStream dout=new DataOutputStream(out);
			dout.writeUTF(str);
			dout.close();
		    } 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	
	}
    
    
    
    
    
    
    

