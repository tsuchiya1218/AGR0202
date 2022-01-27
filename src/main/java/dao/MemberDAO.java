package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Electronic_prescriptionBean;
import model.MemberBean;
import util.Close;
import util.DBconnection;
import util.GenerateQRcode;
import util.SHA256;

public class MemberDAO {
	private MemberDAO() {}
	
	private static class LazyHolder {
		public static final MemberDAO INSTANCE = new MemberDAO();
	}

	public static MemberDAO getInstance() {
		return LazyHolder.INSTANCE;
	}
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public boolean generateQRcode(int m_num,String m_birth) throws SQLException{
		String SQL = "UPDATE member SET m_qr_num = ? WHERE m_num = ?";
		String qr_name = GenerateQRcode.generateQRcode(m_num, m_birth);
		if(qr_name == null) {
			return false;
		}
		try {
			
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, qr_name);
			pstmt.setInt(2, m_num);
			pstmt.executeUpdate();
			conn.commit();
			
			return true;
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
	
	public boolean findByEmail(String m_email) {
		String SQL = "SELECT m_num FROM member WHERE m_email = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, m_email);
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
	
	public int findByM_numToQr_num(int m_num) {
		String SQL = "SELECT m_qr_num FROM member WHERE m_num = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, m_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("m_qr_num");
			}
			return 0;
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
	
	public String findByEmailToName(String m_email) {
		String SQL = "SELECT m_name FROM member WHERE m_email = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, m_email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString("m_name");
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
	
	public boolean singUp(MemberBean member) throws SQLException {
		String SQL = "INSERT INTO member VALUES(null,?,?,?,?,?,?,?,?,?,null,?,?,?,null,false)";
		try {
			Date birth = Date.valueOf(member.getM_birth());
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,SHA256.getEncrypt(member.getM_email()));
			pstmt.setString(2, SHA256.getEncrypt(member.getM_pw()));
			pstmt.setString(3, member.getM_name());
			pstmt.setString(4, member.getM_kana());
			pstmt.setDate(5, birth);
			pstmt.setString(6, member.getM_tel());
			pstmt.setString(7, member.getM_gender());
			pstmt.setString(8, member.getM_zip_code());
			pstmt.setString(9, member.getM_address());
			pstmt.setString(10, member.getM_i_num());
			pstmt.setString(11, member.getM_i_expiry_date());
			pstmt.setString(12, member.getM_i_mark());

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
		}finally {
			try {
				Close.close(conn, pstmt, null);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	public int getM_num(String m_email) {
		String SQL = "SELECT m_num FROM member WHERE m_email = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(m_email));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
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
		return -1;
	}
	
	public String login(String m_email, String m_pw) throws SQLException {
		String member = "SELECT m_pw FROM member WHERE m_email = ?";
		
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(member);
			pstmt.setString(1, SHA256.getEncrypt(m_email));
			rs = pstmt.executeQuery();
			conn.commit();
			if(rs.next()) {
				//ユーザが書いたpwとDBのpwと同じだったらログイン成功
				if(rs.getString("m_pw").equals(SHA256.getEncrypt(m_pw))) {
					return "member";
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
	public boolean isAuth(String m_email) {
		String SQL = "SELECT m_auth FROM member WHERE m_email = ?";
		
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(m_email));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getBoolean("m_auth");
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
	public boolean updateAuth(String m_email) throws SQLException{
		String SQL = "UPDATE member SET m_auth = ? WHERE m_email = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setBoolean(1, true);
			pstmt.setString(2, m_email);
			int result = pstmt.executeUpdate();
			conn.commit();
			if(result != 1) {
				return false;
			}
			return true;
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
	
	public boolean updateM_q_Num(int m_num,int m_q_num) throws SQLException{
		String SQL = "UPDATE member SET m_q_num = ? WHERE m_num = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, m_q_num);
			pstmt.setInt(2, m_num);
			pstmt.executeUpdate();
			conn.commit();
			return true;
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
	public String[] findbyEmailToM_numAndBirth(String m_email) {
		String SQL = "SELECT m_num,m_birth FROM member WHERE m_email = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, m_email);
			rs = pstmt.executeQuery();
			String[] result = new String[2];
			if(rs.next()) {
				result[0] = String.valueOf(rs.getInt("m_num"));
				result[1] = rs.getString("m_birth");
				return result;
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
	public MemberBean getMemberBeanByEmail(String m_email) {
		String SQL = "SELECT * FROM member WHERE m_email = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(m_email));
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
						rs.getBoolean("m_auth")
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
	
	public MemberBean getMemberListByM_numToUseHospital(int m_num) {
		String SQL = "SELECT m_name,m_kana,m_birth,m_gender FROM member WHERE m_num = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, m_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				MemberBean member = new MemberBean();
				member.setM_name(rs.getString("m_name"));
				member.setM_kana(rs.getString("m_kana"));
				member.setM_birth(rs.getString("m_birth").toString());
				member.setM_gender(rs.getString("m_gender"));
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
	
	public MemberBean getMemberBeanByM_num(int m_num) {
		String SQL = "SELECT * FROM member WHERE m_num = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, m_num);
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
						rs.getBoolean("m_auth")
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
	public boolean isDuplicateEmail(String m_email) {
		String SQL = "SELECT m_email FROM member WHERE m_email = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(m_email));
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
	
	public boolean findPassword(String m_email,String m_name) {
		String SQL = "SELECT m_email,m_name FROM member WHERE m_email = ? AND m_name = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(m_email));
			pstmt.setString(2, m_name);
			pstmt.setBoolean(3, false);
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
	public boolean changePassword(String m_email, String m_pw) throws SQLException{
		String SQL = "UPDATE member SET m_pw = ? WHERE m_email = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(m_pw));
			pstmt.setString(2, m_email);
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
	
	public boolean leave(int m_num) throws SQLException{
		String SQL = "SELECT ep_num,ep_di_num FROM electronic_prescription WHERE ep_m_num = ?";
		
		List<Electronic_prescriptionBean> epList = new ArrayList<>();
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, m_num);
			rs = pstmt.executeQuery();
			conn.commit();
			while(rs.next()) {
				Electronic_prescriptionBean ep = new Electronic_prescriptionBean();
				ep.setEp_num(rs.getInt("ep_num"));
				ep.setEp_di_num(rs.getInt("ep_di_num"));
				epList.add(ep);
			}
			pstmt.clearParameters();
			SQL = "DELETE FROM prescribe_medicine WHERE pm_ep_num = ?";
			for(Electronic_prescriptionBean ep : epList) {
				pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, ep.getEp_num());
				pstmt.executeUpdate();
				conn.commit();
				
			}
			
			pstmt.clearParameters();
			SQL = "DELETE FROM drug_information WHERE di_num = ?";
			for(Electronic_prescriptionBean ep : epList) {
				pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, ep.getEp_di_num());
				pstmt.executeUpdate();
				conn.commit();
			}
			
			pstmt.clearParameters();
			SQL = "DELETE FROM electronic_prescription WHERE ep_m_num = ?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, m_num);
			pstmt.executeUpdate();
			conn.commit();
			
			pstmt.clearParameters();
			SQL = "DELETE FROM child WHERE c_m_num = ?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, m_num);
			pstmt.executeUpdate();
			conn.commit();
			
			pstmt.clearParameters();
			SQL = "DELETE FROM member WHERE m_num = ?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, m_num);
			int result = pstmt.executeUpdate();
			conn.commit();
			
			if(result > 0 ) return true;
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
	
	public void sortM_num() throws SQLException {
		try {
			String SQL = "SET @COUNT=0;";
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.executeQuery();
			SQL = "UPDATE member SET m_num =@count:=@count+1;";
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
	
	public boolean updateMemberBean(MemberBean member) throws SQLException{
		String SQL = "UPDATE member SET m_name = ?, m_kana = ?, m_birth = ?,m_tel = ?,"
				+ "m_gender = ?, m_zip_code = ?, m_address = ?, m_i_num = ?, m_i_expiry_date = ?,"
				+ "m_i_mark = ? WHERE m_email = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, member.getM_name());
			pstmt.setString(2, member.getM_kana());
			pstmt.setString(3, member.getM_birth());
			pstmt.setString(4, member.getM_tel());
			pstmt.setString(5, member.getM_gender());
			pstmt.setString(6, member.getM_zip_code());
			pstmt.setString(7, member.getM_address());
			pstmt.setString(8, member.getM_i_num());
			pstmt.setString(9, member.getM_i_expiry_date());
			pstmt.setString(10, member.getM_i_mark());
			pstmt.setString(11, member.getM_email());
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
	
	public boolean updateMemberBeanWithPassword(MemberBean member) throws SQLException{
		String SQL = "UPDATE member SET m_pw = ?, m_name = ?, m_kana = ?, m_birth = ?,m_tel = ?,"
				+ "m_gender = ?, m_zip_code = ?, m_address = ?, m_i_num = ?, m_i_expiry_date = ?,"
				+ "m_i_mark = ? WHERE m_email = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(member.getM_pw()));
			pstmt.setString(2, member.getM_name());
			pstmt.setString(3, member.getM_kana());
			pstmt.setString(4, member.getM_birth());
			pstmt.setString(5, member.getM_tel());
			pstmt.setString(6, member.getM_gender());
			pstmt.setString(7, member.getM_zip_code());
			pstmt.setString(8, member.getM_address());
			pstmt.setString(9, member.getM_i_num());
			pstmt.setString(10, member.getM_i_expiry_date());
			pstmt.setString(11, member.getM_i_mark());
			pstmt.setString(12, member.getM_email());
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
	
	public boolean checkPassword(String m_email, String m_pw) throws SQLException{
		String SQL = "SELECT m_pw FROM member WHERE m_email = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, m_email);
			rs = pstmt.executeQuery();
			conn.commit();
			
			if(rs.next()) {
				if(rs.getString("m_pw").equals(SHA256.getEncrypt(m_pw))) {
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
	public boolean updateMemberEmail(String m_email, String new_email) throws SQLException{
		String SQL = "UPDATE member SET m_email = ?, m_auth = ? WHERE m_email = ? AND m_auth = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(new_email));
			pstmt.setBoolean(2, false);
			pstmt.setString(3, m_email);
			pstmt.setBoolean(4, true);
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
	public String countBirth(String m_birth) throws SQLException{
		String SQL = "SELECT ROUND((TO_DAYS(NOW()) - (TO_DAYS(?))) / 365) AS age;";
		try {
			String[] birth = m_birth.split("-");
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
				Close.close(conn, pstmt, null);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	public MemberBean findByQr_numToMember(String m_qr_num) {
		String SQL = "SELECT * FROM member WHERE m_qr_num = ?";
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
						rs.getString("m_birth"),
						rs.getString("m_tel"),
						rs.getString("m_gender"),
						rs.getString("m_zip_code"),
						rs.getString("m_address"),
						rs.getInt("m_q_num"),
						rs.getString("m_i_num"),
						rs.getString("m_i_expiry_date"),
						rs.getString("m_i_mark"),
						rs.getString("m_qr_num"),
						rs.getBoolean("m_auth")
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
	
}
