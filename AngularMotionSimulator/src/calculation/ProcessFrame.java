package calculation;

import javax.swing.*;
import java.awt.*;

public class ProcessFrame extends JFrame{
	private Calculations calculations;
	private JPanel mainPanel;
	private GivensPanel givensPanel;
	private CalculationsPanel calcPanel;
	private JButton save;

	public ProcessFrame(Calculations calculations, boolean visible) {
		super();
		this.calculations = calculations;
	    this.createComponents();
	    this.createFrame(visible);
	    this.registerControllers();
	}

	private void createFrame(boolean visible){
		this.setVisible(visible);
	    this.setSize(600,300);
	    
	    this.mainPanel.setLayout(new GridLayout(3, 1));
	    this.mainPanel.add(givensPanel);
	    this.mainPanel.add(calcPanel);
	    this.mainPanel.add(save);
	    
	    this.setContentPane(this.mainPanel);
	}
	
	private void createComponents(){
		this.mainPanel = new JPanel();
		this.givensPanel = new GivensPanel(this.calculations);
	    this.calcPanel = new CalculationsPanel(this.calculations);
	    this.save = new JButton("Save");
	}
	
	private void registerControllers(){
		ProcessSaveController processSaveController = new ProcessSaveController(this.calculations);
		this.save.addActionListener(processSaveController);
	}
	
}