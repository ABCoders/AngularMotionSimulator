package inputs;

import java.util.TreeMap;
import animation.Animation;
import calculation.Calculations;

/**
 * Inputs information from user and sends to calculations model and updates input fields in answer machine
 * @author Cindy Zhao
 * @author Bryan Kristiono
 * @author Amritpal Aujla
 * @since 09/12/15
 */

public class Input extends Object{
	public static final String[] VARIABLES = new String[] {"Angular Velocity", //A constant memory location for all variable names
															"Linear Velocity",
															"Radius",
															"Arc Length",
															"Time",
															"Angle"};
	private TreeMap<String, Double> variables; //Variables names and values
	
	private Animation animation; //The Animation model
	private Calculations calculations; //The Calculations model the Input is managing
	private AnswerMachinePanel answerMachinePanel; //View for Input
	
	public static final int MIN_FIELDS = 2; //Minimum number of fields
	public static final int MAX_FIELDS = 5; //Maximum number of fields
	
	private int numberFields; //The current number of input fields
	private int removedField; //The location of the field being removed
	
	private String wantedVariable; //The variable the user wants to solve for
	
	/**
	 * Main Constructor to initialize variables
	 */
	public Input() 
	{
		this.calculations = new Calculations(this);
		this.animation = new Animation(calculations);
		this.variables = new TreeMap<String, Double>();
		
		this.numberFields = MIN_FIELDS;
		this.resetVariables();
		this.wantedVariable = VARIABLES[0];
	}
	
	/**
	 * Sets GUI for Input
	 * @param answerMachinePanel GUI to set
	 */
	public void setGUI(AnswerMachinePanel answerMachinePanel) //Sets GUI
	{
		this.answerMachinePanel = answerMachinePanel;
	}
	
	/**
	 * Adds an extra input field
	 */
	public void addField() {
		if (numberFields<MAX_FIELDS) {
			numberFields++;
			this.updateInputPanel(false);
		}
	}
	
	/**
	 * Removes an input field if number of fields is greater than 2
	 * @param position position of field to remove
	 */
	public void removeField(int position) {
		if (numberFields>MIN_FIELDS) {
			numberFields--;
			this.removedField = position;
			this.updateInputPanel(false);
		}
	}

	/**
	 * Sets the variable user wants to calculate for
	 * @param wantedVariable the wantedVariable to set
	 */
	public void setWantedVariable(String wantedVariable) {
		this.wantedVariable = wantedVariable;
	}
	
	/**
	 * Returns number of current input fields
	 * @return number of fields
	 */
	public int getNumberFields() {
		return this.numberFields;
	}
	
	/**
	 * Returns location of field being removed
	 * @return location of removed field
	 */
	public int getRemovedField() {
		return removedField;
	}
	
	/**
	 * Returns the variable the user wants to solve for
	 * @return wanted variable
	 */
	public String getWantedVariable() {
		return this.wantedVariable;
	}
	
	/**
	 * Returns animation model of input
	 * @return animation model
	 */
	public Animation getAnimation() {
		return this.animation;
	}
	
	/**
	 * Returns calculations model of input
	 * @return calculations model
	 */
	public Calculations getCalculations() {
		return this.calculations;
	}
	
	/**
	 * Sets value of specified variable
	 * @param variable the variable to set
	 * @param value the value to set
	 */
	public void setVariableValue(String variable, Double value) {
		if(variables.containsKey(variable))
			variables.put(variable, value);
	}
	
	
	/**
	 * Returns value of variable
	 * @param index - the variable to get value of 
	 * @return value value of variable
	 */
	public double getVariableValue(int index) {
		if(index > -1 && index < VARIABLES.length) {
			return variables.get(VARIABLES[index]);
		}
		return 0;
	}
	
	/**
	 * Sets value of specified variable
	 * @param index the variable to set
	 * @param value the value to set
	 * @return
	 */
	public boolean setVariableValue(int index,  double value) {
		if(index > -1 && index < VARIABLES.length) {
			variables.put(VARIABLES[index], value);
			return true;
		}
		return false;
	}
	
	/**
	 * Sets all variable values to 0
	 */
	public void resetVariables() {
		for(int i=0; i<VARIABLES.length; i++) {
			this.variables.put(VARIABLES[i], 0.0);
		}
	}
	
	/**
	 * Returns radius of animation
	 * @return radius
	 */
	public double getRadius() {
		return this.variables.get("Radius");
	}
	
	/**
	 * Returns time of animation
	 * @return time
	 */
	public double getTime() {
		return this.variables.get("Time");
	}
	
	/**
	 * Returns angular velocity of animation
	 * @return angular velocity
	 */
	public double getAngularVelocity() {
		return this.variables.get("Angular Velocity");
	}
	
	/**
	 * Returns linear velocity of animation
	 * @return linear velocity
	 */
	public double getLinearVelocity() {
		return this.variables.get("Linear Velocity");
	}
	
	/**
	 * Returns arc length of animation
	 * @return arc length
	 */
	public double getArcLength() {
		return this.variables.get("Arc Length");
	}
	
	/**
	 * Returns angle of animation
	 * @return angle
	 */
	public double getAngle() {
		return this.variables.get("Angle");
	}
	
	/**
	 * Returns GUI of animation
	 * @return answer machine GUI
	 */
	public AnswerMachinePanel getView() {
		return answerMachinePanel;
	}
	
	/**
	 * Updates input panel.
	 * @param state whether or not to update field
	 */
	private void updateInputPanel(boolean state) {
		InputPanel input = answerMachinePanel.getInputPanel();
		input.update(state);
	}
	
	/**
	 * Updates wanted variable combo box.
	 */
	private void updateWantedFieldPanel() {
		answerMachinePanel.getWantedFieldPanel().update();
	}
	
	/**
	 * Updates the model and views.
	 * @return Whether it has enough information to update
	 */
	public boolean updateModel() {
		int numVar = 0;
		//Checks if there is sufficient amount of information
		for(int i=0; i<VARIABLES.length; i++) {
			if(variables.get(VARIABLES[i])!=0)
				numVar++;
		}
		
		//Updates the view
		if(numVar>1) {
			this.numberFields = numVar;
			this.updateInputPanel(true);
			this.updateWantedFieldPanel();
			return true;
		}
		return false;
	}
}
