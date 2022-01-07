package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Electronic_prescriptionBean;
import util.Close;
import util.DBconnection;

public class Electronic_prescriptionDAO {
	private Electronic_prescriptionDAO() {
	}

	private static class LazyHolder {
		public static final Electronic_prescriptionDAO INSTANCE = new Electronic_prescriptionDAO();
	}

	public static Electronic_prescriptionDAO getInstance() {
		return LazyHolder.INSTANCE;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public String getNow() {
		String SQL = "SELECT NOW()";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.executeQuery();
			
			rs = pstmt.executeQuery(SQL);
			if(rs.next()) {
				return rs.getString(1);
			}
			return null;
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle.getMessage());
		} catch (NullPointerException nule) {
			throw new RuntimeException(nule.getMessage());
		} finally {
			try {
				Close.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public int createEp(Electronic_prescriptionBean ep) throws SQLException {
		String SQL = "INSERT INTO electronic_prescription VALUES(null,?,?,null,?,NOW(),?,?,?,?,?,?,?)";
		try {
			Date expiry_date = Date.valueOf(ep.getEp_expiry_date());
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, ep.getEp_d_num());
			pstmt.setInt(2, ep.getEp_m_num());
			pstmt.setDate(3, expiry_date);
			pstmt.setString(4, ep.getEp_patient_type());
			pstmt.setString(5, ep.getEp_burden_num());
			pstmt.setString(6, ep.getEp_burden_person());
			pstmt.setString(7, ep.getEp_disease());
			pstmt.setString(8, ep.getEp_note());
			pstmt.setBoolean(9, ep.isEp_auth());
			pstmt.setInt(10, ep.getEp_c_num());
			pstmt.executeUpdate();
			conn.commit();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
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
	
	public boolean updateElectronic_prescriptionToDrug_infomation_num(int di_num,int ep_num) throws SQLException {
		String SQL = "UPDATE electronic_prescription SET ep_di_num = ? WHERE ep_num = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, di_num);
			pstmt.setInt(2, ep_num);

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
	
	public boolean updateElectronic_prescription(Electronic_prescriptionBean ep) throws SQLException {
		String SQL = "UPDATE electronic_prescription SET ep_expiry_date = ?,ep_patient_type = ?, ep_burden_num = ?, "
				+ "ep_burden_person = ?, ep_disease = ?, ep_note = ? WHERE ep_num = ?";
		try {
			Date ep_expiry_date = Date.valueOf(ep.getEp_expiry_date());
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setDate(1, ep_expiry_date);
			pstmt.setString(2, ep.getEp_patient_type());
			pstmt.setString(3, ep.getEp_burden_num());
			pstmt.setString(4, ep.getEp_burden_person());
			pstmt.setString(5, ep.getEp_disease());
			pstmt.setString(6, ep.getEp_note());
			pstmt.setInt(7, ep.getEp_num());

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
	
	public List<Electronic_prescriptionBean> findByM_numAndC_numToEp_num(int m_num, int c_num) throws SQLException {
		String SQL = "SELECt * FROM electronic_prescription WHERE ep_m_num = ? AND ep_c_num = ? ORDER BY ep_reg_date DESC";
		List<Electronic_prescriptionBean> epList = new ArrayList<>();
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, m_num);
			pstmt.setInt(2, c_num);
			rs = pstmt.executeQuery();
			conn.commit();
			while(rs.next()) {
				Electronic_prescriptionBean ep = new Electronic_prescriptionBean(
						rs.getInt("ep_num"),
						rs.getInt("ep_d_num"),
						rs.getInt("ep_m_num"),
						rs.getInt("ep_di_num"),
						rs.getString("ep_expiry_date"),
						rs.getString("ep_reg_date").substring(0, 11),
						rs.getString("ep_patient_type"),
						rs.getString("ep_burden_num"),
						rs.getString("ep_burden_person"),
						rs.getString("ep_disease"),
						rs.getString("ep_note"),
						rs.getBoolean("ep_auth"),
						rs.getInt("ep_c_num")
						);
				epList.add(ep);
			}
			return epList;
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
	

	public Electronic_prescriptionBean findByEp_num(int ep_num) throws SQLException {
		String SQL = "SELECt * FROM electronic_prescription WHERE ep_num = ? ORDER BY ep_reg_date DESC";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  ep_num);
			rs = pstmt.executeQuery();
			conn.commit();
			if(rs.next()) {
				Electronic_prescriptionBean ep = new Electronic_prescriptionBean(
						rs.getInt("ep_num"),
						rs.getInt("ep_d_num"),
						rs.getInt("ep_m_num"),
						rs.getInt("ep_di_num"),
						rs.getString("ep_expiry_date"),
						rs.getString("ep_reg_date").substring(0, 11),
						rs.getString("ep_patient_type"),
						rs.getString("ep_burden_num"),
						rs.getString("ep_burden_person"),
						rs.getString("ep_disease"),
						rs.getString("ep_note"),
						rs.getBoolean("ep_auth"),
						rs.getInt("ep_c_num")
						);
				return ep;
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
	
	public Electronic_prescriptionBean findByDi_numToEpBean(int di_num) throws SQLException {
		String SQL = "SELECt * FROM electronic_prescription WHERE ep_di_num = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  di_num);
			rs = pstmt.executeQuery();
			conn.commit();
			if(rs.next()) {
				Electronic_prescriptionBean ep = new Electronic_prescriptionBean(
						rs.getInt("ep_num"),
						rs.getInt("ep_d_num"),
						rs.getInt("ep_m_num"),
						rs.getInt("ep_di_num"),
						rs.getString("ep_expiry_date"),
						rs.getString("ep_reg_date").substring(0, 11),
						rs.getString("ep_patient_type"),
						rs.getString("ep_burden_num"),
						rs.getString("ep_burden_person"),
						rs.getString("ep_disease"),
						rs.getString("ep_note"),
						rs.getBoolean("ep_auth"),
						rs.getInt("ep_c_num")
						);
				return ep;
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
	
	public int findByEp_numToM_num(int ep_num) throws SQLException {
		String SQL = "SELECt ep_m_num FROM electronic_prescription WHERE ep_num = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  ep_num);
			rs = pstmt.executeQuery();
			conn.commit();
			if(rs.next()) {
				return rs.getInt("ep_m_num");
			}
			return 0;
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
	
	public boolean updateAuth(int ep_num) throws SQLException {
		String SQL = "UPDATE electronic_prescription SET ep_auth = ? WHERE ep_num = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setBoolean(1, true);
			pstmt.setInt(2, ep_num);

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

}
