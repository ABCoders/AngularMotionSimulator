package inputs;

import javax.swing.*;

import java.awt.*;

public class AnswerMachinePanel extends JPanel{
	//Determine Minimum size of panel
	private Input input;
	private InputPanel inputPanel;
	private JButton calculateButton;
	private JButton addButton;
	private WantedFieldPanel wantedFieldPanel;
	
	public AnswerMachinePanel(Input input) {
		this.input = input;
		this.createComponents();
		this.createPanel();
		this.registerControllers();
	}
	
	private void createComponents() {
		this.inputPanel = new InputPanel(input);
		this.wantedFieldPanel = new WantedFieldPanel(input);
		this.calculateButton = new JButton("CALCULATE");
		this.addButton = new JButton("ADD");
	}
	
	private void createPanel() {
		this.setLayout(new BorderLayout());
		this.add(wantedFieldPanel, BorderLayout.NORTH);
		this.add(inputPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(addButton);
		buttonPanel.add(calculateButton);
		
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
		
	private void registerControllers() {
		addButton.addActionListener(new AddFieldController(input));
		calculateButton.addActionListener(new CalculateController(input, inputPanel.getFields()));
	}
	
	public void update() {
		inputPanel.update();
	}
}
