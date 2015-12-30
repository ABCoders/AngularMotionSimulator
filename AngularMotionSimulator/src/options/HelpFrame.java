package options;

import java.awt.*;
import java.io.*;

import javax.swing.*;

/**
 * @author BRYAN KRISTIONO, AMRITPAL AUJLA
 * @since 12/12/2015
 */
public class HelpFrame extends JFrame {
	private JEditorPane text;		//The component that handles styled text documents
	
	/**
	 * Creates a new HelpFrame. Contains needed components and pops up on screen.
	 */
	public HelpFrame() {
		super("Help - Angular Motion Simulator");
		this.createComponents();
		this.createFrame();
	}	
	
	/**
	 * Initialize and set values to all needed components.
	 */
	private void createComponents() {
		text = new JEditorPane();
		text.setEditable(false);
		java.net.URL url = HelpFrame.class.getResource("help.html");
		try {
			text.setPage(url);
		} 
		catch (IOException e) {}
	}
	
	/**
	 * Set up the components in the frame layout.
	 */
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
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
}
