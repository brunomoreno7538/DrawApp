package edu.udc.drawapp.persistence.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.udc.drawapp.model.Circle;
import edu.udc.drawapp.model.Line;
import edu.udc.drawapp.model.Point;
import edu.udc.drawapp.persistence.jdbc.JDBCConnection;

public class CircleDAO {
	private Connection connection;
	private int desenhoId;
	private String insertPointsQuery = "INSERT INTO pontos (x, y) VALUES (?, ?)";
	private String insertQuery = "INSERT INTO circulos (desenho_id, ponto_id_1, radius) VALUES (?, ?, ?)";
	private String selectAllQuery = "SELECT * FROM circulos left join pontos on circulos.ponto_id_1 = pontos.id where circulos.desenho_id = ?;";
	private List<Circle> circles = new ArrayList<Circle>();
	private int pointId;
	private Point point;
	
	public CircleDAO(int desenhoId){
		this.connection = JDBCConnection.getConnection().sqlConnection();
		this.desenhoId = desenhoId;
	}
	
	public void insert(Point a, float radius) {
		try {
			ResultSet generatedKeys;
			try(PreparedStatement stmt = this.connection.prepareStatement(insertPointsQuery, Statement.RETURN_GENERATED_KEYS))
			{
				stmt.setFloat(1, a.x);
				stmt.setFloat(2, a.y);
				stmt.executeUpdate();
				generatedKeys = stmt.getGeneratedKeys();
				if(generatedKeys.next()) {
					pointId = generatedKeys.getInt(1);
				}
			}
			try(PreparedStatement stmt = this.connection.prepareStatement(insertQuery))
			{
				stmt.setInt(1, desenhoId);
				stmt.setInt(2, pointId);
				stmt.setFloat(3, radius);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Circle> getAll() {
		PreparedStatement stmt;
		try {
			stmt = this.connection.prepareStatement(selectAllQuery);
			stmt.setInt(1, this.desenhoId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
					point = new Point(rs.getFloat(7), rs.getFloat(8));
				circles.add(new Circle(point, rs.getFloat(4)));
			}
			stmt.close();
			return circles;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
