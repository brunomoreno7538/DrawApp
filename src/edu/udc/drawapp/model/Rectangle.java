package edu.udc.drawapp.model;

import edu.udc.drawapp.model.handler.RectangleHandler;
import edu.udc.drawapp.model.handler.ShapeHandler;

public class Rectangle implements Shape {
	public Point a;
	public Point b;
	public Point c;
	public Point d;

	public Rectangle(Point a, Point b, Point c, Point d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	@Override
	public ShapeHandler getHandler() {
		return new RectangleHandler(this);
	}

	@Override
	public String toString() {
		return "Rectangle [a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + "]";
	}

}
