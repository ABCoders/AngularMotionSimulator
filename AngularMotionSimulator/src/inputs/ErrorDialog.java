package inputs;

import javax.swing.*;
import java.awt.*;

/**
 * A pop window that notifies the user that an error occurred within the program.
 * Notifies what kind of error occurred.
 * @author Bryan Kristiono
 * @since 09/12/15
 */
public class ErrorDialog extends JDialog {
	/*		Attributes		*/
	private JFrame frame; 	//Frame for error dialog
	private JPanel panel; 	//Panel containing error text
	private String message; //Text describing error
	private JLabel label; 	//Label containing error message

	/**
	 * Creates a new instance of the class with required information.
	 * @param jFrame 		The main frame that the dialog is displaying over
	 * @param errorMessage 	The message that describes the error that occurred
	 */
	public ErrorDialog(JFrame jFrame, String errorMessage) {
		super(jFrame, "Error", true);
		this.frame = jFrame;
		this.message = "<html>" + errorMessage + "</html>";
		this.createComponents();
		this.createDialog();
	}

	/**
	 * Creates panel and label for error message.
	 */
	private void createComponents() {
		panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(350, 125));
		label = new JLabel(message, SwingConstants.CENTER);
	}

	/**
	 * Adds text to panel and creates frame.
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
