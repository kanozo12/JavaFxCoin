package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import layout.MainController;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader mainLoader = new FXMLLoader();
			mainLoader.setLocation(
				getClass().getResource("/layout/MainLayout.fxml"));
			StackPane mainPage = mainLoader.load();
			
			MainController mc = mainLoader.getController();
			mc.init();
			
			FXMLLoader loginLoader = new FXMLLoader();
			loginLoader.setLocation(
				getClass().getResource("/layout/LoginLayout.fxml"));
			AnchorPane loginPage = loginLoader.load();
			
			Scene scene = new Scene(mainPage);
			mainPage.getChildren().add(loginPage);
			
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

