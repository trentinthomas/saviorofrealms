package Game.TrentinAndErikGame.items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will create an Inventory for an entity. This keeps track of all the
 * items the entity has, and how much it can carry.
 * @author Trentin
 *
 */
public class Inventory implements Serializable{
	
	private final int DEFAULT_CAPACITY = 20;
	
	
	private int capacity; //how big the inventory is
	private int numItems;
	private Item[] items;
	
	/**
	 * Creates a default Inventory with capacity of 20. Most players will
	 * have the default capacity
	 */
	public Inventory()
	{
		capacity = DEFAULT_CAPACITY;
		numItems = 0;
		items = new Item[capacity];
	}
	
	/**
	 * Creates an inventory with a specified size. Might be useful for enemies.
	 * @param size
	 */
	public Inventory(int size)
	{
		capacity = size;
		numItems = 0;
		items = new Item[capacity];
	}
	
	/**
	 * Increases the capacity of the Inventory. Don't know if we should be using
	 * this kind of method, but might help later down the road.
	 * @param newCapacity
	 */
	public void increaseCapacity(int newCapacity)
	{
		//Will need to grow the array, or make it an ArrayList of items
		this.capacity = newCapacity; 
	}
	
	/**
	 * Adds an item into the inventory. 
	 * @param item
	 * @return
	 */
	public boolean addItem(Item item)
	{
		if(numItems == capacity)
		{
			return false;
		}
		else
		{
			for(int i = 0; i < numItems; i++)
			{
				if(items[i] == null)
				{
					items[numItems] = item;
					numItems++;
					return true;
				}
			}
		}
		return false;
	}

}
