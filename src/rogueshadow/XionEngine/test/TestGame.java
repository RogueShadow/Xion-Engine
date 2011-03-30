package rogueshadow.XionEngine.test;

import java.util.ArrayList;

import org.newdawn.slick.tiled.*;
import org.newdawn.slick.Input;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import rogueshadow.XionEngine.Camera;
import rogueshadow.XionEngine.Player;
import rogueshadow.XionEngine.Level;

//TODO add networking support

/**
 *
 * @author Adam
 */
public class TestGame extends BasicGame {

    public static  final Integer HEIGHT = 600;
    public static  final Integer WIDTH = 800;
    public static void main(String[] argv) throws SlickException {
    	AppGameContainer container = new AppGameContainer(new TestGame(), WIDTH, HEIGHT, false);
    	container.setVSync(true);
    	container.setTargetFrameRate(60);
    	container.start();
    }
	public GameContainer container;
    public Input input;
    public ArrayList<Level> levels = new ArrayList<Level>();
    public Camera cam = new Camera(400,300,WIDTH,HEIGHT);
    public Player player;
    public int currentLevel = 0;
    
    public TestGame(){
        super("Great Tiles of Doom");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        input = container.getInput();
    	Level level = new Level(new TiledMap("res/xion_graal.tmx"));
    	levels.add(level);
    	player = new Player("Rogue",50,50,new Image("res/background.png"));
    	
    }

 
    @Override
	public void render(GameContainer container, Graphics g) throws SlickException {;
        cam.translateIn(g);
        levels.get(currentLevel).getMap().render(0,0);
        cam.translateOut(g);
    }

    @Override
	public void update(GameContainer container, int delta) throws SlickException {
        Integer speed = 10;
        Integer buffer = 50;
        input = container.getInput();
        if (input.isKeyDown(Input.KEY_UP))cam.moveCam(0,-speed);
        if (input.isKeyDown(Input.KEY_DOWN))cam.moveCam(0, speed);
        if (input.isKeyDown(Input.KEY_LEFT))cam.moveCam(-speed, 0);
        if (input.isKeyDown(Input.KEY_RIGHT))cam.moveCam(speed, 0);
        if (input.isKeyDown(Input.KEY_ESCAPE))container.exit();
        levels.get(currentLevel).update();
    }
}
