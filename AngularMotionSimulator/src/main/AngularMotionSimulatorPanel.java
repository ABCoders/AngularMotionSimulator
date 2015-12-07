package main;

import java.awt.*;
import javax.swing.*;

import animation.Animation;
import animation.AnimationPanel;

/**AngularMotionSimulatorPanel
 * The main Container of the program
 * @author BRYAN KRISTIONO
 * @since 7/12/2015
 */
public class AngularMotionSimulatorPanel extends JPanel {
	private AnimationPanel animationPanel;
	private Animation animation;
	
	public AngularMotionSimulatorPanel() {
		super();
		animation = new Animation();
		createComponents();
		createPanel();
	}
	
	public void createComponents() {
		animationPanel = new AnimationPanel(animation);
	}
	
	public void createPanel() {
		this.setLayout(new BorderLayout());
		
		animationPanel.setBorder(BorderFactory.createTitledBorder("Animation"));
		this.add(animationPanel, BorderLayout.CENTER);
	}
	
	public void update() {
		
	}
}
