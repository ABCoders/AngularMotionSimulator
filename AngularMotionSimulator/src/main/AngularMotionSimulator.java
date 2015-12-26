package main;

import java.awt.*;

import javax.swing.*;
import inputs.Input;

/**AngularMotionSimulator
 * A Main project software that creates an animation of a spinning circle
 * With user-inputted variables that could change the circle's properties
 * 
 */
public class AngularMotionSimulator {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Angular Motion Simulator");
		Input input = new Input();
		AngularMotionSimulatorPanel contentPane = new AngularMotionSimulatorPanel(input);
		frame.setContentPane(contentPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon icon = new ImageIcon(AngularMotionSimulator.class.getResource("LogoTriangle.png"));
		frame.setIconImage(icon.getImage());
		
		frame.setVisible(true);
		frame.pack();
		
		frame.setLocationRelativeTo(null);
		
//		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
//	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
//	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
//	    frame.setLocation(x, y);
	}
} 