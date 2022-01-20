package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DoctorBean;
import util.Close;
import util.DBconnection;
import util.SHA256;

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

	public String login(String d_email, String d_pw) throws SQLException {
		String SQL = "SELECt d_pw FROM doctor WHERE d_email = ? AND d_leave = ?";
		
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(d_email));
			pstmt.setBoolean(2, false);
			rs = pstmt.executeQuery();
			conn.commit();
			if(rs.next()) {
				if(rs.getString("d_pw").equals(SHA256.getEncrypt(d_pw))) {
					return "doctor";
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
	
	public DoctorBean getDoctorBean(String d_email) {
		String SQL = "SELECT * FROM doctor WHERE d_email = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(d_email));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				DoctorBean doctor = new DoctorBean(
						rs.getInt("d_num"),
						rs.getString("d_email"),
						rs.getString("d_pw"),
						rs.getString("d_name"),
						rs.getString("d_kana"),
						rs.getString("d_birth"),
						rs.getString("d_tel"),
						rs.getString("d_gender"),
						rs.getString("d_department"),
						rs.getInt("d_h_num"),
						rs.getBoolean("d_auth"),
						rs.getBoolean("d_leave")
						);
				return doctor;
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
	
	public DoctorBean findByD_numToDoctor(int d_num) throws SQLException {
		String SQL = "SELECT * FROM doctor WHERE d_num = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, d_num);
			rs = pstmt.executeQuery();
			conn.commit();
			if(rs.next()) {
				DoctorBean doctor = new DoctorBean();
				doctor.setD_num(rs.getInt("d_num"));
				doctor.setD_name(rs.getString("d_name"));
				doctor.setD_h_num(rs.getInt("d_h_num"));
				doctor.setD_department(rs.getString("d_department"));
				return doctor;
			}
			return null;
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
	
	public int findByD_numToD_h_num(int d_num) throws SQLException {
		String SQL = "SELECT d_h_num FROM doctor WHERE d_num = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, d_num);
			rs = pstmt.executeQuery();
			conn.commit();
			if(rs.next()) {
				return rs.getInt("d_h_num");
			}
			return 0;
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
	
	public boolean leave(String d_email) throws SQLException{
		String SQL = "UPDATE doctor SET d_leave = ? WHERE d_email = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setBoolean(1, true);
			pstmt.setString(2, d_email);
			int result = pstmt.executeUpdate();
			conn.commit();
			if(result == 1) return true;
			return false;
		}catch (SQLException sqle) {
			conn.rollback();
			throw new RuntimeException(sqle.getMessage());
		} catch (Exception e) {
			conn.rollback();
			throw new RuntimeException(e.getMessage());
		}finally {
			try {
				Close.close(conn, pstmt, null);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	
	public boolean findByEmail(String d_email) {
		String SQL = "SELECT d_num FROM doctor WHERE d_email = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, d_email);
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
	
	public String findByEmailToName(String d_email) {
		String SQL = "SELECT d_name FROM doctor WHERE d_email = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, d_email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString("d_name");
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
	
	public boolean findPassword(String d_email,String d_name) {
		String SQL = "SELECT d_email,d_name FROM doctor WHERE d_email = ? AND d_name = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(d_email));
			pstmt.setString(2, d_name);
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
	
	public boolean changePassword(String d_email, String d_pw) throws SQLException{
		String SQL = "UPDATE doctor SET d_pw = ? WHERE d_email = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(d_pw));
			pstmt.setString(2, d_email);
			int result = pstmt.executeUpdate();
			conn.commit();
			
			if(result == 1) return true;
			
			return false;
		}catch (SQLException sqle) {
			conn.rollback();
			throw new RuntimeException(sqle.getMessage());
		} catch (Exception e) {
			conn.rollback();
			throw new RuntimeException(e.getMessage());
		}finally {
			try {
				Close.close(conn, pstmt, null);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	public boolean isDuplicateEmail(String d_email) {
		String SQL = "SELECT d_email FROM doctor WHERE d_email = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(d_email));
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
	
	public boolean isAuth(String d_email) {
		String SQL = "SELECT d_auth FROM doctor WHERE d_email = ?";
		
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(d_email));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getBoolean("d_auth");
			}
		}catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}finally {
			try {
				Close.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return false;
	}
	
	public boolean checkPassword(String d_email, String d_pw) throws SQLException{
		String SQL = "SELECT d_pw FROM doctor WHERE d_email = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, d_email);
			rs = pstmt.executeQuery();
			conn.commit();
			
			if(rs.next()) {
				if(rs.getString("d_pw").equals(SHA256.getEncrypt(d_pw))) {
					return true;
				}
			}
			
			return false;
		}catch (SQLException sqle) {
			conn.rollback();
			throw new RuntimeException(sqle.getMessage());
		} catch (Exception e) {
			conn.rollback();
			throw new RuntimeException(e.getMessage());
		}finally {
			try {
				Close.close(conn, pstmt, null);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	public boolean updateDoctorEmail(String d_email, String new_email) throws SQLException{
		String SQL = "UPDATE doctor SET d_email = ?, d_auth = ? WHERE d_email = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(new_email));
			pstmt.setBoolean(2, false);
			pstmt.setString(3, d_email);
			int result = pstmt.executeUpdate();
			conn.commit();
			
			if(result == 1) return true;
			
			return false;
		}catch (SQLException sqle) {
			conn.rollback();
			throw new RuntimeException(sqle.getMessage());
		} catch (Exception e) {
			conn.rollback();
			throw new RuntimeException(e.getMessage());
		}finally {
			try {
				Close.close(conn, pstmt, null);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public boolean updateDoctor(DoctorBean doctor) throws SQLException {
		String SQL = "UPDATE doctor SET d_name =?,d_kana = ?, d_birth = ?, d_tel = ?, d_gender = ?,"
				+ " d_department = ? WHERE d_email = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, doctor.getD_name());
			pstmt.setString(2, doctor.getD_kana());
			pstmt.setString(3, doctor.getD_birth());
			pstmt.setString(4, doctor.getD_tel());
			pstmt.setString(5, doctor.getD_gender());
			pstmt.setString(6, doctor.getD_department());
			pstmt.setString(7, doctor.getD_email());
			int result = pstmt.executeUpdate();
			conn.commit();

			if (result > 0)
				return true;

			return false;
		} catch (SQLException sqle) {
			conn.rollback();
			throw new RuntimeException(sqle.getMessage());
		} catch (Exception e) {
			conn.rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				Close.close(conn, pstmt, null);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	public boolean updateDoctorWithPassword(DoctorBean doctor) throws SQLException {
		String SQL = "UPDATE doctor SET d_pw = ?, d_name =?,d_kana = ?, d_birth = ?, d_tel = ?, d_gender = ?,"
				+ " d_department = ? WHERE d_email = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(doctor.getD_pw()));
			pstmt.setString(2, doctor.getD_name());
			pstmt.setString(3, doctor.getD_kana());
			pstmt.setString(4, doctor.getD_birth());
			pstmt.setString(5, doctor.getD_tel());
			pstmt.setString(6, doctor.getD_gender());
			pstmt.setString(7, doctor.getD_department());
			pstmt.setString(8, doctor.getD_email());
			int result = pstmt.executeUpdate();
			conn.commit();

			if (result > 0)
				return true;

			return false;
		} catch (SQLException sqle) {
			conn.rollback();
			throw new RuntimeException(sqle.getMessage());
		} catch (Exception e) {
			conn.rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				Close.close(conn, pstmt, null);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	public boolean updateAuth(String d_email) throws SQLException{
		String SQL = "UPDATE doctor SET d_auth = ? WHERE d_email = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setBoolean(1, true);
			pstmt.setString(2, d_email);
			int result = pstmt.executeUpdate();
			conn.commit();
			if(result > 0) {
				return true;
			}
			return false;
		}catch (SQLException sqle) {
			conn.rollback();
			throw new RuntimeException(sqle.getMessage());
		} catch (Exception e) {
			conn.rollback();
			throw new RuntimeException(e.getMessage());
		}finally {
			try {
				Close.close(conn, pstmt, null);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	public String findByNumToName(int d_num) throws SQLException {
		String SQL = "SELECT d_name FROM doctor WHERE d_num = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, d_num);
			rs = pstmt.executeQuery();
			conn.commit();
			if(rs.next()) {
				return rs.getString("d_name");
			}
			return null;
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
}
