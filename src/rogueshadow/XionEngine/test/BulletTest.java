package rogueshadow.XionEngine.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import rogueshadow.XionEngine.Camera;
import rogueshadow.XionEngine.Body;
import rogueshadow.XionEngine.Pulser;

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
	public Camera cam = new Camera(0, 0);
	public GameContainer container;
	public Input input;
	public boolean isKeyDown = false;

	public BulletTest() {
		super("Rogue Shadow's Dots and Pulsers of DOOOM!!!");
	}

	public Integer getMouseX() {
		return cam.getWorldX(input.getMouseX());
	}

	public Integer getMouseY() {
		return cam.getWorldY(input.getMouseY());
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		container.setVSync(true);
		container.setAlwaysRender(true);
		input = container.getInput();
	}

	public Boolean isKeyDown(int key) {
		return input.isKeyDown(key);
	}

	public Boolean isMouseButtonDown(int button) {
		return input.isMouseButtonDown(button);
	}

	@Override
	public void mouseWheelMoved(int change) {
		cam.setZoom((cam.getZoom() + (change / 5000f)));
	}

	public float rand(float max) {
		Random rnd = new Random();
		float i = rnd.nextFloat() * max;
		return i;
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		for (Body b : bodies)
			b.render(g, cam);
		g.setColor(Color.white);
		g.drawString((String) "Bodies: " + Integer.toString(bodies.size())
				+  " Left: Add bodies ",
				10, 25);
		g.drawString(
				(String) "E: Clear bodies, MouseWheel: Zoom,  WASD: Move",
				10, 40);
		g.drawString("ResetZoom: Z" , 10, 55);
	}

	public void addBody(int x, int y, float angle, int life, float speed) {
		bodies.add(new Body(x, y, angle, life, speed));
	}
	public void keyPressed(int key,char c){
		System.out.print(Integer.toString(key) + " " + c);
	}
	public void update(GameContainer container, int delta)
			throws SlickException {
		if (isKeyDown(Input.KEY_Z)){
			cam.setZoom(1);
			cam.setCam(0,0);
		}
		if (isKeyDown(Input.KEY_E))
			bodies.clear();
		if (isKeyDown(Input.KEY_W))
			cam.moveCam(0, -((int) (cam.getZoom() * 10)));
		if (isKeyDown(Input.KEY_A))
			cam.moveCam(-((int) (cam.getZoom() * 10)), 0);
		if (isKeyDown(Input.KEY_S))
			cam.moveCam(0, ((int) (cam.getZoom() * 10)));
		if (isKeyDown(Input.KEY_D))
			cam.moveCam(((int) (cam.getZoom() * 10)), 0);
		if (isMouseButtonDown(1) && !isKeyDown) {
			isKeyDown = true;
		}
		if (!isMouseButtonDown(1) && !isMouseButtonDown(2) && isKeyDown)
			isKeyDown = false;
		if (isKeyDown(Input.KEY_ESCAPE))
			container.exit();
		if (isMouseButtonDown(0)) {
			for (int i = 0; i < 1; i++) {
				addBody(getMouseX(), getMouseY(), 0, 30000, 0);
			}
		}
		if (isMouseButtonDown(2) && !isKeyDown) {
			isKeyDown = true;
		}
		for (Iterator<Body> i = bodies.iterator(); i.hasNext();) {
			Body b = i.next();
			if (!b.update(delta))
				i.remove();
		}
	}
}
