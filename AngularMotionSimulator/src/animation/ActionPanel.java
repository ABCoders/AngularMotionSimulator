package animation;

import javax.swing.*;

/**ActionPanel
 * A Container for All buttons and sliders that changes the animation
 * @author BRYAN KRISTIONO
 * @since 7/12/2015
 */
public class ActionPanel extends JPanel {
	private Animation animation;
	
	private JButton playButton;
	private JButton pauseButton;
	private JButton clearButton;
	private JLabel timeLabel;
	private JLabel scaleLabel;
	private JSlider timeSlider;
	private JSlider scaleSlider;
	
	public ActionPanel(Animation animation) {
		super();
		this.animation = animation;
		this.createComponents();
		this.createPanel();
		this.registerControllers();
	}
	
	private void createComponents() {
		playButton = new JButton("PLAY");
		pauseButton = new JButton("PAUSE");
		clearButton = new JButton("CLEAR");
		timeLabel = new JLabel("TIME");
		timeLabel.setLabelFor(timeSlider);
		scaleLabel = new JLabel("SCALE");
		scaleLabel.setLabelFor(scaleSlider);
		timeSlider = new JSlider(JSlider.HORIZONTAL, 60);
		timeSlider.setMajorTickSpacing(10);
		timeSlider.setMinorTickSpacing(2);
		timeSlider.setPaintTicks(true);
		timeSlider.setPaintLabels(true);
		scaleSlider = new JSlider(JSlider.HORIZONTAL, 60);
		scaleSlider.setMajorTickSpacing(10);
		scaleSlider.setMinorTickSpacing(2);
		scaleSlider.setPaintTicks(true);
		scaleSlider.setPaintLabels(true);
	}
	
	private void createPanel() {
		this.add(scaleLabel);
		this.add(scaleSlider);
		this.add(timeLabel);
		this.add(timeSlider);
		this.add(playButton);
		this.add(pauseButton);
		this.add(clearButton);
	}
	
	private void registerControllers() {
		playButton.addActionListener(new ActionController(animation));
		pauseButton.addActionListener(new ActionController(animation));
		clearButton.addActionListener(new ActionController(animation));
			
		timeSlider.addChangeListener(new TimeSliderController(animation));
		scaleSlider.addChangeListener(new ScaleSliderController(animation));
	}
	
	public void update() {
		if (animation.getState()) {
			playButton.setEnabled(false);
			pauseButton.setEnabled(true);
			timeSlider.setValue(animation.getTime());
			scaleSlider.setValue(animation.getScale());
		}
		else {
			playButton.setEnabled(true);
			pauseButton.setEnabled(false);
		}
	}
}
