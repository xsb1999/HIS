package application;

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

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import application.model.GetYaoFang;
import application.model.Medicine;
import application.model.Name;
import application.model.Patient;
import application.model.YaoFang;
import application.model.Yf;
import application.model.Yp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ZengFangController {
	@FXML
	private TableView<Medicine> T1=new TableView<>();
	@FXML
	private TableView<Medicine> T2=new TableView<>();
	@FXML
	private TableColumn<Medicine,String> c1=new TableColumn<>();
	@FXML
	private TableColumn<Medicine,String> c2=new TableColumn<>();
	@FXML
	private TableColumn<Medicine,String> c3=new TableColumn<>();
	@FXML
	private TableColumn<Medicine,String> c4=new TableColumn<>();
	@FXML
	private TextField T;
	@FXML
	private TextField TT;
	@FXML
	private TextField t1;
	@FXML
	private Button B;
	@FXML
	private Button BB;
	@FXML
	private Button B1;
	@FXML
	private Button B2;
	@FXML
	private Label L;
	@FXML
	private Label LL;
	@FXML
	private Label lab=new Label();
	@FXML
	private Label Lab=new Label();
	@FXML
	private Label lll=new Label();
	@FXML
	private Label L5=new Label();
	
	public void initialize() {
		
		List<Medicine>listm1=new ArrayList<>();
		ObservableList<Medicine>MedicinelistT2= FXCollections.observableArrayList();
		InputStream in2;
		try {
			in2 = new FileInputStream("src\\yp");
			DataInputStream din=new DataInputStream(in2);
			String str=din.readUTF();
			Gson gson=new Gson();
			listm1=gson.fromJson(str,new TypeToken<List<Medicine>>() {}.getType() );
			for(Medicine m:listm1) {
				MedicinelistT2.add(m);
			}
			
			
			c3.setCellValueFactory( new PropertyValueFactory<>("name"));
			c4.setCellValueFactory( new PropertyValueFactory<>("shuliang"));
			T2.setItems(MedicinelistT2);
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		List<Medicine>listm=new ArrayList<>();
		
		InputStream in;
		try {
			in = new FileInputStream("src\\Medicine");
			DataInputStream din=new DataInputStream(in);
			String s=din.readUTF();
			listm=JSON.parseArray(s,Medicine.class);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ObservableList<Medicine>MedicinelistT1= FXCollections.observableArrayList();
		for(Medicine m:listm) {
			MedicinelistT1.add(m);
		}
		c1.setCellValueFactory( new PropertyValueFactory<>("name"));
		c2.setCellValueFactory( new PropertyValueFactory<>("guige"));
		T1.setItems(MedicinelistT1);
		
		T1.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue)->{if (newValue != null) {
			
	//一个药方中最多只能加五种药品		
			List<Medicine>list=Yp.read();
			int i=0;
			for(Medicine m:list) {
				i++;
			}
			
			if(i<5) {
			List<String>lists=Name.read();
			lists.add(newValue.getName());
			
		//更新name文件	
			Gson gson=new Gson();
			String str=gson.toJson(lists);
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
			
			
			shuliang();
			}	
			
			else {
				L5.setText("一个药方中最多只能添加五种药品！");
			}
			
	       }
	    });
		
	}
	
	
	//弹出药品数量窗口
	public void shuliang() {
		AnchorPane root;
		try {
			root = FXMLLoader.load(getClass().getResource("kaiyao/ShuLiang.fxml"));
			Scene s1=new Scene(root);
			
			Stage primaryStage=(Stage) B1.getScene().getWindow();
			
			
			primaryStage.setScene(s1);
			
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	public void qingkongYiXuan() {
		
		//清空已选药品列表
		File f=new File("src\\yp");
		f.delete();
		List<Medicine>listm=new ArrayList<>();
		
		Gson gson=new Gson();
		String str=gson.toJson(listm);
		try {
			OutputStream out=new FileOutputStream("src\\yp");
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
		
		//刷新ZengFang界面（清除完毕的）
		AnchorPane root;
		try {
			
		
			root = FXMLLoader.load(getClass().getResource("kaiyao/ZengFang.fxml"));
			Scene s1=new Scene(root);
			
			Stage primaryStage=(Stage) B1.getScene().getWindow();

			primaryStage.setScene(s1);
			
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

	
	//确认添加按钮
	public void qingkongYP() {
	
		
		if(t1.getText().equals("")) {
			Lab.setText("请输入药方名");
		}
		
		else {
		//新建一个药方，并将其加到YaoFang文件中
		List<Medicine>list=Yp.read();
			double sum=0;
			for(Medicine m:list) {
					sum+=m.getPrice()*m.getShuliang();
					//m.setTotalamount(m.getTotalamount()-m.getShuliang());
				}
			
		String s=t1.getText();
		YaoFang y=new YaoFang(s,"个人","暂存",sum);
		y.setListM(list);
		
		List<YaoFang>listy=GetYaoFang.getYaoFang();
		//List<YaoFang>listy1=Yf.read();
		listy.add(y);
		//listy1.add(y);
		
		Gson g=new Gson();
		String s1=g.toJson(listy);
		//String s2=g.toJson(listy1);
		try {
			OutputStream out=new FileOutputStream("src\\YaoFang");
			DataOutputStream dout=new DataOutputStream(out);
			dout.writeUTF(s1);
			dout.close();
			
//			OutputStream out1=new FileOutputStream("src\\yf");
//			DataOutputStream dout1=new DataOutputStream(out1);
//			dout1.writeUTF(s2);
//			dout1.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		//清空yp文件（因为已经加完药品到药方中了）
		File f=new File("src\\yp");
		f.delete();
		List<Medicine>listm=new ArrayList<>();
		
		Gson gson=new Gson();
		String str=gson.toJson(listm);
		try {
			OutputStream out=new FileOutputStream("src\\yp");
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
		
	
		Lab.setText("增方成功！");
		
		}

		
	}
	
	
	
	
	
	//删方
	public void ShanFang() {
		
		if(TT.getText().equals("")) {
			LL.setText("请输入药方名称！");
		}
		else {
		boolean a=false;
		List<YaoFang>listy=GetYaoFang.getYaoFang();
		
		for(YaoFang y:listy) {
			if(y.getName().equals(TT.getText())) {
				listy.remove(y);
				a=true;
				break;
			}
		}
		
		
		
		if(a==true) {
		Gson g=new Gson();
		String s1=g.toJson(listy);
		try {
			OutputStream out=new FileOutputStream("src\\YaoFang");
			DataOutputStream dout=new DataOutputStream(out);
			dout.writeUTF(s1);
			dout.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		LL.setText("删方成功！");
		}
		else {
			LL.setText("没有此药方！");
		}
		
	}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
		
		
		
		
		
		
		
		
		
		
	
	

	
	

