package animation;

import javax.swing.*;

/**
 * A Container for all buttons and sliders that changes the animation.
 * @author Bryan Kristiono
 * @since 19/01/2016
 */
public class ActionPanel extends JPanel {
	private Animation animation;		//The model of the animation

	private JButton animationButton;	//The button that starts and stops the animation
	private JButton clearButton;		//The button that resets the drawn point
	private JLabel timeLabel;			//The label to indicate which slider is for time
	private JLabel scaleLabel;			//The label to indicate which slider is for scaling
	private JSlider timeSlider;			//The slider that changes the time of the animation
	private JSlider scaleSlider;		//The slider that changes the scale of the animation

	private ImageIcon playIcon;			//The icon to indicate the play button
	private ImageIcon pauseIcon;		//The icon to indicate the pause button

	private int max;					//The max value of the time slider

	/**
	 * Initialize a new ActionPanel with configured components needed.
	 * @param animation The model of animation with information needed
	 */
	public ActionPanel(Animation animation) {
		super();
		//Get icons from saved files
		playIcon = new ImageIcon(this.getClass().getResource("playIcon.png"));
		pauseIcon = new ImageIcon(this.getClass().getResource("pauseIcon.png"));

		this.animation = animation;
		this.createComponents();
		this.createPanel();
		this.registerControllers();
	}

	/**
	 * Initialize and set values to all needed components.
	 */
	private void createComponents() {
		//Buttons
		animationButton = new JButton(playIcon);
		clearButton = new JButton("CLEAR");

		//Labels
		timeLabel = new JLabel("TIME (ds)");
		timeLabel.setLabelFor(timeSlider);
		scaleLabel = new JLabel("SCALE (%)");
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
	 * Set up the components in the panel layout.
	 */
	private void createPanel() {
		this.add(scaleLabel);
		this.add(scaleSlider);
		this.add(timeLabel);
		this.add(timeSlider);
		this.add(animationButton);
		this.add(clearButton);
	}

	/**
	 * Add Listeners to needed components.
	 */
	private void registerControllers() {
		//Buttons
		animationButton.addActionListener(new ActionController(animation));
		clearButton.addActionListener(new ActionController(animation));

		//Sliders
		timeSlider.addChangeListener(new TimeSliderController(animation));
		scaleSlider.addChangeListener(new ScaleSliderController(animation));
	}

	/**
	 * Updates the panel.
	 */
	public void update() {
		//Updates the timeSlider if need be changed
		if(max!=(int) (Math.ceil(animation.getWidth()/10/animation.getLinearVelocity()/animation.getScale()))) {
			max = (int) (Math.ceil(animation.getWidth()/10/animation.getLinearVelocity()/animation.getScale()));

			//Updates the slider ticks
			try {
				timeSlider.setMaximum(max);

				//Configures the ticks on the slider based on size of slider
				timeSlider.setLabelTable(null);
				if(max<5) {
					timeSlider.setMajorTickSpacing(1);
				}
				else {
					timeSlider.setMajorTickSpacing(Math.round(timeSlider.getMaximum()/5));
					timeSlider.setMinorTickSpacing(Math.round(timeSlider.getMaximum()/20));
				}
			} catch (Exception e) {
				System.err.println("Time Slider Error");
			}
		}

		//When the animation is running
		if (animation.getState()) {
			//Changes button look
			animationButton.setText("PAUSE");
			animationButton.setIcon(pauseIcon);
			animationButton.setActionCommand("pause");
			
			try {
				//Changes slider value as animation runs
				timeSlider.setValue((int)Math.round(animation.getTime()*10));
				scaleSlider.setValue((int)(animation.getScale()*100));
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		//When the animation is not running
		else {
			//Changed button look
			animationButton.setIcon(playIcon);
			animationButton.setText("PLAY   ");
			animationButton.setActionCommand("play");
		}
	}
}
