package rogueshadow.XionEngine;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;


public class Pulser extends Entity {
	public static final int ATTRACTER = 0;
	public static final int REPULSER = 1;
	public static final Integer MAX_STRENGTH = 50;

	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPow() {
		return pow;
	}

	public void setPow(int pow) {
		this.pow = pow;
	}

	int type; // 0 - attractor. 1 - repulsor 
	int pow; //  strength of force
	
	public Pulser(double x, double y, int type, int pow) {
		super(x, y);
		this.type = type;
		this.pow = pow;
	}
	public void render(Graphics g, Camera cam){
		int x = (int) cam.getScreenX((int)getX());
		int y = (int) cam.getScreenY((int)getY());
		Color c = (getType() == Pulser.REPULSER) ? Color.red:Color.green;
		g.setColor(c);
		float scale = (float)((1f + (getPow()/10f)*2)*cam.getZoom());
		g.fillOval(x, y,scale,scale);
		g.drawString(Integer.toString(getPow()), x, y + 20);
	}

}
