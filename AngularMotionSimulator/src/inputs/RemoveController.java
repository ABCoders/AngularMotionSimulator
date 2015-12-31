package inputs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for button to remove field
 * @author Cindy Zhao
 * @since 12/12/15
 */


public class RemoveController implements ActionListener{

	private Input input; //Input model instance
	private InputFieldPanel inputField; //GUI 
	
	/**
	 * Main Constructor
	 * @param input input model instance
	 * @param inputField GUI containing button
	 */
	public RemoveController(Input input, InputFieldPanel inputField) {
		this.input = input;
		this.inputField = inputField;
	}
	
	/**
	 * Deletes field
	 */
	public void actionPerformed(ActionEvent e) {
		input.removeField(inputField.getPosition());
	}
}
