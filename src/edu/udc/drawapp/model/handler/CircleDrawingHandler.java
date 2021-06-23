package edu.udc.drawapp.model.handler;

import java.awt.Graphics;

import edu.udc.drawapp.model.Circle;

public class CircleDrawingHandler implements ShapeHandler {

	private Circle circle;

	public CircleDrawingHandler(Circle circle) {
		this.circle = circle;
	}

	@Override
	public void mouseMove(int x, int y) {
		circle.radius = circle.center.distance(x, y);
	}

	@Override
	public boolean mouseClick(int x, int y) {
		circle.radius = circle.center.distance(x ,y);
		circle.setState(new CircleNotInitiatedHandler(circle));
		return true;
	}

	@Override
	public void paint(Graphics g) {
	}

}