package com.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeDao {

	static String dburl;
	static String dbuname;
	static String dbpassword;
	static String dbdriver;

	public EmployeeDao() {

		dburl = "jdbc:mysql://localhost:3306/library";
		dbuname = "root";
		dbpassword = "Sowmya@11";
		dbdriver = "com.mysql.cj.jdbc.Driver";

	}

	public void loadDriver() {
		try {
			Class.forName(dbdriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() {

		try {
			Connection con = DriverManager.getConnection(dburl, dbuname, dbpassword);
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public PreparedStatement getPreparedStatement(Connection con, String sql) {
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			return ps;
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}

	}
	
	public void closeConnection(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closePreparedStatement(PreparedStatement ps) {
		try {
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
