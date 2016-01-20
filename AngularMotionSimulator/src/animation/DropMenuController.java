package animation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import options.ColorChooserDialog;

/**
 * A Controller that listens to the button events on DropMenu.
 * Sends the data to the Animation model about individual points.
 * @author Bryan Kristiono
 * @since 19/01/2016
 */
public class DropMenuController implements ActionListener {
	private Animation animation;
	
	/**
	 * Initializes a newly created listener for buttons on DropMenu.
	 * It connects to the animation model.
	 * @param animation The model that calculates the animation
	 */
	public DropMenuController(Animation animation) {
		this.animation = animation;
	}
	
	/**
	 * Does certain actions to the currently selected point in animation. 
	 * It allows the point to be deleted or change color.
	 * @param e The event sent from the buttons in the DropMenu
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("delete")) {
			this.animation.deletePoint();
		}
		else if (e.getActionCommand().equalsIgnoreCase("change color")) {
			new ColorChooserDialog(animation, ColorChooserDialog.POINT);
		}
	}

}
