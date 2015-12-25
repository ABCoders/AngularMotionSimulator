package animation;

import javax.swing.*;

/**ActionPanel
 * A Container for All buttons and sliders that changes the animation
 * @author BRYAN KRISTIONO
 * @since 12/12/2015
 * Created: 7/12/2015
 */
public class ActionPanel extends JPanel {
	private Animation animation;	//The model of the animation
	
	private JButton playButton;		//The button that starts the animation
	private JButton pauseButton;	//The button that stops the animation
	private JButton clearButton;	//The button that resets the drawn point
	private JLabel timeLabel;		//The label to indicate which slider is for time
	private JLabel scaleLabel;		//The label to indicate which slider is for scaling
	private JSlider timeSlider;		//The slider that changes the time of the animation
	private JSlider scaleSlider;	//The slider that changes the scale of the animation
	
	private int max;
	/**
	 * The main constructor
	 * Creates the look and feel of the animation view
	 * @param animation
	 */
	public ActionPanel(Animation animation) {
		super();
		this.animation = animation;
		this.createComponents();
		this.createPanel();
		this.animation.setGUI(this);
		this.registerControllers();
	}
	
	/**
	 * Initialize and set values to all needed components
	 */
	private void createComponents() {
		//Buttons
		playButton = new JButton("PLAY");
		pauseButton = new JButton("PAUSE");
		clearButton = new JButton("CLEAR");
		
		//Labels
		timeLabel = new JLabel("TIME");
		timeLabel.setLabelFor(timeSlider);
		scaleLabel = new JLabel("SCALE");
		scaleLabel.setLabelFor(scaleSlider);
		
		//Time Slider
		timeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		timeSlider.setMajorTickSpacing(20);
		timeSlider.setMinorTickSpacing(5);
		timeSlider.setPaintTicks(true);
		timeSlider.setPaintLabels(true);
		
		//Scale Slider
		scaleSlider = new JSlider(JSlider.HORIZONTAL, 0, 200, 100);
		scaleSlider.setMajorTickSpacing(50);
		scaleSlider.setMinorTickSpacing(10);
		scaleSlider.setExtent(1);
		scaleSlider.setPaintTicks(true);
		scaleSlider.setPaintLabels(true);
	}
	
	/**
	 * Set up the components in the panel layout
	 */
	private void createPanel() {
		this.add(scaleLabel);
		this.add(scaleSlider);
		this.add(timeLabel);
		this.add(timeSlider);
		this.add(playButton);
		this.add(pauseButton);
		this.add(clearButton);
	}
	
	/**
	 * Add Listeners to all components
	 */
	private void registerControllers() {
		playButton.addActionListener(new ActionController(animation));
		pauseButton.addActionListener(new ActionController(animation));
		clearButton.addActionListener(new ActionController(animation));
			
		timeSlider.addChangeListener(new TimeSliderController(animation));
		scaleSlider.addChangeListener(new ScaleSliderController(animation));
	}
	
	/**
	 * Updates the panel
	 */
	public void update() {
		if(max!=(int) (animation.getWidth()/10/animation.getLinearVelocity()/animation.getScale())) {
			max = (int) (animation.getWidth()/10/animation.getLinearVelocity()/animation.getScale());
			timeSlider.setMaximum(max);
			
			timeSlider.setLabelTable(null);
			if(timeSlider.getMaximum()<=10) {
				timeSlider.setMajorTickSpacing(2);
				timeSlider.setMinorTickSpacing(1);
			} 
			else if(timeSlider.getMaximum()<=25) {
				timeSlider.setMajorTickSpacing(5);
				timeSlider.setMinorTickSpacing(1);
			} 
			else if(timeSlider.getMaximum()<=50) {
				timeSlider.setMajorTickSpacing(10);
				timeSlider.setMinorTickSpacing(2);
			} 
			else if(timeSlider.getMaximum()<=100) {
				timeSlider.setMajorTickSpacing(20);
				timeSlider.setMinorTickSpacing(5);
			} 
			else if(timeSlider.getMaximum()<=200) {
				timeSlider.setMajorTickSpacing(50);
				timeSlider.setMinorTickSpacing(20);
			} 
			else if(timeSlider.getMaximum()<=500) {
				timeSlider.setMajorTickSpacing(100);
				timeSlider.setMinorTickSpacing(25);
			} 
			else if(timeSlider.getMaximum()<=1000) {
				timeSlider.setMajorTickSpacing(200);
				timeSlider.setMinorTickSpacing(50);
			} 
			else if(timeSlider.getMaximum()<=2500) {
				timeSlider.setMajorTickSpacing(500);
				timeSlider.setMinorTickSpacing(100);
			} 
			else if(timeSlider.getMaximum()<=5000) {
				timeSlider.setMajorTickSpacing(1000);
				timeSlider.setMinorTickSpacing(500);
			} 
			else {
				timeSlider.setMajorTickSpacing(2500);
				timeSlider.setMinorTickSpacing(1000);
			}
		}
		
		if (animation.getState()) {
			playButton.setEnabled(false);
			pauseButton.setEnabled(true);
			timeSlider.setValue((int)Math.round(animation.getTime()*10));
			scaleSlider.setValue((int)(animation.getScale()*100));
		}
		else {
			playButton.setEnabled(true);
			pauseButton.setEnabled(false);
		}
	}
}
