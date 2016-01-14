package animation;

import java.awt.*;
import java.util.ArrayList;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

/**
 * A Component for the animation.
 * Displays a circle moving and spinning across the screen.
 * Gets data from Animation class to create the animation in a certain way.
 * @author Bryan Kristiono
 * @author Cindy Zhao
 * @since 7/12/2015
 */
public class AnimationComponent extends JComponent {
	private Animation animation;	//The model with all the information it needs

	/*  Attributes for drawing the circle  */
	private int diameter;			//The diameter of the circle
	private double scale;			//The scale of the animation
	private int radius;				//The radius of the circle
	private int xCoord;				//The x-Coordinate of the circle
	private double angle;			//The angle of the line that indicates the movement of the circle
	private Color color; 			//The color of the circle

	private ArrayList<DrawPoint> drawPoints;

	/**
	 * Initialize a new AnimationComponent that connects to animation model.
	 * @param animation The model of animation with information needed
	 */
	public AnimationComponent(Animation animation) {
		super();
		this.animation = animation;
		this.setPreferredSize(new Dimension(1000,250));
	}

	/**
	 * Gets values of variables needed from the model.
	 */
	private void getVariables() {
		this.scale = animation.getScale();
		this.xCoord = (int)(animation.getXCoord()*scale);
		this.radius = (int)(animation.getRadius()*scale);
		this.diameter = radius*2;
		this.angle = animation.getAngle();
		this.drawPoints = animation.getDrawPoints();
		this.color = animation.getCircleColor();
	}

	/**
	 * Draws the component with the values given from the model.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D d = (Graphics2D) g;

		d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

		this.getVariables();

		//Determines the center of the circle
		int x = (xCoord + radius);
		int y = (this.getHeight() - radius);

		//Determines the end of the line connected to the center of the circle
		int endX = (int) (x + radius * Math.sin(angle));
		int endY = (int) (y + radius * Math.cos(angle));

		//Determines the location of the drawn point
		for (DrawPoint drawPoint : drawPoints) {
			drawPoint.setLocation(xCoord, radius, this.getHeight());
		}

		//Draws the circle
		d.setColor(color);
		d.fillOval(xCoord, this.getHeight() - diameter, diameter, diameter);
		d.setColor(Color.BLACK);
		d.drawLine(x, y, endX, endY);


		d.setColor(Color.BLACK);

		//Draws the circle and clicked point if the circle goes past the view
		if ((xCoord + diameter) > this.getWidth()) {
			d.setColor(color);
			d.fillOval(-this.getWidth() + xCoord, this.getHeight() - diameter, diameter, diameter);
			d.setColor(Color.BLACK);
			d.drawLine(-this.getWidth() + x, y, -this.getWidth() + endX, endY);

			for (DrawPoint drawPoint : drawPoints) {
				d.setColor(drawPoint.getColor());
				d.drawLine(-this.getWidth() + x, y, -this.getWidth() + drawPoint.getLocation().x, drawPoint.getLocation().y);
				d.fillOval(-this.getWidth() + drawPoint.getLocation().x - 10, drawPoint.getLocation().y - 10, 20, 20);

			}
			d.setColor(Color.BLACK);
		}

		else if (xCoord<0) {
			d.setColor(color);
			d.fillOval(this.getWidth() + xCoord, this.getHeight() - diameter, diameter, diameter);
			d.setColor(Color.BLACK);
			d.drawLine(this.getWidth() + x, y, this.getWidth() + endX, endY);
			
			for (DrawPoint drawPoint : drawPoints) {
				d.setColor(drawPoint.getColor());
				d.drawLine(this.getWidth() + x, y, this.getWidth() + drawPoint.getLocation().x, drawPoint.getLocation().y);
				d.fillOval(this.getWidth() + drawPoint.getLocation().x - 10, drawPoint.getLocation().y - 10, 20, 20);
			}
			d.setColor(Color.BLACK);
		}

		//Draws each clicked point
		for (DrawPoint drawPoint : drawPoints) {
			d.setColor(drawPoint.getColor());
			d.drawLine(x, y, drawPoint.getLocation().x, drawPoint.getLocation().y);
			d.fillOval(drawPoint.getLocation().x - 10, drawPoint.getLocation().y - 10, 20, 20);
			if (drawPoint.getWithCircle()) //if drawing circle is selected
			{
				Ellipse2D.Double circle = new Ellipse2D.Double((x-drawPoint.getDistance()), (y-drawPoint.getDistance()), drawPoint.getDistance()*2, drawPoint.getDistance()*2);
				d.draw(circle);
			}
			if (drawPoint.getLocation().getX() > this.getWidth()||x+drawPoint.getDistance() > this.getWidth()) //if point location is past right edge frame
			{
				d.drawLine(x - this.getWidth(), y, drawPoint.getLocation().x - this.getWidth(), drawPoint.getLocation().y);
				d.fillOval(drawPoint.getLocation().x - 10 - this.getWidth(), drawPoint.getLocation().y - 10, 20, 20);
				if (drawPoint.getWithCircle())
				{
					Ellipse2D.Double circle2 = new Ellipse2D.Double((x-drawPoint.getDistance()) - this.getWidth(), (y-drawPoint.getDistance()), drawPoint.getDistance()*2, drawPoint.getDistance()*2);
					d.draw(circle2);
				}
			}
			else if (drawPoint.getLocation().getX() < 0 || x-drawPoint.getDistance() < 0)
			{
				d.drawLine(x + this.getWidth(), y, drawPoint.getLocation().x + this.getWidth(), drawPoint.getLocation().y);
				d.fillOval(drawPoint.getLocation().x - 10 + this.getWidth(), drawPoint.getLocation().y - 10, 20, 20);
				if (drawPoint.getWithCircle())
				{
					Ellipse2D.Double circle2 = new Ellipse2D.Double((x-drawPoint.getDistance()) + this.getWidth(), (y-drawPoint.getDistance()), drawPoint.getDistance()*2, drawPoint.getDistance()*2);
					d.draw(circle2);
				}
			}
		}
		d.setColor(Color.BLACK);

		//Draws the base of the animation
		d.drawLine(0, this.getHeight() - 1, this.getWidth(), this.getHeight() - 1);
	}
}