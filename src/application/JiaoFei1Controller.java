package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.google.gson.Gson;

import application.model.Case;
import application.model.Medicine;
import application.model.YaoFang;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class JiaoFei1Controller {
	@FXML
	private TableView<YaoFang> T1=new TableView<>();
	@FXML
	private TableView<Medicine> T2=new TableView<>();
	@FXML
	private TableColumn<YaoFang,String> C1=new TableColumn<>();
	@FXML
	private TableColumn<YaoFang,String> C2=new TableColumn<>();
	@FXML
	private TableColumn<Medicine,String> c1=new TableColumn<>();
	@FXML
	private TableColumn<Medicine,String> c2=new TableColumn<>();
	@FXML
	private TableColumn<Medicine,String> c3=new TableColumn<>();
	@FXML
	private TableColumn<Medicine,String> c4=new TableColumn<>();
	@FXML
	private TableColumn<Medicine,String> c5=new TableColumn<>();
	@FXML
	private TableColumn<Medicine,String> c6=new TableColumn<>();
	@FXML
	private TableColumn<Medicine,String> c7=new TableColumn<>();
	@FXML
	private TableColumn<Medicine,String> c8=new TableColumn<>();
	
	
	@FXML
	private MenuItem GuaHao;
	@FXML
	private Button QRJF;
	@FXML
	private TextField binglihao;
	@FXML
	private TextField xingming;
	@FXML
	private TextField nianling;
	@FXML
	private TextField yisheng;
	@FXML
	private TextField JinE;
	@FXML
	private TextField jfqk;
	@FXML
	private Label weichadao;
	@FXML
	private Label jfcg;
	
	
	public void initialize() {
		
		
	}
	

	
	//�л����ҺŽ���
	public void guahaoYeMian() {
		AnchorPane root;
		try {
			root = FXMLLoader.load(getClass().getResource("guahao/GuaHaoXinXi.fxml"));
			Scene scene=new Scene(root);
			Stage primaryStage=(Stage) QRJF.getScene().getWindow();
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
				
				Stage primaryStage=(Stage) QRJF.getScene().getWindow();
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
				Stage primaryStage=(Stage) QRJF.getScene().getWindow();
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
			
			boolean b=false;
			String str=binglihao.getText();
			
			List<Case>listc=Case.readCase();
			
			
			for(Case c:listc) {
				
				if(c.getId().equals(str)) {
					
					//�ж��Ƿ�֧������
					if(c.isJiaofeifou()) {
						jfqk.setText("�ѽ���");
					}
					else {
						jfqk.setText("δ����");
					}
					weichadao.setText("");
					b=true;
					xingming.setText(c.getPatient().getName());
					nianling.setText(c.getPatient().getAge());
					yisheng.setText(c.getDoctor().getName());
					double cost=c.getCost();
					String s=String.valueOf(cost);
					JinE.setText(s);
					
					//��ʾ������ҩ���б�
						xianshiyf();
						
						break;
				}
				
				
			}
			
			if(!b) {
				weichadao.setText("δ��ѯ�����");
			}
			
			
		}
		
		
		
		//��ʾ������ҩ���б�	
		public void xianshiyf() {
			
			String str=binglihao.getText();
			List<Case>listc=Case.readCase();
			for(Case c:listc) {
				if(c.getId().equals(str)) {
					
					if(c.getYaofang()==null) {
						break;
					}
					
					List<YaoFang>listy=c.getYaofang();
					
					ObservableList<YaoFang>listYF= FXCollections.observableArrayList();
					for(YaoFang y:listy) {
						listYF.add(y);
					}
					
					C1.setCellValueFactory( new PropertyValueFactory<>("name"));
					C2.setCellValueFactory( new PropertyValueFactory<>("cost"));
					T1.setItems(listYF);
					
					T1.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue)->{if (newValue != null) {
					
						List<YaoFang>listyy=c.getYaofang();
						for(YaoFang y:listyy) {
							if(y.equals(newValue)) {
								
								List<Medicine>listm=y.getListM();
								ObservableList<Medicine>listM= FXCollections.observableArrayList();
								for(Medicine m:listm) {
									listM.add(m);
								}
								
								c1.setCellValueFactory( new PropertyValueFactory<>("name"));
								c2.setCellValueFactory(new PropertyValueFactory<>("guige"));
								c3.setCellValueFactory(new PropertyValueFactory<>("danwei"));
								c4.setCellValueFactory(new PropertyValueFactory<>("yongfa"));
								c5.setCellValueFactory(new PropertyValueFactory<>("yongliang"));
								c6.setCellValueFactory(new PropertyValueFactory<>("pinlv"));
								c7.setCellValueFactory(new PropertyValueFactory<>("shuliang"));
								c8.setCellValueFactory(new PropertyValueFactory<>("price"));
								T2.setItems(listM);
								
								break;
		
							}
							
						}
						
					  }
				    });
					
					//breakҪ��if�����ŵ����棡����������		
					break;
				}
				
			}
			
			
			
		}
		
		
		
		public void querenjiaofei() {
			if(jfqk.getText().equals("�ѽ���")) {
				jfcg.setText("���Ѿ��������ˣ�");
			}
			
			else {
			String str=binglihao.getText();
			List<Case>listc=Case.readCase();
			for(Case c:listc) {
				if(c.getId().equals(str)) {
					
					c.setJiaofeifou(true);
					
					//����case�ļ��е�֧����false�ĳ�true
					try {
						OutputStream out=new FileOutputStream("src\\Cases");
						DataOutputStream dout =new DataOutputStream(out);
						Gson gson=new Gson();
						String s=gson.toJson(listc);
						dout.writeUTF(s);
						dout.close();
						
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					jfcg.setText("���ѳɹ���");
					break;
				}
			}
				
			}
			
		}
		
		
		
		
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
	
	
	
	
}
