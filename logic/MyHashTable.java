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

public class MyHashTable<K,V> {

	@SuppressWarnings("unchecked")
	private V[] arr=(V[])new Object[1000];

	private List<Counts<K>>keyList=new ArrayList<>();
	
	

	public List<Counts<K>> getKeyList() {
		return keyList;
	}

	public void setKeyList(List<Counts<K>> keyList) {
		this.keyList = keyList;
	}

	public V[] getArr() {
		return arr;
	}

	public void setArr(V[] arr) {
		this.arr = arr;
	}
	
	
	
	
	
	
	public  MyHashTable<K,V> read() {
		
		MyHashTable<K,V> ht=new MyHashTable<K,V>();
		
		try {
			InputStream in=new FileInputStream("src\\MyHashTable");
			DataInputStream din=new DataInputStream(in);
			Gson gson=new Gson();
			String str=din.readUTF();
			ht=gson.fromJson(str,new TypeToken<MyHashTable<K,V>>() {}.getType() );
			
			//ht=gson.fromJson(str, MyHashTable.class);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ht;
		
	}
	
	
	
	
	
	public void put(K k,V v) {

		boolean flag=true;
		
		MyHashTable<K,V> ht0=new MyHashTable<>();
		MyHashTable<K,V> ht=ht0.read();
		List<Counts<K>>keyList=ht.getKeyList();

		
		for(Counts<K> keys:keyList) {
			
			if(keys.getKeys().toString().equals(k.toString())) {
				
				flag=false;
				System.out.println("不能输入相同的key");
				break;
			}			
		}
		
		if(flag) {
			
			Counts<K> c=new Counts<K>();
			c.setKeys(k);
			
			String sk=k.toString();
			if(sk.endsWith("T")||sk.endsWith("U")) {
				
				if(sk.endsWith("UT")) {
					sk=sk.substring(0, sk.length()-2);
				}
				else {
					sk=sk.substring(0, sk.length()-1);
				}
				
			}
			

				int a=Integer.parseInt(sk);
				int b=a%1000;
				int i=0;
				
				
				//线性探查法找下一个地址		
				while(ht.getArr()[b]!=null) {
					b=b+1;
					i++;        //记录改变的次数    
					if(b>=1000) {
						break;
					}
				}
					
				
					if(b>=1000) {
						b=b/10;
					}
					
					
					
					ht.getArr()[b]=v;
					c.setNumber(i);
					
					keyList.add(c);
			
			
			
			
			
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
	
	

	
	
	public  V get(Object k) {
		
		boolean flag=false;
		MyHashTable<K,V> ht0=new MyHashTable<K,V>();
		MyHashTable<K,V> ht=ht0.read();
		
		List<Counts<K>>listK=ht.getKeyList();
		
		int i=0;
		
		for(Counts<K> key:listK) {
	
			if(key.getKeys().equals(k)) {
				i=key.getNumber();
				flag=true;
				break;
			}	
		}
		
		
		if(!flag) {
			return null;
		}
		
		
		String sk=k.toString();
		if(sk.endsWith("T")||sk.endsWith("U")) {
			
			if(sk.endsWith("UT")) {
				sk=sk.substring(0, sk.length()-2);
			}
			else {
				sk=sk.substring(0, sk.length()-1);
			}
			
		}
		
		
		int a=Integer.parseInt(sk);
		int b=a%1000;
		
		if(b+i>=1000) {
			b=(b+i)/10-i;
		}
		

		V v=ht.getArr()[b+i];
		
		
		return v;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public void remove(K k) {
		
		MyHashTable<K,V> ht0=new MyHashTable<K,V>();
		MyHashTable<K,V> ht=ht0.read();
		List<Counts<K>>listK=ht.getKeyList();
		
		int i=0;
		for(Counts<K> k1:listK) {
			if(k1.getKeys().equals(k)) {
				
				String sk=k.toString();
				if(sk.endsWith("T")||sk.endsWith("U")) {
					
					if(sk.endsWith("UT")) {
						sk=sk.substring(0, sk.length()-2);
					}
					else {
						sk=sk.substring(0, sk.length()-1);
					}
					
				}
				
				
				int a=Integer.parseInt(sk);
				int b=a%1000;
				ht.getArr()[b + k1.getNumber()]=null;
				
				listK.remove(i);
				break;
			}
			i++;
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
	
	
	
	



	public static void main(String[] args) {
		
		
		MyHashTable ht=new MyHashTable();
		
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
