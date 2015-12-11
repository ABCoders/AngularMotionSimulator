package inputs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InputFieldPanel extends JPanel{
	
	private Input input;
	private int position;
	
	private JComboBox <String> variablePicker;
	private JSpinner valueSpinner;
	private JButton deleteButton;
	
	public InputFieldPanel(Input input, int index) {
		super();
		this.input = input;
		this.position = index;
		this.createComponent();
		this.createPanel();
		this.registerControllers();
	}
	
	private void createComponent() {
		variablePicker = new JComboBox<String>(Input.VARIABLES);
		variablePicker.setSelectedIndex(0);
		valueSpinner = new JSpinner(new SpinnerNumberModel(1.0, 0.0, 99999.0, 0.5));
		deleteButton = new JButton("X");
	}
	
	private void createPanel() {
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		this.add(variablePicker);
		this.add(valueSpinner);
		this.add(deleteButton);
	}
	
	private void registerControllers() {
		deleteButton.addActionListener(new RemoveController(input, this));
	}
	
	public void setPosition(int index) {
		this.position = index;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void update() {
		
	}
}
