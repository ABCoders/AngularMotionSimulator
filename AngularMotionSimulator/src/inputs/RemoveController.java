package inputs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveController implements ActionListener{

	private Input input;
	private InputFieldPanel inputField;
	
	public RemoveController(Input input, InputFieldPanel inputField) {
		this.input = input;
		this.inputField = inputField;
	}
	
	public void actionPerformed(ActionEvent e) {
		input.removeField(inputField.getPosition());
	}
}
