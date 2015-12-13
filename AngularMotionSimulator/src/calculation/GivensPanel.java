package calculation;

import java.awt.GridLayout;

import javax.swing.*;

public class GivensPanel extends JPanel{
	private Calculations calculations;
	private JLabel angularVelocity;
	private JLabel linearVelocity;
	private JLabel radius;
	private JLabel time;
	private JLabel angle;
	private JLabel arcLength;
	
	public GivensPanel(Calculations calculations){
		super();
		this.calculations = calculations;
		this.createComponents();
		this.createPanel();
	}
	
	private void createComponents(){
		angularVelocity = new JLabel("Angular Velocity: " + GivensPanel.changeIfZero(this.calculations.getAngularVelocity()) + " rad/s");
		linearVelocity = new JLabel("Linear Velocity: " + GivensPanel.changeIfZero(this.calculations.getLinearVelocity()) + " m/s");
		radius = new JLabel("Angle: " + GivensPanel.changeIfZero(this.calculations.getAngle()) + " rad");
		time = new JLabel("Radius: " + GivensPanel.changeIfZero(this.calculations.getRadius()) + " m");
		angle = new JLabel("Time: " + GivensPanel.changeIfZero(this.calculations.getTime()) + " s");
		arcLength = new JLabel("Arc Length: " + GivensPanel.changeIfZero(this.calculations.getArcLength()) + " m");
	}
	
	private void createPanel(){
		this.setBorder(BorderFactory.createTitledBorder("Given Values"));
	    this.setLayout(new GridLayout(3, 3));
	    
		this.add(this.angularVelocity);
		this.add(this.linearVelocity);
		this.add(this.radius);
		this.add(this.time);
		this.add(this.angle);
		this.add(this.arcLength);
	}
	
	private static String changeIfZero(double num){
		if(num == 0.0)
			return "---";
		else
			return Double.toString(num);
	}

}
