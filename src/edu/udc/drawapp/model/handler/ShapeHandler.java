package edu.udc.drawapp.model.handler;

import java.awt.Graphics;

public interface ShapeHandler {

	void mouseMove(int x, int y);

	void mouseClick(int x, int y);

	void paint(Graphics g);

}
