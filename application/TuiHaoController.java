package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import application.model.Case;
import application.model.Doctor;
import application.model.Patient;
import javafx.event.ActionEvent;

import javafx.scene.control.MenuItem;

import javafx.scene.control.Label;

public class TuiHaoController {
	@FXML
	private MenuItem GuaHao;
	@FXML
	private Button but_;
	@FXML
	private Button chaxun;
	@FXML
	private TextField id;
	@FXML
	private Button tuihao;
	@FXML
	private TextField xingming;
	@FXML
	private TextField yisheng;
	@FXML
	private TextField nianling;
	@FXML
	private Label lab;
	@FXML
	private Label thcg;

	
	//�˳���ť
	public void exit() {
		
		Stage primaryStage=(Stage) chaxun.getScene().getWindow();
		primaryStage.close();

	}
	
	
	//�л����ҺŽ���
		public void guahaoYeMian() {
			AnchorPane root;
			try {
				root = FXMLLoader.load(getClass().getResource("guahao/GuaHaoXinXi.fxml"));
				Scene scene=new Scene(root);
				Stage primaryStage=(Stage) tuihao.getScene().getWindow();
				primaryStage.hide();
			    primaryStage.setScene(scene);
				primaryStage.show();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		//�л������ѽ���
			public void jiaofeiyemian() {
				AnchorPane root;
				try {
					root = FXMLLoader.load(getClass().getResource("jiaofei/JiaoFei1.fxml"));
					
					Scene scene=new Scene(root);
					
					Stage primaryStage=(Stage) tuihao.getScene().getWindow();
					primaryStage.hide();
				    primaryStage.setScene(scene);
					primaryStage.show();
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
	
	
	
			//�л����˺Ž���
			public void tuihaoYeMian() {
				AnchorPane root;
				try {
					root = FXMLLoader.load(getClass().getResource("tuihao/TuiHao.fxml"));
					Scene scene=new Scene(root);
					Stage primaryStage=(Stage) tuihao.getScene().getWindow();
					primaryStage.hide();
				    primaryStage.setScene(scene);
					primaryStage.show();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
			
			
			//��ѯ��ť
			public void chaxun() {
				xingming.setText("");
				nianling.setText("");
				yisheng.setText("");
				thcg.setText("");
				
				boolean b=false;
				String str=id.getText();
				List<Case>listc=Case.readCase();
				for(Case c:listc) {
					if(c.getId().equals(str)) {
						b=true;
						if(c.isYijingfayao()==true) {
							lab.setText("ҽ���Ѿ���Ͽ�ҩ��ϣ������˺ţ�");
							break;
						}
						if(c.isTuihao()==true) {
							lab.setText("�����˺ţ������ظ��˺ţ�");
							break;
						}
						
						xingming.setText(c.getPatient().getName());
						nianling.setText(c.getPatient().getAge());
						yisheng.setText(c.getDoctor().getName());
						lab.setText("");
						
						break;
					}
				}
				
				if(b==false) {
					lab.setText("δ��ѯ�������");
				}
			}
			
			
			
			
			
			//�˺Ű�ť
			public void tuihao() {
			
				if(xingming.getText().equals("")||id.getText().equals("")) {
					thcg.setText("�˺�ʧ�ܣ�");
				}
				
				
				
				else {
					
					List<Patient>listp=new ArrayList<>();
					List<Patient>listp1=new ArrayList<>();
					List<Doctor>listd=new ArrayList<>();
					
					String str=id.getText();
					List<Case>listc=Case.readCase();
					for(Case c:listc) {
						if(c.getId().equals(str)) {
							c.setTuihao(true);
							
							//�Ӳ����б����Ƴ�
							listp1=Patient.read();
							for(Patient p:listp1) {
								if(p.getId().equals(str)) {
									listp1.remove(p);
									break;
								}
							}

							//��ҽ����δ���б����Ƴ�
							Doctor d=c.getDoctor();
							listd=Doctor.readD();
							for(Doctor d1:listd) {
								
								////���ﲻ��дd1.equals(d),����ֱ�ӱȽ�����Doctor���󣡣���
								if(d1.getName().equals(d.getName())) {
									
									listp=d1.getToSee();
									for(Patient p:listp) {
										if(p.getId().equals(str)) {	
											listp.remove(p);
											d1.setToSee(listp);
											break;
										}
									}
									break;
								}
								
							}
							
							
							
						}
					}
					
					
					//����
					OutputStream out;
					try {
						out = new FileOutputStream("src/Doctors");
						DataOutputStream dout=new DataOutputStream(out);
						Gson g=new Gson();
						String str1=g.toJson(listd);
						dout.writeUTF(str1);
						dout.close();
						
						OutputStream out1 = new FileOutputStream("src/Patients");
						DataOutputStream dout1=new DataOutputStream(out1);
						Gson g1=new Gson();
						String str11=g1.toJson(listp1);
						dout1.writeUTF(str11);
						dout1.close();
						
						out = new FileOutputStream("src/Cases");
						DataOutputStream dout11=new DataOutputStream(out);
						Gson gson=new Gson();
						String str111=gson.toJson(listc);
						dout11.writeUTF(str111);
						dout11.close();
						
						
						
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
						
					thcg.setText("�˺ųɹ���");
					
				}

			}


	
}
