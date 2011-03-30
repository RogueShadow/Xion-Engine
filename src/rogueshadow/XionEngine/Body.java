package rogueshadow.XionEngine;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class Body extends Entity {
	public static final int DOT = 0;
	public static final int REPULSER = 1;
	public static final int ATTRACTER = 2;
	
	int type = Body.DOT;
	int life = 0;
	int lived = 0;
	int radius = 8;
	float strength = 1f;
	Color color = Color.white;
	Shape shape = new Circle(0,0,this.radius);
	public Body(float x, float y){
		super(x,y);
		setType(Body.DOT);
	}
	public Body(float x, float y, Vector2f vel){
		super(x,y,vel);
		setType(Body.DOT);
	}
	public Body(float x, float y, int type){
		super(x,y);
		setType(type);
	}
	public Body(float x, float y, Vector2f vel, int life, float strength){
		super(x,y,vel);
		this.life = life;
		this.strength = strength;
		setType(0);
	}
	public void setLife(int life){
		this.life = life;
	}
	public boolean update(int delta){
		if (this.life == 0){
			super.update(delta);
			this.shape.setLocation(this.pos);
			return true;
		}
		if (this.lived < this.life){
			super.update(delta);
			this.shape.setLocation(this.pos);
			this.lived += delta;
			return true;
		}else{
			return false;
		}
	}
	public Color getColor(){
		return this.color;
	}
	public void setColor(float r, float g, float b){
		this.color = new Color(r,g,b,color.a);
	}
	public float getStrength(){
		return this.strength;
	}
	public void	setStrength(float strength){
		this.strength = strength;
	}
	public boolean isColliding(Body b){
		if (this.shape.intersects(b.getShape())){
			return true;
		}else return false;
	}
	private Shape getShape() {
		return this.shape;
	}
	public float getForce(Body b){
		float dist = this.getPos().distance(b.getPos());
		return (b.getStrength()/(dist*dist));
	}
	public void applyForce(Body b){
		if (b.getType() == Body.ATTRACTER)this.push(this.getVector(b).scale(this.getForce(b)));
		if (b.getType() == Body.REPULSER)this.pull(this.getVector(b).scale(this.getForce(b)));
	}
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type = type;
		if (type == 0)this.color = new Color((float)Math.random(),(float)Math.random(),(float)Math.random(),1f);
		if (type == 1)this.color = Color.green;
		if (type == 2)this.color = Color.red;
	}
}
