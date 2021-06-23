package edu.udc.drawapp.persistence.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import edu.udc.drawapp.model.Shape;
import edu.udc.drawapp.model.Triangle;
import edu.udc.drawapp.model.Circle;
import edu.udc.drawapp.model.Line;
import edu.udc.drawapp.model.Point;
import edu.udc.drawapp.model.Rectangle;
import edu.udc.drawapp.persistence.jdbc.JDBCConnection;

public class DrawDAO {
	
	private Connection connection;
	private String insertQuery = "INSERT INTO desenhos (nome) VALUES (?)";
	private String selectQuery = "SELECT desenhos.id from desenhos where desenhos.nome = ? LIMIT 1";
	private List<Shape> shapeList = new ArrayList<Shape>();
	private int desenhoId;
	
	public DrawDAO() {
		this.connection = JDBCConnection.getConnection().sqlConnection();
	}
	
	public void insert(String nome) {
		ResultSet generatedKeys;
		try {
			PreparedStatement stmt = this.connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, nome);
			stmt.executeUpdate();
			generatedKeys = stmt.getGeneratedKeys();
			if(generatedKeys.next()) {
				this.desenhoId = generatedKeys.getInt(1);
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Shape> search(String nome) {
		try {
			PreparedStatement stmt = this.connection.prepareStatement(selectQuery);
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
					this.desenhoId = rs.getInt(1);
			}
			stmt.close();
			return this.getAllShapes();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void insertPoint(float x, float y) {
		PointDAO p = new PointDAO(this.desenhoId);
		p.insert(x, y);
	}
	
	private void getAllPoints() {
		PointDAO p = new PointDAO(this.desenhoId);
		for(Point point : p.getAll()) {
			this.shapeList.add(point);
		}
	}
	
	public void insertLine(Point a, Point b) {
		LineDAO l = new LineDAO(this.desenhoId);
		l.insert(a, b);
	}
	
	private void getAllLines() {
		LineDAO l = new LineDAO(this.desenhoId);
		for(Line line: l.getAll()) {
			this.shapeList.add(line);
		}
	}
	
	public void insertTriangle(Point a, Point b, Point c) {
		TriangleDAO t = new TriangleDAO(this.desenhoId);
		t.insert(a, b, c);
	}
	
	private void getAllTriangles() {
		TriangleDAO t = new TriangleDAO(this.desenhoId);
		for(Triangle triangle: t.getAll()) {
			this.shapeList.add(triangle);
		}
	}
	
	public void insertRectangle(Point a, Point b, Point c, Point d) {
		RectangleDAO t = new RectangleDAO(this.desenhoId);
		t.insert(a, b, c, d);
	}
	
	private void getAllRectangles() {
		RectangleDAO t = new RectangleDAO(this.desenhoId);
		for(Rectangle rectangle: t.getAll()) {
			this.shapeList.add(rectangle);
		}
	}
	
	public void insertCircle(Point a, float radius) {
		CircleDAO c = new CircleDAO(this.desenhoId);
		c.insert(a, radius);
	}
	
	private void getAllCircles() {
		CircleDAO c = new CircleDAO(this.desenhoId);
		for(Circle circle: c.getAll()) {
			this.shapeList.add(circle);
		}
	}
	
	public List<Shape> getAllShapes() {
		this.getAllPoints();
		this.getAllLines();
		this.getAllTriangles();                               
		this.getAllRectangles();
		this.getAllCircles();
		return this.shapeList; 
	}
	
	public void saveShapeList(List<Shape> shapeList) {
		shapeList = shapeList.stream().distinct().collect(Collectors.toList());
		for(Shape shape: shapeList) {
			System.out.println(shape);
			switch(shape.getClass().getSimpleName()) {
			case "Point": 
				Point p = (Point) shape;
				this.insertPoint(p.x, p.y);
				break;
			case "Line":
				Line l = (Line) shape;
				Point p1 = l.a;
				Point p2 = l.b;
				this.insertLine(p1, p2);
				break;
			case "Triangle":
				Triangle t = (Triangle) shape;
				Point p3 = t.a;
				Point p4 = t.b;
				Point p5 = t.c;
				this.insertTriangle(p3, p4, p5);
			case "Rectangle":
				Rectangle r = (Rectangle) shape;
				Point p6 = r.a;
				Point p7 = r.b;
				Point p8 = r.c;
				Point p9 = r.d;
				this.insertRectangle(p6, p7, p8, p9);
				break;
			case "Circle":
				Circle c = (Circle) shape;
				Point p10 = c.center;
				this.insertCircle(p10, c.radius);
				break;
			default:
				break;
			}
		}
	}
}
