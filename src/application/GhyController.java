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
		
		choicebox1.getItems().addAll("��","Ů");
		choicebox1.getSelectionModel().selectFirst();
		choicebox2.getItems().addAll("�Է�","��ҽ��");
		choicebox2.getSelectionModel().selectFirst();
		choicebox3.getItems().addAll("����","����");
		choicebox3.getSelectionModel().selectFirst();
		choicebox4.getItems().addAll("�ǿ�","��Ѫ�����","�����");

		
		//�л����ҵ�ͬʱ�л�ҽ��
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
		
		choicebox5.getItems().addAll("ר�Һ�","��ͨ��");
		choicebox5.getSelectionModel().selectFirst();
		choicebox6.getItems().addAll("�ֽ�","΢��","֧����");
		choicebox6.getSelectionModel().selectFirst();
		
		
		//��������Ĳ�����
		Integer random= (int) Math.floor(Math.random()*100000+10000);
		RandomText.setText(random.toString());
		

	}
	
	//�л����ҺŽ���
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
	
	
	
	
	
	//�л������ѽ���
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
	
	
	
	//�л����˺Ž���
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
	

	
	
	
	//�ӹҺ�ҳ���л������ѽ���
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
	
	
	
	
	//�Һ�
	public void guahao() {
		if(Patient.GuaHao(choiceboxdoc.getValue(), NameText.getText(), AgeText.getText(), RandomText.getText(),choicebox1.getValue())&&NameText.getText()!=null) {
			
			if(choicebox5.getValue().equals("ר�Һ�")) {
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
			
			guahaosuccess.setText("�Һųɹ���");
			
		}
		
		else {
			guahaosuccess.setText("�Һ�ʧ�ܣ�������������Ϣ��");
		}
	}
	
	
	
	public void qingkong() {
		NameText.setText(null);
		AgeText.setText(null);
		IdText.setText(null);
		cashText.setText(null);
		GuaHaoSuccess.setText("   ");
		
		//��������ɲ�����
		Integer r= (int) Math.floor(Math.random()*100000+10000);
		RandomText.setText(r.toString());
		
	}
	
	
	
	
}
