//package and imports
package calculation;
import java.util.*;

import javax.swing.*;

import inputs.ErrorDialog;
import inputs.Input;

/* Calculations
 * The model of the calculations - contains all variables from input, calculates for the wanted variable, and provides needed values for animation
 * @author Amritpal Aujla
 * @since 25/12/2015 */
public class Calculations{
	//Attributes
	private ProcessFrame processFrame;                     //the view that shows the process for the calculation for the wanted variable
	private Input input;                                   //the input model that the measurements are acquired from

	private TreeMap<String, Double> variables;             //the collection of variable names (the keys) and their corresponding values (the values)
	private String wantedVariable;                         //the variable the user wants to calculate

	private String equation;                               //the equation for the wanted variable, made up of the variable names and operations
	private String valueEquation;                          //the equation for the wanted variable, made up of the variable values and operations
	private String result;                                 //the solution to the equation for the wanted variable

	/* Default Constructor - initializes the input attribute
	 * @param input - the input model with the measurements that Calculations needs */
	public Calculations(Input input){
		this.input = input;
	}
	
	/* Sets the view for this model
	 * @param frame - the ProcessFrame that is set as the view for this model */
	public void setGUI(ProcessFrame frame){
		this.processFrame = frame;
	}

	/* Gets the many measurements and variables from the input model and sets them to the collection in this Calculations */
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
	
	/* Finds angular velocity, linear velocity, and radius for Animation to use to generate the animation */
	private void setAnimationVariables(){
		//if the variable's value is 0 (has not been input), then calculate it to give to animation
		if(this.variables.get("Angular Velocity") == 0)
			this.variables.put("Angular Velocity", this.findAngularVelocity());
		if(this.variables.get("Linear Velocity") == 0)
			this.variables.put("Linear Velocity", this.findLinearVelocity());
		if(this.variables.get("Radius") == 0)
			this.variables.put("Radius", this.findRadius());
	}
	
