package options;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import inputs.Input;

public class OptionsController implements ActionListener {
	private Input input;
	private HelpFrame help;
	private JFileChooser fileChooser;
	
	public OptionsController(Input input) {
		this.input = input;
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
				this.saveVariables();
			}
		}
		else if (e.getActionCommand().equalsIgnoreCase("load")) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
			fileChooser.setFileFilter(filter);
			int fileSelected = fileChooser.showOpenDialog(null);
			if(fileSelected == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile();
				this.loadVariables();
			}
		}
	}
}
