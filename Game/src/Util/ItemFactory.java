package Util;

import Items.Item;
import Items.Weapon;
import ItemsImplementation.BronzeSword;
import saviorOfRealms.errorHandling.ItemNotFoundException;

public class ItemFactory {
	
	
	public static Item generateItem(int itemId) throws ItemNotFoundException
	{
		Item item = null;
		if(itemId == Item.BRONZE_SWORD_ID)
		{
			Weapon weapon = new BronzeSword();
			weapon.generateMinAndMaxDamage();
			item = weapon;
		}
		return item;
	}

}
