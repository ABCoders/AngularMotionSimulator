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

public class OptionsController implements ActionListener {
	private Input input;
	private HelpFrame help;
	private JFileChooser fileChooser;
	private OptionsMenuBar menuBar;
	private File file;
	
	public OptionsController(Input input, OptionsMenuBar menuBar) {
		this.input = input;
		this.menuBar = menuBar;
		fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
		fileChooser.setFileFilter(filter);
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
				file = fileChooser.getSelectedFile();
				saveVariables();
			}
		}
		else if (e.getActionCommand().equalsIgnoreCase("load")) {
			int fileSelected = fileChooser.showOpenDialog(null);
			if(fileSelected == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile();
				loadVariables();
			}
		}
	}
	
	private void saveVariables() {
		try {
			PrintWriter out = new PrintWriter(file);
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
			ArrayList<String> variables = new ArrayList<String>();
			ArrayList<Double> values = new ArrayList<Double>();
			String wantedVariable = in.next();
			for(int x = 0; x < 6; x++){
				Double value = in.nextDouble();
				if(value != null){
				switch(x){
					case 0:
						variables.add("Radius");
						values.add(value);
						break;
					case 1:
						variables.add("Angular Velocity");
						values.add(value);
						break;
					case 2:
						variables.add("Linear Velocity");
						values.add(value);
						break;
					case 3:
						variables.add("Arc Length");
						values.add(value);
						break;
					case 4:
						variables.add("Time");
						values.add(value);
						break;
					case 5:
						variables.add("Angle");
						values.add(value);
						break;
					}
				}
			}
			this.input.getInputPanel().updateFields(variables, values);
			in.close();
		} catch (FileNotFoundException e) {
			new ErrorDialog((JFrame)SwingUtilities.getWindowAncestor(menuBar), "File cannot be recognized");
		}
	}
}
