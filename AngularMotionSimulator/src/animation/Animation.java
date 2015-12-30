package animation;

import java.awt.*;

import calculation.Calculations;
import inputs.Input;

/**
 * The model of the animation. It contains all variables needed to create and configure the animation.
 * It calculates the values for animating angular motion form varying speed, scale and user-inputted values.
 * @author BRYAN KRISTIONO
 * @since 27/12/2015
 */
public class Animation implements Runnable {

	/* Class Object created for program */
	private AnimationPanel animationPanel;	//The view of all Animation
	private ActionPanel actionPanel;		//The view of the animation actions
	private AnimationComponent component;	//The component drawing the animation
	private Calculations calculations;		//The model that has the needed values for variables

	/* Attributes for drawing the circle */
	private double radius; 			//The radius of the circle
	private double xCoord; 			//The x-Coordinate of the circle
	private double linearVelocity; 	//The linear velocity of the circle
	private double angularVelocity; //Angular velocity of the circle
	private double angle; 			//The angle of the line that indicates the movement of the circle

	/* Attributes for drawing a point */
	private Point drawPoint; 	//The location of the point that the user clicks
	private double drawAngle; 	//The original angle of the draw point
	private double difference; 	//The time the user draws the location of the point
	private double pointAngle; 	//The angle of the drawn point from the circle
	private double distance; 	//The distance between the drawn point and the center of the circle

	/* Attributes affected through buttons and sliders */
	private double scale; 			//The scale of the animation
	private double timeAngle; 		//The angle of the black line after animation ends
	private double pointTimeAngle; 	//The angle of the red line after animation ends
	private double time;			//The time the animation starts on
	private boolean state = false; 	//The state of the animation

	private Color color; 			//The color of the circle

	/**
	 * Initialize variables with default values.
	 * @param calculations The model animation will get its values from
	 */
	public Animation(Calculations calculations) {
		this.calculations = calculations;
		this.radius = 100;
		this.linearVelocity = 1.0;
		this.angularVelocity = (double) linearVelocity / (radius / 100);
		this.pointAngle = 0;
		this.distance = 0;
		this.timeAngle = 0;
		this.pointTimeAngle = 0;
		this.scale = 1;
		this.color = Color.YELLOW;
		this.resetVariables();
	}

	/**
	 * Sets the views that Animation will update after calculating, then starts
	 * a new thread that redraws the animation.
	 * @param animationPanel The main view for animation
	 */
	public void setGUI(AnimationPanel animationPanel) {
		this.animationPanel = animationPanel;
		this.component = animationPanel.getAnimationComponent();
		this.actionPanel = animationPanel.getActionPanel();
		new Thread(this).start();
	}

	/**
	 * A Runnable method that calculates new values for attributes of the circle.
	 * It updates needed views to display the animation and other attributes.
	 */
	@Override
	public void run() {
		while (true) {
			//Runs while animation is running
			while (state) {
				//Sets value based on time
				this.xCoord = linearVelocity * time * 100;
				this.angle = timeAngle + -angularVelocity * time;
				this.pointAngle = pointTimeAngle + drawAngle + -angularVelocity * (time - difference);
				//Increase time
				this.time += 0.001;
				try {
					Thread.sleep(1);
				} catch (Exception e) {}
				
				//If circle location goes past animation frame
				if (this.xCoord * scale > component.getWidth()) {
					this.time = 0;
					this.timeAngle = this.angle;
					this.drawAngle = this.pointAngle;
					this.difference = 0;
				}
				
				//Updates views
				updateComponent();
				updateActions();
			}
			
			//When animation is not running
			this.xCoord = linearVelocity * time * 100;
			this.angle = timeAngle + -angularVelocity * time;
			this.pointAngle = pointTimeAngle + drawAngle + -angularVelocity * (time - difference);
			try {
				Thread.sleep(0);
			} catch (Exception e) {}
			
			//If circle location goes past animation frame
			if (this.xCoord * scale > component.getWidth()) {
				this.time = 0;
				this.timeAngle = this.angle;
				this.drawAngle = this.pointAngle;
				this.difference = 0;
			}
			
			//Updates Views
			updateComponent();
			updateActions();
		}
	}

	
	//***************************************************************************
	//								Getter Queries
	//***************************************************************************

	
	/**
	 * Returns the Radius of the circle used in animation.
	 * @return The radius of the circle
	 */
	public double getRadius() {
		return radius;
	}

	
	/**
	 * Returns the left most x-coordinate of the circle.
	 * @return The x-coordinate of the circle,
	 */
	public double getXCoord() {
		return xCoord;
	}

	/**
	 * Returns the angle of the line in the circle.
	 * @return The angle of the line in the circle
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * Returns the angle of the line connecting from the middle of the circle to the point that the user clicks on.
	 * @return The angle of the line
	 */
	public double getPointAngle() {
		return pointAngle;
	}

