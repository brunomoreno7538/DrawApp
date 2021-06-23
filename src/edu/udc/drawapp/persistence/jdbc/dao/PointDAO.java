package edu.udc.drawapp.persistence.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.udc.drawapp.model.Point;
import edu.udc.drawapp.model.Shape;
import edu.udc.drawapp.persistence.jdbc.JDBCConnection;

public class PointDAO {
	
	public Connection connection;
	private int desenhoId;
	private String insertQuery = "INSERT INTO pontos (desenho_id, x, y) VALUES (?, ?, ?)";
	private String selectAllQuery = "SELECT id, x, y from pontos where desenho_id = ?";
	private List<Point> points = new ArrayList<Point>();
	
	public PointDAO(int desenhoId) {
		this.connection = JDBCConnection.getConnection().sqlConnection();
		this.desenhoId = desenhoId;
	}
	
	public void insert(float x, float y) {
		try {
			PreparedStatement stmt = this.connection.prepareStatement(insertQuery);
			stmt.setInt(1, desenhoId);
			stmt.setFloat(2, x);
			stmt.setFloat(3, y);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Point> getAll() {
		PreparedStatement stmt;
		try {
			stmt = this.connection.prepareStatement(selectAllQuery);
			stmt.setInt(1, desenhoId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				this.points.add(new Point(rs.getFloat(2), rs.getFloat(3)));
			}
			stmt.close();
			return this.points;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
