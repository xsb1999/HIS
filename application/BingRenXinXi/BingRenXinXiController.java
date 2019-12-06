package application.BingRenXinXi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.alibaba.fastjson.JSON;

import Sorting.MyQuickSortP;
import application.model.Bingzhong;
import application.model.Case;
import application.model.Certificate;
import application.model.DiseaseType;
import application.model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.MyHashTable;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class BingRenXinXiController {
	@FXML
	private TextField text1;
	@FXML
	private Button cx;
	@FXML
	private TableView<Patient> T1=new TableView<>();
	@FXML
	private TableView<DiseaseType> T2=new TableView<>();
	@FXML
	private TableColumn <Patient,String> c1=new TableColumn<>();
	@FXML
	private TableColumn <Patient,String>c2=new TableColumn<>();
	@FXML
	private TableColumn <DiseaseType,String>c3=new TableColumn<>();

	
	
	//清空列表
	public void qingkong() {
		
		AnchorPane root;
		try {
			root = FXMLLoader.load(getClass().getResource("BingRenXinXi.fxml"));
			Scene s=new Scene(root);
			
			Stage primaryStage=(Stage) cx.getScene().getWindow();
			
			//primaryStage.hide();
			primaryStage.setScene(s);
			
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	public void chaxun() {
		
		String str=text1.getText();
		Vector<DiseaseType>v=Bingzhong.read();
		List<Patient>listp=new ArrayList<>();
		
		
		for(DiseaseType d:v) {
			if(d.getName().equals(str)) {
				listp=rec(d,listp);
			}
		}
		
		
		ObservableList<Patient>patientlist= FXCollections.observableArrayList();
		
		for(Patient p:listp) {
			patientlist.add(p);
		}
		
		c1.setCellValueFactory( new PropertyValueFactory<>("id"));
		c2.setCellValueFactory( new PropertyValueFactory<>("name"));
		T1.setItems(patientlist);
		
		T1.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue)->{if (newValue != null) {
			
			String sid=newValue.getId();
			MyHashTable<String,String>ht0=new MyHashTable<>();
			MyHashTable<String,String>ht=ht0.read();
			String sc=ht.get(sid);
			Case c=JSON.parseObject(sc,Case.class);
			
			Certificate cer=c.getC();
			List<DiseaseType>listdis=cer.getDiseases();
			
			ObservableList<DiseaseType>caselist= FXCollections.observableArrayList();
				for(DiseaseType dis:listdis) {
					caselist.add(dis);
				}
				
				c3.setCellValueFactory( new PropertyValueFactory<>("name"));
				T2.setItems(caselist);
			
	       }
	    });
	
		
	}
	
	
	
	
	//ID排序
	public void sortingID() {
		ObservableList<Patient>pl= FXCollections.observableArrayList();
		Patient[] a=MyQuickSortP.quickSort(T1.getItems(), true);
		T1.getItems().clear();
		
		for(int i=0;i<a.length;i++) {
			pl.add(a[i]);
		}
		
		c1.setCellValueFactory( new PropertyValueFactory<>("id"));
		c2.setCellValueFactory( new PropertyValueFactory<>("name"));
		T1.setItems(pl);
		
	}
	
	
	
	
	//姓名排序
	public void sortingName() {
		ObservableList<Patient>pl= FXCollections.observableArrayList();
		Patient[] a=MyQuickSortP.quickSort(T1.getItems(), false);
		T1.getItems().clear();
		
		for(int i=0;i<a.length;i++) {
			pl.add(a[i]);
		}
		
		c1.setCellValueFactory( new PropertyValueFactory<>("id"));
		c2.setCellValueFactory( new PropertyValueFactory<>("name"));
		T1.setItems(pl);
		
	}
	
	
	

	
	
	
	// 递归查询某一个大的病种下的所有病种及病人
		private static List<Patient> rec(DiseaseType node,List<Patient>lp) {
			
			if(node.getSub_diseases().isEmpty()) {
				
				
				Vector<Patient>vp=node.getPatient();
				
				for(Patient p:vp) {
					lp.add(p);
				}
					
				return lp;
			}
			
			
			else {
				Vector<DiseaseType>v11=node.getSub_diseases();
						
	    		for(DiseaseType d1:v11) {
	    			
	    			// 递归
	    			lp=rec(d1,lp);
	    			
	    		}
			}
			return lp;
	
		}
	

}
