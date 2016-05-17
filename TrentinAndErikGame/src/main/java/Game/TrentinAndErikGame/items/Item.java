package Game.TrentinAndErikGame.items;

import java.awt.Image;

public abstract class Item {
	
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
	
	protected abstract Image getItemImage();
	
	

}
