package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Close;
import util.DBconnection;

public class DoctorDAO {
	private DoctorDAO() {
	}

	private static class LazyHolder {
		public static final DoctorDAO INSTANCE = new DoctorDAO();
	}

	public static DoctorDAO getInstance() {
		return LazyHolder.INSTANCE;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public void sortD_num() throws SQLException {
		try {
			String SQL = "SET @COUNT=0;";
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.executeQuery();
			SQL = "UPDATE doctor SET d_num =@count:=@count+1;";
			pstmt.executeUpdate(SQL);
			conn.commit();
		} catch (SQLException sqle) {
			conn.rollback();
			throw new RuntimeException(sqle.getMessage());
		} catch (NullPointerException nule) {
			conn.rollback();
			throw new RuntimeException(nule.getMessage());
		} finally {
			try {
				Close.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
}
