/**
 * 
 */
package options;

import javax.swing.JColorChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import animation.Animation;

/**
 * A Controller that listens the color the user chooses in the color chooser.
 * Sends the data to the Animation model.
 * @author Bryan Kristiono
 * @since 27/12/2015
 */
public class ColorController implements ChangeListener {
	private Animation animation;			//The model used for calculation animation
	private JColorChooser colorChooser;		//The color chooser being listened
	private int object;						//The object that is changing color
	
	/**
	 * Initializes a newly created listener for buttons in the ActionPanel.
	 * @param animation		 The model of the controller
	 * @param colorChooser	 The component containing data that the user chose
	 * @param object 		 The object that is changing color
	 */
	public ColorController(JColorChooser colorChooser, Animation animation, int object) {
		this.animation = animation;
		this.colorChooser = colorChooser;
		this.object = object;
	}

	/**
	 * Once a color is chosen from the color chooser, it sends the data to be saved in animation.
	 * @param e The event from choosing a color in colorChooser
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		if(object==ColorChooserDialog.CIRCLE)
			animation.setCircleColor(colorChooser.getColor());
		else if(object==ColorChooserDialog.POINT)
			animation.setPointColor(colorChooser.getColor());
		else if(object==ColorChooserDialog.ALL_POINTS)
			animation.setAllPointsColor(colorChooser.getColor());
	}
}