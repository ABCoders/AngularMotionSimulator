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
	    main.setLayout(new GridLayout(2, 1));
	    JPanel givensPanel = new JPanel();
	    JPanel calculationPanel = new JPanel();
	    givensPanel.setBorder(BorderFactory.createTitledBorder("Givens"));
	    givensPanel.setLayout(new GridLayout(3, 3));
	    calculationPanel.setBorder(BorderFactory.createTitledBorder("Calculation Process"));
	    calculationPanel.setLayout(new GridLayout(4, 1));
	    main.add(givensPanel);
	    main.add(calculationPanel);
	    
	    givensPanel.add(new JLabel("Angular Velocity: " + this.calculations.getAngularVelocity() + " rad/s"));
	    givensPanel.add(new JLabel("Linear Velocity: " + this.calculations.getLinearVelocity() + " m/s"));
	    givensPanel.add(new JLabel("Angle: " + this.calculations.getAngle() + " rad"));
	    givensPanel.add(new JLabel("Radius: " + this.calculations.getRadius() + " m"));
	    givensPanel.add(new JLabel("Time: " + this.calculations.getTime() + " s"));
	    givensPanel.add(new JLabel("Arc Length: " + this.calculations.getArcLength() + " m"));
	    
	    calculationPanel.add(new JLabel(this.calculations.getWantedVariable() + " = " + this.calculations.getEquation()));
	    calculationPanel.add(new JLabel(this.calculations.getWantedVariable() + " = " + this.calculations.getValueEquation()));
	    calculationPanel.add(new JLabel(this.calculations.getWantedVariable() + " = " + this.calculations.getResult()));
	    
	    JButton saveButton = new JButton("Save");
	    calculationPanel.add(saveButton);
	    
	    return main;
	}
	
	private void registerControllers(){
		
	}
}
