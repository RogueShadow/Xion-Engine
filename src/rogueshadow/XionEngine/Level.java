package rogueshadow.XionEngine;

import org.newdawn.slick.tiled.*;
import rogueshadow.XionEngine.Player;

public class Level {
	TiledMap map;
	Player player;
	
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
	public void movePlayer(int deltax, int deltay){
		if (! onWall( player.getPos().getX() + deltax - 16, player.getPos().getY()-16))
			player.movePlayer(deltax, 0);

		if (! onWall( player.getPos().getX()-16, player.getPos().getY() + deltay -16))
			player.movePlayer(0, deltay);

	}
	public boolean onWall(float x, float y){
		int id = getTileId((int)x/getTileWidth(),(int) y/getTileHeight(), 1);
		if (id == 0)return false; else return true;
	}
	public void update() {

		
	}
}
