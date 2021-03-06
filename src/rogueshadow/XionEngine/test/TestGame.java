package rogueshadow.XionEngine.test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import rogueshadow.XionEngine.Camera;
import rogueshadow.XionEngine.Level;
import rogueshadow.XionEngine.Player;

//TODO add networking support

/**
 *
 * @author Adam
 */
public class TestGame extends BasicGame {
    public static  final Integer HEIGHT = 600;
    public static  final Integer WIDTH = 800;
	public static GameContainer container;
    public Input input;
    public boolean debugmode = true;
    public ArrayList<Level> levels = new ArrayList<Level>();
    public ArrayList<Player> players = new ArrayList<Player>();
    public Camera cam;
    public int currentLevel = 0;
    public int currentPlayer = 0;
    public UnicodeFont debug;
	DecimalFormat df = new DecimalFormat("00.00");
	public String ds; // debug info string
	
    public Level getCurrentLevel(){
    	return levels.get(currentLevel);
    }
	public ArrayList<Player> getPlayers() {
		return players;
	}
    public boolean setCurrentLevel(String level){
    	int levelIndex = -1;
    	for (Level l: levels){
    		if (l.getName().equals(level)){
    			levelIndex = levels.indexOf(l);
    			break;
    		}
    	}
    	if (levelIndex != -1){
    		currentLevel = levelIndex;
    		cam = new Camera(WIDTH,HEIGHT,getCurrentLevel().getWidth(),getCurrentLevel().getHeight(),getCurrentLevel().getTileHeight());
    		for (Player p: players){
    			p.setLevel(getCurrentLevel());
    		}
    		return true;
    	}
    	return false;
    }
    public Level loadLevel(String tmxFile) throws SlickException {
    	String[] lvlname = tmxFile.split("/");
    	String lvlname2 = lvlname[lvlname.length-1];
    	lvlname2 = lvlname2.substring(0, lvlname2.length()-4);
    	if (debugmode)System.out.println("Loaded Level: " + lvlname2);
    	return new Level(new TiledMap(tmxFile),lvlname2);
    }


	@SuppressWarnings("unchecked")
	@Override
    public void init(GameContainer container) throws SlickException {
        input = container.getInput();
        debug = new UnicodeFont("C:\\Windows\\Fonts\\Arial.ttf",12,false,false);
        debug.getEffects().add(new ColorEffect(java.awt.Color.white));
        debug.addAsciiGlyphs();
        debug.loadGlyphs();
    	levels.add(loadLevel("res/xion_graal.tmx"));
    	levels.add(loadLevel("res/xion_graal.tmx"));
    	levels.add(loadLevel("res/platform_test.tmx"));
    	players.add(new Player("Giant Jack",3,6));
    	players.add(new Player("Test",2,2));
    	players.add(new Player("Tiny Tim",1,1));
    	players.add(new Player("Odd Eye", 1.6f, 1.3f));
    	if (!setCurrentLevel("platform_test")){
    		System.out.println("Failed to set level");
    		container.exit();
    	}
    }
    @Override
	public void render(GameContainer container, Graphics g) throws SlickException {;
		
		cam.translateIn(g);
        levels.get(currentLevel).getMap().render(0, 0,0);
        levels.get(currentLevel).getMap().render(0, 0, 1);
        for (Player p: players){
        	p.render(g);
        }
        levels.get(currentLevel).getMap().render(0, 0, 2);
       // levels.get(currentLevel).drawCollision(g);
        cam.translateOut(g);
        g.drawString("Intersects?: " + ds, 10, 30);
    }
    @Override
	public void update(GameContainer container, int delta) throws SlickException {
        Player p = players.get(currentPlayer);
        input = container.getInput();
        if (input.isKeyDown(Input.KEY_UP) && p.canJump())p.jump();
        if (input.isKeyDown(Input.KEY_DOWN))p.moveDown();
        if (input.isKeyDown(Input.KEY_LEFT))p.moveLeft();
        if (input.isKeyDown(Input.KEY_RIGHT))p.moveRight();
        if (input.isKeyDown(Input.KEY_Q))currentPlayer = ((currentPlayer + 1) % players.size()) ;
        if (input.isKeyDown(Input.KEY_ESCAPE))container.exit();
        levels.get(currentLevel).update(delta);
        for (Player ps: players){
        	ps.update(delta);
        }
        cam.focusCam(p.getPos());
    }
    public TestGame(){
        super("Great Tiles of Doom");
    }
    public static void main(String[] argv) throws SlickException {
    	AppGameContainer container = new AppGameContainer(new TestGame(), WIDTH, HEIGHT, false);
    	container.setVSync(true);
    	container.setTargetFrameRate(60);
    	container.start();
    }
}
