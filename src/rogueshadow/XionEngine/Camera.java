package rogueshadow.XionEngine;


public class Camera {
	int x;
	int y;
	double zoom = 1;
	public Camera(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int[] moveCam(int deltax, int deltay){
		this.x -= deltax;
		this.y -= deltay;
		return new int[] {this.x,this.y};
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getScreenX(int worldX){
		return (int)(x-(worldX*zoom));
	}
	public int getScreenY(int worldY){
		return (int)(y-(worldY*zoom));
	}
	public int getWorldX(int screenX){
		return (int)((x/zoom)-screenX/zoom);
	}
	public int getWorldY(int screenY){
		return (int)((y/zoom)-screenY/zoom);
	}
	public double getZoom(){
		return zoom;
	}
	public void setZoom(double zoom){
		double change = (this.zoom-zoom)*250;
		this.zoom = zoom;
		this.x += change;
		this.y += change;
	}
	public void setCam(int x, int y) {
		this.x = x;
		this.y = y;
		
	}
}
