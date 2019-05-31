package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcUtil {
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB연결 라이브러리가 없습니다");
			return null;
		}
		//gondr.asuscomm.com/phpmyadmin
		String cString = "jdbc:mysql://gondr.asuscomm.com/kanozo12"
				+ "?useUnicode=true"
				+ "&characterEncoding=utf8"
				+ "&useSSL=false"
				+ "&serverTimezone=Asia/Seoul";
		String userId = "kanozo12";
		String password = "1234";
		
		Connection con = null;
	
		try {
			con = DriverManager.getConnection(
					cString, userId, password);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB연결 오류 발생");
		}
		return con;
	}
}
