//package and imports
package calculation;
import java.util.*;

import javax.swing.*;

import inputs.ErrorDialog;
import inputs.Input;

/** Calculations
 *  The model of the calculations contains all variables from input, calculates for the wanted variable, and provides needed values for animation.
 *  Calculates the value of the wanted variable and the equations for its process work
 *  @author Amritpal Aujla
 *  @author Bryan Kristiono
 *  @since 26/12/2015
 */
public class Calculations{
	//Attributes
	private ProcessFrame processFrame;                     //the view that shows the process for the calculation for the wanted variable
	private Input input;                                   //the input model that the measurements are acquired from

	private TreeMap<String, Double> variables;             //the collection of variable names (the keys) and their corresponding values (the values)
	private String wantedVariable;                         //the variable the user wants to calculate

	private String equation;                               //the equation for the wanted variable, made up of the variable names and operations
	private String valueEquation;                          //the equation for the wanted variable, made up of the variable values and operations
	private String result;                                 //the solution to the equation for the wanted variable

	/** Default Constructor initializes the input attribute
	 *  @param input the input model with the measurements that Calculations needs 
	 */
	public Calculations(Input input){
		this.input = input;
	}

	/** Sets the view for Calculations
	 *  @param frame the ProcessFrame that is set as the view for this model 
	 */
	public void setGUI(ProcessFrame frame){
		this.processFrame = frame;
	}

	/** Gets the many measurements and variables from the input model and sets them to the collection in this Calculations 
	 */
	private void setVariables(){
		//making the array list and setting its values
		this.variables = new TreeMap<String, Double>();
		for(int x = 0; x < Input.VARIABLES.length; x++){
			this.variables.put(Input.VARIABLES[x], input.getVariableValue(x));
		}
		this.wantedVariable = input.getWantedVariable();
	}

	/** Finds angular velocity, linear velocity, and radius for Animation to use to generate the animation 
	 */
	private void setAnimationVariables(){
		//calculating all the variables normally to give to animation
		this.variables.put("Angular Velocity", this.findAngularVelocity());
		this.variables.put("Linear Velocity", this.findLinearVelocity());
		this.variables.put("Radius", this.findRadius());

		//setting the default variables if only angular velocity is given
		if(this.variables.get("Linear Velocity") == 0 && this.variables.get("Radius") == 0 && this.variables.get("Angular Velocity") != 0){
			this.variables.put("Linear Velocity", this.variables.get("Angular Velocity"));
			this.variables.put("Radius", this.findRadius());
		}
		//setting the default variables if only linear velocity is given
		if(this.variables.get("Radius") == 0 && this.variables.get("Angular Velocity") == 0 && this.variables.get("Linear Velocity") != 0){
			this.variables.put("Angular Velocity", this.variables.get("Linear Velocity") / 1);
			this.variables.put("Radius", this.findRadius());
		}
		//setting the default variables if only radius is given
		if(this.variables.get("Linear Velocity") == 0 && this.variables.get("Angular Velocity") == 0 && this.variables.get("Radius") != 0){
			this.variables.put("Angular Velocity", 1/this.variables.get("Radius"));
			this.variables.put("Linear Velocity", this.findLinearVelocity());
		}
	}

	/** decides the value for the equation, valueEquation, and result attributes
	 *  @param variable1 the first variable in the equation
	 *  @param operation1 the first operation character in the equation evaluating the first and second variable
	 *  @param variable2 the second variable in the equation
	 *  @param operation2 the second operation character in the equation evaluating the second and third variable
	 *  @param variable3 the third variable in the equation
	 *  @param result the solution to the equation 
	 */
	private void decideEquation(String variable1, String operation1, String variable2, String operation2, String variable3, double result){
		boolean addBracketAfter = false;                        //whether the equation should add a bracket after one of the variables
		//making the word equation
		this.equation = variable1 + " " + operation1 + " " + variable2 + " " + operation2 + " " + variable3;

		//making the equation with values replacing words
		String valueEquation = "";

		//making brackets appear in front of the equation, replacing the word with the value and adding first operator
		if(variable1.contains("(")){
			valueEquation = "(";
			variable1 = variable1.replace("(", "");
		}
		valueEquation = valueEquation + getRespectiveValue(variable1);
		valueEquation = valueEquation + operation1;

		//replacing variable 2's brackets, replacing the words with values and adding operator
		if(variable2.contains("(")){
			valueEquation = valueEquation + "(";
			variable2 = variable2.replace("(", "");
		}
		else if(variable2.contains(")")){
			addBracketAfter = true;
			variable2 = variable2.replace(")", "");
		}
		valueEquation = valueEquation + getRespectiveValue(variable2);
		if(addBracketAfter){
			valueEquation = valueEquation + ")";
			addBracketAfter = false;
		}
		valueEquation = valueEquation + operation2;

		//adding brackets after variable 3 and replacing variable 3 with value if variable3 is not nothing
		if(!variable3.equals("")){
			if(variable3.contains(")")){
				addBracketAfter = true;
				variable3 = variable3.replace(")", "");
			}
			valueEquation = valueEquation + getRespectiveValue(variable3);
			if(addBracketAfter)
				valueEquation = valueEquation + ")";
		}
		//setting the values for the valueEquation and result attributes
		this.valueEquation = valueEquation;
		this.result = "" + result;
	}

