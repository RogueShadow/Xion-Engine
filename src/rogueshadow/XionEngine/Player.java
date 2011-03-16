/**
 * 
 */
package rogueshadow.XionEngine;

import org.newdawn.slick.Image;

/**
 * @author Adam
 *
 */
public class Player {
	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	private int x = 0;
	private int y = 0;
	private String name;
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	private int width = 32;
	private int height = 32;
	
	public String getAccount() {
		return account;
	}

	private String account;
	private Image image;
	
	public Player(String account, String name, int x, int y, Image image) {
		this.account = account;
		this.name = name;
		this.x = x;
		this.y = y;
		this.image = image;
	}
	
	public void movePlayer(Integer deltaX, Integer deltaY) {
		this.x += deltaX;
		this.y += deltaY;
	}
	
	public void draw(int worldOffsetx, int worldOffsety){
		image.draw(worldOffsetx + x,worldOffsety + y);
		
	}
	
}
