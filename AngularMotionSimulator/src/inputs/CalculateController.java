package inputs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculateController implements ActionListener {

	private Input input;
	
	public CalculateController(Input input) {
		this.input = input;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
	}

}
