package Util;

import Items.Item;
import Items.Weapon;
import ItemsImplementation.BronzeSword;
import ItemsImplementation.IronSword;
import ItemsImplementation.SteelSword;
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
		else if(itemId == Item.IRON_SWORD_ID)
		{
			Weapon weapon = new IronSword();
			weapon.generateMinAndMaxDamage();
			item = weapon;
		}
		else if(itemId == Item.STEEL_SWORD_ID)
		{
			Weapon weapon = new SteelSword();
			weapon.generateMinAndMaxDamage();
			item = weapon;
		}
		else if(itemId == Item.ARROW_ID)
		{
			//TODO implement arrow item.
		}
		return item;
	}

}
