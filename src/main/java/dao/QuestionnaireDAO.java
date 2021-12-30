package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.QuestionnaireBean;
import util.Close;
import util.DBconnection;

public class QuestionnaireDAO {
	private QuestionnaireDAO(){}
	
	private static class LazyHolder {
		public static final QuestionnaireDAO INSTANCE = new QuestionnaireDAO();
	}

	public static QuestionnaireDAO getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public boolean createQuestionnaire(QuestionnaireBean questionnaire) throws SQLException {
		String SQL = "INSERT INTO questionnaire VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, questionnaire.getQ_num());
			pstmt.setString(2, questionnaire.getQ_blood_type());
			pstmt.setString(3, questionnaire.getQ_medical_history());
			pstmt.setString(4, questionnaire.getQ_medication());
			pstmt.setBoolean(5, questionnaire.isQ_drink());
			pstmt.setBoolean(6, questionnaire.isQ_smoke());
			pstmt.setBoolean(7, questionnaire.isQ_pregnancy());
			pstmt.setString(8, questionnaire.getQ_allergy());
			pstmt.executeUpdate();
			conn.commit();
			return true;
		} catch (SQLException sqle) {
			conn.rollback();
			throw new RuntimeException(sqle.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				Close.close(conn, pstmt, null);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return false;
	}
	
	public QuestionnaireBean getQuestionnaire(int m_num) {
		String SQL = "SELECT * FROM questionnaire WHERE q_num = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, m_num);
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
	
	public boolean updateQuestionnaire(QuestionnaireBean questionnaire) {
		String SQL = "UPDATE questionnaire SET q_blood_type = ?, q_medical_history = ?, q_medication = ?,"
				+ "q_drink = ?, q_smoke = ?, q_pregnancy = ?, q_allergy = ? WHERE q_num = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, questionnaire.getQ_blood_type());
			pstmt.setString(2, questionnaire.getQ_medical_history());
			pstmt.setString(3, questionnaire.getQ_medication());
			pstmt.setBoolean(4, questionnaire.isQ_drink());
			pstmt.setBoolean(5, questionnaire.isQ_smoke());
			pstmt.setBoolean(6, questionnaire.isQ_pregnancy());
			pstmt.setString(7, questionnaire.getQ_allergy());
			pstmt.setInt(8, questionnaire.getQ_num());
			int result = pstmt.executeUpdate();
			if(result > 0) return true;
			return false;
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
