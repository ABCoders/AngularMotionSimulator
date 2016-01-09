package options;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;

import inputs.AnswerMachinePanel;
import inputs.Input;
import inputs.InputFieldPanel;
import inputs.WantedFieldPanel;

/**
 * The Menu bar of the angular motion simulator program that allows the user to save, load, change the color
 * the animation, and get help.
 * @author Bryan Kristiono
 * @author Amritpal Aujla
 * @since 12/12/2015
 */
public class OptionsMenuBar extends JMenuBar {
	private Input input;							//The model that contains information of the given variables
	private AnswerMachinePanel answerMachinePanel;	//The view where the user input all the variable values
	
	private JMenu file;								//The menu for file related menu items
	private JMenu animation;						//The menu for animation related menu items
	private JMenu other;							//The menu for other related menu items
	
	private JMenuItem saveItem;						//The menu item for saving information
	private JMenuItem loadItem;						//The menu item for loading information
	private JMenuItem helpItem;						//The menu item for showing the help frame
	private JMenuItem colorItem;					//The menu item for choosing the color of the animation
	private JCheckBoxMenuItem reverseItem;          //The menu item for reversing the direction the animation is moving
	private JCheckBoxMenuItem circleItem;     		//The menu item for choosing whether click the animation draws a circle or not
	private JMenuItem undoItem;						//The menu item that undoes an action with the red dots in animation
	
	/**
	 * Initializes a new OptionsMenuBar that sends data to Input from the information given from AnswerMachinePanel.
	 * @param input					The model that contains information of the given variables
	 * @param answerMachinePanel	The view that contains all the components with user given variable values
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
	 * Initialize and set values to all needed components.
	 */
	private void createComponents() {
		//JMenus
		file = new JMenu("File");
		other = new JMenu("Other");
		this.animation = new JMenu("Animation");
		
		//JMenuItems
		saveItem = new JMenuItem("Save As");
		loadItem = new JMenuItem("Load");
		helpItem = new JMenuItem("Help");
		colorItem = new JMenuItem("Change Color");
		this.reverseItem = new JCheckBoxMenuItem("Reverse Animation Direction");
		this.circleItem = new JCheckBoxMenuItem("Do Not Draw Red Circle");
		this.undoItem = new JMenuItem("Undo");
		
		//setting the accelerators for the items
		this.setAccelerators();
	}
	
	/**
	 * Set up the components in the menu bar.
	 */
	private void createMenuBar() {
		//Adding menu items to appropriate menus
		file.add(saveItem);
		file.add(loadItem);
		this.animation.add(this.reverseItem);
		this.animation.add(this.circleItem);
		this.animation.add(this.undoItem);
		other.add(helpItem);
		other.add(colorItem);
		
		//Adding the menus to the menu bar
		this.add(file);
		this.add(this.animation);
		this.add(other);
	}
	
	/** Sets the menu items for animation to be accessible using keyboard shortcuts
	 */
	private void setAccelerators(){
		this.reverseItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		this.circleItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		this.undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
	}
	
	/**
	 * Add Listeners to all components.
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
		this.reverseItem.addActionListener(controller);
		this.circleItem.addActionListener(controller);
		this.undoItem.addActionListener(controller);
	}
}