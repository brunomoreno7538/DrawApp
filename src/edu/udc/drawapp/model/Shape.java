package edu.udc.drawapp.model;

import java.io.Serializable;

import edu.udc.drawapp.model.handler.ShapeHandler;

public interface Shape extends Serializable {

	ShapeHandler getHandler(); // método de fábrica para objetos da abstração (que implementam a interface)
								// ShapeHandler

}
