package calculation;

import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

import inputs.ErrorDialog;
import inputs.Input;

public class Calculations extends Object{
	private ProcessFrame processFrame;
	private Input input;

	private double angularVelocity;
	private double linearVelocity;
	private double radius;
	private double angle;
	private double time;
	private double arcLength;
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
		this.wantedVariable = input.getWantedVariable();
		this.angularVelocity = input.getAngularVelocity();
		this.linearVelocity = input.getLinearVelocity();
		this.radius = input.getRadius();
		this.angle = input.getAngle();
		this.time = input.getTime();
		this.arcLength = input.getArcLength();

		if(this.angularVelocity == 0)
			this.angularVelocity = this.findAngularVelocity();
		if(this.linearVelocity == 0)
			this.linearVelocity = this.findLinearVelocity();
		if(this.radius == 0)
			this.radius = this.findRadius();
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
				valueEquation = valueEquation + this.angularVelocity;
				break;
			case("Linear Velocity"):
				valueEquation = valueEquation + this.linearVelocity;
				break;
			case("Radius"):
				valueEquation = valueEquation + this.radius;
				break;
			case("Arc Length"):
				valueEquation = valueEquation + this.arcLength;
				break;
			case("Time"):
				valueEquation = valueEquation + this.time;
				break;
			case("Angle"):
				valueEquation = valueEquation + this.angle;
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
				valueEquation = valueEquation + this.angularVelocity;
				break;
			case("Linear Velocity"):
				valueEquation = valueEquation + this.linearVelocity;
				break;
			case("Radius"):
				valueEquation = valueEquation + this.radius;
				break;
			case("Arc Length"):
				valueEquation = valueEquation + this.arcLength;
				break;
			case("Time"):
				valueEquation = valueEquation + this.time;
				break;
			case("Angle"):
				valueEquation = valueEquation + this.angle;
				break;
		}
		
		if(variable3.indexOf(")") > -1){
			valueEquation = valueEquation + ")";
			variable3 = variable3.replace(")", "");
		}
		
		switch(variable3){
			case("Angular Velocity"):
				valueEquation = valueEquation + this.angularVelocity;
				break;
			case("Linear Velocity"):
				valueEquation = valueEquation + this.linearVelocity;
				break;
			case("Radius"):
				valueEquation = valueEquation + this.radius;
				break;
			case("Arc Length"):
				valueEquation = valueEquation + this.arcLength;
				break;
			case("Time"):
				valueEquation = valueEquation + this.time;
				break;
			case("Angle"):
				valueEquation = valueEquation + this.angle;
				break;
		}
		this.valueEquation = valueEquation;
		this.result = "" + result;
	}
	
	public boolean calculate(){
		double solution = 0;
		switch(this.wantedVariable){
			case("Angular Velocity"):
				solution = this.angularVelocity;
				break;
			case("Linear Velocity"):
				solution = this.linearVelocity;
				break;
			case("Radius"):
				solution = this.radius;
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
		double angularVelocity = this.angularVelocity;
			if(this.angle != 0 && this.time != 0){
				angularVelocity = this.angle/this.time;
				this.decideEquation("Angle", "/", "Time", "", "", angularVelocity);
			}
			else if(this.linearVelocity != 0 && this.radius != 0){
				angularVelocity = this.linearVelocity/this.radius;
				this.decideEquation("Linear Velocity", "/", "Radius", "", "", angularVelocity);
			}
			else if(this.arcLength != 0 && this.time != 0 && this.radius!= 0){
					angularVelocity = (this.arcLength/this.radius)/this.time;
					this.decideEquation("(Arc Length", "/", "Radius)", "/", "Time", angularVelocity);
			}
			else if(this.angle != 0 && this.arcLength != 0 && this.linearVelocity != 0){
					angularVelocity = this.angle/(this.arcLength/this.linearVelocity);
					this.decideEquation("Angle", "/", "(Arc Length", "/", "Linear Velocity)", angularVelocity);
			}
		return angularVelocity;
	}

	private double findLinearVelocity(){
		double linearVelocity = this.linearVelocity;
			if(this.angularVelocity != 0 && this.radius != 0){
				linearVelocity = this.angularVelocity * this.radius;
				this.decideEquation("Angular Velocity", "x", "Radius", "", "", linearVelocity);
			}
			else if(this.arcLength != 0 && this.time != 0){
				linearVelocity = this.arcLength/this.time;
				this.decideEquation("Arc Length", "/", "Time", "", "", linearVelocity);
			}
			else if(this.angularVelocity != 0 && this.arcLength != 0 && this.angle != 0){
					linearVelocity = this.angularVelocity * (this.radius / this.angle);
					this.decideEquation("Angular Velocity", "x", "(Radius", "/", "Angle)", linearVelocity);
			}
			else if(this.angle != 0 && this.radius == 0 && this.time != 0){
					linearVelocity = (this.angle / this.time) * this.radius;
					this.decideEquation("(Angle", "/", "Time)", "x", "Radius", linearVelocity);
			}
		return linearVelocity;
	}

	private double findRadius(){
		double radius = this.radius;
			if(this.linearVelocity != 0 && this.angularVelocity != 0){
				radius = this.linearVelocity/this.angularVelocity;
				this.decideEquation("Linear Velocity", "/", "Angular Velocity", "", "", radius);
			}
			else if(this.arcLength != 0 && this.angle != 0){
				radius = this.arcLength/this.angle;
				this.decideEquation("Arc Length", "/", "Angle", "", "", radius);
			}
			else if(this.arcLength != 0 && this.angularVelocity != 0 && this.time != 0){
					radius = (this.arcLength / this.time)/this.angularVelocity;
					this.decideEquation("(Arc Length", "/", "Time)", "/", "Angular Velocity", radius);
			}
			else if(this.linearVelocity != 0 && this.time != 0 && this.angle != 0){
					radius = this.linearVelocity/(this.angle / this.time);
					this.decideEquation("Linear Velocity", "/", "(Angle", "/", "Time)", radius);
			}
		return radius;
	}

	private double findArcLength(){
		double arcLength = this.arcLength;
			if(this.angle != 0 && this.radius != 0){
				arcLength = this.angle * this.radius;
				this.decideEquation("Angle", "x", "Radius", "", "", arcLength);
			}
			else if(this.linearVelocity != 0 && this.time != 0){
				arcLength = this.linearVelocity * this.time;
				this.decideEquation("Linear Velocity", "x", "Time", "", "", arcLength);
			}
			else if(this.angularVelocity != 0 && this.radius != 0 && this.time != 0){
					arcLength = this.angularVelocity * this.time * this.radius;
					this.decideEquation("Anglular Velocity", "x", "Time", "x", "Radius", arcLength);
			}
			else if(this.angle != 0 && this.linearVelocity != 0 && this.angularVelocity != 0){
					arcLength = this.linearVelocity * (this.angle / this.angularVelocity);
					this.decideEquation("Linear Velocity", "x", "(Angle", "/", "Angular Velocity)", arcLength);
			}
		return arcLength;
	}

	private double findTime(){
		double time = this.time;
			if(this.angularVelocity != 0 && this.angle != 0){
				time = this.angularVelocity/this.angle;
				this.decideEquation("Anglular Velocity", "/", "Angle", "", "", time);
			}
			else if(this.arcLength != 0 && this.linearVelocity != 0){
				time = this.arcLength/this.linearVelocity;
				this.decideEquation("Arc Length", "/", "Linear Velocity", "", "", time);
			}
			else if(this.linearVelocity != 0 && this.angle != 0 && radius != 0){
					time = this.angle/(this.linearVelocity/this.radius);
					this.decideEquation("Angle", "/", "(Linear Velocity", "/", "Radius)", time);
			}
			else if(this.angularVelocity != 0 && this.radius != 0 && this.arcLength != 0){
					time = (this.arcLength / this.radius) / this.angularVelocity;
					this.decideEquation("(Arc Length", "/", "Radius)", "/", "Angular Velocity", time);
			}
		return time;
	}

	private double findAngle(){
		double angle = this.angle;
			if(this.angularVelocity != 0 && this.time != 0){
				angle = this.angularVelocity * this.time;
				this.decideEquation("Angular Velocity", "x", "Time", "", "", angle);
			}
			else if(this.arcLength != 0 && this.radius != 0){
				angle = this.arcLength/this.radius;
				this.decideEquation("Arc Length", "/", "Radius", "", "", angle);
			}
			else if(this.linearVelocity != 0 && this.time != 0 && this.radius != 0){
					angle = (this.linearVelocity / this.radius) * this.time;
					this.decideEquation("(Linear Velocity", "/", "Radius)", "x", "Time", angle);
			}
			else if(this.angularVelocity != 0 && this.arcLength != 0 && this.linearVelocity != 0){
					angle = this.angularVelocity * (this.arcLength / this.linearVelocity);
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
	 * @return the angularVelocity
	 */
	public double getAngularVelocity() {
		return angularVelocity;
	}

	/**
	 * @return the linearVelocity
	 */
	public double getLinearVelocity() {
		return linearVelocity;
	}

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @return the angle
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * @return the time
	 */
	public double getTime() {
		return time;
	}

	/**
	 * @return the arcLength
	 */
	public double getArcLength() {
		return arcLength;
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
