package edu.udc.drawapp.gui;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import edu.udc.drawapp.DrawApp;
import edu.udc.drawapp.controller.DrawDocument;
import edu.udc.drawapp.model.Shape;
public class DrawPanel extends JPanel implements DrawView {

	private Shape shape;
	private DrawDocument drawDocument;
	public List<Shape> shapeList = new ArrayList<Shape>();

	/**
	 * Create the panel.
	 */
	public DrawPanel() {
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				if (shape != null) {
					shape.getHandler().mouseMove(e.getX(), e.getY());
					repaint();
				}
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				shape = DrawApp.getDocument().getCurrentDrawingShape();
				if (shape != null) {
					System.out.println(shape.getHandler().getClass().getSimpleName());
					if(shape.getState().mouseClick(e.getX(), e.getY())) {
						shapeList.add(shape);
						DrawApp.getDocument().finishDrawingShape();
					}
					repaint();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Shape s : shapeList) {
			s.getHandler().paint(g);
		}
		if (shape != null) {
			shape.getHandler().paint(g);
		}
	}

	public void lerArquivo(File file) {
		shapeList = DrawApp.getDocument().lerArquivo(file);
		repaint();
	}
	
	public void carregarDb(List<Shape> shapeList) {
		System.out.println(shapeList);
		this.shapeList = shapeList;
		repaint();
	}

	@Override
	public void update(List<Shape> shapeList) {
		this.shapeList = shapeList;
		repaint();
	}

}
