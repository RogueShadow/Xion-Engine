package rogueshadow.XionEngine;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class XionGame extends BasicGame {
	
	public static Integer width = 800;
	public static Integer height = 600;
	public Input input;
	public GameContainer container;
	public Level levels;
	public Player players;
	public String startLevel = "res/xion_graal.tmx";
	public int currentLevel = 0;
	
	public static void main(String[] argv) throws SlickException {
		AppGameContainer container = new AppGameContainer(new XionGame("Xion Engine Test"),
				width, height, false);
		container.setTargetFrameRate(60);
		container.setVSync(true);
		container.start();
	}
	
	public XionGame(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	
}
