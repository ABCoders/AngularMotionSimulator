package animation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**ActionController
 * A Controller that listens to the button events on ActionPanel
 * Sends the data to the Animation model
 * @author BRYAN KRISTIONO
 * @since 7/12/2015
 */
public class ActionController implements ActionListener {
	private Animation animation;
	
	/**Main Constructor
	 * 
	 * @param
	 */
	public ActionController (Animation animation) {
		this.animation = animation;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action performed");
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
