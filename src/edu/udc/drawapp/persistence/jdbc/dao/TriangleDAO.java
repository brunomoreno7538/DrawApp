package edu.udc.drawapp.persistence.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.udc.drawapp.model.Point;
import edu.udc.drawapp.model.Triangle;
import edu.udc.drawapp.persistence.jdbc.JDBCConnection;

public class TriangleDAO {
	
	private Connection connection;
	private int desenhoId;
	private String insertPointsQuery = "INSERT INTO pontos (x, y) VALUES (?, ?)";
	private String insertQuery = "INSERT INTO triangulos (desenho_id, ponto_id_1, ponto_id_2, ponto_id_3) VALUES (?, ?, ?, ?)";
	private String selectAllQuery = "SELECT * FROM triangulos t left join pontos on t.ponto_id_1 = pontos.id left join pontos p2 on t.ponto_id_2 = p2.id left join pontos p3 on t.ponto_id_3 = p3.id where t.desenho_id = ?";
	private List<Triangle> triangles = new ArrayList<Triangle>();
	private int[] pointsId = new int[3];
	private Point[] points = new Point[3];
	
	public TriangleDAO(int desenhoId){
		this.connection = JDBCConnection.getConnection().sqlConnection();
		this.desenhoId = desenhoId;
	}
	
	public void insert(Point a, Point b, Point c) {
		try {
			ResultSet generatedKeys;
			
			try(PreparedStatement stmt = this.connection.prepareStatement(insertPointsQuery, Statement.RETURN_GENERATED_KEYS))
			{
				stmt.setFloat(1, a.x);
				stmt.setFloat(2, a.y);
				stmt.executeUpdate();
				generatedKeys = stmt.getGeneratedKeys();
				if(generatedKeys.next()) {
					pointsId[0] = generatedKeys.getInt(1);
				}
				stmt.setFloat(1, b.x);
				stmt.setFloat(2, b.y);
				stmt.executeUpdate();
				generatedKeys = stmt.getGeneratedKeys();
				if(generatedKeys.next()) {
					pointsId[1] = generatedKeys.getInt(1);
				}
				stmt.setFloat(1, b.x);
				stmt.setFloat(2, b.y);
				stmt.executeUpdate();
				generatedKeys = stmt.getGeneratedKeys();
				if(generatedKeys.next()) {
					pointsId[2] = generatedKeys.getInt(1);
				}
			}
			try(PreparedStatement stmt = this.connection.prepareStatement(insertQuery))
			{
				stmt.setInt(1, desenhoId);
				stmt.setInt(2, pointsId[0]);
				stmt.setInt(3, pointsId[1]);
				stmt.setInt(4, pointsId[2]);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Triangle> getAll() {
		PreparedStatement stmt;
		try {
			stmt = this.connection.prepareStatement(selectAllQuery);
			stmt.setInt(1, this.desenhoId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
					points[0] = new Point(rs.getFloat(8), rs.getFloat(9));
					points[1] = new Point(rs.getFloat(12), rs.getFloat(13));
					points[2] = new Point(rs.getFloat(16), rs.getFloat(17));
				triangles.add(new Triangle(points[0], points[1], points[2]));
			}
			stmt.close();
			return triangles;
			//return lines;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return null;
		return null;
	}
}
