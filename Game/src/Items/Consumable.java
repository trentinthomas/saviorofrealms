package Items;

import java.awt.Image;

public abstract class Consumable extends Item implements Stackable {

	public Consumable(int itemId) {
		super(itemId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ItemType getItemType()
	{
		return Item.ItemType.CONSUMABLE;
	}

}
