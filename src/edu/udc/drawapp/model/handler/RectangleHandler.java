package edu.udc.drawapp.model.handler;

import java.awt.Graphics;

import edu.udc.drawapp.model.Rectangle;

public class RectangleHandler implements ShapeHandler {

	private Rectangle rectangle;
	/*
	 * Drawing state 0 - not drawing 1 point defined 2 points defined 3 points
	 * defined 4 points defined
	 */
	private static int state;
	private boolean finalizado = false;

	public RectangleHandler(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	@Override
	public void mouseMove(int x, int y) {
		switch (state) {
		case 1:
			rectangle.b.x = x;
			rectangle.b.y = y;
			break;
		case 2:
			rectangle.c.x = x;
			rectangle.c.y = y;
			break;
		case 3:
			rectangle.d.x = x;
			rectangle.d.y = y;
			break;
		}
	}

	@Override
	public boolean mouseClick(int x, int y) {
		switch (state) {
		case 0:
			rectangle.a.x = x;
			rectangle.a.y = y;
			rectangle.b.x = x;
			rectangle.b.y = y;
			rectangle.c.x = x;
			rectangle.c.y = y;
			rectangle.d.x = x;
			rectangle.d.y = y;
			state = 1;
			return false;
		case 1:
			rectangle.b.x = x;
			rectangle.b.y = y;
			state = 2;
			return false;
		case 2:
			rectangle.c.x = x;
			rectangle.c.y = y;
			state = 3;
			return false;
		case 3:
			rectangle.d.x = x;
			rectangle.d.y = y;
			state = 0;
			return true;
		}
		return false;
	}

	@Override
	public void paint(Graphics g) {
		// Draw the rectangle - not verified
		g.drawLine((int) rectangle.a.x, (int) rectangle.a.y, (int) rectangle.b.x, (int) rectangle.b.y);
		g.drawLine((int) rectangle.b.x, (int) rectangle.b.y, (int) rectangle.c.x, (int) rectangle.c.y);
		g.drawLine((int) rectangle.c.x, (int) rectangle.c.y, (int) rectangle.d.x, (int) rectangle.d.y);
		g.drawLine((int) rectangle.d.x, (int) rectangle.d.y, (int) rectangle.a.x, (int) rectangle.a.y);
	}

}
