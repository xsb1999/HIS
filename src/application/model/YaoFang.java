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

public class YaoFang {
	private String name;
	private String fanwei;
	private String zhuangtai;
	private double cost;
	private List<Medicine>listM=new ArrayList<>();
	
	
	public YaoFang(String name, String fanwei,String zhuangtai,double cost) {
		
		this.name = name;
		this.fanwei = fanwei;
		this.zhuangtai=zhuangtai;
		this.cost=cost;
	}
	
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getFanwei() {
		return fanwei;
	}
	public void setFanwei(String fanwei) {
		this.fanwei = fanwei;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Medicine> getListM() {
		return listM;
	}
	public void setListM(List<Medicine> listM) {
		this.listM = listM;
	}
	



	
	

	public String getZhuangtai() {
		return zhuangtai;
	}
	public void setZhuangtai(String zhuangtai) {
		this.zhuangtai = zhuangtai;
	}
	public static void main(String[]args) {
		
		List<YaoFang>listY=new ArrayList<>();
		
		YaoFang y1=new YaoFang("流行性感冒","个人","暂存",63.5);
		
		YaoFang y2=new YaoFang("急性肠炎","科室","暂存",12);
		YaoFang y3=new YaoFang("支气管炎","个人","暂存",34);
		
//读如药品文件，将药品加入药方	
		InputStream in;
		try {
			in = new FileInputStream("src/Medicine");
			DataInputStream din=new DataInputStream(in);
			String str=din.readUTF();
			List<Medicine>listm=JSON.parseArray(str, Medicine.class);
			
			
			List<Medicine>list1=new ArrayList<>();
			List<Medicine>list2=new ArrayList<>();
			List<Medicine>list3=new ArrayList<>();
			
			for(Medicine m:listm) {
			
				list1.add(m);
				
			}
			
			for(Medicine m:listm) {

				list2.add(m);
				break;
			}
			
			list3.add(new Medicine("感冒冲剂",15,"3g/12g/袋","袋","冲泡","每次一袋","每日两次",2));
			list3.add(new Medicine("芬必得",30,"20g×1 瓶","瓶","口服","每次两片","每日三次",1));
		
			y1.setListM(list1);
			y2.setListM(list2);
			y3.setListM(list3);
		
		
	//更新药品文件		
			String str1=JSON.toJSONString(listm);
			OutputStream out=new FileOutputStream("src/Medicine");
			DataOutputStream dout=new DataOutputStream(out);
			dout.writeUTF(str1);
			dout.close();
			
			
			
		//创建一个药方的list	
			listY.add(y1);
			listY.add(y2);
			listY.add(y3);
		
		//将药方list写入文件（用Gson）
			Gson gson=new Gson();
			String str11=gson.toJson(listY);
			OutputStream out1=new FileOutputStream("src/YaoFang");
			DataOutputStream dout1=new DataOutputStream(out1);
			dout1.writeUTF(str11);
			dout1.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		List<Medicine>l=new ArrayList<>();
		l=y3.getListM();
		for(Medicine mm:l) {
			System.out.println(mm.getPrice());
		}
		System.out.println(y1.getListM().isEmpty());
		
		
		
		
	}
	
	
}
