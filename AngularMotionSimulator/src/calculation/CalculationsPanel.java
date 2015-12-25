package calculation;

import java.awt.GridLayout;

import javax.swing.*;

public class CalculationsPanel extends JPanel{
	
	private Calculations calculations;
	private JLabel equation;
	private JLabel valueEquation;
	private JLabel result;
	
	public CalculationsPanel(Calculations calculations){
		super();
		this.calculations = calculations;
		this.createComponents();
		this.createPanel();
	}
	
	private void createComponents(){
		this.equation = new JLabel(this.calculations.getWantedVariable() + " = " + this.calculations.getEquation());
	    this.valueEquation = new JLabel(this.calculations.getWantedVariable() + " = " + this.calculations.getValueEquation());
	    this.result = new JLabel(this.calculations.getWantedVariable() + " = " + this.calculations.getResult());
	}
	
	private void createPanel(){
	    this.setBorder(BorderFactory.createTitledBorder("Calculation Process"));
	    this.setLayout(new GridLayout(4, 1));
		
	    this.add(this.equation);
	    this.add(this.valueEquation);
	    this.add(result);
	}
	
	/**
	 * @return the equation
	 */
	public JLabel getEquation() {
		return equation;
	}

	/**
	 * @return the valueEquation
	 */
	public JLabel getValueEquation() {
		return valueEquation;
	}

	/**
	 * @return the result
	 */
	public JLabel getResult() {
		return result;
	}
}