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
		ArrayList<String> variables = new ArrayList<String>();
		ArrayList<Double> values = new ArrayList<Double>();
		String wantedVariable;

		//Gets values and variables from input
		wantedVariable = wantedField.getSelectedVariable();
		for (InputFieldPanel field: inputFields) {
			variables.add(field.getSelectedVariable());
			values.add(field.getValue());
		}
		
		
	}

}
