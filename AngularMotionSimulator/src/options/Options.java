package options;

import java.awt.Color;
import java.io.File;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.filechooser.*;

import inputs.Input;

public class Options {
	private JFileChooser fileChooser;
	private Input input;
	
	private File file;
	
	public Options(Input input) {
		this.input = input;
		fileChooser = new JFileChooser();
	}
	
	public void save() {
		fileChooser.showSaveDialog(null);
		this.sendVariables();
	}
	
	public void load() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
		fileChooser.setFileFilter(filter);
		int returnVal = fileChooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " +
					fileChooser.getSelectedFile().getName());
		}
	}
	
	public void showHelp() {
		new HelpFrame();
	}
	
	public void changeColor() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JColorChooser colorChooser = new JColorChooser();
		panel.add(colorChooser);
		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.pack();
		Color color = colorChooser.getColor();
	}
	
	private void sendVariables() {
		try {
			Scanner in = new Scanner(file);
			input.setRadius(in.nextInt());
			in.close();
		} catch (Exception e) {
		}
	}
}
