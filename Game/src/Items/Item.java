package Items;

import java.io.Serializable;

import org.newdawn.slick.Image;

import Entities.Entity;
import Util.GameSessionFactory;

public abstract class Item implements Serializable {
	
	private static final long serialVersionUID = 3556887492644208293L;

	public static final int NOT_STACKABLE = 1;
	
	private int itemId;
	private int sellAmount;
	private float xCoord; //The Coords are only used if it is dropped in the world.
	private float yCoord;
	
	private double dropRate; //for adding to a droptable.
	
	public enum ItemType { ARMOUR, WEAPON, CONSUMABLE, ITEM };
	
	public Item(){
	}
	
	public int getItemId()
	{
		return itemId;
	}
	
	public int getSellAmount()
	{
		return sellAmount;
	}
	
	public ItemType getItemType()
	{
		return ItemType.ITEM;
	}
	
	/**
	 * Drops item based on players coordinates.
	 */
	public void dropItem() {
		setX(GameSessionFactory.getGameSession().getPlayer().getxCoord());
		setY(GameSessionFactory.getGameSession().getPlayer().getyCoord());
		GameSessionFactory.getGameSession().getItemsOnGround().add(this);
	}
	
	/**
	 * Drops item based on the entities x and y coordinate
	 * @param e
	 */
	public void dropItem(Entity e) {
		setX(e.getxCoord());
		setY(e.getyCoord());
		GameSessionFactory.getGameSession().getItemsOnGround().add(this);
	}
	
	public void setX(float xCoord) {
		this.xCoord = xCoord;
	}
	
	public void setY(float yCoord) {
		this.yCoord = yCoord;
	}
	
	public float getX() {
		return xCoord;
	}
	
	public float getY() {
		return yCoord;
	}
	
	public double getDropRate() {
		return dropRate;
	}
	
	public void setDropRate(double dropRate) {
		this.dropRate = dropRate;
	}
	
	public abstract Image getItemImage();
	public abstract int getStackSize();
	public abstract int getItemID();
	
	//Add all Item ID's inside this as static int. Every item ID is unique, so do not have duplicates

	public static int BRONZE_SWORD_ID = 1;
	public static int IRON_SWORD_ID = 2;
	public static int STEEL_SWORD_ID = 3;
	public static int ARROW_ID = 4;
}
