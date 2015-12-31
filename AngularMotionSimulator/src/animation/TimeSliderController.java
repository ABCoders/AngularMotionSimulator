package animation;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A Controller that gets information from the time slider.
 * Sends data to the animation model.
 * Changes the position of the animation.
 * @author Bryan Kristiono
 * @since 7/12/2015
 */
public class TimeSliderController implements ChangeListener {
	private Animation animation;		//The model used for calculation animation

	/**
	 * Initializes the controller, connecting to the animation model.
	 * @param animation The model calculating the animation
	 */
	public TimeSliderController(Animation animation) {
		this.animation = animation;
	}
	
	/**
	 * When the slider is changed while the animation is not running,
	 * it changes the value of the time and position in the animation.
	 * @param e The event sent from the time slider in the action panel
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if (!animation.getState()) {
			animation.setTime(source.getValue()/10.0);
		}
	}
}
