package Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import ItemsImplementation.BronzeSword;
import Util.ItemFactory;
import saviorOfRealms.errorHandling.ItemNotFoundException;

public class DropTable {

	List<Item> items;
	Random random;
	
	public DropTable( ) {
		items = new ArrayList<Item>();
		random = new Random();
	}

	public void addItem(Item item, Double percentage) {
		items.add(item);
		item.setDropRate(percentage);
	}

	public Item getNextItem() throws ItemNotFoundException {
		double percentage = 1;
		
		
		int itemIDToDrop = 0;
		//TODO need to make this random, instead of getting the same item everytime from droptable.
		//So instead of for loop, do a while loop and percentage is > 0.   
		while(percentage > 0)
		{
			int getItem = (int)(Math.random() * items.size());
//			get a random item from the hashmap.
			percentage -= items.get(getItem).getDropRate();
			itemIDToDrop = items.get(getItem).getItemID();
		}
		
		return ItemFactory.generateItem(itemIDToDrop);
	}
	
}
