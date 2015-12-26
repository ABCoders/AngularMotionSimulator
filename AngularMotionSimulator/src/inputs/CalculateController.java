//packages and imports
package inputs;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/** CalculateController
 *  The controller that sends the data from the AnswerMachinePanel to Input, Calculations, and then calculates for the wanted variable
 *  @author Amritpal Aujla
 *  @author Bryan Kristiono
 *  @since 26/12/2015
 */
public class CalculateController implements ActionListener{
	//attributes
	private Input input;										//the input model to set the values in
	private ArrayList<InputFieldPanel> inputFields;				//the array list of fields in the Answer Machine Panel that the input is gotten from
	private WantedFieldPanel wantedField;						//the wanted field panel containing the name of the variable the user wants to calculate

	/** The Default Constructor - initializes the input model, sets the values of the input fields and wanted field 
	 *  @param input - the input model to give the input values to
	 *  @param inputFields - the collection of input fields that contain the variable values
	 *  @param wantedField - the field that has the name of the wanted variable
	 */
	public CalculateController(Input input, ArrayList<InputFieldPanel> inputFields, WantedFieldPanel wantedField) {
		this.input = input;
		this.inputFields = inputFields;
		this.wantedField = wantedField;
	}
	
	/** 
	 *  @param e - the event from the action listener that designates the calculate button being pressed
	 */
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
			new ErrorDialog((JFrame)SwingUtilities.getWindowAncestor(inputFields.get(0)), "Have only one type of variable in each input.");
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
		for (int x = 0; x < variables.size(); x++) {
			String variable = variables.get(x);
			if (variable.equals(wantedVariable))
				return true;
			for (int y = x+1; y < variables.size(); y++) {
				if (variable.equals(variables.get(y))) {
					return true;
				}
			}
		}
		return false;
	}

}
