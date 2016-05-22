package Items;

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
	private List<InventorySlot> items;
	
	/**
	 * Creates a default Inventory with capacity of 20. Most players will
	 * have the default capacity
	 */
	public Inventory()
	{
		capacity = DEFAULT_CAPACITY;
		numItems = 0;
		items = new ArrayList<InventorySlot>(capacity);
	}
	
	/**
	 * Creates an inventory with a specified size. Might be useful for enemies.
	 * @param size
	 */
	public Inventory(int size)
	{
		capacity = size;
		numItems = 0;
		items = new ArrayList<InventorySlot>(capacity);
	}
	
	/**
	 * Increases the capacity of the Inventory. Don't know if we should be using
	 * this kind of method, but might help later down the road.
	 * @param newCapacity
	 */
	public void increaseCapacity(int newCapacity)
	{
		this.capacity = newCapacity; 
	}
	
	/**
	 * Adds an item into the inventory. 
	 * @param item
	 * @return
	 */
	public boolean addItem(Item item)
	{
		for(InventorySlot slot : items)
		{
			//If there is an item already in here, that isnt at max stacksize, add it to this slot.
			if( slot.getItem().getItemId() == item.getItemId() && slot.getNumItems() < item.getStackSize())
			{
				slot.addItem();
				return true;
			}
		}
		//If it gets here, there is no InventorySlot that has less than stacksize. check if there is any empty spots to add the item.
		//TODO could throw a custom Exception such as InventoryFullException.
		if( numItems == capacity )
			return false;
		
		//We are good, add it to next available slot.
		else
		{
			for( InventorySlot slot : items)
			{
				if( slot == null )
				{
					slot = new InventorySlot();
					slot.setItem( item );
					slot.setNumItems( 1 );
				}
			}
			numItems++;
			return true;
		}
	}
	
	public boolean removeItem( Item item )
	{
		for(InventorySlot slot : items)
		{
			if( slot.getItem().getItemId() == item.getItemId() )
			{
				if(slot.getNumItems() > 1)
					slot.removeItem();
				else
					slot = null;
				return true;
			}
		}
		
		//Couldn't find the item, return false. This probably shouldn't happen. We could
		//make a new exception called ItemNotFoundException
		//TODO review this and decide if worth to throw exception.
		return false;
	}

}
