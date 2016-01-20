package animation;

import java.awt.*;
import java.util.ArrayList;

import calculation.Calculations;
import inputs.Input;

/**
 * The model of the animation. It contains all variables needed to create and configure the animation.
 * It calculates the values for animating angular motion form varying speed, scale and user-inputted values.
 * @author Bryan Kristiono
 * @author Amritpal Aujla
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
	private double time;			//The time the animation starts on
	private boolean state;		 	//The state of the animation

	private boolean reverse;		//Whether the animation is reverse
	private boolean drawCircle;		//Whether to draw a concurrent circle

	private Color circleColor; 			//The color of the circle

	private ArrayList<DrawPoint> drawPoints;	//A list of all points drawn by the user
	private ArrayList<DrawAction> drawActions;	//A list of all drawing actions
	
	private DrawPoint currentPoint;			//Selected draw point
	private Color pointColor;				//The color of the drawn points

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
		this.circleColor = Color.YELLOW;
		this.time = 0;
		this.angle = 0;
		this.state = false;
		this.xCoord = 0;

		this.reverse = false;
		this.drawCircle = true;

		this.drawPoints = new ArrayList<DrawPoint>();
		this.drawActions = new ArrayList<DrawAction>();
		
		this.currentPoint = null;
		this.pointColor = Color.RED;
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
	public Color getCircleColor() {
		return circleColor;
	}

	/**
	 * Returns the width of the animation view.
	 * @return The width of the animation view
	 */
	public int getWidth() {
		return this.component.getWidth();
	}
	
	/**
	 * Returns the height of the animation view.
	 * @return the height of the animation view
	 */
	public int getHeight() {
		return this.component.getHeight();
	}
	
	/**
	 * Returns the animation component.
	 * @return the animation component
	 */
	public AnimationComponent getComponent() {
		return this.component;
	}

	/**
	 * Returns the list of draw points.
	 * @return the list of draw points
	 */
	public ArrayList<DrawPoint> getDrawPoints() {
		return this.drawPoints;
	}
	
	/**
	 * Returns whether or not the animation is in reverse.
	 * @return whether or not the animation is in reverse
	 */
	public boolean getReverse() {
		return this.reverse;
	}
	
	/**
	 * Returns whether the dot is drawn with a concurrent circle.
	 * @return whether to draw a concurrent circle
	 */
	public boolean getDrawCircle() {
		return this.drawCircle;
	}
	
	/**
	 * Returns the color of the point.
	 * @return the color of the point
	 */
	public Color getPointColor() {
		return this.pointColor;
	}

	//*************************************************************************
	//								Setter Methods
	//*************************************************************************

	/**
	 * Adds a draw point to the list and adds an "add" action.
	 * @param point The point being added
	 */
	public void addDrawPoint(Point point) {
		drawPoints.add(new DrawPoint(point, this.xCoord, this.radius, this.scale,
								this.component.getHeight(), this.time, this.drawCircle, this.pointColor));
		drawActions.add(new DrawAction(DrawAction.CREATE, drawPoints.get(drawPoints.size()-1), drawPoints.size()-1));
	}
	
	/**
	 * Removes the selected draw point ands a "delete" action.
	 * @param point The point being deleted
	 */
	public void removeDrawPoint(DrawPoint point) {
		int index = 0;
		drawPoints.remove(point);
		for (int i=0; i<drawPoints.size(); i++) {
			if(drawPoints.get(i)==point)
				index = i;
		}
		drawActions.add(new DrawAction(DrawAction.DELETE, point, index));
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
	public void setCircleColor(Color color) {
		this.circleColor = color;
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
			else if(action.getAction()==DrawAction.DELETE) {
				this.drawPoints.add(action.getIndex(), action.getPoint());
			}
			this.drawActions.remove(drawActions.size()-1);
		}
	}
	
	public void setCurrentPoint(DrawPoint point) {
		this.currentPoint = point;
	}
	
	public void deletePoint() {
		if (currentPoint!=null)
			this.removeDrawPoint(currentPoint);
		currentPoint = null;
	}

	/**
	 * Sets the color of all of the points
	 * @param color The new color
	 */
	public void setAllPointsColor(Color color) {
		for(DrawPoint point : this.drawPoints){
			point.setColor(color);
		}
		this.pointColor = color;
	}
	
	/**
	 * Sets the color of a specific point
	 * @param color The new color
	 */
	public void setPointColor(Color color) {
		if(currentPoint != null)
			currentPoint.setColor(color);
	}

	//**********************************************************************
	//								Methods
	//**********************************************************************

	private void runAnimation() {
		if(reverse) {
			this.xCoord = this.component.getWidth() - (radius*scale*2) - linearVelocity * 100 * time;
			this.angle = timeAngle + angularVelocity * time;
			try {
				for(DrawPoint point: drawPoints)
					point.setAngle(point.getEndAngle()+point.getStartAngle()+ angularVelocity * (time-point.getTime()));
			} catch (Exception e) {}
			//If circle location goes past animation frame
			if ((xCoord+radius*2)*scale < 0)
				this.resetVariables();
		}
		//In reverse
		else {
			this.xCoord = linearVelocity * 100 * time;
			this.angle = timeAngle + -angularVelocity * time;
			try {
				for(DrawPoint point: drawPoints)
					point.setAngle(point.getEndAngle()+point.getStartAngle()+ -angularVelocity * (time-point.getTime()));
			} catch (Exception e) {}

			//If circle location goes past animation frame
			if (this.xCoord * scale > component.getWidth())
				this.resetVariables();
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