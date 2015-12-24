package calculation;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

public class CalculationsPanel extends JPanel{
	
	private Calculations calculations;
	private JPanel[] equationPanel;

	private ArrayList<String> equation;
	private ArrayList<String> valueEquation;
	private ArrayList<String> result;
	
	public CalculationsPanel(Calculations calculations){
		super();
		this.calculations = calculations;
		equation = calculations.getEquation();
		valueEquation = calculations.getValueEquation();
		result = calculations.getResult();
		equationPanel = new JPanel[this.equation.size()];
		this.createComponents();
		this.createPanel();
	}
	
	private void createComponents(){
		for(int x = 0; x < this.equation.size(); x++){
			JLabel calcFormulas = new JLabel(this.calculations.getWantedVariable() +  " = " + this.calculations.getEquation().get(x));
			JLabel calcWithValues = new JLabel(this.calculations.getWantedVariable() +  " = " + this.calculations.getValueEquation().get(x));
			JLabel calcAnswer = new JLabel(this.calculations.getWantedVariable() +  " = " + this.calculations.getResult().get(x));
			
			this.equationPanel[x] = new JPanel();
			this.equationPanel[x].setLayout(new GridLayout(3, 1));
			this.equationPanel[x].add(calcFormulas);
			this.equationPanel[x].add(calcWithValues);
			this.equationPanel[x].add(calcAnswer);
		}
	}
	
	private void createPanel(){
	    this.setBorder(BorderFactory.createTitledBorder("Calculation Process"));
	    for(int x = 0; x < equationPanel.length; x++){
	    	this.add(equationPanel[x]);
	    }
	}
}
