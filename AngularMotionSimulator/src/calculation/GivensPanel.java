//packages and imports
package calculation;
import java.awt.*;
import javax.swing.*;

import inputs.Input;

/** GivensPanel
 *  The view showing the variables and values that the user inputs
 *  @author Amritpal Aujla
 *  @since 26/12/2015
 */
public class GivensPanel extends JPanel{
	//attributes
	private Input input;						//the input model that the given values are acquired from	
	private JLabel angularVelocity;				//the label for the name and value for angular velocity
	private JLabel linearVelocity;				//the label for the name and value for linear velocity
	private JLabel radius;						//the label for the name and value for radius
	private JLabel time;						//the label for the name and value for time
	private JLabel angle;						//the label for the name and value for angle
	private JLabel arcLength;					//the label for the name and value for arc length
	
	/** The Default Constructor - makes a GivensPanel, initializes input, creates all components and creates the panel
	 *  @param input - the input model for the given variable values that the panel needs
	 */
	public GivensPanel(Input input){
		super();
		this.input = input;
		this.createComponents();
		this.createPanel();
	}
	
	/** Makes all of the label components with the appropriate values for their names
	 */
	private void createComponents(){
		angularVelocity = new JLabel("Angular Velocity: " + GivensPanel.changeIfZero(this.input.getAngularVelocity()) + " rad/s");
		linearVelocity = new JLabel("Linear Velocity: " + GivensPanel.changeIfZero(this.input.getLinearVelocity()) + " m/s");
		radius = new JLabel("Angle: " + GivensPanel.changeIfZero(this.input.getAngle()) + " rad");
		time = new JLabel("Radius: " + GivensPanel.changeIfZero(this.input.getRadius()) + " m");
		angle = new JLabel("Time: " + GivensPanel.changeIfZero(this.input.getTime()) + " s");
		arcLength = new JLabel("Arc Length: " + GivensPanel.changeIfZero(this.input.getArcLength()) + " m");
	}
	
	/** Sets the panel's properties and puts all components in their appropriate places
	 */
	private void createPanel(){
		//makes the layout for the givens
		this.setBorder(BorderFactory.createTitledBorder("Given Values"));
	    this.setLayout(new GridLayout(3, 3));
	    
	    //puts all the components into the layout
		this.add(this.angularVelocity);
		this.add(this.linearVelocity);
		this.add(this.radius);
		this.add(this.time);
		this.add(this.angle);
		this.add(this.arcLength);
	}
	
	/** Checks if a number is 0, and returns a three dash line if it is; returns the number in string form if it is not 0
	 *  @param num - the number to be checked
	 *  @return string - either the number transformed into a string or a three dash line based on what the parameter is
	 */
	private static String changeIfZero(double num){
		if(num == 0)
			return "---";
		else
			return Double.toString(num);
	}

}
