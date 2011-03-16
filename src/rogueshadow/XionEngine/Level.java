package rogueshadow.XionEngine;

import org.newdawn.slick.tiled.*;
import rogueshadow.XionEngine.Player;
import rogueshadow.XionEngine.test.TestGame;

public class Level {
	TiledMap map;
	Player player;
	int x = 16;
	int y = 16;
	
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
		map.render(0, 0, x/getTileWidth(), y/getTileHeight(), TestGame.getWidth()/getTileWidth(), TestGame.getHeight()/getTileHeight(), 0, false);
		map.render(0, 0, x/getTileWidth(), y/getTileHeight(), TestGame.getWidth()/getTileWidth(), TestGame.getHeight()/getTileHeight(), 1, false);

		player.draw(-x,-y);
		map.render(0, 0, x/getTileWidth(), y/getTileHeight(), TestGame.getWidth()/getTileWidth(), TestGame.getHeight()/getTileHeight(), 2, false);

	}
	public void movePlayer(int deltax, int deltay){
		if (! onWall( player.getX() + deltax - 16, player.getY()-16))
			player.movePlayer(deltax, 0);

		if (! onWall( player.getX()-16, player.getY() + deltay -16))
			player.movePlayer(0, deltay);

	}
	public boolean onWall(int x, int y){
		int id = getTileId(x/getTileWidth(), y/getTileHeight(), 1);
		if (id == 0)return false; else return true;
	}
}
