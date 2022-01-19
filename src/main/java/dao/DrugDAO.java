package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DrugBean;
import util.Close;
import util.DBconnection;

public class DrugDAO {
	private DrugDAO() {
	}

	private static class LazyHolder {
		public static final DrugDAO INSTANCE = new DrugDAO();
	}

	public static DrugDAO getInstance() {
		return LazyHolder.INSTANCE;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public boolean createDrug(DrugBean drug) throws SQLException {
		String SQL = "INSERT INTO drug VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?)";
		

		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, drug.getDrug_name());
			pstmt.setString(2, drug.getDrug_type());
			pstmt.setString(3, drug.getDrug_effect());
			pstmt.setString(4, drug.getDrug_guide());
			pstmt.setString(5, drug.getDrug_note());
			pstmt.setInt(6, drug.getDrug_price());
			pstmt.setString(7, drug.getDrug_img_name());
			pstmt.setBoolean(8, true);
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
	
	public List<DrugBean> getDrugList() throws SQLException {
		String SQL = "SELECT * FROM drug WHERE drug_isAvailable = ? ORDER BY drug_num DESC ";
		List<DrugBean> drugList = new ArrayList<>();
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setBoolean(1, true);
			rs = pstmt.executeQuery();
			conn.commit();
			while(rs.next()) {
				DrugBean drug = new DrugBean(
							rs.getInt("drug_num"),
							rs.getString("drug_name"),
							rs.getString("drug_type"),
							rs.getString("drug_effect"),
							rs.getString("drug_guide"),
							rs.getString("drug_note"),
							rs.getInt("drug_price"),
							rs.getString("drug_img_name")
						);
				drugList.add(drug);
			}
			
			return drugList;
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
	
	public List<DrugBean> getDrugListByPageNum(int lastNum,int fristNum) throws SQLException {
		String SQL = "SELECT * FROM drug WHERE drug_num <= ? AND drug_num > ? AND drug_isAvailable = ? ORDER BY drug_num DESC";
		List<DrugBean> drugList = new ArrayList<>();
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, lastNum);
			pstmt.setInt(2, fristNum);
			pstmt.setBoolean(3, true);
			rs = pstmt.executeQuery();
			conn.commit();
			while(rs.next()) {
				DrugBean drug = new DrugBean(
							rs.getInt("drug_num"),
							rs.getString("drug_name"),
							rs.getString("drug_type"),
							rs.getString("drug_effect"),
							rs.getString("drug_guide"),
							rs.getString("drug_note"),
							rs.getInt("drug_price"),
							rs.getString("drug_img_name")
						);
				drugList.add(drug);
			}
			
			return drugList;
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
	
	public List<DrugBean> searchByName(String drug_name) throws SQLException {
		String SQL = "SELECT * FROM drug WHERE drug_name LIKE ? AND drug_isAvailable = ? ORDER BY drug_num DESC";
		List<DrugBean> drugList = new ArrayList<>();
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + drug_name + "%");
			pstmt.setBoolean(2, true);
			rs = pstmt.executeQuery();
			conn.commit();
			while(rs.next()) {
				DrugBean drug = new DrugBean(
							rs.getInt("drug_num"),
							rs.getString("drug_name"),
							rs.getString("drug_type"),
							rs.getString("drug_effect"),
							rs.getString("drug_guide"),
							rs.getString("drug_note"),
							rs.getInt("drug_price"),
							rs.getString("drug_img_name")
						);
				drugList.add(drug);
			}
			
			return drugList;
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
	public int countSearchByName(String drug_name) throws SQLException {
		String SQL = "SELECT count(drug_num) as count FROM drug WHERE drug_name LIKE ? AND drug_isAvailable = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + drug_name + "%");
			pstmt.setBoolean(2, true);
			rs = pstmt.executeQuery();
			conn.commit();
			if(rs.next()) {
				return rs.getInt("count");
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
	
	public List<DrugBean> searchByNameAndType(String drug_name, String drug_type)
			throws SQLException {
		String SQL = "SELECT * FROM drug WHERE drug_name LIKE ? AND drug_type = ? AND drug_isAvailable = ? ORDER BY drug_num DESC";
		List<DrugBean> drugList = new ArrayList<>();
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + drug_name + "%");
			pstmt.setString(2, drug_type);
			pstmt.setBoolean(3, true);
			rs = pstmt.executeQuery();
			conn.commit();
			while (rs.next()) {
				DrugBean drug = new DrugBean(
							rs.getInt("drug_num"),
							rs.getString("drug_name"),
							rs.getString("drug_type"),
							rs.getString("drug_effect"),
							rs.getString("drug_guide"),
							rs.getString("drug_note"),
							rs.getInt("drug_price"),
							rs.getString("drug_img_name")
						);
				drugList.add(drug);
			}
			
			return drugList;
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
	
	public int countSearchByNameAndType(String drug_name,String drug_type) throws SQLException {
		String SQL = "SELECT count(drug_num) as count FROM drug WHERE drug_name LIKE ? AND drug_type = ? AND drug_isAvailable = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + drug_name + "%");
			pstmt.setString(2, drug_type);
			pstmt.setBoolean(3, true);
			rs = pstmt.executeQuery();
			conn.commit();
			if(rs.next()) {
				return rs.getInt("count");
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
	
	public List<DrugBean> searchByEffect(String drug_effect) throws SQLException {
		String SQL = "SELECT * FROM drug WHERE drug_effect LIKE ? AND drug_isAvailable = ? ORDER BY drug_num DESC";
		List<DrugBean> drugList = new ArrayList<>();
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%"+drug_effect+"%");
			pstmt.setBoolean(2, true);
			rs = pstmt.executeQuery();
			conn.commit();
			while(rs.next()) {
				DrugBean drug = new DrugBean(
							rs.getInt("drug_num"),
							rs.getString("drug_name"),
							rs.getString("drug_type"),
							rs.getString("drug_effect"),
							rs.getString("drug_guide"),
							rs.getString("drug_note"),
							rs.getInt("drug_price"),
							rs.getString("drug_img_name")
						);
				drugList.add(drug);
			}
			
			return drugList;
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
	
	public int countSearchByEffect(String drug_name) throws SQLException {
		String SQL = "SELECT count(drug_num) as count FROM drug WHERE drug_effect LIKE ? AND drug_isAvailable = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + drug_name + "%");
			pstmt.setBoolean(2, true);
			rs = pstmt.executeQuery();
			conn.commit();
			if(rs.next()) {
				return rs.getInt("count");
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
	
	public List<DrugBean> searchByEffectAndType(String drug_effect,String drug_type) throws SQLException {
		String SQL = "SELECT * FROM drug WHERE drug_effect LIKE ? AND drug_type = ? AND drug_isAvailable = ? ORDER BY drug_num DESC";
		List<DrugBean> drugList = new ArrayList<>();
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%"+drug_effect+"%");
			pstmt.setString(2, drug_type);
			pstmt.setBoolean(3, true);
			rs = pstmt.executeQuery();
			conn.commit();
			while(rs.next()) {
				DrugBean drug = new DrugBean(
							rs.getInt("drug_num"),
							rs.getString("drug_name"),
							rs.getString("drug_type"),
							rs.getString("drug_effect"),
							rs.getString("drug_guide"),
							rs.getString("drug_note"),
							rs.getInt("drug_price"),
							rs.getString("drug_img_name")
						);
				drugList.add(drug);
			}
			
			return drugList;
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
	
	public int countSearchByEffectAndType(String drug_effect,String drug_type) throws SQLException {
		String SQL = "SELECT count(drug_num) as count FROM drug WHERE drug_effect LIKE ? AND drug_type = ? AND drug_isAvailable = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + drug_effect + "%");
			pstmt.setString(2, drug_type);
			pstmt.setBoolean(3, true);
			rs = pstmt.executeQuery();
			conn.commit();
			if(rs.next()) {
				return rs.getInt("count");
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
	
	public boolean isDuplicateMDrug_name(String drug_name) {
		String SQL = "SELECT drug_name FROM drug WHERE drug_name = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, drug_name);
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
	public int findByDrug_numToDrug_name(String drug_name) {
		String SQL = "SELECT drug_num FROM drug WHERE drug_name LIKE ? AND drug_isAvailable = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, drug_name);
			pstmt.setBoolean(2, true);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("drug_num");
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
	
	public boolean updateDrugDuplicate(DrugBean drug) throws SQLException {
		String SQL = "UPDATE drug SET drug_type = ?, drug_effect = ?,drug_note = ?, drug_price = ?, drug_img_name = ?, drug_isAvailable = ? WHERE drug_name = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, drug.getDrug_type());
			pstmt.setString(2, drug.getDrug_effect());
			pstmt.setString(3, drug.getDrug_note());
			pstmt.setInt(4, drug.getDrug_price());
			pstmt.setString(5, drug.getDrug_img_name());
			pstmt.setBoolean(6, true);
			pstmt.setString(7, drug.getDrug_name());

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
	
	public boolean updateDrug(DrugBean drug) throws SQLException {
		String SQL = "UPDATE drug SET drug_name = ?, drug_type = ?, drug_effect = ?,drug_note = ?, drug_price = ?, drug_img_name = ? WHERE drug_num = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, drug.getDrug_name());
			pstmt.setString(2, drug.getDrug_type());
			pstmt.setString(3, drug.getDrug_effect());
			pstmt.setString(4, drug.getDrug_note());
			pstmt.setInt(5, drug.getDrug_price());
			pstmt.setString(6, drug.getDrug_img_name());
			pstmt.setInt(7, drug.getDrug_num());

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
	
	public boolean updateDrugImgName(String drug_img_name,int drug_num) throws SQLException {
		String SQL = "UPDATE drug SET drug_img_name = ? WHERE drug_num = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, drug_img_name);
			pstmt.setInt(2, drug_num);

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
	
	public boolean deleteDrug(int drug_num) throws SQLException {
		String SQL = "UPDATE drug SET drug_isAvailable = ? WHERE drug_num = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setBoolean(1, false);
			pstmt.setInt(2, drug_num);
			
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
	
	public String getDrugImgName(String drug_name) throws SQLException {
		String SQL = "SELECT drug_img_name FROM drug WHERE drug_name = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, drug_name);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getString("drug_img_name");
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
	
	public DrugBean findByDrug_numToDrug(int drug_num) throws SQLException {
		String SQL = "SELECT * FROM drug WHERE drug_num = ? AND drug_isAvailable = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, drug_num);
			pstmt.setBoolean(2, true);
			rs = pstmt.executeQuery();
			conn.commit();
			if(rs.next()) {
				DrugBean drug = new DrugBean(
						rs.getInt("drug_num"),
						rs.getString("drug_name"),
						rs.getString("drug_type"),
						rs.getString("drug_effect"),
						rs.getString("drug_guide"),
						rs.getString("drug_note"),
						rs.getInt("drug_price"),
						rs.getString("drug_img_name")
						);
				return drug;
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
	
	public int totalPageCount() {
		String SQL = "SELECT count(drug_num) as total FROM drug WHERE drug_isAvailable = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setBoolean(1, true);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("total");
			}
			return 0;
			
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle.getMessage());
		} finally {
			try {
				Close.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	public String findByDrug_img_nameToDrug_num(int drug_num) throws SQLException {
		String SQL = "SELECT drug_img_name FROM drug WHERE drug_num = ? AND drug_isAvailable = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, drug_num);
			pstmt.setBoolean(2, true);
			rs = pstmt.executeQuery();
			conn.commit();
			if(rs.next()) {
				return rs.getString("drug_img_name");
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
	
	public DrugBean findByDrug_nameToDrug(String drug_name) throws SQLException {
		String SQL = "SELECT * FROM drug WHERE drug_name LIKE ? AND drug_isAvailable = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, drug_name);
			pstmt.setBoolean(2, true);
			rs = pstmt.executeQuery();
			conn.commit();
			if(rs.next()) {
				DrugBean drug = new DrugBean(
						rs.getInt("drug_num"),
						rs.getString("drug_name"),
						rs.getString("drug_type"),
						rs.getString("drug_effect"),
						rs.getString("drug_guide"),
						rs.getString("drug_note"),
						rs.getInt("drug_price"),
						rs.getString("drug_img_name")
						);
				return drug;
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
