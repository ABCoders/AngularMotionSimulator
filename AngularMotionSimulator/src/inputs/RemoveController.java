package inputs;
import java.awt.event.ActionEvent;

/**
 * Controller for button to remove field
 * @author Cindy Zhao
 * @since 12/12/15
 */
import java.awt.event.ActionListener;

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
