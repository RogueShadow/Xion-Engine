package rogueshadow.XionEngine;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.*;
import rogueshadow.XionEngine.Player;

public class Level {
	TiledMap map;
	Player player;
	String name; // try to keep this a unique name
	
	public String getName() {
		return name;
	}
	public Vector2f getWorldPos(Vector2f pos){
		Vector2f wpos = new Vector2f();
		wpos.x = pos.x/map.getTileWidth();
		wpos.y = pos.y/map.getTileHeight();
		return wpos;
	}
	public void setName(String name) {
		this.name = name;
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
	public Level(TiledMap map) {
		this.map = map;
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
	public int getTileId(int x, int y, int layer){
		return map.getTileId( x, y, layer);
	}
	public boolean onWall(Vector2f pos,Shape shape){
		float x = pos.getX();
		float y = pos.getY();
		int id = getTileId((int)x,(int) y, 1);
		if (id != 0){
			Shape test = new Rectangle((int)x,(int)y,1,1);
			if (shape.intersects(test)){
				return true;
			}else return false;
		}return false;
	}
	public void update() {
		
	}
}
