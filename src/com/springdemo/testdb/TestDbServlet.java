package com.springdemo.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
										   IOException {

	// setup connection variables
	String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC";
	String driver = "com.mysql.jdbc.Driver";

	String user = "springstudent";
	String pass = "springstudent";

	// get connection to the database
	try {

	    PrintWriter out = response.getWriter();

	    out.println("Connecting to the database: " + jdbcUrl);

	    try {
		Class.forName(driver);
	    } catch (ClassNotFoundException e) {
		System.out.println("class not found: " + e.getMessage());
		e.printStackTrace();
	    }

	    Connection con = DriverManager.getConnection(jdbcUrl, user, pass);

	    out.println("Success!!!!");

	    con.close();

	} catch (SQLException e) {
	    e.printStackTrace();
	    throw new ServletException(e);
	}
    }

}
