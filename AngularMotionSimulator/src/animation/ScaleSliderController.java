package animation;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A Controller that gets information from the scale slider.
 * Sends data to the animation model.
 * Changes the scale of the animation.
 * @author BRYAN KRISTIONO
 * @since 7/12/2015
 */
public class ScaleSliderController implements ChangeListener {
	private Animation animation;		//The model used for calculation animation

	/**
	 * Initializes the class.
	 * @param animation The model of the controller
	 */
	public ScaleSliderController(Animation animation) {
		this.animation = animation;
	}
	
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
