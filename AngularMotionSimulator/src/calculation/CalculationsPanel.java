package calculation;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

public class CalculationsPanel extends JPanel{
	
	private Calculations calculations;
	private JLabel calcFormulas;
	private JLabel calcWithValues;
	private JLabel calcAnswer;
	
	public CalculationsPanel(Calculations calculations){
		super();
		this.calculations = calculations;
		this.createComponents();
		this.createPanel();
	}
	
	private void createComponents(){
		this.calcFormulas = new JLabel(this.calculations.getWantedVariable() + " = " + this.calculations.getEquation());
	    this.calcWithValues = new JLabel(this.calculations.getWantedVariable() + " = " + this.calculations.getValueEquation());
	    this.calcAnswer = new JLabel(this.calculations.getWantedVariable() + " = " + this.calculations.getResult());
	}
	
	private void createPanel(){
	    this.setBorder(BorderFactory.createTitledBorder("Calculation Process"));
	    this.setLayout(new GridLayout(4, 1));
		
	    this.add(this.calcFormulas);
	    this.add(this.calcWithValues);
	    this.add(calcAnswer);
	}
}
