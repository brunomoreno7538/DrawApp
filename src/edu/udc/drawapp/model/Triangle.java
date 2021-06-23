package edu.udc.drawapp.model;

import edu.udc.drawapp.model.Shape.ShapeState;
import edu.udc.drawapp.model.handler.ShapeHandler;
import edu.udc.drawapp.model.handler.TriangleHandler;

public class Triangle implements Shape {
	private static final long serialVersionUID = 1L;
	public Point a;
	public Point b;
	public Point c;

	public Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public ShapeHandler getHandler() {
		return new TriangleHandler(this);
	}

	@Override
	public String toString() {
		return "Triangle [a=" + a + ", b=" + b + ", c=" + c + "]";
	}

	@Override
	public void setState(ShapeState state) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public ShapeState getState() {
		return null;
	}

}
