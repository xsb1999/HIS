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

public class Medicine {

	
	private String name;
	private double price;
	private String guige;
	private String danwei;
	private String yongfa;
	private String yongliang;
	private String pinlv;
	private int shuliang;
	
	
	
	public String getGuige() {
		return guige;
	}

	public void setGuige(String guige) {
		this.guige = guige;
	}

	public String getYongfa() {
		return yongfa;
	}

	public void setYongfa(String yongfa) {
		this.yongfa = yongfa;
	}

	public String getYongliang() {
		return yongliang;
	}

	public void setYongliang(String yongliang) {
		this.yongliang = yongliang;
	}

	public String getPinlv() {
		return pinlv;
	}

	public void setPinlv(String pinlv) {
		this.pinlv = pinlv;
	}

	public String getDanwei() {
		return danwei;
	}

	public void setDanwei(String danwei) {
		this.danwei = danwei;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getType() {
		return guige;
	}

	public void setType(String type) {
		this.guige = type;
	}

	public Medicine(String name, int price, String guige,String danwei,String yongfa,String yongliang,String pinlv,int shuliang) {
		this.name = name;
		this.price = price;
		this.guige = guige;
		this.danwei=danwei;
		this.yongfa=yongfa;
		this.yongliang=yongliang;
		this.pinlv=pinlv;
		this.setShuliang(shuliang);
		
	}

	public static void main(String[] args) {
		List<Medicine>listm=new ArrayList<>();
		listm.add(new Medicine("������",10,"1g/10g/��","��","����","ÿ��һ��","ÿ������",3));
		listm.add(new Medicine("��ð���",15,"3g/12g/��","��","����","ÿ��һ��","ÿ������",2));
		listm.add(new Medicine("��˾ƥ��",20,"1g/15g/��","��","�ڷ�","ÿ��һƬ","ÿ������",2));
		listm.add(new Medicine("�ұص�",30,"20g��1 ƿ","ƿ","�ڷ�","ÿ����Ƭ","ÿ������",1));
		
		
		try {
			String str=JSON.toJSONString(listm);
			OutputStream out = new FileOutputStream("src\\Medicine");
			DataOutputStream dout=new DataOutputStream(out);
			dout.writeUTF(str);
			dout.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		InputStream in;
//		try {
//			in = new FileInputStream("D:\\java����eclipse��\\RegisterPane\\src\\Medicine");
//			DataInputStream din=new DataInputStream(in);
//			String str=din.readUTF();
//			List<Medicine>list=JSON.parseArray(str,Medicine.class);
//			for(Medicine m:list) {
//				System.out.println(m.getName());
//			}
//			
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		
	}

	public int getShuliang() {
		return shuliang;
	}

	public void setShuliang(int shuliang) {
		this.shuliang = shuliang;
	}

}
