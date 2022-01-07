package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Drug_informationBean;
import model.Electronic_prescriptionBean;
import util.Close;
import util.DBconnection;

public class Drug_informationDAO {
	private Drug_informationDAO() {}
	
	private static class LazyHolder {
		public static final Drug_informationDAO INSTANCE = new Drug_informationDAO();
	}

	public static Drug_informationDAO getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public int createDi(Drug_informationBean di) throws SQLException {
		String SQL = "INSERT INTO drug_information VALUES(null,NOW(),?,?)";
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS);
			pstmt.setBoolean(1, di.isDi_auth());
			pstmt.setInt(2, di.getDi_p_num());
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle.getMessage());
		} catch (NullPointerException nule) {
			throw new RuntimeException(nule.getMessage());
		}finally {
			try {
				Close.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	public Drug_informationBean findByDi_numToDrug_informationBean(int di_num) throws SQLException {
		String SQL = "SELECT * FROM drug_information WHERE di_num = ?";
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  di_num);
			rs = pstmt.executeQuery();
			conn.commit();
			if(rs.next()) {
				Drug_informationBean di = new Drug_informationBean(
						rs.getInt("di_num"),
						rs.getString("di_reg_date"),
						rs.getBoolean("di_auth"),
						rs.getInt("di_p_num")
						);
				return di;
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
	
	public Drug_informationBean findByDi_numAndReg_DateToDrug_informationBean(int di_num,String startDate_,String endDate_) throws SQLException {
		String SQL = "SELECT * FROM drug_information WHERE di_num = ? AND di_reg_date >= ? AND di_reg_date <= ?";
		Date startDate = Date.valueOf(startDate_);
		Date endDate = Date.valueOf(endDate_);
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  di_num);
			pstmt.setDate(2,  startDate);
			pstmt.setDate(3,  endDate);
			rs = pstmt.executeQuery();
			conn.commit();
			if(rs.next()) {
				Drug_informationBean di = new Drug_informationBean(
						rs.getInt("di_num"),
						rs.getString("di_reg_date"),
						rs.getBoolean("di_auth"),
						rs.getInt("di_p_num")
						);
				return di;
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
	
}
