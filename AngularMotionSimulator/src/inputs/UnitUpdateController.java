//packages and imports
package inputs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** UnitUpdateController
 *  Updates the unit label in input fields when a combo box value changes
 *  @author Amritpal Aujla
 *  @since 1/8/2016
 */
public class UnitUpdateController implements ActionListener {
	
	//attributes
	InputFieldPanel panel;			//the input field panel containing the combo box and unit label
	
	/** Main constructor to set panel to the input field panel
	 *  @param panel - The input field panel that has the combo box and the unit label
	 */
	public UnitUpdateController(InputFieldPanel panel){
		this.panel = panel;
	}

	/** Updates the input field panel
	 *  @param e - the event that has the combo box being acted upon
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		panel.update();
	}

}
