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
		try{
		text = new JEditorPane();
		text.setPage("files\\help.html");
		}
		catch(IOException ex){
			System.err.println("It didnt work");
		}
		text.setEditable(false);
	}
	
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
		this.setLocation(400, 50);
		this.pack();
	}
}
