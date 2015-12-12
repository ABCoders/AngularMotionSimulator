package options;

import java.awt.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.*;

import inputs.Input;

public class Options {
	private OptionsMenuBar menuBar;
	private JFileChooser fileChooser;
	private Input input;
	
	private File file;
	
	private Color color;
	
	public Options(Input input) {
		this.input = input;
		fileChooser = new JFileChooser();
		file = null;
	}
	
	public void setGUI(OptionsMenuBar menuBar) {
		this.menuBar = menuBar;
	}
	
	public void save() {
		int fileSelected = fileChooser.showSaveDialog(null);
		if (fileSelected == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			this.saveVariables();
		}
	}
	
	public void load() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
		fileChooser.setFileFilter(filter);
		int fileSelected = fileChooser.showOpenDialog(null);
		if(fileSelected == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			this.loadVariables();
		}
	}
	
	public void changeColor() {
		new ColorChooserDialog((JFrame)SwingUtilities.getWindowAncestor(menuBar), input.getAnimation());
	}
	
	private void saveVariables() {
		try {
			PrintWriter out = new PrintWriter(file);
			out.println();
			out.close();
		} catch (FileNotFoundException e) {}
	}
	
	private void loadVariables() {
		try {
			Scanner in = new Scanner(file);
			while (in.hasNextInt()) {
				input.setRadius(in.nextInt());
			}
			in.close();
		} catch (Exception e) {
		}
	}
	
	public void updateModels() {
		input.updateView();
		input.updateCalculations();
	}
}
