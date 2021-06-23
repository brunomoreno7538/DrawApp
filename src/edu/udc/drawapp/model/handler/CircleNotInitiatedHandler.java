package edu.udc.drawapp.model.handler;

import java.awt.Graphics;

import edu.udc.drawapp.model.Circle;
import edu.udc.drawapp.model.Shape.ShapeState;

public class CircleNotInitiatedHandler implements ShapeHandler {

	private Circle circle;

	public CircleNotInitiatedHandler(Circle circle) {
		this.circle = circle;
		this.circle.setState(ShapeState.NOT_INITIALIZED);
	}

	@Override
	public void mouseMove(int x, int y) {
	}

	@Override
	public boolean mouseClick(int x, int y) {
		circle.center.x = x;
		circle.center.y = y;
		circle.radius = 0f;
		circle.setState(ShapeState.DRAWING);
		return false;
	}

	@Override
	public void paint(Graphics g) {
	}

}