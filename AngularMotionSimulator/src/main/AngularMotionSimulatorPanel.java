package main;

import java.awt.*;
import javax.swing.*;

import animation.Animation;
import animation.AnimationPanel;
import inputs.Input;
import options.Options;
import options.OptionsMenuBar;

/**AngularMotionSimulatorPanel
 * The main Container of the program
 * @author BRYAN KRISTIONO
 * @since 7/12/2015
 */
public class AngularMotionSimulatorPanel extends JPanel {
	private AnimationPanel animationPanel;
	private OptionsMenuBar menuBar;
	
	private Animation animation;
	private Input input;
	private Options options;
	
	public AngularMotionSimulatorPanel(Input input) {
		super();
		this.input = input;
		this.animation = input.getAnimation();
		this.options = input.getOptions();
		createComponents();
		createPanel();
	}
	
	public void createComponents() {
		animationPanel = new AnimationPanel(animation);
		menuBar = new OptionsMenuBar(options);
	}
	
	public void createPanel() {
		this.setLayout(new BorderLayout());
		
		animationPanel.setBorder(BorderFactory.createTitledBorder("Animation"));
		this.add(animationPanel, BorderLayout.CENTER);
		this.add(menuBar, BorderLayout.NORTH);
	}
	
	public void update() {
		
	}
}
