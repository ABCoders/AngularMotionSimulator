/**AngularMotionSimulator
 * A Main project software that creates an animation of a spinning circle
 * With user-inputted variables that could change the circle's properties
 * 
 */

package main;

import java.awt.*;
import javax.swing.*;

public class AngularMotionSimulator {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Angular Motion Simulator");
		frame.setContentPane(new JPanel());
		
		frame.setVisible(true);
		frame.setSize(new Dimension(500,500));
		frame.setLocation(100,100);
	}
} 