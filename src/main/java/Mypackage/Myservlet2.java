package Mypackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Register")
	public class Myservlet2 extends HttpServlet {
	    private static final long serialVersionUID = 1L;

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        String fullname = request.getParameter("fullname");
	        String email = request.getParameter("email");
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con=DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/dbjeeaug","root","sql123");

	            PreparedStatement psm = con.prepareStatement(
	                "INSERT INTO user(fullname, email, username, password) VALUES (?, ?, ?, ?)");

	            psm.setString(1, fullname);
	            psm.setString(2, email);
	            psm.setString(3, username);
	            psm.setString(4, password);
	            int rows = psm.executeUpdate();

	            if(rows > 0) {
	                RequestDispatcher rd = request.getRequestDispatcher("Home.html");
	                rd.forward(request,response);
	            } else {
	                response.getWriter().println("<h3>Registration failed, try again!</h3>");
	            }
	            con.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

