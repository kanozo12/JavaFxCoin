package util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Utility {
	public static void alert(String title, String msg) {
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle(title);
		a.setHeaderText(null);
		a.setContentText(msg);
		
		a.showAndWait();
	}
}
