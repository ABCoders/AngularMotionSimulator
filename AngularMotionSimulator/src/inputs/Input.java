package inputs;

import animation.Animation;
import calculation.Calculations;
import options.Options;

public class Input extends Object{
	
	private Animation animation;
	private Options options;
	private Calculations calculations;
	private InputPanel inputPanel;
	private String wantedVariable;
	private double radius;
	private double time;
	private double angularVelocity;
	private double linearVelocity;
	private double arclength;
	private double angle;
	private int numberFields;
	
	public Input()
	{
		this.animation = new Animation(this);
		this.options = new Options(this);
		this.calculations = new Calculations(this);
	}
	
	public void setGUI(InputPanel input)
	{
		
	}
	
	public void updateView()
	{
	
	}
	
	public void addField()
	{
		
	}
	
	public void removeField(InputFieldPanel panel)
	{
		
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
}
