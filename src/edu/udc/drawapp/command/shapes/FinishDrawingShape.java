package edu.udc.drawapp.command.shapes;

import edu.udc.drawapp.command.Command;
import edu.udc.drawapp.controller.DrawDocument;

public class FinishDrawingShape extends Command {

	private DrawDocument drawDocument;

	public FinishDrawingShape(DrawDocument document) {
		super(document);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute() {
		// shapeList.add(currentShape);
		// updateObservers();
		return true;
	}

	@Override
	public void undo() {
		super.undo();
	}
}
