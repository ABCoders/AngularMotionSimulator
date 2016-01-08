package animation;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * A Controller that draws a point on where the user clicks with a mouse.
 * It only runs when the animation is paused.
 * Sends information gained to animation model.
 * @author Bryan Kristiono
 * @since 7/12/2015
 */
public class DrawPointController implements MouseListener, MouseMotionListener{
	private Animation animation;		//The model used for calculation animation
	private ArrayList<DrawPoint> drawPoints;
	
	/**
	 * Initializes the class.
	 * @param animation The model of the controller
	 */
	public DrawPointController (Animation animation, ArrayList<DrawPoint> drawPoints) {
		this.animation = animation;
		this.drawPoints = drawPoints;
	}
	
	/**
	 * A Stub.
	 * @param e The event sent from the mouse
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	/**
	 * Gets the coordinates of the mouse and sends it to the model.
	 * @param e The event sent from the mouse when clicked on the animation component
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (!animation.getState()) {
			boolean onPoint = false;
			Point location = new Point(e.getX(), e.getY());
			
			for(DrawPoint points: drawPoints) {
				Point pointLocation = points.getLocation();
				if(Math.abs(pointLocation.getX()-location.getX()) < 10 &&
						Math.abs(pointLocation.getY()-location.getY()) < 10)
					onPoint = true;
			}
			if(!onPoint)
				animation.addDrawPoint(location);
		}
	}

	/**
	 * A Stub.
	 * @param e The event sent from the mouse
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * A Stub.
	 * @param e The event sent from the mouse
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * A Stub.
	 * @param e The event sent from the mouse
	 */
	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	//***************************************************************************
	//					   	MouseMotionListener Methods
	//***************************************************************************

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	/**
	 * A Stub.
	 * @param e The event sent from the mouse
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
	}

}
