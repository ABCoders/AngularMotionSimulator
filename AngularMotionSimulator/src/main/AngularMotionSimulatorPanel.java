package main;

import java.awt.*;
import javax.swing.*;

import animation.AnimationPanel;
import inputs.AnswerMachinePanel;
import inputs.Input;
import options.OptionsMenuBar;

/**AngularMotionSimulatorPanel
 * The main Container of the program
 * @author BRYAN KRISTIONO
 * @since 7/12/2015
 */
public class AngularMotionSimulatorPanel extends JPanel {
	private AnimationPanel animationPanel;
	private AnswerMachinePanel answerPanel;
	private OptionsMenuBar menuBar;
	
	private Input input;
	
	public AngularMotionSimulatorPanel(Input input) {
		super();
		this.input = input;
		createComponents();
		createPanel();
	}
	
	private void createComponents() {
		animationPanel = new AnimationPanel(input.getAnimation());
		answerPanel = new AnswerMachinePanel(input);
		menuBar = new OptionsMenuBar(input);
		answerPanel = new AnswerMachinePanel(input);
	}
	
	private void createPanel() {
		this.setLayout(new BorderLayout());
		
		animationPanel.setBorder(BorderFactory.createTitledBorder("Animation"));
		this.add(animationPanel, BorderLayout.CENTER);
		this.add(menuBar, BorderLayout.NORTH);
		answerPanel.setBorder(BorderFactory.createTitledBorder("Answer Machine"));
		this.add(answerPanel, BorderLayout.SOUTH);
	}
	
	public void update() {
		animationPanel.update();
		answerPanel.update();
	}
}
