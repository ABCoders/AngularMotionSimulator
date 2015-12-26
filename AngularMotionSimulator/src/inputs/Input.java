package inputs;

import java.util.TreeMap;
import animation.Animation;
import calculation.Calculations;

public class Input extends Object{
	public static final String[] VARIABLES = new String[] {"Angular Velocity",
															"Linear Velocity",
															"Radius",
															"Arc Length",
															"Time",
															"Angle"};
	private TreeMap<String, Double> variables;
	
	private Animation animation;
	private Calculations calculations;
	private AnswerMachinePanel answerMachinePanel;
	
	public static final int MIN_FIELDS = 2;
	public static final int MAX_FIELDS = 5;
	
	private int numberPanels;
	private int numberFields;
	private int removedField;
	
	private String wantedVariable;
	
	public Input() {
		this.calculations = new Calculations(this);
		this.animation = new Animation(calculations);
		this.variables = new TreeMap<String, Double>();
		
		this.resetVariables();
		
		this.wantedVariable = VARIABLES[0];
		this.numberPanels = 1;
		this.numberFields = 2;
	}
	
	public void setGUI(AnswerMachinePanel answerMachinePanel) {
		this.answerMachinePanel = answerMachinePanel;
	}
	
	public void setNumberPanel(int number) {
		this.numberPanels = number;
		this.updateView();
	}
	
	public void addField() {
		if (numberFields<MAX_FIELDS) {
			numberFields++;
			this.updateInputPanel();
		}
	}
	
	public void removeField(int position) {
		if (numberFields>MIN_FIELDS) {
			numberFields--;
			this.removedField = position;
			this.updateInputPanel();
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

	public int getNumberPanels() {
		return this.numberPanels;
	}
	
	public int getNumberFields() {
		return this.numberFields;
	}
	
	public int getRemovedField() {
		return removedField;
	}
	
	public String getWantedVariable() {
		return this.wantedVariable;
	}
	
	public Animation getAnimation() {
		return this.animation;
	}
	
	public Calculations getCalculations() {
		return this.calculations;
	}
	
	public boolean setVariableValue(int index,  double value) {
		if(index > -1 && index < VARIABLES.length) {
			variables.put(VARIABLES[index], value);
			return true;
		}
		return false;
	}
	
	public double getVariableValue(int index) {
		if(index > -1 && index < VARIABLES.length) {
			return variables.get(VARIABLES[index]);
		}
		return 0;
	}
	
	public void resetVariables() {
		for(int i=0; i<VARIABLES.length; i++) {
			this.variables.put(VARIABLES[i], 0.0);
		}
	}
	
	public double getRadius() {
		return this.variables.get("Radius");
	}
	
	public double getTime() {
		return this.variables.get("Time");
	}
	
	public double getAngularVelocity() {
		return this.variables.get("Angular Velocity");
	}
	
	public double getLinearVelocity() {
		return this.variables.get("Linear Velocity");
	}
	
	public double getArcLength() {
		return this.variables.get("Arc Length");
	}
	
	public double getAngle() {
		return this.variables.get("Angle");
	}
	
	public AnswerMachinePanel getView() {
		return answerMachinePanel;
	}
	
	private void updateView() {
		answerMachinePanel.update();
	}
	
	private void updateInputPanel() {
		answerMachinePanel.getInputPanel().update();
	}
	
	private void updateWantedFieldPanel() {
		answerMachinePanel.getWantedFieldPanel().update();
	}
	
	public boolean updateModel() {
		int numVar = 0;
		for(int i=0; i<VARIABLES.length; i++) {
			if(variables.get(VARIABLES[i])!=0) {
				numVar++;
			}
		}
		if(numVar>1) {
			this.numberFields = numVar;
			this.updateInputPanel();
			this.updateWantedFieldPanel();
			return true;
		}
		return false;
	}
	
	public void updateCalculations() {
		calculations.update();
	}
}
