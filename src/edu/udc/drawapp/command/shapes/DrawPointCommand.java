package edu.udc.drawapp.command.shapes;

import edu.udc.drawapp.command.Command;
import edu.udc.drawapp.controller.DrawDocument;
import edu.udc.drawapp.model.Point;

public class DrawPointCommand extends Command {

	public DrawPointCommand(DrawDocument doc) {
		super(doc);
	}

	@Override
	public boolean execute() {
		drawDocument.setCurrentDrawingShape(new Point(-1, -1));
		return true;
	}

	@Override
	public void undo() {
		super.undo();
	}

}
