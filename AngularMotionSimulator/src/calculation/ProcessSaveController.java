package calculation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProcessSaveController implements ActionListener {
	private Calculations calculations;
	
	public ProcessSaveController(Calculations calculations) {
		this.calculations = calculations;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.calculations.saveProcess();
	}
}
