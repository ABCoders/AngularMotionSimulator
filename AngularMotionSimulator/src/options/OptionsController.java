package options;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsController implements ActionListener {
	private Options options;
	
	public OptionsController(Options options) {
		this.options = options;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
	}
}
