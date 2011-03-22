package rogueshadow.XionEngine;

import org.newdawn.slick.Color;


public class Projectile extends Entity {
	int life;
	int lived;
	float scale = 1;
	Color color = Color.white;
	
	public Projectile(float x, float y, float angle, int life, float speed){
		super(x,y);
		this.life = life;
		this.vx = Math.sin(angle)*speed;
		this.vy = Math.cos(angle)*speed;
	}
	public boolean update(){
		if (lived > life){	
			return false;
		}else{
			lived++;
			x += vx;
			y += vy;
			color.a = 1f;
			color.r = 1f;
			color.g = 1f;
			color.b = 1f;
			if (x <0)vx*=-1;
			if (y <0)vy*=-1;
			if (x > 800)vx*= -1;
			if (y > 600)vy*=-1;
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
