package application;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.google.gson.Gson;

import application.model.Doctor;
import application.model.SxHfF4;
import application.model.TomJimKim;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RegisterController{
	@FXML
	private Label label1;
	@FXML
	private TextArea text1;
	@FXML
	private TextArea text2;
	
	

	@FXML
	private Label label2;
	@FXML
	private Label label3;
	@FXML
	private Button button1;
	@FXML
	private Button exit1;
	@FXML
	private TableView<Doctor> doctorTable;
	 @FXML
	    private TableColumn<Doctor, String> firstNameColumn;
	    @FXML
	    private TableColumn<Doctor, String> lastNameColumn;



	
	//排班
	public void paiban() {
		try {
			AnchorPane anchor2=FXMLLoader.load(getClass().getResource("PaiBan.fxml"));
			Stage s2=new Stage();
			s2.setScene(new Scene(anchor2));
			s2.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	 
	
	
	//登录
	public void login() {
		
		//清空shuaxinhuifuL4文件
		SxHfF4.clear();
		
		
		String a=text1.getText();
		String b=text2.getText();
		if(a.equals("ghy")&&b.equals("ghy123")) {	
			try {
				AnchorPane root = FXMLLoader.load(getClass().getResource("guahao/Ghy.fxml"));
				Scene s=new Scene(root);
				Stage primaryStage=new Stage();
				primaryStage.setScene(s);
				primaryStage.show();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			label3.setText("");
			
		}
		
		
		
		else if(a.equals("root")&&b.equals("root123")) {
			try {
				AnchorPane root = FXMLLoader.load(getClass().getResource("fayao/Fayao.fxml"));
				Scene s=new Scene(root);
				Stage primaryStage=new Stage();
				primaryStage.setScene(s);
				primaryStage.show();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			label3.setText("");
		}
		
		
		else if((a.equals("tom")&&b.equals("tom123"))||(a.equals("jim")&&b.equals("jim123"))||a.equals("kim")&&b.equals("kim123")||a.equals("Jordan")&&b.equals("Jordan123")||a.equals("bob")&&b.equals("bob123")){
			
			TomJimKim.clear();
			List<String>list=TomJimKim.read();
			list.add(text1.getText());
			
			Gson g=new Gson();
			String s1=g.toJson(list);
			try {
				OutputStream out=new FileOutputStream("src\\tomjimkim");
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
			
			
			
			
			try {
				AnchorPane root = FXMLLoader.load(getClass().getResource("doctor/DoctorYeMian.fxml"));
				Scene s=new Scene(root);
				Stage primaryStage=new Stage();
				primaryStage.setScene(s);
				primaryStage.show();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
		

		else {
			label3.setText("用户名或密码错误，请重新输入！");
		}
	}
	
	
	
	
	//退出登录
	
	public void exit1() {
		Stage s1=(Stage) exit1.getScene().getWindow();
		s1.hide();	
	}
	

	
	
	
	
	
	
	
}
