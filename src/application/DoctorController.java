package application;

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

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import application.model.Case;
import application.model.Doctor;
import application.model.DoctorListOfPatient;
import application.model.Patient;
import application.model.TomJimKim;
import application.model.YaoFang;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DoctorController {
	@FXML
	private MenuItem menzhenbingli;

	@FXML
	private TextField Text1;
	
	@FXML
	private TextField Text2;
	
	@FXML
	private TextField Text3;
	
	@FXML
	private TextField Text4;
	
	@FXML
	private TextField Text5;
	
	@FXML
	private TextField Text6;
	
	@FXML
	private TextField Text7;
	
	@FXML
	private TextField Text8;
	@FXML
	private TextArea ListText1=new TextArea();
	
	@FXML
	private TextArea ListText2=new TextArea();
	
	@FXML
	private Label submitsuccess=new Label() ;
	
	@FXML
	private Label speciallabel=new Label();
	
	@FXML
	private Button b1;
	
	@FXML
	private Button b2;
	
	@FXML
	private Button b11;
	

	
	@FXML
	private Button chufangBut_;
	
	@FXML
	private TableView<Patient> table1=new TableView<>();
	
	@FXML
	private TableView<Patient> table11=new TableView<>();
	
	@FXML
	private TableColumn<Patient,String> id1=new TableColumn<>();
	
	@FXML
	private TableColumn<Patient,String> id11=new TableColumn<>();
	
	@FXML
	private TableColumn<Patient,String> name1=new TableColumn<>();
	
	@FXML
	private TableColumn<Patient,String> name11=new TableColumn<>();
	
	@FXML
	private TableColumn<Patient,String> age1=new TableColumn<>();
	
	@FXML
	private TableColumn<Patient,String> age11=new TableColumn<>();
	
	@FXML
	private TableView<Patient> table2=new TableView<>();
	@FXML
	private TableColumn<Patient,String> id2=new TableColumn<>();
	@FXML
	private TableColumn<Patient,String> name2=new TableColumn<>();
	@FXML
	private TableColumn<Patient,String> age2=new TableColumn<>();
	

	
	public void showToSeeListtom() {

		
	//切换到诊断界面	
		AnchorPane root;
		try {
			root = FXMLLoader.load(getClass().getResource("doctor/HuanZheLieBiao.fxml"));
			Scene s=new Scene(root);
			
			Stage primaryStage=(Stage) b1.getScene().getWindow();
			
			//primaryStage.hide();
			primaryStage.setScene(s);
			
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	

	
	public void initialize() {
		
	//医生患者列表（未诊）
		
		List<String>list=TomJimKim.read();
		String str=new String();
		int i=list.size();
		int c=1;
		for(String a:list) {
			if(c==i) {
			str=a;
			break;
			}
			c++;
		}
		
		List<Patient>listp=DoctorListOfPatient.getToSeeList(str);
		
		ObservableList<Patient>patientlistT1= FXCollections.observableArrayList();
		
		for(Patient p:listp) {

			patientlistT1.add(p);
		}
		
		id1.setCellValueFactory( new PropertyValueFactory<>("id"));
		name1.setCellValueFactory(new PropertyValueFactory<>("name"));
		age1.setCellValueFactory(new PropertyValueFactory<>("age"));
		table1.setItems(patientlistT1);
		
		
		table1.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue)->{if (newValue != null) {
			ListText1.setText("姓名:"+newValue.getName()+"   "+"病历号:"+newValue.getId()+"   "+"年龄:"+newValue.getAge()+"   "+"性别:"+newValue.getSex());
			speciallabel.setText(newValue.getId());
	       }
	    });
		
		
		
		//医生患者列表（已诊）
		List<Patient>listp1=DoctorListOfPatient.getHaveSeenList(str);
		
		ObservableList<Patient>patientlistT11= FXCollections.observableArrayList();
		
		for(Patient p:listp1) {

			patientlistT11.add(p);
		}
		
		id11.setCellValueFactory( new PropertyValueFactory<>("id"));
		name11.setCellValueFactory(new PropertyValueFactory<>("name"));
		age11.setCellValueFactory(new PropertyValueFactory<>("age"));
		table11.setItems(patientlistT11);
		
		
		table11.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue)->{if (newValue != null) {
			ListText1.setText("姓名:"+newValue.getName()+"   "+"病历号:"+newValue.getId()+"   "+"年龄:"+newValue.getAge()+"   "+"性别:"+newValue.getSex());
			
	       }
	    });
	}	

		
	
	
	
	//提交诊断报告（病历信息）,将病历信息添加到病历类中（Case）
	
	public void submit() {
		
		submitsuccess.setText("提交成功！");
		
		List<String>msg=new ArrayList<>();
		msg.add(Text1.getText());
		msg.add(Text2.getText());
		msg.add(Text3.getText());
		msg.add(Text4.getText());
		msg.add(Text5.getText());
		msg.add(Text6.getText());
		msg.add(Text7.getText());
		msg.add(Text8.getText());
		
		
		List<String>list=TomJimKim.read();
		String str=new String();
		int i=list.size();
		int c=1;
		for(String a:list) {
			if(c==i) {
			str=a;
			break;
			}
			c++;
		}
		
		
		InputStream in;
		try {
			in = new FileInputStream("src/Cases");
			DataInputStream din=new DataInputStream(in);
			String str1=din.readUTF();
			Gson gson=new Gson();
			List<Case>listc=gson.fromJson(str1,new TypeToken<List<Case>>() {}.getType() );
			
			InputStream in1=new FileInputStream("src/Doctors");
			DataInputStream din1=new DataInputStream(in1);
			String str11=din1.readUTF();
			List<Doctor>listd=JSON.parseArray(str11, Doctor.class);
			
			for(Case c1:listc) {
				if(speciallabel.getText().equals(c1.getId())) {
					c1.setBingliXinXi(msg);
					Patient p=c1.getPatient();
					for(Doctor d:listd) {
						if(d.getName().equals(str)) {
							
							List<Patient>listp=d.getToSee();
							 for(Patient pa:listp) {
								 if(pa.getId().equals(p.getId()))
								 {
									 listp.remove(pa);
									 break;
								 }
								 }
							 d.setToSee(listp);
							 

							d.getHaveSeen().add(p);
							 System.out.println("...");
							break;
						}
					}
					
					
					break;
				}
			}
			
			String str111=JSON.toJSONString(listd);
			OutputStream out1=new FileOutputStream("src/Doctors");
			DataOutputStream dout1=new DataOutputStream(out1);
			dout1.writeUTF(str111);
			dout1.close();
			
			
			Gson g=new Gson(); 
			String s=g.toJson(listc);
			OutputStream out=new FileOutputStream("src/Cases");
			DataOutputStream dout=new DataOutputStream(out);
			dout.writeUTF(s);
			dout.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	
		
	}
	
	//清空
	public void qingkong() {
		Text1.setText(null);
		Text2.setText(null);
		Text3.setText(null);
		Text4.setText(null);
		Text5.setText(null);
		Text6.setText(null);
		Text7.setText(null);
		Text8.setText(null);
	}
	
	
	//切换到tom成药处方界面
	
	public void qiehuan1() {
		AnchorPane root;
		try {
			root = FXMLLoader.load(getClass().getResource("kaiyao/KaiYao.fxml"));
			Scene s=new Scene(root);
			
			Stage primaryStage=(Stage) b11.getScene().getWindow();
			
			//primaryStage.hide();
			primaryStage.setScene(s);
			primaryStage.show();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	//刷新Tom
	public void shuaxin() {
		
		AnchorPane root;
		try {
			root = FXMLLoader.load(getClass().getResource("doctor/HuanZheLieBiao.fxml"));
			Scene s=new Scene(root);
			
			Stage primaryStage=(Stage) b11.getScene().getWindow();
			
			//primaryStage.hide();
			primaryStage.setScene(s);
			
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
