/**
 * 
 */
package rogueshadow.XionEngine;

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
	private String name;
	Shape shape;
	int WorldW;
	int WorldH;
	Level level;
	public void setLevel(Level level) {
		this.level = level;
		this.WorldW = level.getWidth();
		this.WorldH = level.getHeight();
	}
	float speed = 12f/1000f;

	public Player(String name, int x, int y, Shape shape, Level level) {
		super(x,y);
		this.shape = shape;
		this.setLevel(level);
		this.name = name;
	}
	public float getHeight() {
		return this.getShape().getHeight();
	}
	public String getName() {
		return name;
	}
	public float getWidth() {
		return this.getShape().getWidth();
	}
	public void movePlayer(Vector2f vel) {
		if (!level.onWall(getPos().add(getVel()), shape)){
			this.push(vel.scale(this.getSpeed()));
		}else this.pull(vel.scale(this.getSpeed()));
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
		super.update(delta);
		this.vel.scale(0);
		if (this.pos.x < 0)this.pos.x = 0;
		if (this.pos.y < 0)this.pos.y = 0;
		if (this.pos.x > this.WorldW - this.getWidth())this.pos.x = this.WorldW - this.getWidth();
		if (this.pos.y > this.WorldH - this.getHeight())this.pos.y = this.WorldH - this.getHeight();
		return true;
	}
	public Shape getShape() {
		this.shape.setLocation(this.pos);
		return this.shape;
	}
	public void render(Graphics g) {
		float x = getPos().getX()*level.getTileWidth();
		float y = getPos().getY()*level.getTileHeight();
		float w = this.getWidth()*level.getTileWidth();
		float h = this.getHeight()*level.getTileHeight();
		Shape drawn = new Rectangle(x,y,w,h);
		g.fill(drawn);
		g.drawString(getName(), x - (getName().length()*2), y + h + 2);
	}
}
