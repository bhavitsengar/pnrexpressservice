package com.bhavit.pnrexpress.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bhavit.pnrexpress.util.Helper;


@SuppressWarnings("serial")
public class SubmitLocation extends HttpServlet {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://mysql1.000webhost.com/a6735275_ishani";

	//  Database credentials
	static final String USER = "a6735275_bhavit";
	static final String PASS = "iloversv_123";


	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		//Helper.setProxy();
		
		String timestamp = (String) req.getParameter("timestamp");
		String lat = (String) req.getParameter("lat");
		String lon = (String) req.getParameter("long");

		Connection conn = null;
		Statement stmt = null;
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");

			//STEP 4: Execute a query
			System.out.println("Inserting records into the table...");
			stmt = conn.createStatement();

			String sql = "INSERT INTO ishanilocation " +
					"VALUES ('"+timestamp+"', '"+lat+"', '"+lon+"')";
			stmt.executeUpdate(sql);

			System.out.println("Inserted records into the table...");

		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					conn.close();
			}catch(SQLException se){
			}// do nothing
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try

		resp.setContentType("text/plain");
		resp.getWriter().print("Saved these location values: Latitude ="+lat+"Longitude="+lon);
	}

}
