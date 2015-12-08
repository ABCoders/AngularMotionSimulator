package calculation;

import javax.swing.*;

import inputs.Input;
import main.AngularMotionSimulatorPanel;

public class Calculations extends Object{
	
	private AngularMotionSimulatorPanel amsPanel;
	private ProcessFrame processFrame;
	private Input input;
	
	private String equation;
	private String valueEquation;
	private String result;
	
	public Calculations(Input input){
		this.input = input;
	}
	
	public void setGUI(JPanel panel){
		
	}
	
	public void calculate(){
		
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
	
	private double findAngularVelocity(double angle, double time, double radius, double linearVelocity){
		double angularVelocity = 0;
		return angularVelocity;
	}
	
	private double findLinearVelocity(double angularVelocity, double radius, double arcLength, double time){
		double linearVelocity = 0;
		return linearVelocity;
	}
	
	private double findRadius(double angularVelocity, double linearVelocity, double arcLength, double angle){
		double radius = 0;
		return radius;
	}
	
	private double findArcLength(double time, double linearVelocity, double angle, double radius){
		double arcLength = 0;
		return arcLength;
	}
	
	private double findTime(double angularVelocity, double linearVelocity, double angle, double arcLength){
		double time = 0;
		return time;
	}
	
	private double findAngle(double angularVelocity, double time, double arcLength, double radius){
		double angle = 0;
		return angle;
	}
	
	public void saveProcess(String saveString){
		
	}
	
	public String getEquation(){
		return this.equation;
	}
	
	public String getValuesEquation(){
		return this.valueEquation;
	}
	
	public String getResult(){
		return this.result;
	}
	

}
