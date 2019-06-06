package layout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import jdbc.JdbcUtil;

public class SingUpController {
	@FXML
	private AnchorPane SignUpPage;
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtpass;
	@FXML
	private TextField name;
	@FXML
	private Button cancel;
	@FXML
	private Button SignUp;
	@FXML
	private Label lblErrors;

	Connection con = JdbcUtil.getConnection();

	public boolean handleBtnSignUp() {
		String userName = name.getText();
		String userId = txtId.getText();
		String userPass = txtpass.getText();

		Connection con = JdbcUtil.getConnection();

		PreparedStatement pstmt = null;
		int rs = 0;

		String sql = "INSERT INTO coinUsers (id, pass, name, level) VALUES (?, ?, ?, 1)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPass);
			pstmt.setString(3, userName);
			rs = pstmt.executeUpdate();
			
			if (rs == 1) {
				System.out.println("회원가입 성공");
				lblErrors.setTextFill(Color.GREEN);
				lblErrors.setText("회원가입 성공 ");

				StackPane root = (StackPane) cancel.getScene().getRoot();

				Timeline timeline = new Timeline();
				KeyValue keyValue = new KeyValue(SignUp.opacityProperty(), 1);

				KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), (ActionEvent event) -> {
					root.getChildren().remove(SignUpPage);
				}, keyValue);

				timeline.getKeyFrames().add(keyFrame);
				timeline.play();
				
				return true;
				
			} else {
				//duplicate 오류 수정 해야함
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

	public void handleBtnCancel() {
		StackPane root = (StackPane) cancel.getScene().getRoot();
		root.getChildren().remove(SignUpPage);
	}

	public void onKeyPress(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {

		}
	}

}
