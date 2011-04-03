/**
 * 
 */
package rogueshadow.XionEngine;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

import rogueshadow.XionEngine.test.TestGame;

/**
 * @author Adam
 *
 */
public class Player extends Entity {
	boolean initialized = false;
	private String name;
	int WorldW;
	int WorldH;
	float width;
	float height;
	Level level;
	public void setLevel(Level level) {
		this.level = level;
		this.WorldW = level.getWidth();
		this.WorldH = level.getHeight();
		this.initialized = true;
	}
	public void setLevel(Level level, float x, float y){
		this.pos = new Vector2f(x,y);
		this.level = level;
		this.WorldW = level.getWidth();
		this.WorldH = level.getHeight();
		this.initialized = true;
	}
	float speed = 12f/1000f;

	public Player(String name, float width, float height) {
		super(0,0);
		this.name = name;
		this.width = width;
		this.height = height;
	}
	public float getHeight() {
		return this.height;
	}
	public String getName() {
		return name;
	}
	public float getWidth() {
		return this.width;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setX(Integer x) {
		this.pos.set(x, this.pos.getY());
	}
	public void setY(Integer y) {
		this.pos.set(this.pos.getX(), y);
	}
	public void setPos(Vector2f pos) {
		this.pos = pos;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public boolean update(int delta){
		if (!this.initialized)return false;
		this.vel.scale(getSpeed());
		checkCollisions(delta);
		super.update(delta);
		this.vel.scale(0);
		if (this.pos.x < 0)this.pos.x = 0;
		if (this.pos.y < 0)this.pos.y = 0;
		if (this.pos.x > this.WorldW - this.getWidth())this.pos.x = this.WorldW - this.getWidth();
		if (this.pos.y > this.WorldH - this.getHeight())this.pos.y = this.WorldH - this.getHeight();
		return true;
	}
	private void checkCollisions(int delta) {
		float nX = this.pos.getX() + this.vel.getX()*delta;
		float nY = this.pos.getY() + this.vel.getY()*delta;
		if (level.checkCollisions(new Rectangle(nX,nY,this.width,this.height))){
			if (level.checkCollisions(new Rectangle(nX,this.pos.getY(),this.width,this.height))){
				this.vel.x = 0;
			}
			if (level.checkCollisions(new Rectangle(this.pos.getX(),nY,this.width,this.height))){
				this.vel.y = 0;
			}
		}
	}
	public Shape getShape() {
		return new Rectangle(this.pos.getX(),this.pos.getY(),this.width,this.height);
	}
	protected Shape getRenderShape() {
		float x = this.pos.getX() * level.getTileWidth();
		float y = this.pos.getY() * level.getTileHeight();
		float w = this.width * level.getTileWidth();
		float h = this.height * level.getTileHeight();
		return new Rectangle(x,y,w,h);
	}
	public void render(Graphics g) {
		if (!this.initialized)return;
		Shape rect = getRenderShape();
		g.setColor(Color.blue);
		g.draw(rect);
		g.drawString(getName(), rect.getX() - (getName().length()*2), rect.getY() + rect.getHeight() + 2);
		g.setColor(Color.white);
	}
}
