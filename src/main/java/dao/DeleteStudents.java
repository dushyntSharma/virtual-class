package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import utility.DBConnection;

public class DeleteStudents {
	private static Logger logger = Logger.getLogger("dao.DeleteStudents");

	public void deleteStudent(String email) {
		Connection con = DBConnection.getConnection();
		PreparedStatement pt = null;
		String sql = "delete from student where email=?";
		try {
			pt = con.prepareStatement(sql);
			pt.setString(1, email);
			pt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.log(Level.WARNING, "Exception :: ", e);
		} finally {

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
