package options;

import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;

public class OptionsController implements MenuKeyListener {
	private Options options;
	
	public OptionsController(Options options) {
		this.options = options;
	}

	@Override
	public void menuKeyPressed(MenuKeyEvent e) {
		//Stub
	}

	@Override
	public void menuKeyReleased(MenuKeyEvent e) {
		//Stub
	}

	@Override
	public void menuKeyTyped(MenuKeyEvent e) {
	}
}
