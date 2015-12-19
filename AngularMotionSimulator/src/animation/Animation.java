package animation;

import java.awt.*;

import calculation.Calculations;

/**
 * Animation
 * The model of the animation
 * Contains all variables needed to create and configure the animation
 * @author BRYAN KRISTIONO
 * @since 7/12/2015
 */
public class Animation implements Runnable {
	//Have Time slider change depending on scale
	//Have animation end after the screen instead of before the end of the screen
	
	
	/*  Class Object created for program  */
	private AnimationPanel animationPanel;	//The view of all Animation
	private ActionPanel actionPanel;		//The view of the animation actions
	private AnimationComponent component;	//The component drawing the animation
	private Calculations calculations;		//The model that has the needed values for variables
	
	/*  Attributes for drawing the circle  */
	private double radius;				//The radius of the circle
	private double xCoord;				//The x-Coordinate of the circle
	private double linearVelocity;		//The linear velocity of the circle
	private double angularVelocity;		//Angular velocity of the circle
	private double angle;				//The angle of the line that indicates the movement of the circle

	/*  Attributes for drawing a point  */
	private Point drawPoint;		//The location of the point that the user clicks
	private double drawAngle;		//The original angle of the draw point
	private double difference;
	private double pointAngle;		//The angle of the drawn point from the circle
	private double distance;			//The distance between the drawn point and the center of the circle
	
	/*  Attributes affected through buttons and sliders  */
	private double scale;			//The scale of the animation
//	private double drawTime;		//The time of the drawpoint
	private double time;			//The time the animation starts on
	private boolean state = false;	//The state of the animation
	
	private Color color;			//The color of the circle
	
	/**
	 * Main Constructor
	 * Initialize variables with default values
	 * @param input - The model animation will get its values from
	 */
	public Animation(Calculations calculations) {
		this.calculations = calculations;
		this.radius = 100;
		this.xCoord = 0;
		this.linearVelocity = 1.0;
		this.angularVelocity = (double) linearVelocity / (radius/100);
		this.angle = 0;
		this.pointAngle = 0;
		this.distance = 0;
		this.time = 0;
		this.scale = 1;
		this.color = Color.YELLOW;
	}
	
	/**
	 * Sets the view that Animation will update after calculating
	 * Starts a new Thread that redraws the animation
	 * @param animationPanel - The view that is updating
	 */
	public void setGUI(AnimationPanel animationPanel) {
		this.animationPanel = animationPanel;
		new Thread(this).start();
	}
	
	public void setGUI(AnimationComponent component) {
		this.component = component;
	}
	
	public void setGUI(ActionPanel actionPanel) {
		this.actionPanel = actionPanel;
	}
	
	/**
	 * A Runnable method that calculates new values for attributes of the circle
	 * Redraws the circle to create an animation
	 */
	@Override
	public void run() {
		while(true) {
			while(state) {
				this.xCoord = linearVelocity*time*100;
				this.angle = angularVelocity*-time;
				this.pointAngle = drawAngle + -angularVelocity*(time-difference);
				this.time +=0.001;  
//				this.drawTime +=0.001;
				try {
					Thread.sleep(1);
				} catch (Exception e) {}
				if (this.xCoord*scale > component.getWidth()) {
					this.xCoord = 0;
					this.time = 0;
				}
				updateComponent();
				updateActions();
			}
			this.xCoord = linearVelocity*time*100;
			this.angle = angularVelocity*-time;
			this.pointAngle = drawAngle + -angularVelocity*(time-difference);
			try {
				Thread.sleep(0);
			} catch (Exception e) {}
			if (this.xCoord*scale > component.getWidth()) {
				this.xCoord = 0;
				this.time = 0;
			}
			updateComponent();
		}
	}

	/*
	 * Getter Queries
	 */
	
	
	public double getRadius() {
		return radius;
	}

	public double getXCoord() {
		return xCoord;
	}

	public double getAngle() {
		return angle;
	}

	public double getPointAngle() {
		return pointAngle;
	}

	public double getDistance() {
		return distance;
	}

	public double getScale() {
		return scale;
	}
	
	public double getTime() {
		return time;
	}

	public boolean getState() {
		return state;
	}
	
	public double getAngularVelocity() {
		return angularVelocity;
	}
	
	public double getLinearVelocity() {
		return linearVelocity;
	}
	
	public Color getColor() {
		return color;
	}
	
	public double getWidth() {
//		System.out.println("Getting width");
		return this.component.getWidth();
	}
	
	/*
	 * Setter Methods
	 */
	
	/**
	 * Sets the location of the drawn point and calculates the distance between it and the circle
	 * @param drawX - The x-Coordinate of the drawn point
	 * @param drawY - The y-Coordinate of the drawn point
	 */
	public void setDrawPoints(int drawX, int drawY) {
		drawPoint = new Point(drawX, drawY);
		this.calculateDistance();
	}
	
	/**
	 * Sets the scale of the animation
	 * @param scale - The new scale for the animation
	 */
	public void setScale(double scale) {
		this.scale = scale;
	}
	
	/**
	 * Sets the time of the animation
	 * @param time - The time animation is on
	 */
	public void setTime(double time) {
		this.time = time;
	}
	
	/**
	 * Sets the state of the animation
	 * @param newState - The new state of the animation
	 */
	public void setState(boolean newState) {
		this.state = newState;
	}
	
	/**
	 * Sets the color of the circle
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	
	/*
	 * Methods
	 */
	
	/**
	 * Resets the drawn point location
	 */
	public void clear() {
		distance = 0;
	}
	
	/**
	 * Calculates the distance and angle between the drawn point and the circle
	 * Uses Pythagorean theorem to find distance
	 * Uses Trigonometry to find angle
	 */
	private void calculateDistance() {
		double dBetweenX = ((xCoord + radius)*scale - drawPoint.x);
		double dBetweenY = ((component.getHeight() - radius*scale) - drawPoint.y);
		distance = Math.sqrt(Math.pow(dBetweenX, 2) + Math.pow(dBetweenY, 2));
		drawAngle = Math.atan((double) dBetweenY / (double) dBetweenX);
//		drawTime = 0;
		difference = time;
		
//		System.out.println("Before: " + Math.toDegrees(drawAngle));
		//Fixes 90 degree bug
		if(Math.abs(Math.toDegrees(drawAngle))==90) {
			drawAngle = -drawAngle;
		}
				
		//Due to the difference between how Java calculates angles and how Java draws angle
		//This determines which side of the circle it should be drawn on
		if (drawPoint.x < (xCoord + radius)*scale) {
			drawAngle = -drawAngle - 0.5 * Math.PI;
		} else {
			drawAngle = -drawAngle + 0.5 * Math.PI;
		}
//		System.out.println("After: " + Math.toDegrees(drawAngle));
		
	}
	
	private void getVariables() {
		//Gets variable values from calculations
		if(calculations.getAngularVelocity()!=0)
			this.angularVelocity = calculations.getAngularVelocity();
		if(calculations.getLinearVelocity()!=0)
			this.linearVelocity = calculations.getLinearVelocity();
		if(calculations.getRadius()!=0)
			this.radius = calculations.getRadius()*100;
	}
	
	public void updateModel() {
		this.getVariables();
		
		//Resets animation
		this.time = 0;
		this.state = false;
		this.xCoord = 0;
		this.updateView();
	}
	
	/**
	 * Updates the View
	 */
	private void updateActions() {
		actionPanel.update();
	}
	
	private void updateComponent() {
		component.repaint();
	}
	
	private void updateView() {
		animationPanel.update();
	}
	
}
 