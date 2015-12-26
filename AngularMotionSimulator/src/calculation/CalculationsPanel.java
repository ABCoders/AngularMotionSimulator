//packages and imports
package calculation;
import java.awt.GridLayout;

import javax.swing.*;

/** CalculationsPanel
 *  The view showing the process work of the calculation for the wanted variable
 *  @author Amritpal Aujla
 *  @since 26/12/2015
 */
public class CalculationsPanel extends JPanel{
	//attributes
	private Calculations calculations;						//the Calculations model providing the data to this panel
	private JLabel equation;								//the label for the word equation for the calculation
	private JLabel valueEquation; 							//the label for the equation with values instead of words
	private JLabel result;									//the label with the result of the calculation
	
	/** The Default Constructor - makes a CalculationsPanel, initializes calculations, creates all components and the panel
	 *  @param calculations - the calculations model for the calculations process that the panel needs
	 */
	public CalculationsPanel(Calculations calculations){
		super();
		this.calculations = calculations;
		this.createComponents();
		this.createPanel();
	}
	
	/** Makes the three label components with the appropriate text
	 */
	private void createComponents(){
		this.equation = new JLabel(this.calculations.getWantedVariable() + " = " + this.calculations.getEquation());
	    this.valueEquation = new JLabel(this.calculations.getWantedVariable() + " = " + this.calculations.getValueEquation());
	    this.result = new JLabel(this.calculations.getWantedVariable() + " = " + this.calculations.getResult());
	}
	
	/** Sets the panel's properties and puts all components in their appropriate places
	 */
	private void createPanel(){
		//makes the title and layout of the panel
	    this.setBorder(BorderFactory.createTitledBorder("Calculation Process"));
	    this.setLayout(new GridLayout(3, 1));
		
	    //puts all the components in the panel
	    this.add(this.equation);
	    this.add(this.valueEquation);
	    this.add(result);
	}
	
	/** Returns the equation attribute
	 *  @return the equation - word equation
	 */
	public JLabel getEquation() {
		return equation;
	}

	/** Returns the valueEquation attribute
	 *  @return the valueEquation - the equation with values instead of words
	 */
	public JLabel getValueEquation() {
		return valueEquation;
	}

	/** Returns the result attribute
	 *  @return the result - the solution to the equation
	 */
	public JLabel getResult() {
		return result;
	}
}