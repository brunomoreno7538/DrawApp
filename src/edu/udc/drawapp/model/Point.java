package edu.udc.drawapp.model;

import edu.udc.drawapp.model.handler.PointHandler;
import edu.udc.drawapp.model.handler.ShapeHandler;
import edu.udc.drawapp.persistence.jdbc.dao.PointDAO;

public class Point implements Shape {
	public float x;
	public float y;

	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float distance(Point p) {
		return (float) Math.sqrt((p.x - x) * (p.x - x) + (p.y - y) * (p.y - y));
	}

	public float distance(float x, float y) {
		return (float) Math.sqrt((this.x - x) * (this.x - x) + (this.y - y) * (this.y - y));
	}

	@Override
	public ShapeHandler getHandler() {
		return new PointHandler(this);
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
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
