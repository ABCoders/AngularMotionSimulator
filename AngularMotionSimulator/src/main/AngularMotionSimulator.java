package main;

import javax.swing.*;
import inputs.Input;

/**AngularMotionSimulator
 * A Main project software that creates an animation of a spinning circle
 * With user-inputted variables that could change the circle's properties
 * @author Bryan Kristiono
 * @since 28/12/2015
 */
public class AngularMotionSimulator {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Angular Motion Simulator");
		Input input = new Input();
		AngularMotionSimulatorPanel contentPane = new AngularMotionSimulatorPanel(input);
		
		//Sets the frame to appear visually
		frame.setContentPane(contentPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon(AngularMotionSimulator.class.getResource("Logo.png"));
		frame.setIconImage(icon.getImage());
		
		frame.setVisible(true);
		frame.pack();
		
		frame.setLocationRelativeTo(null);
	}
} 