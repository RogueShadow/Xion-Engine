package rogueshadow.XionEngine;

import org.newdawn.slick.Color;


public class Projectile {
	double index;
	float x;
	float y;
	float vx;
	float vy;
	int life;
	int lived;
	float alpha = 1;
	Color color = Color.white;
	
	public Projectile(float x, float y, float angle, int life, float speed, Color color){
		this.color = color;
		this.x = x;
		this.y = y;
		this.life = life;
		this.vx = (float) Math.sin(angle)*speed;
		this.vy = (float) Math.cos(angle)*speed;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public boolean update(){
		if (lived > life){	
			return false;
		}else{
			lived++;
			x += vx;
			y += vy;
			color.a = 1f - ((float) lived / (float) life);
			if (x <0)vx*=-1;
			if (y <0)vy*=-1;
			if (x > 800)vx*= -1;
			if (y > 600)vy*=-1;
			vx*=.99f;
			vy*=.99f;
			return true;
		}
	}
	public Color getColor(){
		return color;
	}
	public float getScale() {
		return (1f - color.a*1f);
	}
	public float getVx() {
		return vx;
	}
	public void setVx(float vx) {
		this.vx = vx;
	}
	public float getVy() {
		return vy;
	}
	public void setVy(float vy) {
		this.vy = vy;
	}

}
