package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import application.model.Case;
import application.model.Doctor;
import application.model.DoctorListOfPatient;
import application.model.GetYaoFang;
import application.model.Medicine;
import application.model.Patient;
import application.model.SxHfF4;
import application.model.TomJimKim;
import application.model.YaoFang;
import application.model.Yf;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.MyHashTable;
import logic.MyHeap;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class KaiYaoController {
	@FXML
	private TextArea ListText2;
	@FXML
	private Button b13;
	@FXML
	private Button qkb;
	@FXML
	private Button shuaxin;
	@FXML
	private TableView<Patient> table1;
	@FXML
	private TableView<Patient> table2;
	@FXML
	private TableView<YaoFang> table3=new TableView<>();
	@FXML
	private TableView<Medicine> table4=new TableView<>();
	@FXML
	private TableView<YaoFang> T1=new TableView<>();
	@FXML
	private TableView<Medicine> T2=new TableView<>();
	@FXML
	private TableColumn<YaoFang,String> C1;
	@FXML
	private TableColumn<YaoFang,String> C2;
	@FXML
	private TableColumn<YaoFang,String> con1;
	@FXML
	private TableColumn<YaoFang,String> con2;
	
	@FXML
	private TableColumn<Medicine,String> c3;
	@FXML
	private TableColumn<Medicine,String> c4;
	@FXML
	private TableColumn<Medicine,String> c5;
	@FXML
	private TableColumn<Medicine,String> c6;
	@FXML
	private TableColumn<Medicine,String> c7;
	@FXML
	private TableColumn<Medicine,String> c8;
	@FXML
	private TableColumn<Medicine,String> c9;
	
	@FXML
	private TableColumn<Medicine,String> cc1;
	@FXML
	private TableColumn<Medicine,String> cc2;
	@FXML
	private TableColumn<Medicine,String> cc3;
	@FXML
	private TableColumn<Medicine,String> cc4;
	@FXML
	private TableColumn<Medicine,String> cc5;
	@FXML
	private TableColumn<Medicine,String> cc6;
	@FXML
	private TableColumn<Medicine,String> cc7;
	
	@FXML
	private TableColumn<Patient,String> id11;
	@FXML
	private TableColumn<Patient,String> id22;
	@FXML
	private TableColumn<Patient,String> name11;
	@FXML
	private TableColumn<Patient,String> name22;
	@FXML
	private TableColumn<Patient,String> age11;
	@FXML
	private TableColumn<Patient,String> age22;
	
	@FXML
	private TextField TT;
	@FXML
	private TextField t1;
	@FXML
	private TextField t2;
	@FXML
	private TextField t3;
	@FXML
	private TextField t4;
	@FXML
	private TextField t5;
	@FXML
	private TextField t6;
	@FXML
	private Button tijiao2;
	@FXML
	private Button qingkong2;
	@FXML
	private Label submitsuccess;
	@FXML
	private Label speciallabe2;
	@FXML
	private Label L;
	@FXML
	private Label l1;
	@FXML
	private Label l2;

	@FXML
	private Label L1;

	@FXML
	private Label L3;
	@FXML
	private Label L4;
	
	
	
	
	public void initialize() {
		
		
		//��ʾ��ǰ�Ǹ�˭��ҩ����ʾL4��text�������ţ�
		List<String>l=SxHfF4.read();
		for(String s:l) {
			L4.setText(s);
			break;
		}
		
		
		//��textfield����ʾ��ǰ��ҩ�Ļ�����Ϣ������Ϊˢ�¶��ı䣩
		List<Case>listc=Case.readCase();
		for(Case c:listc) {
			if(c.getId().equals(L4.getText())) {
				ListText2.setText("����:"+c.getPatient().getName()+"   "+"������:"+c.getId()+"   "+"����:"+c.getPatient().getAge()+"   "+"�Ա�:"+c.getPatient().getSex());
				break;
			}
		}
		
		
		//��ʾL3��text����˭��ҩ
		if(L4.getText().equals("")) {
			L3.setText("��ѡ��һ������");
		}
		else {
			L3.setText("��ǰ��"+L4.getText()+"�Ż��߿�ҩ");
		}
		
		//��ʾ�ܽ��
		double sum=0;
		List<YaoFang>listyy=Yf.read();
		for(YaoFang y:listyy) {
			sum+=y.getCost();
		}
		TT.setText(""+sum);	
		
		
		//ҽ�������б�δ�
		
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
		
	
		
		id11.setCellValueFactory( new PropertyValueFactory<>("id"));
		name11.setCellValueFactory(new PropertyValueFactory<>("name"));
		age11.setCellValueFactory(new PropertyValueFactory<>("age"));
		table1.setItems(patientlistT1);
		
		
		table1.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue)->{if (newValue != null) {
			ListText2.setText("����:"+newValue.getName()+"   "+"������:"+newValue.getId()+"   "+"����:"+newValue.getAge()+"   "+"�Ա�:"+newValue.getSex());
			speciallabe2.setText(newValue.getId());
			
	       }
	    });
		
		
		
		
		//ҽ�������б����	
		
		List<Patient>listp1=DoctorListOfPatient.getHaveSeenList(str);
		
		ObservableList<Patient>patientlistT11= FXCollections.observableArrayList();
		
		for(Patient p:listp1) {

			patientlistT11.add(p);
		}
		
		id22.setCellValueFactory( new PropertyValueFactory<>("id"));
		name22.setCellValueFactory(new PropertyValueFactory<>("name"));
		age22.setCellValueFactory(new PropertyValueFactory<>("age"));
		table2.setItems(patientlistT11);
		
		
		table2.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue)->{if (newValue != null) {
			ListText2.setText("����:"+newValue.getName()+"   "+"������:"+newValue.getId()+"   "+"����:"+newValue.getAge()+"   "+"�Ա�:"+newValue.getSex());

			L4.setText(newValue.getId());
			
			L3.setText("��ǰ��"+L4.getText()+"�Ż��߿�ҩ");
			
			//��ֹ���ˢ��ʱL4�еĵ�ǰ���ߵĲ�������ʧ
			SxHfF4.clear();
			List<String>lists=SxHfF4.read();
			lists.add(L4.getText());
			
			try {
				OutputStream out=new FileOutputStream("src\\shuaxinhuifuL4");
				DataOutputStream dout =new DataOutputStream(out);
				Gson gson=new Gson();
				String s=gson.toJson(lists);
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
	    });
		

		//��ȡҩ���б�
		
		List<YaoFang>listY=GetYaoFang.getYaoFang();
		ObservableList<YaoFang>yaofanglist= FXCollections.observableArrayList();
		for(YaoFang y:listY) {
			yaofanglist.add(y);
		}
		
		con1.setCellValueFactory( new PropertyValueFactory<>("name"));
		con2.setCellValueFactory(new PropertyValueFactory<>("fanwei"));
		table3.setItems(yaofanglist);
		
		table3.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue)->{if (newValue != null) {
			
			mobanmx(newValue);
			L1.setText(newValue.getName());

	       }
	    });
		
		
		
		
		//��ȡ���ﴦ���б�
		List<YaoFang>l11=GetYaoFang.getYaoFang2();
		ObservableList<YaoFang>yaofang= FXCollections.observableArrayList();
		for(YaoFang y:l11) {
			yaofang.add(y);
		}
		C1.setCellValueFactory( new PropertyValueFactory<>("name"));
		C2.setCellValueFactory(new PropertyValueFactory<>("zhuangtai"));
		T1.setItems(yaofang);
		
		T1.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue)->{if (newValue != null) {
			List<Medicine>listm=new ArrayList<>();
			ObservableList<Medicine>om= FXCollections.observableArrayList();
			for(YaoFang y:l11) {
				if(y.equals(newValue)) {
					listm=y.getListM();
					break;
				}
			}
			for(Medicine m:listm) {
				om.add(m);
			}
			cc1.setCellValueFactory( new PropertyValueFactory<>("name"));
			cc2.setCellValueFactory(new PropertyValueFactory<>("guige"));
			cc3.setCellValueFactory(new PropertyValueFactory<>("danwei"));
			cc4.setCellValueFactory(new PropertyValueFactory<>("yongfa"));
			cc5.setCellValueFactory(new PropertyValueFactory<>("yongliang"));
			cc6.setCellValueFactory(new PropertyValueFactory<>("pinlv"));
			cc7.setCellValueFactory(new PropertyValueFactory<>("shuliang"));
			T2.setItems(om);
			
			
	       }
	    });
		
		
	}
	
	
	//��ʼ��ҩ��ť
