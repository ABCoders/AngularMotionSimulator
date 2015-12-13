package calculation;

import javax.swing.*;
import java.awt.*;

public class ProcessFrame extends JFrame{
	private Calculations calculations;
	private GivensPanel givensPanel;
	private CalculationsPanel calcPanel;
	private JButton save;
	
	public ProcessFrame(Calculations calculations){
		super();
		this.calculations = calculations;
		this.setVisible(true);
	    this.setSize(500,250);
	    this.setContentPane(this.createFrame());
	}
	
	public void update(){
		
	}
	
	private JPanel createFrame(){
		JPanel main = new JPanel();
	    main.setLayout(new GridLayout(3, 1));
		GivensPanel givensPanel = new GivensPanel(this.calculations);
	    CalculationsPanel calculationsPanel = new CalculationsPanel(this.calculations);
	    main.add(givensPanel);
	    main.add(calculationsPanel);
	    
	    JButton saveButton = new JButton("Save");
	    this.add(saveButton);
	    return main;
	}
	
	private void registerControllers(){
		
	}
}