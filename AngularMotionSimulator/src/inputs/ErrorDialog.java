/**
 * 
 */
package inputs;

import javax.swing.*;
import java.awt.*;

/**
 * @author Bryan Kristiono
 * @since 09/12/15
 */
public class ErrorDialog extends JDialog {
	//attributes
	private JFrame frame; //Frame for error dialog
	private JPanel panel; //Panel containing error text
	private String message; //Text describing error
	private JLabel label; //Label containing error message

	/**
	 * Main constructor
	 * @param jFrame - frame for dialog
	 * @param errorMessage - message describing error
	 */
	public ErrorDialog(JFrame jFrame, String errorMessage) {
		super(jFrame, "Error", true);
		this.frame = jFrame;
		this.message = "<html>" + errorMessage + "</html>";
		this.createComponents();
		this.createDialog();
	}

	/**
	 * Creates panel and label for error message
	 */
	private void createComponents() {
		panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(350, 125));
		label = new JLabel(message, SwingConstants.CENTER);
	}

	/**
	 * Adds text to panel and creates frame
	 */
	private void createDialog() {
		panel.add(label, BorderLayout.CENTER);
		this.setContentPane(panel);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(frame);
		this.setResizable(false);
		this.setVisible(true);
	}
}
