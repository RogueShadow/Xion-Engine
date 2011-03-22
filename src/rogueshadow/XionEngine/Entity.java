package rogueshadow.XionEngine;

public class Entity {
	float x = 0;
	float y = 0;
	double vx = 0;
	double vy = 0;
	
	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public Entity(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public void pull(float angle, float speed){
		this.vx -= Math.sin(angle)*speed;
		this.vy -= Math.cos(angle)*speed;
	}
	
	public void push(float angle, float speed){
		this.vx += Math.sin(angle)*speed;
		this.vy += Math.cos(angle)*speed;
	}
	
	public double getSpeed(){
		double spd = Math.abs(vx) + Math.abs(vy);
		return spd;
	}

}
