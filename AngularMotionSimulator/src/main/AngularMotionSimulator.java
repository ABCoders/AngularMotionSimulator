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
		Input input1 = new Input();
		AngularMotionSimulatorPanel pane1 = new AngularMotionSimulatorPanel(input1);
		Input input2 = new Input();
		AngularMotionSimulatorPanel pane2 = new AngularMotionSimulatorPanel(input2);
		JPanel mainPane = new JPanel(new BorderLayout());
		mainPane.add(pane1, BorderLayout.EAST);
		mainPane.add(pane2, BorderLayout.WEST);
		frame.setContentPane(mainPane);
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