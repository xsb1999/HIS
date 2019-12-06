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
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import application.GhyController;
import javafx.scene.control.Label;

public class Patient {
	private String name;
	private String age;
	private String id;
	private String sex;
	private List<String>msg=new ArrayList<>();
	

	public List<String> getMsg() {
		return msg;
	}


	public void setMsg(List<String> msg) {
		this.msg = msg;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public Patient(String name, String age,String id,String sex) {
		
		this.name = name;
		this.age = age;
		this.id=id;
		this.sex=sex;
	}


	public Patient() {
		// TODO Auto-generated constructor stub
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
	return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getId() {
		return id;
		}
		public void setId(String id) {
			this.id = id;
		}

		
		
		public static List<Patient> read() {
			try {
				InputStream in=new FileInputStream("src\\Patients");
				DataInputStream din=new DataInputStream(in);
				List<Patient>list=new ArrayList<>();
				Gson gson=new Gson();
				String str=din.readUTF();
				list=gson.fromJson(str,new TypeToken<List<Patient>>() {}.getType() );
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
		
		
		
		
		
		
		
		
		
		
		

//挂号

	public static boolean GuaHao(String Dname,String Pname,String age,String id,String sex) {
	
		boolean flag=false;
		
		try {
			InputStream in=new FileInputStream("src/Doctors");
			DataInputStream din=new DataInputStream(in);
			String str=din.readUTF();
			List<Doctor>listd=JSON.parseArray(str, Doctor.class);
			
			
			InputStream in2=new FileInputStream("src/Patients");
			DataInputStream din2=new DataInputStream(in2);
			String str2=din2.readUTF();
			List<Patient>listp=JSON.parseArray(str2, Patient.class);
			
			
			InputStream in3=new FileInputStream("src/Cases");
			DataInputStream din3=new DataInputStream(in3);
			String str3=din3.readUTF();
			Gson g=new Gson();
			List<Case>listc=g.fromJson(str3,new TypeToken<List<Case>>() {}.getType() );
			
			for(Doctor d:listd) {
				if(d.getName().equals(Dname)) {
					Patient p=new Patient(Pname,age,id,sex);
					
					listp.add(p);
					
					d.getToSee().add(p);
					
					Case c=new Case(id,d,p);
					listc.add(c);
					
					System.out.println("挂号成功!");
					flag=true;
				break;
				}
				
			}
			
			
			
			//更新
			String s=JSON.toJSONString(listd);
			OutputStream out=new FileOutputStream("src/Doctors");
			DataOutputStream dout=new DataOutputStream(out);
			dout.writeUTF(s);
			dout.close();
			
			String s2=JSON.toJSONString(listp);
			OutputStream out2=new FileOutputStream("src/Patients");
			DataOutputStream dout2=new DataOutputStream(out2);
			dout2.writeUTF(s2);
			dout2.close();
			
			Gson g1=new Gson();
			String s3=g1.toJson(listc);
			OutputStream out3=new FileOutputStream("src/Cases");
			DataOutputStream dout3=new DataOutputStream(out3);
			dout3.writeUTF(s3);
			dout3.close();
		
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag) {
			return true;
		}
		else {
			return false;
		}
	}

	
	//初始化一个患者，存入患者文件
	public static void main(String[]args) {
		List<Patient>listPatient=new ArrayList<>();
		
		try {
			String str=JSON.toJSONString(listPatient);
			OutputStream out=new FileOutputStream("src/Patients");
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
