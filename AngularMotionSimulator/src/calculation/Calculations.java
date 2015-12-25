package calculation;

import java.io.*;
import java.util.TreeMap;

import javax.swing.*;

import inputs.ErrorDialog;
import inputs.Input;

public class Calculations extends Object{
	private ProcessFrame processFrame;
	private Input input;

	private TreeMap<String, Double> variables;
	private String wantedVariable;

	private String equation;
	private String valueEquation;
	private String result;

	public Calculations(Input input){
		this.input = input;
	}

	public void setGUI(ProcessFrame frame){
		this.processFrame = frame;
	}

	private void setVariables(){
		this.variables = new TreeMap<String, Double>();
		variables.put("Angular Velocity", input.getVariableValue(0));
		variables.put("Linear Velocity", input.getVariableValue(1));
		variables.put("Radius", input.getVariableValue(2));
		variables.put("Arc Length", input.getVariableValue(3));
		variables.put("Time", input.getVariableValue(4));
		variables.put("Angle", input.getVariableValue(5));
		this.wantedVariable = input.getWantedVariable();
	}
	
	private void setAnimationVariables(){
		if(this.variables.get("Angular Velocity") == 0)
			this.variables.put("Angular Velocity", this.findAngularVelocity());
		if(this.variables.get("Linear Velocity") == 0)
			this.variables.put("Linear Velocity", this.findLinearVelocity());
		if(this.variables.get("Radius") == 0)
			this.variables.put("Radius", this.findRadius());
	}
	
	private void decideEquation(String variable1, String operation1, String variable2, String operation2, String variable3, double result){
		this.equation = variable1 + " " + operation1 + " " + variable2 + " " + operation2 + " " + variable3;
		
		String valueEquation = "";
		if(variable1.indexOf("(") > -1){
			valueEquation = "(";
			variable1 = variable1.replace("(", "");
		}
		
		switch(variable1){
			case("Angular Velocity"):
				valueEquation = valueEquation + this.variables.get("Angular Velocity");
				break;
			case("Linear Velocity"):
				valueEquation = valueEquation + this.variables.get("Linear Velocity");
				break;
			case("Radius"):
				valueEquation = valueEquation + this.variables.get("Radius");
				break;
			case("Arc Length"):
				valueEquation = valueEquation + this.variables.get("Arc Length");
				break;
			case("Time"):
				valueEquation = valueEquation + this.variables.get("Time");
				break;
			case("Angle"):
				valueEquation = valueEquation + this.variables.get("Angle");
				break;
		}
		
		valueEquation = valueEquation + operation1;
		if(variable2.indexOf("(") > -1){
			valueEquation = valueEquation + "(";
			variable2 = variable2.replace("(", "");
		}
		else if(variable2.indexOf(")") > -1){
			valueEquation = valueEquation + ")";
			variable2 = variable2.replace(")", "");
		}
		
		switch(variable2){
			case("Angular Velocity"):
				valueEquation = valueEquation + this.variables.get("Angular Velocity");
				break;
			case("Linear Velocity"):
				valueEquation = valueEquation + this.variables.get("Linear Velocity");
				break;
			case("Radius"):
				valueEquation = valueEquation + this.variables.get("Radius");
				break;
			case("Arc Length"):
				valueEquation = valueEquation + this.variables.get("Arc Length");
				break;
			case("Time"):
				valueEquation = valueEquation + this.variables.get("Time");
				break;
			case("Angle"):
				valueEquation = valueEquation + this.variables.get("Angle");
				break;
		}
		
		if(variable3.indexOf(")") > -1){
			valueEquation = valueEquation + ")";
			variable3 = variable3.replace(")", "");
		}
		
		switch(variable3){
			case("Angular Velocity"):
				valueEquation = valueEquation + this.variables.get("Angular Velocity");
				break;
			case("Linear Velocity"):
				valueEquation = valueEquation + this.variables.get("Linear Velocity");
				break;
			case("Radius"):
				valueEquation = valueEquation + this.variables.get("Radius");
				break;
			case("Arc Length"):
				valueEquation = valueEquation + this.variables.get("Arc Length");
				break;
			case("Time"):
				valueEquation = valueEquation + this.variables.get("Time");
				break;
			case("Angle"):
				valueEquation = valueEquation + this.variables.get("Angle");
				break;
		}
		this.valueEquation = valueEquation;
		this.result = "" + result;
	}
	
	public boolean calculate(){
		double solution = 0;
		switch(this.wantedVariable){
			case("Angular Velocity"):
				solution = this.findAngularVelocity();
				break;
			case("Linear Velocity"):
				solution = this.findLinearVelocity();
				break;
			case("Radius"):
				solution = this.findRadius();
				break;
			case("Time"):
				solution = this.findTime();
				break;
			case("Arc Length"):
				solution = this.findArcLength();
				break;
			case("Angle"):
				solution = this.findAngle();
				break;
		}
		if(solution == 0){
			return false;
		}
		return true;
	}

