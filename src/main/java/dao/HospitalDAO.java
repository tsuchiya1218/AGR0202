package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import model.Electronic_prescriptionBean;
import model.HospitalBean;
import util.Close;
import util.DBconnection;
import util.SHA256;

public class HospitalDAO {
	private HospitalDAO() {
	}

	private static class LazyHolder {
		public static final HospitalDAO INSTANCE = new HospitalDAO();
	}

	public static HospitalDAO getInstance() {
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
			SQL = "UPDATE hospital SET h_num =@count:=@count+1;";
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

	public boolean isDuplicateEmail(String h_email) {
		String SQL = "SELECT h_email FROM hospital WHERE h_email = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(h_email));
			rs = pstmt.executeQuery();
			return rs.next();
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

	public List<Electronic_prescriptionBean> getEpList(int h_num) {
		String SQL = "SELECT * FROM electronic_prescription WHERE ep_d_num = "
				+ "(SELECT d_num FROM doctor WHERE d_h_num = (SELECT h_num FROM hospital WHERE h_num = ?)) AND "
				+ "ep_reg_date LIKE ? ORDER BY ep_reg_date DESC";
		List<Electronic_prescriptionBean> epList = new ArrayList<>();
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN);
			String todayDate = sdf.format(date).replaceAll("/", "-");
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, h_num);
			pstmt.setString(2, todayDate+"%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Electronic_prescriptionBean ep = new Electronic_prescriptionBean();
				ep.setEp_num(rs.getInt("ep_num"));
				ep.setEp_m_num(rs.getInt("ep_m_num"));
				ep.setEp_reg_date(rs.getString("ep_reg_date").substring(0, 16));
				ep.setEp_auth(rs.getBoolean("ep_auth"));
				epList.add(ep);
			}
			return epList;

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

	public boolean updateEpAuth(int ep_num) {
		String SQL = "SELECT  FROM hospital WHERE h_email = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setBoolean(1, true);
			pstmt.setInt(2, ep_num);
			rs = pstmt.executeQuery();
			return rs.next();
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

	public String login(String h_email, String h_pw) throws SQLException {
		String SQL = "SELECT h_pw FROM hospital WHERE h_email = ?";

		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(h_email));
			rs = pstmt.executeQuery();
			conn.commit();
			if (rs.next()) {
				// ユーザが書いたpwとDBのpwと同じだったらログイン成功
				if (rs.getString("h_pw").equals(SHA256.getEncrypt(h_pw))) {
					return "hospital";
				}
			}
			return null;
		} catch (Exception e) {
			conn.rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				Close.close(conn, pstmt, rs);
			} catch (Exception e) {
				conn.rollback();
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public HospitalBean getHospitalBean(String h_email) {
		String SQL = "SELECT * FROM hospital WHERE h_email = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(h_email));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				HospitalBean hospital = new HospitalBean(
						rs.getInt("h_num"), 
						rs.getString("h_name"),
						rs.getString("h_email"), 
						rs.getString("h_pw"), 
						rs.getString("h_tel"),
						rs.getString("h_address")
						);
				return hospital;
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

	public String findByD_h_numToH_name(int d_h_num) throws SQLException {
		String SQL = "SELECT h_name FROM hospital WHERE h_num = "
				+ "(SELECT d_h_num FROM doctor WHERE d_num = "
				+ "(SELECT ep_d_num FROM electronic_prescription WHERE ep_num = ?))";

		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, d_h_num);
			rs = pstmt.executeQuery();
			conn.commit();
			if (rs.next()) {
				return rs.getString("h_name");
			}
			return null;
		} catch (Exception e) {
			conn.rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				Close.close(conn, pstmt, rs);
			} catch (Exception e) {
				conn.rollback();
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public String findByD_numToH_tel(int d_h_num) throws SQLException {
		String SQL = "SELECt h_tel FROM hospital WHERE h_num = ?";

		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, d_h_num);
			rs = pstmt.executeQuery();
			conn.commit();
			if (rs.next()) {
				return rs.getString("h_tel");
			}
			return null;
		} catch (Exception e) {
			conn.rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				Close.close(conn, pstmt, rs);
			} catch (Exception e) {
				conn.rollback();
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public HospitalBean findByH_numToDoctor_hospital_num(int d_h_num) throws SQLException {
		String SQL = "SELECT * FROM hospital WHERE h_num = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, d_h_num);
			rs = pstmt.executeQuery();
			conn.commit();
			if (rs.next()) {
				HospitalBean hospital = new HospitalBean();
				hospital.setH_num(rs.getInt("H_num"));
				hospital.setH_name(rs.getString("H_name"));
				hospital.setH_tel(rs.getString("h_tel"));
				hospital.setH_address(rs.getString("h_address"));
				return hospital;
			}
			return null;
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
