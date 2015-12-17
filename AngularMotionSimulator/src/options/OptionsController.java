package options;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
			FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
			fileChooser.setFileFilter(filter);
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
		} catch (FileNotFoundException e) {}
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
			in.close();
		} catch (Exception e) {
			new ErrorDialog((JFrame)SwingUtilities.getWindowAncestor(menuBar), "File cannot be recognized");
		}
	}
}
