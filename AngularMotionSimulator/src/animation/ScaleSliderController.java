package animation;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A Controller that gets information from the scale slider.
 * Sends data to the animation model.
 * Changes the scale of the animation.
 * @author Bryan Kristiono
 * @since 7/12/2015
 */
public class ScaleSliderController implements ChangeListener {
	private Animation animation;		//The model used for calculation animation

	/**
	 * Initializes the controller, connecting to the animation model.
	 * @param animation The model calculating the animation
	 */
	public ScaleSliderController(Animation animation) {
		this.animation = animation;
	}
	
	/**
	 * When the slider is changed while the animation is not running,
	 * it changes the value of the scale in the animation.
	 * @param e The event sent from the scale slider in the action panel
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if (!animation.getState()) {
			if(source.getValue()==0)
				source.setValue(1);
			animation.setScale(source.getValue()/100.0);
		}
	}
}
