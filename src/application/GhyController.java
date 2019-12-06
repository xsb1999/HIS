package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import application.model.Doctor;
import application.model.Patient;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GhyController {
	@FXML
	private MenuItem GuaHao;

	@FXML
	private Label GuaHaoSuccess=new Label();
	
	@FXML
	private TextArea DoctorText;
	
	@FXML
	private TextArea NameText;
	
	@FXML
	private TextArea AgeText;
	
	@FXML
	private TextArea IdText;
	
	@FXML
	private DatePicker dateText;
	
	@FXML
	private TextArea cashText;
	
	
	@FXML
	private TextArea RandomText=new TextArea();
	
	@FXML
	private Label guahaosuccess;

	
	@FXML
	private CheckBox checkbox2;
	
	@FXML
	private ChoiceBox<String> choicebox1=new ChoiceBox<String>();
	
	
	@FXML
	private ChoiceBox<String> choicebox2=new ChoiceBox<String>();
	
	@FXML
	private ChoiceBox<String> choicebox3=new ChoiceBox<String>();
	
	@FXML
	private ChoiceBox<String> choicebox4=new ChoiceBox<String>();
	
	@FXML
	private ChoiceBox<String> choicebox5=new ChoiceBox<String>();
	
	@FXML
	private ChoiceBox<String> choicebox6=new ChoiceBox<String>();
	
	@FXML
	private ChoiceBox<String> choiceboxdoc=new ChoiceBox<String>();
	
	@FXML
	private Button but_;
	@FXML
	private Button keshiBut_;
	
	
	public MenuItem getGuaHao() {
		return GuaHao;
	}


	public void setGuaHao(MenuItem guaHao) {
		GuaHao = guaHao;
	}


	public Label getGuaHaoSuccess() {
		return GuaHaoSuccess;
	}


	public void setGuaHaoSuccess(Label guaHaoSuccess) {
		GuaHaoSuccess = guaHaoSuccess;
	}

	
	
	@FXML
	public void initialize() {
		choicebox1.getItems().clear();
		choicebox2.getItems().clear();
		choicebox3.getItems().clear();
		choicebox4.getItems().clear();
		choicebox5.getItems().clear();
		choicebox6.getItems().clear();
		choiceboxdoc.getItems().clear();
		
		choicebox1.getItems().addAll("男","女");
		choicebox1.getSelectionModel().selectFirst();
		choicebox2.getItems().addAll("自费","市医保");
		choicebox2.getSelectionModel().selectFirst();
		choicebox3.getItems().addAll("上午","下午");
		choicebox3.getSelectionModel().selectFirst();
		choicebox4.getItems().addAll("骨科","心血管外科","胸外科");

		
		//切换科室的同时切换医生
		choicebox4.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue)->{if (newValue != null) {
						
						List<Doctor>listd=new ArrayList<>();
						listd=Doctor.readD();
						
						choiceboxdoc.getItems().clear();
						for(Doctor d:listd) {
							if(d.getDepartment().equals(newValue)) {
								choiceboxdoc.getItems().add(d.getName());
								choiceboxdoc.getSelectionModel().selectFirst();
							}
						}
						
				}
			});
		
		choicebox5.getItems().addAll("专家号","普通号");
		choicebox5.getSelectionModel().selectFirst();
		choicebox6.getItems().addAll("现金","微信","支付宝");
		choicebox6.getSelectionModel().selectFirst();
		
		
		//生成随机的病历号
		Integer random= (int) Math.floor(Math.random()*100000+10000);
		RandomText.setText(random.toString());
		

	}
	
	//切换到挂号界面
	public void guahaoYeMian() {
		AnchorPane root;
		try {
			root = FXMLLoader.load(getClass().getResource("guahao/GuaHaoXinXi.fxml"));
			
			Scene scene=new Scene(root);
			
			Stage primaryStage=(Stage) but_.getScene().getWindow();
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
			
			Stage primaryStage=(Stage) but_.getScene().getWindow();
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
			Stage primaryStage=(Stage) but_.getScene().getWindow();
			primaryStage.hide();
		    primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
	
	
	//从挂号页面切换到交费界面
	public void qiehuan() {
		AnchorPane root;
		try {
			root = FXMLLoader.load(getClass().getResource("jiaofei/JiaoFei1.fxml"));
			
			Scene scene=new Scene(root);
			
			Stage primaryStage=(Stage) but_.getScene().getWindow();
			primaryStage.hide();
		    primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	//挂号
	public void guahao() {
		if(Patient.GuaHao(choiceboxdoc.getValue(), NameText.getText(), AgeText.getText(), RandomText.getText(),choicebox1.getValue())&&NameText.getText()!=null) {
			
			if(choicebox5.getValue().equals("专家号")) {
				if(checkbox2.isSelected()) {
					cashText.setText("71");
				}
				else {
				cashText.setText("70");
			}
			}		
			else {
				if(checkbox2.isSelected()) {
				cashText.setText("51");
				}
				else {
					cashText.setText("50");
				}
			}
			
			guahaosuccess.setText("挂号成功！");
			
		}
		
		else {
			guahaosuccess.setText("挂号失败，请输入完整信息！");
		}
	}
	
	
	
	public void qingkong() {
		NameText.setText(null);
		AgeText.setText(null);
		IdText.setText(null);
		cashText.setText(null);
		GuaHaoSuccess.setText("   ");
		
		//随机数生成病历号
		Integer r= (int) Math.floor(Math.random()*100000+10000);
		RandomText.setText(r.toString());
		
	}
	
	
	
	
}
