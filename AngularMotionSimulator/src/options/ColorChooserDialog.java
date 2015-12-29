package options;

import javax.swing.*;

import animation.Animation;

/**
 * ColorChooserDialog
 * The dialog class for choosing the color of the circle in the animation
 * Sends the data to the Animation model
 * @author BRYAN KRISTIONO
 * @since 27/12/2015
 */
public class ColorChooserDialog extends JDialog {
	private Animation animation;		//The model used for calculation animation
	private JPanel panel;				//The main panel that contains the color chooser
	private JColorChooser colorChooser;	//The component that allows color to be chosen
	
	/**
	 * Initializes a new JDialog that contains a color chooser for the user to 
	 * @param frame - The parent frame that the dialog is connected to
	 * @param animation  - The model for animation
	 */
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
