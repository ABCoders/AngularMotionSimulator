//packages and imports
package calculation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

/** ProcessSaveController
 *  The controller that saves the process work of the calculation used to get the wanted variable
 *  @author Amritpal Aujla
 *  @since 26/12/2015
 */
public class ProcessSaveController implements ActionListener {
	//attributes
	private JLabel equation;						//the label containing the word equation
	private JLabel valueEquation;					//the label containing the equation with the values replacing the words
	private JLabel result;							//the label containing the result of the equation
	private String wantedVariable;					//the variable the user wants to calculate for
	
	/** The Default Constructor - sets the values of the labels and wanted variable 
	 *  @param equation - the word equation label from CalculationsPanel
	 *  @param valueEquation - the equation label from CalcuationsPanel with values replacing the words
	 *  @param result - the result label from CalculationsPanel with the solution to the equation
	 */
	public ProcessSaveController(JLabel equation, JLabel valueEquation, JLabel result) {
		this.equation = equation;
		this.valueEquation = valueEquation;
		this.result = result;
		this.wantedVariable = this.equation.getText().substring(0, this.equation.getText().indexOf(" ="));
	}

	/** Save the calculation process into a text file of the user's choice of name and location
	 *  @param e - the event from the save button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//marking a file name and location
		JFileChooser fileChooser = new JFileChooser(); 				//the file chooser that the user uses to mark a location and name for the file
		int working = fileChooser.showSaveDialog(equation.getTopLevelAncestor());
		if(working == JFileChooser.APPROVE_OPTION){
			PrintWriter output = null;								//the PrintWriter object that puts the data into the chosen file
			//trying to output to the file
			try{
				//setting the file name so that the user does not need to add a .txt extension
			    String path = fileChooser.getSelectedFile().getAbsolutePath();
			    if(path.substring(path.length() - 4).equals(".txt"))
			     path = fileChooser.getSelectedFile().getAbsolutePath();
			    else
			    	path = fileChooser.getSelectedFile().getAbsolutePath() + ".txt";
			    
			    //outputting all the equations and result into the file and then closing it
			    output = new PrintWriter(new File(path));
			    output.println("Calculation for " + this.wantedVariable);
			    output.println(this.equation.getText());
			    output.println(this.valueEquation.getText());
			    output.println(this.result.getText());
			    output.close();
			}
			//if the file is not found somehow
			catch(FileNotFoundException ex){}
		}
	}
}
