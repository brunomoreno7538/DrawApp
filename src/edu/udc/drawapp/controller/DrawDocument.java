package edu.udc.drawapp.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.udc.drawapp.command.Command;
import edu.udc.drawapp.gui.DrawView;
import edu.udc.drawapp.model.Shape;
import edu.udc.drawapp.persistence.BinaryShapeFile;
import edu.udc.drawapp.persistence.SerialShapeFile;
import edu.udc.drawapp.persistence.ShapeFile;
import edu.udc.drawapp.persistence.TextShapeFile;

public class DrawDocument {
	private ShapeFile shapeFile = null;
	private List<Shape> shapeList;
	private Shape currentShape;
	private List<DrawView> observerList;
	private List<Command> commandHistory = new Stack<>();

	private static final DrawDocument instance = new DrawDocument();

	private DrawDocument() {
		shapeList = new ArrayList<>();
		observerList = new LinkedList<>();
	}

	public void addObserver(DrawView view) {
		observerList.add(view);
	}

	public void removeObserver(DrawView view) {
		observerList.remove(view);
	}

	public void updateObservers() {
		for (DrawView view : observerList) {
			view.update(shapeList);
		}
	}

	public List<Shape> getShapeList() {
		return shapeList;
	}

	public void removeLastShape() {
		this.shapeList.remove(this.shapeList.size() - 1);
	}

	public void setCurrentDrawingShape(Shape shape) {
		currentShape = shape;
	}

	public Shape getCurrentDrawingShape() {
		return currentShape;
	}

	public void finishDrawingShape() {
		shapeList.add(currentShape);
		updateObservers();
	}

	public void novoArquivo() {
		shapeList.clear();
		updateObservers();
	}

	public void addCommand(Command c) {
		commandHistory.add(c);
	}

	public void removeLastCommand() {
		this.commandHistory.remove(this.commandHistory.size() - 1);
	}

	public List<Command> getCommandHistory() {
		return this.commandHistory;
	}

	public List<Shape> lerArquivo(File file) {
		chooseFileType(file);
		shapeList.clear();
		shapeList = shapeFile.readFile();
		updateObservers();
		return shapeList;
	}

	public void salvarArquivo(File file) {
		chooseFileType(file);
		shapeFile.saveFile(shapeList);
	}

	private File chooseFile(boolean gravar) {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		FileNameExtensionFilter textFilterS = new FileNameExtensionFilter("Serial Filter", "ser");
		fc.addChoosableFileFilter(textFilterS);
		FileNameExtensionFilter textFilterT = new FileNameExtensionFilter("Text file", "txt");
		fc.addChoosableFileFilter(textFilterT);
		FileNameExtensionFilter textFilterB = new FileNameExtensionFilter("Binary File", "bin");
		fc.addChoosableFileFilter(textFilterB);

		fc.setFileFilter(textFilterT);

		int result = gravar ? fc.showSaveDialog(null) : fc.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		}

		return null;
	}

	private void chooseFileType(File f) {
		String name = f.getName();
		String ext = name.substring(name.lastIndexOf('.') + 1);
		if (ext.compareTo("ser") == 0) {
			shapeFile = new SerialShapeFile(f);
		}
		if (ext.compareTo("txt") == 0) {
			shapeFile = new TextShapeFile(f);
		}
		if (ext.compareTo("bin") == 0) {
			shapeFile = new BinaryShapeFile(f);
		}
	}

	public static DrawDocument getInstance() {
		return instance;
	}

}
