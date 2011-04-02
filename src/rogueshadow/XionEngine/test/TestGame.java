package rogueshadow.XionEngine.test;

import java.text.DecimalFormat;
import java.util.ArrayList;

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
    public static void main(String[] argv) throws SlickException {
    	AppGameContainer container = new AppGameContainer(new TestGame(), WIDTH, HEIGHT, false);
    	container.setVSync(true);
    	container.setTargetFrameRate(60);
    	container.start();
    }
	public static GameContainer container;
    public Input input;
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
    public void setCurrentLevel(int i){
    	currentLevel = i;
    	//TODO add code to attempt to find and load a level.
    }
    public TestGame(){
        super("Great Tiles of Doom");
    }
    public Level loadLevel(String tmxFile) throws SlickException {
    	return new Level(new TiledMap(tmxFile));
    }

    @SuppressWarnings("unchecked")
	@Override
    public void init(GameContainer container) throws SlickException {
        input = container.getInput();
        debug = new UnicodeFont("C:\\Windows\\Fonts\\Arial.ttf",12,false,false);
        debug.getEffects().add(new ColorEffect(java.awt.Color.white));
        debug.addAsciiGlyphs();
        debug.loadGlyphs();
    	Level level = loadLevel("res/xion_graal.tmx");
    	Player player = new Player("Rogue",50,50,new Rectangle(0,0,2,3),level);
    	Player p1 = new Player("Shadow",5,5,new Rectangle(0,0,2,2),level);
    	cam = new Camera(0,0,WIDTH,HEIGHT,level.getHeight(),level.getWidth(),level.getTileHeight());
    	levels.add(level);
    	players.add(player);
    	players.add(p1);

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

        cam.translateOut(g);
        g.drawString("Intersects?: " + ds, 10, 30);
    }
    @Override
	public void update(GameContainer container, int delta) throws SlickException {
        Vector2f vel = new Vector2f(0,0);
        input = container.getInput();
        if (input.isKeyDown(Input.KEY_UP))vel.y -= 1;
        if (input.isKeyDown(Input.KEY_DOWN))vel.y += 1;
        if (input.isKeyDown(Input.KEY_LEFT))vel.x -= 1;
        if (input.isKeyDown(Input.KEY_RIGHT))vel.x += 1;
        if (input.isKeyDown(Input.KEY_Q))currentPlayer = ((currentPlayer + 1) % players.size()) ;
        
        
        players.get(currentPlayer).movePlayer(vel);

        if (input.isKeyDown(Input.KEY_ESCAPE))container.exit();
        levels.get(currentLevel).update();
        for (Player p: players){
        	p.update(delta);
        }
        cam.focusCam(players.get(currentPlayer).getPos());
    }
	public ArrayList<Player> getPlayers() {
		return players;
	}
}
