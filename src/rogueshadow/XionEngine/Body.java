package rogueshadow.XionEngine;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;


public class Body extends Entity {
	public static final int G = 1; // Gravitational Constant ;)
	int life;
	int lived;
	int mass = 1;
	static Graphics g;
	static Camera cam;
	Color color = Color.white;
	

	public static Graphics getG() {
		return g;
	}
	public static void setG(Graphics g) {
		Body.g = g;
	}
	public static Camera getCam() {
		return cam;
	}
	public static void setCam(Camera cam) {
		Body.cam = cam;
	}
	public Body(double x, double y, double angle, int life, double speed){
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
	public int getMass(){
		return this.mass;
	}
	public void	setMass(int mass){
		this.mass = mass;
	}
	public void render(){
		render(g, cam);
	}
	public void render(Graphics g, Camera cam){
		g.setColor(color);
		g.fillOval( cam.getScreenX((int)getX()), cam.getScreenY((int)getY()),(float)(8f*cam.getZoom()),(float)(8f*cam.getZoom()));
	}
	public float getScale(){
		return (this.mass/1000);
	}

}
