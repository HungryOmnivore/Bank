package com.revature.web;


import java.io.IOException;

import java.io.PrintWriter;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;



@WebServlet("/LoginServlet")

public class LoginServlet extends HttpServlet {



	private static final long serialVersionUID = 1L;

	private static Connection conn;  // Make sure that the driver software is included in lib.

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

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 

		throws ServletException, IOException {

		String email = req.getParameter("email");

		String password = req.getParameter("password"); // this is the password you want to update

		try {

			Statement statement = conn.createStatement();

			ResultSet rs = statement.executeQuery("SELECT password FROM dataset WHERE email= '"+email+"'");
			rs.next();
			RequestDispatcher rd = req.getRequestDispatcher("home.html");
			RequestDispatcher red = req.getRequestDispatcher("welcome.html");
			
			String x = rs.getString("password");
			
			System.out.println(x);
			System.out.println(password);
			
			if (x!=password) {
				rd.include(req, res);
			} else {

			    red.include(req, res);
			}

//			if (password!=x) { // here you're checking that the password is correct
//				
//				rs.next();
//				red.forward(req, res);
//
//				req.setAttribute("message", "Access granted! Welcome to the HomeServlet " + email);
//
//				
//
//			} else {
//				
//				rs.next();
//				rd.include(req, res);
//				System.out.println("Oh NO!");
//				
//			}
			
//			if (x==password) {
//				red.include(req,res);
//			}
			
			System.out.println("hi");

			

		} catch (SQLException e) {
			

			e.printStackTrace();

		}
		
		
		System.out.println("bye");


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