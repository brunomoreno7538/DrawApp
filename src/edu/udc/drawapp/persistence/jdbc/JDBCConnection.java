package edu.udc.drawapp.persistence.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCConnection {
	
	private static JDBCConnection instance = new JDBCConnection();
	
	private JDBCConnection() {}
	
	public Connection sqlConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/draw_app_psw2", "root", "sasasa123");
			return con;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static JDBCConnection getConnection() {
		return instance;
	}

}
