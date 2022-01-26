package util;



import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
	public static Connection getConnection() {
		try {
			/*
			 java.sql.SQLNonTransientConnectionException: No operations allowed after connection closed.
			 の例外のためautoReconnect=trueを追加
			*/
			String dbURL = "jdbc:mysql://1252SV02/20gr22?serverTimezone=UTC&autoReconnect=true";
			String dbID = "20gr22";
			String dbPassword = "20gr22";
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