	/** Returns the value that the variable name in the parameter has
	 *  @param variable the name of the variable that the value is to be retrieved for
	 *  @return the value that is to be returned for a certain variable
	 */
	private Double getRespectiveValue(String variable){
		return this.variables.get(variable);
	}

	/** Calculates for the wanted variable and returns true after doing so, returning false if it is unable to calculate
	 *  @return whether the calculation has finished properly 
	 */
	public boolean calculate(){
		//calculating for a certain wanted variable
		double solution = 0;
		if(this.wantedVariable.equalsIgnoreCase(Input.VARIABLES[0]))
			solution = this.findAngularVelocity();
		else if(this.wantedVariable.equalsIgnoreCase(Input.VARIABLES[1]))
			solution = this.findLinearVelocity();
		else if(this.wantedVariable.equalsIgnoreCase(Input.VARIABLES[2]))
			solution = this.findRadius();
		else if(this.wantedVariable.equalsIgnoreCase(Input.VARIABLES[3]))
			solution = this.findTime();
		else if(this.wantedVariable.equalsIgnoreCase(Input.VARIABLES[4]))
			solution = this.findArcLength();
		else if(this.wantedVariable.equalsIgnoreCase(Input.VARIABLES[5]))
			solution = this.findAngle();
		//if the solution could not be calculated
		if(solution == 0){
			return false;
		}
		return true;
	}

	/** finds angular velocity using various equations relating to Angular Motion
	 *  @return the calculated angular velocity if the calculation has finished properly, and the current angular velocity if it has not 
	 */
	private double findAngularVelocity(){
		//gets the current angular velocity to return if a value cannot be calculated for
		double angularVelocity = this.variables.get("Angular Velocity");

		//if the user enters certain variables, the program is able to calculate angular velocity with them and then make their equations
		//angular velocity = angle / time if the user enters angle and time
		if(this.variables.get("Angle") != 0 && this.variables.get("Time") != 0){
			angularVelocity = this.variables.get("Angle")/this.variables.get("Time");
			this.decideEquation("Angle", "/", "Time", "", "", angularVelocity);
		}
		
		//angular velocity = linear velocity / radius if the user enters linear velocity and radius
		else if(this.variables.get("Linear Velocity") != 0 && this.variables.get("Radius") != 0){
			angularVelocity = this.variables.get("Linear Velocity")/this.variables.get("Radius");
			this.decideEquation("Linear Velocity", "/", "Radius", "", "", angularVelocity);
		}
		
		//angular velocity = (arc length / radius) / linear velocity if the user enters arc length, time, and radius
		else if(this.variables.get("Arc Length") != 0 && this.variables.get("Time") != 0 && this.variables.get("Radius")!= 0){
			angularVelocity = (this.variables.get("Arc Length")/this.variables.get("Radius"))/this.variables.get("Time");
			this.decideEquation("(Arc Length", "/", "Radius)", "/", "Time", angularVelocity);
		}
		
		//angular velocity = angle / (arc length / linear velocity) if the user enters angle, arc length, and linear velocity
		else if(this.variables.get("Angle") != 0 && this.variables.get("Arc Length") != 0 && this.variables.get("Linear Velocity") != 0){
			angularVelocity = this.variables.get("Angle")/(this.variables.get("Arc Length")/this.variables.get("Linear Velocity"));
			this.decideEquation("Angle", "/", "(Arc Length", "/", "Linear Velocity)", angularVelocity);
		}

		return angularVelocity;
	}

