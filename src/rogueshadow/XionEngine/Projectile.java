package rogueshadow.XionEngine;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;


public class Projectile extends Entity {
	int life;
	int lived;
	float scale = 1;
	Color color = Color.white;
	
	public Projectile(double x, double y, double angle, int life, double speed){
		super(x,y);
		this.life = life;
		this.vx = Math.sin(Math.toRadians(angle))*speed;
		this.vy = Math.cos(Math.toRadians(angle))*speed;
		color = new Color((float)Math.random(),(float)Math.random(),(float)Math.random(),1f);
	}
	public boolean update(int delta){
		if (lived > life){	
			return false;
		}else{
			lived += delta;
			x += (vx*delta/500);
			y += (vy*delta/500);
			return true;
		}
	}
	public Color getColor(){
		return color;
	}
	public void setColor(float r, float g, float b){
		this.color = new Color(r,g,b,color.a);
	}
	public float getScale() {
		return this.scale;
	}
	public void render(Graphics g, Camera cam){
		g.setColor(color);
		g.fillOval( cam.getScreenX((int)getX()), cam.getScreenY((int)getY()),(float)(8f*cam.getZoom()),(float)(8f*cam.getZoom()));
	}

}
