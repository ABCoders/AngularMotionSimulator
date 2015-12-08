package inputs;

import animation.Animation;
import calculation.Calculations;
import options.Options;

public class Input {
	private Animation animation;
	private Options options;
	private Calculations calculations;
	
	public Input() {
		animation = new Animation(this);
		options = new Options(this);
		calculations = new Calculations(this);
	}

	public Animation getAnimation() {
		return animation;
	}

	public Options getOptions() {
		return options;
	}

	public Calculations getCalculations() {
		return calculations;
	}
}
