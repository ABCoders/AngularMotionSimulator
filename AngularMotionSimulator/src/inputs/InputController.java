package inputs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.Action;

public class InputController implements ActionListener{
	private Input input;
	private ArrayList<InputFieldPanel> inputFields;
	private WantedFieldPanel wantedField;

	public InputController(Input input, ArrayList<InputFieldPanel> inputFields, WantedFieldPanel wantedField) {
		this.input = input;
		this.inputFields = inputFields;
		this.wantedField = wantedField;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
//		ArrayList<String> variables = new ArrayList<String>();
//		ArrayList<Double> values = new ArrayList<Double>();
//		String wantedVariable;
		
		//Gets values and variables from input
		input.setWantedVariable(wantedField.getSelectedVariable());
		for (InputFieldPanel field: inputFields) {
//			variables.add(field.getSelectedVariable());
//			values.add(field.getValue());
			
			if (field.getSelectedVariable().equals(Input.VARIABLES[0])) {
				input.setAngularVelocity(field.getValue());
			} else if (field.getSelectedVariable().equals(Input.VARIABLES[1])) {
				input.setLinearVelocity(field.getValue());
			} else if (field.getSelectedVariable().equals(Input.VARIABLES[2])) {
				input.setRadius(field.getValue());
			} else if (field.getSelectedVariable().equals(Input.VARIABLES[3])) {
				input.setArcLength(field.getValue());
			} else if (field.getSelectedVariable().equals(Input.VARIABLES[4])) {
				input.setTime(field.getValue());
			} else if (field.getSelectedVariable().equals(Input.VARIABLES[5])) {
				input.setAngle(field.getValue());
			}
		}
	}

}
