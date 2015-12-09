package animation;

import java.awt.*;
import javax.swing.*;

/**AnimationPanel
 * A Container for the ActionPanel and AnimationComponent
 * @author BRYAN KRISTIONO
 * @since 7/12/2015
 */
public class AnimationPanel extends JPanel {
	private Animation animation;
	private ActionPanel actionPanel;
	private AnimationComponent animationComponent;
	private JLabel angularVelocity;
	private JLabel linearVelocity;
	private JLabel radius;
	
	public AnimationPanel(Animation animation) {
		super();
		this.animation = animation;
		createComponents();
		createPanel();
		registerControllers();
	}
	
	private void createComponents() {
		actionPanel = new ActionPanel(animation);
		animationComponent = new AnimationComponent(animation);
		angularVelocity = new JLabel("Angular Velocity = " + animation.getAngularVelocity());
		linearVelocity = new JLabel("Linear Velocity = " + animation.getLinearVelocity());
		radius = new JLabel("Radius = " + animation.getRadius());
	}
	
	private void createPanel() {
		this.setLayout(new BorderLayout());
		this.add(actionPanel, BorderLayout.NORTH);
		this.add(animationComponent, BorderLayout.CENTER);
		JPanel labels = new JPanel();
		labels.add(radius);
		labels.add(linearVelocity);
		labels.add(angularVelocity);
		this.add(labels, BorderLayout.SOUTH);
	}
	
	private void registerControllers() {
		animationComponent.addMouseListener(new DrawPointController(animation));
	}
	
	public void update() {
		
	}
}
