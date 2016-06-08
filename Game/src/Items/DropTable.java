package Items;

import java.util.HashMap;
import java.util.Random;

import ItemsImplementation.BronzeSword;
import Util.ItemFactory;
import saviorOfRealms.errorHandling.ItemNotFoundException;

public class DropTable {

	HashMap<Integer, Double> dropTable;
	
	public DropTable( ) {
		dropTable = new HashMap<Integer, Double>();
	}

	public void addItem(Integer itemId, Double percentage) {
		dropTable.put(itemId, percentage);
	}

	public Item getNextItem() throws ItemNotFoundException {
		double percentage = 1;
		
		
		int itemIDToDrop = 0;
		//TODO need to make this random, instead of getting the same item everytime from droptable.
		//So instead of for loop, do a while loop and percentage is > 0.   
//		while(percentage > 0)
//		{
//			Random random = new Random();
			//get a random item from the hashmap.
//		}
		
		for( int itemId : dropTable.keySet() )
		{
			percentage -= dropTable.get(itemId);
			if(percentage < 0)
			{
				itemIDToDrop = itemId;
				break;
			}
		}
		
		return ItemFactory.generateItem(itemIDToDrop);
	}
	
}
