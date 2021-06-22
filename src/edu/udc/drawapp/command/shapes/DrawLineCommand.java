package edu.udc.drawapp.command.shapes;

import edu.udc.drawapp.command.Command;
import edu.udc.drawapp.controller.DrawDocument;
import edu.udc.drawapp.model.Line;
import edu.udc.drawapp.model.Point;

public class DrawLineCommand extends Command {

	public DrawLineCommand(DrawDocument doc) {
		super(doc);
	}

	@Override
	public boolean execute() {
		drawDocument.setCurrentDrawingShape(new Line(new Point(-1, -1), new Point(-1, -1)));
		return true;
	}

	@Override
	public void undo() {
		super.undo();
	}

}
