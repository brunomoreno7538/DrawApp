package edu.udc.drawapp.model.handler;

import java.awt.Graphics;

import edu.udc.drawapp.model.Circle;
import edu.udc.drawapp.model.Shape.ShapeState;

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
		circle.setState(ShapeState.FINISHED);
		return false;
	}

	@Override
	public void paint(Graphics g) {
	}

}