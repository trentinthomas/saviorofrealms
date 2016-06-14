package UI;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

public class GraphicButton extends AbstractComponent{

	protected int width;
	protected int height;
	protected int x;
	protected int y;
	protected int mouseX;
	protected int mouseY;
	protected boolean pressed;
	protected Shape hitbox;
	protected boolean over;
	protected String text;
	
	protected Image buttonUp;
	protected Image buttonHover;
	protected Image currentImage;
	
	protected int buttonId;
	
	public GraphicButton(GUIContext container, int x, int y, Image buttonUp, Image buttonHover) {
	    super(container);
	    setLocation(x, y);
	    this.width = buttonUp.getWidth();
	    this.height = buttonUp.getHeight();
	    this.buttonUp = buttonUp;
	    this.buttonHover = buttonHover;
	    hitbox = new Rectangle(x, y, width, height);
	    currentImage = buttonUp;
	}
	
	public GraphicButton(GUIContext container, int x, int y, Image buttonUp, Image buttonHover, String text) {
	    super(container);
	    setLocation(x, y);
	    this.width = buttonUp.getWidth();
	    this.height = buttonUp.getHeight();
	    this.buttonUp = buttonUp;
	    this.buttonHover = buttonHover;
	    this.text = text;
	    hitbox = new Rectangle(x, y, width, height);
	    currentImage = buttonUp;
	}
	
	@Override
	public int getHeight() {
	    return height;
	}
	
	@Override
	public int getWidth() {
	    return width;
	}
	
	@Override
	public int getX() {
	    return x;
	}
	
	@Override
	public int getY() {
	    return y;
	}
	
	public boolean isPressed() {
	    return pressed;
	}
	public void update(GUIContext container) {
	    mouseX = container.getInput().getMouseX();
	    mouseY = container.getInput().getMouseY();
	    over = hitbox.contains(mouseX, mouseY);
	
	    if (over && container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
	        pressed = true;
	    }else{
	        pressed = false;
	    }
	}
	
	@Override
	public void render(GUIContext container, Graphics g) throws SlickException {
		setCurrentImage();
		g.drawImage(currentImage, x, y);
	}
	
	@Override
	public void setLocation(int x, int y) {
	    this.x = x;
	    this.y = y;
	    hitbox = new Rectangle(x, y, width, height);
	}
	
	public void setCurrentImage() {
		currentImage = over ? buttonHover : buttonUp;
	}
	
	public void setButtonId(int buttonId) {
		this.buttonId = buttonId;
	}
	
	public int getButtonId() {
		return buttonId;
	}
	
	
}