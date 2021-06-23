package edu.udc.drawapp.model;

import edu.udc.drawapp.model.handler.CircleHandler;
import edu.udc.drawapp.model.handler.CircleNotInitiatedHandler;
import edu.udc.drawapp.model.handler.ShapeHandler;
import edu.udc.drawapp.persistence.jdbc.dao.CircleDAO;

public class Circle implements Shape {
	public Point center;
	public float radius;
	private ShapeHandler state = new CircleNotInitiatedHandler(this);

	public Circle(Point center, float radius) {
		this.center = center;
		this.radius = radius;
	}

	@Override
	public ShapeHandler getHandler() {
		return new CircleHandler(this);
	}
	
	@Override
	public String toString() {
		return "Circle [center=" + center + ", radius=" + radius + "]";
	}

	@Override
	public void setState(ShapeHandler state) {
		this.state = state;
	}

	@Override
	public ShapeHandler getState() {
		return this.state;
	}

}
