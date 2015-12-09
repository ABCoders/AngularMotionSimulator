package animation;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**TimeSliderController
 * A Controller that gets information from the time slider
 * Sends data to the animation model
 * Changes the position of the animation
 * @author BRYAN KRISTIONO
 * @since 7/12/2015
 */
public class TimeSliderController implements ChangeListener {
	
	private Animation animation;

	public TimeSliderController(Animation animation) {
		this.animation = animation;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
	}
}
