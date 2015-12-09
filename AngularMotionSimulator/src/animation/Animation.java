package animation;

import java.awt.*;

import inputs.Input;

/**Animation
 * The model of the animation
 * Contains all variables needed to create and configure the animation
 * @author BRYAN KRISTIONO
 * @since 7/12/2015
 */
public class Animation implements Runnable {
	private AnimationComponent component;
	private Input input;
	
	private double diameter;
	private double radius;
	private double xCoord;
	private double linearVelocity;
	private double angularVelocity;
	private double angle;

	private Point drawPoint;
	private double pointAngle;
	private int distance;
	
	private double scale;
	private int time;
	private boolean state = false;
	
	public Animation(Input input) {
		this.input = input;
		this.radius = 50;
		this.xCoord = 0;
		this.linearVelocity = 1;
		this.angularVelocity = (double) linearVelocity / radius;
		this.angle = 0;
		this.pointAngle = 0;
		this.distance = 0;
	}
	
	public void setGUI(AnimationComponent animiationComponent) {
		this.component = animiationComponent;
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		while(true) {
			while(state) {
				this.xCoord += linearVelocity;
				this.angle -= angularVelocity;
				this.pointAngle -= angularVelocity;
				try {
					Thread.sleep(10);
				} catch (Exception e) {}
				if (this.xCoord > component.getWidth()) {
					this.xCoord = 0;
				}
				update();
			}
			try {
				Thread.sleep(0);
			} catch (Exception e) {}
			update();
		}
	}

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

	public int getDistance() {
		return distance;
	}

	public double getScale() {
		return scale;
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
	
	public void setDrawPoints(int drawX, int drawY) {
		drawPoint = new Point(drawX, drawY);
		this.calculateDistance();
	}

	public void setState(boolean newState) {
		this.state = newState;
	}
	
	public void clear() {
		distance = 0;
	}
	
	private void calculateDistance() {
		int dBetweenX = (int)(xCoord + radius - drawPoint.x);
		int dBetweenY = (int)(component.getHeight() - radius - drawPoint.y);
		distance = (int) Math.sqrt(Math.pow(dBetweenX, 2) + Math.pow(dBetweenY, 2));
		pointAngle = Math.atan((double) dBetweenY / (double) dBetweenX);
		if (drawPoint.x < xCoord + radius) {
			pointAngle = -pointAngle - 0.5 * Math.PI;
		} else {
			pointAngle = -pointAngle + 0.5 * Math.PI;
		}
	}
	
	private void update() {
		component.repaint();
	}
}
 