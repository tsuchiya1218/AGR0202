package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
	public static Connection getConnection() { //static을 사용하게되면 인스턴스를 만들지않고도 사용가능
		try {
			/*
			 java.sql.SQLNonTransientConnectionException: No operations allowed after connection closed.
			 の例外のためautoReconnect=trueを追加
			*/
			String dbURL = "jdbc:mysql://localhost:3306/20gr22?serverTimezone=UTC&autoReconnect=true";
			String dbID = "root";
			String dbPassword = "1041";
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // 오류가 발생시
	}
}
