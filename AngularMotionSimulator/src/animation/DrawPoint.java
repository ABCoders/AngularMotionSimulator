/**
 * 
 */
package animation;

import java.awt.Point;

/**
 * @author Bryan
 *
 */
public class DrawPoint {
	private Point location;
	private double startAngle;
	private double angle;
	private double endAngle;
	private double distance;
	private double time;
	private boolean withCircle;
	
	private double xCoord;
	private double radius;
	private double scale;
	private int height;
	
	
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
