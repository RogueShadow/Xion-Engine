package rogueshadow.XionEngine;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;



public class Camera {
	Vector2f pos;
	float W;// half width
	float H;// half height
	float zoom = 1f;
	public Camera(Vector2f pos) {
		this.pos = pos;
	}
	public Camera(float x, float y, int w, int h){
		this.pos = new Vector2f(x,y);
		this.W = w/2f;
		this.H = h/2f;
	}
	public void moveCam(float x, float y){
		this.pos.add(new Vector2f(x,y));
	}
	public float getScreenX(float worldX){
		return (pos.getX()-worldX*zoom);
	}
	public float getScreenY(float worldY){
		return (pos.getY()-worldY*zoom);
	}
	public float getWorldX(float screenX){
		return ((pos.getX()+screenX)*zoom);
	}
	public float getWorldY(float screenY){
		return ((pos.getY()+screenY)*zoom);
	}
	public float getZoom(){
		return zoom;
	}
	public void setZoom(float zoom){
		this.zoom = zoom;
	}
	public void changeZoom(float delta){
		setZoom(this.zoom+delta);
	}
	public void setCam(Vector2f pos) {
		this.pos = pos;
	}
	public void setCam(float x, float y){
		this.pos = new Vector2f(x,y);
	}
	public void translateIn(Graphics g) {
		g.pushTransform();
		g.translate(W, H);
		g.scale(this.zoom, this.zoom);
		g.translate(-W, -H);
		g.translate(-this.pos.getX(), -this.pos.getY());
	}
	public void translateOut(Graphics g) {
		g.popTransform();
	}
}
