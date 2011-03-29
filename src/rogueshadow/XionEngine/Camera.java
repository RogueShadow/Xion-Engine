package rogueshadow.XionEngine;

import org.newdawn.slick.geom.Vector2f;


public class Camera {
	Vector2f pos;
	float zoom = 1f;
	public Camera(Vector2f pos) {
		this.pos = pos;
	}
	public Camera(float x, float y){
		this.pos = new Vector2f(x,y);
	}
	public void moveCam(float x, float y){
		this.pos.add(new Vector2f(x,y));
	}
	public float getScreenX(float worldX){
		return (int)(pos.getX()-worldX*zoom);
	}
	public float getScreenY(float worldY){
		return (int)(pos.getY()-worldY*zoom);
	}
	public float getWorldX(float screenX){
		return ((pos.getX()-screenX)/zoom);
	}
	public float getWorldY(float screenY){
		return ((pos.getY()-screenY)/zoom);
	}
	public float getZoom(){
		return zoom;
	}
	public void setZoom(float zoom){
		this.zoom = zoom;
		if (this.zoom < 0)this.zoom = 0;
		if (this.zoom > 10)this.zoom = 10;
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
}
