package Game.TrentinAndErikGame.items;

import java.awt.Image;
import java.io.Serializable;

public abstract class Item implements Serializable{
	
	public static boolean STACKABLE = true;
	public static boolean NOT_STACKABLE = false;
	
	private static final long serialVersionUID = 1L;
	private int itemId;
	private int sellAmount;
	private boolean stackable;
	
	public enum ItemType { ARMOUR, WEAPON, CONSUMABLE, ITEM };
	
	public Item(int itemId, boolean stackable)
	{
		this.itemId = itemId;
		this.stackable = stackable;
	}
	
	public int getItemId()
	{
		return itemId;
	}
	
	public int getSellAmount()
	{
		return sellAmount;
	}
	
	public abstract ItemType getItemType();
	
	protected abstract Image getItemImage();
	
	

}
