package inputs;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InputPanel extends JPanel{
	
	private Input input;
	private ArrayList<InputFieldPanel> field;
	
	public InputPanel(Input input) {
		this.input = input;
//		input.setGUI(this);
		this.createComponents();
		this.createPanel();
	}
	
	private void createComponents() {
		field = new ArrayList<InputFieldPanel>();
		for (int i=0; i<input.getNumberFields(); i++) {
			field.add(new InputFieldPanel(input, i));
		}
	}
	
	private void createPanel() {
		this.setLayout(new GridLayout(field.size(), 1));
		this.setPreferredSize(new Dimension(500, 200));
		for (int i=0; i<field.size(); i++) {
			this.add(field.get(i));
		}
	}
	
	public ArrayList<InputFieldPanel> getFields() {
		return field;
	}
	
	public void update(boolean state) {
		System.out.println(input.getNumberFields() + " " + field.size());
		while(input.getNumberFields()>field.size()) {
			System.out.println("Adding a Field");
			field.add(new InputFieldPanel(input, field.size()));
			this.add(field.get(field.size()-1));
		}
		while(input.getNumberFields()<field.size()) {
			System.out.println("Removing Field" + " " + input.getRemovedField());
			field.remove(input.getRemovedField());
			this.remove(input.getRemovedField());
			for(int i=input.getRemovedField(); i<field.size(); i++) {
				field.get(i).setPosition(i);
			}
		}
		
		if (state) {
			int variable = 0;
			for(int i=0; i<field.size(); i++) {
				for(int k = variable; k<Input.VARIABLES.length; k++) {
					double value = input.getVariableValue(k);
					if(value!=0) {
						field.get(i).setSelectedVariable(k);
						field.get(i).setValue(value);
						variable = k+1;
						break;
					}
				}
			}
		}
		
		if (field.size() > 3) {
			this.setLayout(new GridLayout (3, 2));
		}
		else {
			this.setLayout(new GridLayout(field.size(), 1));
		}
		this.repaint();
		this.revalidate();
		this.validate();
	}
}