//			public void kaishi() {
//				if(!L4.getText().equals("")) {
//				L3.setText("��"+L4.getText()+"�Ż��߿�ҩ");
//				}
//				else {
//					L3.setText("��ѡ��һ������");
//					
//				}
//			}
	

	
	
	//��ȡҩ��ģ�����ϸ��ҩ���к��е�ҩƷ��Ϣ��
	public void mobanmx(YaoFang yaofang) {
		List<YaoFang>listY=GetYaoFang.getYaoFang();
		ObservableList<Medicine>moban= FXCollections.observableArrayList();
		for(YaoFang y:listY) {
			if(y.getName().equals(yaofang.getName())) {
				List<Medicine>listm=y.getListM();
				
				for(Medicine m:listm) {
					moban.add(m);
				}
				break;
				
			}
		}
		c3.setCellValueFactory( new PropertyValueFactory<>("name"));
		c4.setCellValueFactory(new PropertyValueFactory<>("guige"));
		c5.setCellValueFactory(new PropertyValueFactory<>("danwei"));
		c6.setCellValueFactory(new PropertyValueFactory<>("yongfa"));
		c7.setCellValueFactory(new PropertyValueFactory<>("yongliang"));
		c8.setCellValueFactory(new PropertyValueFactory<>("pinlv"));
		c9.setCellValueFactory(new PropertyValueFactory<>("shuliang"));
		
		table4.setItems(moban);
	}
	
	
	
	//������ť
	public void clear() {
		
		List<Medicine>listM=Medicine.readM();		
		
		//�ж����ĸ�ҽ��
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
		
		
		List<YaoFang>listy=Yf.read();
		for(YaoFang y:listy) {
			List<Medicine>listm=y.getListM();
			
			for(Medicine m:listm) {
				m.setTotalamount(m.getTotalamount()-m.getShuliang());
				
				for(Medicine mm:listM) {
					if(mm.getName().equals(m.getName())) {
						mm.setTotalamount(mm.getTotalamount()-m.getShuliang());
						break;
					}
				}
				
			}
		}
		
		List<Case>listc=Case.readCase();
		for(Case c1:listc) {
			if(c1.getId().equals(L4.getText())) {
				c1.setPaid(false);
				c1.setYijingfayao(true);
				c1.setYaofang(listy);
				List<Doctor>l=Doctor.readD();
				for(Doctor d:l) {
					if(d.getName().equals(str)) {
						c1.setDoctor(d);
						break;
					}
				}
				String s=TT.getText();
				//cost
				double cost=Double.parseDouble(s);
				c1.setCost(cost);
				
				
				
				//����MyHashTable
				String sc=JSON.toJSONString(c1);
				MyHashTable<String,String>ht0=new MyHashTable<>();
				MyHashTable<String,String>ht=ht0.read();
				
				String sid=c1.getId();
				ht.remove(sid);
				ht.put(sid, sc);
				
		
				
				L.setText("�����ɹ���");
				break;
			}
			
		}
		//����
		OutputStream out;
		try {
			out = new FileOutputStream("src/Cases");
			DataOutputStream dout=new DataOutputStream(out);
			Gson g=new Gson();
			String str1=g.toJson(listc);
			dout.writeUTF(str1);
			dout.close();
			
			
			String str11=JSON.toJSONString(listM);
			OutputStream out1 = new FileOutputStream("src\\Medicine");
			DataOutputStream dout1=new DataOutputStream(out1);
			dout1.writeUTF(str11);
			dout1.close();
			
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		//�����ʱҩ���ļ��������ʹ�ø�ģ�塱֮��		
		Yf.clear();
	}
	
	
	
	
	//�л�����������
	public void zengfang() {
		AnchorPane root;
		try {
			root = FXMLLoader.load(getClass().getResource("kaiyao/ZengFang.fxml"));
			Scene s=new Scene(root);
			Stage primaryStage=new Stage();
			//primaryStage.hide();
			primaryStage.setScene(s);
			
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	
	//�л���ɾ������
	public void shanfang() {
		AnchorPane root;
		try {
			root = FXMLLoader.load(getClass().getResource("kaiyao/ShanFang.fxml"));
			Scene s=new Scene(root);
			Stage primaryStage=new Stage();
			//primaryStage.hide();
			primaryStage.setScene(s);
			
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	//��ҩ��ģ��ӵ����ﴦ����
	
	public void MZCF() {
		List<Medicine>listM=Medicine.readM();
		
		for(Medicine m:listM) {
			if(m.getName().equals("������")) {
				System.out.println(m.getTotalamount());
			}
		}
		
		List<YaoFang>listY=GetYaoFang.getYaoFang();
		List<YaoFang>l=GetYaoFang.getYaoFang2();
		ObservableList<YaoFang>yaofanglist= FXCollections.observableArrayList();
		for(YaoFang y:listY) {
			if(y.getName().equals(L1.getText())) {
				List<Medicine>listMM=y.getListM();
				
				for(Medicine m:listMM) {
					for(Medicine mm:listM) {						
						if(m.getName().equals(mm.getName())) {
							
							if(mm.getTotalamount()<m.getShuliang()) {
								
								Alert a=new Alert(Alert.AlertType.ERROR);
								a.setHeaderText(m.getName()+"ʣ���������㣬��ʣ"+mm.getTotalamount()+"��");
								a.showAndWait();
								
								return;
							}
						}	
						
					}
		

				}
		
				l.add(y);
				break;
			}
			
			
			
			
			
		}
		
		for(YaoFang y:l) {
			yaofanglist.add(y);
		}	
		
		C1.setCellValueFactory( new PropertyValueFactory<>("name"));
		C2.setCellValueFactory(new PropertyValueFactory<>("zhuangtai"));
		T1.setItems(yaofanglist);
		
		T1.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue)->{if (newValue != null) {
			List<Medicine>listm=new ArrayList<>();
			ObservableList<Medicine>om= FXCollections.observableArrayList();
			for(YaoFang y:l) {
				if(y.equals(newValue)) {
					listm=y.getListM();
					break;
				}
			}
			for(Medicine m:listm) {
				om.add(m);
			}
			cc1.setCellValueFactory( new PropertyValueFactory<>("name"));
			cc2.setCellValueFactory(new PropertyValueFactory<>("guige"));
			cc3.setCellValueFactory(new PropertyValueFactory<>("danwei"));
			cc4.setCellValueFactory(new PropertyValueFactory<>("yongfa"));
			cc5.setCellValueFactory(new PropertyValueFactory<>("yongliang"));
			cc6.setCellValueFactory(new PropertyValueFactory<>("pinlv"));
			cc7.setCellValueFactory(new PropertyValueFactory<>("shuliang"));
			T2.setItems(om);
			
			
	       }
	    });
		double sum=0;
		for(YaoFang y:l) {
			sum+=y.getCost();
		}
		TT.setText(""+sum);
		
		
		Gson gson=new Gson();
		String str=gson.toJson(l);
		OutputStream out;
		try {
			out = new FileOutputStream("src\\yf");
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
	
	
	
	
	
	
	
	//�л���������ҳ����
	public void qiehuan2() {
		AnchorPane root;
		try {
			root = FXMLLoader.load(getClass().getResource("doctor/HuanZheLieBiao.fxml"));
			Scene s=new Scene(root);
			
			Stage primaryStage=(Stage) b13.getScene().getWindow();
			
			//primaryStage.hide();
			primaryStage.setScene(s);
			primaryStage.show();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//ˢ�½���
	public void shuaxin() {
		AnchorPane root;
		try {
			root = FXMLLoader.load(getClass().getResource("kaiyao/KaiYao.fxml"));
			Scene s1=new Scene(root);
			Stage primaryStage=(Stage) shuaxin.getScene().getWindow();
			primaryStage.setScene(s1);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

	
	//�����ѡ�����ﴦ��
	public void qk() {
		Yf.clear();
		shuaxin();
	}
	
	
	
	//�˳����水ť
	public void close() {
		
		//���shuaxinhuifuL4�ļ�
		SxHfF4.clear();
		
		//������ﴦ���б�
		Yf.clear();
		
		//�رմ���
		Stage primaryStage=(Stage) b13.getScene().getWindow();
		primaryStage.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
