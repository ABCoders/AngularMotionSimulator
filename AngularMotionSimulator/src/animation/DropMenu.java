package animation;

import javax.swing.*;

/**
 * The menu for when the user right clicks on the animation. It allows the user to create an action
 * on individual dots.
 * @author Bryan Kristiono
 * @since 19/01/2016
 */
public class DropMenu extends JPopupMenu {
	/*  Attributes  */
	private Animation animation;	//The model with information for the animation
	private JMenuItem delete;		//The button that deletes a specific point
	private JMenuItem changeColor;	//The button that changes the color of a specific point
	
	/**
	 * Initializes a new instance of DropMenu with required information.
	 * @param animation	The model that calculates the animation
	 */
	public DropMenu(Animation animation) {
		super();
		this.animation = animation;
		this.createComponents();
		this.createMenu();
		this.registerControllers();
	}
	
	/**
	 * Initializes the components needed.
	 */
	private void createComponents() {
		this.delete = new JMenuItem("Delete");
		this.changeColor = new JMenuItem("Change Color");
	}
	
	/**
	 * Initializes the class with components added.
	 */
	private void createMenu() {
		this.add(delete);
		this.addSeparator();
		this.add(changeColor);
	}
	
	/**
	 * Adds required listeners to required components.
	 */
	private void registerControllers() {
		DropMenuController controller = new DropMenuController(animation);
		delete.addActionListener(controller);
		changeColor.addActionListener(controller);
	}
}