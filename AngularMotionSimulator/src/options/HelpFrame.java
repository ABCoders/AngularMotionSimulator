package options;

import java.awt.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

public class HelpFrame extends JFrame{
	//Use text document or this?
	//Make text look more appealing
	private JEditorPane text;
	
	public HelpFrame() {
		super("HELP");
		this.createComponents();
		this.createFrame();
	}
	
	private void createComponents() {
		text = new JEditorPane();
		text.setEditable(false);
		java.net.URL url = HelpFrame.class.getResource("help.html");
		if (url != null) {
            try {
                text.setPage(url);
            } catch (IOException e) {
                System.err.println("Attempted to read a bad URL: " + url);
            }
        } else {
            System.err.println("Couldn't find file: help.html");
        }
	}
	
	private void createFrame() {
		JPanel panel = new JPanel(new BorderLayout());
		JScrollPane scroller = new JScrollPane(text);
		scroller.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel.setPreferredSize(new Dimension(1000, 500));
		panel.add(scroller, BorderLayout.CENTER);
		this.setContentPane(panel);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.setLocation(400, 50);
		this.pack();
	}
}
