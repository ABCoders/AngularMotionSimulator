package calculation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class ProcessSaveController implements ActionListener {
	private JLabel equation;
	private JLabel valueEquation;
	private JLabel result;
	private String wantedVariable;
	
	public ProcessSaveController(JLabel equation, JLabel valueEquation, JLabel result) {
		this.equation = equation;
		this.valueEquation = valueEquation;
		this.result = result;
		this.wantedVariable = this.equation.getText().substring(0, this.equation.getText().indexOf(" ="));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		int working = fileChooser.showSaveDialog(equation.getTopLevelAncestor());
		if(working == JFileChooser.APPROVE_OPTION){
			PrintWriter output = null;
			try{
			    String path = fileChooser.getSelectedFile().getAbsolutePath();
			    if(path.substring(path.length() - 4).equals(".txt")){
			     path = fileChooser.getSelectedFile().getAbsolutePath();
			}
			else
			    path = fileChooser.getSelectedFile().getAbsolutePath() + ".txt";
			    output = new PrintWriter(new File(path));
			    output.println("Calculation for " + this.equation.getText().substring(0, this.equation.getText().indexOf(" =")));
			    output.println(this.wantedVariable + " = " + this.equation);
			    output.println(this.wantedVariable + " = " + this.valueEquation);
			    output.println(this.wantedVariable + " = " + this.result);
			    output.close();
			}
			catch(FileNotFoundException ex){}
		}
	}
}
