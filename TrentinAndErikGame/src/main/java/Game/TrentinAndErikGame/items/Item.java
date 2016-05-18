package Game.TrentinAndErikGame.items;

import java.awt.Image;
import java.io.Serializable;

public abstract class Item implements Serializable, Stackable{
	
	private static final long serialVersionUID = 1L;
	
	public static final int NOT_STACKABLE = 1;
	
	private int itemId;
	private int sellAmount;
	
	public enum ItemType { ARMOUR, WEAPON, CONSUMABLE, ITEM };
	
	public Item(int itemId)
	{
		this.itemId = itemId;
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
	
	protected abstract Image getItemImage();
	
	
	//Add all Item ID's inside this as static int. Every item ID is unique, so do not have duplicates
	
	

}
