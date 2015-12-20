package calculation;

import java.awt.GridLayout;

import javax.swing.*;

import inputs.Input;

public class GivensPanel extends JPanel{
	private Input input;
	private JLabel angularVelocity;
	private JLabel linearVelocity;
	private JLabel radius;
	private JLabel time;
	private JLabel angle;
	private JLabel arcLength;
	
	public GivensPanel(Input input){
		super();
		this.input = input;
		this.createComponents();
		this.createPanel();
	}
	
	private void createComponents(){
		angularVelocity = new JLabel("Angular Velocity: " + GivensPanel.changeIfZero(this.input.getAngularVelocity()) + " rad/s");
		linearVelocity = new JLabel("Linear Velocity: " + GivensPanel.changeIfZero(this.input.getLinearVelocity()) + " m/s");
		radius = new JLabel("Angle: " + GivensPanel.changeIfZero(this.input.getAngle()) + " rad");
		time = new JLabel("Radius: " + GivensPanel.changeIfZero(this.input.getRadius()) + " m");
		angle = new JLabel("Time: " + GivensPanel.changeIfZero(this.input.getTime()) + " s");
		arcLength = new JLabel("Arc Length: " + GivensPanel.changeIfZero(this.input.getArcLength()) + " m");
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
