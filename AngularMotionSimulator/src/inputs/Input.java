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
	private InputPanel inputPanel;
	
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
		
		this.numberFields = 2;
	}
	
	public void setGUI(InputPanel inputPanel) {
		this.inputPanel = inputPanel;
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

	public int getNumberFields()
	{
		return this.numberFields;
	}
	
	public int getRemovedField() {
		return removedField;
	}
	
	public String getWantedVariable()
	{
		return this.wantedVariable;
	}
	
	public Animation getAnimation()
	{
		return this.animation;
	}
	
	public Calculations getCalculations()
	{
		return this.calculations;
	}
	
	public double getRadius()
	{
		return this.radius;
	}
	
	public double getTime()
	{
		return this.time;
	}
	
	public double getAngularVelocity()
	{
		return this.angularVelocity;
	}
	
	public double getLinearVelocity()
	{
		return this.linearVelocity;
	}
	
	public double getArcLength()
	{
		return this.arcLength;
	}
	
	public double getAngle()
	{
		return this.angle;
	}
	
	public void updateView() {
		inputPanel.update();
	}
	
	public void updateCalculations() {
		calculations.update();
	}
}
