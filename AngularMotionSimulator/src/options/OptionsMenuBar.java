package options;

import javax.swing.*;

import inputs.Input;

public class OptionsMenuBar extends JMenuBar {
	private Input input;
	private JMenu file;
	private JMenu other;
	private JMenuItem saveItem;
	private JMenuItem loadItem;
	private JMenuItem helpItem;
	private JMenuItem colorItem;
	
	public OptionsMenuBar(Input input) {
		super();
		this.input = input;
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
		saveItem.addActionListener(new OptionsController(input, this));
		loadItem.addActionListener(new OptionsController(input, this));
		helpItem.addActionListener(new OptionsController(input, this));
		colorItem.addActionListener(new OptionsController(input, this));
	}
}
