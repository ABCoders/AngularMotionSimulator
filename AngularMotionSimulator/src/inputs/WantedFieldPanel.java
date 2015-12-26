package inputs;

import javax.swing.*;

public class WantedFieldPanel extends JPanel{
	
	private Input input;
	private JLabel answerLabel;
	private JComboBox<String> variablePicker;
	
	public WantedFieldPanel(Input input) {
		this.input = input;
		this.createComponents();
		this.createPanel();
	}
	
	private void createComponents() {
		answerLabel = new JLabel("Calculate For: ");
		variablePicker = new JComboBox<String>(Input.VARIABLES);
		variablePicker.setSelectedIndex(0);
	}
	
	private void createPanel() {
		this.add(answerLabel);
		this.add(variablePicker);
	}
	
	public String getSelectedVariable() {
		return (String)variablePicker.getSelectedItem();
	}
	
	public void update() {
		variablePicker.setSelectedItem(input.getWantedVariable());
	}
}
