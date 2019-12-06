package application.model;


import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Test {
	
	public static void main(String[]args) {
		
		InputStream in;
		try {
			in = new FileInputStream("D:\\java程序（eclipse）\\HIS\\src\\YaoFang");
			DataInputStream din=new DataInputStream(in);
			String s=din.readUTF();
			
			//Gson解析嵌套list（nb！！！！！！！！！！！！）
			
			Gson gson=new Gson();
			List<YaoFang> list = gson.fromJson(s, new TypeToken<List<YaoFang>>() {}.getType());
			
			
			for(YaoFang m:list) {
				System.out.println(m.getName());
				
				
				
				if(m.getName().equals("流行性感冒")) {
					System.out.println("okk");
					
					List<Medicine>lm=m.getListM();
					for(Medicine mm:lm) {
						System.out.println(mm.getGuige());
					}
					System.out.println(lm.isEmpty());
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
		
	}
		
//		List<Medicine>listm=new ArrayList<>();
//		listm.add(new Medicine("板蓝根",10,"1g/10g/盒","盒","冲泡","每次一袋","每日三次"));
//		listm.add(new Medicine("感冒冲剂",15,"3g/12g/袋","袋","冲泡","每次一袋","每日两次"));
//		listm.add(new Medicine("阿司匹林",20,"1g/15g/袋","袋","口服","每次一片","每日两次"));
//		listm.add(new Medicine("芬必得",30,"20g×1 瓶","瓶","口服","每次两片","每日三次"));
//		
//		YaoFang y1=new YaoFang("流行性感冒","个人");
//		y1.setListM(listm);
//		
//		Gson gson=new Gson();
//		String s=gson.toJson(y1);
//		System.out.println(s);
//		
//		YaoFang y2=gson.fromJson(s,YaoFang.class);
//		List<Medicine>lm=y2.getListM();
//		for(Medicine m:lm) {
//			System.out.println(m.getName());
//		}
//		System.out.println(y2);
		
		
		
		
	}	

