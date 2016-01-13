package animation;

import java.awt.*;
import javax.swing.*;

public class DropMenu extends JPopupMenu {
	private Animation animation;
	private JMenuItem delete;
	
	public DropMenu(Animation animation) {
		super();
		this.animation = animation;
		this.createComponents();
		this.createMenu();
		this.registerControllers();
	}
	
	private void createComponents() {
		this.delete = new JMenuItem("Delete");
	}
	
	private void createMenu() {
		this.add(delete);
	}
	
	private void registerControllers() {
		DropMenuController controller = new DropMenuController(animation);
		delete.addActionListener(controller);
	}
}
