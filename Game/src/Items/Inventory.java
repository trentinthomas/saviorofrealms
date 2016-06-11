package Items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;

import Util.Camera;
import Util.GameSessionFactory;
import Util.Window;

/**
 * This class will create an Inventory for an entity. This keeps track of all the
 * items the entity has, and how much it can carry.
 * @author Trentin
 *
 */
public class Inventory implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7526329381196867830L;


	private final int DEFAULT_CAPACITY = 20;
	
	private final float INITIAL_X_COORD = 1095;
	private final float INITIAL_Y_COORD = 536;
	private final float X_COORD_SPACING = 45;
	private final float Y_COORD_SPACING = 45;
	
	
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
		
//		for(int i = 0; i < capacity; i++)
//			items.add(new InventorySlot());
		
		float xCoord = INITIAL_X_COORD;
		float yCoord = INITIAL_Y_COORD;
		int row = 0;
		int column = 0;
		for( int i = 0; i < capacity; i++ ) {
			InventorySlot slot = new InventorySlot(xCoord, yCoord);
			xCoord += X_COORD_SPACING;
			column++;
			if(column == 4) {
				xCoord = INITIAL_X_COORD;
				yCoord += Y_COORD_SPACING;
				column = 0;
				row++;
			}
			items.add(slot);
		}
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
			if( slot.getItem() != null && slot.getItem().getItemId() == item.getItemId() && slot.getNumItems() < item.getStackSize())
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
				if( slot == null || slot.getItem() == null)
				{
					slot.setItem( item );
					slot.setNumItems( 1 );
					break;
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
	
	public void drawInventoryItems(Graphics g, Camera cam) {
		for( InventorySlot slot : items ) {
			if(slot.getItem() != null) {
				g.drawImage( slot.getItem().getItemImage(), cam.getX() + slot.getxCoord(), cam.getY() + slot.getyCoord() );
			}
		}
	}

}
