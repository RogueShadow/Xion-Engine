package rogueshadow.XionEngine;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;



public class Camera {
	Vector2f pos;
	float W;// half screen width
	float H;// half screen height
	float WorldW = 0;// world width - screen width 
	float WorldH = 0;// world height - screen height
	int tilesize = 16;
	public Camera(Vector2f pos) {
		this.pos = pos;
	}
	public Camera(float x, float y, int screenw, int screenh,int worldX, int worldY, int tilesize){
		this.pos = new Vector2f(x,y);
		this.W = (screenw/this.tilesize)/2;
		this.H = (screenh/this.tilesize)/2;
		this.WorldW = worldX - W*2f;
		this.WorldH = worldY - H*2f-1.5f;
		this.tilesize = tilesize;
	}
	public Camera(float x, float y, int screenw, int screenh){
		this.pos = new Vector2f(x,y);
		this.W = screenw/2f;
		this.H = screenh/2f;
	}
	public void moveCam(float x, float y){
		this.pos.add(new Vector2f(x,y));
	}
	public void translateIn(Graphics g) {
		g.pushTransform();
		g.translate(-this.pos.getX()*this.tilesize, -this.pos.getY()*this.tilesize);
	}
	public void translateOut(Graphics g) {
		g.popTransform();
	}
	public void focusCam(Vector2f pos2) {
		this.pos.x = pos2.getX() - W;
		this.pos.y = pos2.getY() - H;
		if (this.WorldH != 0){
			if (this.pos.x < 0)this.pos.x = 0;
			if (this.pos.y < 0)this.pos.y = 0;
			if (this.pos.x >= this.WorldW)this.pos.x = this.WorldW ;
			if (this.pos.y >= this.WorldH)this.pos.y = this.WorldH ;
		}
	}
	public Vector2f getPos() {
		return this.pos;
	}
}
