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

	private ArrayList<String> equation;
	private ArrayList<String> valueEquation;
	private ArrayList<String> result;

	public Calculations(Input input){
		this.input = input;
		equation = new ArrayList<String>();
		valueEquation = new ArrayList<String>();
		result = new ArrayList<String>();
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
			this.angularVelocity = this.findAngularVelocity(null);
		if(this.linearVelocity == 0)
			this.linearVelocity = this.findLinearVelocity(null);
		if(this.radius == 0)
			this.radius = this.findRadius(null);
	}
	
	private void makeEquations(String variable1, String operation, String variable2, double result){
		String equation = variable1 + " " + operation + " " + variable2;
		String valueEquation = "";
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
		valueEquation = valueEquation + operation;
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
		
		this.equation.add(equation);
		this.valueEquation.add(valueEquation);
		this.result.add("" + result);
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
				solution = this.findTime(null);
				break;
			case("Arc Length"):
				solution = this.findArcLength(null);
				break;
			case("Angle"):
				solution = this.findAngle(null);
				break;
		}
		if(solution == 0){
			return false;
		}
		return true;
	}

	private double findAngularVelocity(String yetToCheck){
		double angularVelocity = this.angularVelocity;
			if(this.angle != 0 && this.time != 0){
				angularVelocity = this.angle/this.time;
				this.makeEquations("Angle", "/", "Time", angularVelocity);
			}
			else if(this.linearVelocity != 0 && this.radius != 0){
				angularVelocity = this.linearVelocity/this.radius;
				this.makeEquations("Linear Velocity", "/", "Radius", angularVelocity);
			}
		
			else if(this.angle == 0 && this.time != 0 && !yetToCheck.equals("Angle")){
				double angle = this.findAngle("Angular Velocity");
				if(angle != 0){
					this.angle = angle;
					angularVelocity = this.angle/this.time;
					this.makeEquations("Angle", "/", "Time", angularVelocity);
				}
			}
			else if(this.angle != 0 && this.time == 0 && !yetToCheck.equals("Time")){
				double time = this.findTime("Angular Velocity");
				if(time != 0){
					this.time = time;
					angularVelocity = this.angle/this.time;
					this.makeEquations("Angle", "/", "Time", angularVelocity);
				}
			}
			
			else if(this.linearVelocity == 0 && this.radius != 0 && !yetToCheck.equals("Linear Velocity")){
				double linearVelocity = this.findLinearVelocity("Angular Velocity");
				if(linearVelocity != 0){
					this.linearVelocity = linearVelocity;
					angularVelocity = this.linearVelocity/this.radius;
					this.makeEquations("Linear Velocity", "/", "Radius", angularVelocity);
				}
			}
			else if(this.linearVelocity != 0 && this.radius == 0 && !yetToCheck.equals("Radius")){
				double radius = this.findRadius("Angular Velocity");
				if(radius != 0){
					this.radius = radius;
					angularVelocity = this.linearVelocity/this.radius;
					this.makeEquations("Linear Velocity", "/", "Radius", angularVelocity);
				}
			}
		return angularVelocity;
	}

	private double findLinearVelocity(String yetToCheck){
		double linearVelocity = this.linearVelocity;
		
			if(this.angularVelocity != 0 && this.radius != 0){
				linearVelocity = this.angularVelocity * this.radius;
				this.makeEquations("Angular Velocity", "x", "Radius", linearVelocity);
				
			}
			else if(this.arcLength != 0 && this.time != 0){
				linearVelocity = this.arcLength/this.time;
				this.makeEquations("Arc Length", "/", "Time", linearVelocity);
				
			}
			
			else if(this.angularVelocity == 0 && this.radius != 0 && !yetToCheck.equals("Angular Velocity")){
				double angularVelocity = this.findAngularVelocity("Linear Velocity");
				if(angularVelocity != 0){
					this.angularVelocity = angularVelocity;
					linearVelocity = this.angularVelocity * this.radius;
					this.makeEquations("Angular Velocity", "x", "Radius", linearVelocity);
					
				}
			}
			else if(this.angularVelocity != 0 && this.radius == 0 && !yetToCheck.equals("Radius")){
				double radius = this.findRadius("Linear Velocity");
				if(radius != 0){
					this.radius = radius;
					linearVelocity = this.angularVelocity * this.radius;
					this.makeEquations("Angular Velocity", "x", "Radius", linearVelocity);
					
				}
			}
			
			else if(this.arcLength == 0 && this.time != 0 && !yetToCheck.equals("Arc Length")){
				double arcLength = this.findArcLength("Linear Velocity");
				if(arcLength != 0){
					this.arcLength = arcLength;
					linearVelocity = this.arcLength/this.time;
					this.makeEquations("Arc Length", "/", "Time", linearVelocity);
					
				}
			}
			else if(this.arcLength != 0 && this.time == 0 && !yetToCheck.equals("Time")){
				double time = this.findTime("Linear Velocity");
				if(time != 0){
					this.time = time;
					linearVelocity = this.arcLength/this.time;
					this.makeEquations("Arc Length", "/", "Time", linearVelocity);
					
				}
			}
		return linearVelocity;
	}

	private double findRadius(String yetToCheck){
		double radius = this.radius;
		
			if(this.linearVelocity != 0 && this.angularVelocity != 0){
				radius = this.linearVelocity/this.angularVelocity;
				this.makeEquations("Linear Velocity", "/", "Angular Velocity", radius);
				
			}
			else if(this.arcLength != 0 && this.angle != 0){
				radius = this.arcLength/this.angle;
				this.makeEquations("Arc Length", "/", "Angle", radius);
				
			}
			
			else if(this.linearVelocity == 0 && this.angularVelocity != 0 && !yetToCheck.equals("Linear Velocity")){
				double linearVelocity = this.findLinearVelocity("Radius");
				if(linearVelocity != 0){
					this.linearVelocity = linearVelocity;
					radius = this.linearVelocity/this.angularVelocity;
					this.makeEquations("Linear Velocity", "/", "Angular Velocity", radius);
					
				}
			}
			else if(this.linearVelocity != 0 && this.angularVelocity == 0 && !yetToCheck.equals("Angular Velocity")){
				double angularVelocity = this.findAngularVelocity("Radius");
				if(angularVelocity != 0){
					this.angularVelocity = angularVelocity;
					radius = this.linearVelocity/this.angularVelocity;
					this.makeEquations("Linear Velocity", "/", "Angular Velocity", radius);
					
				}
			}
			
			else if(this.arcLength == 0 && this.angle != 0 && !yetToCheck.equals("Arc Length")){
				double arcLength = this.findArcLength("Radius");
				if(arcLength != 0){
					this.arcLength = arcLength;
					radius = this.arcLength/this.angle;
					this.makeEquations("Arc Length", "/", "Angle", radius);
					
				}
			}
			else if(this.arcLength != 0 && this.angle == 0 && !yetToCheck.equals("Angle")){
				double angle = this.findAngle("Radius");
				if(angle != 0){
					this.angle = angle;
					radius = this.arcLength/this.angle;
					this.makeEquations("Arc Length", "/", "Angle", radius);
					
				}
			}
		return radius;
	}

	private double findArcLength(String yetToCheck){
		double arcLength = this.arcLength;
		
			if(this.angle != 0 && this.radius != 0){
				arcLength = this.angle * this.radius;
				this.makeEquations("Angle", "x", "Radius", arcLength);
				
			}
			else if(this.linearVelocity != 0 && this.time != 0){
				arcLength = this.linearVelocity * this.time;
				this.makeEquations("Linear Velocity", "x", "Time", arcLength);
				
			}
			
			else if(this.angle == 0 && this.radius != 0 && !yetToCheck.equals("Angle")){
				double angle = this.findAngle("Arc Length");
				if(angle != 0){
					this.angle = angle;
					arcLength = this.angle * this.radius;
					this.makeEquations("Angle", "x", "Radius", arcLength);
					
				}
			}
			else if(this.angle != 0 && this.radius == 0 && !yetToCheck.equals("Radius")){
				double radius = this.findRadius("Arc Length");
				if(radius != 0){
					this.radius = radius;
					arcLength = this.angle * this.radius;
					this.makeEquations("Angle", "x", "Radius", arcLength);
					
				}
			}
			
			else if(this.linearVelocity == 0 && this.time != 0 && !yetToCheck.equals("Linear Velocity")){
				double linearVelocity = this.findLinearVelocity("Arc Length");
				if(linearVelocity != 0){
					this.linearVelocity = linearVelocity;
					arcLength = this.linearVelocity * this.time;
					this.makeEquations("Linear Velocity", "x", "Time", arcLength);
					
				}
			}
			else if(this.linearVelocity != 0 && this.time == 0 && !yetToCheck.equals("Time")){
				double time = this.findTime("Arc Length");
				if(time != 0){
					this.time = time;
					arcLength = this.linearVelocity * this.time;
					this.makeEquations("Linear Velocity", "x", "Time", arcLength);
					
				}
			}
		return arcLength;
	}

	private double findTime(String yetToCheck){
		double time = this.time;
		
			if(this.angularVelocity != 0 && this.angle != 0){
				time = this.angularVelocity/this.angle;
				this.makeEquations("Anglular Velocity", "/", "Angle", time);
				
			}
			else if(this.arcLength != 0 && this.linearVelocity != 0){
				time = this.arcLength/this.linearVelocity;
				this.makeEquations("Arc Length", "/", "Linear Velocity", time);
				
			}
			
			else if(this.angularVelocity == 0 && this.angle != 0 && !yetToCheck.equals("Angular Velocity")){
				double angularVelocity = this.findAngularVelocity("Time");
				if(angularVelocity != 0){
					this.angularVelocity = angularVelocity;
					time = this.angularVelocity/this.angle;
					this.makeEquations("Anglular Velocity", "/", "Angle", time);
					
				}
			}
			else if(this.angularVelocity != 0 && this.angle == 0 && !yetToCheck.equals("Angle")){
				double angle = this.findAngle("Time");
				if(angle != 0){
					this.angle = angle;
					time = this.angularVelocity/this.angle;
					this.makeEquations("Anglular Velocity", "/", "Angle", time);
					
				}
			}
			
			else if(this.arcLength == 0 && this.linearVelocity != 0 && !yetToCheck.equals("Arc Length")){
				double arcLength = this.findArcLength("Time");
				if(arcLength != 0){
					this.arcLength = arcLength;
					time = this.angularVelocity/this.angle;
					this.makeEquations("Arc Length", "/", "Linear Velocity", time);
					
				}
			}
			else if(this.arcLength != 0 && this.linearVelocity == 0 && !yetToCheck.equals("Linear Velocity")){
				double linearVelocity = this.findLinearVelocity("Time");
				if(linearVelocity != 0){
					this.linearVelocity = linearVelocity;
					time = this.angularVelocity/this.angle;
					this.makeEquations("Arc Length", "/", "Linear Velocity", time);
					
				}
			}
		return time;
	}

	private double findAngle(String yetToCheck){
		double angle = this.angle;
		
			if(this.angularVelocity != 0 && this.time != 0){
				angle = this.angularVelocity * this.time;
				this.makeEquations("Angular Velocity", "x", "Time", angle);
				
			}
			else if(this.arcLength != 0 && this.radius != 0){
				angle = this.arcLength/this.radius;
				this.makeEquations("Arc Length", "/", "Radius", angle);
				
			}
			
			else if(this.angularVelocity == 0 && this.time != 0 && !yetToCheck.equals("Angular Velocity")){
				double angularVelocity = this.findAngularVelocity("Angle");
				if(angularVelocity != 0){
					this.angularVelocity = angularVelocity;
					angle = this.angularVelocity * this.time;
					this.makeEquations("Angular Velocity", "x", "Time", angle);
					
				}
			}
			else if(this.angularVelocity != 0 && this.time == 0 && !yetToCheck.equals("Time")){
				double time = this.findTime("Angle");
				if(time != 0){
					this.time = time;
					angle = this.angularVelocity * this.time;
					this.makeEquations("Angular Velocity", "x", "Time", angle);
					
				}
			}
			
			else if(this.arcLength == 0 && this.radius != 0 && !yetToCheck.equals("Arc Length")){
				double arcLength = this.findArcLength("Angle");
				if(arcLength != 0){
					this.arcLength = arcLength;
					angle = this.arcLength/this.radius;
					this.makeEquations("Arc Length", "/", "Radius", angle);
					
				}
			}
			else if(this.arcLength != 0 && this.radius == 0 && !yetToCheck.equals("Radius")){
				double radius = this.findRadius("Angle");
				if(radius != 0){
					this.radius = radius;
					angle = this.arcLength/this.radius;
					this.makeEquations("Arc Length", "/", "Radius", angle);
					
				}
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
					for(int x = 0; x < this.equation.size(); x++){
						output.println(this.wantedVariable + " = " + this.equation.get(x));
						output.println(this.wantedVariable + " = " + this.valueEquation.get(x));
						output.println(this.wantedVariable + " = " + this.result.get(x));
						output.println();
					}
					output.close();
			}
			catch(FileNotFoundException ex){}
		}
		else if(working == JFileChooser.CANCEL_OPTION){
			
		}
		else if(working == JFileChooser.ERROR_OPTION){
			
		}
	}

	public ArrayList<String> getEquation(){
		return this.equation;
	}

	public ArrayList<String> getValueEquation() {
		return valueEquation;
	}

	public ArrayList<String> getResult(){
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
