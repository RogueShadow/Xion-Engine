package rogueshadow.XionEngine;

import org.newdawn.slick.geom.Vector2f;

public class Entity {
	Vector2f pos;
	Vector2f vel;

	public Entity(float x, float y){
		this.pos = new Vector2f(x,y);
		this.vel = new Vector2f();
	}
	public Entity(float x, float y, Vector2f vel){
		this.pos = new Vector2f(x,y);
		this.vel = vel;
	}
	public float getX() {
		return this.pos.getX();
	}
	public float getY() {
		return this.pos.getY();
	}
	public Vector2f getVel() {
		return vel;
	}
	public void setVel(Vector2f vel) {
		this.vel = vel;
	}
	public void push(Vector2f v){
		vel.add(v);
	}
	public void pull(Vector2f v){
		vel.sub(v);
	}
	public boolean update(int delta){
		this.pos.add(this.vel.copy().scale(delta));
		return true;
	}
	public Vector2f getPos(){
		return this.pos;
	}
	public void setPos(float x, float y){
		this.pos.set(x,y);
	}
	public Vector2f getVector(Body b){
		return new Vector2f(this.getX()-b.getX(),this.getY()-b.getY());
	}
}
