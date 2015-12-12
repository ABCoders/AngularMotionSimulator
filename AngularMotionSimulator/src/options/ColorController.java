/**
 * 
 */
package options;

import javax.swing.JColorChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import animation.Animation;

/**
 * @author BRYAN KRISTIONO
 *
 */
public class ColorController implements ChangeListener {
	private Animation animation;
	private JColorChooser colorChooser;
	
	public ColorController(JColorChooser colorChooser, Animation animation) {
		this.animation = animation;
		this.colorChooser = colorChooser;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		System.out.println("TEST");
		animation.setColor(colorChooser.getColor());
	}
}
