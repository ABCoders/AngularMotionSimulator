package inputs;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InputPanel extends JPanel{
	
	private Input input;
	private ArrayList<InputFieldPanel> field;
	
	public InputPanel(Input input) {
		this.input = input;
		this.createComponents();
		this.createPanel();
		this.registerControllers();
	}
	
	private void createComponents() {
		field = new ArrayList<InputFieldPanel>();
		for (int i=0; i<input.getNumberFields(); i++) {
			field.add(new InputFieldPanel(input));
		}
	}
	
	private void createPanel() {
		this.setLayout(new GridLayout(field.size(), 1));
		for (int i=0; i<field.size(); i++) {
			this.add(field.get(i));
		}
	}
	
	private void registerControllers() {
	}
	
	public void update() {
	
	}
}
