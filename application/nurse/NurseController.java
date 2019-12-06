package application.nurse;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import application.model.Case;
import application.model.Doctor;
import application.model.DoctorListOfPatient;
import application.model.Patient;
import application.model.TomJimKim;
import application.model.Urgent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.MyHashTable;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class NurseController {
	@FXML
	private TextField tf;
	@FXML
	private TextField ListText;
	@FXML
	private Label speciallabel;
	@FXML
	private Label cg;
	@FXML
	private TableView<Patient> TT=new TableView<>();
	@FXML
	private TableColumn<Patient,String> c1=new TableColumn<>();
	@FXML
	private TableColumn<Patient,String> c2=new TableColumn<>();
	@FXML
	private TableColumn<Patient,String> c3=new TableColumn<>();
	@FXML
	private Button jiaji;
	@FXML
	private Button chaxun;

	
	
	
//	public void initialize() {
//		String str="";
//		
//		List<Patient>listp=DoctorListOfPatient.getToSeeList(str);
//		
//		ObservableList<Patient>patientlist= FXCollections.observableArrayList();
//		
//		for(Patient p:listp) {
//			patientlist.add(p);
//		}
//		
//		c1.setCellValueFactory( new PropertyValueFactory<>("id"));
//		c2.setCellValueFactory(new PropertyValueFactory<>("name"));
//		c3.setCellValueFactory(new PropertyValueFactory<>("age"));
//		TT.setItems(patientlist);
//		
//			TT.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue)->{if (newValue != null) {
//			ListText.setText("姓名:"+newValue.getName()+"   "+"病历号:"+newValue.getId()+"   "+"年龄:"+newValue.getAge()+"   "+"性别:"+newValue.getSex());
//			speciallabel.setText(newValue.getId());
//	       }
//	    });
//		
//		
//			
//		
//	}
	
	
	
	
	
	public void chaxun() {
		List<Patient>listp=new ArrayList<>();
		
		String str=tf.getText();
		List<Doctor>listd=Doctor.readD();
		for(Doctor d:listd) {
			if(d.getName().equals(str)) {
				listp=d.getToSee();
				break;
			}
		}
		
		
		ObservableList<Patient>patientlist= FXCollections.observableArrayList();
		
		for(Patient p:listp) {
			patientlist.add(p);
		}
		
		c1.setCellValueFactory( new PropertyValueFactory<>("id"));
		c2.setCellValueFactory(new PropertyValueFactory<>("name"));
		c3.setCellValueFactory(new PropertyValueFactory<>("age"));
		TT.setItems(patientlist);
		
			TT.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue)->{if (newValue != null) {
			ListText.setText("姓名:"+newValue.getName()+"   "+"病历号:"+newValue.getId()+"   "+"年龄:"+newValue.getAge()+"   "+"性别:"+newValue.getSex());
			speciallabel.setText(newValue.getId());
	       }
	    });
			
	}
	
	
	
	
	
	
	public void jiaji() {
		MyHashTable<String,String>ht0=new MyHashTable<String,String>();
		MyHashTable<String,String>ht=ht0.read();
		
		String weight=Urgent.readw();
		
		String str=speciallabel.getText();
		List<Case>listc=Case.readCase();
		List<Patient>listp=Patient.read();
		List<Doctor>listd=Doctor.readD();
		Doctor doc=new Doctor();
		Patient up=new Patient();
		Case case1=new Case();
		
		for(Patient p:listp) {
			if(p.getId().equals(str)) {
				p.setId(str+"U");
				p.setUrgent(true);
				p.setWeight(weight);
							
				List<String> lists=p.getCaseId();
				
				for(int i=0;i<lists.size();i++) {
					
					if(lists.get(i).equals(str)) {
						lists.set(i, str+"U");
						break;
					}
					
				}
				
				up=p;
				
				// 每次挂号后给这个病人赋的权值比上一个人多10					
				int a1=Integer.parseInt(weight)+10;
				String newWeight=a1+"";
				
				Urgent.qingkong(newWeight);
				
				break;
			}
		}
		
		
		
		for(Doctor d:listd) {
			if(d.getName().equals(tf.getText())) {
				List<Patient>listp1=d.getToSee();
				
				//两个对象不能直接写等于来将一个对象赋值为另一个对象，例如：不能写  pp=up				
				for(Patient pp:listp1) {
					if(pp.getId().equals(str)) {			
						
						pp.setId(str+"U");
						pp.setUrgent(true);
						pp.setWeight(up.getWeight());
						pp.setCaseId(up.getCaseId());
						
					
					}
					
				}
				
				doc=d;
			}
		}
		
		
		for(Case c:listc) {
			if(c.getId().equals(str)) {
				
				c.setId(str+"U");
				c.setPatient(up);
				c.setDoctor(doc);
				case1=c;
				
				String sc=JSON.toJSONString(c);
				
				//更新id和Case的Map				
				ht.put(str+"U", sc);
				ht.remove(str);
				
						
				break;
		
			}
		}
		
		
		
		
		
		
		
		//更新文件
		
		try {
					
			Gson g3=new Gson();
			String s=g3.toJson(listd);
			OutputStream out1=new FileOutputStream("src/Doctors");
			DataOutputStream dout1=new DataOutputStream(out1);
			dout1.writeUTF(s);
			dout1.close();
			
			
			Gson g2=new Gson();
			String s4=g2.toJson(listp);
			OutputStream out4=new FileOutputStream("src/Patients");
			DataOutputStream dout4=new DataOutputStream(out4);
			dout4.writeUTF(s4);
			dout4.close();
			
			
			Gson g1=new Gson();
			String s3=g1.toJson(listc);
			OutputStream out3=new FileOutputStream("src/Cases");
			DataOutputStream dout3=new DataOutputStream(out3);
			dout3.writeUTF(s3);
			dout3.close();
			
			
	
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cg.setText("加急成功！");
		
	}
	
	
	
	//清空列表	
	public void qingkong() {
		
		AnchorPane root;
		try {
			root = FXMLLoader.load(getClass().getResource("Nurse.fxml"));
			Scene s=new Scene(root);
			
			Stage primaryStage=(Stage) jiaji.getScene().getWindow();
			
			//primaryStage.hide();
			primaryStage.setScene(s);
			
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	

}
