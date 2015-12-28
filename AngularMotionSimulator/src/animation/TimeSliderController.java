package animation;

import javax.swing.JSlider;
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
	private Animation animation;		//The model used for calculation animation

	/**Main Constructor
	 * Initializes the class
	 * @param animation The model of the controller
	 */
	public TimeSliderController(Animation animation) {
		this.animation = animation;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if (!animation.getState()) {
			animation.setTime(source.getValue()/10.0);
		}
	}
}
