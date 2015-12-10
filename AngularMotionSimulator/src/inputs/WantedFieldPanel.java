package inputs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
		answerLabel = new JLabel("ANSWER = ");
		variablePicker = new JComboBox<String>(Input.VARIABLES);
	}
	
	private void createPanel() {
		this.add(answerLabel);
		this.add(variablePicker);
	}
	
	public void update() {
		//Stub
	}
}
