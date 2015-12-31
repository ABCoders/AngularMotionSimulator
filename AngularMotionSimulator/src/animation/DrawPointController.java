package animation;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A Controller that draws a point on where the user clicks with a mouse.
 * It only runs when the animation is paused.
 * Sends information gained to animation model.
 * @author Bryan Kristiono
 * @since 7/12/2015
 */
public class DrawPointController implements MouseListener {
	private Animation animation;		//The model used for calculation animation
	
	/**
	 * Initializes the class.
	 * @param animation The model of the controller
	 */
	public DrawPointController (Animation animation) {
		this.animation = animation;
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
			int drawX = (int) Math.floor(e.getX());
			int drawY = (int) Math.floor(e.getY());
			animation.setDrawPoints(drawX, drawY);
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

}