	/**
	 * Returns the length of the line between the middle of the circle and the point the user clicks on.
	 * @return The length of the line
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * Returns the scale of the animation.
	 * @return The scale of the animation
	 */
	public double getScale() {
		return scale;
	}

	/**
	 * Returns the current time the animation is on.
	 * @return The current time of the animation
	 */
	public double getTime() {
		return time;
	}

	/**
	 * Returns whether the animation is running or not.
	 * @return The state of the animation
	 */
	public boolean getState() {
		return state;
	}

	/**
	 * Returns the speed of the circle spinning.
	 * @return The spinning speed
	 */
	public double getAngularVelocity() {
		return angularVelocity;
	}

	/**
	 * Returns the speed of the circle moving across.
	 * @return The linear speed of the circle
	 */
	public double getLinearVelocity() {
		return linearVelocity;
	}

	/**
	 * Returns the color of the circle.
	 * @return The color of the circle
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Returns the width of the animation view.
	 * @return The width of the animation view
	 */
	public double getWidth() {
		return this.component.getWidth();
	}

	//*************************************************************************
	//								Setter Methods
	//*************************************************************************

	/**
	 * Sets the location of the drawn point and calculates the distance between
	 * it and the circle.
	 * 
	 * @param drawX
	 *            The x-Coordinate of the drawn point
	 * @param drawY
	 *            The y-Coordinate of the drawn point
	 */
	public void setDrawPoints(int drawX, int drawY) {
		drawPoint = new Point(drawX, drawY);
		this.calculateDistance();
	}

	/**
	 * Sets the scale of the animation.
	 * 
	 * @param scale
	 *            The new scale for the animation
	 */
	public void setScale(double scale) {
		this.scale = scale;
	}

	/**
	 * Sets the time of the animation.
	 * 
	 * @param time
	 *            The time animation is on
	 */
	public void setTime(double time) {
		this.time = time;
	}

	/**
	 * Sets the state of the animation.
	 * 
	 * @param newState
	 *            The new state of the animation
	 */
	public void setState(boolean newState) {
		this.state = newState;
	}

	/**
	 * Sets the color of the circle.
	 * 
	 * @param color The new color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	//**********************************************************************
	//								Methods
	//**********************************************************************

	/**
	 * Resets the drawn point location.
	 */
	public void clear() {
		distance = 0;
	}

	/**
	 * Calculates the distance and angle between the drawn point and the circle.
	 * Uses Pythagorean theorem to find distance Uses Trigonometry to find angle.
	 */
	private void calculateDistance() {
		double dBetweenX = ((xCoord + radius) * scale - drawPoint.x);
		double dBetweenY = ((component.getHeight() - radius * scale) - drawPoint.y);
		distance = Math.sqrt(Math.pow(dBetweenX, 2) + Math.pow(dBetweenY, 2));
		drawAngle = Math.atan((double) dBetweenY / (double) dBetweenX);
		this.pointTimeAngle = 0;
		this.difference = time;

		// System.out.println("Before: " + Math.toDegrees(drawAngle));
		// Fixes 90 degree bug
		if (Math.abs(Math.toDegrees(drawAngle)) == 90) {
			drawAngle = -drawAngle;
		}

		// Due to the difference between how Java calculates angles and how Java
		// draws angle
		// This determines which side of the circle it should be drawn on
		if (drawPoint.x < (xCoord + radius) * scale) {
			drawAngle = -drawAngle - 0.5 * Math.PI;
		} else {
			drawAngle = -drawAngle + 0.5 * Math.PI;
		}
	}

	/**
	 * Gets the variable values needed for animation from Calculations.
	 */
	private void getVariables() {
		// Gets variable values from calculations
		if (calculations.getVariables().get(Input.VARIABLES[0]) != 0)
			this.angularVelocity = calculations.getVariables().get(Input.VARIABLES[0]);
		if (calculations.getVariables().get(Input.VARIABLES[1]) != 0)
			this.linearVelocity = calculations.getVariables().get(Input.VARIABLES[1]);
		if (calculations.getVariables().get(Input.VARIABLES[2]) != 0)
			this.radius = calculations.getVariables().get(Input.VARIABLES[2]) * 100;
	}

	/**
	 * Resets the variable values.
	 */
	private void resetVariables() {
		this.time = 0;
		this.angle = 0;
		this.state = false;
		this.xCoord = 0;
	}

	/**
	 * Updates the Animation Model.
	 */
	public void updateModel() {
		this.getVariables();

		// Resets animation
		this.resetVariables();
		this.updateView();
	}

	/**
	 * Updates the Action Panel.
	 */
	private void updateActions() {
		actionPanel.update();
	}

	/**
	 * Updates the Animation Component.
	 */
	private void updateComponent() {
		component.repaint();
	}

	/**
	 * Updates the Animation Panel.
	 */
	private void updateView() {
		animationPanel.update();
	}
}