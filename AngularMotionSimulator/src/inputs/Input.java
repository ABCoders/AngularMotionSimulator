package inputs;

import java.util.TreeMap;
import animation.Animation;
import calculation.Calculations;

/**
 * Inputs information from user and sends to calculations model and updates input fields in answer machine
 * @author Cindy Zhao
 * @author Bryan Kristiono
 * @since 09/12/15
 */

public class Input extends Object{
	public static final String[] VARIABLES = new String[] {"Angular Velocity", //A constant memory location for all variable names
															"Linear Velocity",
															"Radius",
															"Arc Length",
															"Time",
															"Angle"};
	private TreeMap<String, Double> variables; //Variables names and associated values
	
	private Animation animation; //The Animation model
	private Calculations calculations; //The Calculations model the Input is managing
	private AnswerMachinePanel answerMachinePanel; //View for Input
	
	public static final int MIN_FIELDS = 2; //Minimum number of fields
	public static final int MAX_FIELDS = 5; //Maximum number of fields
	
	private int numberPanels; //
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
		
		this.resetVariables();
		
		this.wantedVariable = VARIABLES[0];
		this.numberPanels = 1;
		this.numberFields = 2;
	}
	
	/**
	 * Sets GUI for Input
	 * @param answerMachinePanel GUI to set
	 */
	public void setGUI(AnswerMachinePanel answerMachinePanel) //Sets GUI
	{
		this.answerMachinePanel = answerMachinePanel;
	}
	
	public void setNumberPanel(int number) {
		this.numberPanels = number;
		this.updateView();
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
	 * @param arcLength the arcLength to set
	 */
	public void setArcLength(double arcLength) {
		this.variables.put("Arc Length", arcLength);
	}

	/**
	 * @param wantedVariable the wantedVariable to set
	 */
	public void setWantedVariable(String wantedVariable) {
		this.wantedVariable = wantedVariable;
	}

	/**
	 * @param radius the radius to set
	 */
	public void setRadius(double radius) {
		this.variables.put("Radius", radius);	
		}

	/**
	 * @param time the time to set
	 */
	public void setTime(double time) {
		this.variables.put("Time", time);
	}

	/**
	 * @param angularVelocity the angularVelocity to set
	 */
	public void setAngularVelocity(double angularVelocity) {
		this.variables.put("Angular Velocity", angularVelocity);
	}

	/**
	 * @param linearVelocity the linearVelocity to set
	 */
	public void setLinearVelocity(double linearVelocity) {
		this.variables.put("Linear Velocity", linearVelocity);
	}

	/**
	 * @param angle the angle to set
	 */
	public void setAngle(double angle) {
		this.variables.put("Angle", angle);
	}

	/**
	 * Return number of panels
	 * @return number of panels
	 */
	public int getNumberPanels() {
		return this.numberPanels;
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
	 * Returns value of variable
	 * @param index - the variable to get value of 
	 * @return value
	 */
	public double getVariableValue(int index) {
		if(index > -1 && index < VARIABLES.length) {
			return variables.get(VARIABLES[index]);
		}
		return 0;
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
	 * Updates answer machine GUI
	 */
	private void updateView() {
		answerMachinePanel.update();
	}
	
	/**
	 * Updates input panel
	 * @param state whether or not to update field
	 */
	private void updateInputPanel(boolean state) {
		InputPanel input = answerMachinePanel.getInputPanel();
		input.update(state);
	}
	
	/**
	 * Updates wanted variable combobox from text file
	 */
	private void updateWantedFieldPanel() {
		answerMachinePanel.getWantedFieldPanel().update();
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean updateModel() {
		int numVar = 0;
		for(int i=0; i<VARIABLES.length; i++) {
			if(variables.get(VARIABLES[i])!=0) {
				numVar++;
			}
		}
		if(numVar>1) {
			this.numberFields = numVar;
			this.updateInputPanel(true);
			this.updateWantedFieldPanel();
			return true;
		}
		return false;
	}
	
	/**
	 * Updates calculations model
	 */
	public void updateCalculations() {
		calculations.update();
	}
}
