package inputs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AddFieldController implements ActionListener{

	private Input input;
	
	public AddFieldController(Input input) {
		this.input = input;
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
	}
}
