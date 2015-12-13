package options;

import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.*;

import inputs.ErrorDialog;
import inputs.Input;

public class Options {
	private OptionsMenuBar menuBar;
	private JFileChooser fileChooser;
	private Input input;
	
	private File file;
	
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
		/* radius
		 * angularVelocity
		 * linearVelocity
		 * arcLength
		 * time
		 * angle
		 */
		try {
			Scanner in = new Scanner(file);
			input.setRadius(in.nextDouble());
			input.setAngularVelocity(in.nextDouble());
			input.setLinearVelocity(in.nextDouble());
			input.setArcLength(in.nextDouble());
			input.setTime(in.nextDouble());
			input.setAngle(in.nextDouble());
			in.close();
		} catch (Exception e) {
			new ErrorDialog((JFrame)SwingUtilities.getWindowAncestor(menuBar), "File cannot be recognized");
		}
	}
	
	public void updateModels() {
		input.updateView();
		input.updateCalculations();
	}
}
