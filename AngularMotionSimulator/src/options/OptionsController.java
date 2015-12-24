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
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
		fileChooser.setFileFilter(filter);
		
		this.inputFields = inputFields;
		this.wantedField = wantedField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("help")) {
			if (help!=null) {
				help.dispose();
				help = null;
			}
			help = new HelpFrame();
		}
		else if (e.getActionCommand().equalsIgnoreCase("change color")) {
			new ColorChooserDialog((JFrame)SwingUtilities.getWindowAncestor(menuBar), input.getAnimation());
		}
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
				if (field.getSelectedVariable().equals(Input.VARIABLES[0])) {
					input.setAngularVelocity(field.getValue());
				} else if (field.getSelectedVariable().equals(Input.VARIABLES[1])) {
					input.setLinearVelocity(field.getValue());
				} else if (field.getSelectedVariable().equals(Input.VARIABLES[2])) {
					input.setRadius(field.getValue());
				} else if (field.getSelectedVariable().equals(Input.VARIABLES[3])) {
					input.setArcLength(field.getValue());
				} else if (field.getSelectedVariable().equals(Input.VARIABLES[4])) {
					input.setTime(field.getValue());
				} else if (field.getSelectedVariable().equals(Input.VARIABLES[5])) {
					input.setAngle(field.getValue());
				}
			}
			
			out.println(input.getWantedVariable());
			out.println(input.getRadius());
			out.println(input.getAngularVelocity());
			out.println(input.getLinearVelocity());
			out.println(input.getArcLength());
			out.println(input.getTime());
			out.println(input.getAngle());
			out.close();
		} catch (FileNotFoundException e) {
			new ErrorDialog((JFrame)SwingUtilities.getWindowAncestor(menuBar), "File cannot be recognized");
		}
	}
	
	private void loadVariables() {
		try {
			Scanner in = new Scanner(file);
			input.setWantedVariable(in.nextLine());
			input.setRadius(in.nextDouble());
			input.setAngularVelocity(in.nextDouble());
			input.setLinearVelocity(in.nextDouble());
			input.setArcLength(in.nextDouble());
			input.setTime(in.nextDouble());
			input.setAngle(in.nextDouble());
			if(!input.updateModel())
				new ErrorDialog((JFrame)SwingUtilities.getWindowAncestor(menuBar), "Incorrect File Layout");
			in.close();
		} catch (FileNotFoundException e) {
			new ErrorDialog((JFrame)SwingUtilities.getWindowAncestor(menuBar), "File cannot be recognized");
		}
	}
}
