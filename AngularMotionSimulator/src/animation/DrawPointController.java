package animation;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**DrawPointController
 * A Controller that draws a point on where the user clicks with a mouse
 * It only runs when the animation is paused
 * Sends information gained to animation model
 * @author BRYAN KRISTIONO
 * @since 7/12/2015
 */
public class DrawPointController implements MouseListener {
	private Animation animation;		//The model used for calculation animation
	
	/**Main Constructor
	 * Initializes the class
	 * @param animation The model of the controller
	 */
	public DrawPointController (Animation animation) {
		this.animation = animation;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!animation.getState()) {
			int drawX = (int) Math.floor(e.getX());
			int drawY = (int) Math.floor(e.getY());
			animation.setDrawPoints(drawX, drawY);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
