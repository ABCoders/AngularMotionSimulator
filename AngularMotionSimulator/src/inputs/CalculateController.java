package inputs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class CalculateController implements ActionListener {

	private Input input;
	private ArrayList<InputFieldPanel> inputFields;
	
	public CalculateController(Input input, ArrayList<InputFieldPanel> inputFields) {
		this.input = input;
		this.inputFields = inputFields;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean canCalculate = true;
		ArrayList<String> variables = new ArrayList<String>();
		ArrayList<Double> values = new ArrayList<Double>();
		
		//Gets values and variables from input
		for (InputFieldPanel field: inputFields) {
			variables.add(field.getSelectedVariable());
			values.add(field.getValue());
		}
		
		//Check if variables are selected twice
		for (int i=0;i<variables.size();i++) {
			String variable = variables.get(i);
			for (int k=i+1;k<variables.size();k++) {
				if (variable.equals(variables.get(k))) {
					//Output Double variable error
					canCalculate = false;
				}
			}
		}
		if(!canCalculate) {
			InputFieldPanel input = inputFields.get(0);
			new ErrorDialog((JFrame)SwingUtilities.getWindowAncestor(input), "Cannot Calculate");
		}
		System.out.println(Arrays.toString(variables.toArray()));
		System.out.println(Arrays.toString(values.toArray()));
	}

}
