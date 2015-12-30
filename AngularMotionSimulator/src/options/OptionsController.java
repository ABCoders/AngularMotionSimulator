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

/**
 * A Controller that listens events created in the options menu bar. 
 * It can save information from the program to a text file, as well as load information to the
 * program from a text file.
 * <p>
 * It has other features like opening a help frame and changing the color of the animation.
 * @author BRYAN KRISTIONO
 * @since 12/12/2015
 */
public class OptionsController implements ActionListener {
	private Input input;				//The model that contains information of the given variables
	private HelpFrame help;				//The class used to display a help frame
	private JFileChooser fileChooser;	//The class used to choose files to save and load
	private OptionsMenuBar menuBar;		//The view it is listening to
	private File file;					//The file used for saving and loading
	
	private ArrayList<InputFieldPanel> inputFields;	//The list of all input fields
	private WantedFieldPanel wantedField;			//The view that contains the wanted variable
	
	/**
	 * Creates a new OptionsController.
	 * @param input			The model that contains information of the given variables
	 * @param menuBar		The view OptionsController is listening to
	 * @param inputFields	The list of all input fields
	 * @param wantedField	The view containing the wanted variable
	 */
	public OptionsController(Input input, OptionsMenuBar menuBar, ArrayList<InputFieldPanel> inputFields, WantedFieldPanel wantedField) {
		this.input = input;
		this.menuBar = menuBar;
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
		
		this.inputFields = inputFields;
		this.wantedField = wantedField;
	}
	
	/**
	 * Depending on the menu item selected, it can either show the help frame, change the color of the animation,
	 * save the information from the program to a file, or load the information from a file to the program.
	 * @param e The event from when a menu item is selected
	 */
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
		else if (e.getActionCommand().equalsIgnoreCase("one animation")) {
			input.setNumberPanel(1);
		}
		else if (e.getActionCommand().equalsIgnoreCase("two animations")) {
			input.setNumberPanel(2);
		}
	}
	
	/**
	 * Saves the variables from the program to the selected file in a certain fashion that
	 * can be read in a later moment.
	 */
	private void saveVariables() {
		try {
			PrintWriter out = new PrintWriter(file);
			input.resetVariables();
			
			//Gets the information from the list of input fields and wanted variable view
			//Saves the information to the Input class
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
			
			//Saves the information from the Input class to the chosen file
			out.println(input.getWantedVariable());
			for(int i=0; i<Input.VARIABLES.length; i++) {
				out.println(input.getVariableValue(i));
			}
			out.close();
		} catch (FileNotFoundException e) {
			//Gives an error if file is not found
			new ErrorDialog((JFrame)SwingUtilities.getWindowAncestor(menuBar), "File cannot be recognized");
		}
	}
	
	/**
	 * Loads the variables to the program from the selected file.
	 */
	private void loadVariables() {
		try {
			Scanner in = new Scanner(file);
			
			//Gets information from the file and sends it to Input
			input.setWantedVariable(in.nextLine());
			for(int i=0; i<Input.VARIABLES.length; i++) {
				input.setVariableValue(i, in.nextDouble());
			}
			
			//If the information given is not proper
			if(!input.updateModel())
				new ErrorDialog((JFrame)SwingUtilities.getWindowAncestor(menuBar), "Incorrect File Layout");
			in.close();
		} catch (FileNotFoundException e) {
			//If the file chosen cannot be found
			new ErrorDialog((JFrame)SwingUtilities.getWindowAncestor(menuBar), "File cannot be recognized");
		}
	}
}