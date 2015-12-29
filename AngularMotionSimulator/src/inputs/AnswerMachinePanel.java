package inputs;

import javax.swing.*;

import java.awt.*;

/** AddFieldController
 * Controller for button that adds a new input field
 * @author 
 * @since
 */

public class AnswerMachinePanel extends JPanel{
	//attribtues
	private Input input; //Input model that gets information from panel
	private InputPanel inputPanel; //Panel containing input fields
	private JButton calculateButton; //Button to calculate
	private JButton addButton; //Button to add field
	private WantedFieldPanel wantedFieldPanel; //Panel containing combobox for selecting wanted variable
	
	/**
	 * Main constructor 
	 * @param input - input model of panel
	 */
	public AnswerMachinePanel(Input input) {
		this.input = input;
		this.createComponents();
		this.createPanel();
		this.registerControllers();
		this.input.setGUI(this);
	}
	
	/**
	 * Creates separate components of panel
	 */
	private void createComponents() {
		this.inputPanel = new InputPanel(input);
		this.wantedFieldPanel = new WantedFieldPanel(input);
		this.calculateButton = new JButton("CALCULATE");
		this.addButton = new JButton("ADD");
	}
	
	/**
	 * 
	 */
	public void update()
	{
	}
	
	/**
	 * Lays out components in panel
	 */
	private void createPanel() {
		//Adds panels
		this.setLayout(new BorderLayout());
		this.add(wantedFieldPanel, BorderLayout.NORTH);
		this.add(inputPanel, BorderLayout.CENTER);
		
		//Adds buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(addButton);
		buttonPanel.add(calculateButton);
		
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
		
	/**
	 * Registers controllers for addField and calculate buttons
	 */
	private void registerControllers() {
		addButton.addActionListener(new AddFieldController(input));
		calculateButton.addActionListener(new CalculateController(input, inputPanel.getFields(), wantedFieldPanel));
//		System.out.println("AP" + inputPanel.getFields() + ", " + wantedFieldPanel);
	}
	
	/**
	 * @return panel of input fields 
	 */
	public InputPanel getInputPanel() {
		return inputPanel;
	}
	
	/**
	 * @return panel for wanted variable
	 */
	public WantedFieldPanel getWantedFieldPanel() {
		return wantedFieldPanel;
	}
}
