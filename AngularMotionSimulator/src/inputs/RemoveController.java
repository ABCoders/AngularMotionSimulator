package inputs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RemoveController implements ActionListener{

	private Input input;
	private InputFieldPanel inputField;
	private JButton delete;
	
	public RemoveController(Input input, InputFieldPanel inputField) {
		this.input = input;
		this.inputField = inputField;
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
	}
}
