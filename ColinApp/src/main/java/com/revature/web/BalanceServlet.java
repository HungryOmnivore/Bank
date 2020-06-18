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
@WebServlet("/BalanceServlet")
public class BalanceServlet extends HttpServlet{
	
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
				
			String mine = req.getParameter("balance");
			Statement statement = conn.createStatement();
			String balance = req.getParameter("balance"); // make sure this matched the "name" value in your HTML!
			String password = req.getParameter("password");
			int result = statement.executeUpdate("UPDATE dataset SET balance = 100 WHERE password = '"+password+"'");
			
			PrintWriter out = res.getWriter();
			RequestDispatcher hom = req.getRequestDispatcher("home.html");

			if (result > 0) {

				out.println("<h1>You have a balance!</h1>");

			} else {

				out.println("<h1>Error</h1>");

			}
			
			hom.forward(req, res);

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


