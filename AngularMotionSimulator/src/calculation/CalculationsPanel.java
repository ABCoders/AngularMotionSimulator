package calculation;

import javax.swing.*;

public class CalculationsPanel extends JPanel{
	
	private Calculations calculations;
	private JLabel wantedLabel;
	private JLabel calcFormulas;
	private JLabel calcWithValues;
	private JLabel calcAnswer;
	
	public CalculationsPanel(Calculations calculations){
		super();
		this.calculations = calculations;
		this.layoutView();
	}
	
	public void update(){
		
	}
	
	private void layoutView(){
		
	}
}
