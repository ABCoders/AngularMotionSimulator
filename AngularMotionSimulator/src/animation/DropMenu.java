package animation;

import javax.swing.*;

public class DropMenu extends JPopupMenu {
	private Animation animation;
	private JMenuItem delete;
	private JMenuItem changeColor;
	
	public DropMenu(Animation animation) {
		super();
		this.animation = animation;
		this.createComponents();
		this.createMenu();
		this.registerControllers();
	}
	
	private void createComponents() {
		this.delete = new JMenuItem("Delete");
		this.changeColor = new JMenuItem("Change Color");
	}
	
	private void createMenu() {
		this.add(delete);
		this.addSeparator();
		this.add(changeColor);
	}
	
	private void registerControllers() {
		DropMenuController controller = new DropMenuController(animation);
		delete.addActionListener(controller);
		changeColor.addActionListener(controller);
	}
}
