/**
 * 
 */
package edu.udc.drawapp;

import java.sql.PreparedStatement;

import edu.udc.drawapp.controller.DrawDocument;
import edu.udc.drawapp.gui.DrawFrame;
import edu.udc.drawapp.model.Point;
import edu.udc.drawapp.persistence.jdbc.JDBCConnection;
import edu.udc.drawapp.persistence.jdbc.dao.DrawDAO;
import edu.udc.drawapp.persistence.jdbc.dao.LineDAO;
import edu.udc.drawapp.persistence.jdbc.dao.PointDAO;
import edu.udc.drawapp.persistence.jdbc.dao.RectangleDAO;
import edu.udc.drawapp.persistence.jdbc.dao.TriangleDAO;

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
		RectangleDAO pd = new RectangleDAO(1);
		pd.getAll();
		
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
