package options;

import java.awt.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

public class HelpFrame extends JFrame{
	//Use text document or this?
	//Make text look more appealing
	private JTextArea text;
	
	public HelpFrame() {
		super("HELP");
		this.createComponents();
		this.createFrame();
	}
	
	private void createComponents() {
		text = new JTextArea("Angular Motion Simulator - By Amritpal Aujla, Bryan Kristiono, Cindy Zhao - abcCoders\n\n"
				+ "Program Features:\n\n "
				+ "The Angular Motion Simulator program is meant to demonstrate Angular Motion using an animated circle and values for various variables that the user inputs. It can also calculate for a specific variable the user wants and output the process work for the calculations."
				+ "\n\nThe circle itself spins according to the angular velocity, sizes itself according to the radius, and moves along the window according to the linear velocity. The animation can be controlled, to a degree, by the sliders provided above the animation and the variable values that the user chooses to input - like angular velocity or radius.\n\n"
				+ "The inputs have a button to calculate using the current values given inside them, which output the process work inside another window."
				+ "\n\n\tThere is an options menu bar at the top of the program to adjust the circle appearance, save. load, or ask for help."
				+ "\n\nProgram GUI and Instructions:"
				+ "\n\nOptions Bar:"
				+ "\nThe options bar has three items, the Options, the Help, and the Preferences."
				+ "\nThe Options item can be hovered over to show two more items, save and load. The save item can be clicked to save the current information about the animation for later use. The load item can be clicked to load a previous animation that has been saved."
				+ "\nThe Help item can be clicked to pop up a frame that shows this instruction manual and program description in order to help use the program."
				+ "\nThe Preferences menu item can clicked to change the appearance of the circle."
				+ "\n\nAnimation:"
				+ "\nThe animation is a spinning and moving wheel. It has a slider that can move the animation forwards or backward in time by dragging its marker and a slider that can resize the animation smaller or bigger by dragging its marker. The animation has a button to pause itself. When it is clicked and the animation is paused, the user can click anywhere around it to form a line from the middle of the circle to that point, ending with a dot at the point the user clicked on. The line and dot spins and moves along with the wheel animation when the play button - beside the pause button - is clicked. There is a clear button beside the play button which erases the dot and makes it possible for the user to add another one in the same or different place."
				+ "\n\nInputting Values:"
				+ "\nThe inputs pane is below the animation, and has many fields. Right below the animation is the wanted field. This field has a drop down list where the user can pick a variable that he/she wishes to calculate for. Beside that drop down list is an area for text where the result for the calculation will appear after the calculate button in the input field is pressed."
				+ "\n\nThe input fields are below the wanted fields. The input fields have a drop down list to pick a given variable that the user wished to enter a value for. Right beside that is a text area where the user can enter the values for the variable type that was picked. To the right of the text area is a button, marked with an X, to delete the input field. Below the input fields are two buttons. One says ‘Add’, and is used to add more input fields. The other says ‘Calculate’, and is used to try and find the value for the wanted variable based on all the given variables that have been entered."
				+ "\n\nThe user must enter the numbers correctly into the text areas, If the given variables dont match up or numbers are not entered correctly, a dialog box will pop up telling the user to re enter their variables."
				+ "\n\nCalculation Process Work:"
				+ "\nThe process work window contains the process work for the calculations used to get the variable value that the user wants. It has three lines and a button. The first line is the equation used to get the result. The second line is the same equation, only with the variable types replaced by the actual values the user inputs. The last line is the answer, the result of the calculations and the value of the user’s wanted variable."
				+ "\nThe button, saying ‘Save’, is used to save the calculation to a text file where it can be viewed again on the user’s leisure.");
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setEditable(false);
	}
	
	private void createFrame() {
		JPanel panel = new JPanel(new BorderLayout());
		JScrollPane scroller = new JScrollPane(text);
		scroller.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel.setPreferredSize(new Dimension(500, 500));
		panel.add(scroller, BorderLayout.CENTER);
		this.setContentPane(panel);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.setLocation(400, 50);
		this.pack();
	}
	
	public void update(){
		
	}
}