	/** finds linear velocity using various equations relating to Angular Motion
	 *  @return the calculated linear velocity if the calculation has finished properly, and the current linear velocity if it has not 
	 */
	private double findLinearVelocity(){
		//gets the current linear velocity to return if a value cannot be calculated for
		double linearVelocity = this.variables.get("Linear Velocity");

		//if the user enters certain variables, the program is able to calculate linear velocity with them and then make their equations
		//linear velocity = angular velocity x radius if the user enters angular velocity and radius
		if(this.variables.get("Angular Velocity") != 0 && this.variables.get("Radius") != 0){
			linearVelocity = this.variables.get("Angular Velocity") * this.variables.get("Radius");
			this.decideEquation("Angular Velocity", "x", "Radius", "", "", linearVelocity);
		}
		
		//linear velocity = arc length / time if the user enters arc length and time
		else if(this.variables.get("Arc Length") != 0 && this.variables.get("Time") != 0){
			linearVelocity = this.variables.get("Arc Length")/this.variables.get("Time");
			this.decideEquation("Arc Length", "/", "Time", "", "", linearVelocity);
		}
		
		//linear velocity = angular velocity x (radius / angle) if the user enters angular velocity, arc length, and angle
		else if(this.variables.get("Angular Velocity") != 0 && this.variables.get("Arc Length") != 0 && this.variables.get("Angle") != 0){
			linearVelocity = this.variables.get("Angular Velocity") * (this.variables.get("Arc Length") / this.variables.get("Angle"));
			this.decideEquation("Angular Velocity", "x", "(Arc Length", "/", "Angle)", linearVelocity);
		}
		
		//linear velocity = (angle / time) x radius if the user enters angle, radius, and time
		else if(this.variables.get("Angle") != 0 && this.variables.get("Radius") == 0 && this.variables.get("Time") != 0){
			linearVelocity = (this.variables.get("Angle") / this.variables.get("Time")) * this.variables.get("Radius");
			this.decideEquation("(Angle", "/", "Time)", "x", "Radius", linearVelocity);
		}
		
		return linearVelocity;
	}

	/** finds radius using various equations relating to Angular Motion
	 *  @return the calculated radius if the calculation has finished properly, and the current radius if it has not 
	 */
	private double findRadius(){
		//gets the current radius to return if a value cannot be calculated for
		double radius = this.variables.get("Radius");

		//if the user enters certain variables, the program is able to calculate radius with them and then make their equations
		//radius = linear velocity / angular velocity if the user enters linear velocity and angular velocity
		if(this.variables.get("Linear Velocity") != 0 && this.variables.get("Angular Velocity") != 0){
			radius = this.variables.get("Linear Velocity")/this.variables.get("Angular Velocity");
			this.decideEquation("Linear Velocity", "/", "Angular Velocity", "", "", radius);
		}
		
		//radius = arc length / angle if the user enters arc length and angle
		else if(this.variables.get("Arc Length") != 0 && this.variables.get("Angle") != 0){
			radius = this.variables.get("Arc Length")/this.variables.get("Angle");
			this.decideEquation("Arc Length", "/", "Angle", "", "", radius);
		}
		
		//radius = (arc length / time) / angular velocity if the user enters arc length, angular velocity, and time
		else if(this.variables.get("Arc Length") != 0 && this.variables.get("Angular Velocity") != 0 && this.variables.get("Time") != 0){
			radius = (this.variables.get("Arc Length") / this.variables.get("Time"))/this.variables.get("Angular Velocity");
			this.decideEquation("(Arc Length", "/", "Time)", "/", "Angular Velocity", radius);
		}
		
		//radius = linear velocity / (angle / time) if the user enters linear velocity, angle, and time
		else if(this.variables.get("Linear Velocity") != 0 && this.variables.get("Time") != 0 && this.variables.get("Angle") != 0){
			radius = this.variables.get("Linear Velocity")/(this.variables.get("Angle") / this.variables.get("Time"));
			this.decideEquation("Linear Velocity", "/", "(Angle", "/", "Time)", radius);
		}
		
		return radius;
	}

