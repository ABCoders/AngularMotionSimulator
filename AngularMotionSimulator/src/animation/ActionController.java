package animation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A Controller that listens to the button events on ActionPanel. 
 * Sends the data to the Animation model.
 * @author BRYAN KRISTIONO
 * @since 12/12/2015
 */
public class ActionController implements ActionListener {
	private Animation animation;		//The model used for calculation animation
	
	/**
	 * Initializes a newly created listener for buttons in the ActionPanel.
	 * @param animation The model of the controller
	 */
	public ActionController (Animation animation) {
		this.animation = animation;
	}
	
	/**
	 * Sets values to the model after a button is clicked.
	 * It changes the state of the animation.
	 * It also resets the drawn point in the animation.
	 * @param e The event from the button
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
