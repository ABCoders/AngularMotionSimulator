/**
 * 
 */
package options;

import javax.swing.*;

import animation.Animation;

/**
 * @author BRYAN KRISTIONO
 *
 */
public class ColorChooserDialog extends JDialog {
	private Animation animation;
	private JPanel panel;
	private JColorChooser colorChooser;
	
	public ColorChooserDialog(JFrame frame, Animation animation) {
		super(frame, "Color Chooser", true);
		this.animation = animation;
		this.createComponents();
		this.registerControllers();
		this.createDialog();
	}
	
	private void createComponents() {
		panel = new JPanel();
		colorChooser = new JColorChooser(animation.getColor());
	}
	
	private void createDialog() {
		panel.add(colorChooser);
		this.setContentPane(panel);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setLocation(400, 50);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}
	
	private void registerControllers() {
		colorChooser.getSelectionModel().addChangeListener(new ColorController(colorChooser, animation));
	}
}
