/**AngularMotionSimulator
 * A Main project software that creates an animation of a spinning circle
 * With user-inputted variables that could change the circle's properties
 * 
 */

package main;

import java.awt.*;
import javax.swing.*;

import inputs.Input;

public class AngularMotionSimulator {

	public static void main(String[] args) {
		AngularMotionSimulatorPanel contentPane = new AngularMotionSimulatorPanel();
		Input input = new Input();
		JFrame frame = new JFrame("Angular Motion Simulator");
		frame.setContentPane(contentPane);
		
		frame.setVisible(true);
		frame.setSize(new Dimension(700,300));
		frame.setLocation(100,100);
	}
} 