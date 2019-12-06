package application;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import application.model.Case;
import application.model.Medicine;
import application.model.YaoFang;
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
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class FayaoController {
	@FXML
	private Button chaxun;
	@FXML
	private TextField text;
	@FXML
	private TableView<Medicine>T=new TableView<>();
	@FXML
	private TableColumn<Medicine,String>c1;
	@FXML
	private TableColumn <Medicine,String>c2;
	@FXML
	private TableColumn <Medicine,String>c3;
	@FXML
	private Button fayao;
	@FXML
	private Button shuaxin;
	@FXML
	private Label lab=new Label();
	@FXML
	private Label l=new Label();
	
	public void initialize() {
		l.setText("");
		lab.setText("");
		
	}

	
	public void chaxun() {
		List<YaoFang>listy=new ArrayList<>();
		List<Medicine>listM=new ArrayList<>();
		ObservableList<Medicine>listm= FXCollections.observableArrayList();
		String str=text.getText();
		List<Case>listc=Case.readCase();
		boolean b=false;
		for(Case c:listc) {
			if(c.getId().equals(str)) {
				b=true;
				if(!c.isJiaofeifou()) {
					lab.setText("该患者还未交费，不能发药！");
					break;
				} 
				
				if(!c.isYijingfayao()) {
					lab.setText("该患者还未开药，不能发药！");
					break;
				}
				
				else {
					lab.setText("");
					
				//显示所开的药品	
				l.setText("ok");
				listy=c.getYaofang();
				for(YaoFang y:listy) {
					listM=y.getListM();
					for(Medicine m:listM) {
						listm.add(m);
					}
				}
				break;
			}
			}
		}
		if(!b) {
			lab.setText("未查询到结果！");
		}
		
		c1.setCellValueFactory( new PropertyValueFactory<>("name"));
		c2.setCellValueFactory( new PropertyValueFactory<>("shuliang"));
		c3.setCellValueFactory( new PropertyValueFactory<>("price"));
		T.setItems(listm);
		
	}
	
	
	
	
	
	
	public void queren() {
		if(l.getText().equals("ok")) {
		List<YaoFang>listy=new ArrayList<>();
		lab.setText("发药成功！");
		l.setText("");
		
		String str=text.getText();
		List<Case>listc=Case.readCase();
		for(Case c:listc) {
			if(c.getId().equals(str)) {
				c.setYijingfayao(false);
				c.setYaofang(listy);
				break;
			}
		}
		
		//更新
				OutputStream out;
				try {
					out = new FileOutputStream("src/Cases");
					DataOutputStream dout=new DataOutputStream(out);
					Gson g=new Gson();
					String str1=g.toJson(listc);
					dout.writeUTF(str1);
					dout.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		else {
			lab.setText("无法发药！");
		}
		
		
	}
	
	
	
	
	
	public void shuaxin() {
		AnchorPane root;
		try {
			root = FXMLLoader.load(getClass().getResource("fayao/Fayao.fxml"));
			Scene s1=new Scene(root);
			Stage primaryStage=(Stage) shuaxin.getScene().getWindow();
			primaryStage.setScene(s1);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
