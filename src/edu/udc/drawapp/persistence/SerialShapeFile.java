package edu.udc.drawapp.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import edu.udc.drawapp.model.Shape;

public class SerialShapeFile implements ShapeFile {

	private File file;

	public SerialShapeFile(File file) {
		this.file = file;
	}

	@Override
	public void saveFile(List<Shape> lista) {
		try {
			FileOutputStream f = new FileOutputStream(file);
			ObjectOutputStream o = new ObjectOutputStream(f);
			for (Shape s : lista) {
				System.out.println(s);
				o.writeObject(s);
			}
			o.close();
			f.close();
		} catch (Exception e) {
		}

	}

	@Override
	public List<Shape> readFile() {
		List<Shape> shapes = new ArrayList();
		try {
			FileInputStream fi = new FileInputStream(file);
			ObjectInputStream oi = new ObjectInputStream(fi);
			while (oi != null) {
				shapes.add((Shape) oi.readObject());
			}
		} catch (Exception e) {

		}
		System.out.println(shapes);
		return shapes;
	}

}
