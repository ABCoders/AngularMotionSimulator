package inputs;

import java.util.TreeMap;
import animation.Animation;
import calculation.Calculations;

public class Input extends Object{
	public TreeMap<String, Double> variables;
	
	private Animation animation;
	private Calculations calculations;
//	private InputPanel inputPanel;
	private AnswerMachinePanel answerMachinePanel;
	
	public static final int MIN_FIELDS = 2;
	public static final int MAX_FIELDS = 5;
	
	private int numberFields;
	private int removedField;
	
	private String wantedVariable;
	
	public Input() {
		this.calculations = new Calculations(this);
		this.animation = new Animation(calculations);
		this.variables = new TreeMap<String, Double>();
		
		this.resetVariables();
		
		this.wantedVariable = "Angular Velocity";
		this.numberFields = 2;
	}
	
//	public void setGUI(InputPanel inputPanel) {
//		this.inputPanel = inputPanel;
//	}
	
	public void setGUI(AnswerMachinePanel answerMachinePanel) {
		this.answerMachinePanel = answerMachinePanel;
	}
	
	public void addField() {
		if (numberFields<MAX_FIELDS) {
			numberFields++;
			this.updateView();
		}
	}
	
	public void removeField(int position) {
		if (numberFields>MIN_FIELDS) {
			numberFields--;
			this.removedField = position;
			this.updateView();
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
	
	public double getVariableValue(int index) {
		switch(index) {
		case 0:
			return this.variables.get("Angular Velocity");
		case 1:
			return this.variables.get("Linear Velocity");
		case 2:
			return this.variables.get("Radius");
		case 3:
			return this.variables.get("Arc Length");
		case 4:
			return this.variables.get("Time");
		case 5:
			return this.variables.get("Angle");
		}
		return 0;		
	}
	
	public void resetVariables() {
		this.variables.put("Angular Velocity", 0.0);
		this.variables.put("Linear Velocity", 0.0);
		this.variables.put("Radius", 0.0);
		this.variables.put("Arc Length", 0.0);
		this.variables.put("Time", 0.0);
		this.variables.put("Angle", 0.0);
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
		return this.variables.get("Linear Velocity");
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
	
	public boolean updateModel() {
		int numVar = 0;
		if(this.variables.get("Radius")!=0)
			numVar++;
		if(this.variables.get("Time")!=0)
			numVar++;
		if(this.variables.get("Angular Velocity")!=0)
			numVar++;
		if(this.variables.get("Linear Velocity")!=0)
			numVar++;
		if(this.variables.get("Arc Length")!=0)
			numVar++;
		if(this.variables.get("Angle")!=0)
			numVar++;
		if(numVar>1) {
			this.numberFields = numVar;
			this.updateView();
			return true;
		}
		return false;
	}
	
	public void updateCalculations() {
		calculations.update();
	}
}
