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
	

	
	//切换到挂号界面
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
	
	
	
	//切换到交费界面
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
	
		
		
		
		

		//切换到退号界面
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

		
		
		
		//查询按钮
		public void chaxun() {
			
			boolean b=false;
			String str=binglihao.getText();
			
			List<Case>listc=Case.readCase();
			
			
			for(Case c:listc) {
				
				if(c.getId().equals(str)) {
					
					//判断是否支付过了
					if(c.isJiaofeifou()) {
						jfqk.setText("已交费");
					}
					else {
						jfqk.setText("未交费");
					}
					weichadao.setText("");
					b=true;
					xingming.setText(c.getPatient().getName());
					nianling.setText(c.getPatient().getAge());
					yisheng.setText(c.getDoctor().getName());
					double cost=c.getCost();
					String s=String.valueOf(cost);
					JinE.setText(s);
					
					//显示所开的药方列表
						xianshiyf();
						
						break;
				}
				
				
			}
			
			if(!b) {
				weichadao.setText("未查询到结果");
			}
			
			
		}
		
		
		
		//显示所开的药方列表	
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
					
					//break要在if大括号的里面！！！！！！		
					break;
				}
				
			}
			
			
			
		}
		
		
		
		public void querenjiaofei() {
			if(jfqk.getText().equals("已交费")) {
				jfcg.setText("您已经交过费了！");
			}
			
			else {
			String str=binglihao.getText();
			List<Case>listc=Case.readCase();
			for(Case c:listc) {
				if(c.getId().equals(str)) {
					
					c.setJiaofeifou(true);
					
					//更新case文件中的支付否将false改成true
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
					
					jfcg.setText("交费成功！");
					break;
				}
			}
				
			}
			
		}
		
		
		
		
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
	
	
	
	
}
