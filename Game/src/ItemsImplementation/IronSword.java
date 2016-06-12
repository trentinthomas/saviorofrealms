package ItemsImplementation;

import org.newdawn.slick.Image;

import Items.Item;
import Items.Weapon;
import Util.Resources;

public class IronSword extends Weapon {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5966874472058830712L;

	public IronSword()
	{
		super();
	}
	
	public IronSword( int minDamage, int maxDamage ) {
		super( minDamage, maxDamage );
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image getItemImage() {
		// TODO Auto-generated method stub
		return Resources.getImage("ironSword");
	}

	public int getStackSize() {
		return Item.NOT_STACKABLE;
	}

	@Override
	public void initializeMinAndMaxDamage() {
		// TODO Auto-generated method stub
		minDamageMin = 3;
		minDamageMax = 4;
		maxDamageMin = 5;
		maxDamageMax = 7;
	}

	public int getItemID() {
		return Item.IRON_SWORD_ID;
	}

}
