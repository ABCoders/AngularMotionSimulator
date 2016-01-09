package main;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

import animation.AnimationPanel;
import inputs.AnswerMachinePanel;
import inputs.Input;
import options.HelpFrame;
import options.OptionsMenuBar;

/**AngularMotionSimulatorPanel
 * The main Container of the program
 * @author BRYAN KRISTIONO
 * @author Amritpal Aujla
 * @since 7/12/2015
 */
public class AngularMotionSimulatorPanel extends JPanel {
	private AnimationPanel animationPanel;		//The view that contains all animation related components
	private AnswerMachinePanel answerPanel;		//The view that contains all answer machine related components
	private OptionsMenuBar menuBar;				//The view that allows users to select various options
	
	private Input input;						//The main model containing needed information
	
	/**
	 * Initializes a new AngularMotionSimulator
	 * @param input
	 */
	public AngularMotionSimulatorPanel(Input input) {
		super();
		this.showSplashScreen();
		this.input = input;
		createComponents();
		createPanel();
		this.showHelpForFirstTime();
	}
	
	/** Opens an instance of HelpFrame only when the user starts the program for the very first time
	 */
	private void showHelpForFirstTime() {
		//getting input from the file
		try {
			File file = new File("ranYet.txt");
			Scanner in = new Scanner(file);
			
			//if file has a 1 in it
			if(in.nextInt() != 0){
				//showing the help
				HelpFrame helpFrame = new HelpFrame();
				Thread t = new Thread(helpFrame);
				t.start();
				//changing the value to a 0
				try{
					PrintWriter out = new PrintWriter(file);
					out.write("0");
					out.close();
					in.close();
				}
				catch(IOException e){}
			}
		}
		catch(Exception e) {System.out.println(e.getMessage());}
	}

	/** Shows a small splash screen image for a few seconds before the rest of the program opens
	 */
	private void showSplashScreen() {
		//makes the window and panel for holding the image and adds the image to a JLabel
		JWindow splashWindow = new JWindow();
		JPanel splashPanel = new JPanel();
		JLabel splashLabel = new JLabel();
		splashLabel.setIcon(new ImageIcon(AngularMotionSimulatorPanel.class.getResource("splashLogo.png")));
		
		//adding image to panel, panel as content frame of window, and setting window properties
		splashPanel.add(splashLabel);
		splashWindow.setContentPane(splashPanel);
		splashWindow.pack();
		splashWindow.setLocationRelativeTo(null);
		splashWindow.setVisible(true);
		
		//making the image splash for a while before disposing of it
		try {
			Thread.sleep(1500);
		}
		catch (InterruptedException e) {}
		splashWindow.dispose();
	}
	
	/**
	 * Initialize and set values to all needed components
	 */
	private void createComponents() {
		animationPanel = new AnimationPanel(input.getAnimation());
		answerPanel = new AnswerMachinePanel(input);
		menuBar = new OptionsMenuBar(input, answerPanel);
	}
	
	/**
	 * Set up the components in the panel layout
	 */
	private void createPanel() {
		this.setLayout(new BorderLayout());
		
		animationPanel.setBorder(BorderFactory.createTitledBorder("Animation"));
		this.add(animationPanel, BorderLayout.CENTER);
		this.add(menuBar, BorderLayout.NORTH);
		answerPanel.setBorder(BorderFactory.createTitledBorder("Answer Machine"));
		this.add(answerPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Updates the panel
	 */
	public void update() {
		animationPanel.update();
		this.requestFocus();
	}
}
