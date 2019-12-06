package application.model;

import javafx.scene.control.Alert;

public class MyAlert {
	
	public void Alert(String text) {
		Alert a=new Alert(Alert.AlertType.ERROR);
		a.setHeaderText(text);
		a.showAndWait();
	}

	
	
}
