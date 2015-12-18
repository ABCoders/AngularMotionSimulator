package options;

import java.util.ArrayList;

import javax.swing.*;

import inputs.AnswerMachinePanel;
import inputs.Input;
import inputs.InputFieldPanel;
import inputs.WantedFieldPanel;

public class OptionsMenuBar extends JMenuBar {
	private Input input;
	private AnswerMachinePanel answerMachinePanel;
	private JMenu file;
	private JMenu other;
	private JMenuItem saveItem;
	private JMenuItem loadItem;
	private JMenuItem helpItem;
	private JMenuItem colorItem;
	
	public OptionsMenuBar(Input input, AnswerMachinePanel answerMachinePanel) {
		super();
		this.input = input;
		createComponents();
		createMenuBar();
		this.answerMachinePanel = answerMachinePanel;
		registerControllers();
	}
	
	private void createComponents() {
		file = new JMenu("File");
		other = new JMenu("Other");
		saveItem = new JMenuItem("Save As");
		loadItem = new JMenuItem("Load");
		helpItem = new JMenuItem("Help");
		colorItem = new JMenuItem("Change Color");
	}
	
	private void createMenuBar() {
		file.add(saveItem);
		file.add(loadItem);
		other.add(helpItem);
		other.add(colorItem);
		this.add(file);
		this.add(other);
	}
	
	private void registerControllers() {
		WantedFieldPanel wantedField = answerMachinePanel.getWantedFieldPanel();
		ArrayList<InputFieldPanel> inputFields = answerMachinePanel.getInputPanel().getFields();
//		System.out.println("MB" + inputFields + "/" + wantedField);
		OptionsController controller = new OptionsController(input, this, inputFields, wantedField);
		saveItem.addActionListener(controller);
		loadItem.addActionListener(controller);
		helpItem.addActionListener(controller);
		colorItem.addActionListener(controller);
	}
}
