package logic;

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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MyHashTable0{

	private Object[] arr=new Object[1000];
	private List<Object>keyList=new ArrayList<>();
	

	public Object[] getArr() {
		return arr;
	}
	public void setArr(Object[] arr) {
		this.arr = arr;
	}
	


	
	public List<Object> getKeyList() {
		return keyList;
	}
	public void setKeyList(List<Object> keyList) {
		this.keyList = keyList;
	}
	public static MyHashTable0 read() {
		
		MyHashTable0 ht=new MyHashTable0();
		
		try {
			InputStream in=new FileInputStream("src\\MyHashTable");
			DataInputStream din=new DataInputStream(in);
			Gson gson=new Gson();
			String str=din.readUTF();
			ht=gson.fromJson(str,new TypeToken<MyHashTable0>() {}.getType() );
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ht;
		
	}
	
	
	
	
	
	
	




	public void put(Object k,Object v) {

		boolean flag=true;
		MyHashTable0 ht=MyHashTable0.read();
		List<Object>keyList=ht.getKeyList();
		
		for(Object keys:keyList) {
			if(keys.toString().equals(k.toString())) {
				flag=false;
				System.out.println("不能输入相同的key");
				break;
			}
			
		}
		
		if(flag) {
			
			int a=Integer.parseInt(k.toString());
			int b=a/100;
			
			if(ht.getArr()[b]!=null) {
				ht.getArr()[b/10]=v;
				
			}	
			
			else{
				ht.getArr()[b]= v;
			}
			
		}
		
		
		
		OutputStream out;
		try {
			out = new FileOutputStream("src/MyHashTable");
			DataOutputStream dout=new DataOutputStream(out);
			Gson gson=new Gson();
			String str=gson.toJson(ht);
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
	
	
	
	
	
	public static Object get(Object k) {
		
		MyHashTable0 ht=MyHashTable0.read();
		int a=Integer.parseInt(k.toString());
		Object v=ht.getArr()[a];
		return v;
		
	}
	
	
	
	
	
	
	
	
	public static void main(String[]args)
	{	
		MyHashTable0 ht=new MyHashTable0();
		
		OutputStream out;
		try {
			out = new FileOutputStream("src/MyHashTable");
			DataOutputStream dout=new DataOutputStream(out);
			Gson gson=new Gson();
			String str=gson.toJson(ht);
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
