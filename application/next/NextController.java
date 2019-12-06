package application.next;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import application.model.Case;
import application.model.Certificate;
import application.model.DiseaseType;
import application.model.Doctor;
import application.model.DoctorListOfPatient;
import application.model.Medicine;
import application.model.Patient;
import application.model.TomJimKim;
import application.model.YaoFang;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.MyHashTable;
import logic.MyHeap;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

public class NextController {
	@FXML
	private TableView<Case> T=new TableView<>();
	
	@FXML
	private TableView<DiseaseType> T0=new TableView<>();
	@FXML
	private TableView<YaoFang> T1=new TableView<>();
	@FXML
	private TableView<Medicine> T2=new TableView<>();
	
	@FXML
	private TableColumn <Case,String>c1=new TableColumn<>();
	@FXML
	private TableColumn <Case,String>c2=new TableColumn<>();
	@FXML
	private TableColumn<Case,String> c3=new TableColumn<>();
	@FXML
	private TableColumn<Case,String> c4=new TableColumn<>();
	@FXML
	private TableColumn <DiseaseType,String>cc1=new TableColumn<>();
	@FXML
	private TableColumn <YaoFang,String>cc2=new TableColumn<>();
	
	@FXML
	private TableColumn<Medicine,String> C1=new TableColumn<>();
	@FXML
	private TableColumn<Medicine,String> C2=new TableColumn<>();
	@FXML
	private TableColumn<Medicine,String> C3=new TableColumn<>();
	@FXML
	private TableColumn<Medicine,String> C4=new TableColumn<>();
	@FXML
	private TableColumn<Medicine,String> C5=new TableColumn<>();
	@FXML
	private TableColumn<Medicine,String> C6=new TableColumn<>();
	@FXML
	private TableColumn<Medicine,String> C7=new TableColumn<>();
	@FXML
	private Button sx=new Button();
	
	
	
	
	public void initialize() throws EOFException {
		
		List <Case>listc=new ArrayList<>();
		MyHashTable<String,String>ht=new MyHashTable<String,String>();

				//��õ�ǰҽ��		
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
				
				//���ҷ��������ҵ���ҽ�����������δ���б�֮�����в��˷������Լ�д�Ķѽṹ�У�֮��������get root����
				List<Patient>listp=DoctorListOfPatient.getToSeeList(str);
				
				MyHeap mh=new MyHeap(100);
				
				for(Patient p:listp) {
					mh.insert(p);
				}
				
				//��Ϊ�գ��򷵻�				
				if(mh.getFirstfree()==1) {
					return;
				}
				
				Patient p1=mh.getNodes()[1];
				List<String>lists=p1.getCaseId();
				
				MyHashTable<String,String>ht0=new MyHashTable<String,String>();
				
				ht=ht0.read();
				
				for(String s:lists) {
				
					System.out.println(s);
					
					//�õ�Case����	
					
					String sc=ht.get(s);
					
					Case c1=JSON.parseObject(sc,Case.class);
					listc.add(c1);
				}
				
				ObservableList<Case>listT11= FXCollections.observableArrayList();
				

				
				for(Case c1:listc) {
					
					listT11.add(c1);
				}
				
				c1.setCellValueFactory( new PropertyValueFactory<>("id"));
				//c2.setCellValueFactory(cellData -> cellData.getValue().getDoctor().getName());
				
				c2.setCellValueFactory( new PropertyValueFactory<>("pname"));
				c3.setCellValueFactory(new PropertyValueFactory<>("dname"));
				c4.setCellValueFactory(new PropertyValueFactory<>("cost"));
				T.setItems(listT11);
		
					T.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue)->{if (newValue != null) {
						
						
						try {
						//�����б�
						ObservableList<DiseaseType>listd= FXCollections.observableArrayList();
						
						Certificate cer=newValue.getC();
						List<DiseaseType>listdis=cer.getDiseases();
						
						for(DiseaseType d:listdis) {
							listd.add(d);
						}
						
						cc1.setCellValueFactory( new PropertyValueFactory<>("name"));
						T0.setItems(listd);
		
						
						//ҩ���б�
						ObservableList<YaoFang>listy= FXCollections.observableArrayList();
						List<YaoFang>listyf=newValue.getYaofang();
						for(YaoFang y:listyf) {
							listy.add(y);
						}
						
						cc2.setCellValueFactory( new PropertyValueFactory<>("name"));
						T1.setItems(listy);
						
						
						//ҩƷ�б�
						ObservableList<Medicine>listM= FXCollections.observableArrayList();
						List<Medicine>listm=new ArrayList<>();
						List<YaoFang>listyy=new ArrayList<>();
						
						List<Case>listca=Case.readCase();
						for(Case ca:listca) {
							if(ca.getId().equals(newValue.getId())) {
								listyy=ca.getYaofang();
								break;
							}
						}
						
						for(YaoFang yf:listyy) {
							
							List<Medicine>listmm=yf.getListM();
							
							for(Medicine mm:listmm) {
								listm.add(mm);
							}
						}
						
						for(Medicine med:listm) {
							listM.add(med);
						}
						
						C1.setCellValueFactory( new PropertyValueFactory<>("name"));
						C2.setCellValueFactory(new PropertyValueFactory<>("guige"));
						C3.setCellValueFactory(new PropertyValueFactory<>("danwei"));
						C4.setCellValueFactory(new PropertyValueFactory<>("yongfa"));
						C5.setCellValueFactory(new PropertyValueFactory<>("yongliang"));
						C6.setCellValueFactory(new PropertyValueFactory<>("pinlv"));
						C7.setCellValueFactory(new PropertyValueFactory<>("shuliang"));
						T2.setItems(listM);
						
						}
						catch(Exception e) {
							
							shuaxin();
							
						}				
	
			       }
			    });
		
	}
	
	
	
	//ˢ�½���
	public void shuaxin() {
		
		AnchorPane root;
		try {
			root = FXMLLoader.load(getClass().getResource("Next.fxml"));
			Scene s1=new Scene(root);
			Stage primaryStage=(Stage) sx.getScene().getWindow();
			primaryStage.setScene(s1);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	

}
