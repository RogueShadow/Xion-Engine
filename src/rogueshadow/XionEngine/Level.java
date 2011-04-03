package rogueshadow.XionEngine;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.*;
import rogueshadow.XionEngine.Player;

public class Level {
	TiledMap map;
	Player player;
	String name; 
	ArrayList<Shape> blocks = new ArrayList<Shape>();
	
	public String getName() {
		return name;
	}
	public Vector2f getWorldPos(Vector2f pos){
		Vector2f wpos = new Vector2f();
		wpos.x = pos.x/map.getTileWidth();
		wpos.y = pos.y/map.getTileHeight();
		return wpos;
	}
	public TiledMap getMap() {
		return map;
	}
	public void setMap(TiledMap map) {
		this.map = map;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Level(TiledMap map, String lvlname) {
		this.map = map;
		this.name = lvlname;
		float p = 1f/16f;
		for (int x = 0; x < map.getWidth();x++){
			for (int y = 0; y < map.getHeight();y++){
				if (map.getTileId(x, y, 1) != 0){
					blocks.add(new Rectangle(x+p,y+p,1f-p,1f-p));
				}
			}
		}
	}
	public int getWidth(){
		return map.getWidth();
	}
	public int getHeight(){
		return map.getHeight();
	}
	public int getTileWidth(){
		return map.getTileWidth();
	}
	public int getTileHeight(){
		return map.getTileHeight();
	}
	public void update(int delta) {
		
	}
	public ArrayList<Shape> getRenderShapes(ArrayList<Shape> shapes){
		ArrayList<Shape> renders = new ArrayList<Shape>();
		for (Shape b: shapes){
			float x = b.getX()*getTileWidth();
			float y = b.getY()*getTileHeight();
			float w = b.getWidth()*getTileWidth();
			float h = b.getHeight()*getTileHeight();
			renders.add(new Rectangle(x,y,w,h));
		}
		return renders;
	}
	public void drawCollision(Graphics g) {
		for (Shape b: getRenderShapes(blocks)){
			g.draw(b);
		}
	}
	public boolean checkCollisions(Shape p) {
		for (Shape b: blocks){
			if (p.intersects(b)){
				return true;
			}
		}
		return false;
	}
}
