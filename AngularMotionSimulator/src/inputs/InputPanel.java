package inputs;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * GUI for collection of user input fields
 * @author Cindy Zhao
 * @author Bryan Kristiono
 * @since 09/12/15
 */

public class InputPanel extends JPanel{
	
	private Input input; //Input model
	private ArrayList<InputFieldPanel> field; //Array list of panels for user input variables
	
	/**
	 * Main constructor
	 * @param input - input model of panel
	 */
	public InputPanel(Input input) {
		this.input = input;
		this.createComponents();
		this.createPanel();
	}
	
	/**
	 * Creates separate components of panel
	 */
	private void createComponents() {
		field = new ArrayList<InputFieldPanel>();
		for (int i=0; i<input.getNumberFields(); i++) {
			field.add(new InputFieldPanel(input, i));
		}
	}
	
	/**
	 * Lays out components in panel
	 */
	private void createPanel() {
		this.setLayout(new GridLayout(field.size(), 1));
		this.setPreferredSize(new Dimension(500, 200));
		for (int i=0; i<field.size(); i++) {
			this.add(field.get(i));
		}
	}
	
	/**
	 * @return array list of user input fields
	 */
	public ArrayList<InputFieldPanel> getFields() {
		return field;
	}
	
	/**
	 * @param state whether or not to update panel
	 */
	public void update(boolean state) {
		//If there should be more fields
		while(input.getNumberFields()>field.size()) {
			field.add(new InputFieldPanel(input, field.size()));
			this.add(field.get(field.size()-1));
		}
		
		//There should be less fields
		while(input.getNumberFields()<field.size()) {
			field.remove(input.getRemovedField());
			this.remove(input.getRemovedField());
			for(int i=input.getRemovedField(); i<field.size(); i++) {
				field.get(i).setPosition(i);
			}
		}
		
		//Whether or not the field should updated from input
		if (state) {
			int variable = 0;
			for(int i=0; i<field.size(); i++) {
				for(int k = variable; k<Input.VARIABLES.length; k++) {
					double value = input.getVariableValue(k);
					if(value!=0) {
						field.get(i).setSelectedVariable(k);
						field.get(i).setValue(value);
						variable = k+1;
						break;
					}
				}
			}
		}
		
		if (field.size() > 3) {
			this.setLayout(new GridLayout (3, 2));
		}
		else {
			this.setLayout(new GridLayout(field.size(), 1));
		}
		this.repaint();
		this.revalidate();
		this.validate();
	}
}
