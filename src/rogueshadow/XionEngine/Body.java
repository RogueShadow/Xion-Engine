package rogueshadow.XionEngine;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Vector2f;

public class Body extends Entity {
	public static final double G = 0.0000000000672428f;

	int life;
	int lived;
	float mass = 1;
	int radius = 32;
	Integer fadecounter = 0;
	Integer fadeduration = 500;
	int fadetime = 0;
	Color color = Color.white;
	
	public Body(float x, float y, Vector2f vel, int life, float mass){
		super(x,y,vel);
		this.life = life;
		this.mass = mass;
		color = new Color((float)Math.random(),(float)Math.random(),(float)Math.random(),1f);
		if (this.life != 0){
			if (this.life < this.fadetime){
				this.fadetime = life;
				this.fadeduration = this.life;
			}else{
				this.fadetime = this.life - this.fadeduration;
			}
		}
	}
	public boolean update(int delta){
		if (this.life == 0){
			super.update(delta);
			return true;
		}
		if (this.lived < this.life){
			super.update(delta);
			this.lived += delta;
			if (this.lived > this.fadetime){
				this.fadecounter += delta;
				this.color.a = 1f - (this.fadecounter.floatValue()/this.fadeduration.floatValue());
			}
			return true;
		}else{
			return false;
		}
	}
	public Color getColor(){
		return this.color;
	}
	public void setColor(float r, float g, float b){
		this.color = new Color(r,g,b,color.a);
	}
	public float getMass(){
		return this.mass;
	}
	public void	setMass(float mass){
		this.mass = mass;
	}
	public boolean isColliding(Body b){
		float dist = pos.distance(b.getPos());
		if (dist < (radius+radius)){
			return true;
		}else{
			return false;
		}
	}
	public Vector2f getVectorToOther(Body b){
		float dist = pos.distance(b.getPos());
		double force = ((getMass()*b.getMass())/(dist*dist))*G;
		return new Vector2f(b.getX()-pos.getX(),b.getY()-pos.getY()).scale((float)force);
	}
}
