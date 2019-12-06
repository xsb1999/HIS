package application.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import logic.MyHashTable;

public class TestG {
	
	public static Map<String, String> read(){
		HashMap<String,String> hm=new HashMap<>();
		
		try {
			InputStream in=new FileInputStream("src\\Map");
			DataInputStream din=new DataInputStream(in);
			
			Gson gson=new Gson();
			String str=din.readUTF();
			//hm=gson.fromJson(str, HashMap.class);
			hm=gson.fromJson(str, new TypeToken<HashMap<String,String>>() {}.getType() );
			return hm;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	

	public static void main(String[] args)  {
		MyHashTable<String,Case>ht0=new MyHashTable<>();
		MyHashTable<String,Case>ht=ht0.read();
		
		Case c=new Case();
		//String str=JSON.toJSONString(c);
		ht.put("10001", c);
		
		//String d=ht.get("10001");
		
		//Case ca=JSON.parseObject(d,Case.class);
		
		//System.out.println(ca);
		
		
		
		
		
//		hm.put("a", "1");
//		
//		Gson gson=new Gson();
//		String s=gson.toJson(hm);
//		OutputStream out;
//		try {
//			out = new FileOutputStream("src/Map");
//			DataOutputStream dout=new DataOutputStream(out);
//			dout.writeUTF(s);
//			dout.close();
//		
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
	}

}
