package edu.udc.drawapp.persistence.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.udc.drawapp.model.Point;
import edu.udc.drawapp.model.Rectangle;
import edu.udc.drawapp.persistence.jdbc.JDBCConnection;

public class RectangleDAO {
	
	private Connection connection;
	private int desenhoId;
	private String insertPointsQuery = "INSERT INTO pontos (x, y) VALUES (?, ?)";
	private String insertQuery = "INSERT INTO retangulos (desenho_id, ponto_id_1, ponto_id_2, ponto_id_3, ponto_id_4) VALUES (?, ?, ?, ?, ?)";
	private String selectAllQuery = "SELECT * FROM retangulos left join pontos on retangulos.ponto_id_1 = pontos.id left join pontos p2 on retangulos.ponto_id_2 = p2.id left join pontos p3 on retangulos.ponto_id_3 = p3.id left join pontos p4 on retangulos.ponto_id_4 = p4.id where retangulos.desenho_id = ?";
	private List<Rectangle> rectangles = new ArrayList<Rectangle>();
	private int[] pointsId = new int[4];
	private Point[] points = new Point[4];
	
	public RectangleDAO(int desenhoId){
		this.connection = JDBCConnection.getConnection().sqlConnection();
		this.desenhoId = desenhoId;
	}
	
	public void insert(Point a, Point b, Point c, Point d) {
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
				stmt.setFloat(1, c.x);
				stmt.setFloat(2, c.y);
				stmt.executeUpdate();
				generatedKeys = stmt.getGeneratedKeys();
				if(generatedKeys.next()) {
					pointsId[2] = generatedKeys.getInt(1);
				}
				stmt.setFloat(1, d.x);
				stmt.setFloat(2, d.y);
				stmt.executeUpdate();
				generatedKeys = stmt.getGeneratedKeys();
				if(generatedKeys.next()) {
					pointsId[3] = generatedKeys.getInt(1);
				}
			}
			try(PreparedStatement stmt = this.connection.prepareStatement(insertQuery))
			{
				stmt.setInt(1, desenhoId);
				stmt.setInt(2, pointsId[0]);
				stmt.setInt(3, pointsId[1]);
				stmt.setInt(4, pointsId[2]);
				stmt.setInt(5, pointsId[3]);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Rectangle> getAll() {
		PreparedStatement stmt;
		try {
			stmt = this.connection.prepareStatement(selectAllQuery);
			stmt.setInt(1, this.desenhoId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
					points[0] = new Point(rs.getFloat(9), rs.getFloat(10));
					points[1] = new Point(rs.getFloat(13), rs.getFloat(14));
					points[2] = new Point(rs.getFloat(17), rs.getFloat(18));
					points[3] = new Point(rs.getFloat(21), rs.getFloat(22));
				rectangles.add(new Rectangle(points[0], points[1], points[2], points[3]));
			}
			stmt.close();
			System.out.println(rectangles);
			return rectangles;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
