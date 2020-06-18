package com.revature.web;

import java.io.IOException;

import java.io.PrintWriter;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

@WebServlet("/ReadUserServlet")


public class ReadUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Connection conn; // Make sure that the driver software is included in lib.

	public void init() throws ServletException {

		System.out.println(this.getServletName() + " INSTANTIATED!");

		super.init();

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();

		}

		try {

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "colin", "1234");

			System.out.println("Connected!");

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	// we use a doGet which is IDEMPOTENT

	protected void doPost(HttpServletRequest req, HttpServletResponse res)

			throws ServletException, IOException {

		try {
			Statement statement = conn.createStatement();

			ResultSet resultSet = statement.executeQuery("SELECT * FROM dataset");

			// we still use Print Writer to build a dynamic HTML

			PrintWriter out = res.getWriter();

			res.setContentType("text/html");

			out.println("<table>"); // First Name Last Name Email

			out.println("<tr>");
			
			out.println("<th>");

			out.println("Balance");

			out.println("</th>");

			out.println("<th>");

			out.println("First Name"); // we will return a table in HTML of FN, LN, EMAIL.

			out.println("</th>");

			out.println("<th>");

			out.println("Last Name");

			out.println("</th>");

			out.println("<th>");

			out.println("Email");

			out.println("</th>");
			
			out.println("<th>");

			out.println("Password");

			out.println("</th>");
			
			out.println("<th>");

			out.println("RoleID");

			out.println("</th>");
			
			out.println("<th>");

			out.println("Role");

			out.println("</th>");
			
			out.println("<th>");

			out.println("Checking");

			out.println("</th>");
			
			out.println("<th>");

			out.println("Savings");

			out.println("</th>");

			out.println("</tr>");

			while (resultSet.next()) {
				

				out.println("<tr>");
				
				out.println("<td>");

				out.println(resultSet.getString("balance"));

				out.println("</td>");

				out.println("<td>");

				out.println(resultSet.getString("first_name"));

				out.println("</td>");

				out.println("<td>");

				out.println(resultSet.getString("last_name"));

				out.println("</td>");

				out.println("<td>");

				out.println(resultSet.getString("email"));

				out.println("</td>");
				
				out.println("<td>");

				out.println(resultSet.getString("password"));

				out.println("</td>");
				
				out.println("<td>");

				out.println(resultSet.getString("role_id"));

				out.println("</td>");
				
				out.println("<td>");

				out.println(resultSet.getString("role"));

				out.println("</td>");
				
				out.println("<td>");

				out.println(resultSet.getString("chk"));

				out.println("</td>");
				
				out.println("<td>");

				out.println(resultSet.getString("sav"));

				out.println("</td>");

				out.println("</tr>");
				
//				out.println("<tr>");
//				
//
//				out.println("<h1>Return Home!</h1>");
//
//				
//
//				out.println("<body>");
//
//				
//
//				out.println("<form method='post' action='createUser.html'>");
//
//				out.println("<table>");
//				out.println("<tr>");
//				out.println("<td><input type='submit' value='Enter'/></td>");
//				out.println("</tr>");
//				out.println("</table>");
//				out.println("</form>");
//				out.println("</body>");
//				//out.println("<td />");
//				
//				out.println("</tr>");

			}

			out.println("</table>");
			out.println("<tr>");
			

			out.println("<h1>Return Home!</h1>");

			

			out.println("<body>");

			

			out.println("<form method='post' action='home.html'>");

			out.println("<table>");
			out.println("<tr>");
			out.println("<td><input type='submit' value='Enter'/></td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</form>");
			out.println("</body>");
			//out.println("<td />");
			
			out.println("</tr>");

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