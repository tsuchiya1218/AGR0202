package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AdminBean;
import model.DoctorBean;
import model.HospitalBean;
import model.MemberBean;
import model.PharmacyBean;
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
	
	/* Doctor START */
	
	public boolean addDoctor(DoctorBean doctor) throws SQLException {
		String SQL = "INSERT INTO doctor VALUES(null,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			Date birth = Date.valueOf(doctor.getD_birth());
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(doctor.getD_email()));
			pstmt.setString(2, SHA256.getEncrypt(doctor.getD_pw()));
			pstmt.setString(3, doctor.getD_name());
			pstmt.setString(4, doctor.getD_kana());
			pstmt.setDate(5, birth);
			pstmt.setString(6, doctor.getD_tel());
			pstmt.setString(7, doctor.getD_gender());
			pstmt.setString(8, doctor.getD_department());
			pstmt.setInt(9, doctor.getD_h_num());
			pstmt.setBoolean(10, false);
			pstmt.setBoolean(11, false);
			
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
	
	
	public boolean deleteDoctor(int d_num) throws SQLException {
		String SQL = "UPDATE doctor SET d_leave = ? WHERE d_num = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setBoolean(1, true);
			pstmt.setInt(2, d_num);

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

	public boolean updateDoctorWithEmailAndPassword(DoctorBean doctor) throws SQLException {
		String SQL = "UPDATE doctor SET d_email = ?,d_pw = ? , d_name =?,d_kana = ?, d_birth = ?, d_tel = ?, d_gender = ?,"
				+ " d_department = ?,d_h_num = ? WHERE d_num = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(doctor.getD_email()));
			pstmt.setString(2, SHA256.getEncrypt(doctor.getD_pw()));
			pstmt.setString(3, doctor.getD_name());
			pstmt.setString(4, doctor.getD_kana());
			pstmt.setString(5, doctor.getD_birth());
			pstmt.setString(6, doctor.getD_tel());
			pstmt.setString(7, doctor.getD_gender());
			pstmt.setString(8, doctor.getD_department());
			pstmt.setInt(9, doctor.getD_h_num());
			pstmt.setInt(10, doctor.getD_num());
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
	
	public boolean updateDoctor(DoctorBean doctor) throws SQLException {
		String SQL = "UPDATE doctor SET d_name =?,d_kana = ?, d_birth = ?, d_tel = ?, d_gender = ?,"
				+ " d_department = ?,d_h_num = ? WHERE d_num = ?";
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
			pstmt.setInt(7, doctor.getD_h_num());
			pstmt.setInt(8, doctor.getD_num());
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
	
	public DoctorBean getDoctorBean(int d_num) {
		String SQL = "SELECT * FROM doctor WHERE d_num = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, d_num);
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
	
	public DoctorBean findByEmaulToDoctor(String d_email) {
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
	
	public List<DoctorBean> searchByKanaToDoctor(String kana) {
		String SQL = "SELECT * FROM doctor WHERE d_kana = ?";
		List<DoctorBean> doctorList = new ArrayList<>();
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, kana);
			rs = pstmt.executeQuery();
			while(rs.next()) {
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
				doctorList.add(doctor);
			}
			return doctorList;
			
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
	
	public List<DoctorBean> searchByNameToDoctor(String name) {
		String SQL = "SELECT * FROM doctor WHERE d_name = ?";
		List<DoctorBean> doctorList = new ArrayList<>();
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while(rs.next()) {
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
				doctorList.add(doctor);
			}
			return doctorList;
			
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
	
	public List<DoctorBean> searchByEmailToDoctor(String email) {
		String SQL = "SELECT * FROM doctor WHERE d_email = ?";
		List<DoctorBean> doctorList = new ArrayList<>();
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(email));
			rs = pstmt.executeQuery();
			while(rs.next()) {
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
				doctorList.add(doctor);
			}
			return doctorList;
			
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
	
	
	public int findByH_nameToH_num(String hospital_name) {
		String SQL = "SELECT h_num FROM hospital WHERE h_name = ? AND h_leave = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, hospital_name);
			pstmt.setBoolean(2, false);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("h_num");
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
	
	public String findByH_numToH_name(int hospital_num) {
		String SQL = "SELECT h_name FROM hospital WHERE h_num = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, hospital_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getString("h_name");
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
	
	
	/* Doctor END*/
	
	/* Pharmacy START */
	
	public boolean addPharmacy(PharmacyBean pharmacy) {
		String SQL = "INSERT INTO pharmacy VALUES(null,?,?,?,?,?,false)";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,pharmacy.getP_name());
			pstmt.setString(2, SHA256.getEncrypt(pharmacy.getP_email()));
			pstmt.setString(3,SHA256.getEncrypt(pharmacy.getP_pw()));
			pstmt.setString(4, pharmacy.getP_tel());
			pstmt.setString(5, pharmacy.getP_address());
			int result = pstmt.executeUpdate();
			if(result > 0) {
				return true;
			}
			return false;
		}catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}finally {
			try {
				Close.close(conn, pstmt, null);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	public boolean deletePharmacy(int p_num) throws SQLException {
		String SQL = "UPDATE pharmacy SET p_leave = ? WHERE p_num = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setBoolean(1, true);
			pstmt.setInt(2, p_num);

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
				Close.close(conn, pstmt, null);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	
	public boolean updatePharmacy(PharmacyBean pharmacy) throws SQLException {
		String SQL = "UPDATE pharmacy SET p_name =?, p_tel = ?, p_address = ? WHERE p_num = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, pharmacy.getP_name());
			pstmt.setString(2, pharmacy.getP_tel());
			pstmt.setString(3, pharmacy.getP_address());
			pstmt.setInt(4, pharmacy.getP_num());
			
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
	
	public boolean updatePharmacyWithEmailAndPassword(PharmacyBean pharmacy) throws SQLException {
		String SQL = "UPDATE pharmacy SET p_name =?, p_email =?, p_pw = ?, p_tel = ?, p_address = ? WHERE p_num = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, pharmacy.getP_name());
			pstmt.setString(2, SHA256.getEncrypt(pharmacy.getP_email()));
			pstmt.setString(3, SHA256.getEncrypt(pharmacy.getP_pw()));
			pstmt.setString(4, pharmacy.getP_tel());
			pstmt.setString(5, pharmacy.getP_address());
			pstmt.setInt(6, pharmacy.getP_num());
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
	
	public PharmacyBean getPharmacyBean(int p_num) {
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
						rs.getBoolean("p_leave")						);
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
	
	public PharmacyBean findByEmailToPharmacy(String p_email) {
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
	
	public boolean isDuplicateEmailToPharmacy(String email) {
		String SQL = "SELECT p_email FROM pharmacy WHERE p_email = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(email));
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
	
	public List<PharmacyBean> searchByNameToPharmacy(String name) {
		String SQL = "SELECT * FROM pharmacy WHERE p_name LIKE ?";
		List<PharmacyBean> pharmacyList = new ArrayList<>();
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%"+name+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				PharmacyBean pharmacy = new PharmacyBean(
						rs.getInt("p_num"),
						rs.getString("p_name"),
						rs.getString("p_email"),
						rs.getString("p_pw"),
						rs.getString("p_tel"),
						rs.getString("p_address"),
						rs.getBoolean("p_leave")
						);
				pharmacyList.add(pharmacy);
			}
			return pharmacyList;
			
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
	
	public List<PharmacyBean> searchByAddressToPharmacy(String address) {
		String SQL = "SELECT * FROM pharmacy WHERE p_address LIKE ?";
		List<PharmacyBean> pharmacyList = new ArrayList<>();
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%"+address+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				PharmacyBean pharmacy = new PharmacyBean(
						rs.getInt("p_num"),
						rs.getString("p_name"),
						rs.getString("p_email"),
						rs.getString("p_pw"),
						rs.getString("p_tel"),
						rs.getString("p_address"),
						rs.getBoolean("p_leave")
						);
				pharmacyList.add(pharmacy);
			}
			return pharmacyList;
			
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
	
	public List<PharmacyBean> searchByEmailToPharmacy(String email) {
		String SQL = "SELECT * FROM pharmacy WHERE p_email LIKE ?";
		List<PharmacyBean> pharmacyList = new ArrayList<>();
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%"+SHA256.getEncrypt(email)+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				PharmacyBean pharmacy = new PharmacyBean(
						rs.getInt("p_num"),
						rs.getString("p_name"),
						rs.getString("p_email"),
						rs.getString("p_pw"),
						rs.getString("p_tel"),
						rs.getString("p_address"),
						rs.getBoolean("p_leave")
						);
				pharmacyList.add(pharmacy);
			}
			return pharmacyList;
			
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
	
	public List<PharmacyBean> searchByTelToPharmacy(String tel) {
		String SQL = "SELECT * FROM pharmacy WHERE p_tel LIKE ?";
		List<PharmacyBean> pharmacyList = new ArrayList<>();
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%"+tel+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				PharmacyBean pharmacy = new PharmacyBean(
						rs.getInt("p_num"),
						rs.getString("p_name"),
						rs.getString("p_email"),
						rs.getString("p_pw"),
						rs.getString("p_tel"),
						rs.getString("p_address"),
						rs.getBoolean("p_leave")
						);
				pharmacyList.add(pharmacy);
			}
			return pharmacyList;
			
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
	
	
	/* Pharmacy END */
	
	/* Hospital START */
	
	
	public boolean addHospital(HospitalBean hospital) {
		String SQL = "INSERT INTO hospital VALUES(null,?,?,?,?,?,false)";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, hospital.getH_name());
			pstmt.setString(2, SHA256.getEncrypt(hospital.getH_email()));
			pstmt.setString(3, SHA256.getEncrypt(hospital.getH_pw()));
			pstmt.setString(4, hospital.getH_tel());
			pstmt.setString(5, hospital.getH_address());
			
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				return true;
			}
			
			return false;
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
	
	public boolean deleteHospital(int h_num) throws SQLException {
		String SQL = "UPDATE hospital SET h_leave = ? WHERE h_num = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setBoolean(1, true);
			pstmt.setInt(2, h_num);

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
				Close.close(conn, pstmt, null);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	public boolean updateHospital(HospitalBean hospital) throws SQLException {
		String SQL = "UPDATE hospital SET h_name =?, h_tel = ?, h_address = ? WHERE h_num = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, hospital.getH_name());
			pstmt.setString(2, hospital.getH_tel());
			pstmt.setString(3, hospital.getH_address());
			pstmt.setInt(4, hospital.getH_num());
			
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
	
	public boolean updateHospitalWithEmailAndPassword(HospitalBean hospital) throws SQLException {
		String SQL = "UPDATE hospital SET h_name =?, h_email =?, h_pw = ?, h_tel = ?, h_address = ? WHERE h_num = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, hospital.getH_name());
			pstmt.setString(2, SHA256.getEncrypt(hospital.getH_email()));
			pstmt.setString(3, SHA256.getEncrypt(hospital.getH_pw()));
			pstmt.setString(4, hospital.getH_tel());
			pstmt.setString(5, hospital.getH_address());
			pstmt.setInt(6, hospital.getH_num());
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
	
	public HospitalBean getHospitalBean(int h_num) {
		String SQL = "SELECT * FROM hospital WHERE h_num = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, h_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				HospitalBean hospital = new HospitalBean(
						rs.getInt("h_num"),
						rs.getString("h_name"),
						rs.getString("h_email"),
						rs.getString("h_pw"),
						rs.getString("h_tel"),
						rs.getString("h_address"),
						rs.getBoolean("h_leave")
						);
				return hospital;
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
	
	public HospitalBean findByEmailToHospital(String h_email) {
		String SQL = "SELECT * FROM hospital WHERE h_email = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(h_email));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				HospitalBean hospital = new HospitalBean(
						rs.getInt("h_num"),
						rs.getString("h_name"),
						rs.getString("h_email"),
						rs.getString("h_pw"),
						rs.getString("h_tel"),
						rs.getString("h_address"),
						rs.getBoolean("h_leave")
						);
				return hospital;
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
	
	
	public boolean isDuplicateEmailToHospital(String email) {
		String SQL = "SELECT h_email FROM hospital WHERE h_email = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(email));
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
	
	public List<HospitalBean> searchByNameToHospital(String name) {
		String SQL = "SELECT * FROM hospital WHERE h_name LIKE ?";
		List<HospitalBean> hospitalList = new ArrayList<>();
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%"+name+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HospitalBean hospital = new HospitalBean(
						rs.getInt("h_num"),
						rs.getString("h_name"),
						rs.getString("h_email"),
						rs.getString("h_pw"),
						rs.getString("h_tel"),
						rs.getString("h_address"),
						rs.getBoolean("h_leave")
						);
				hospitalList.add(hospital);
			}
			return hospitalList;
			
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
	
	public List<HospitalBean> searchByAddressToHospital(String address) {
		String SQL = "SELECT * FROM hospital WHERE h_address LIKE ?";
		List<HospitalBean> hospitalList = new ArrayList<>();
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%"+address+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HospitalBean hospital = new HospitalBean(
						rs.getInt("h_num"),
						rs.getString("h_name"),
						rs.getString("h_email"),
						rs.getString("h_pw"),
						rs.getString("h_tel"),
						rs.getString("h_address"),
						rs.getBoolean("h_leave")
						);
				hospitalList.add(hospital);
			}
			return hospitalList;
			
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
	
	public List<HospitalBean> searchByEmailToHospital(String email) {
		String SQL = "SELECT * FROM hospital WHERE h_email LIKE ?";
		List<HospitalBean> hospitalList = new ArrayList<>();
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%"+SHA256.getEncrypt(email)+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HospitalBean hospital = new HospitalBean(
						rs.getInt("h_num"),
						rs.getString("h_name"),
						rs.getString("h_email"),
						rs.getString("h_pw"),
						rs.getString("h_tel"),
						rs.getString("h_address"),
						rs.getBoolean("h_leave")
						);
				hospitalList.add(hospital);
			}
			return hospitalList;
			
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
	
	public List<HospitalBean> searchByTelToHospital(String tel) {
		String SQL = "SELECT * FROM hospital WHERE h_tel LIKE ?";
		List<HospitalBean> hospitalList = new ArrayList<>();
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%"+tel+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HospitalBean hospital = new HospitalBean(
						rs.getInt("h_num"),
						rs.getString("h_name"),
						rs.getString("h_email"),
						rs.getString("h_pw"),
						rs.getString("h_tel"),
						rs.getString("h_address"),
						rs.getBoolean("h_leave")
						);
				hospitalList.add(hospital);
			}
			return hospitalList;
			
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
	
	/* Hospital END */
	
	/* Member START */
	
	public List<MemberBean> searchByKanaToMember(String kana) {
		String SQL = "SELECT * FROM member WHERE m_kana = ?";
		List<MemberBean> memberList = new ArrayList<>();
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, kana);
			rs = pstmt.executeQuery();
			while(rs.next()) {
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
				memberList.add(member);
			}
			return memberList;
			
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
	
	public List<MemberBean> searchByNameToMember(String name) {
		String SQL = "SELECT * FROM member WHERE m_name = ?";
		List<MemberBean> memberList = new ArrayList<>();
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while(rs.next()) {
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
				memberList.add(member);
			}
			return memberList;
			
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
	
	public List<MemberBean> searchByEmailToMember(String email) {
		String SQL = "SELECT * FROM member WHERE m_email = ?";
		List<MemberBean> memberList = new ArrayList<>();
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, SHA256.getEncrypt(email));
			rs = pstmt.executeQuery();
			while(rs.next()) {
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
				memberList.add(member);
			}
			return memberList;
			
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
	
	/* Member END */
	
}
