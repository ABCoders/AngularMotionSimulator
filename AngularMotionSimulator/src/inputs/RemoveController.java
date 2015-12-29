package inputs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveController implements ActionListener{

	private Input input; //Input model instance
	private InputFieldPanel inputField; //GUI 
	
	/**
	 * Main Constructor
	 * @param input - Input model instance
	 * @param inputField - GUI
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