	private double findAngularVelocity(){
		double angularVelocity = this.variables.get("Angular Velocity");
			if(this.variables.get("Angle") != 0 && this.variables.get("Time") != 0){
				System.out.println("It got here");
				angularVelocity = this.variables.get("Angle")/this.variables.get("Time");
				this.decideEquation("Angle", "/", "Time", "", "", angularVelocity);
			}
			else if(this.variables.get("Linear Velocity") != 0 && this.variables.get("Radius") != 0){
				angularVelocity = this.variables.get("Linear Velocity")/this.variables.get("Radius");
				this.decideEquation("Linear Velocity", "/", "Radius", "", "", angularVelocity);
			}
			else if(this.variables.get("Arc Length") != 0 && this.variables.get("Time") != 0 && this.variables.get("Radius")!= 0){
					angularVelocity = (this.variables.get("Arc Length")/this.variables.get("Radius"))/this.variables.get("Time");
					this.decideEquation("(Arc Length", "/", "Radius)", "/", "Time", angularVelocity);
			}
			else if(this.variables.get("Angle") != 0 && this.variables.get("Arc Length") != 0 && this.variables.get("Linear Velocity") != 0){
					angularVelocity = this.variables.get("Angle")/(this.variables.get("Arc Length")/this.variables.get("Linear Velocity"));
					this.decideEquation("Angle", "/", "(Arc Length", "/", "Linear Velocity)", angularVelocity);
			}
		return angularVelocity;
	}

	private double findLinearVelocity(){
		double linearVelocity = this.variables.get("Linear Velocity");
			if(this.variables.get("Angular Velocity") != 0 && this.variables.get("Radius") != 0){
				linearVelocity = this.variables.get("Angular Velocity") * this.variables.get("Radius");
				this.decideEquation("Angular Velocity", "x", "Radius", "", "", linearVelocity);
			}
			else if(this.variables.get("Arc Length") != 0 && this.variables.get("Time") != 0){
				linearVelocity = this.variables.get("Arc Length")/this.variables.get("Time");
				this.decideEquation("Arc Length", "/", "Time", "", "", linearVelocity);
			}
			else if(this.variables.get("Angular Velocity") != 0 && this.variables.get("Arc Length") != 0 && this.variables.get("Angle") != 0){
					linearVelocity = this.variables.get("Angular Velocity") * (this.variables.get("Radius") / this.variables.get("Angle"));
					this.decideEquation("Angular Velocity", "x", "(Radius", "/", "Angle)", linearVelocity);
			}
			else if(this.variables.get("Angle") != 0 && this.variables.get("Radius") == 0 && this.variables.get("Time") != 0){
					linearVelocity = (this.variables.get("Angle") / this.variables.get("Time")) * this.variables.get("Radius");
					this.decideEquation("(Angle", "/", "Time)", "x", "Radius", linearVelocity);
			}
		return linearVelocity;
	}

	private double findRadius(){
		double radius = this.variables.get("Radius");
			if(this.variables.get("Linear Velocity") != 0 && this.variables.get("Angular Velocity") != 0){
				radius = this.variables.get("Linear Velocity")/this.variables.get("Angular Velocity");
				this.decideEquation("Linear Velocity", "/", "Angular Velocity", "", "", radius);
			}
			else if(this.variables.get("Arc Length") != 0 && this.variables.get("Angle") != 0){
				radius = this.variables.get("Arc Length")/this.variables.get("Angle");
				this.decideEquation("Arc Length", "/", "Angle", "", "", radius);
			}
			else if(this.variables.get("Arc Length") != 0 && this.variables.get("Angular Velocity") != 0 && this.variables.get("Time") != 0){
					radius = (this.variables.get("Arc Length") / this.variables.get("Time"))/this.variables.get("Angular Velocity");
					this.decideEquation("(Arc Length", "/", "Time)", "/", "Angular Velocity", radius);
			}
			else if(this.variables.get("Linear Velocity") != 0 && this.variables.get("Time") != 0 && this.variables.get("Angle") != 0){
					radius = this.variables.get("Linear Velocity")/(this.variables.get("Angle") / this.variables.get("Time"));
					this.decideEquation("Linear Velocity", "/", "(Angle", "/", "Time)", radius);
			}
		return radius;
	}

