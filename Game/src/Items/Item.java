package Items;

import java.io.Serializable;

import org.newdawn.slick.Image;

public abstract class Item implements Serializable {
	
	private static final long serialVersionUID = 3556887492644208293L;

	public static final int NOT_STACKABLE = 1;
	
	private int itemId;
	private int sellAmount;
	
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
	
	public abstract Image getItemImage();
	public abstract int getStackSize();
	//Add all Item ID's inside this as static int. Every item ID is unique, so do not have duplicates
	
	

	public static int BRONZE_SWORD_ID = 1;
}
