/**
 * 
 */
package inputs;

import javax.swing.*;

/**
 * @author BRYAN KRISTIONO
 *
 */
public class ErrorDialog extends JDialog {
	private JPanel panel;
	private String message;
	private JLabel label;
	
	public ErrorDialog(JFrame frame, String errorMessage) {
		super(frame, "Error", true);
		this.message = errorMessage;
		this.createComponents();
		this.createDialog();
	}
	
	private void createComponents() {
		panel = new JPanel();
		label = new JLabel(message);
	}
	
	private void createDialog() {
		panel.add(label);
		this.setContentPane(panel);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setLocation(400, 50);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}
}
