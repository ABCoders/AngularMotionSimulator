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
	
	
	public DrawPoint(Point location, double xCoord, double radius, double scale, int height, double time) {
		this.location = location;
		this.calculateDistance(xCoord, radius, scale, height, time);
//		System.out.println("location: " + location);
//		System.out.println("startAngle: " + startAngle);
//		System.out.println("angle: " + angle);
//		System.out.println("endAngle: " + endAngle);
//		System.out.println("distance: " + distance);
//		System.out.println("time: " + time);
	}
	
	public void setLocation(double xCoord, double radius, double height) {
		this.location.x = (int) (xCoord + radius + distance * Math.sin(angle));
		this.location.y = (int) (height - radius + distance * Math.cos(angle));
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
	
	/**
	 * Calculates the distance and angle between the drawn point and the circle.
	 * Uses Pythagorean theorem to find distance Uses Trigonometry to find angle.
	 */
	private void calculateDistance(double xCoord, double radius, double scale, int height, double time) {
		double dBetweenX = ((xCoord + radius) * scale - location.x);
		double dBetweenY = ((height - radius * scale) - location.y);
		distance = Math.sqrt(Math.pow(dBetweenX, 2) + Math.pow(dBetweenY, 2));
		startAngle = Math.atan((double) dBetweenY / (double) dBetweenX);
		this.endAngle = 0;
		this.time = time;

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
