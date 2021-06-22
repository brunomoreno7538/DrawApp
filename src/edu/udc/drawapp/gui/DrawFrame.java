package edu.udc.drawapp.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.udc.drawapp.DrawApp;
import edu.udc.drawapp.model.Circle;
import edu.udc.drawapp.model.Line;
import edu.udc.drawapp.model.Point;
import edu.udc.drawapp.model.Rectangle;
import edu.udc.drawapp.model.Triangle;
import edu.udc.drawapp.persistence.BinaryShapeFile;
import edu.udc.drawapp.persistence.SerialShapeFile;
import edu.udc.drawapp.persistence.ShapeFile;
import edu.udc.drawapp.persistence.TextShapeFile;
import edu.udc.drawapp.command.Command;
import edu.udc.drawapp.command.shapes.*;

public class DrawFrame extends JFrame {

	private DrawPanel contentPane;

	public DrawFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new DrawPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		DrawApp.getDocument().addObserver(contentPane);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnGeometrias = new JMenu("Geometrias");
		menuBar.add(mnGeometrias);

		JMenuItem mntmPoint = new JMenuItem("Ponto");
		mntmPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executeCommand(new DrawPointCommand(DrawApp.getDocument()));
			}
		});
		mnGeometrias.add(mntmPoint);

		JMenuItem mntmLine = new JMenuItem("Linha");
		mntmLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executeCommand(new DrawLineCommand(DrawApp.getDocument()));
			}
		});
		mnGeometrias.add(mntmLine);

		JMenuItem mntmCircle = new JMenuItem("Circulo");
		mntmCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executeCommand(new DrawCircleCommand(DrawApp.getDocument()));
			}
		});
		mnGeometrias.add(mntmCircle);

		JMenuItem mntmRectangle = new JMenuItem("Retângulo");
		mntmRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executeCommand(new DrawRectangleCommand(DrawApp.getDocument()));
			}
		});
		mnGeometrias.add(mntmRectangle);

		JMenuItem mntmTriangle = new JMenuItem("Triângulo");
		mntmTriangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executeCommand(new DrawTriangleCommand(DrawApp.getDocument()));
			}
		});
		mnGeometrias.add(mntmTriangle);

		JMenu mnArquivos = new JMenu("Arquivos");
		menuBar.add(mnArquivos);

		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File f = chooseFile(false);
				if (f == null) {
					return;
				}
				contentPane.lerArquivo(f);
			}
		});
		mnArquivos.add(mntmAbrir);

		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mntmSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File f = chooseFile(true);
				if (f == null) {
					return;
				}
				DrawApp.getDocument().salvarArquivo(f);
			}
		});
		mnArquivos.add(mntmSalvar);
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

	private ShapeFile chooseFileType(File f) {
		ShapeFile file = null;
		String name = f.getName();
		String ext = name.substring(name.lastIndexOf('.') + 1);
		if (ext.compareTo("ser") == 0) {
			file = new SerialShapeFile(f);
		}
		if (ext.compareTo("txt") == 0) {
			file = new TextShapeFile(f);
		}
		if (ext.compareTo("bin") == 0) {
			file = new BinaryShapeFile(f);
		}

		return file;
	}

	private void executeCommand(Command c) {
		if (c.execute()) {
			DrawApp.getDocument().addCommand(c);
		}
	}

	private void undo() {
		if (DrawApp.getDocument().getCommandHistory().isEmpty())
			return;
		Command command = DrawApp.getDocument().getCommandHistory()
				.get(DrawApp.getDocument().getCommandHistory().size() - 1);
		if (command != null) {
			DrawApp.getDocument().removeLastCommand();
			command.undo();
		}
	}
}
