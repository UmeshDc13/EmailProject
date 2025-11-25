package Mypackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Login")
public class Myservlet1 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	String Umesh = request.getParameter("Username");
    	String DC = request.getParameter("password");
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		Connection con=DriverManager.getConnection(
    				"jdbc:mysql://localhost:3306/dbjeeaug","root","sql123");
    		PreparedStatement ps= con.prepareStatement(
    				"SELECT * FROM user WHERE username=? AND password=?");
    		
    		ps.setString(1,Umesh);
    		ps.setString(2, DC);
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			RequestDispatcher rd = request.getRequestDispatcher("Home.html");
    			rd.forward(request, response);
    		}else {
    			response.getWriter().println("Registration failed, try again!");
    		}
    		}
    		catch(Exception e) {
    			System.out.println(e);
    		}
        
    }
}

