/**
 * 
 */
package edu.udc.drawapp;

import edu.udc.drawapp.controller.DrawDocument;
import edu.udc.drawapp.gui.DrawFrame;

/**
 * @author matrakas
 *
 */
public class DrawApp {

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DrawFrame frame = new DrawFrame();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DrawDocument getDocument() {
		return DrawDocument.getInstance();
	}

}
