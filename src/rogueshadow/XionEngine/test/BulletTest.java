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


/**
 *
 * @author Adam
 */
public class BulletTest extends BasicGame {

	public Input input;
    public GameContainer container;
    public ArrayList<Projectile> bullets = new ArrayList<Projectile>();
    public Image background;
    public Image bullet;


    public static void main(String[] argv) throws SlickException {
	AppGameContainer container = new AppGameContainer(new BulletTest(), 800, 600, false);
	container.start();
    }
    
    public BulletTest(){
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
	public void update(GameContainer container, int delta) throws SlickException {

    	if (input.isMouseButtonDown(0)){
    		int shots = 150;
    		
    		for (int i = 0; i < shots; i++){
    		shootBullet(input.getMouseX(),input.getMouseY(),  rand(360f), 50 + (int)(100*rand(1f)),rand(2f));
    	
    		}
    	}
    		
    	for (Iterator<Projectile> i = bullets.iterator(); i.hasNext(); ){
    		Projectile b = i.next();
    		if (!b.update()){
    			i.remove();
    		}
    	}
    	
    }
    
    public void shootBullet(int x, int y, float angle, int life, float speed){
  
    	bullets.add(new Projectile(x,y,angle,life,speed,new Color(rand(1),rand(1),rand(1),1)));
    	
    }

    public float rand(float max){
    	Random rnd = new Random();
    	float i = rnd.nextFloat()*max;
    	return i;
    }

    @Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.drawImage(background, 0, 0);
		for (Iterator<Projectile> i = bullets.iterator(); i.hasNext(); 	){
			Projectile b = i.next();
			float x = b.getX();
			float y = b.getY();
			

			bullet.draw(x, y, b.getScale(),b.getColor());
		
			
		}
		g.drawString((String) Integer.toString(bullets.size()), 30, 30);
		
    }
}