	private double findArcLength(){
		double arcLength = this.variables.get("Arc Length");
			if(this.variables.get("Angle") != 0 && this.variables.get("Radius") != 0){
				arcLength = this.variables.get("Angle") * this.variables.get("Radius");
				this.decideEquation("Angle", "x", "Radius", "", "", arcLength);
			}
			else if(this.variables.get("Linear Velocity") != 0 && this.variables.get("Time") != 0){
				arcLength = this.variables.get("Linear Velocity") * this.variables.get("Time");
				this.decideEquation("Linear Velocity", "x", "Time", "", "", arcLength);
			}
			else if(this.variables.get("Angular Velocity") != 0 && this.variables.get("Radius") != 0 && this.variables.get("Time") != 0){
					arcLength = this.variables.get("Angular Velocity") * this.variables.get("Time") * this.variables.get("Radius");
					this.decideEquation("Anglular Velocity", "x", "Time", "x", "Radius", arcLength);
			}
			else if(this.variables.get("Angle") != 0 && this.variables.get("Linear Velocity") != 0 && this.variables.get("Angular Velocity") != 0){
					arcLength = this.variables.get("Linear Velocity") * (this.variables.get("Angle") / this.variables.get("Angular Velocity"));
					this.decideEquation("Linear Velocity", "x", "(Angle", "/", "Angular Velocity)", arcLength);
			}
		return arcLength;
	}

	private double findTime(){
		double time = this.variables.get("Time");
			if(this.variables.get("Angular Velocity") != 0 && this.variables.get("Angle") != 0){
				time = this.variables.get("Angular Velocity")/this.variables.get("Angle");
				this.decideEquation("Anglular Velocity", "/", "Angle", "", "", time);
			}
			else if(this.variables.get("Arc Length") != 0 && this.variables.get("Linear Velocity") != 0){
				time = this.variables.get("Arc Length")/this.variables.get("Linear Velocity");
				this.decideEquation("Arc Length", "/", "Linear Velocity", "", "", time);
			}
			else if(this.variables.get("Linear Velocity") != 0 && this.variables.get("Angle") != 0 && this.variables.get("Radius") != 0){
					time = this.variables.get("Angle")/(this.variables.get("Linear Velocity")/this.variables.get("Radius"));
					this.decideEquation("Angle", "/", "(Linear Velocity", "/", "Radius)", time);
			}
			else if(this.variables.get("Angular Velocity") != 0 && this.variables.get("Radius") != 0 && this.variables.get("Arc Length") != 0){
					time = (this.variables.get("Arc Length") / this.variables.get("Radius")) / this.variables.get("Angular Velocity");
					this.decideEquation("(Arc Length", "/", "Radius)", "/", "Angular Velocity", time);
			}
		return time;
	}

	private double findAngle(){
		double angle = this.variables.get("Angle");
			if(this.variables.get("Angular Velocity") != 0 && this.variables.get("Time") != 0){
				angle = this.variables.get("Angular Velocity") * this.variables.get("Time");
				this.decideEquation("Angular Velocity", "x", "Time", "", "", angle);
			}
			else if(this.variables.get("Arc Length") != 0 && this.variables.get("Radius") != 0){
				angle = this.variables.get("Arc Length")/this.variables.get("Radius");
				this.decideEquation("Arc Length", "/", "Radius", "", "", angle);
			}
			else if(this.variables.get("Linear Velocity") != 0 && this.variables.get("Time") != 0 && this.variables.get("Radius") != 0){
					angle = (this.variables.get("Linear Velocity") / this.variables.get("Radius")) * this.variables.get("Time");
					this.decideEquation("(Linear Velocity", "/", "Radius)", "x", "Time", angle);
			}
			else if(this.variables.get("Angular Velocity") != 0 && this.variables.get("Arc Length") != 0 && this.variables.get("Linear Velocity") != 0){
					angle = this.variables.get("Angular Velocity") * (this.variables.get("Arc Length") / this.variables.get("Linear Velocity"));
					this.decideEquation("Angular Velocity", "x", "(Arc Length", "/", "Linear Velocity)", angle);
			}
		return angle;
	}

	public void saveProcess(){
		JFileChooser fileChooser = new JFileChooser();
		int working = fileChooser.showSaveDialog(processFrame);
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
			    output.println("Calculations for " + this.wantedVariable);
			    output.println(this.wantedVariable + " = " + this.equation);
			    output.println(this.wantedVariable + " = " + this.valueEquation);
			    output.println(this.wantedVariable + " = " + this.result);
			    output.close();
			}
			catch(FileNotFoundException ex){}
		}
	}

	public String getEquation(){
		return this.equation;
	}

	public String getValueEquation() {
		return valueEquation;
	}

	public String getResult(){
		return this.result;
	}

	/**
	 * @return the wantedVariable
	 */
	public String getWantedVariable() {
		return wantedVariable;
	}

	/**
	 * @return the variables treemap of all the measurements
	 */
	public TreeMap<String, Double> getVariables(){
		return this.variables;
	}

	public void update() {
		this.setVariables();
		if(this.calculate()){
			try{
				this.processFrame.dispose();
			}
			catch(NullPointerException ex){
			}
			this.processFrame = new ProcessFrame(this, true);
			this.setAnimationVariables();
			this.input.getAnimation().updateModel();
		}
		else{
			this.processFrame = new ProcessFrame(this, false);
			new ErrorDialog((JFrame)SwingUtilities.getWindowAncestor(processFrame), "Cannot calculate using current givens");
		}
	}

	public Input getInput(){
		return this.input;
	}

}
