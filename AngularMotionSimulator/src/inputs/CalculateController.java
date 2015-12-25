package inputs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class CalculateController implements ActionListener {

	private Input input;
	private ArrayList<InputFieldPanel> inputFields;
	private WantedFieldPanel wantedField;

	public CalculateController(Input input, ArrayList<InputFieldPanel> inputFields, WantedFieldPanel wantedField) {
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

		if(isDoubleVariables(variables, wantedVariable)) {
			new ErrorDialog((JFrame)SwingUtilities.getWindowAncestor(inputFields.get(0)), "Double Variables");
		}
		else {
			System.out.println(Arrays.toString(variables.toArray()));
			System.out.println(Arrays.toString(values.toArray()));

			this.input.resetVariables();
			this.input.setWantedVariable(wantedVariable);

			for(int i = 0; i < variables.size(); i++){
				switch(variables.get(i)){
					case("Angular Velocity"):
						this.input.setAngularVelocity(values.get(i));
						break;
					case("Linear Velocity"):
						this.input.setLinearVelocity(values.get(i));
						break;
					case("Radius"):
						this.input.setRadius(values.get(i));
						break;
					case("Time"):
						this.input.setTime(values.get(i));
						break;
					case("Angle"):
						this.input.setAngle(values.get(i));
						break;
					case("Arc Length"):
						this.input.setArcLength(values.get(i));
						break;
				}
			}
			this.input.updateCalculations();
		}
	}

	private boolean isDoubleVariables(ArrayList<String> variables, String wantedVariable) {
		for (int i=0;i<variables.size();i++) {
			String variable = variables.get(i);
			if (variable.equals(wantedVariable))
				return true;
			for (int k=i+1;k<variables.size();k++) {
				if (variable.equals(variables.get(k))) {
					return true;
				}
			}
		}
		return false;
	}

}
