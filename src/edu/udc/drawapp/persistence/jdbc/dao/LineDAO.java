package edu.udc.drawapp.persistence.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.udc.drawapp.model.Line;
import edu.udc.drawapp.model.Point;
import edu.udc.drawapp.model.handler.ShapeHandler;
import edu.udc.drawapp.persistence.jdbc.JDBCConnection;

public class LineDAO {
	
	private Connection connection;
	private int desenhoId;
	private String insertPointsQuery = "INSERT INTO pontos (x, y) VALUES (?, ?)";
	private String insertQuery = "INSERT INTO linhas (desenho_id, ponto_id_1, ponto_id_2) VALUES (?, ?, ?)";
	private String selectAllQuery = "SELECT * FROM linhas left join pontos on linhas.ponto_id_1 = pontos.id left join pontos p2 on linhas.ponto_id_2 = p2.id where linhas.desenho_id = ?";
	private List<Line> lines = new ArrayList<Line>();
	private int[] pointsId = new int[2];
	private Point[] points = new Point[2];
	
	public LineDAO(int desenhoId){
		this.connection = JDBCConnection.getConnection().sqlConnection();
		this.desenhoId = desenhoId;
	}
	
	public void insert(Point a, Point b) {
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
			}
			try(PreparedStatement stmt = this.connection.prepareStatement(insertQuery))
			{
				stmt.setInt(1, desenhoId);
				stmt.setInt(2, pointsId[0]);
				stmt.setInt(3, pointsId[1]);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Line> getAll() {
		PreparedStatement stmt;
		try {
			stmt = this.connection.prepareStatement(selectAllQuery);
			stmt.setInt(1, this.desenhoId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
					points[0] = new Point(rs.getFloat(7), rs.getFloat(8));
					points[1] = new Point(rs.getFloat(11), rs.getFloat(12));
				lines.add(new Line(points[0], points[1]));
			}
			stmt.close();
			return lines;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
