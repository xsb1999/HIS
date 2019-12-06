package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
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

import application.model.Medicine;
import application.model.Name;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class ShuLiangController {
	@FXML
	private TextField T;
	@FXML
	private Button B;
	@FXML
	private Label L;
	@FXML
	private Label lll;

	public void initialize() {
		
		//在lll label上显示当前是什么药
		List<String>lists=Name.read();
		String sss=new String();
		int i=lists.size();
		int c=1;
		for(String a:lists) {
			if(c==i) {
			sss=a;
			break;
			}
			c++;
		}	
		lll.setText(sss);
	}
	
	
	
	//填写药品数量确认键
	
		public void tianyao() {
			
		//更新(清空)name文件
			File f=new File("src\\name");
			f.delete();
			List<String>list=new ArrayList<>();
			
			Gson gson=new Gson();
			String str=gson.toJson(list);
			try {
				OutputStream out=new FileOutputStream("src\\name");
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
		
			
			
			//更改该药的数量属性
			String s=T.getText();
			int b=Integer.parseInt(s);
			
			List<Medicine>list1=new ArrayList<>();
			List<Medicine>listt=new ArrayList<>();
			InputStream in;
			try {
				in = new FileInputStream("src\\yp");
				DataInputStream din=new DataInputStream(in);
				String str1=din.readUTF();
				Gson gson1=new Gson();
				list1=gson1.fromJson(str1,new TypeToken<List<Medicine>>() {}.getType() );
				
				in = new FileInputStream("src\\Medicine");
				DataInputStream din1=new DataInputStream(in);
				String str11=din1.readUTF();
				Gson gson11=new Gson();
				listt=gson11.fromJson(str11,new TypeToken<List<Medicine>>() {}.getType() );
				
				for(Medicine m:listt) {
					if(m.getName().equals(lll.getText())) {
						m.setShuliang(b);
						list1.add(m);
						break;
					}
				}
			
				
				//更新yp文件
				String ss=gson11.toJson(list1);
				OutputStream out=new FileOutputStream("src\\yp");
				DataOutputStream dout=new DataOutputStream(out);
				dout.writeUTF(ss);
				dout.close();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			//回到增方界面
			AnchorPane root;
			try {
				
			
				root = FXMLLoader.load(getClass().getResource("kaiyao/ZengFang.fxml"));
				Scene s1=new Scene(root);
				
				Stage primaryStage=(Stage) B.getScene().getWindow();

				primaryStage.setScene(s1);
				
				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	
}
