package saviorOfRealms.Tiles;

import org.newdawn.slick.Image;

public abstract class Tile {
	
	public static boolean SOLID = true;
	public static boolean NOT_SOLID = false;
	public static float TILE_WIDTH = 32;
	public static float TILE_HEIGHT = 32;
	
	protected boolean solid;
	protected float x;
	protected float y;
	protected float width;
	protected float height;
	
	public Tile(boolean solid, float x, float y, float width, float height) {
		this.solid = solid;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean isSolid() {
		return solid;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public abstract Image getImage();

}
