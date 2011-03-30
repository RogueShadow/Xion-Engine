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
	public Image img;
	public Camera cam = new Camera(0,0,WIDTH,HEIGHT);
	public float strength = 1;

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
		g.fillOval(8, 8, 16, 16);
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
		cam.translateIn(g);
		for (Body b : bodies){
			float x = b.getX()+16;
			float y = b.getY()+16;
			img.draw(x,y,b.getColor());
			
			
		}
		cam.translateOut(g);
		g.setColor(Color.white);
		g.drawString(Integer.toString(bodies.size()), 10, 40);
		g.drawString("Str: " + Float.toString(strength),10,55);
		
	}
	public void mouseWheelMoved(int change){
		float zoom = (change < 0) ? 0.05f:-0.05f;
		zoom *= cam.getZoom();
		cam.changeZoom(zoom);
	}
	public void keyPressed(int key, char c) {

	}
	public void keyReleased(int key, char c){
		
	}
	public void mouseClicked(int button, int x, int y, int clickCount) {
		if (button == 1){
			Body b = new Body(getMouseX(),getMouseY(),Body.ATTRACTER);
			b.setStrength(strength);
			bodies.add(b);
		}
		if (button == 2){
			Body b = new Body(getMouseX(),getMouseY(),Body.REPULSER);
			b.setStrength(strength);
			bodies.add(b);
		}
	}
	public void update(GameContainer container, int delta)
			throws SlickException {
		int speed = (int)(100*cam.getZoom());
		if (isKeyDown(Input.KEY_ESCAPE))container.exit();
		if (isKeyDown(Input.KEY_W))cam.moveCam(0,-speed);
		if (isKeyDown(Input.KEY_S))cam.moveCam(0,speed);
		if (isKeyDown(Input.KEY_A))cam.moveCam(-speed, 0);
		if (isKeyDown(Input.KEY_D))cam.moveCam(speed, 0);
		if (isKeyDown(Input.KEY_Q))bodies.clear();
		if (isKeyDown(Input.KEY_Z)){
			strength -= 0.01f;
			if (strength <= 0.1f)strength = 0.1f;
		}
		if (isKeyDown(Input.KEY_X)){
			strength += 0.1f;
			if (strength >= 200)strength = 200;
		}
		if (isMouseButtonDown(0)){
			
			Body b = new Body(getMouseX(),getMouseY());
			b.setLife(10000);
			bodies.add(b);
		}

		for (Iterator<Body> i = bodies.iterator(); i.hasNext();) {
			Body b1 = i.next();
			if (b1.getType() == 0){
				for (Body b2: bodies){
					if (b2.getType() == Body.DOT)continue;
					if (b1 == b2)continue;
					if (b1.isColliding(b2)){
						b1.setColor(0, 0, 1f);
						b1.getPos().add(b1.getVector(b2));
					}else{
						b1.applyForce(b2);
					}
				}
			}
			if (!b1.update(delta)) i.remove();
		}
	}
}
