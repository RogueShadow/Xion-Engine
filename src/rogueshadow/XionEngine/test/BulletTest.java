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

import rogueshadow.XionEngine.Projectile;
import rogueshadow.XionEngine.Pulser;

/**
 * 
 * @author Adam
 */
public class BulletTest extends BasicGame {

	public Input input;
	public GameContainer container;
	public ArrayList<Projectile> bullets = new ArrayList<Projectile>();
	public ArrayList<Pulser> gravs = new ArrayList<Pulser>();
	public Image background;
	public Image bullet;
	public float angle = 0;
	public float dx = 0;
	public float dy = 0;
	public float dist = 0;
	public int strength = 50;
	public boolean isKeyDown = false;

	public static void main(String[] argv) throws SlickException {

		AppGameContainer container = new AppGameContainer(new BulletTest(),
				800, 600, false);
		container.start();
	}

	public BulletTest() {
		super("Test Game");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		container.setVSync(true);

		input = container.getInput();
		background = new Image("res/star_field.png");
		bullet = new Image("res/bullet.png");
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		
		if (input.isKeyDown(Input.KEY_Q)){
			gravs.clear();
		}
		if (input.isKeyDown(Input.KEY_W)){
			bullets.clear();
		}
		if (input.isKeyDown(Input.KEY_A)){
			if (strength < 100)strength++;
		}
		if (input.isKeyDown(Input.KEY_Z)){
			if (strength > 0)strength--;
		}
		if (input.isMouseButtonDown(1) && !isKeyDown) {
			isKeyDown = true;
			gravs.add(new Pulser(input.getMouseX(), input.getMouseY(), 0, strength));
		}
		if (!input.isMouseButtonDown(1) && !input.isMouseButtonDown(2) && isKeyDown)isKeyDown = false;
		
		if (input.isKeyDown(Input.KEY_ESCAPE))
			container.exit();
		if (input.isMouseButtonDown(0)) {
					shootBullet(input.getMouseX(), input.getMouseY(),
							rand(360f), 600, .5f);
		}
		if (input.isMouseButtonDown(2) && !isKeyDown){
			isKeyDown = true;
			gravs.add(new Pulser(input.getMouseX(), input.getMouseY(), 1, strength));
		}

		for (Pulser p : gravs) {
			for (Iterator<Projectile> i = bullets.iterator(); i.hasNext();) {
				Projectile b = i.next();
				float dx = b.getX() - p.getX();
				float dy = b.getY() - p.getY();
				float d = dx*dx + dy*dy;
				d = (float) Math.pow(d, 0.5f);
				float pow = (1f/d)*(p.getPow()/5f);
				float angle = (float) Math.atan2(dx, dy);
				if (p.getType() == 0){
					b.pull(angle,pow);
				}else if (p.getType() == 1){
					b.push(angle,pow);
				}
			}
		}

		for (Iterator<Projectile> i = bullets.iterator(); i.hasNext();) {
			Projectile b = i.next();
			if (!b.update())
				i.remove();
		}

	}

	public void shootBullet(int x, int y, float angle, int life, float speed) {

		bullets.add(new Projectile(x, y, angle, life, speed));

	}

	public float rand(float max) {
		Random rnd = new Random();
		float i = rnd.nextFloat() * max;
		return i;
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.drawImage(background, 0, 0);
		for (Projectile b: bullets) {
			float x = b.getX();
			float y = b.getY();
			bullet.draw(x, y, b.getScale(),b.getColor());
		}
		for (Pulser p: gravs) {
			if (p.getType() == 0){
			bullet.draw(p.getX() - (bullet.getWidth() / 2), p.getY()
					- (bullet.getHeight() / 2), 1f + (p.getPow()/100f)*2,Color.green);
			g.drawString(Integer.toString(p.getPow()), p.getX(), p.getY()+bullet.getHeight()+10);
			}
			if (p.getType() == 1){
				bullet.draw(p.getX() - (bullet.getWidth() / 2), p.getY()
						- (bullet.getHeight() / 2), 1f + (p.getPow()/100f)*2,Color.red);
				g.drawString(Integer.toString(p.getPow()), p.getX(), p.getY()+bullet.getHeight()+10);
				}
		}
		g.drawString((String) "Dots: " + Integer.toString(bullets.size()) + " Left: Add dots  Right: Add Attracter  Left: Add Repulser", 10, 25);
		g.drawString((String) "Q: Clear Pulsers W: Clear dots A: +str Z: -str", 10, 40);
		g.drawString("Strength: " + Integer.toString(strength), 10, 55);
		

	}
}
