package animation;

import java.awt.*;
import javax.swing.*;

/**
 * A Container that contains the ActionPanel and AnimationComponent. It is the main view for animation.
 * @author Bryan Kristiono
 * @since 7/12/2015
 */
public class AnimationPanel extends JPanel {
	private Animation animation;
	private ActionPanel actionPanel;
	private AnimationComponent animationComponent;
	private JLabel angularVelocity;
	private JLabel linearVelocity;
	private JLabel radius;
	
	/**
	 * Initialize a new AnimationPanel.
	 * Creates the look and feel of the animation view.
	 * @param animation The model of animation with information needed
	 */
	public AnimationPanel(Animation animation) {
		super();
		this.animation = animation;
		createComponents();
		createPanel();
		registerControllers();
		this.animation.setGUI(this);
	}
	
	/**
	 * Returns the Animation Component that contains the actual animation.
	 * @return The animation Component
	 */
	public AnimationComponent getAnimationComponent() {
		return animationComponent;
	}
	
	/**
	 * Returns the Action Panel that contains buttons and sliders.
	 * @return The Action Panel
	 */
	public ActionPanel getActionPanel() {
		return actionPanel;
	}
	
	/**
	 * Initialize and set values to all needed components.
	 */
	private void createComponents() {
		animationComponent = new AnimationComponent(animation);
		actionPanel = new ActionPanel(animation);
		
		//Labels
		angularVelocity = new JLabel("Angular Velocity = " + animation.getAngularVelocity() + "rad/s");
		linearVelocity = new JLabel("Linear Velocity = " + animation.getLinearVelocity() + "m/s");
		radius = new JLabel("Radius = " + animation.getRadius()/100 + "m");
	}
	
	/**
	 * Set up the components in the panel layout.
	 */
	private void createPanel() {
		this.setLayout(new BorderLayout());
		this.add(animationComponent, BorderLayout.CENTER);
		this.add(actionPanel, BorderLayout.NORTH);
		
		//Panel for labels
		JPanel labels = new JPanel();
		labels.add(radius);
		labels.add(linearVelocity);
		labels.add(angularVelocity);
		this.add(labels, BorderLayout.SOUTH);
	}
	
	/**
	 * Register listeners to sub-components.
	 */
	private void registerControllers() {
		animationComponent.addMouseListener(new DrawPointController(animation, animation.getDrawPoints()));
	}
	
	/**
	 * Updates the sub-components as well as labels.
	 */
	public void update() {
		angularVelocity.setText("Angular Velocity = " + animation.getAngularVelocity() + "rad/s");
		linearVelocity.setText("Linear Velocity = " + animation.getLinearVelocity() + "m/s");
		radius.setText("Radius = " + animation.getRadius()/100 + "m");
		animationComponent.repaint();
		actionPanel.update();
	}
}
