package edu.udc.drawapp.model.handler;

import java.awt.Graphics;

import edu.udc.drawapp.model.Triangle;

public class TriangleHandler implements ShapeHandler {

	private Triangle triangle;
	/*
	 * Drawing state
	 * 0 - not drawing
	 * 1 point defined
	 * 2 points defined
	 * 3 points defined
	 */
	private static int state;

	public TriangleHandler(Triangle triangle) {
		this.triangle = triangle;
	}

	@Override
	public void mouseMove(int x, int y) {
		switch(state) {
		case 1:
			triangle.b.x = x;
			triangle.b.y = y;
			break;
		case 2:
			triangle.c.x = x;
			triangle.c.y = y;
			break;
		}
	}

	@Override
	public void mouseClick(int x, int y) {
		switch(state) {
		case 0:
			triangle.a.x = x;
			triangle.a.y = y;
			triangle.b.x = x;
			triangle.b.y = y;
			triangle.c.x = x;
			triangle.c.y = y;
			state = 1;
			break;
		case 1:
			triangle.b.x = x;
			triangle.b.y = y;
			state = 2;
			break;
		case 2:
			triangle.c.x = x;
			triangle.c.y = y;
			state = 0;
			break;
		}
	}

	@Override
	public void paint(Graphics g) {
		// Draw the triangle
		g.drawLine((int)triangle.a.x, (int)triangle.a.y, (int)triangle.b.x, (int)triangle.b.y);
		g.drawLine((int)triangle.b.x, (int)triangle.b.y, (int)triangle.c.x, (int)triangle.c.y);
		g.drawLine((int)triangle.c.x, (int)triangle.c.y, (int)triangle.a.x, (int)triangle.a.y);
	}

}
