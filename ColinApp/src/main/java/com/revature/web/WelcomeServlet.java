package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static Connection conn;
	
	public void init() throws ServletException{
		System.out.println(this.getServletName() + " is instantiated.");
		super.init();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "colin", "1234");
			System.out.println("Connected!");
					
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 

			throws ServletException, IOException {
		
		try {

			Statement statement = conn.createStatement();

			RequestDispatcher rd = req.getRequestDispatcher("login.html"); 
			
			PrintWriter out = res.getWriter();
			
			int result = statement.executeUpdate("SELECT COUNT(*) FROM users"); 
			

			if (result > 0) {
				rd.forward(req, res);

				out.println("<h1>Login page!</h1>");

			} else {

				out.println("<h1>Error...</h1>");

			}

		} catch (SQLException e) {

			e.printStackTrace();

		}
		
	}
	
	public void destroy() {

		System.out.println(this.getServletName() + " DESTROYED!");

		super.destroy();


		try {

			conn.close();

			System.out.println("Connection closed.");

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}
	//should simply allow you to proceed to the login page.
}
