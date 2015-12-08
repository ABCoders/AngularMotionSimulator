package options;

import javax.swing.*;

public class OptionsMenuBar extends JMenuBar {
	private Options options;
	private JMenu file;
	private JMenu other;
	private JMenuItem saveItem;
	private JMenuItem loadItem;
	private JMenuItem helpItem;
	private JMenuItem colorItem;
	
	public OptionsMenuBar(Options options) {
		super();
		this.options = options;
		createComponents();
		createMenuBar();
		registerControllers();
	}
	
	private void createComponents() {
		file = new JMenu("File");
		other = new JMenu("Other");
		saveItem = new JMenuItem("Save As");
		loadItem = new JMenuItem("Load");
		helpItem = new JMenuItem("Help");
		colorItem = new JMenuItem("Change Color");
	}
	
	private void createMenuBar() {
		file.add(saveItem);
		file.add(loadItem);
		other.add(helpItem);
		other.add(colorItem);
		this.add(file);
		this.add(other);
	}
	
	private void registerControllers() {
		saveItem.addActionListener(new OptionsController(options));
		loadItem.addActionListener(new OptionsController(options));
		helpItem.addActionListener(new OptionsController(options));
		colorItem.addActionListener(new OptionsController(options));
	}
}
