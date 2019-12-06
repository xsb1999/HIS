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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Case {
	private String id;
	private Doctor doctor;
	private Patient patient;
	private Medicine medicine;
	private double cost;
	private List<String>bingliXinXi;
	private List<YaoFang>yaofang;
	private boolean jiaofeifou=false;
	private boolean yijingfayao=false;
	private boolean tuihao=false;
	
	
	public boolean isTuihao() {
		return tuihao;
	}

	public void setTuihao(boolean tuihao) {
		this.tuihao = tuihao;
	}

	public boolean isYijingfayao() {
		return yijingfayao;
	}

	public void setYijingfayao(boolean yijingfayao) {
		this.yijingfayao = yijingfayao;
	}

	public boolean isJiaofeifou() {
		return jiaofeifou;
	}

	public void setJiaofeifou(boolean jiaofeifou) {
		this.jiaofeifou = jiaofeifou;
	}

	public List<YaoFang> getYaofang() {
		return yaofang;
	}

	public void setYaofang(List<YaoFang> yaofang) {
		this.yaofang = yaofang;
	}

	public String getId() {
		return id;
	}

	public Case(String id, Doctor doctor, Patient patient) {
		super();
		this.id = id;
		this.doctor = doctor;
		this.patient = patient;
	
	}

	public void setId(String id) {
		this.id = id;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost2) {
		this.cost = cost2;
	}

	public List<String> getBingliXinXi() {
		return bingliXinXi;
	}

	public void setBingliXinXi(List<String> bingliXinXi) {
		this.bingliXinXi = bingliXinXi;
	}

	
	
	
	
	public static List<Case> readCase(){
		List <Case>c =new ArrayList<>();
		InputStream in;
		try {
			in = new FileInputStream("src/Cases");
			DataInputStream din=new DataInputStream(in);
			Gson gson=new Gson();
			String str=din.readUTF();
			//c=JSON.parseArray(str,Case.class);
			c=gson.fromJson(str,new TypeToken<List<Case>>() {}.getType() );
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
		
		
	}
	
	
	
	
	public static void main(String[]args) {
		List <Case>cases =new ArrayList<>();
		
		OutputStream out;
		try {
			out = new FileOutputStream("src/Cases");
			DataOutputStream dout=new DataOutputStream(out);
			Gson gson=new Gson();
			String str=gson.toJson(cases);
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


