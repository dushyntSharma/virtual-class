package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import utility.DBConnection;

public class AdminDaoImpl implements AdminDao {
	private static Logger logger = Logger.getLogger("controller.AdminDaoImpl");

	@Override
	public boolean checkAdmin(String email, String password) {
		// TODO Auto-generated method stub
		boolean st = false;
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from admin where aEmail=? and pass=?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();
			st = rs.next();

		} catch (Exception e) {
			logger.log(Level.WARNING, "Exception :: ", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				logger.log(Level.WARNING, "Exception :: ", e);
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				logger.log(Level.WARNING, "Exception :: ", e);
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				logger.log(Level.WARNING, "Exception :: ", e);
			}
		}
		return st;
	}

}
