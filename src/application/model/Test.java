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
			in = new FileInputStream("D:\\java����eclipse��\\HIS\\src\\YaoFang");
			DataInputStream din=new DataInputStream(in);
			String s=din.readUTF();
			
			//Gson����Ƕ��list��nb��������������������������
			
			Gson gson=new Gson();
			List<YaoFang> list = gson.fromJson(s, new TypeToken<List<YaoFang>>() {}.getType());
			
			
			for(YaoFang m:list) {
				System.out.println(m.getName());
				
				
				
				if(m.getName().equals("�����Ը�ð")) {
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
//		listm.add(new Medicine("������",10,"1g/10g/��","��","����","ÿ��һ��","ÿ������"));
//		listm.add(new Medicine("��ð���",15,"3g/12g/��","��","����","ÿ��һ��","ÿ������"));
//		listm.add(new Medicine("��˾ƥ��",20,"1g/15g/��","��","�ڷ�","ÿ��һƬ","ÿ������"));
//		listm.add(new Medicine("�ұص�",30,"20g��1 ƿ","ƿ","�ڷ�","ÿ����Ƭ","ÿ������"));
//		
//		YaoFang y1=new YaoFang("�����Ը�ð","����");
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

