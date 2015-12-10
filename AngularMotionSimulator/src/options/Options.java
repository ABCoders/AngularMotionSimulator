package options;

import java.io.File;

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
		
	}
}
