package rogueshadow.XionEngine;


public class Camera {
	int x;
	int y;
	int zoom = 64;
	public Camera(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int[] moveCam(int deltax, int deltay){
		this.x += deltax;
		this.y += deltay;
		return new int[] {this.x,this.y};
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getScreenX(int worldX){
		return (worldX + x*zoom);
	}
	public int getScreenY(int worldY){
		return (worldY + y*zoom);
	}
	public int getWorldX(int screenX){
		return (screenX - x*zoom);
	}
	public int getWorldY(int screenY){
		return (screenY - y*zoom);
	}
	public int getZoom(){
		return zoom;
	}
	public void setZoom(int zoom){
		this.zoom = zoom;
	}
}
