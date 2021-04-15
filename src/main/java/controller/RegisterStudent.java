package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import dao.StudentDaoImpl;
import model.Student;

/**
 * Servlet implementation class RegisterStudent
 */
public class RegisterStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static StudentDao dao = new StudentDaoImpl();
	private static Logger logger = Logger.getLogger("controller.RegisterStudent");

	public RegisterStudent() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/RegisterStudent");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name = req.getParameter("name");
		short age = 0;
		try {
			age = (short) Integer.parseInt(req.getParameter("age"));
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			logger.log(Level.WARNING, "Exception :: ", e1);
		}
		String address = req.getParameter("address");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String country = req.getParameter("country");
		int pincode = 0;
		try {
			pincode = Integer.parseInt(req.getParameter("pincode"));
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			logger.log(Level.WARNING, "Exception :: ", e1);
		}
		String email = req.getParameter("email");
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		Student std = new Student(name, age, address, city, state, country, pincode, email, username, password);
		List<Student> st = new ArrayList<Student>();
		st.add(std);
		try {
			dao.registerStudents(st);
		} catch (Exception e) {
			// TODO: handle exception
			logger.log(Level.WARNING, "Exception :: ", e);
		}
		RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
		rd.forward(req, res);

	}

}
