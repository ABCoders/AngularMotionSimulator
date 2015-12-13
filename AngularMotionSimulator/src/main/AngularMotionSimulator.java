/**AngularMotionSimulator
 * A Main project software that creates an animation of a spinning circle
 * With user-inputted variables that could change the circle's properties
 * 
 */

package main;

import java.awt.Image;

import javax.swing.*;

import inputs.Input;

public class AngularMotionSimulator {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Angular Motion Simulator");
		Input input = new Input();
		AngularMotionSimulatorPanel contentPane = new AngularMotionSimulatorPanel(input);
		frame.setContentPane(contentPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		frame.setIconImage(new ImageIcon(frame.getClass().getClassLoader().getResource("Logo.jpeg")).getImage());
		
		frame.setVisible(true);
		frame.pack();
		frame.setLocation(100,10);
	}
} 