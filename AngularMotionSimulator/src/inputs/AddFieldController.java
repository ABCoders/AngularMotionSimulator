package inputs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** 
 * Controller for button that adds a new input field
 * @author Cindy Zhao
 * @author Bryan Kristiono
 * @since 09/12/14
 */

public class AddFieldController implements ActionListener{

	private Input input; //Input model that gets information from controller
	
	/**
	 * Main constructor 
	 * @param input input model that adds a field
	 */
	public AddFieldController(Input input) {
		this.input = input;
	}
	
	/**
	 * Adds field in input model
	 */
	public void actionPerformed(ActionEvent e) {
		input.addField();
	}
}
