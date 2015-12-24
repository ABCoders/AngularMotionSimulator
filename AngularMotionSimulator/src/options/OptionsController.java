package options;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import inputs.ErrorDialog;
import inputs.Input;
import inputs.InputFieldPanel;
import inputs.WantedFieldPanel;

public class OptionsController implements ActionListener {
	private Input input;
	private HelpFrame help;
	private JFileChooser fileChooser;
	private OptionsMenuBar menuBar;
	private File file;
	
	private ArrayList<InputFieldPanel> inputFields;
	private WantedFieldPanel wantedField;
	
	public OptionsController(Input input, OptionsMenuBar menuBar, ArrayList<InputFieldPanel> inputFields, WantedFieldPanel wantedField) {
		this.input = input;
		this.menuBar = menuBar;
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
		
		this.inputFields = inputFields;
		this.wantedField = wantedField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Choosing the help button
		if (e.getActionCommand().equalsIgnoreCase("help")) {
			if (help!=null) {
				help.dispose();
				help = null;
			}
			help = new HelpFrame();
		}
		//Choosing to change color
		else if (e.getActionCommand().equalsIgnoreCase("change color")) {
			new ColorChooserDialog((JFrame)SwingUtilities.getWindowAncestor(menuBar), input.getAnimation());
		}
		//Choosing to save inputs
		else if (e.getActionCommand().equalsIgnoreCase("save as")) {
			int fileSelected = fileChooser.showSaveDialog(null);
			if (fileSelected == JFileChooser.APPROVE_OPTION) {
				String path = fileChooser.getSelectedFile().getAbsolutePath();
				if(path.substring(path.length() - 4).equals(".txt")){
					path = fileChooser.getSelectedFile().getAbsolutePath();
				}
				else
					path = fileChooser.getSelectedFile().getAbsolutePath() + ".txt";
				file = new File(path);
				saveVariables();
			}
		}
		//Choosing to load inputs
		else if (e.getActionCommand().equalsIgnoreCase("load")) {
			int fileSelected = fileChooser.showOpenDialog(null);
			if(fileSelected == JFileChooser.APPROVE_OPTION) {
				String path = fileChooser.getSelectedFile().getAbsolutePath();
				if(path.substring(path.length() - 4).equals(".txt")){
					path = fileChooser.getSelectedFile().getAbsolutePath();
				}
				else
					path = fileChooser.getSelectedFile().getAbsolutePath() + ".txt";
				file = new File(path);
				loadVariables();
			}
		}
	}
	
	private void saveVariables() {
		try {
			PrintWriter out = new PrintWriter(file);
			input.resetVariables();
			input.setWantedVariable(wantedField.getSelectedVariable());
			for (InputFieldPanel field: inputFields) {
				field.update();
				for(int i=0; i<Input.VARIABLES.length; i++) {
					if (field.getSelectedVariable().equals(Input.VARIABLES[i])) {
						input.setVariableValue(i, field.getValue());
						break;
					}
				}
			}
			
			out.println(input.getWantedVariable());
			for(int i=0; i<Input.VARIABLES.length; i++) {
				out.println(input.getVariableValue(i));
			}
			out.close();
		} catch (FileNotFoundException e) {
			new ErrorDialog((JFrame)SwingUtilities.getWindowAncestor(menuBar), "File cannot be recognized");
		}
	}
	
	private void loadVariables() {
		try {
			Scanner in = new Scanner(file);
			input.setWantedVariable(in.nextLine());
			for(int i=0; i<Input.VARIABLES.length; i++) {
//				System.err.println(Input.VARIABLES[i]);
				input.setVariableValue(i, in.nextDouble());
			}
			if(!input.updateModel())
				new ErrorDialog((JFrame)SwingUtilities.getWindowAncestor(menuBar), "Incorrect File Layout");
			in.close();
		} catch (FileNotFoundException e) {
			new ErrorDialog((JFrame)SwingUtilities.getWindowAncestor(menuBar), "File cannot be recognized");
		}
	}
}
