package edu.udc.drawapp.command.shapes;

import edu.udc.drawapp.command.Command;
import edu.udc.drawapp.controller.DrawDocument;
import edu.udc.drawapp.model.Circle;
import edu.udc.drawapp.model.Point;

public class DrawCircleCommand extends Command {

	public DrawCircleCommand(DrawDocument doc) {
		super(doc);
	}

	@Override
	public boolean execute() {
		drawDocument.setCurrentDrawingShape(new Circle(new Point(-1, -1), 0));
		return true;
	}

	@Override
	public void undo() {
		super.undo();
	}

}
