package inputs;

import animation.Animation;
import calculation.Calculations;

public class Input extends Object{
	public static final String[] VARIABLES = {"Angular Velocity",
			"Linear Velocity", 
			"Radius", 
			"Arc Length",
			"Time", 
			"Angle"};
	
	private Animation animation;
	private Calculations calculations;
//	private InputPanel inputPanel;
	private AnswerMachinePanel answerMachinePanel;
	
	public static final int MIN_FIELDS = 2;
	public static final int MAX_FIELDS = 5;
	
	private int numberFields;
	private int removedField;
	
	private String wantedVariable;
	private double radius;
	private double time;
	private double angularVelocity;
	private double linearVelocity;
	private double arcLength;
	private double angle;
	
	public Input() {
		this.calculations = new Calculations(this);
		this.animation = new Animation(calculations);
		
		radius = -1;
		time = -1;
		angularVelocity = -1;
		linearVelocity = -1;
		arcLength = -1;
		angle = -1;
		
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
		this.arcLength = arcLength;
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
		this.radius = radius;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(double time) {
		this.time = time;
	}

	/**
	 * @param angularVelocity the angularVelocity to set
	 */
	public void setAngularVelocity(double angularVelocity) {
		this.angularVelocity = angularVelocity;
	}

	/**
	 * @param linearVelocity the linearVelocity to set
	 */
	public void setLinearVelocity(double linearVelocity) {
		this.linearVelocity = linearVelocity;
	}

	/**
	 * @param angle the angle to set
	 */
	public void setAngle(double angle) {
		this.angle = angle;
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
			return this.angularVelocity;
		case 1:
			return this.linearVelocity;
		case 2:
			return this.radius;
		case 3:
			return this.arcLength;
		case 4:
			return this.time;
		case 5:
			return this.angle;
		}
		return 0;		
	}
	
	public double getRadius() {
		return this.radius;
	}
	
	public double getTime() {
		return this.time;
	}
	
	public double getAngularVelocity() {
		return this.angularVelocity;
	}
	
	public double getLinearVelocity() {
		return this.linearVelocity;
	}
	
	public double getArcLength() {
		return this.arcLength;
	}
	
	public double getAngle() {
		return this.angle;
	}
	
	public AnswerMachinePanel getView() {
		return answerMachinePanel;
	}
	
	public void updateView() {
		answerMachinePanel.update();
	}
	
	public boolean updateModel() {
		int numVar = 0;
		if(radius!=0)
			numVar++;
		if(time!=0)
			numVar++;
		if(angularVelocity!=0)
			numVar++;
		if(linearVelocity!=0)
			numVar++;
		if(arcLength!=0)
			numVar++;
		if(angle!=0)
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
