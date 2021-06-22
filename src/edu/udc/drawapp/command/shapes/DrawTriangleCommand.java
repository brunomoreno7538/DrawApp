package edu.udc.drawapp.command.shapes;

import edu.udc.drawapp.command.Command;
import edu.udc.drawapp.controller.DrawDocument;
import edu.udc.drawapp.model.Point;
import edu.udc.drawapp.model.Triangle;

public class DrawTriangleCommand extends Command {

	public DrawTriangleCommand(DrawDocument doc) {
		super(doc);
	}

	@Override
	public boolean execute() {
		drawDocument.setCurrentDrawingShape(new Triangle(new Point(-1, -1), new Point(-1, -1), new Point(-1, -1)));
		return true;
	}

	@Override
	public void undo() {
		super.undo();
	}

}
