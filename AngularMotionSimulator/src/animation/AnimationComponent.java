package animation;

import java.awt.*;
import javax.swing.*;

/**AnimationComponent
 * A Component for the animation
 * Displays a circle moving and spinning across the screen
 * Gets data from Animation class to create the animation in a certain way
 * @author BRYAN KRISTIONO
 * @since 7/12/2015
 */
public class AnimationComponent extends JComponent {
	private Animation animation;
	private int xCoord;
	private int radius;
	private int diameter;
	private double angle;
	private int distance;
	private double pointAngle;
	
	public AnimationComponent(Animation animation) {
		this.animation = animation;
		animation.setGUI(this);
	}
	
	private void getVariables() {
		this.xCoord = (int)animation.getXCoord();
		this.radius = (int)animation.getRadius();
		this.diameter = radius*2;
		this.angle = animation.getAngle();
		this.distance = animation.getDistance();
		this.pointAngle = animation.getPointAngle();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D d = (Graphics2D) g;
		
		this.getVariables();
		
		int x = xCoord + radius;
		int y = this.getHeight() - radius;
		int endX = (int) (x + radius * Math.sin(angle));
		int endY = (int) (y + radius * Math.cos(angle));

		int circleX = (int) (x + distance * Math.sin(pointAngle));
		int circleY = (int) (y + distance * Math.cos(pointAngle));
		
		Color color = Color.YELLOW;
		
		// Circle
		d.setColor(color);
		d.drawOval(xCoord, this.getHeight() - diameter, diameter, diameter);
		d.setColor(Color.BLACK);
		d.drawLine(x, y, endX, endY);

		d.setColor(Color.RED);
		d.drawLine(x, y, circleX, circleY);
		d.fillOval(circleX - 10, circleY - 10, 20, 20);
		d.setColor(Color.BLACK);

		d.drawLine(0, this.getHeight() - 1, this.getWidth(), this.getHeight() - 1);
		if (this.xCoord + diameter > this.getWidth()) {
			d.setColor(color);
			d.drawOval(-this.getWidth() + xCoord, this.getHeight() - diameter, diameter, diameter);
			d.setColor(Color.BLACK);
			d.drawLine(-this.getWidth() + x, y, -this.getWidth() + endX, endY);
			d.setColor(Color.RED);
			d.drawLine(-this.getWidth() + x, y, -this.getWidth() + circleX, circleY);
			d.fillOval(-this.getWidth() + circleX - 10, circleY - 10, 20, 20);
			d.setColor(color);
		}
	}
}
