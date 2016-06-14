package saviorOfRealms.HUD;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.GUIContext;

public abstract class HUDComponent {
	
	private boolean isVisible;
	protected Shape hitbox;
	protected GUIContext gc;
	private Image hudImage;
	private int x;
	private int y;
	private int width;
	private int height;
	
	/**
	 * initializes the hud with the guicontext, x and y position, and image of the component. visibility default is false.
	 * @param gc
	 * @param x
	 * @param y
	 * @param image
	 */
	public HUDComponent(GUIContext gc, int x, int y, Image image) {
		this.gc = gc;
		this.hudImage = image;
		this.x = x;
		this.y = y;
		isVisible = false;
		width = hudImage.getWidth();
		height = hudImage.getHeight();
		hitbox = new Rectangle(x, y, width, height);
	}
	
	/**
	 * initializes the hud with the guicontext, x and y position, and image, with visibility
	 * @param gc
	 * @param x
	 * @param y
	 * @param image
	 * @param visibilty
	 */
	public HUDComponent(GUIContext gc, int x, int y, Image image, boolean visibilty) {
		this.gc = gc;
		this.hudImage = image;
		this.x = x;
		this.y = y;
		isVisible = visibilty;
		width = hudImage.getWidth();
		height = hudImage.getHeight();
		hitbox = new Rectangle(x, y, width, height);
	}
	
	
	public void setVisible(boolean visible) {
		this.isVisible = visible;
	}
	
	public boolean containsMouseCoords() {
	    return hitbox.contains( gc.getInput().getMouseX(), gc.getInput().getMouseY() );
	}
	
	public boolean contains(int x, int y) {
		return hitbox.contains( x, y );
	}
	
	/**
	 * draws the hud component on screen, and other things if needed.
	 * @param g
	 */
	public void draw(Graphics g) {
		if(isVisible)
		{
			g.drawImage(hudImage, x, y);
			drawOtherThings(g);
		}
	}
	
	/**
	 * Does an action if there was a click on this HUD object.
	 */
	public abstract void doAction();
	
	/**
	 * Draws other things, such as stats, or players inventory, if the component is visible.
	 * @param g
	 */
	public abstract void drawOtherThings(Graphics g);
	

	

}
