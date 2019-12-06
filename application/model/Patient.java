package application.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import application.GhyController;
import javafx.scene.control.Label;
import logic.MyHashTable;

public class Patient{
	private String name;
	private String age;
	private String id;
	private String sex;
	private List<String>msg=new ArrayList<>();
	
	private boolean seenTwice=false;
	
	private String weight;
	
	private boolean urgent;
	
	private String idP;
	
	private List<String>caseId=new ArrayList<>();
	

	
	


	public List<String> getCaseId() {
		return caseId;
	}


	public void setCaseId(List<String> caseId) {
		this.caseId = caseId;
	}


	public String getIdP() {
		return idP;
	}


	public void setIdP(String idP) {
		this.idP = idP;
	}


	public boolean isUrgent() {
		return urgent;
	}


	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}


	public String getWeight() {
		return weight;
	}


	public void setWeight(String weight) {
		this.weight = weight;
	}


	public boolean isSeenTwice() {
		return seenTwice;
	}


	public void setSeenTwice(boolean seenTwice) {
		this.seenTwice = seenTwice;
	}


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

	public static boolean GuaHao(String Dname,String Pname,String age,String id,String sex,String jinji,String idp) {
	
		MyHashTable<String,String>ht=new MyHashTable<String,String>();
		
		boolean flag=false;
		
		boolean flag2=false;
		
		try {
			
			List<Doctor>listd=Doctor.readD();
			
	
			List<Patient>listp=Patient.read();
			
			
			List<Case>listc=Case.readCase();
			
			
			for(Doctor d:listd) {
				if(d.getName().equals(Dname)) {
					
//					if(d.getToSee().size()==6) {
//						
//						return false;
//					}
					
					Patient p=new Patient(Pname,age,id,sex);
					
					//如果该患者以前挂过号，则可以继续挂号，idP不变					
					for(Patient pa:listp) {
						if(pa.getIdP().equals(idp)) {
							
							if(!pa.getName().equals(Pname)) {
								System.out.println("该编号已经有患者，请输入正确患者编号！");
								return false;
							}
							
							p=pa;
							
							flag2=true;
							break;
						}
					}
					
					if(!flag2) {
					String sid=IdP.readw();
					p.setIdP(sid);
					int a=Integer.parseInt(sid)+1;
					String sid2=a+"";
					IdP.qingkong(sid2);
					}
					
					
					if(jinji.equals("紧急")) {
					   p.setUrgent(true);
					   id=id+"U";
					   p.setId(id);
					}
					
					else {
						p.setUrgent(false);
						p.setId(id);
					}
					
					
					String s=Weight.readw();
					String ss=Urgent.readw();
					
					
					// 紧急病人（初始权值为1）					
					if(p.isUrgent()) {
						p.setWeight(ss);
						
						// 每次挂号后给这个病人赋的权值比上一个人多10					
						int a1=Integer.parseInt(ss)+10;
						String s1=a1+"";
						
						Urgent.qingkong(s1);
						
					}
					
					// 普通病人（初始权值为1001）					
					else {
						p.setWeight(s);
						
						// 每次挂号后给这个病人赋的权值比上一个人多300	
						int a1=Integer.parseInt(s)+300;
						String s1=a1+"";
						Weight.qingkong(s1);
						
					}

					
					Case c=new Case(id,d,p);
					c.setDname(Dname);
					c.setPname(Pname);
					
					p.getCaseId().add(c.getId());
					c.setPatient(p);
					
					
					System.out.print(c.getDname());
					listc.add(c);
					
					d.getToSee().add(p);
					
					MyHashTable<String,String> mh=new MyHashTable<String,String>();
					
					ht=mh.read();
					
					String sc=JSON.toJSONString(c);
					
					ht.put(id, sc);
					
					
			
					if(flag2) {
						
						for(Patient pa:listp) {
							if(pa.getIdP().equals(idp)) {		
								pa=p;
								break;
							}
						}
						
					}
					
					else{
						listp.add(p);
					}
							
					
					System.out.println("挂号成功!");
					
					flag=true;
				break;
				}
				
			}
			
			
			
			//更新
			
	
			String s=JSON.toJSONString(listd);
			OutputStream out1=new FileOutputStream("src/Doctors");
			DataOutputStream dout1=new DataOutputStream(out1);
			dout1.writeUTF(s);
			dout1.close();
			
			
			
			
			Gson g2=new Gson();
			String s4=g2.toJson(listp);
			OutputStream out4=new FileOutputStream("src/Patients");
			DataOutputStream dout4=new DataOutputStream(out4);
			dout4.writeUTF(s4);
			dout4.close();
			
			
			
			
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
		
		OutputStream out;
		try {
			out = new FileOutputStream("src/Patients");
			DataOutputStream dout=new DataOutputStream(out);
			Gson gson=new Gson();
			String str=gson.toJson(listPatient);
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
