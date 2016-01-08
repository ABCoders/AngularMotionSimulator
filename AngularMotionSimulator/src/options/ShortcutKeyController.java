/**
 * 
 */
package options;

import java.awt.event.*;

import animation.Animation;
import main.AngularMotionSimulatorPanel;

/** ShortcutKeyController
 *  Gets input from the keyboard and does certain actions like undoing a DrawPoint drag in the program
 *  @author Amritpal Aujla
 *  @since 01/07/2016
 */
public class ShortcutKeyController implements KeyListener{
	
	//attributes
	private AngularMotionSimulatorPanel amsPanel;
	private Animation animation;
	
	/** Default Constructor - sets the panel to get the key input from and the animation to use methods from
	 * @param amsPanel The main panel which the user must be focused on to get the input from
	 * @param animation The animation model to use methods from for the shortcuts
	 */
	public ShortcutKeyController(AngularMotionSimulatorPanel amsPanel, Animation animation){
		this.amsPanel = amsPanel;
		this.animation = animation;
		
		System.out.println(amsPanel.isFocusable());
	}

	/** if a key is pressed, do a certain action based on what key is pressed
	 * @param KeyEvent e The event that signifies a key being pressed
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Key Pressed");
		// if the key pressed is a Ctrl key
		if(e.getModifiers() == KeyEvent.VK_CONTROL){
			System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.print("Key Typed");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("Key Released");
		// if the key pressed is a Ctrl key
		if(e.getModifiers() == KeyEvent.VK_CONTROL){
			System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
		}
	}
	
	
	
}
