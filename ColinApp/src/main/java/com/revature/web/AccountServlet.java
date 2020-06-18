package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/AccountServlet")
public abstract class AccountServlet extends HttpServlet{
	
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
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 

			throws ServletException, IOException {
		System.out.println("hi");

			String balance = req.getParameter("balance"); // make sure this matched the "name" value in your HTML!
			try {

				Statement statement = conn.createStatement();

				int result = statement.executeUpdate("SELECT * FROM balance "
						+ "VALUES('"+balance+"')"); 
				
				PrintWriter out = res.getWriter();

				if (result > 0) {

					out.println("<h1>Balance Displayed!</h1>");

				} else {

					out.println("<h1>Error in display...</h1>");

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
}
