package application.model;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GetYaoFang {

	
	
	public static List<YaoFang> getYaoFang(){
		List<YaoFang>list=new ArrayList<>();
		
		InputStream in;
		try {
			in = new FileInputStream("src/YaoFang");
			DataInputStream din=new DataInputStream(in);
			String str=din.readUTF();
			Gson gosn=new Gson();
			List<YaoFang>listy=gosn.fromJson(str,new TypeToken<List<YaoFang>>() {}.getType() );
			list=listy;
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	
	
	
	public static List<YaoFang> getYaoFang2(){
		
		List<YaoFang>list=new ArrayList<>();
		
		InputStream in;
		try {
			in = new FileInputStream("src/yf");
			DataInputStream din=new DataInputStream(in);
			String str=din.readUTF();
			Gson gosn=new Gson();
			List<YaoFang>listy=gosn.fromJson(str,new TypeToken<List<YaoFang>>() {}.getType() );
			list=listy;
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
		
		
	}
	
	
	
	
	
	
}
