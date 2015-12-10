package inputs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InputFieldPanel extends JPanel{
	
	private Input input;
	
	private JComboBox <String> variablePicker;
	private JSpinner valueSpinner;
	private JButton deleteButton;
	
	public InputFieldPanel(Input input) {
		super();
		this.input = input;
		this.createComponent();
		this.createPanel();
		this.registerControllers();
	}
	
	private void createComponent() {
		variablePicker = new JComboBox<String>(Input.VARIABLES);
		variablePicker.setSelectedIndex(0);
		valueSpinner = new JSpinner();
		deleteButton = new JButton("X");
	}
	
	private void createPanel() {
		this.add(variablePicker);
		this.add(valueSpinner);
		this.add(deleteButton);
	}
	
	private void registerControllers() {
		deleteButton.addActionListener(new RemoveController(input, this));
	}
	
	public void update() {
	
	}
}
