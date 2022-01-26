package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Prescribe_medicineBean;
import util.Close;
import util.DBconnection;

public class Prescribe_medicineDAO {
	private Prescribe_medicineDAO() {}
	
	private static class LazyHolder {
		public static final Prescribe_medicineDAO INSTANCE = new Prescribe_medicineDAO();
	}

	public static Prescribe_medicineDAO getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public boolean createPm(List<Prescribe_medicineBean> pmList) throws SQLException {
		String SQL = "INSERT INTO prescribe_medicine VALUES(?,?,?,?,?,?,?)";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			for(Prescribe_medicineBean pm : pmList){
				pstmt.setInt(1,pm.getPm_drug_num());
				pstmt.setInt(2, pm.getPm_ep_num());
				pstmt.setString(3, pm.getPm_dosage());
				pstmt.setString(4, pm.getPm_dose());
				pstmt.setString(5, pm.getPm_usage());
				pstmt.setInt(6, pm.getPm_dose_day());
				pstmt.setInt(7, pm.getPm_all_dose_day());
	
				int result = pstmt.executeUpdate();
				conn.commit();
				
				if(result == 0) return false;
			}
			return true;
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
	
	public List<Prescribe_medicineBean> getPmList(int ep_num) throws SQLException {
		String SQL = "SELECT * FROM prescribe_medicine WHERE pm_ep_num = ?";
		List<Prescribe_medicineBean> pmList = new ArrayList<>();
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, ep_num);
			rs = pstmt.executeQuery();
			conn.commit();
			while(rs.next()) {
				Prescribe_medicineBean pm = new Prescribe_medicineBean(
							rs.getInt("pm_drug_num"),
							rs.getInt("pm_ep_num"),
							rs.getString("pm_dosage"),
							rs.getString("pm_dose"),
							rs.getString("pm_usage"),
							rs.getInt("pm_dose_day"),
							rs.getInt("pm_all_dose_day")
						);
				pmList.add(pm);
			}
			
			return pmList;
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
	
	
	public boolean updateDrug_information(List<Prescribe_medicineBean> pm) throws SQLException {
		String SQL = "UPDATE prescribe_medicine SET pm_dosage = ?, pm_dose = ?, pm_usage = ?, pm_dose_day = ?,"
				+ "pm_all_dose_day = ? WHERE pm_ep_num = ?";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			int i = 0;
			while(i < pm.size()) {
				pstmt.setString(1, pm.get(i).getPm_dosage());
				pstmt.setString(2, pm.get(i).getPm_dose());
				pstmt.setString(3, pm.get(i).getPm_usage());
				pstmt.setInt(4, pm.get(i).getPm_dose_day());
				pstmt.setInt(5, pm.get(i).getPm_all_dose_day());
				pstmt.setInt(6, pm.get(i).getPm_ep_num());
				int result = pstmt.executeUpdate();
				if (result == 0) return false;
			}
			return true;
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
	
	public int findByEp_numToDrugTotalPrice(int ep_num) throws SQLException {
		String SQL = "SELECT SUM(drug.drug_price) AS 'total' FROM prescribe_medicine "
				+ "INNER JOIN Drug ON drug.drug_num = prescribe_medicine.pm_drug_num WHERE pm_ep_num = ?;";
		List<Prescribe_medicineBean> pmList = new ArrayList<>();
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, ep_num);
			rs = pstmt.executeQuery();
			conn.commit();
			if(rs.next()) {
				return rs.getInt("total");
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
}
