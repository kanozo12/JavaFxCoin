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
		String id = txtId.getText();
		String pw = txtPass.getText();

		Connection con = JdbcUtil.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM coinUsers WHERE id = ? AND pass = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();

			if (rs.next()) { // 그 다음원소가 존재하니?
				// 로그인 성공했다는 것
				String name = rs.getString("name");
				int level = rs.getInt("level");
				System.out.println(name + "(" + level + ")님 로그인");
				lblErrors.setTextFill(Color.GREEN);
				lblErrors.setText("로그인 성공 ");
				return true;
			} else {
				lblErrors.setTextFill(Color.RED);
				lblErrors.setText("잘못된 아이디 또는 비밀번호입니다.");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			lblErrors.setText("알수없는 오류 발생. 로그 확인");
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
