package rogueshadow.XionEngine;


public class Pulser extends Entity {
	public static final int ATTRACTER = 0;
	public static final int REPULSER = 1;
	public static final Integer MAX_STRENGTH = 50;

	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPow() {
		return pow;
	}

	public void setPow(int pow) {
		this.pow = pow;
	}

	int type; // 0 - attractor. 1 - repulsor 
	int pow; // 0-100, strength of force
	
	public Pulser(double x, double y, int type, int pow) {
		super(x, y);
		this.type = type;
		this.pow = pow;
	}

}
