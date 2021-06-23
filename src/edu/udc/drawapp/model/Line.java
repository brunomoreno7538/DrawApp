package edu.udc.drawapp.model;

import edu.udc.drawapp.model.handler.LineHandler;
import edu.udc.drawapp.model.handler.ShapeHandler;
import edu.udc.drawapp.persistence.jdbc.dao.LineDAO;

public class Line implements Shape {
	public Point a;
	public Point b;

	public Line(Point a, Point b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public ShapeHandler getHandler() {
		return new LineHandler(this);
	}

	@Override
	public String toString() {
		return "Line [a=" + a + ", b=" + b + "]";
	}

	@Override
	public void setState(ShapeHandler state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ShapeHandler getState() {
		// TODO Auto-generated method stub
		return null;
	}

}
