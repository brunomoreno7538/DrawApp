package edu.udc.drawapp.model;

import java.io.Serializable;

import edu.udc.drawapp.model.handler.ShapeHandler;

public interface Shape extends Serializable {

	ShapeHandler getHandler(); // método de fábrica para objetos da abstração (que implementam a interface)
								// ShapeHandler
	public enum ShapeState {
		NOT_INITIALIZED(0),
		DRAWING(1),
		FINISHED(2);

		private int value;
		
		ShapeState(int value) {
			this.value = value;
		}
		
		private int getValue() {
			return this.value;
		}
	}
	
	void setState(ShapeState state);
	
	ShapeState getState();
}
