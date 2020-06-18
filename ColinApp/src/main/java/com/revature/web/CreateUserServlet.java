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
@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {
	
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

			String firstname = req.getParameter("first_name"); // make sure this matched the "name" value in your HTML!

			String lastname = req.getParameter("last_name");

			String email = req.getParameter("email");

			String password = req.getParameter("password");

			String id = req.getParameter("user_id");
			
			String role = req.getParameter("role");
			
			String balance = req.getParameter("balance");
			
			String checking = req.getParameter("checking");
			
			String savings = req.getParameter("savings");


			try {

				Statement statement = conn.createStatement();

				int result = statement.executeUpdate("INSERT INTO dataset (balance, first_name, last_name, email, password, role_id, role, chk, sav)" + 
						"VALUES("+balance+",'"+firstname+"', '"+lastname+"', '"+email+"', '"+password+"', "+id+", '"+role+"', "+checking+", "+savings+")"); 
				
				PrintWriter out = res.getWriter();
				RequestDispatcher log = req.getRequestDispatcher("login.html"); // instead of sending this to login.html, send it to a welcomeServlet
				
//				if (result > 0) {
//					System.out.println("You made it!");
//
//					ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE first_name=" + firstname);
//					
//
//					res.setContentType("text/html");
//
//					out.println("<table>"); // First Name Last Name Email
//
//					out.println("<tr>");
//
//					out.println("<th>");
//
//					out.println("Enter first name to login:"); // we will return a table in HTML of FN, LN, EMAIL.
//
//					out.println("</th>");
//
//					out.println("</tr>");
//
//					while (resultSet.next()) {
//						
//
//						out.println("<tr>");
//
//						out.println("<td>");
//
//						out.println(resultSet.getString("first_name"));
//
//						out.println("</td>");
//
//						out.println("</tr>");
//
//					}
//
//					out.println("</table>");
//
//				} else {
//
//					out.println("<h1>Error creating user...</h1>");
//
//				}
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
