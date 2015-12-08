package calculation;

import javax.swing.*;

public class ProcessFrame extends JFrame{
	private Calculations calculations;
	private GivensPanel givensPanel;
	private CalculationsPanel calcPanel;
	private JButton save;
	
	public ProcessFrame(Calculations calculations){
		super();
		this.calculations = calculations;
		this.layoutView();
		this.registerControllers();
	}
	
	public void update(){
		
	}
	
	private void layoutView(){
		
	}
	
	private void registerControllers(){
		
	}
}
