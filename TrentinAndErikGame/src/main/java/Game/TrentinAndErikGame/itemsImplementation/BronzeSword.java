package Game.TrentinAndErikGame.itemsImplementation;

import java.awt.Image;

import Game.TrentinAndErikGame.items.Item;
import Game.TrentinAndErikGame.items.Weapon;

public class BronzeSword extends Weapon {

	public BronzeSword(int itemId, int minDamage, int maxDamage) {
		super(itemId, minDamage, maxDamage);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Image getItemImage() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getStackSize() {
		return Item.NOT_STACKABLE;
	}

}
