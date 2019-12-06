package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;



public class Main1 extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
//			FXMLLoader loader=new FXMLLoader();
//			loader.setLocation(Main.class.getResource("jiaofei/JiaoFei1.fxml"));
//			AnchorPane a=new AnchorPane();
//			a=(AnchorPane) loader.load();
			
			
			AnchorPane anchor1=FXMLLoader.load(getClass().getResource("administrator/Administrator2.fxml"));
			
			Scene scene = new Scene(anchor1);
		
			primaryStage.setScene(scene);
			
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

