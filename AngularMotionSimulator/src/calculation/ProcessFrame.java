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
	    this.setContentPane(this.layoutView());
	}
	
	public void update(){
		
	}
	
	private JPanel layoutView(){
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
	    
	    givensPanel.add(new JLabel("Angular Velocity: null rad/s"));
	    givensPanel.add(new JLabel("Linear Velocity: null m/s"));
	    givensPanel.add(new JLabel("Angle: 1 rad"));
	    givensPanel.add(new JLabel("Radius: 1 m"));
	    givensPanel.add(new JLabel("Time: 1 s"));
	    givensPanel.add(new JLabel("Arc Length: null m"));
	    
	    calculationPanel.add(new JLabel("w = angle / time"));
	    calculationPanel.add(new JLabel("w = 1 / 1"));
	    calculationPanel.add(new JLabel("w = 1 rad/s"));
	    
	    JButton saveButton = new JButton("Save");
	    calculationPanel.add(saveButton);
	    
	    return main;
	}
	
	private void registerControllers(){
		
	}
}
