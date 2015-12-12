package inputs;

import animation.Animation;
import calculation.Calculations;
import options.Options;

public class Input extends Object{
	public static final String[] VARIABLES = {"Angular Velocity",
			"Linear Velocity", 
			"Radius", 
			"Arc Length",
			"Time", 
			"Angle"};
	
	private Animation animation;
	private Options options;
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
	private double arclength;
	private double angle;
	
	public Input() {
		this.options = new Options(this);
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
	
	public void setWantedVariable(String wantedVariable)
	{
		
	}
	
	public void setRadius(double radius)
	{
		
	}
	
	public void setTime(double time)
	{
		
	}
	
	public void setAngularVelocity(double angularVelocity)
	{
		
	}
	
	public void setLinearVelocity(double linearVelocity)
	{
		
	}
	
	public void setArclength(double arclength)
	{
		
	}
	
	public void setAngle(double angle)
	{
		
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
	
	public Options getOptions()
	{
		return this.options;
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
	
	public double getArclength()
	{
		return this.arclength;
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
