package rogueshadow.XionEngine;

import org.newdawn.slick.tiled.*;
import rogueshadow.XionEngine.Player;

public class Level {
	TiledMap map;
	Player player;
	int x = 0;
	int y = 0;
	
	public Level(TiledMap map, Player player) {
		this.map = map;
		this.player = player;
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
	public void render(int x, int y){
		map.render(-x,-y,0);
		map.render(-x,-y,1);
		player.draw(-x,-y);
		map.render(-x, -y, 2);
	}
	public void movePlayer(int deltax, int deltay){
		if (! onWall( player.getX() + deltax, player.getY()))
			player.movePlayer(deltax, 0);

		if (! onWall( player.getX(), player.getY() + deltay ))
			player.movePlayer(0, deltay);

	}
	public boolean onWall(int x, int y){
		int id = getTileId(x/32, y/32, 1);
		if (id == 0)return false; else return true;
	}
}
