package animation;

import java.awt.Point;

public class DrawAction {
	public static final int MOVE = 0;
	public static final int CREATE = 1;
	public static final int DELETE = 2;
	
	private int index;
	private int action;
	private DrawPoint point;
	private Point location;
	
	public DrawAction(int action, DrawPoint point, int index) {
		this.action = action;
		this.point = point;
		this.index = index;
	}
	
	public int getAction() {
		return this.action;
	}
	
	public DrawPoint getPoint() {
		return this.point;
	}
	
	public Point getLocation() {
		return this.location;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public void setLocation(Point location) {
		this.location = location;
	}
}
