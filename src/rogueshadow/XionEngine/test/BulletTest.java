package rogueshadow.XionEngine.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import rogueshadow.XionEngine.Body;
import rogueshadow.XionEngine.Camera;

/**
 * 
 * @author Adam
 */
public class BulletTest extends BasicGame {
	public static int HEIGHT = 600;
	public static int WIDTH = 800;
	public static void main(String[] argv) throws SlickException {
		AppGameContainer container = new AppGameContainer(new BulletTest(),
				WIDTH, HEIGHT, false);
		container.start();
	}
	public ArrayList<Body> bodies = new ArrayList<Body>();
	public GameContainer container;
	public Input input;
	public boolean isKeyDown = false;
	public Image img;
	public Camera cam = new Camera(0,0);

	public BulletTest() {
		super("Rogue Shadow's Dots and Pulsers of DOOOM!!!");
	}

	public float getMouseX() {
		return cam.getWorldX(input.getMouseX());
	}

	public float getMouseY() {
		return cam.getWorldY(input.getMouseY());
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		container.setVSync(true);
		container.setAlwaysRender(true);
		input = container.getInput();
		img = new Image(32,32);
		Graphics g = img.getGraphics();
		g.setColor(Color.white);
		g.fillOval(0, 0, 16, 16);
		g.flush();
	}

	public Boolean isKeyDown(int key) {
		return input.isKeyDown(key);
	}

	public Boolean isMouseButtonDown(int button) {
		return input.isMouseButtonDown(button);
	}

	public float rand(float max) {
		Random rnd = new Random();
		float i = rnd.nextFloat() * max;
		return i;
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		for (Body b : bodies){
			float x = cam.getScreenX(b.getX()-8);
			float y = cam.getScreenY(b.getY()-8);
			img.draw(x,y,cam.getZoom()*(b.getMass()/10000f),b.getColor());
			g.setColor(Color.white);
			//g.drawString(Integer.toString((int)b.getMass()),cam.getScreenX(b.getX())-16,cam.getScreenY(b.getY())+16);
			
		}
		g.drawString(Integer.toString(bodies.size()), 10, 40);
	}
	public void mouseWheelMoved(int change){
		float zoom = (change < 0) ? 0.05f:-0.05f;
		zoom *= cam.getZoom();
		cam.changeZoom(zoom);
	}
	public void update(GameContainer container, int delta)
			throws SlickException {

		int speed = (int)(100*cam.getZoom());
		if (isKeyDown(Input.KEY_W))cam.moveCam(0,speed);
		if (isKeyDown(Input.KEY_S))cam.moveCam(0,- speed);
		if (isKeyDown(Input.KEY_A))cam.moveCam(speed, 0);
		if (isKeyDown(Input.KEY_D))cam.moveCam(-speed, 0);
		if (isKeyDown(Input.KEY_Z))cam.setZoom(cam.getZoom()+0.01f);
		if (isKeyDown(Input.KEY_X))cam.setZoom(cam.getZoom()-0.01f);
		if (isMouseButtonDown(0)){
			for (int i = 0; i < 1; i++ ){
				Vector2f vec = new Vector2f(Math.toDegrees(Math.random()*Math.PI*2)).scale(0.01f);
				bodies.add(new Body(getMouseX()-8,getMouseY()-8,vec,30000,(float)(1000f+Math.random()*50000f)));
			}
		}
		if (isMouseButtonDown(1)){
			bodies.add(new Body(getMouseX(),getMouseY(),new Vector2f().scale(0),40000,100000f));
		}
		for (Iterator<Body> i = bodies.iterator(); i.hasNext();) {
			Body b1 = i.next();
			for (Body b2: bodies){
				if (b1 == b2)continue;
				b1.push(b1.getVectorToOther(b2));
			}
			if (!b1.update(delta))
				i.remove();
		}
	}
}
