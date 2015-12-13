/**
 * 
 */
package inputs;

import javax.swing.*;
import java.awt.*;

/**
 * @author BRYAN KRISTIONO
 *
 */
public class ErrorDialog extends JDialog {
	private JPanel panel;
	private String message;
	private JLabel label;

	public ErrorDialog(JFrame jFrame, String errorMessage) {
		super(jFrame, "Error", true);
		this.message = errorMessage;
		this.createComponents();
		this.createDialog();
	}

	private void createComponents() {
		panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(300, 100));
		label = new JLabel(message, SwingConstants.CENTER);
	}

	private void createDialog() {
		panel.add(label, BorderLayout.CENTER);
		this.setContentPane(panel);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setLocation(450, 200);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}
}
