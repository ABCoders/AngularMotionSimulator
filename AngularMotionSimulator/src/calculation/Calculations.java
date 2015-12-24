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
			this.angularVelocity = this.findAngularVelocity(true);
		if(this.linearVelocity == 0)
			this.linearVelocity = this.findLinearVelocity(true);
		if(this.radius == 0)
			this.radius = this.findRadius(true);
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
				solution = this.findTime(true);
				break;
			case("Arc Length"):
				solution = this.findArcLength(true);
				break;
			case("Angle"):
				solution = this.findAngle(true);
				break;
		}
		if(solution == 0){
			return false;
		}
		return true;
	}

	private double findAngularVelocity(boolean yetToCheck){
		double angularVelocity = this.angularVelocity;
		if(yetToCheck){
			if(this.angle != 0 && this.time != 0){
				angularVelocity = this.angle/this.time;
				this.makeEquations("Angle", "/", "Time", angularVelocity);
				yetToCheck = false;
			}
			else if(this.linearVelocity != 0 && this.radius != 0){
				angularVelocity = this.linearVelocity/this.radius;
				this.makeEquations("Linear Velocity", "/", "Radius", angularVelocity);
				yetToCheck = false;
			}
		
			else if(this.angle == 0 && this.time != 0){
				double angle = this.findAngle(yetToCheck);
				if(angle != 0){
					this.angle = angle;
					angularVelocity = this.angle/this.time;
					this.makeEquations("Angle", "/", "Time", angularVelocity);
					yetToCheck = false;
				}
			}
			else if(this.angle != 0 && this.time == 0){
				double time = this.findTime(yetToCheck);
				if(time != 0){
					this.time = time;
					angularVelocity = this.angle/this.time;
					this.makeEquations("Angle", "/", "Time", angularVelocity);
					yetToCheck = false;
				}
			}
			
			else if(this.linearVelocity == 0 && this.radius != 0){
				double linearVelocity = this.findLinearVelocity(yetToCheck);
				if(linearVelocity != 0){
					this.linearVelocity = linearVelocity;
					angularVelocity = this.linearVelocity/this.radius;
					this.makeEquations("Linear Velocity", "/", "Radius", angularVelocity);
					yetToCheck = false;
				}
			}
			else if(this.linearVelocity != 0 && this.radius == 0){
				double radius = this.findRadius(yetToCheck);
				if(radius != 0){
					this.radius = radius;
					angularVelocity = this.linearVelocity/this.radius;
					this.makeEquations("Linear Velocity", "/", "Radius", angularVelocity);
					yetToCheck = false;
				}
			}
		}
		return angularVelocity;
	}

	private double findLinearVelocity(boolean yetToCheck){
		double linearVelocity = this.linearVelocity;
		if(yetToCheck){
			if(this.angularVelocity != 0 && this.radius != 0){
				linearVelocity = this.angularVelocity * this.radius;
				this.makeEquations("Angular Velocity", "x", "Radius", linearVelocity);
				yetToCheck = false;
			}
			else if(this.arcLength != 0 && this.time != 0){
				linearVelocity = this.arcLength/this.time;
				this.makeEquations("Arc Length", "/", "Time", linearVelocity);
				yetToCheck = false;
			}
			
			else if(this.angularVelocity == 0 && this.radius != 0){
				double angularVelocity = this.findAngularVelocity(yetToCheck);
				if(angularVelocity != 0){
					this.angularVelocity = angularVelocity;
					linearVelocity = this.angularVelocity * this.radius;
					this.makeEquations("Angular Velocity", "x", "Radius", linearVelocity);
					yetToCheck = false;
				}
			}
			else if(this.angularVelocity != 0 && this.radius == 0){
				double radius = this.findRadius(yetToCheck);
				if(radius != 0){
					this.radius = radius;
					linearVelocity = this.angularVelocity * this.radius;
					this.makeEquations("Angular Velocity", "x", "Radius", linearVelocity);
					yetToCheck = false;
				}
			}
			
			else if(this.arcLength == 0 && this.time != 0){
				double arcLength = this.findArcLength(yetToCheck);
				if(arcLength != 0){
					this.arcLength = arcLength;
					linearVelocity = this.arcLength/this.time;
					this.makeEquations("Arc Length", "/", "Time", linearVelocity);
					yetToCheck = false;
				}
			}
			else if(this.arcLength != 0 && this.time == 0){
				double time = this.findTime(yetToCheck);
				if(time != 0){
					this.time = time;
					linearVelocity = this.arcLength/this.time;
					this.makeEquations("Arc Length", "/", "Time", linearVelocity);
					yetToCheck = false;
				}
			}
		}
		return linearVelocity;
	}

	private double findRadius(boolean yetToCheck){
		double radius = this.radius;
		if(yetToCheck){
			if(this.linearVelocity != 0 && this.angularVelocity != 0){
				radius = this.linearVelocity/this.angularVelocity;
				this.makeEquations("Linear Velocity", "/", "Angular Velocity", radius);
				yetToCheck = false;
			}
			else if(this.arcLength != 0 && this.angle != 0){
				radius = this.arcLength/this.angle;
				this.makeEquations("Arc Length", "/", "Angle", radius);
				yetToCheck = false;
			}
			
			else if(this.linearVelocity == 0 && this.angularVelocity != 0){
				double linearVelocity = this.findLinearVelocity(yetToCheck);
				if(linearVelocity != 0){
					this.linearVelocity = linearVelocity;
					radius = this.linearVelocity/this.angularVelocity;
					this.makeEquations("Linear Velocity", "/", "Angular Velocity", radius);
					yetToCheck = false;
				}
			}
			else if(this.linearVelocity != 0 && this.angularVelocity == 0){
				double angularVelocity = this.findAngularVelocity(yetToCheck);
				if(angularVelocity != 0){
					this.angularVelocity = angularVelocity;
					radius = this.linearVelocity/this.angularVelocity;
					this.makeEquations("Linear Velocity", "/", "Angular Velocity", radius);
					yetToCheck = false;
				}
			}
			
			else if(this.arcLength == 0 && this.angle != 0){
				double arcLength = this.findArcLength(yetToCheck);
				if(arcLength != 0){
					this.arcLength = arcLength;
					radius = this.arcLength/this.angle;
					this.makeEquations("Arc Length", "/", "Angle", radius);
					yetToCheck = false;
				}
			}
			else if(this.arcLength != 0 && this.angle == 0){
				double angle = this.findAngle(yetToCheck);
				if(angle != 0){
					this.angle = angle;
					radius = this.arcLength/this.angle;
					this.makeEquations("Arc Length", "/", "Angle", radius);
					yetToCheck = false;
				}
			}
		}
		return radius;
	}

	private double findArcLength(boolean yetToCheck){
		double arcLength = this.arcLength;
		if(yetToCheck){
			if(this.angle != 0 && this.radius != 0){
				arcLength = this.angle * this.radius;
				this.makeEquations("Angle", "x", "Radius", arcLength);
				yetToCheck = false;
			}
			else if(this.linearVelocity != 0 && this.time != 0){
				arcLength = this.linearVelocity * this.time;
				this.makeEquations("Linear Velocity", "x", "Time", arcLength);
				yetToCheck = false;
			}
			
			else if(this.angle == 0 && this.radius != 0){
				double angle = this.findAngle(yetToCheck);
				if(angle != 0){
					this.angle = angle;
					arcLength = this.angle * this.radius;
					this.makeEquations("Angle", "x", "Radius", arcLength);
					yetToCheck = false;
				}
			}
			else if(this.angle != 0 && this.radius == 0){
				double radius = this.findRadius(yetToCheck);
				if(radius != 0){
					this.radius = radius;
					arcLength = this.angle * this.radius;
					this.makeEquations("Angle", "x", "Radius", arcLength);
					yetToCheck = false;
				}
			}
			
			else if(this.linearVelocity == 0 && this.time != 0){
				double linearVelocity = this.findLinearVelocity(yetToCheck);
				if(linearVelocity != 0){
					this.linearVelocity = linearVelocity;
					arcLength = this.linearVelocity * this.time;
					this.makeEquations("Linear Velocity", "x", "Time", arcLength);
					yetToCheck = false;
				}
			}
			else if(this.linearVelocity != 0 && this.time == 0){
				double time = this.findTime(yetToCheck);
				if(time != 0){
					this.time = time;
					arcLength = this.linearVelocity * this.time;
					this.makeEquations("Linear Velocity", "x", "Time", arcLength);
					yetToCheck = false;
				}
			}
		}
		return arcLength;
	}

	private double findTime(boolean yetToCheck){
		double time = this.time;
		if(yetToCheck){
			if(this.angularVelocity != 0 && this.angle != 0){
				time = this.angularVelocity/this.angle;
				this.makeEquations("Anglular Velocity", "/", "Angle", time);
				yetToCheck = false;
			}
			else if(this.arcLength != 0 && this.linearVelocity != 0){
				time = this.arcLength/this.linearVelocity;
				this.makeEquations("Arc Length", "/", "Linear Velocity", time);
				yetToCheck = false;
			}
			
			else if(this.angularVelocity == 0 && this.angle != 0){
				double angularVelocity = this.findAngularVelocity(yetToCheck);
				if(angularVelocity != 0){
					this.angularVelocity = angularVelocity;
					time = this.angularVelocity/this.angle;
					this.makeEquations("Anglular Velocity", "/", "Angle", time);
					yetToCheck = false;
				}
			}
			else if(this.angularVelocity != 0 && this.angle == 0){
				double angle = this.findAngle(yetToCheck);
				if(angle != 0){
					this.angle = angle;
					time = this.angularVelocity/this.angle;
					this.makeEquations("Anglular Velocity", "/", "Angle", time);
					yetToCheck = false;
				}
			}
			
			else if(this.arcLength == 0 && this.linearVelocity != 0){
				double arcLength = this.findArcLength(yetToCheck);
				if(arcLength != 0){
					this.arcLength = arcLength;
					time = this.angularVelocity/this.angle;
					this.makeEquations("Arc Length", "/", "Linear Velocity", time);
					yetToCheck = false;
				}
			}
			else if(this.arcLength != 0 && this.linearVelocity == 0){
				double linearVelocity = this.findLinearVelocity(yetToCheck);
				if(linearVelocity != 0){
					this.linearVelocity = linearVelocity;
					time = this.angularVelocity/this.angle;
					this.makeEquations("Arc Length", "/", "Linear Velocity", time);
					yetToCheck = false;
				}
			}
		}
		return time;
	}

	private double findAngle(boolean yetToCheck){
		double angle = this.angle;
		if(yetToCheck){
			if(this.angularVelocity != 0 && this.time != 0){
				angle = this.angularVelocity * this.time;
				this.makeEquations("Angular Velocity", "x", "Time", angle);
				yetToCheck = false;
			}
			else if(this.arcLength != 0 && this.radius != 0){
				angle = this.arcLength/this.radius;
				this.makeEquations("Arc Length", "/", "Radius", angle);
				yetToCheck = false;
			}
			
			else if(this.angularVelocity == 0 && this.time != 0){
				double angularVelocity = this.findAngularVelocity(yetToCheck);
				if(angularVelocity != 0){
					this.angularVelocity = angularVelocity;
					angle = this.angularVelocity * this.time;
					this.makeEquations("Angular Velocity", "x", "Time", angle);
					yetToCheck = false;
				}
			}
			else if(this.angularVelocity != 0 && this.time == 0){
				double time = this.findTime(yetToCheck);
				if(time != 0){
					this.time = time;
					angle = this.angularVelocity * this.time;
					this.makeEquations("Angular Velocity", "x", "Time", angle);
					yetToCheck = false;
				}
			}
			
			else if(this.arcLength == 0 && this.radius != 0){
				double arcLength = this.findArcLength(yetToCheck);
				if(arcLength != 0){
					this.arcLength = arcLength;
					angle = this.arcLength/this.radius;
					this.makeEquations("Arc Length", "/", "Radius", angle);
					yetToCheck = false;
				}
			}
			else if(this.arcLength != 0 && this.radius == 0){
				double radius = this.findRadius(yetToCheck);
				if(radius != 0){
					this.radius = radius;
					angle = this.arcLength/this.radius;
					this.makeEquations("Arc Length", "/", "Radius", angle);
					yetToCheck = false;
				}
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
