package Game.TrentinAndErikGame.items;

public class InventorySlot 
{
	
	private Item item;
	private int numItems;
	
	public void setItem(Item item)
	{
		this.item = item;
	}
	
	public Item getItem()
	{
		return item;
	}
	
	public void setNumItems(int numItems)
	{
		this.numItems = numItems;
	}
	
	public int getNumItems()
	{
		return numItems;
	}
	
	public void addItem()
	{
		numItems++;
	}
	
	public void removeItem()
	{
		numItems--;
	}
	

}
