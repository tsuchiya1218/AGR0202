package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ChildBean;
import model.MemberBean;
import model.PharmacyBean;
import model.QuestionnaireBean;
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
	
	public String login(String p_email, String p_pw) throws SQLException {
		String SQL = "SELECt p_pw FROM pharmacy WHERE p_email = ?";
		
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(p_email));
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
						rs.getString("p_address")
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
	
	public MemberBean findByQr_numToMember(String m_qr_num) {
		String SQL = "SELECT * FROM member WHERE m_qr_num = ? AND m_leave = false ";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, m_qr_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				MemberBean member = new MemberBean(
						rs.getInt("m_num"),
						rs.getString("m_email"),
						rs.getString("m_pw"),
						rs.getString("m_name"),
						rs.getString("m_kana"),
						rs.getString("m_birth").toString(),
						rs.getString("m_tel"),
						rs.getString("m_gender"),
						rs.getString("m_zip_code"),
						rs.getString("m_address"),
						rs.getInt("m_q_num"),
						rs.getString("m_i_num"),
						rs.getString("m_i_expiry_date"),
						rs.getString("m_i_mark"),
						rs.getString("m_qr_num"),
						rs.getBoolean("m_auth"),
						rs.getBoolean("m_leave")
						);
				return member;
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
	
	public QuestionnaireBean findByM_numToQuestionnaire(int m_q_num) {
		String SQL = "SELECT * FROM questionnaire WHERE q_num = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, m_q_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				QuestionnaireBean questionnaire = new QuestionnaireBean(
						rs.getInt("q_num"),
						rs.getString("q_blood_type"),
						rs.getString("q_medical_history"),
						rs.getString("q_medication"),
						rs.getBoolean("q_drink"),
						rs.getBoolean("q_smoke"),
						rs.getBoolean("q_pregnancy"),
						rs.getString("q_allergy")
						);
				return questionnaire;
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
	
	public List<ChildBean> getChild(int m_num) throws SQLException {
		String SQL = "SELECT * FROM child WHERE c_m_num = ?";
		List<ChildBean> childList = new ArrayList<>();
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, m_num);
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
	
	
}
