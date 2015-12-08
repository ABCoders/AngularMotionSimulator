package inputs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AddFieldController implements ActionListener{

	private Input input;
	private JButton addVariable;
	
	public AddFieldController(Input input, JButton button)
	{
		this.input = input;
		this.addVariable = button;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
	}
}
