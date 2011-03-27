/**
 * 
 */
package rogueshadow.XionEngine;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

/**
 * @author Adam
 *
 */
public class Player {
	private String account;

	private int height = 32;

	private Image image;

	private String name;

	private int width = 32;

	private int x = 0;

	private int y = 0;
	
	int speed = 3;
	
	static Input input;

	public static void setInput(Input input) {
		Player.input = input;
	}

	public Player(String account, String name, int x, int y, Image image) {
		this.account = account;
		this.name = name;
		this.x = x;
		this.y = y;
		this.image = image;
	}

	public void draw(int worldOffsetx, int worldOffsety){
		image.draw(worldOffsetx + x,worldOffsety + y);
		
	}
	public String getAccount() {
		return account;
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

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}
	public void movePlayer(Integer deltaX, Integer deltaY) {
		this.x += deltaX;
		this.y += deltaY;
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
		this.x = x;
	}
	
	public void setY(Integer y) {
		this.y = y;
	}

	public void move(Level level) {
        if (input.isKeyDown(Input.KEY_W))level.movePlayer(0,-speed);
        if (input.isKeyDown(Input.KEY_S))level.movePlayer(0, speed);
        if (input.isKeyDown(Input.KEY_A))level.movePlayer(-speed, 0);
        if (input.isKeyDown(Input.KEY_D))level.movePlayer(speed, 0);
		
	}
	
}
