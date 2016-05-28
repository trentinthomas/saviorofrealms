package Util;

public class Camera 
{
	
	private float offsetMaxX;
	private float offsetMaxY;
	private float offsetMinX = 0;
	private float offsetMinY = 0;
	
	private float x;
	private float y;
	
	public Camera()
	{
		
	}
	
	public Camera(int offsetMaxX, int offsetMaxY)
	{
		this.offsetMaxX = offsetMaxX;
		this.offsetMaxY = offsetMaxY;
		this.x = 0;
		this.y = 0;
	}
	
	public void checkPosition()
	{
		float camX;
		float camY;
		
		camX = (float) (GameSessionFactory.getGameSession().getPlayer().getCenterX() - ((float)Window.WIDTH / 2));
		camY = (float) (GameSessionFactory.getGameSession().getPlayer().getCenterY() - ((float)Window.HEIGHT / 2));
		
		
//		if(camX > offsetMaxX)
//			camX = offsetMaxX;
//		if(camX < offsetMinX)
//			camX = offsetMinX;
//		if(camY > offsetMaxY)
//			camY = offsetMaxY;
//		if(camY < offsetMinY)
//			camY = offsetMinY;
		
		x = camX;
		y = camY;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}

}
