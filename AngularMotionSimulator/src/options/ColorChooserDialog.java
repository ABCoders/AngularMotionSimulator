package options;

import javax.swing.*;

import animation.Animation;

/**
 * The dialog class for choosing the color of the circle in the animation.
 * Sends the data to the Animation model.
 * @author Bryan Kristiono
 * @since 27/12/2015
 */
public class ColorChooserDialog extends JDialog {
	public static final int CIRCLE = 0;
	public static final int POINT = 1;
	
	private Animation animation;		//The model used for calculation animation
	private JPanel panel;				//The main panel that contains the color chooser
	private JColorChooser colorChooser;	//The component that allows color to be chosen
	private int object;
	
	/**
	 * Initializes a new JDialog that contains a color chooser for the user to.
	 * @param frame		 The parent frame that the dialog is connected to
	 * @param animation	 The model for animation
	 */
	public ColorChooserDialog(Animation animation, int object) {
		super((JFrame)SwingUtilities.getWindowAncestor(animation.getComponent()), "Color Chooser", true);
		this.animation = animation;
		this.object = object;
		this.createComponents();
		this.registerControllers();
		this.createDialog();
	}
	
	/**
	 * Initialize and set values to all needed components.
	 */
	private void createComponents() {
		panel = new JPanel();
		colorChooser = new JColorChooser(animation.getCircleColor());
	}
	
	/**
	 * Set up the components in the panel layout.
	 */
	private void createDialog() {
		panel.add(colorChooser);
		this.setContentPane(panel);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setLocation(400, 50);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}
	
	/**
	 * Add Listeners to all components.
	 */
	private void registerControllers() {
		colorChooser.getSelectionModel().addChangeListener(new ColorController(colorChooser, animation, object));
	}
}
