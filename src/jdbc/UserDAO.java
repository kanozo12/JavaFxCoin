package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	public int loginCheck(String id, String pass) {
		int result = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String loginCheckSql = "SELECT * FROM coinUsers WHERE id = ? and pass =?";
		
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(loginCheckSql);
			ps.setString(1, id);
			ps.setString(2, pass);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				String dbPass = rs.getString("pass");
				if(dbPass.equals(pass)) {
					result = 1;
				} else {
					result = 0;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if(ps!=null) ps.close();} catch(SQLException e) { }
			try { if(rs!=null) rs.close();} catch(SQLException e) { }
			try { if(conn!=null) conn.close();} catch(SQLException e) { }
		}
		 return result;
	}
	
}