	/** finds arc length using various equations relating to Angular Motion
	 *  @return the calculated arc length if the calculation has finished properly, and the current arc length if it has not 
	 */
	private double findArcLength(){
		//gets the current arc length to return if a value cannot be calculated for
		double arcLength = this.variables.get("Arc Length");

		//if the user enters certain variables, the program is able to calculate arc length with them and then make their equations
		//arc length = angle x radius if the user enters angle and radius
		if(this.variables.get("Angle") != 0 && this.variables.get("Radius") != 0){
			arcLength = this.variables.get("Angle") * this.variables.get("Radius");
			this.decideEquation("Angle", "x", "Radius", "", "", arcLength);
		}
		
		//arc length = linear velocity x time if the user enters linear velocity and time
		else if(this.variables.get("Linear Velocity") != 0 && this.variables.get("Time") != 0){
			arcLength = this.variables.get("Linear Velocity") * this.variables.get("Time");
			this.decideEquation("Linear Velocity", "x", "Time", "", "", arcLength);
		}
		
		//arc length = angular velocity x time x radius if the user enters angular velocity, time, and radius
		else if(this.variables.get("Angular Velocity") != 0 && this.variables.get("Radius") != 0 && this.variables.get("Time") != 0){
			arcLength = this.variables.get("Angular Velocity") * this.variables.get("Time") * this.variables.get("Radius");
			this.decideEquation("Angular Velocity", "x", "Time", "x", "Radius", arcLength);
		}
		
		//arc length = linear velocity x (angle / angular velocity) if the user enters linear velocity, angle, and angular velocity
		else if(this.variables.get("Angle") != 0 && this.variables.get("Linear Velocity") != 0 && this.variables.get("Angular Velocity") != 0){
			arcLength = this.variables.get("Linear Velocity") * (this.variables.get("Angle") / this.variables.get("Angular Velocity"));
			this.decideEquation("Linear Velocity", "x", "(Angle", "/", "Angular Velocity)", arcLength);
		}
		
		return arcLength;
	}

	/** finds time using various equations relating to Angular Motion
	 *  @return the calculated time if the calculation has finished properly, and the current time if it has not 
	 */
	private double findTime(){
		//gets the current time to return if a value cannot be calculated for
		double time = this.variables.get("Time");

		//if the user enters certain variables, the program is able to calculate for time with them and then make their equations
		//time = angular velocity / angle if the user enters angular velocity and angle
		if(this.variables.get("Angular Velocity") != 0 && this.variables.get("Angle") != 0){
			time = this.variables.get("Angular Velocity")/this.variables.get("Angle");
			this.decideEquation("Angular Velocity", "/", "Angle", "", "", time);
		}
		
		//time = arc length / linear velocity if the user enters arc length and linear velocity
		else if(this.variables.get("Arc Length") != 0 && this.variables.get("Linear Velocity") != 0){
			time = this.variables.get("Arc Length")/this.variables.get("Linear Velocity");
			this.decideEquation("Arc Length", "/", "Linear Velocity", "", "", time);
		}
		
		//time = angle / (linear velocity / radius) if the user enters linear velocity, angle, and radius
		else if(this.variables.get("Linear Velocity") != 0 && this.variables.get("Angle") != 0 && this.variables.get("Radius") != 0){
			time = this.variables.get("Angle")/(this.variables.get("Linear Velocity")/this.variables.get("Radius"));
			this.decideEquation("Angle", "/", "(Linear Velocity", "/", "Radius)", time);
		}
		
		//time = (arc length / radius) / angular velocity if the user enters arc length, radius, and angular velocity
		else if(this.variables.get("Angular Velocity") != 0 && this.variables.get("Radius") != 0 && this.variables.get("Arc Length") != 0){
			time = (this.variables.get("Arc Length") / this.variables.get("Radius")) / this.variables.get("Angular Velocity");
			this.decideEquation("(Arc Length", "/", "Radius)", "/", "Angular Velocity", time);
		}
		
		return time;
	}

	/** finds angle using various equations relating to Angular Motion
	 *  @return the calculated angle if the calculation has finished properly, and the current angle if it has not 
	 */
	private double findAngle(){
		//gets the current angle value to return if a value cannot be calculated for
		double angle = this.variables.get("Angle");

		//if the user enters certain variables, the program is able to calculate for angle with them and then make their equations
		//angle = angular velocity x time if the user enters angular velocity and time
		if(this.variables.get("Angular Velocity") != 0 && this.variables.get("Time") != 0){
			angle = this.variables.get("Angular Velocity") * this.variables.get("Time");
			this.decideEquation("Angular Velocity", "x", "Time", "", "", angle);
		}
		
		//angle = arc length / radius if the user enters arc length and radius
		else if(this.variables.get("Arc Length") != 0 && this.variables.get("Radius") != 0){
			angle = this.variables.get("Arc Length")/this.variables.get("Radius");
			this.decideEquation("Arc Length", "/", "Radius", "", "", angle);
		}
		
		//angle = (linear velocity / radius) x time if the user enters linear velocity, radius, and time
		else if(this.variables.get("Linear Velocity") != 0 && this.variables.get("Time") != 0 && this.variables.get("Radius") != 0){
			angle = (this.variables.get("Linear Velocity") / this.variables.get("Radius")) * this.variables.get("Time");
			this.decideEquation("(Linear Velocity", "/", "Radius)", "x", "Time", angle);
		}
		
		//angle = angular velocity x (arc length / linear velocity) if the user enters angular velocity, arc length, and linear velocity
		else if(this.variables.get("Angular Velocity") != 0 && this.variables.get("Arc Length") != 0 && this.variables.get("Linear Velocity") != 0){
			angle = this.variables.get("Angular Velocity") * (this.variables.get("Arc Length") / this.variables.get("Linear Velocity"));
			this.decideEquation("Angular Velocity", "x", "(Arc Length", "/", "Linear Velocity)", angle);
		}
		
		return angle;
	}

