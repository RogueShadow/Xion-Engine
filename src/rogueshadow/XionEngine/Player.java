/**
 * 
 */
package rogueshadow.XionEngine;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

/**
 * @author Adam
 *
 */
public class Player {
	private int height = 32;
	private Image image;
	private String name;
	private int width = 32;
	private Vector2f pos;
	private Vector2f vel;
	int speed = 12;

	public Player(String name, int x, int y, Image image) {
		this.name = name;
		this.pos = new Vector2f(x,y);
		this.image = image;
	}
	public int getHeight() {
		return height;
	}
	public Image getImage() {
		return image;
	}
	public String getName() {
		return name;
	}
	public int getWidth() {
		return width;
	}
	public void movePlayer(Integer deltaX, Integer deltaY) {
		this.pos.add(new Vector2f(deltaX,deltaY));
	}
	public void setHeight(int height) {
		this.height = height;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setX(Integer x) {
		this.pos.set(x, this.pos.getY());
	}
	
	public void setY(Integer y) {
		this.pos.set(this.pos.getX(), y);
	}

	public Vector2f getPos() {
		return pos;
	}

	public void setPos(Vector2f pos) {
		this.pos = pos;
	}

	public Vector2f getVel() {
		return vel;
	}

	public void setVel(Vector2f vel) {
		this.vel = vel;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
}
