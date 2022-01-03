package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Electronic_prescriptionDAO {
private Electronic_prescriptionDAO() {}
	
	private static class LazyHolder {
		public static final Electronic_prescriptionDAO INSTANCE = new Electronic_prescriptionDAO();
	}

	public static Electronic_prescriptionDAO getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

}
