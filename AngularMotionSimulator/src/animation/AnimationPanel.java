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
	}
	
	private void createPanel() {
		this.setLayout(new BorderLayout());
		this.add(actionPanel, BorderLayout.NORTH);
		this.add(animationComponent, BorderLayout.CENTER);
	}
	
	private void registerControllers() {
		animationComponent.addMouseListener(new DrawPointController(animation));
	}
	
	public void update() {
		
	}
}
