package animation;

import java.awt.*;
import java.util.ArrayList;

import calculation.Calculations;
import inputs.Input;

/**
 * The model of the animation. It contains all variables needed to create and configure the animation.
 * It calculates the values for animating angular motion form varying speed, scale and user-inputted values.
 * @author Bryan Kristiono
 * @since 9/1/2016
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
	private double angularVelocity; //The angular velocity of the circle
	private double angle; 			//The angle of the line that indicates the movement of the circle


	/* Attributes affected through buttons and sliders */
	private double scale; 			//The scale of the animation
	private double timeAngle; 		//The angle of the black line after animation ends
	//	private double pointTimeAngle; 	//The angle of the red line after animation ends
	private double time;			//The time the animation starts on
	private boolean state = false; 	//The state of the animation

	private boolean reverse;
	private boolean drawCircle;

	private Color color; 			//The color of the circle

	private ArrayList<DrawPoint> drawPoints;	//A list of all points drawn by the user
	private ArrayList<DrawAction> drawActions;	//A list of all drawing actions

	/**
	 * Initialize a new Animation with default values.
	 * @param calculations The model animation will get its values from
	 */
	public Animation(Calculations calculations) {
		this.calculations = calculations;
		this.radius = 100;
		this.linearVelocity = 1.0;
		this.angularVelocity = (double) linearVelocity / (radius / 100);
		this.timeAngle = 0;
		this.scale = 1;
		this.color = Color.YELLOW;
		this.time = 0;
		this.angle = 0;
		this.state = false;
		this.xCoord = 0;

		this.reverse = true;
		this.drawCircle = true;

		this.drawPoints = new ArrayList<DrawPoint>();
		this.drawActions = new ArrayList<DrawAction>();
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
				this.runAnimation();
				
				//Increase time
				this.time += 0.001;
				try {
					Thread.sleep(1);
				} catch (Exception e) {}
				
				//Updates views
				updateComponent();
				updateActions();
			}

			//When animation is not running
			this.runAnimation();
			try {
				Thread.sleep(0);
			} catch (Exception e) {}

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
	 * @return The x-coordinate of the circle
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
	public int getWidth() {
		return this.component.getWidth();
	}
	
	public int getHeight() {
		return this.component.getHeight();
	}

	public ArrayList<DrawPoint> getDrawPoints() {
		return drawPoints;
	}
	
	public boolean getReverse() {
		return reverse;
	}
	
	public boolean getDrawCircle() {
		return drawCircle;
	}

	//*************************************************************************
	//								Setter Methods
	//*************************************************************************

	public void addDrawPoint(Point point) {
		drawPoints.add(new DrawPoint(point, xCoord, radius, scale, this.component.getHeight(), time, drawCircle));
		drawActions.add(new DrawAction(DrawAction.CREATE, drawPoints.get(drawPoints.size()-1), drawPoints.size()-1));
	}
	
	public void addDrawAction(DrawAction action) {
		drawActions.add(action);
	}

	/**
	 * Sets the scale of the animation.
	 * @param scale The new scale for the animation
	 */
	public void setScale(double scale) {
		this.scale = scale;
	}

	/**
	 * Sets the time of the animation.
	 * @param time The new time of the animation
	 */
	public void setTime(double time) {
		this.time = time;
	}

	/**
	 * Sets the state of the animation.
	 * @param newState The new state of the animation
	 */
	public void setState(boolean newState) {
		this.state = newState;
	}

	/**
	 * Sets the color of the circle.
	 * @param color The new color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	public void setReverse() {
		if(reverse)
			reverse = false;
		else
			reverse = true;
	}

	public void setDrawCircle() {
		if(drawCircle)
			drawCircle = false;
		else
			drawCircle = true;
	}

	public void setDrawCircle(boolean state) {
		drawCircle = state;
	}
	
	public void undo() {
		if(!drawActions.isEmpty()) {
			DrawAction action = this.drawActions.get(drawActions.size()-1);
			if(action.getAction()==DrawAction.CREATE) {
				this.drawPoints.remove(action.getPoint());
			}
			else if(action.getAction()==DrawAction.MOVE) {
				this.drawPoints.get(action.getIndex()).setLocation(action.getLocation());;
			}
			this.drawActions.remove(drawActions.size()-1);
		}
	}

	//**********************************************************************
	//								Methods
	//**********************************************************************

	private void runAnimation() {
		if(reverse) {
			this.xCoord = linearVelocity * 100 * time;
			this.angle = timeAngle + -angularVelocity * time;
			try {
				for(DrawPoint point: drawPoints) {
					point.setAngle(point.getEndAngle()+point.getStartAngle()+ -angularVelocity * (time-point.getTime()));
				}
			} catch (Exception e) {}

			//If circle location goes past animation frame
			if (this.xCoord * scale > component.getWidth()) {
				this.resetVariables();
			}
		}
		//In reverse
		else {
			this.xCoord = this.component.getWidth() - (radius*scale*2) - linearVelocity * 100 * time;
			this.angle = timeAngle + angularVelocity * time;
			try {
				for(DrawPoint point: drawPoints) {
					point.setAngle(point.getEndAngle()+point.getStartAngle()+ angularVelocity * (time-point.getTime()));
				}
			} catch (Exception e) {}
			//If circle location goes past animation frame
			if ((xCoord+radius*2)*scale < 0) {
				this.resetVariables();
			}
		}
	}
	
	private void resetVariables() {
		this.time = 0;
		this.timeAngle = this.angle;
		if (!drawPoints.isEmpty()) {
			for(DrawPoint point: drawPoints) {
				point.setStartAngle(point.getAngle());
				point.setTime(0);
			}
		}
	}
	
	/**
	 * Resets the drawn point location.
	 */
	public void clear() {
		this.drawActions.clear();
		this.drawPoints.clear();
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
	 * Updates the Animation Model.
	 */
	public void updateModel() {
		this.getVariables();
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