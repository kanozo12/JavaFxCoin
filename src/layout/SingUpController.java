package layout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import jdbc.JdbcUtil;

public class SingUpController {
	@FXML
	private AnchorPane loginPage;

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtpass;

	@FXML
	private TextField name;

	@FXML
	void onKeyPress(KeyEvent event) {

	}

	Connection con = JdbcUtil.getConnection();

	public String saveData() {

		String un = name.getText();
		String id = txtId.getText();
		String pw = txtpass.getText();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM users WHERE id = ? AND pass = ? AND name = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, un);
			rs = pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sql;
	}

}
