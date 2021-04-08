package edu.udc.drawapp.model;

import edu.udc.drawapp.model.handler.CircleHandler;
import edu.udc.drawapp.model.handler.ShapeHandler;

public class Circle implements Shape {
	public Point center;
	public float radius;

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

}
