package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import application.model.Bingzhong;
import application.model.Case;
import application.model.Certificate;
import application.model.DiseaseType;
import application.model.Diseases;
import application.model.Doctor;
import application.model.DoctorListOfPatient;
import application.model.Patient;
import application.model.TomJimKim;
import application.model.Urgent;
import application.model.Weight;
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
import logic.MyHashTable;
import logic.MyHeap;

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
	private TableView<DiseaseType> T0=new TableView<>();
	
	@FXML
	private TableView<Patient> table1=new TableView<>();
	
	@FXML
	private TableView<Patient> table11=new TableView<>();
	@FXML
	private TableColumn<DiseaseType,String> C0=new TableColumn<>();

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
	

	//退出按钮
	public void exit() {
		
		Stage primaryStage=(Stage) b11.getScene().getWindow();
		primaryStage.close();

	}
	
	
	
	public void showChaXun() {
		
		//切换到病人信息查询界面	
				AnchorPane root;
				try {
					root = FXMLLoader.load(getClass().getResource("BingRenXinXi/BingRenXinXi.fxml"));
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
		
		
	//	医生所加的病种
		
		
		ObservableList<DiseaseType>listD= FXCollections.observableArrayList();
		
		List<DiseaseType>listdis=Diseases.readDiseases();
		
		for(DiseaseType d:listdis) {
			listD.add(d);
		}
		
		C0.setCellValueFactory( new PropertyValueFactory<>("name"));
		T0.setItems(listD);
			
		
		

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
		
		MyHeap mh=new MyHeap(100);
		
		for(Patient p:listp) {
			mh.insert(p);
		}
		
		
		ObservableList<Patient>patientlistT1= FXCollections.observableArrayList();
		
//		for(Patient p:listp) {
//
//			patientlistT1.add(p);
//		}
		
		while(mh.getFirstfree()!=1) {
			patientlistT1.add(mh.getNodes()[1]);
			mh.remove();
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
	//msg是诊断书	
	
	public void submit() {

		submitsuccess.setText("提交成功！");
		
		
		Certificate cer=new Certificate();
		cer.setMain(Text1.getText());
		cer.setNow(Text2.getText());
		cer.setPast(Text3.getText());
		cer.setExamine(Text4.getText());
		
		List<DiseaseType>dis=Diseases.readDiseases();
		
		cer.setDiseases(dis);
			
		Vector<DiseaseType>vd=Bingzhong.read();
		String pId=null;
		
	
		List<String>msg=new ArrayList<>();
		msg.add(Text1.getText());
		msg.add(Text2.getText());
		msg.add(Text3.getText());
		msg.add(Text4.getText());			
		
		
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
					c1.setC(cer);
					c1.setBingliXinXi(msg);
					
					Patient p=c1.getPatient();
					
					
					//更新MyHashTable
					String sc=JSON.toJSONString(c1);
					MyHashTable<String,String>ht0=new MyHashTable<>();
					MyHashTable<String,String>ht=ht0.read();
					
					String sid=c1.getId();
					
					ht.remove(sid);
					ht.put(sid, sc);
					
				
					
					//将该病人加入该病种属性结构中	
					
					for(DiseaseType s:dis) {
						for(DiseaseType dt1:vd) {
							if(s.getName().equals(dt1.getName())) {
	
									pId=dt1.getParentID();
									
									dt1.getPatient().add(p);
									
									
									while(!dt1.getParentID().equals("-1")) {	
									 for(DiseaseType dt11:vd) {
										 
											if(dt11.getID().equals(dt1.getParentID())) {
													Vector<DiseaseType> sub=dt11.getSub_diseases();
													for(DiseaseType ds:sub) {
														if(ds.getName().equals(dt1.getName())) {
															ds.setPatient(dt1.getPatient());
															ds.setSub_diseases(dt1.getSub_diseases());
															break;
															}
														}
														
													dt1=dt11;
													
													
													}
												}
									
									
									
								}
								
								
								
								
								
								
								
							}
						}	
					}		
								
					

					
					
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
							break;
						}
					}
					
					
					break;
				}
			}
			
			
			//清空Disease文件			
			Diseases.qingkong();
			
			
			
			Gson g1=new Gson(); 
			String s1=g1.toJson(vd);
			OutputStream outt = new FileOutputStream("src/bingzhong.txt");
			DataOutputStream doutt=new DataOutputStream(outt);
			doutt.writeUTF(s1);
			doutt.close();
				
			
			
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
	
	
	
	//复诊按钮
	public void fuzhen() {
		
		
		String strb=null;
		String str=speciallabel.getText();
		String str1=str+"T";
		
		
		InputStream in;
		try {
			in = new FileInputStream("src/Cases");
			DataInputStream din=new DataInputStream(in);
			String str11=din.readUTF();
			Gson gson=new Gson();
			List<Case>listc=gson.fromJson(str11,new TypeToken<List<Case>>() {}.getType() );
			
			
			InputStream in1=new FileInputStream("src/Doctors");
			DataInputStream din1=new DataInputStream(in1);
			String str111=din1.readUTF();
			List<Doctor>listd=JSON.parseArray(str111, Doctor.class);
			
		
			InputStream in2=new FileInputStream("src/Patients");
			DataInputStream din2=new DataInputStream(in2);
			String str3=din2.readUTF();
			List<Patient>listP=JSON.parseArray(str3, Patient.class);
			
			
			MyHashTable<String,String>ht0=new MyHashTable<String,String>();
			MyHashTable<String,String>ht=ht0.read();
			
			for(Case c1:listc) {
				
				if(str.equals(c1.getId())) {
					
					Patient p=c1.getPatient();
					
					
					if(p.isSeenTwice()) {
						System.out.println("该患者已经复诊！");
						break;
					}
					
					for(Patient pp:listP) {
						if(pp.getId().equals(str)) {
							pp.setId(str1);
							break;
						}
					}
					
					
					c1.setId(str1);
					
					
	

					for(Doctor d:listd) {
						if(d.getName().equals(c1.getDoctor().getName())) {
							
							List<Patient>listp=d.getToSee();
							
							int sum1 = 0;
							int i1=0;
							int sum2 = 0;
							int i2=0;
							
							for(Patient pp:listp) {
								
							//	对普通病人改写复诊后的权值（插在所有普通患者队列的中间）							
							  if(!pp.isUrgent()) {
								int a=Integer.parseInt(pp.getWeight());
								sum1=sum1+a;
								i1++;	
								}
							  
							  
							  //对紧急病人改写复诊后的权值（插在所有紧急患者队列的中间）
							  else {
								  int a=Integer.parseInt(pp.getWeight());
									sum2=sum2+a;
									i2++;	
							  }
							  
							  
							  
							  
							}
							
		
							   if(p.isUrgent()) {
								   int b=sum2/i2;
								   
								   // 解决冲突问题（如果新修改的B类病人的权值等于原来列表中的A类某位病人的权值，则将B类病人的权值加上1）								   
								   for(Patient pp:listp) {
									   if(pp.isUrgent()&&pp.getWeight().equals(b+"")) {
										   b=b+1;
										   break;
									   }   
								   }
								   
								   strb=b+"";
							   }
							   
							   else {
								   int b=sum1/i1;
								   
								   // 解决冲突问题（如果新修改的B类病人的权值等于原来列表中的A类某位病人的权值，则将B类病人的权值加上1）								   
								   for(Patient pp:listp) {
									   if((!pp.isUrgent())&&pp.getWeight().equals(b+"")) {
										   b=b+1;
										   break;
									   }   
								   }
								   
									strb=b+"";
							   }
								
							   
							   for(Patient pp:listP) {
									 if(pp.getId().equals(str1)) {
										 List<String>lists=pp.getCaseId();
										 
										 for(int i=0;i<lists.size();i++) {
											 if(lists.get(i).equals(str)) {
												 lists.set(i, str1);
												 break;
											 }
										 }
										
									 }
								 }
								    
							   
							   
							   
		
							 for(Patient pa:listp) {
								 if(pa.getIdP().equals(p.getIdP()))
								 { 								
									 
									 pa.setId(str1);
									 
									 List<String>lists=pa.getCaseId();
									 
									 for(int i=0;i<lists.size();i++) {
										 if(lists.get(i).equals(str)) {
											 lists.set(i, str1);
											 break;
										 }
									 }
									 
									 
									 pa.setSeenTwice(true);
			
									 pa.setWeight(strb);
 
									 break;
								 }
								 }
							 
							    p.setId(str1);
								p.setSeenTwice(true);
								
								p.setWeight(strb);
								
								 List<String>lists=p.getCaseId();
								 for(int i=0;i<lists.size();i++) {
									 if(lists.get(i).equals(str)) {
										 lists.set(i, str1);
										 break;
									 }
								 }
								p.setCaseId(lists);
								
								 
								d.setToSee(listp);
								 
								c1.setPatient(p);
								
								String sc=JSON.toJSONString(c1);
								
								ht.put(str1, sc);
								ht.remove(str);
								
						break;
						}
					}
		
				}
			}
			
		
	
			String str2=JSON.toJSONString(listd);
			OutputStream out1=new FileOutputStream("src/Doctors");
			DataOutputStream dout1=new DataOutputStream(out1);
			dout1.writeUTF(str2);
			dout1.close();
			
			
			Gson g=new Gson(); 
			String s=g.toJson(listc);
			OutputStream out=new FileOutputStream("src/Cases");
			DataOutputStream dout=new DataOutputStream(out);
			dout.writeUTF(s);
			dout.close();
			
			
			String s2=JSON.toJSONString(listP);
			OutputStream out2=new FileOutputStream("src/Patients");
			DataOutputStream dout2=new DataOutputStream(out2);
			dout2.writeUTF(s2);
			dout2.close();
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
	
	
	//下一位按钮
	public void xiayiwei() {
		
		//跳转到Next界面
		AnchorPane root;
		try {
			root = FXMLLoader.load(getClass().getResource("next/Next.fxml"));
			
			Scene scene=new Scene(root);
			
			Stage primaryStage=new Stage();
			primaryStage.hide();
		    primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}
	
	
	
	
	
	//选择病种按钮
	public void xuanzebz() {
		
		AnchorPane root;
		try {
			root = FXMLLoader.load(getClass().getResource("zhenduan/Zhenduan.fxml"));
			
			Scene scene=new Scene(root);
			
			Stage primaryStage=new Stage();
			primaryStage.hide();
		    primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	

	
	
}
