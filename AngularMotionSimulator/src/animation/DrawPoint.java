/**
 * 
 */
package animation;

import java.awt.Point;

/**
 * 
 * @author Bryan Kristiono
 * @since 9/1/2016
 */
public class DrawPoint {
	/* Attributes of DrawPoint */
	private Point location;		//The location of the point
	private double startAngle;	//The original angle of the point
	private double angle;		//The current angle of the point
	private double endAngle;	//The ending angle of the point
	private double distance;	//The distance between the point and the center of the circle
	private double time;		//The time the point is created
	private boolean withCircle;	//Whether or not it is drawn with a red circle
	
	/* Attributes of animation for calculations */
	private double xCoord;		//The x-coordinate of the circle
	private double radius;		//The radius of the circle
	private double scale;		//The scale of the animation
	private int height;			//The height of the animation
	
	
	public DrawPoint(Point location, double xCoord, double radius, double scale, int height, double time, boolean withCircle) {
		this.location = location;
		this.withCircle = withCircle;
		this.setVariables(xCoord, radius, height, scale);
		this.time = time;
		this.calculateDistance();
	}
	
	//*************************************************************************
	//								Setter Methods
	//*************************************************************************
	
	public void setLocation(double xCoord, double radius, int height) {
		this.location.x = (int) (xCoord + radius + distance * Math.sin(angle));
		this.location.y = (int) (height - radius + distance * Math.cos(angle));
	}
	
	public void setLocation(int xCoord, int yCoord) {
		this.location.x = xCoord;
		this.location.y = yCoord;
		this.calculateDistance();
	}
	
	public void setLocation(Point location) {
		this.location = location;
		this.calculateDistance();
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public void setStartAngle(double angle) {
		this.startAngle = angle;
	}
	
	public void setTime(double time) {
		this.time = time;
	}
	
	public void setWithCircle(boolean state) {
		this.withCircle = state;
	}
	
	public void setVariables(double xCoord, double radius, int height, double scale) {
		this.xCoord = xCoord;
		this.radius = radius;
		this.height = height;
		this.scale = scale;
	}
	
	//*************************************************************************
	//								Getter Methods
	//*************************************************************************
	
	public Point getLocation() {
		return location;
	}
	
	public double getStartAngle() {
		return startAngle;
	}
	
	public double getAngle() {
		return angle;
	}
	
	public double getEndAngle() {
		return endAngle;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public double getTime() {
		return time;
	}
	
	public boolean getWithCircle() {
		return withCircle;
	}
	
	//*************************************************************************
	//									Methods
	//*************************************************************************
	
	/**
	 * Calculates the distance and angle between the drawn point and the circle.
	 * Uses Pythagorean theorem to find distance Uses Trigonometry to find angle.
	 */
	private void calculateDistance() {
		double dBetweenX = ((xCoord + radius) * scale - location.x);
		double dBetweenY = ((height - radius * scale) - location.y);
		distance = Math.sqrt(Math.pow(dBetweenX, 2) + Math.pow(dBetweenY, 2));
		startAngle = Math.atan((double) dBetweenY / (double) dBetweenX);
		this.endAngle = 0;

		// System.out.println("Before: " + Math.toDegrees(drawAngle));
		// Fixes 90 degree bug
		if (Math.abs(Math.toDegrees(startAngle)) == 90) {
			startAngle = -startAngle;
		}

		// Due to the difference between how Java calculates angles and how Java
		// draws angle
		// This determines which side of the circle it should be drawn on
		if (location.x < (xCoord + radius) * scale) {
			startAngle = -startAngle - 0.5 * Math.PI;
		} else {
			startAngle = -startAngle + 0.5 * Math.PI;
		}
	}
}
