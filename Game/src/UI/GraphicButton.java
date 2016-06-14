package UI;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;

import Util.Window;

public class GraphicButton 
{
	private Image buttonUp;
	private Image buttonHover;
	private Image currentImage;
	private float width;
	private float height;
	
	private float x;
	private float y;
	
	public GraphicButton(Image buttonUp, Image buttonHover, float x, float y) {
		this.buttonUp = buttonUp;
		this.buttonHover = buttonHover;
		this.currentImage = buttonUp;
		this.x = x;
		this.y = y;
		this.width = buttonUp.getWidth();
		this.height = buttonUp.getHeight();
	}
	
	public GraphicButton(Image buttonUp, Image buttonHover, float x, float y, String text) {
		this.buttonUp = buttonUp;
		this.buttonHover = buttonHover;
		this.x = x;
		this.y = y;
		this.currentImage = buttonUp;
		this.width = buttonUp.getWidth();
		this.height = buttonUp.getHeight();
	}
	
	public boolean contains(float x, float y) {
		System.out.println("does it contain?: " + (x <= this.x + this.width
				&& y <= this.y + this.height
				&& x >= this.x
				&& y >= this.y));
		
		System.out.println("Mouse values: " + Mouse.getX() + " " + Mouse.getY());
		
		return x <= this.x + this.width
				&& y <= this.y + this.height
				&& x >= this.x
				&& y >= this.y;
	}
	
	public boolean contains(Point p) {
		return new Polygon( new float[] { x, y, x+width, y, x, y+height, x+width, y+height } ).contains(p);
	}
	
	public void draw(Graphics g) {
		setCurrentImage(contains(Mouse.getX(), Window.HEIGHT - Mouse.getY()));
		g.drawImage(currentImage, x, y);
	}
	
	
	
	public void setCurrentImage(boolean isHovering) {
		this.currentImage = isHovering ? buttonHover : buttonUp;
	}

}
