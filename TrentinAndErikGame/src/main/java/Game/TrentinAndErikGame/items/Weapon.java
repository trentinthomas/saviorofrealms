package Game.TrentinAndErikGame.items;

public abstract class Weapon extends Item {

	public int minDamage;
	public int maxDamage;
	public double critPercent;
	
	public Weapon(int itemId, int minDamage, int maxDamage) {
		super( itemId, Item.NOT_STACKABLE );
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
	}
	
	public ItemType getItemType()
	{
		return ItemType.WEAPON;
	}
	
	public abstract void attack();

}
