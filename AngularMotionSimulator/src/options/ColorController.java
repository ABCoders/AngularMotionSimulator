/**
 * 
 */
package options;

import javax.swing.JColorChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import animation.Animation;

/**
 * ColorController
 * A Controller that listens the color the user chooses in the color chooser.
 * Sends the data to the Animation model
 * @author BRYAN KRISTIONO
 * @since 27/12/2015
 */
public class ColorController implements ChangeListener {
	private Animation animation;			//The model used for calculation animation
	private JColorChooser colorChooser;		//The color chooser being listened
	
	/**
	 * Initializes a newly created listener for buttons in the ActionPanel
	 * @param animation - The model of the controller
	 * @param colorChooser - The component containing data that the user chose
	 */
	public ColorController(JColorChooser colorChooser, Animation animation) {
		this.animation = animation;
		this.colorChooser = colorChooser;
	}

	/**
	 * Once a color is chosen from the color chooser, it sends the data to be saved in animation
	 * @param e - The event from choosing a color in colorChooser
	 * @see ChangeEvent, Animation
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		animation.setColor(colorChooser.getColor());
	}
}