	/** Returns the equation attribute
	 *  @return the equation 
	 */
	public String getEquation(){
		return this.equation;
	}

	/** Returns the valueEquation attribute
	 *  @return the equation with values instead of words 
	 */
	public String getValueEquation() {
		return valueEquation;
	}

	/** Returns the result attribute
	 *  @return the solution to the calculation 
	 */
	public String getResult(){
		return this.result;
	}

	/** Returns the wantedVariable attribute
	 *  @return the wantedVariable 
	 */
	public String getWantedVariable() {
		return wantedVariable;
	}

	/** Returns the variables attribute
	 *  @return the TreeMap of all the measurements and their values 
	 */
	public TreeMap<String, Double> getVariables(){
		return this.variables;
	}

	/** Updates Calculations, setting variables, calculating for the wanted variable, making the view, etc. 
	 */
	public void update() {
		this.setVariables();
		//if the calculation is successful
		if(this.calculate()){
			//getting rid of an old process frame if one exists
			try{
				this.processFrame.dispose();
			}
			catch(NullPointerException ex){
			}
			//making a new process frame and giving animation its variables before updating it
			this.processFrame = new ProcessFrame(this, this.input);
			this.setAnimationVariables();
			this.input.getAnimation().updateModel();
		}
		//if the calculation cannot be done, make a new error dialog
		else{
			new ErrorDialog((JFrame)SwingUtilities.getWindowAncestor(this.input.getView()), "Cannot calculate using current givens. " + this.giveRelevantError());
		}
	}

	/** Returns the appropriate error containing all the values the user needs to calculate for the wanted variable
	 *  @return the error message that the user gets for their respective wanted variable 
	 */
	private String giveRelevantError(){
		//sets the error message as which variables the user needs to calculate for the wanted variable that the user has input
		if(this.wantedVariable.equalsIgnoreCase(Input.VARIABLES[0]))
			return "To calculate Angular Velocity, you need either of these sets of measurements: Angle and Time <p>Linear Velocity and Radius <p>Arc Length, Angle, and Linear Velocity <p>Time, Radius, and Arc Length</p></p></p></p>";
		else if(this.wantedVariable.equalsIgnoreCase(Input.VARIABLES[1]))
			return "To calculate Linear Velocity, you need either of these sets of measurements: <p>Arc Length and Time <p>Angular Velocity and Radius <p>Arc Length, Angle, and Angular Velocity <p>Time, Radius, and Angle</p></p></p></p>";
		else if(this.wantedVariable.equalsIgnoreCase(Input.VARIABLES[2]))
			return "To calculate Radius, you need either of these sets of measurements: <p>Linear Velocity and Angular Velocity <p>Arc Length and Angle <p>Arc Length, Time, and Angular Velocity <p>Time, Angle, and Linear Velocity</p></p></p></p>";
		else if(this.wantedVariable.equalsIgnoreCase(Input.VARIABLES[3]))
			return "To calculate Arc Length you need either of these sets of measurements: <p>Time and Linear Velocity <p>Angle and Radius <p>Radius, Time, and Angular Velocity <p>Angular Velocity, Angle, and Angular Velocity</p></p></p></p>";
		else if(this.wantedVariable.equalsIgnoreCase(Input.VARIABLES[4]))
			return "To calculate Time, you need either of these sets of measurements: <p>Angular Velocity and Angle <p>Linear Velocity and Arc Length <p>Radius, Angle, and Linear Velocity <p>Angular Velocity, Radius, and Arc Length</p></p></p></p>";
		else if(this.wantedVariable.equalsIgnoreCase(Input.VARIABLES[5]))
			return "To calculate Angle, you need either of these sets of measurements: <p>Angular Velocity and Time <p>Radius and Arc Length <p>Arc Length, Angular Velocity, and Linear Velocity <p>Time, Radius, and Linear Velocity</p></p></p></p>";
		return "";
	}
}
