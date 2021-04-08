package edu.udc.drawapp.persistence;

import java.util.List;

import edu.udc.drawapp.model.Shape;

public interface ShapeFile {
	void saveFile(List<Shape> lista);

	List<Shape> readFile();
}
