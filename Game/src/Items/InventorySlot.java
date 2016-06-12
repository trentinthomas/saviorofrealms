package Items;

import java.io.Serializable;

public class InventorySlot implements Serializable
{
	
	private static final long serialVersionUID = 2851985060611918082L;
	private Item item;
	private int numItems;
	
	private float xCoord; //These are for drawing purposes.
	private float yCoord;
	
	public InventorySlot() {
		numItems = 0;
		xCoord = 500;
		yCoord = 600;
		item = null;
	}
	
	public InventorySlot(float xCoord, float yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	
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
	
	public float getxCoord() {
		return xCoord;
	}
	
	public float getyCoord() {
		return yCoord;
	}
	

}
