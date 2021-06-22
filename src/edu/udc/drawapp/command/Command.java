package edu.udc.drawapp.command;

import java.util.List;

import edu.udc.drawapp.model.Shape;
import edu.udc.drawapp.controller.DrawDocument;

public abstract class Command {
	protected DrawDocument drawDocument;
	private List<Shape> shapeList;

	public Command(DrawDocument document) {
		this.drawDocument = document;
	}

	public abstract boolean execute();

	public void undo() {
		this.drawDocument.removeLastShape();
	}

}
