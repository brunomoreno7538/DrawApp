package edu.udc.drawapp.model.handler;

import java.awt.Graphics;

import edu.udc.drawapp.model.Line;

public class LineHandler implements ShapeHandler {

	private Line line;
	/*
	 * Drawing state 0 - not drawing 1 - center defined
	 */
	private static int state;

	public LineHandler(Line line) {
		this.line = line;
	}

	@Override
	public void mouseMove(int x, int y) {
		switch (state) {
		case 1:
			line.b.x = x;
			line.b.y = y;
			break;
		}
	}

	@Override
	public boolean mouseClick(int x, int y) {
		switch (state) {
		case 0:
			line.a.x = x;
			line.a.y = y;
			line.b.x = x;
			line.b.y = y;
			state = 1;
			return false;
		case 1:
			System.out.println("CU");
			line.b.x = x;
			line.b.y = y;
			state = 0;
			return true;
		}
		return false;
	}

	@Override
	public void paint(Graphics g) {
		g.drawLine((int) line.a.x, (int) line.a.y, (int) line.b.x, (int) line.b.y);
	}

}
