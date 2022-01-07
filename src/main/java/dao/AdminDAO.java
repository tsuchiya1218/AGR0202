package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.AdminBean;
import util.Close;
import util.DBconnection;
import util.SHA256;

public class AdminDAO {
	private AdminDAO() {
	}

	private static class LazyHolder {
		public static final AdminDAO INSTANCE = new AdminDAO();
	}

	public static AdminDAO getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public boolean isDuplicateEmail(String admin_email) {
		String SQL = "SELECT admin_email FROM admin WHERE admin_email = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(admin_email));
			rs = pstmt.executeQuery();
			return rs.next();
		}catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}finally {
			try {
				Close.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	public String login(String admin_email, String admin_pw) throws SQLException {
		String member = "SELECT admin_pw FROM admin WHERE admin_email = ?";
		
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(member);
			pstmt.setString(1, SHA256.getEncrypt(admin_email));
			pstmt.setBoolean(2, false);
			rs = pstmt.executeQuery();
			conn.commit();
			if(rs.next()) {
				//ユーザが書いたpwとDBのpwと同じだったらログイン成功
				if(rs.getString("admin_pw").equals(SHA256.getEncrypt(admin_pw))) {
					return "admin";
				}
			}
			return null;
		}catch (Exception e) {
			conn.rollback();
			throw new RuntimeException(e.getMessage());
		}finally {
			try {
				Close.close(conn, pstmt, rs);
			} catch (Exception e) {
				conn.rollback();
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	public AdminBean getAdminBean(String admin_email) {
		String SQL = "SELECT * FROM admin WHERE admin_email = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(admin_email));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				AdminBean admin = new AdminBean(
						rs.getString("admin_email"), 
						rs.getString("admin_pw")
						);
				return admin;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				Close.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
}
