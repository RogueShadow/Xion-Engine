package rogueshadow.XionEngine;

import org.newdawn.slick.Color;


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
	}
	public boolean update(int delta){
		if (lived > life){	
			return false;
		}else{
			lived += delta;
			x += vx*delta;
			y += vy*delta;
			color.a = 1f;
			color.r = 1f;
			color.g = 1f;
			color.b = 1f;
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

}
