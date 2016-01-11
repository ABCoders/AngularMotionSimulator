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
	private boolean onPoint;
	private DrawPoint dragPoint;
	
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
			int index = 0;
			onPoint = false;
			Point location = new Point(e.getX(), e.getY());
			
			for(int i =0; i<drawPoints.size(); i++) {
				DrawPoint point = drawPoints.get(i);
				Point pointLocation = point.getLocation();
				if(Math.abs(pointLocation.getX()-location.getX()) < 10 &&
						Math.abs(pointLocation.getY()-location.getY()) < 10) {
					onPoint = true;
					dragPoint = point;
					index = i;
				}
			}
			if(!onPoint) {
				animation.addDrawPoint(location);
				dragPoint = drawPoints.get(drawPoints.size()-1);
			}
			else {
				DrawAction action = new DrawAction(DrawAction.MOVE, dragPoint, index);
				action.setLocation(location);
				animation.addDrawAction(action);
			}
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
		if (!animation.getState()) {
			dragPoint.setVariables(animation.getXCoord(), animation.getRadius(), animation.getHeight(), animation.getScale());
			dragPoint.setTime(animation.getTime());
			dragPoint.setLocation(e.getX(), e.getY());
		}
	}

	/**
	 * A Stub.
	 * @param e The event sent from the mouse
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
	}

}
