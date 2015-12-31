package animation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A Controller that listens to the button events on ActionPanel.
 * Sends the data to the Animation model. Either stopping, playing, or clearing the animation.
 * @author Bryan Kristiono
 * @since 12/12/2015
 */
public class ActionController implements ActionListener {
	private Animation animation;		//The model used for calculation animation
	
	/**
	 * Initializes a newly created listener for buttons in the ActionPanel.
	 * It connects to the animation model.
	 * @param animation The model that calculates the animation
	 */
	public ActionController (Animation animation) {
		this.animation = animation;
	}
	
	/**
	 * Sets values to the model after a button is clicked.
	 * It changes the state of the animation.
	 * It also resets the drawn point in the animation.
	 * @param e The event sent from the buttons in the action panel
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equalsIgnoreCase("play")) {
			animation.setState(true);
		} else if(command.equalsIgnoreCase("pause")) {
			animation.setState(false);
		} else if(command.equalsIgnoreCase("clear")) {
			animation.clear();
		}
	}

}
