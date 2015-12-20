package calculation;

import java.io.*;

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

	private String decideEquation(){
		String equation = null;
		return equation;
	}

	private String decideValueEquation(){
		String valueEquation = null;
		return valueEquation;
	}

	private String decideResult(){
		String result = null;
		return result;
	}

	private double findAngularVelocity(){
		double angularVelocity = this.angularVelocity;
		if(this.angle != 0 && this.time != 0){
			angularVelocity = this.angle/this.time;
			this.equation = "Angle / Time";
			this.valueEquation = this.angle + " / " + this.time;
			this.result = "" + angularVelocity;
		}
		else if(this.angle == 0 && this.time != 0){
			
		}
		else if(this.angle != 0 && this.time == 0){
			
		}
		
		else if(this.linearVelocity != 0 && this.radius != 0){
			angularVelocity = this.linearVelocity/this.radius;
			this.equation = "Linear Velocity / Radius";
			this.valueEquation = this.linearVelocity + " / " + this.radius;
			this.result = "" + angularVelocity;
		}
		else if(this.linearVelocity == 0 && this.radius != 0){
			
		}
		else if(this.linearVelocity != 0 && this.radius == 0){
			
		}
		return angularVelocity;
	}

	private double findLinearVelocity(){
		double linearVelocity = this.linearVelocity;
		if(this.angularVelocity != 0 && this.radius != 0){
			linearVelocity = this.angularVelocity * this.radius;
			this.equation = "Angular Velocity x Radius";
			this.valueEquation = this.angularVelocity + " x " + this.radius;
			this.result = "" + linearVelocity;
		}
		else if(this.angularVelocity == 0 && this.radius != 0){
			
		}
		else if(this.angularVelocity != 0 && this.radius == 0){
			
		}
		
		else if(this.arcLength != 0 && this.time != 0){
			linearVelocity = this.arcLength/this.time;
			this.equation = "Arc Length / Time";
			this.valueEquation = this.arcLength + " / " + this.time;
			this.result = "" + linearVelocity;
		}
		else if(this.arcLength == 0 && this.time != 0){
			
		}
		else if(this.arcLength != 0 && this.time == 0){
			
		}
		return linearVelocity;
	}

	private double findRadius(){
		double radius = this.radius;
		if(this.angularVelocity != 0 && this.linearVelocity != 0){
			radius = this.angularVelocity/this.linearVelocity;
			this.equation = "Anglular Velocity / Linear Velocity";
			this.valueEquation = this.angularVelocity + " / " + this.linearVelocity;
			this.result = "" + radius;
		}
		else if(this.angularVelocity == 0 && this.linearVelocity != 0){
			
		}
		else if(this.angularVelocity != 0 && this.linearVelocity == 0){
			
		}
		
		else if(this.arcLength != 0 && this.angle != 0){
			radius = this.arcLength/this.angle;
			this.equation = "Arc Length / Angle";
			this.valueEquation = this.arcLength + " / " + this.angle;
			this.result = "" + radius;
		}
		else if(this.arcLength == 0 && this.angle != 0){
			
		}
		else if(this.arcLength != 0 && this.angle == 0){
			
		}
		return radius;
	}

	private double findArcLength(){
		double arcLength = this.arcLength;
		if(this.angle != 0 && this.radius != 0){
			arcLength = this.angle * this.radius;
			this.equation = "Angle x Radius";
			this.valueEquation = this.angle + " x " + this.radius;
			this.result = "" + arcLength;
		}
		else if(this.angle == 0 && this.radius != 0){
			
		}
		else if(this.angle != 0 && this.radius == 0){
			
		}
		
		else if(this.linearVelocity != 0 && this.time != 0){
			arcLength = this.linearVelocity * this.time;
			this.equation = "Linear Velocity x Time";
			this.valueEquation = this.linearVelocity + " x " + this.time;
			this.result = "" + arcLength;
		}
		else if(this.linearVelocity == 0 && this.time != 0){
			
		}
		else if(this.linearVelocity != 0 && this.time == 0){
			
		}
		return arcLength;
	}

	private double findTime(){
		double time = this.time;
		if(this.angularVelocity != 0 && this.angle != 0){
			time = this.angularVelocity/this.angle;
			this.equation = "Anglular Velocity / Angle";
			this.valueEquation = this.angularVelocity + " / " + this.angle;
			this.result = "" + time;
		}
		else if(this.angularVelocity == 0 && this.angle != 0){
			
		}
		else if(this.angularVelocity != 0 && this.angle == 0){
			
		}
		
		else if(this.arcLength != 0 && this.linearVelocity != 0){
			time = this.arcLength/this.linearVelocity;
			this.equation = "Arc Length / Linear Velocity";
			this.valueEquation = this.arcLength + " / " + this.linearVelocity;
			this.result = "" + time;
		}
		else if(this.arcLength == 0 && this.linearVelocity != 0){
			
		}
		else if(this.arcLength != 0 && this.linearVelocity == 0){
			
		}
		return time;
	}

	private double findAngle(){
		double angle = this.angle;
		if(this.angularVelocity != 0 && this.time != 0){
			angle = this.angularVelocity * this.time;
			this.equation = "Angular Velocity x Time";
			this.valueEquation = this.angularVelocity + " x " + this.time;
			this.result = "" + angle;
		}
		else if(this.angularVelocity == 0 && this.time != 0){
			
		}
		else if(this.angularVelocity != 0 && this.time == 0){
			
		}
		
		else if(this.arcLength != 0 && this.radius != 0){
			angle = this.arcLength/this.radius;
			this.equation = "Arc Length / Radius";
			this.valueEquation = this.arcLength + " / " + this.radius;
			this.result = "" + angle;
		}
		else if(this.arcLength == 0 && this.radius != 0){
			
		}
		else if(this.arcLength != 0 && this.radius == 0){
			
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
		else if(working == JFileChooser.CANCEL_OPTION){
			
		}
		else if(working == JFileChooser.ERROR_OPTION){
			
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
			this.processFrame.setVisible(false);
			new ErrorDialog((JFrame)SwingUtilities.getWindowAncestor(processFrame), "Cannot calculate using current givens");
		}
	}

	public Input getInput(){
		return this.input;
	}

}
