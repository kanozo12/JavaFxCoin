package layout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import jdbc.JdbcUtil;
import jdbc.UserDAO;

public class LoginController {
	@FXML
	private Label lblErrors;
	@FXML
	private TextField txtId;
	@FXML
	private PasswordField txtPass;
	@FXML
	private AnchorPane loginPage;
	@FXML
	private Button btnLogin;
	@FXML
	private Button btnSingUp;

	private boolean checkLogin() {
		UserDAO dao = new UserDAO();

		String id = txtId.getText();
		String pw = txtPass.getText();

		try {
			int rst = dao.loginCheck(id, pw);
			
			if(rst == 1) {
				System.out.println("로그인 성공");
				return true;
			} else {
				System.out.println("로그인 실패");
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void onKeyPress(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			handleBtnLogin();
		}
	}

	public void handleBtnLogin() {
		if (!checkLogin()) {
			// lblStatus.setText("잘못된 아이디 또는 비밀번호");
			return;
		}
		try {
			StackPane mainPage = (StackPane) btnLogin.getScene().getRoot();

			Timeline timeline = new Timeline();
			KeyValue keyValue = new KeyValue(loginPage.opacityProperty(), 0);

			KeyFrame keyFrame = new KeyFrame(Duration.millis(800), (ActionEvent event) -> {
				mainPage.getChildren().remove(loginPage);
			}, keyValue);

			timeline.getKeyFrames().add(keyFrame);
			timeline.play();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("화면전환중 오류 발생");
			lblErrors.setText("화면전환 오류 발생");
		}
	}

	public void handleBtnSignUp() {
		try {
			Parent members = FXMLLoader.load(getClass().getResource("SingUpLayout.fxml"));
			StackPane root = (StackPane) btnSingUp.getScene().getRoot();

			Timeline timeline = new Timeline();
			KeyValue keyValue = new KeyValue(loginPage.opacityProperty(), 1);

			KeyFrame keyFrame = new KeyFrame(Duration.millis(800), (ActionEvent event) -> {
				root.getChildren().add(members);
			}, keyValue);

			timeline.getKeyFrames().add(keyFrame);
			timeline.play();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("화면전환중 오류 발생");
			lblErrors.setText("화면전환 오류 발생");
		}
	}

}
