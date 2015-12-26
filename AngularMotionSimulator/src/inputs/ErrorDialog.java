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
	private JFrame frame;
	private JPanel panel;
	private String message;
	private JTextArea text;

	public ErrorDialog(JFrame jFrame, String errorMessage) {
		super(jFrame, "Error", true);
		this.frame = jFrame;
		this.message = "<html><ALIGN CENTER>" + errorMessage + "</ALIGN></html>";
		this.createComponents();
		this.createDialog();
	}

	private void createComponents() {
		panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(300, 100));
		text = new JTextArea(message);
	}

	private void createDialog() {
		panel.add(text, BorderLayout.CENTER);
		this.setContentPane(panel);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(frame);
		this.setResizable(false);
		this.setVisible(true);
	}
}