	/* decides the value for the equation, valueEquation, and result attributes
	 * @param variable1 - the first variable in the equation
	 * @param operation1 - the first operation character in the equation evaluating the first and second variable
	 * @param variable2 - the second variable in the equation
	 * @param operation2 - the second operation character in the equation evaluating the second and third variable
	 * @param variable3 - the third variable in the equation
	 * @param result - the solution to the equation */
	private void decideEquation(String variable1, String operation1, String variable2, String operation2, String variable3, double result){
		boolean addBracketAfter = false;                        //whether the equation should add a bracket after one of the variables
		//making the word equation
		this.equation = variable1 + " " + operation1 + " " + variable2 + " " + operation2 + " " + variable3;
		
		//making the equation with values replacing words
		String valueEquation = "";
		//for the first variable, removing the bracket from the word, and replacing the word with a value
		if(variable1.contains("(")){
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
		//adding the operator
		valueEquation = valueEquation + operation1;
		
		//replacing variable 2's brackets and replacing the words with values
		if(variable2.contains("(")){
			valueEquation = valueEquation + "(";
			variable2 = variable2.replace("(", "");
		}
		else if(variable2.contains(")")){
			addBracketAfter = true;
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
		//adding brackets after the value if there is a need for it
		if(addBracketAfter){
			valueEquation = valueEquation + ")";
			addBracketAfter = false;
		}
		//adding second operator and continuing the bracket-removing and word-replacing with variable 3
		valueEquation = valueEquation + operation2;
		if(variable3.contains(")")){
			addBracketAfter = true;
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
		//adding bracket after variable 3 if there is a need for it
		if(addBracketAfter)
			valueEquation = valueEquation + ")";
		
		//setting the values for the valueEquation and result attributes
		this.valueEquation = valueEquation;
		this.result = "" + result;
	}
	
	/* Calculates for the wanted variable and returns true after doing so, returning false if it is unable to calculate
	 * @return whether the calculation has finished properly */
	public boolean calculate(){
		//calculating for a certain wanted variable
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
		//if the solution could not be calculated
		if(solution == 0){
			return false;
		}
		return true;
	}

	/* finds angular velocity using various equations relating to Angular Motion
	 * @return angularVelocity - the calculated angular velocity if the calculation has finished properly, and the current angular velocity if it has not */
	private double findAngularVelocity(){
		//gets the current angular velocity to return if a value cannot be calculated for
		double angularVelocity = this.variables.get("Angular Velocity");
		
		//if the user enters certain variables, the program is able to calculate angular velocity with them and then make their equations
		//aV = angle / time if the user enters angle and time
		if(this.variables.get("Angle") != 0 && this.variables.get("Time") != 0){
			angularVelocity = this.variables.get("Angle")/this.variables.get("Time");
			this.decideEquation("Angle", "/", "Time", "", "", angularVelocity);
		}
		//aV = linV / radius if the user enters linear velocity and radius
		else if(this.variables.get("Linear Velocity") != 0 && this.variables.get("Radius") != 0){
			angularVelocity = this.variables.get("Linear Velocity")/this.variables.get("Radius");
			this.decideEquation("Linear Velocity", "/", "Radius", "", "", angularVelocity);
		}
		//aV = (arc length / radius) / linV if the user enters arc length, time, and radius
		else if(this.variables.get("Arc Length") != 0 && this.variables.get("Time") != 0 && this.variables.get("Radius")!= 0){
			angularVelocity = (this.variables.get("Arc Length")/this.variables.get("Radius"))/this.variables.get("Time");
			this.decideEquation("(Arc Length", "/", "Radius)", "/", "Time", angularVelocity);
		}
		//aV = angle / (arc length / linV) if the user enters angle, arc length, and linear velocity
		else if(this.variables.get("Angle") != 0 && this.variables.get("Arc Length") != 0 && this.variables.get("Linear Velocity") != 0){
			angularVelocity = this.variables.get("Angle")/(this.variables.get("Arc Length")/this.variables.get("Linear Velocity"));
			this.decideEquation("Angle", "/", "(Arc Length", "/", "Linear Velocity)", angularVelocity);
		}
		
		return angularVelocity;
	}

	/* finds linear velocity using various equations relating to Angular Motion
	 * @return linearVelocity - the calculated linear velocity if the calculation has finished properly, and the current linear velocity if it has not */
	private double findLinearVelocity(){
		//gets the current linear velocity to return if a value cannot be calculated for
		double linearVelocity = this.variables.get("Linear Velocity");
		
		//if the user enters certain variables, the program is able to calculate linear velocity with them and then make their equations
		//linV = angular velocity x radius if the user enters angular velocity and radius
		if(this.variables.get("Angular Velocity") != 0 && this.variables.get("Radius") != 0){
			linearVelocity = this.variables.get("Angular Velocity") * this.variables.get("Radius");
			this.decideEquation("Angular Velocity", "x", "Radius", "", "", linearVelocity);
		}
		//linV = arc length / time if the user enters arc length and time
		else if(this.variables.get("Arc Length") != 0 && this.variables.get("Time") != 0){
			linearVelocity = this.variables.get("Arc Length")/this.variables.get("Time");
			this.decideEquation("Arc Length", "/", "Time", "", "", linearVelocity);
		}
		//linV = angular velocity x (radius / angle) if the user enters angular velocity, arc length, and angle
		else if(this.variables.get("Angular Velocity") != 0 && this.variables.get("Arc Length") != 0 && this.variables.get("Angle") != 0){
				linearVelocity = this.variables.get("Angular Velocity") * (this.variables.get("Arc Length") / this.variables.get("Angle"));
				this.decideEquation("Angular Velocity", "x", "(Arc Length", "/", "Angle)", linearVelocity);
		}
		//linV = (angle / time) x radius if the user enters angle, radius, and time
		else if(this.variables.get("Angle") != 0 && this.variables.get("Radius") == 0 && this.variables.get("Time") != 0){
				linearVelocity = (this.variables.get("Angle") / this.variables.get("Time")) * this.variables.get("Radius");
				this.decideEquation("(Angle", "/", "Time)", "x", "Radius", linearVelocity);
		}
		return linearVelocity;
	}

	/* finds radius using various equations relating to Angular Motion
	 * @return radius - the calculated radius if the calculation has finished properly, and the current radius if it has not */
	private double findRadius(){
		//gets the current radius to return if a value cannot be calculated for
		double radius = this.variables.get("Radius");
		
		//if the user enters certain variables, the program is able to calculate radius with them and then make their equations
		//radius = linear velocity / angular velocity if the user enters linear velocity and angular velocity
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

	/* finds arc length using various equations relating to Angular Motion
	 * @return arc length - the calculated arc length if the calculation has finished properly, and the current arc length if it has not */
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
					this.decideEquation("Angular Velocity", "x", "Time", "x", "Radius", arcLength);
			}
			else if(this.variables.get("Angle") != 0 && this.variables.get("Linear Velocity") != 0 && this.variables.get("Angular Velocity") != 0){
					arcLength = this.variables.get("Linear Velocity") * (this.variables.get("Angle") / this.variables.get("Angular Velocity"));
					this.decideEquation("Linear Velocity", "x", "(Angle", "/", "Angular Velocity)", arcLength);
			}
		return arcLength;
	}

	/* finds time using various equations relating to Angular Motion
	 * @return time - the calculated time if the calculation has finished properly, and the current time if it has not */
	private double findTime(){
		double time = this.variables.get("Time");
			if(this.variables.get("Angular Velocity") != 0 && this.variables.get("Angle") != 0){
				time = this.variables.get("Angular Velocity")/this.variables.get("Angle");
				this.decideEquation("Angular Velocity", "/", "Angle", "", "", time);
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

	/* finds angle using various equations relating to Angular Motion
	 * @return angle - the calculated angle if the calculation has finished properly, and the current angle if it has not */
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
	
	/* Returns the equation attribute
	 * @return the equation */
	public String getEquation(){
		return this.equation;
	}

	/* Returns the valueEquation attribute
	 * @return the valueEquation - the equation with values instead of words */
	public String getValueEquation() {
		return valueEquation;
	}

	/* Returns the result attribute
	 * @return the reult - the solution to the calculation */
	public String getResult(){
		return this.result;
	}

	/* Returns the wantedVariable attribute
	 * @return the wantedVariable */
	public String getWantedVariable() {
		return wantedVariable;
	}

	/* Returns the variables attribute
	 * @return the variables - the TreeMap of all the measurements and their values */
	public TreeMap<String, Double> getVariables(){
		return this.variables;
	}

	/* Updates Calculations, setting variables, calculating for the wanted variable, making the view, etc. */
	public void update() {
		this.setVariables();
		if(this.calculate()){
			try{
				this.processFrame.dispose();
			}
			catch(NullPointerException ex){
			}
			this.processFrame = new ProcessFrame(this, this.input, true);
			this.setAnimationVariables();
			this.input.getAnimation().updateModel();
		}
		else{
			this.processFrame = new ProcessFrame(this, this.input, false);
			new ErrorDialog((JFrame)SwingUtilities.getWindowAncestor(processFrame), "Cannot calculate using current givens. " + this.giveRelevantError());
		}
	}
	
	/* Returns the variables attribute
	 * @return the variables - the TreeMap of all the measurements and their values */
	private String giveRelevantError(){
		switch(this.wantedVariable){
			case("Angular Velocity"):
				return "To calculate Angular Velocity, you need either of these sets of measurements:<p></p> <p>Angle and Time <p>Linear Velocity and Radius <p>Arc Length, Angle, and Linear Velocity <p>Time, Radius, and Arc Length</p></p></p></p>";
			case("Linear Velocity"):
				return "To calculate Linear Velocity, you need either of these sets of measurements:<p></p> <p>Arc Length and Time <p>Angular Velocity and Radius <p>Arc Length, Angle, and Angular Velocity <p>Time, Radius, and Angle</p></p></p></p>";
			case("Radius"):
				return "To calculate Radius, you need either of these sets of measurements:<p></p> <p>Linear Velocity and Angular Velocity <p>Arc Length and Angle <p>Arc Length, Time, and Angular Velocity <p>Time, Angle, and Linear Velocity</p></p></p></p>";
			case("Time"):
				return "To calculate Time, you need either of these sets of measurements:<p></p> <p>Angular Velocity and Angle <p>Linear Velocity and Arc Length <p>Radius, Angle, and Linear Velocity <p>Angular Velocity, Radius, and Arc Length</p></p></p></p>";
			case("Arc Length"):
				return "To calculate Arc Length you need either of these sets of measurements:<p></p> <p>Time and Linear Velocity <p>Angle and Radius <p>Radius, Time, and Angular Velocity <p>Angular Velocity, Angle, and Angular Velocity</p></p></p></p>";
			case("Angle"):
				return "To calculate Angle, you need either of these sets of measurements:<p></p> <p>Angular Velocity and Time <p>Radius and Arc Length <p>Arc Length, Angular Velocity, and Linear Velocity <p>Time, Radius, and Linear Velocity</p></p></p></p>";
			default:
				return "";
		}
	}
}
