package animation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import options.ColorChooserDialog;

public class DropMenuController implements ActionListener {
	private Animation animation;
	
	public DropMenuController(Animation animation) {
		this.animation = animation;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("delete")) {
			this.animation.deletePoint();
		}
		else if (e.getActionCommand().equalsIgnoreCase("change color")) {
			new ColorChooserDialog(animation, ColorChooserDialog.POINT);
		}
	}

}
