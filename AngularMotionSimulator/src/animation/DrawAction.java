package animation;

import java.awt.Point;

/**
 * A class to store all drawn related actions on the animation.
 * @author Bryan Kristiono
 * @since 19/01/2016
 */
/**
 * @author Bryan
 *
 */
/**
 * @author Bryan
 *
 */
public class DrawAction {
	/*       Constants      */
	public static final int MOVE 	= 0;	//When a point is moved
	public static final int CREATE	= 1;	//When a point is created
	public static final int DELETE 	= 2;	//When a point is deleted
	
	/* Attributes of the class */
	private int index;			//The location of the drawn point in a list
	private int action;			//The type of action
	private DrawPoint point;	//The point that is being affected
	private Point location;		//The location of the point when acted upon
	
	/**
	 * Initializes a new action with required information.
	 * @param action The type of action created
	 * @param point  The drawn point that was affected
	 * @param index	 The location of the drawn point in a list
	 */
	public DrawAction(int action, DrawPoint point, int index) {
		this.action = action;
		this.point = point;
		this.index = index;
	}
	
	/**
	 * Returns the type of action
	 * @return The type of action
	 */
	public int getAction() {
		return this.action;
	}
	
	/**
	 * Returns the affected drawn point
	 * @return The affected drawn point
	 */
	public DrawPoint getPoint() {
		return this.point;
	}
	
	/**
	 * Returns the location of the point when acted
	 * @return The location of the point when acted
	 */
	public Point getLocation() {
		return this.location;
	}
	
	/**
	 * Returns the location of the point on a list
	 * @return The location of the point on a list
	 */
	public int getIndex() {
		return this.index;
	}
	
	/**
	 * Sets the location of the action
	 * @param location The new location
	 */
	public void setLocation(Point location) {
		this.location = location;
	}
}
