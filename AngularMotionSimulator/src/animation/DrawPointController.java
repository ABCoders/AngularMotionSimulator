package animation;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

/**
 * A Controller that draws a point on when the user left clicks with a mouse. A right click allows
 * a pop-up menu to appear. Holding a left click allows the user to move a point.
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
	private DropMenu menu;

	/**
	 * Initializes a newly created listener for the motion and actions of the mouse.
	 * The model that calculates the animation
	 * @param animation The model that calculates the animation
	 */
	public DrawPointController (Animation animation, ArrayList<DrawPoint> drawPoints) {
		this.animation = animation;
		this.drawPoints = drawPoints;
		this.menu = new DropMenu(animation);
		this.animation.getComponent().setComponentPopupMenu(menu);
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
			
			//Gets the location of where the mouse was selected
			Point location = new Point(e.getX(), e.getY());

			//Checks if selected location is in range of a point
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

			//If user left clicks
			if(SwingUtilities.isLeftMouseButton(e)) {
				//If the location is in range of an already created point
				if(onPoint) {
					DrawAction action = new DrawAction(DrawAction.MOVE, dragPoint, index);
					action.setLocation(location);
					animation.addDrawAction(action);
				}
				
				//If location is an empty space
				else {
					animation.addDrawPoint(location);
					dragPoint = drawPoints.get(drawPoints.size()-1);
				}
			}
			
			//If user right clicks
			else if (SwingUtilities.isRightMouseButton(e)) {
				//Sets the currently selected point if in range of a point
				if(onPoint) {
					this.animation.setCurrentPoint(dragPoint);
				} 
				else {
					this.animation.setCurrentPoint(null);
				}
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

	/**
	 * When the mouse is being dragged while holding left click.
	 * It moves the location of the selected point.
	 * @param e The event sent from the mouse
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		if (!animation.getState() && SwingUtilities.isLeftMouseButton(e)) {
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
