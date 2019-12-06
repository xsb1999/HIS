package application.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CaseIdHashMap1 {

	
	
	public static Map<String, Case> readHM(){
		
		HashMap<String,Case> hm=new HashMap<>();
		
		try {
			InputStream in=new FileInputStream("src\\CaseIdHashMap");
			DataInputStream din=new DataInputStream(in);
							
				String str=din.readUTF();
				hm=JSON.parseObject(str, new TypeToken<HashMap<String,Case>>() {}.getType() );
				
				
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
//		Reader r;
//		try {
//			r = new FileReader("src/CaseIdHashMap");
//			BufferedReader br=new BufferedReader(r);
//			String line=br.readLine();
//		
//			
//			
//			hm=JSON.parseObject(line, new TypeToken<HashMap<String,Case>>() {}.getType() );
//			
//			
//					
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		

		return hm;
		
		
	
		
		
		
		

		
		

//		FileInputStream fis=null;
//		ObjectInputStream ois=null;
//	    try {
//			fis=new FileInputStream("src/CaseIdHashMap");
//			ois=new ObjectInputStream(fis);
//			hm=(HashMap<String, Case>)ois.readObject();
//			return hm;
//			
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
		
	}

	
	public static void main(String[] args) {
		
		Map<String,Case> hm=new HashMap<>();
//		Case c=new Case("tom");
//		c.setId("ppp");
		Case c=new Case();
		
		
		hm.put("a",c);
		
		
//		try {
//			Writer fw=new FileWriter("src/CaseIdHashMap");
//			BufferedWriter bw=new BufferedWriter(fw);
//			String str=JSON.toJSONString(hm);
//			
//			bw.write(str);
//
//			bw.close();
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	
		String str=JSON.toJSONString(hm);
		
		try {
			OutputStream out = new FileOutputStream("src/CaseIdHashMap");
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
		
		
	}

}
