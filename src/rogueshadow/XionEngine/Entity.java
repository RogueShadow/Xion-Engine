package rogueshadow.XionEngine;

public class Entity {
	double x = 0;
	double y = 0;
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

	public Entity(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public void push(double angle, double speed){
		this.vx += Math.sin(angle)*speed;
		this.vy += Math.cos(angle)*speed;
	}
	
	public double getSpeed(){
		double spd = Math.abs(vx) + Math.abs(vy);
		return spd;
	}

}
