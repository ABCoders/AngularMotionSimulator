package inputs;

import javax.swing.*;

/**
 * GUI for user to select the variable they wish to solve for.
 * @author Cindy Zhao
 * @author Bryan Kristiono
 * @since 12/12/15
 */

public class WantedFieldPanel extends JPanel{
	/*		Attributes		*/
	private Input input; 						//Input model getting the wanted variable from the panel
	private JLabel answerLabel; 				//Label for user to select the variable they wish to calculate for
	private JComboBox<String> variablePicker; 	//List for user to select which variable they wish to calculate for
	
	
	/**
	 * Creates a new instance with required information to be displayed.
	 * @param input the input model to send wanted variable to
	 */
	public WantedFieldPanel(Input input) {
		this.input = input;
		this.createComponents();
		this.createPanel();
	}
	
	/**
	 * Creates separate components of panel.
	 */
	private void createComponents() {
		answerLabel = new JLabel("Calculate For: ");
		variablePicker = new JComboBox<String>(Input.VARIABLES);
		variablePicker.setSelectedIndex(0);
	}
	
	/**
	 * Lays out components in panel
	 */
	private void createPanel() {
		this.add(answerLabel);
		this.add(variablePicker);
	}
	
	/**
	 * Returns the variable the user wished to solve for.
	 * @return The variable the user wishes to solve for
	 */
	public String getSelectedVariable() {
		return (String)variablePicker.getSelectedItem();
	}
	
	/**
	 * Sets the displayed item to the variable selected by user.
	 */
	public void update() {
		variablePicker.setSelectedItem(input.getWantedVariable());
	}
}
