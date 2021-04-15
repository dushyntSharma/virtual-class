package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import utility.DBConnection;

public class GetNewsDetailsForStudent {
	private static Logger logger = Logger.getLogger("dao.GetNewsDetailsForStudent");

	public void getNews() {
		Connection con = DBConnection.getConnection();
		PreparedStatement pt = null;
		ResultSet res = null;
		String sql = "select * from news";
		try {
			pt = con.prepareStatement(sql);
			res = pt.executeQuery();
			while (res.next()) {
				res.getString(1);
				res.getString(2);
				res.getDate(3);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.log(Level.WARNING, "Exception :: ", e);
		} finally {
			try {
				if (res != null) {
					res.close();
				}
			} catch (Exception e) {
				logger.log(Level.WARNING, "Exception :: ", e);
			}
			try {
				if (pt != null) {
					pt.close();
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

	}

}
