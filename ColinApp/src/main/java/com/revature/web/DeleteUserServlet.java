package com.revature.web;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	
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


			String email = req.getParameter("email");

			String password = req.getParameter("password");

			
			


			try {

				Statement statement = conn.createStatement();

				int result = statement.executeUpdate("DELETE FROM dataset WHERE email= '"+email+"'"); 
				
				PrintWriter out = res.getWriter();
				RequestDispatcher log = req.getRequestDispatcher("login.html"); // instead of sending this to login.html, send it to a welcomeServlet
				
 				log.forward(req, res);

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

}
