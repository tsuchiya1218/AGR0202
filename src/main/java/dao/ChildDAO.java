package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ChildBean;
import util.Close;
import util.DBconnection;

public class ChildDAO {
	private ChildDAO() {}
	
	private static class LazyHolder {
		public static final ChildDAO INSTANCE = new ChildDAO();
	}

	public static ChildDAO getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public boolean addChild(ChildBean child) throws SQLException {
		String SQL = "INSERT INTO child VALUES(null,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			Date birth = Date.valueOf(child.getC_birth());
			
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, child.getC_m_num());
			pstmt.setString(2, child.getC_medical_num());
			pstmt.setString(3, child.getC_i_num());
			pstmt.setString(4, child.getC_i_expiry_date());
			pstmt.setString(5, child.getC_i_mark());
			pstmt.setString(6, child.getC_name());
			pstmt.setString(7, child.getC_kana());
			pstmt.setDate(8, birth);
			pstmt.setString(9, child.getC_gender());
			pstmt.setString(10, child.getC_blood_type());
			pstmt.setString(11, child.getC_medical_history());
			pstmt.setString(12, child.getC_medication());
			pstmt.setString(13, child.getC_allergy());
			
			int result = pstmt.executeUpdate();
			conn.commit();
			if(result > 0) return true;
			
			return false;
		} catch (SQLException sqle) {
			conn.rollback();
			throw new RuntimeException(sqle.getMessage());
		} catch (NullPointerException nule) {
			conn.rollback();
			throw new RuntimeException(nule.getMessage());
		} finally {
			try {
				Close.close(conn, pstmt, null);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public boolean deleteChild(int c_num) throws SQLException {
		String SQL = "DELETE FROM Child WHERE c_num = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, c_num);

			int result = pstmt.executeUpdate();
			conn.commit();

			if (result > 0)
				return true;
			return false;
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

	public boolean updateChild(ChildBean child) throws SQLException {
		String SQL = "UPDATE child SET c_medical_num = ?, c_i_num = ?, c_i_expiry_date = ?,"
				+ "c_i_mark = ?, c_name = ?, c_kana = ?, c_birth = ?, c_gender = ?, c_blood_type = ?,"
				+ "c_medical_history = ?, c_medication = ?, c_allergy = ? WHERE c_num = ?";
		try {
			Date birth = Date.valueOf(child.getC_birth());
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, child.getC_medical_num());
			pstmt.setString(2, child.getC_i_num());
			pstmt.setString(3, child.getC_i_expiry_date());
			pstmt.setString(4, child.getC_i_mark());
			pstmt.setString(5, child.getC_name());
			pstmt.setString(6, child.getC_kana());
			pstmt.setDate(7, birth);
			pstmt.setString(8, child.getC_gender());
			pstmt.setString(9, child.getC_blood_type());
			pstmt.setString(10, child.getC_medical_history());
			pstmt.setString(11, child.getC_medication());
			pstmt.setString(12, child.getC_allergy());
			pstmt.setInt(13, child.getC_num());

			int result = pstmt.executeUpdate();
			conn.commit();

			if (result > 0) return true;
			
			return false;
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

	public List<ChildBean> getChild(int c_m_num) throws SQLException {
		String SQL = "SELECT * FROM child WHERE c_m_num = ?";
		List<ChildBean> childList = new ArrayList<>();
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, c_m_num);
			rs = pstmt.executeQuery();
			conn.commit();
			while(rs.next()) {
				ChildBean child = new ChildBean(
							rs.getInt("c_num"),
							rs.getInt("c_m_num"),
							rs.getString("c_medical_num"),
							rs.getString("c_i_num"),
							rs.getString("c_i_expiry_date"),
							rs.getString("c_i_mark"),
							rs.getString("c_name"),
							rs.getString("c_kana"),
							rs.getString("c_birth"),
							rs.getString("c_gender"),
							rs.getString("c_blood_type"),
							rs.getString("c_medical_history"),
							rs.getString("c_medication"),
							rs.getString("c_allergy")
						);
				childList.add(child);
			}
			
			return childList;
		} catch (SQLException sqle) {
			conn.rollback();
			throw new RuntimeException(sqle.getMessage());
		} catch (NullPointerException nule) {
			conn.rollback();
			throw new RuntimeException(nule.getMessage());
		}finally {
			try {
				Close.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	public boolean isDuplicateMedical_num(String c_medical_num) {
		String SQL = "SELECT c_medical_num FROM child WHERE c_medical_num = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, c_medical_num);
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
	public boolean isDuplicateC_i_num(String c_i_num) {
		String SQL = "SELECT c_i_num FROM child WHERE  c_i_num = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, c_i_num);
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
	
	
	public String countBirth(String c_birth) throws SQLException{
		String SQL = "SELECT ROUND((TO_DAYS(NOW()) - (TO_DAYS(?))) / 365) AS age;";
		try {
			String[] birth = c_birth.split("-");
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, birth[0]+birth[1]+birth[2]);
			rs = pstmt.executeQuery();
			conn.commit();
			if(rs.next()) {
				return rs.getString("age"); 
			}
			return null;
		}catch (SQLException sqle) {
			conn.rollback();
			throw new RuntimeException(sqle.getMessage());
		} catch (Exception e) {
			conn.rollback();
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
