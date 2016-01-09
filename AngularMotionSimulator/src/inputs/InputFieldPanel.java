package inputs;

import javax.swing.*;
import java.awt.*;

/**
 * InputFieldPanel
 * Panel for one variable value to be input, can be deleted
 * @author Cindy Zhao
 * @author Bryan Kristiono
 * @author Amritpal Aujla
 * @since 09/12/15
 */
public class InputFieldPanel extends JPanel{
	
	private Input input; //Model that variables and values are sent to
	private int position; //Position of specific field in answer machine
	
	private JComboBox <String> variablePicker; //List of variable names
	private JSpinner valueSpinner; //Spinner for inputting values
	private JButton deleteButton; //Button to delete field
	
	/**
	 * Main constructor to set variables and create panel
	 * @param input input model
	 * @param index position of panel
	 */
	public InputFieldPanel(Input input, int index) {
		super();
		this.input = input;
		this.position = index;
		this.createComponent();
		this.createPanel();
		this.registerControllers();
	}
	
	/**
	 * Creates separate components of panel
	 */
	private void createComponent() {
		variablePicker = new JComboBox<String>(Input.VARIABLES);
		if(this.position == 0)
			variablePicker.setSelectedIndex(1);
		else if(this.position == 1)
			variablePicker.setSelectedIndex(2);
		else
			variablePicker.setSelectedIndex(position);
		valueSpinner = new JSpinner(new SpinnerNumberModel(1.0, 0.0001, 99999.0, 0.5));
		deleteButton = new JButton("X");
	}
	
	/**
	 * Lays out components in panel
	 */
	private void createPanel() {
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		this.add(variablePicker);
		this.add(valueSpinner);
		this.add(deleteButton);
	}

	/**
	 * Registers controller for delete button
	 */
	private void registerControllers() {
		deleteButton.addActionListener(new RemoveController(input, this));
	}
	
	/**
	 * Sets location of panel
	 * @param index position of panel
	 */
	public void setPosition(int index) {
		this.position = index;
	}
	
	/**
	 * @return location of panel
	 */
	public int getPosition() {
		return position;
	}
	
	/**
	 * @return variable selected by user to input value for
	 */
	public String getSelectedVariable() {
		return (String)variablePicker.getSelectedItem();
	}
	
	/**
	 * @return value of variable selected by user
	 */
	public double getValue() {
		return (double)valueSpinner.getValue();
	}
	
	/**
	 * Sets which variable the field is inputting
	 * @param index index of variable in combobox
	 */
	public void setSelectedVariable(int index) {
		variablePicker.setSelectedIndex(index);
	}
	
	/**
	 * Sets value of variable selected by user
	 * @param value value to be set
	 */
	public void setValue(double value) {
		valueSpinner.setValue(value);
	}
	
	/**
	 * 
	 */
	public void update() {
		try {	
			valueSpinner.commitEdit();
		} catch (Exception e) {
		}
	}
}
