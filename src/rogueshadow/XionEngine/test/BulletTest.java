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
import rogueshadow.XionEngine.Projectile;
import rogueshadow.XionEngine.Pulser;

/**
 * 
 * @author Adam
 */
public class BulletTest extends BasicGame {

	public Input input;
	public GameContainer container;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public ArrayList<Projectile> bullets = new ArrayList<Projectile>();
	public ArrayList<Pulser> gravs = new ArrayList<Pulser>();
	public float angle = 0;
	public float dx = 0;
	public float dy = 0;
	public float dist = 0;
	public int strength = (int) (Pulser.MAX_STRENGTH.floatValue()/2f);
	public boolean isKeyDown = false;
	public Camera cam = new Camera(0,0);
	

	public Integer getMouseX(){
		return cam.getWorldX(input.getMouseX());
	}
	public Integer getMouseY(){
		return cam.getWorldY(input.getMouseY());
	}
	public Boolean isKeyDown(int key){
		return input.isKeyDown(key);
	}
	public Boolean isMouseButtonDown(int button){
		return input.isMouseButtonDown(button);
	}
	
	public static void main(String[] argv) throws SlickException {

		AppGameContainer container = new AppGameContainer(new BulletTest(),
				800, 600, false);
		container.start();
	}

	public BulletTest() {
		super("Rogue Shadow's Dots and Pulsers of DOOOM!!!");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		container.setVSync(true);
		input = container.getInput();
	}

	@Override
	public void mouseWheelMoved(int change){
		cam.setZoom((cam.getZoom() + (change/5000f)));
		System.out.println(Double.toString(cam.getZoom()));
		if (strength > Pulser.MAX_STRENGTH)strength = Pulser.MAX_STRENGTH;
		if (strength < 1)strength = 1;
	}
	public void update(GameContainer container, int delta)
			throws SlickException {
		if (isKeyDown(Input.KEY_Q))gravs.clear();
		if (isKeyDown(Input.KEY_E))bullets.clear();
		if (isKeyDown(Input.KEY_W))cam.moveCam(0, -((int)(cam.getZoom()*10)));
		if (isKeyDown(Input.KEY_A))cam.moveCam(-((int)(cam.getZoom()*10)), 0);
		if (isKeyDown(Input.KEY_S))cam.moveCam(0, ((int)(cam.getZoom()*10)));
		if (isKeyDown(Input.KEY_D))cam.moveCam(((int)(cam.getZoom()*10)),0);
		

		if (isMouseButtonDown(1) && !isKeyDown) {
			isKeyDown = true;
			gravs.add(new Pulser(getMouseX(), getMouseY(), 0, strength));
		}
		if (!isMouseButtonDown(1) && !isMouseButtonDown(2) && isKeyDown)isKeyDown = false;
		
		if (isKeyDown(Input.KEY_ESCAPE))
			container.exit();
		if (isMouseButtonDown(0)) {
			for (int i = 0; i < 1; i ++){
					shootBullet(getMouseX(), getMouseY(),
							0,30000, 0);
			}
		}
		if (isMouseButtonDown(2) && !isKeyDown){
			isKeyDown = true;
			gravs.add(new Pulser(getMouseX(), getMouseY(), 1, strength));
		}

		for (Pulser p : gravs) {
			for (Iterator<Projectile> i = bullets.iterator(); i.hasNext();) {
				Projectile b = i.next();
				double dx = b.getX() - p.getX();
				double dy = b.getY() - p.getY();
				double d = dx*dx + dy*dy;
				d =  Math.pow(d, 0.5f);
				double pow = (1/d)*(p.getPow()*5d);
				double angle =  Math.atan2(dx, dy);
				pow = (p.getType() == Pulser.ATTRACTER) ? -1:1;
				b.push(angle,pow);
			}
		}

		for (Iterator<Projectile> i = bullets.iterator(); i.hasNext();) {
			Projectile b = i.next();
			if (!b.update(delta))
				i.remove();
		}

	}

	public void shootBullet(int x, int y, float angle, int life, float speed) {

		Projectile p = new Projectile(x, y, angle, life, speed);
		//p.track(true);
		bullets.add(p);
		

	}

	public float rand(float max) {
		Random rnd = new Random();
		float i = rnd.nextFloat() * max;
		return i;
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {

		for (Projectile b: bullets) {
			b.render(g,cam);
		}

		for (Pulser p: gravs) {
			p.render(g,cam);
		}
		g.setColor(Color.white);
		g.drawString((String) "Dots: " + Integer.toString(bullets.size()) + " Pulsers: " + Integer.toString(gravs.size()) + " Left: Add dots  Right: Add Attracter  Left: Add Repulser", 10, 25);
		g.drawString((String) "Q: Clear Pulsers, E: Clear dots, MouseWheel: Zoom,  WASD: Move", 10, 40);
		g.drawString("Strength: " + Integer.toString(strength), 10, 55);
		

	}
}
