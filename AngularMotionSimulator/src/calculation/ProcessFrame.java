//package and imports
package calculation;
import javax.swing.*;
import java.awt.*;

import inputs.Input;

/** ProcessFrame
 *  The main view of the calculations contains the individual panels showing givens/calculation process and the save button
 *  @author Amritpal Aujla
 *  @since 26/12/2015 
 */
public class ProcessFrame extends JFrame{
	//attributes
	private Calculations calculations;					//the Calculations model to create the process frame and give it values
	private Input input;                                //the input view to get the value of the gives
	private JPanel mainPanel;                           //the main panel holding all the components
	private GivensPanel givensPanel;					//the givens panel showing the values that the user input
	private CalculationsPanel calcPanel;				//the calculations panel showing the calculation process for the wanted variable
	private JButton save;								//the save button to save the process inside calculations panel

	/** The Default Constructor makes a ProcessFrame, initializes input and calculations, create all components, frame, and controllers
	 *  @param calculations the calculations model for the calculations process that the frame needs
	 *  @param input the input model with the givens that the frame needs
	 */
	public ProcessFrame(Calculations calculations, Input input) {
		super();
		this.calculations = calculations;
		this.input = input;
	    this.createComponents();
	    this.createFrame();
	    this.registerControllers();
	}

	/** Makes the frame, sets its properties and puts all components in their appropriate places
	 */
	private void createFrame(){
		//setting the frame's properties to visible and size to 600 by 300
		this.setVisible(true);
	    this.setSize(600,300);
	    this.setLocation(this.input.getView().getTopLevelAncestor().getX(), this.input.getView().getTopLevelAncestor().getY() + this.input.getView().getY());
	    
	    //putting all components into their appropriate place in the grid layout
	    this.mainPanel.setLayout(new GridLayout(3, 1));
	    this.mainPanel.add(givensPanel);
	    this.mainPanel.add(calcPanel);
	    this.mainPanel.add(save);
	    
	    //setting the content pane to the main panel
	    this.setContentPane(this.mainPanel);
	}
	
	/** Initializes the four components to make them ready to be put into the frame
	 */
	private void createComponents(){
		this.mainPanel = new JPanel();
		this.givensPanel = new GivensPanel(this.input);
	    this.calcPanel = new CalculationsPanel(this.calculations);
	    this.save = new JButton("Save");
	}
	
	/** Registers the controller for the save button
	 */
	private void registerControllers(){
		ProcessSaveController processSaveController;             //the controller to attach to the save button
		processSaveController = new ProcessSaveController(this.calcPanel.getEquation(), this.calcPanel.getValueEquation(), this.calcPanel.getResult());
		
		//adding the action listener to the controller
		this.save.addActionListener(processSaveController);
	}
	
}