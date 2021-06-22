package edu.udc.drawapp.gui;

import java.util.List;

import edu.udc.drawapp.model.Shape;

public interface DrawView {
	void update(List<Shape> shapeList);
}
