package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.PharmacyBean;
import util.Close;
import util.DBconnection;
import util.SHA256;

public class PharmacyDAO {
private PharmacyDAO() {}
	
	private static class LazyHolder {
		public static final PharmacyDAO INSTANCE = new PharmacyDAO();
	}

	public static PharmacyDAO getInstance() {
		return LazyHolder.INSTANCE;
	}
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public boolean isDuplicateEmail(String p_email) {
		String SQL = "SELECT p_email FROM pharmacy WHERE p_email = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(p_email));
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
	
	public String login(String p_email, String p_pw) throws SQLException {
		String SQL = "SELECt p_pw FROM pharmacy WHERE p_email = ? AND p_leave = ?";
		
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(p_email));
			pstmt.setBoolean(2, false);
			rs = pstmt.executeQuery();
			conn.commit();
			if(rs.next()) {
				//ユーザが書いたpwとDBのpwと同じだったらログイン成功
				if(rs.getString("p_pw").equals(SHA256.getEncrypt(p_pw))) {
					return "pharmacy";
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
	public PharmacyBean getPharmacyBean(String p_email) {
		String SQL = "SELECT * FROM pharmacy WHERE p_email = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(p_email));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				PharmacyBean pharmacy = new PharmacyBean(
						rs.getInt("p_num"),
						rs.getString("p_name"),
						rs.getString("p_email"),
						rs.getString("p_pw"),
						rs.getString("p_tel"),
						rs.getString("p_address"),
						rs.getBoolean("p_leave")
						);
				return pharmacy;
			}
			return null;
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
	
	public PharmacyBean findByPharmacyBeanToP_num(int p_num) {
		String SQL = "SELECT * FROM pharmacy WHERE p_num = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, p_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				PharmacyBean pharmacy = new PharmacyBean(
						rs.getInt("p_num"),
						rs.getString("p_name"),
						rs.getString("p_email"),
						rs.getString("p_pw"),
						rs.getString("p_tel"),
						rs.getString("p_address"),
						rs.getBoolean("p_leave")
						);
				return pharmacy;
			}
			return null;
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
	
	public String findBDi_p_numToP_name(int di_p_num) {
		String SQL = "SELECT p_name FROM pharmacy WHERE p_num = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, di_p_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString("p_name");
			}
			return null;
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
	
}
