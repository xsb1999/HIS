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
		
		YaoFang y1=new YaoFang("�����Ը�ð","����","�ݴ�",63.5);
		
		YaoFang y2=new YaoFang("���Գ���","����","�ݴ�",12);
		YaoFang y3=new YaoFang("֧������","����","�ݴ�",34);
		
//����ҩƷ�ļ�����ҩƷ����ҩ��	
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
			
			list3.add(new Medicine("��ð���",15,"3g/12g/��","��","����","ÿ��һ��","ÿ������",2));
			list3.add(new Medicine("�ұص�",30,"20g��1 ƿ","ƿ","�ڷ�","ÿ����Ƭ","ÿ������",1));
		
			y1.setListM(list1);
			y2.setListM(list2);
			y3.setListM(list3);
		
		
	//����ҩƷ�ļ�		
			String str1=JSON.toJSONString(listm);
			OutputStream out=new FileOutputStream("src/Medicine");
			DataOutputStream dout=new DataOutputStream(out);
			dout.writeUTF(str1);
			dout.close();
			
			
			
		//����һ��ҩ����list	
			listY.add(y1);
			listY.add(y2);
			listY.add(y3);
		
		//��ҩ��listд���ļ�����Gson��
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
