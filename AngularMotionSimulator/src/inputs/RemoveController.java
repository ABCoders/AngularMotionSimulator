package inputs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RemoveController implements ActionListener{

	private Input input;
	private JButton delete;
	
	public RemoveController(Input input, JButton delete)
	{
		this.input = input;
		this.delete = delete;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
	}
}
