package Util;

public class Camera 
{

	
	private float x;
	private float y;
	
	public Camera()
	{
		this.x = 0;
		this.y = 0;
	}
	
	public void checkPosition()
	{
		float camX;
		float camY;
		
		camX = (float) (GameSessionFactory.getGameSession().getPlayer().getCenterX() - ((float)Window.WIDTH / 2));
		camY = (float) (GameSessionFactory.getGameSession().getPlayer().getCenterY() - ((float)Window.HEIGHT / 2));
		
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
