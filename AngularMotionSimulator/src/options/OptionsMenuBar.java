package options;

import java.util.ArrayList;

import javax.swing.*;

import inputs.AnswerMachinePanel;
import inputs.Input;
import inputs.InputFieldPanel;
import inputs.WantedFieldPanel;

/**
 * OptionsMenuBar
 * <p>
 * The Menu bar of the angular motion simulator program that allows the user to save, load, change the color
 * the animation, and get help.
 * @author BRYAN KRISTIONO
 * @since 12/12/2015
 */
public class OptionsMenuBar extends JMenuBar {
	private Input input;							//The model that contains information of the given variables
	private AnswerMachinePanel answerMachinePanel;	//The view where the user input all the variable values
	
	private JMenu file;								//The menu for file related menu items
	private JMenu other;							//The menu for other related menu items
	
	private JMenuItem saveItem;						//The menu item for saving information
	private JMenuItem loadItem;						//The menu item for loading information
	private JMenuItem helpItem;						//The menu item for showing the help frame
	private JMenuItem colorItem;					//The menu item for choosing the color of the animation
	private JRadioButtonMenuItem oneCircleItem;		
	private JRadioButtonMenuItem twoCircleItem;
	
	/**
	 * Initializes a new OptionsMenuBar that sends data to Input from the information given from AnswerMachinePanel
	 * @param input					- The model that contains information of the given variables
	 * @param answerMachinePanel	- The view that contains all the components with user given variable values
	 */
	public OptionsMenuBar(Input input, AnswerMachinePanel answerMachinePanel) {
		super();
		this.input = input;
		createComponents();
		createMenuBar();
		this.answerMachinePanel = answerMachinePanel;
		registerControllers();
	}
	
	/**
	 * Initialize and set values to all needed components
	 */
	private void createComponents() {
		//JMenus
		file = new JMenu("File");
		other = new JMenu("Other");
		
		//JMenuItems
		saveItem = new JMenuItem("Save As");
		loadItem = new JMenuItem("Load");
		helpItem = new JMenuItem("Help");
		colorItem = new JMenuItem("Change Color");
		
		//JRadioButtonMenuItems
		oneCircleItem = new JRadioButtonMenuItem("One Animation");
		twoCircleItem = new JRadioButtonMenuItem("Two Animations");
	}
	
	/**
	 * Set up the components in the menu bar
	 */
	private void createMenuBar() {
		//Adding menu items to appropriate menus
		file.add(saveItem);
		file.add(loadItem);
		other.add(helpItem);
		other.add(colorItem);
		other.addSeparator();
		
		//Creating a group for the radio buttons
		oneCircleItem.setSelected(true);
		ButtonGroup group = new ButtonGroup();
		group.add(oneCircleItem);
		group.add(twoCircleItem);
		other.add(oneCircleItem);
		other.add(twoCircleItem);
		
		//Adding the menus to the menu bar
		this.add(file);
		this.add(other);
	}
	
	/**
	 * Add Listeners to all components
	 */
	private void registerControllers() {
		//Initializes the controller
		WantedFieldPanel wantedField = answerMachinePanel.getWantedFieldPanel();
		ArrayList<InputFieldPanel> inputFields = answerMachinePanel.getInputPanel().getFields();
		OptionsController controller = new OptionsController(input, this, inputFields, wantedField);
		
		//Adds the listeners
		saveItem.addActionListener(controller);
		loadItem.addActionListener(controller);
		helpItem.addActionListener(controller);
		colorItem.addActionListener(controller);
		oneCircleItem.addActionListener(controller);
		twoCircleItem.addActionListener(controller);
	}
}
