package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Staff;
import utility.DBConnection;

/**
 * Servlet implementation class GetJSONStaffDetails
 */
public class GetJSONStaffDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger("controller.GetJSONStaffDetails");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = DBConnection.getConnection();
		String sql = "select * from staff";
		List<Staff> staff = new ArrayList<Staff>();
		PrintWriter out = response.getWriter();
		PreparedStatement st = null;
		ResultSet res = null;
		FileWriter writer = null;

		try {
			st = con.prepareStatement(sql);
			res = st.executeQuery();

			while (res.next()) {
				String name = res.getString("name");
				short age = (short) Integer.parseInt(res.getString("age"));
				String address = res.getString("address");
				String city = res.getString("city");
				String state = res.getString("state");
				String country = res.getString("country");
				int pincode = Integer.parseInt(res.getString("pincode"));
				String email = res.getString("email");
				String username = res.getString("username");
				String password = res.getString("password");

				Staff s2 = new Staff(name, age, address, city, state, country, pincode, email, username, password);
				staff.add(s2);
				Gson gson = new Gson();
				String staffJSON = gson.toJson(staff);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");

				out.write(staffJSON);
				out.close();
				try {
					writer = new FileWriter("D:\\Script Workspace\\ClassRoomWeb\\staffdetails.json");

					writer.write(staffJSON + System.lineSeparator());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.log(Level.WARNING, "Exception :: ", e);
				} finally {
					try {
						if (writer != null)
							writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						logger.log(Level.WARNING, "Exception :: ", e);
					}
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.log(Level.WARNING, "Exception :: ", e);
		} finally {

			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.log(Level.WARNING, "Exception :: ", e);
			}
			try {
				if (res != null) {
					res.close();
				}
			} catch (Exception e) {
				logger.log(Level.WARNING, "Exception :: ", e);
			}
			try {
				if (st != null) {
					st.close();
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
